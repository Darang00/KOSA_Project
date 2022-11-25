package kr.or.kosa.service.user;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Board;

public class idCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//수정하기
		//HttpSession session = request.getSession();	
		String newid = request.getParameter("userid");
	    System.out.println("보드에딧서비스newid:"+newid);

		MemberDao member;
		ActionForward forward = null;
		String msg="";
	    String url="";
		try {		
//			if(pwd == null || pwd.trim().equals("")){  //pwd가 없다면 기본 리스트페이지로
//				response.sendRedirect("index.user"); //cpage=1 , ps=5
//				return null;
//			}

			member = new MemberDao();
			int idcheck = member.signIdCheck(newid);  //유저 비밀번호 체크
			
			if(idcheck == 0){  //게시물이 뭔가 잘못 됬다면 오류메시지
				System.out.println("실패");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/web/idOverLapFalse.jsp");
				System.out.println("이거나오면 오류");
				
			}else {  //잘 수정 됬다면
				System.out.println("성공");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/web/idOverLap.jsp");
				System.out.println("잘 처리되서 수정페이지 띄워주는지?");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
