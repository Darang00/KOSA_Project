package kr.or.kosa.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;

public class FileService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");// 댓글의 원본 게시글 번호
		String filename = "";
		
		String msg = "";
		String url = "";
		try {
			if (idx == null) {
				msg = "이상한 링크 입니다.";
				url = "BoardContent.board?idx=" + idx;
			} else {
				
				MemberDao dao = new MemberDao();
				filename = dao.FileList(idx);
				
				
				if (filename != null) {
					msg = "파일 있음";
					url = "boardContent.user?idx=" + idx;
				} else {
					msg = "파일 없음";
					url = "boardContent.user?idx=" + idx;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("idx_fk", idx);
		request.setAttribute("filename", filename);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/web/boardFileView.jsp"); //파일만 크게 보여주는 페이지 불러옴
		/* forward.setPath("/WEB-INF/views/board/redirect.jsp"); */

		return forward;
	}

}
