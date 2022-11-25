package kr.or.kosa.service.user;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Bookmarks;

public class BookMarksListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			System.out.println("BookMarksListService try문 진입");
			
			MemberDao memberdao = new MemberDao();
			System.out.println("new MemberDao() 성공!");
			
			Bookmarks bookmarks = new Bookmarks();
			System.out.println("new Bookmarks() 성공!");
			
			HttpSession session = request.getSession();
			
			int idx = Integer.parseInt(request.getParameter("idx")); //board_content.jsp의 name을 getParameter로 가져온다
			System.out.println(idx);
			String userid = request.getParameter("userid");
			System.out.println(userid);
			String title = request.getParameter("title");
			System.out.println(title);
			String writedate = request.getParameter("writedate");
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
			
			//memberdao.getBookmarksList(bookidx, userid, idx);
			
		
			
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/web/bookmarkList.jsp");
			

		} catch (Exception e) {
			System.out.println("The error e is " + e.getMessage());
			e.getStackTrace();
		}
		
		
		return forward;
	}

}
