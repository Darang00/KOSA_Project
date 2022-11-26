package kr.or.kosa.service.user;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;

public class BoardReplyDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx_fk = request.getParameter("idx");// 댓글의 원본 게시글 번호
		String no = request.getParameter("no");// 댓글의 순번(PK)
		
		System.out.println("글번호" + idx_fk);
		System.out.println("댓글번호" + no);
		

		String msg = "";
		String url = "";

		if (idx_fk == null || no == null || no.trim().equals("")) {
			msg = "이상한 링크 입니다.";
			/* url = "BoardContent.do?idx=" + idx_fk; */
		} else {

			try {
				System.out.println("서비스");
				MemberDao dao = new MemberDao();

				int result = dao.replyDelete(no);
				
				if (result > 0) {
					msg = "댓글 삭제 성공";
					/* url = "BoardContent.do?idx=" + idx_fk; */
				} else {
					msg = "댓글 삭제 실패";
					/* url = "BoardContent.do?idx=" + idx_fk; */
				}

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/common/redirect.jsp");

		return forward;
	}

}
