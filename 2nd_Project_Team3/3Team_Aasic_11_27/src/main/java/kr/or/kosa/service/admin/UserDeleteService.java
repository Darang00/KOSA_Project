package kr.or.kosa.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CommonDao;

public class UserDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//회원 탈퇴 처리 (글번호 받기).
		HttpSession session = request.getSession();
		String userid = request.getParameter("userid");
		String sessionid = (String)session.getAttribute("userid");
		CommonDao commondao;
		int resultRow = 0;

		System.out.println("삭제서비스-userid : "+userid);
		
		ActionForward forward = null;
		String msg="";
	    String url="";
	    try {
	    	commondao = new CommonDao();
	    	resultRow = commondao.userDelete(userid);
	    	
	    	if(resultRow == 0){  //입력 오류
				msg ="삭제 실패";
				url = "userDelete.do";
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/web/redirect.jsp");
				
			} else {  //제대로 처리됬다면
				if(sessionid.equals("admin")) { //관리자인 경우
					msg ="탈퇴 성공";
					url = "adminMain.admin";
					
					request.setAttribute("board_msg", msg);
					request.setAttribute("board_url", url);
				}else {  //일반 회원인 경우
					msg ="그동안 감사했습니다, 안녕히가세요!";
					url = "index.do";
					
					request.setAttribute("board_msg", msg);
					request.setAttribute("board_url", url);
				}
				
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/web/redirect.jsp");  //삭제버튼 누르면 보여주는 페이지
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(forward);
		
		
		return forward;
	}

}
