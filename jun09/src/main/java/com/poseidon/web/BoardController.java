package com.poseidon.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poseidon.web.util.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private Util util;
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
	//글 수정 저장 repairPost POST
	@PostMapping(value = "repairPost")
	public String repairPost2(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		//필요한 값 : 세션, title, content, b_no
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null && request.getParameter("b_no") != null 
			&& request.getParameter("title") != null && request.getParameter("content") != null) {
			
			//값이 다 있다면. DTO에 담기 -> DB
			BoardDTO dto = new BoardDTO();
			dto.setB_content(request.getParameter("content"));;
			dto.setB_title(request.getParameter("title"));
			dto.setB_no(util.str2Int(request.getParameter("b_no")));
			dto.setU_id((String)session.getAttribute("id"));
			
			//저장하기 
			int result = boardService.repairPost(dto);
			if(result == 1) {
				return "redirect:/success";//삭제 성공했습니다. 			
			}else {
				return "redirect:/failure";//실패했습니다. 다시 시도하세요.
			}
		}else {			
			return "redirect:/failure";
		}
	}
	
	//글수정  repairPost?b_no=12
	@GetMapping(value = "/repairPost")
	public ModelAndView repairPost(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("repairPost");
		//b_no    session
		HttpSession session = request.getSession();
		if(request.getParameter("b_no") != null && session.getAttribute("id") != null) {
			//dto에 담아서 mv에 붙이기 = /detail
			//변경필 -> 글번호만 맞으면 다른 사람 글도 가져옵니다. 수정해야 합니다.
			BoardDTO repairPost = new BoardDTO();
			repairPost.setB_no(util.str2Int(request.getParameter("b_no")));
			repairPost.setU_id((String)session.getAttribute("id"));
			
			BoardDTO dto = boardService.detail(   repairPost    );//int -> dto
			System.out.println(dto);//나오는 결과값을 꼭 확인해주세요.
			
			if(dto != null){				
				mv.addObject("dto", dto);
			}
			
		}else {
			//다른 페이지로 이동시키기 
			mv.setViewName("redirect:/failure");
		}
		return mv;
		
	}
	
	
	//"./deletePost?b_no=${detail.b_no}";
	//해당 메소드를 실행할 때 들어오는 필수값 잡기 @RequestParam(value="b_no")
	//없을 경우 에러가 납니다. required=false, defaultValue="0" 기본값
	@GetMapping(value = "/deletePost")
	public String deletePost(@RequestParam(value="b_no", required=false, defaultValue="0") int b_no, HttpSession session) {
		//System.out.println("b_no : " + b_no);
		//System.out.println("id : " + session.getAttribute("id"));
		int result = 0;
		if(b_no > 0 && session.getAttribute("id") != null){
			//모든 값이 들어온다면 데이터베이스로 값 보내기
			BoardDTO dto = new BoardDTO();
			dto.setB_no(b_no);
			dto.setU_id((String) session.getAttribute("id"));
			
			result = boardService.deletePost(dto);
			//System.out.println("처리결과 : " + result);
		}
		if(result == 1) {
			return "redirect:/success";//삭제 성공했습니다. 			
		}else {
			return "redirect:/failure";//실패했습니다. 다시 시도하세요.
		}
		//return "redirect:/board";
	}
	
	
	//commentRepair
	@PostMapping(value = "/commentRepair")
	public String commentRepair(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		//System.out.println("comment : " + request.getParameter("comment"));
		//System.out.println("b_no : " + request.getParameter("b_no"));
		//System.out.println("c_no : " + request.getParameter("c_no"));
		//세션만들기
		HttpSession session = request.getSession();
		//데이터베이스에 반영하기 -> service -> dao -> sqlSession -> xml
		CommentDTO dto = new CommentDTO();
		dto.setB_no(util.str2Int(request.getParameter("b_no")));
		dto.setC_no(util.str2Int(request.getParameter("c_no")));
		dto.setC_content(util.html(request.getParameter("comment")));
		dto.setU_id((String) session.getAttribute("id"));//세션도 저장해주세요 
		
		boardService.commentUpdate(dto);
		
		return "redirect:/detail?b_no=" + request.getParameter("b_no");	
	}
	
	//   ./comment 글쓰기 post
	@PostMapping(value = "/comment")
	public String comment(HttpServletRequest request) throws UnsupportedEncodingException {
		//한글설정
		request.setCharacterEncoding("UTF-8");
		
		// 로그인 했느냐? 세션검사 -> 세션객체
		HttpSession session = request.getSession();
		// b_no, comment 오는지?
		if(session.getAttribute("id") != null && request.getParameter("b_no") != null 
				&& request.getParameter("comment") != null) {
			//다 있다면? -> DB에 저장하기 -> CommentDTO에 저장해서 보내기
			CommentDTO dto = new CommentDTO();
			dto.setB_no(		Integer.parseInt(request.getParameter("b_no"))	);
			
			//html tag막기, 줄바꿈 설정하기
			String comment = request.getParameter("comment");
			comment = util.html(comment);
			dto.setC_content(		comment			);
			//dto.setC_content(		util.html(request.getParameter("comment"))			);
			
			dto.setU_id(		(String) session.getAttribute("id")				);
		
			//DB로 보내기 -> Service -> DAO -> sqlSession -> 
			boardService.commentWrite(dto);
		}		
		
		return "redirect:/detail?b_no=" + request.getParameter("b_no");//되돌아가기
	}
	
	
	//댓글 삭제./commentDelete?b_no=11&c_no=15
	@GetMapping(value = "/commentDelete")
	public String commentDelete(HttpServletRequest request) {
		//System.out.println("b_no : " + request.getParameter("b_no"));
		//System.out.println("c_no : " + request.getParameter("c_no"));
		//잠시 뒤에 수정
		
		//c_no, b_no, u_id
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null && request.getParameter("b_no") != null
				&& request.getParameter("c_no") != null) {
			CommentDTO dto = new CommentDTO();
			dto.setB_no(util.str2Int(request.getParameter("b_no")));
			dto.setC_no(util.str2Int(request.getParameter("c_no")));
			dto.setU_id((String)session.getAttribute("id"));
			
			boardService.commentDelete(dto);
		}
		
		return "redirect:/detail?b_no=" + request.getParameter("b_no");
	}
	
	
	
	
	//상세보기 화면 /detail?b_no=154
	@GetMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("b_no") int b_no) {
		ModelAndView mv = new ModelAndView("detail");//jsp
		//System.out.println("들어오는 b_no : " + b_no);
		//DB로 보내서 값이 있는지 확인하기
		BoardDTO detail = new BoardDTO();
		detail.setB_no(b_no);
		
		//조회수 올리기
		//boardService.countUp(b_no);//받아오는 값 없음.
		
		BoardDTO dto = boardService.detail(detail);
		mv.addObject("detail", dto);//붙이기
		//System.out.println(dto);
		
		//댓글 출력 2022-06-14
		//능력단위명 : 네트워크 프로그래밍 구현
		//능력단위요소 : 기능 구현하기
		//b_no에 쓴 댓글이 있는지 질의
		List<CommentDTO> cList = boardService.commentList(b_no);//154 전체
		mv.addObject("cList", cList);
		
		return mv;
	}
	
	
	//글쓰기 화면 나오게 하기
	//화면만 이동하려면 String으로 하시면 편합니다.
	@GetMapping(value = "/write")
	public String write(HttpSession session) {
		if(session.getAttribute("id") != null) {
			return "write";			
		}else {
			return "redirect:/login";
		}
	}
	@PostMapping(value = "/write")
	public String write(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		//각 데이터가 들어오지 않는다면 모두 failure로 보내기 작업만들어주세요.
		if(session.getAttribute("id") != null 
				&& request.getParameter("title") != null 
				&& request.getParameter("content") != null){
			
			//해야 할 일. -> DB로...
			BoardDTO write = new BoardDTO();
			write.setB_title(request.getParameter("title"));
			write.setB_content(request.getParameter("content"));
			write.setU_id((String) session.getAttribute("id"));
			
			//데이터베이스로 보내기
			int result = boardService.write(write);
			System.out.println("처리결과? " + result);

			if(result == 1) {
				return "redirect:/success";//글쓰기에 성공했습니다. 			
			}else {
				return "redirect:/failure";//실패했습니다. 다시 시도하세요.
			}
			
		}else {
			return "redirect:/failure";//실패했습니다. 다시 시도하세요. 
		}
				
		
		
	}
	
	@GetMapping(value = "/success")
	public String success() {
		return "success";
	}
	
	@GetMapping(value = "/failure")
	public String failure() {
		return "failure";
	}
	
	
	
	
	@RequestMapping(value = "/board")
	public ModelAndView board(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board");//jsp
		//값 보내기
		//mv.addObject("test", "테스트");//이름, 값
		//service에게 일 시키기
		
		//카테고리 잡기
		int b_cate = 1;
		if(request.getParameter("b_cate") != null) {
			b_cate = util.str2Int(request.getParameter("b_cate"));
		}
		
		//전자정부페이징 사용하기
		int pageNo = 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		//recordCountPageNo 한 페이지당 게시되는 게시물 수 yes
		int listScale = 10;
		//pageSize = 페이지 리스트에 게시되는 페이지 수 yes
		int pageScale = 10;
		//totalRecordCount  전체 게시물 건수 yes
		int totalCount = boardService.totalCount(b_cate);
		
		//System.out.println("totalCount : " + totalCount);
		
		//전자정부페이징 호출
		PaginationInfo paginationInfo = new PaginationInfo();
		//값대입
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		paginationInfo.setTotalRecordCount(totalCount);
		//전자정부 계산하기
		int startPage = paginationInfo.getFirstRecordIndex();
		int lastPage = paginationInfo.getRecordCountPerPage();
		
		//서버로 보내기
		PageDTO page = new PageDTO();
		page.setStartPage(startPage);
		page.setLastPage(lastPage);
		page.setB_cate(b_cate);
		
		
		List<BoardDTO> boardList = boardService.boardList(page);
		mv.addObject("boardList", boardList);
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("pageNo", pageNo);
		mv.addObject("b_cate", b_cate);
		return mv;
	}

}
