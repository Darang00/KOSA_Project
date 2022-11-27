package kr.or.kosa.service.user;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;

public class BoardEditOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();	
		String idx = request.getParameter("idx");
		String userid = (String)session.getAttribute("userid");
		String writeruserid = request.getParameter("userid");
		String boardname = request.getParameter("boardname");
		
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String filename = request.getParameter("filename");
		
		String msg = "";
		String url = "";
		ActionForward forward = null;
		
		System.out.println("================EditOK서비스=================");
		System.out.println("여기로 오긴오나?");
		System.out.println("boardname : "+boardname);
		System.out.println("userid : "+userid);
		System.out.println("writeruserid : "+writeruserid);
		System.out.println("filename : "+filename);
		
		try {
			MemberDao dao = new MemberDao();
			if(!(userid.equals(writeruserid))) {
				msg="작성자 본인이 아니면 수정할 수 없습니다";
				url="boardList.user?boardname="+boardname;
			}else {
				if (idx == null || idx.trim().equals("")) {  //가져온 글번호 오류
					msg = "글번호 입력 오류";
					url = "boardList.user?boardname="+boardname;
					
				}else {
									
					int result = dao.boardEdit(idx, userid, content, title, filename);  //가져온 값들 담아서 함수에 넣어줌
					
					if (result > 0) {  //함수가 제대로 기능 했다면
						msg = "edit success";
						
						url = "boardList.user?boardname="+boardname;
					} else {
						msg = "edit fail";
						url = "boardList.user?boardname="+boardname+"&idx=" + idx;
					}
					
				}
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/web/redirect.jsp");  //수정 알림창
			
		} catch (NamingException e) {
			// TOboard Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}

}
