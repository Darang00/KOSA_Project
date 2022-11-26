package kr.or.kosa.service.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.AdminDao;
import kr.or.kosa.dao.CommonDao;
import kr.or.kosa.dto.Category;
import kr.or.kosa.dto.Member;

public class SessionCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
	
		
		try {
			HttpSession session = request.getSession();//세션객체생성은 로그인체크에서만.
			//파라미터
			String userid = (String)session.getAttribute("userid");
			String idCheck=null;

			AdminDao adminDao = new AdminDao();
			List<Member> memberList;
			if(session == null) {
				CommonDao commonDao = new CommonDao();
				List<Category> categoryList;
				categoryList = commonDao.getAllCategory();
				request.setAttribute("categoryList", categoryList);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/index.jsp");//사용자 페이지
			}
			if(userid.equals("admin")) {
				memberList = adminDao.getAllTableList();
	     		request.setAttribute("memberList", memberList);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/admin/admin.jsp");
			}
			else {
				CommonDao commonDao = new CommonDao();
				List<Category> categoryList;
				categoryList = commonDao.getAllCategory();
				request.setAttribute("categoryList", categoryList);
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
