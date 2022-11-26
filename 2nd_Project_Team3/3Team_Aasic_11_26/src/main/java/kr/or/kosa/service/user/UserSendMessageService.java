package kr.or.kosa.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;

public class UserSendMessageService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			System.out.println("메세지 서비스");
			String senduserid = request.getParameter("senduserid");
			String userid = request.getParameter("userid");
			String messagetitle = request.getParameter("messagetitle");
			String messagecontent = request.getParameter("messagecontent");
			
			/*
			 * System.out.println(senduserid + "send 유저"); System.out.println(userid +
			 * "유저 아이디"); System.out.println(messagetitle+ " : 제목");
			 * System.out.println(messagecontent + " 내용");
			 */
			
			String msg = "";
			String url = "";
			
			MemberDao dao = new MemberDao();
			
			int result = dao.sendMessage(senduserid, userid, messagetitle, messagecontent);
			System.out.println(result);
			
			if(result > 0){
		    	msg ="쪽지보내기 성공";
				 url ="loginok.do";
		    	
		    }else{
		    	msg="쪽지보내기 실패";
				url = "index.do";
		    	
		    }
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/common/redirect.jsp");
		
					
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return forward;
	}

}
