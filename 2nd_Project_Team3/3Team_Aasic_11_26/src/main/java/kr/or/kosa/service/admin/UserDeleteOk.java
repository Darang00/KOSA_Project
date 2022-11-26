package kr.or.kosa.service.admin;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CommonDao;

public class UserDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String myid = (String)session.getAttribute("userid");	//내 아이디(admin/회원)
		String userid = request.getParameter("userid");  //삭제할 아이디
		
		System.out.println("myid : "+myid);
		System.out.println("userid : "+userid);
		
		String msg="";
		String url="";
		int result=0;
		
		CommonDao dao;
		try {
			dao = new CommonDao();
			
			if(!(myid.equals("admin"))) {   //회원인 경우
				if(!(myid.equals(userid))) {
					msg="당신의 아이디와 다릅니다";
					url="userDelete.do";
				} else{
					result = dao.userDelete(userid);
					if(result > 0){
						msg="삭제완료";
						url="index.user";
					}else{
						msg="삭제실패";
						url="userDelete.do";
					}
				}
			}else {  //관리자인 경우
				result = dao.userDelete(userid);
				if(result > 0){
					msg="삭제완료";
					url="userDelete.admin";
				}else{
					msg="삭제실패";
					url="userDelete.admin";
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url",url);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("/WEB-INF/views/web/redirect.jsp");

		return forward;
	}

}
