package kr.or.kosa.service.user;

import java.io.File;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Files;

public class BoardAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			String title = "";
			String content = "";
			String filename = "";
			String boardname = "";
			int filesize=0;
			int size = 1024*1024*10;
			HttpSession session = request.getSession();
			String userid = (String)session.getAttribute("userid");
			String root = session.getServletContext().getRealPath("/");
			String savepath = "upload";
			String uploadpath = root+ savepath;
			
			int result = 0;
				
			try {
				MultipartRequest multi = new MultipartRequest(
						request, 	
						uploadpath, 	
						size, 
						"UTF-8",
						new DefaultFileRenamePolicy()
				);
				Enumeration filenames = multi.getFileNames();
				title = multi.getParameter("title");
				content = multi.getParameter("content");
				boardname = multi.getParameter("boardname");
		        String file = (String) filenames.nextElement();
		        filename = multi.getFilesystemName(file);
		        File fileObj = multi.getFile(file);
		        filesize = (int)fileObj.length();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Board board = new Board();
			board.setUserid(userid);
			board.setTitle(title);
			board.setBoardname(boardname);
			board.setContent(content);
			MemberDao memberDao = new MemberDao();
			if(filename==null) {
				result = memberDao.writeok(board);				
			}else {
				Files file = new Files();
				file.setFilename(filename);
				file.setFilesize(filesize);
				result = memberDao.writeok(board,file);
			}

			String msg = "";
			String url = "";
			if (result > 0) {
				msg = "insert success";
				url = "index.do";
			} else {
				msg = "insert fail";
				url = "boardWrite.do";
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/web/redirect.jsp");



		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
			
		

}
