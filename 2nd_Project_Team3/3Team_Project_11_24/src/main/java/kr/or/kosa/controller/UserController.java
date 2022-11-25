package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.user.BoardAddService;
import kr.or.kosa.service.user.BoardContentService;
import kr.or.kosa.service.user.BoardListService;
import kr.or.kosa.service.user.BoardViewService;
import kr.or.kosa.service.user.BookMarksListService;
import kr.or.kosa.service.user.BookMarksService;
import kr.or.kosa.service.user.UpdateService;


@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserController() {
        super();
        
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
       	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	Action action=null;
    	ActionForward forward=null;
    	Action action1 = null;
    	ActionForward forward1=null;

    	if(urlcommand.equals("/index.user")) {   //메인페이지(홈버튼)
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/index.jsp");
	    }else if (urlcommand.equals("/update.user")) {
    		//action = new updateViewService();
    		forward = new ActionForward();	   //메인페이지에서 로그인 가능
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/web/edit.jsp");
		} else if (urlcommand.equals("/updateOK.user")) {
			action = new UpdateService();
			forward = action.execute(request, response);
		}else if(urlcommand.equals("/boardList.user")){//게시글 리스트출력
    		action = new BoardListService();
    		forward = action.execute(request, response);
	    }else if(urlcommand.equals("/boardWrite.user")){//게시글 작성
	    	action = new BoardViewService();
    		forward = action.execute(request, response);
	    }else if(urlcommand.equals("/boardWriteOK.user")) {
    		action = new BoardAddService();
    		forward = action.execute(request, response);
    	}else if(urlcommand.equals("/boardContent.user")) { //만약 있다면 상세보기
    		action = new BoardContentService();
    		forward = action.execute(request, response);
    	}else if(urlcommand.equals("/bookMarksList.user")) { //userHeader.jsp 에서 북마크 목록 버튼 눌리면 일로 온다
    		System.out.println("boardMarksList.user 컨트롤러 탔다^^ 이제 서비스 호출할거다");
    		action = new BookMarksListService();
    		forward = action.execute(request, response);
    	}
    	else if(urlcommand.equals("/bookMarks.user")) { //board_content에서 북마크 버튼 눌리면 일로 온다
    		System.out.println("boardMarks.user 컨트롤러 탔다^^ 이제 서비스 호출할거다");
    		action = new BookMarksService();
    		forward = action.execute(request, response);
    	}

    	
    
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
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
