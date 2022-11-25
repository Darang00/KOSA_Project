package kr.or.kosa.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UserDao;
import kr.or.kosa.dto.Member;
import kr.or.kosa.dto.MemberDetail;

public class UserInsertService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
				
		try {
			//파라미터
			String userid = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			//String firstRenumber = request.getParameter("firstRenumber");
			//String lastRenumber = request.getParameter("lastRenumber");
			//String reNumber = firstRenumber + lastRenumber;
			String name = request.getParameter("name");
			//int age = Integer.parseInt(request.getParameter("age"));
			String email = request.getParameter("email");
			//String firstNumber = request.getParameter("firstNumber");
			String phone = request.getParameter("phone");
			//String Phone = (firstNumber + phoneNumber);
			String address = request.getParameter("address");
			String gender = request.getParameter("gender");
			String ip = request.getRemoteAddr();	
			//String startDate =request.getParameter("startDate");
			
			Member user = new Member();
			MemberDetail userDetail = new MemberDetail();
			UserDao userDao = new UserDao();
			List<Member> userDetailDtoList;
			int check = 0;
			userDetail.setUserid(userid);
		    userDetail.setPwd(pwd);
		    userDetail.setName(name);
		    userDetail.setEmail(email);
		    userDetail.setPhone(phone);
		    userDetail.setAddress(address);
		    userDetail.setGender(gender);
		  
		    user.setUserid(userid);
		    user.setIp(ip);
		    
		    check = userDao.writeOk(user, userDetail);
		    forward = new ActionForward();
		    forward.setRedirect(false);
		    if(check>0) {
		 	   forward.setPath("/WEB-INF/views/common/login.jsp");
		    }else {
		 	   forward.setPath("/WEB-INF/views/common/register.jsp");
		    }		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
