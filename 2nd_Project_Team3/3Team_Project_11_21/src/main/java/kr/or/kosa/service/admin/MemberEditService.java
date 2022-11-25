package kr.or.kosa.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.AdminDao;
import kr.or.kosa.dto.Member;

public class MemberEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			List<Member> memberList;
			AdminDao adminDao = new AdminDao();
			memberList = adminDao.getAllTableList();
     		request.setAttribute("memberList", memberList);
			forward = new ActionForward();
			forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/admin/edit.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
	
}
