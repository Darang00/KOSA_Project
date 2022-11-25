package kr.or.kosa.service.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Bookmarks;

public class BookMarksService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			System.out.println("BookMarksService try문 진입");
			
			MemberDao memberdao = new MemberDao();
			System.out.println("new MemberDao() 성공!");
			
			Bookmarks bookmarks = new Bookmarks();
			System.out.println("new Bookmarks() 성공!");
			
			//int bookMarking =-1;
//			int bookMarkChceck //dao 체크까지; 참 1 아니면 0
			
			int idx = Integer.parseInt(request.getParameter("idx")); //board_content.jsp의 name을 getParameter로 가져온다
			System.out.println(idx);
			String userid = request.getParameter("userid");
			System.out.println(userid);
			String title = request.getParameter("title");
			System.out.println(title);
			java.sql.Date writedate = java.sql.Date.valueOf(request.getParameter("writedate"));
			System.out.println(writedate);
			//int lovenum = Integer.parseInt(request.getParameter("lovenum"));
			//System.out.println(lovenum);
			System.out.println("ids, userid, title, writedate 각각에 getParameter 성공");
			
			bookmarks.setIdx(idx);
			bookmarks.setUserid(userid);
			bookmarks.setTitle(title);
			bookmarks.setWritedate(writedate);
			//bookmarks.setLovenum(lovenum);
			
			
			System.out.println("Bookmarks dto에 idx, userid, title, writedate, lovenum set 성공");
			
			int resultrow = memberdao.bookmarkCheck(bookmarks);
			
			if(resultrow ==0) {
				//북마크 하기
				
				int bookMarking = memberdao.bookmarkUpdate(bookmarks);
				//bookMarking =1;
				
				request.setAttribute("bookMarking", bookMarking);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/board/board_content.jsp");
				
			} else if(resultrow ==1) {
				//이미 북마크 한 경우
				int bookMarking = memberdao.bookmarkUpdate(bookmarks);
				
				//bookMarking =0;
				
				request.setAttribute("bookMarking", bookMarking);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/board/board_content.jsp");
			}
			
			
			/*request.setAttribute("bookMarking", bookMarking);
			forward = new ActionForward();
			forward.setRedirect(false); //필수 //redirect 를 끈다
			
			forward.setPath("/WEB-INF/views/web/board_content.jsp"); */
//
//			request.setAttribute("chceck1", chceck);
//			forward.setRedirect(false); //필수 //redirect 를 끈다
//			forward.setPath("/WEB-INF/views/web/board_content.jsp");
		} catch (Exception e) {
//			System.out.println("The error e is " + e.getMessage());
			e.getStackTrace();
		}
		
		
		return forward;
	}
	

}
