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

public class MemberList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
				
		try {
			AdminDao adminDao = new AdminDao();
			List<MemberDetail> memberList = adminDao.getAllMemberList();
			
			//int check = adminDao.stop(userid);
			request.setAttribute("memberList", memberList);
			System.out.println(memberList);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/admin/admin_delete.jsp");				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
