package kr.or.kosa.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Member;


public class SessionCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
	
		
		try {
			HttpSession session = request.getSession();//세션객체생성은 로그인체크에서만.
			//파라미터
			String id = (String)session.getAttribute("id");
			String idCheck=null;

			UserDao dao = new UserDao();
			List<Member> memberList;
			if(id.equals("admin")) {
				memberList = dao.getAllTableList();
	     		request.setAttribute("memberlist", memberList);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/admin/admin.jsp");
			}
			else {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/web/web.jsp");//사용자 페이지
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
