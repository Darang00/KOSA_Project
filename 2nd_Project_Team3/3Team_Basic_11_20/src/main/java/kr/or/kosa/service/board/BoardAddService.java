package kr.or.kosa.service.board;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class BoardAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer"); //board_write.jsp에서 
		// String boardname = request.getParameter("boardname");
	   //String homepage = request.getParameter("homepage");
		String content = request.getParameter("content");
		//String pwd = request.getParameter("pwd");
		String filename = request.getParameter("filename");
		
		int result = 0;

		Board board = new Board();
		
		board.setUserid(writer);
		//board.setBoardname(boardname);
		board.setTitle(title);
		board.setContent(content);
		//board.setReadnum(readnum);
		
		try {
			BoardDao dao = new BoardDao();
			
			result = dao.writeok(board);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		

		// write.jsp 화면 >> writeok.jsp 처리 >> service >> dao > DB 작업 >
		// return dao > return service > writeok.jsp 결과처리 >> 이동 (공통) >> redirect.jsp

		String msg = "";
		String url = "";
		if (result > 0) {
			msg = "insert success";
			url = "BoardList.board";
		} else {
			msg = "insert fail";
			url = "BoardWrite.board";
		}

		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/redirect.jsp");

		return forward;

	}

}
