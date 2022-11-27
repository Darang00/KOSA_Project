package kr.or.kosa.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class BoardViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			String boardname = request.getParameter("boardname");
			request.setAttribute("boardname", boardname);
			System.out.println(boardname);
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		if(boardname.equals("공지사항"))
    		{
        		forward.setPath("/WEB-INF/views/admin/boardNotice_write.jsp");    			
    		}else if(boardname.equals("자유게시판")) {
        		forward.setPath("/WEB-INF/views/web/boardFree_write.jsp");
    		}else if(boardname.equals("질문과답변")) {
        		forward.setPath("/WEB-INF/views/web/boardQN_write.jsp");
    		}else if(boardname.equals("트러블슈팅")) {
        		forward.setPath("/WEB-INF/views/web/boardTrouble_write.jsp");    			
    		}else if(boardname.equals("프로젝트모집")) {
        		forward.setPath("/WEB-INF/views/web/boardProject_write.jsp");
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
