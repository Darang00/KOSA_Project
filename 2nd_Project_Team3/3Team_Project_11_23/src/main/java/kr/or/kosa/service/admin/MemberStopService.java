package kr.or.kosa.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.AdminDao;
import kr.or.kosa.dao.CommonDao;
import kr.or.kosa.dto.MemberDetail;
import kr.or.kosa.dto.Member;

public class MemberStopService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
				
		try {
			AdminDao adminDao = new AdminDao();
			List<Member> memberList;
			String userid = request.getParameter("userid");
			
			//계정정지 후 관리자 페이지 리셋.
			int check = adminDao.stop(userid);
			memberList = adminDao.getAllTableList();
			request.setAttribute("memberList", memberList);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			
			if(check>0)
			{
				forward.setPath("/WEB-INF/views/admin/admin.jsp");				
			}
			else {
				System.out.println("잘못됨");
				forward.setPath("/WEB-INF/views/admin/admin.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
