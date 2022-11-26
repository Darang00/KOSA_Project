package kr.or.kosa.service.user;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Files;

public class BoardEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//수정하기
		String idx = request.getParameter("idx");  //수정할 글 번호 가져옴
		String boardname = request.getParameter("boardname");
		String filename = request.getParameter("filename");
		String msg="";
	    String url="";
	    System.out.println("보드에딧서비스boardname:"+boardname);

		MemberDao member;
		Files files = new Files();
		ActionForward forward = null;
		try {		
			if(idx == null || idx.trim().equals("")){  //글번호가 없다면 기본 리스트페이지로
				response.sendRedirect("boardList.user"); //cpage=1 , ps=5
				return null;
			}

			member = new MemberDao();
			
			//BoardService service = BoardService.getInBoardService();
			Board board = member.getEditContent(idx);  //게시물 상세보기 함수 재활용
			
			if(board == null){  //게시물이 뭔가 잘못 됬다면 오류메시지
				msg ="데이터 오류";
				url ="boardList.user?boardname="+boardname;
				
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				request.setAttribute("boardname", boardname);
				request.setAttribute("filename", filename);
				request.setAttribute("files", files);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/web/redirect.jsp");
				System.out.println("이거나오면 오류");
				
			}else {  //잘 수정 됬다면
				request.setAttribute("idx", idx);  //해당 글번호의 수정페이지로 가기위해 글번호 받아서 보내고
				request.setAttribute("board", board);
				request.setAttribute("boardname", boardname);
				request.setAttribute("filename", filename);
				request.setAttribute("files", files);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/web/boardEdit.jsp");  //게시물 수정 화면 보여준다
				
				System.out.println("잘 처리되서 수정페이지 띄워주는지?");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
