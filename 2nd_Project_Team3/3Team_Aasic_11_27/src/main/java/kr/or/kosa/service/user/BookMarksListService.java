package kr.or.kosa.service.user;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Bookmarks;
import kr.or.kosa.utils.ThePager;

public class BookMarksListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();//세션객체생성은 로그인체크에서만.

		try {
			MemberDao memberDao = new MemberDao();
			String userid = (String)session.getAttribute("userid");
			String boardname = request.getParameter("boardname");
			// 게시물 총 건수
			int totalbookmarkcount = memberDao.totalBookmarksCount(userid);
			// 상세보기 >> 다시 LIST 넘어올때 >> 현재 페이지 설정
			String ps = request.getParameter("ps"); // pagesize
			String cp = request.getParameter("cp"); // current page
			System.out.println(ps);
			System.out.println(cp);
			System.out.println(userid);
			// List 페이지 처음 호출 ... 기본 값 설정
			if (ps == null || ps.trim().equals("")) {
				// default 값 설정
				ps = "5"; // 5개씩
			}
			// List 페이지 처음 호출 ... 기본 값 설정
			if (cp == null || cp.trim().equals("")) {
				// default 값 설정
				cp = "1"; // 1번째 페이지 보겠다
			}
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;

			// 23건 % 5
			if (totalbookmarkcount % pagesize == 0) {
				pagecount = totalbookmarkcount / pagesize; // 20 << 100/5
			} else {
				pagecount = (totalbookmarkcount / pagesize) + 1;
			}

			// 102건 : pagesize=5 >> pagecount=21페이지

			// 카테고리별 목록 가져오기

			List<Bookmarks> bookmarklist = memberDao.BookmarksList(cpage, pagesize, userid); // list >> 1 , 20
			System.out.println(bookmarklist.toString());
			int pagersize = 3; // [1][2][3]
//			ThePager pager = new ThePager(totalboardcount,cpage,pagesize,pagersize,"BoardList.board");

			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("bookmarklist", bookmarklist);
			request.setAttribute("totalboardcount", totalbookmarkcount);
			request.setAttribute("userid", userid);
			request.setAttribute("boardname", boardname);
//			request.setAttribute("pager", pager);

			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/web/bookMarkList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
