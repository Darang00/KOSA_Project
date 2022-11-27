package kr.or.kosa.service.user;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;

public class LoveCount implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		int lovecount = 0;
		
		try {
			MemberDao memberDao;
			memberDao = new MemberDao();
			
			System.out.println("LoveCount 진입 성공");

			int idx = Integer.parseInt(request.getParameter("idx"));
			System.out.println("idx parameter: " + idx);

			lovecount = memberDao.loveCount(idx);
			request.setAttribute("lovecount", lovecount);
			System.out.println(lovecount);
			

		} catch (NamingException e) {
			e.printStackTrace();
		}

		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/board_content.jsp");
		
		System.out.println("카운트 포워드 직전");
		
		return forward;
	}

}
