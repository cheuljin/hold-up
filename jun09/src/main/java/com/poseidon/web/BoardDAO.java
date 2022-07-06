package com.poseidon.web;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	//연결
	@Autowired
	private SqlSession sqlSession;

	public List<BoardDTO> boardList(PageDTO page) {
		return sqlSession.selectList("board.boardList", page);//board.xml 네임스페이스.id
	}

	public BoardDTO detail(BoardDTO repairPost) {//int -> dto로 변경하고 있어요.
		//countUp 조회수 올리기
		sqlSession.update("board.countUp", repairPost);
		return sqlSession.selectOne("board.detail", repairPost);
	}

	public int write(BoardDTO write) {
		return sqlSession.insert("board.write", write);
	}

	public int totalCount(int b_cate) {
		return sqlSession.selectOne("board.totalCount", b_cate);
	}

	public List<CommentDTO> commentList(int b_no) {
		return sqlSession.selectList("board.commentList", b_no);
	}

	public void commentWrite(CommentDTO dto) {
		sqlSession.insert("board.commentWrite", dto);
	}

	public void commentDelete(CommentDTO dto) {
		sqlSession.delete("board.commentDelete", dto);
	}

	public void commentUpdate(CommentDTO dto) {
		sqlSession.update("board.commentUpdate", dto);
	}

	public int deletePost(BoardDTO dto) {
		return sqlSession.delete("board.deletePost", dto);
	}

	public int repairPost(BoardDTO dto) {
		return sqlSession.update("board.repairPost", dto);
	}

}


/*
 * Controller -> Service -> DAO -> sqlSession
 * 
 * 
 *클래스 생성하기 
 * @Controller : 객체 생성, Controller 역활을 합니다.
 * @Service    : 객체 생성, Service
 * @Repository : 객체 생성, DAO
 * @Component  : 객체 생성, 그 외
 * 
 * 
 *객체 연결하기
 * @Autowired : 타입으로 검색해서 넣어주기 = 스프링에서 제공
 * @Inject    : 타입으로 검색해서 = 자바가 제공합니다
 * @Resource  : name으로 검색해서 = 스프링에서 제공
 */ 
