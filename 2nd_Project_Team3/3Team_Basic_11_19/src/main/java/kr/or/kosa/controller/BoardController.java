package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part; // 이건 뭐에요??

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.board.BoardListService;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardController() {
        super();

    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());

    	
    	Action action=null;
    	ActionForward forward=null;
    	
    		
    	if(url_Command.equals("/BoardList.board")) { //해당 게시판의 게시글 전체 조회
    		System.out.println("BoardController에서 BoardList.board 호출 성공");
    		action = new BoardListService();
    		forward = action.execute(request, response);    		
    	}else if(url_Command.equals("/BoardWrite.do")) { //만약 있다면 상세보기
    		//UI 제공 ...
    		//예) /WEB-INF/views/memoview.jsp 가정
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/board/board_write.jsp");
    	}
		/*
		 * else if(url_Command.equals("/BoardWriteOK.do")) { //만약 있다면 상세보기 //UI 제공 ...
		 * //예) /WEB-INF/views/memoview.jsp 가정 action = new BoardAddService(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/BoardContent.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정 action = new BoardContentService(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/ReplyOk.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정
		 * 
		 * action = new ReplyAddService(); forward = action.execute(request, response);
		 * }else if(url_Command.equals("/BoardEdit.do")) { //만약 있다면 상세보기 //UI 제공 ...
		 * //예) /WEB-INF/views/memoview.jsp 가정 action = new BoardEditService(); forward
		 * = action.execute(request, response); }else
		 * if(url_Command.equals("/BoardEditOk.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정 action = new BoardEditOk(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/BoardDelete.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정 action = new BoardDeleteService(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/BoardDeleteOk.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정 action = new BoardDeleteOk(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/BoardRewrite.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정 action = new BoardRewriteService(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/BoardRewriteOk.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정 action = new BoardRewriteOk(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/ReplyDeleteOk.do")) { //만약 있다면 상세보기 //UI 제공 ... //예)
		 * /WEB-INF/views/memoview.jsp 가정 action = new ReplyDeleteOk(); forward =
		 * action.execute(request, response); }else
		 * if(url_Command.equals("/fileDetailList.do")) { //파일 상세보기 비동기 action = new
		 * ReplyFileService(); forward = action.execute(request, response); }
		 */
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			//UI
    			//UI + 로직
    			//forward url 주소 변환 없어 View 내용을 받을 수 있다
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
