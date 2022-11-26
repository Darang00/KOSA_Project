package kr.or.kosa.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;

public class BoardReplyAddService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
			try {
				String userid = request.getParameter("reply_userid");
				String content = request.getParameter("reply_content");
				
				String idx = request.getParameter("idx");
				/* String userid = "empty"; */
				
				//Reply reply = new Reply();
				String msg="";
			    String url="";
			    
				//System.out.println("댓글입력 service");
				MemberDao dao = new MemberDao();
				int idx_fk = Integer.parseInt(idx);
				
				/*
				reply.setWriter(writer);
				reply.setContent(content);
				reply.setPwd(pwd);
				reply.setIdx_fk(idx_fk);
				reply.setUserid(userid);
				*/
//				System.out.println(idx_fk );
//				System.out.println(userid);
//				System.out.println(content);
				
				int result = dao.replyWrite(idx_fk, userid, content);
//				System.out.println(result);
				System.out.println(idx_fk); // 글번호
				System.out.println(userid); // 작성자 아이디
				System.out.println(content); // 내용
				System.out.println("댓글 입력 = " + result);
				
				if(result > 0){
			    	 msg ="댓글 입력 성공";
						/* url ="BoardContent.do?idx="+idx_fk; */ 
			    	
			    }else{
			    	 msg="댓글 입력 실패";
						/* url="BoardContent.do?idx="+idx_fk; */
					 
			    }
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/common/redirect.jsp");
			
				
			} catch (Exception e) {
				e.getStackTrace();
			}
			return forward;
		}
	}


