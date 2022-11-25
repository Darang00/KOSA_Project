package kr.or.kosa.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Member;
import kr.or.kosa.dto.MemberDetail;

public class UpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userid = request.getParameter("userid");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		
		//Member member;
		MemberDetail memberdetail;
		MemberDao dao;
		ActionForward forward = new ActionForward();
		int check = 0;
		//List<Member> memberlist;
		try {
			dao = new MemberDao();
			memberdetail = new MemberDetail();
			memberdetail.setUserid(userid);
			memberdetail.setName(name);
			memberdetail.setEmail(email);
			memberdetail.setGender(gender);
			memberdetail.setAddress(address);
			memberdetail.setPwd(pwd);
			memberdetail.setPhone(phone);
			
			check = dao.update(memberdetail);
			System.out.println("check : "+check);
			if (check > 0) {
				forward.setRedirect(false); // true로 하면 뜻 -> 클라이언트가 새로운 페이지를 요청하게 할 거예요
				forward.setPath("/WEB-INF/views/web/web.jsp");
			} else {
				forward.setRedirect(false); // true로 하면 뜻 -> 클라이언트가 새로운 페이지를 요청하게 할 거예요
				forward.setPath("/WEB-INF/views/web/edit.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return forward;
	}

}
