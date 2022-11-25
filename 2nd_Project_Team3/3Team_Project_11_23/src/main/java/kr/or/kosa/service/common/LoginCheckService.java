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

public class LoginCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			HttpSession session = request.getSession();//세션객체생성은 로그인체크에서만.
			//파라미터
			String userid = request.getParameter("userid");
			String pwd = request.getParameter("pwd");
			String idCheck=null;
			
			CommonDao commonDao = new CommonDao();
			AdminDao adminDao = new AdminDao();
			
			List<Member> memberList;
			
			boolean idStatus = commonDao.idStatus(userid); //정지된 계정을 화인할것이다.
		
			if(idStatus == false) {
				//정지된 계정이면 메세지 정지 출력후 로그인창으로 리턴한다. 
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/common/login.jsp");
				return forward;
			}
			boolean success = commonDao.idCheck(userid, pwd); // 추후에 패스워드 틀리는 횟수가 많은 경우 정지 기능 만들것.
			
			if(success == true) {

				session.setAttribute("userid", userid); //세션ID 생성
				
				if(userid.equals("admin")) {
					memberList = adminDao.getAllTableList();
		     		request.setAttribute("memberList", memberList);
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("/WEB-INF/views/admin/admin.jsp");
				}
				else {
					List<Category> categoryList;
					categoryList = commonDao.getAllCategory();
					request.setAttribute("categoryList", categoryList);
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("/WEB-INF/views/web/web.jsp");//사용자 페이지
				}
			}
			else {
				forward.setPath("/WEB-INF/views/common/login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
