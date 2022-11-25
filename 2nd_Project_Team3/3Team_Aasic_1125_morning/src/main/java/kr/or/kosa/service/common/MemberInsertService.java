package kr.or.kosa.service.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CommonDao;
import kr.or.kosa.dto.MemberDetail;
import kr.or.kosa.dto.Member;

public class MemberInsertService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
				
		try {
			//파라미터
			String userid = request.getParameter("userid");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String firstEmail = request.getParameter("firstEmail");
			String lastEmail = request.getParameter("lastEmail");
			String email = firstEmail + lastEmail;
			String firstNumber = request.getParameter("firstNumber");
			String phoneNumber = request.getParameter("phoneNumber");
			String Phone = (firstNumber + phoneNumber);
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			String ip = request.getRemoteAddr();		
			int check = 0;
			
			MemberDetail memberDetail = new MemberDetail();							
			Member member = new Member();
			CommonDao commonDao = new CommonDao();


			memberDetail.setUserid(userid);
			memberDetail.setPwd(pwd);
			memberDetail.setName(name);
			memberDetail.setEmail(email);
			memberDetail.setPhone(Phone);
			memberDetail.setAddress(address);
			memberDetail.setGender(gender);
			member.setUserid(userid);
			member.setIp(ip);
		    
		    check = commonDao.writeOk(member, memberDetail);
		
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
