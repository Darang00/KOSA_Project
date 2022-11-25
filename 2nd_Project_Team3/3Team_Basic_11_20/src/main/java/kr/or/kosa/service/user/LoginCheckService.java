package kr.or.kosa.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.MemberDetail;

public class LoginCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		
		try {
			HttpSession session = request.getSession();//세션객체생성은 로그인체크에서만.
			//파라미터
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String idCheck=null;
			
			
			MemberDetail memberDto = new MemberDetail();
			UserDao dao = new UserDao();
			List<MemberDetail> MemberList;
			boolean success = dao.idCheck(id, pwd);

			
			if(success == true) {
				memberDto.setUserid(id);
				memberDto.setPwd(pwd);
				
				session.setAttribute("id", id); //세션ID 생성
				
				if(id.equals("admin")) { //관리자 아이디
					
					forward = new ActionForward();
					forward.setRedirect(false);
					
					System.out.println("관리자 로그인 성공");
					forward.setPath("/WEB-INF/views/admin/admin.jsp");//관리자 페이지
	
				}
				else { //관리자 아이디가 아닌 아이디가 아닌 모든 아이디 = 사용자
					forward = new ActionForward();
					forward.setRedirect(false);
					
					System.out.println("회원 로그인 성공");
					forward.setPath("/WEB-INF/views/web/web.jsp");//사용자 페이지
				}
			}
			else {
				System.out.println("아이디, 비밀번호를 다시 확인하세요");
				forward.setPath("/WEB-INF/views/common/login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
