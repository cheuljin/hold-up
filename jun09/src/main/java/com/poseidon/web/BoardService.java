package com.poseidon.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;

	public List<BoardDTO> boardList(PageDTO page) {
		return boardDAO.boardList(page);
	}

	public BoardDTO detail(BoardDTO repairPost) {//원래 int가 오는 것을 dto로 변경하고 있습니다. /detail도 변경해야 해요.
		return boardDAO.detail(repairPost);
	}

	public int write(BoardDTO write) {
		return boardDAO.write(write);
	}

	public int totalCount(int b_cate) {
		return boardDAO.totalCount(b_cate);
	}

	public List<CommentDTO> commentList(int b_no) {
		return boardDAO.commentList(b_no);
	}

	public void commentWrite(CommentDTO dto) {
		boardDAO.commentWrite(dto);
	}

	public void commentDelete(CommentDTO dto) {
		boardDAO.commentDelete(dto);
	}

	public void commentUpdate(CommentDTO dto) {
		boardDAO.commentUpdate(dto);
	}

	public int deletePost(BoardDTO dto) {
		return boardDAO.deletePost(dto);
	}

	public int repairPost(BoardDTO dto) {
		return boardDAO.repairPost(dto);
	}

}
