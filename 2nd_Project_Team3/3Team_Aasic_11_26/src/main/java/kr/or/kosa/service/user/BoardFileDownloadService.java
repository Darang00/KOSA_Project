package kr.or.kosa.service.user;

import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class BoardFileDownloadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//다운로드할 파일명 얻기
		String filename = request.getParameter("filename");
		ActionForward forward = null;

	    //물리적 경로 얻기
	    HttpSession session = request.getSession(); //프로젝트명까지의 루트경로를 잡아주는 놈
		String root = session.getServletContext().getRealPath("/");
		ServletContext context = request.getSession().getServletContext();
		String savepath = "upload";  //폴더명
		String downloadpath = root+ savepath+ "\\"+ filename;
	    
		System.out.println("session : "+session);
		System.out.println("root : "+root);
		System.out.println("context : "+context);
		System.out.println("savepath : "+savepath);
		System.out.println("downloadpath : "+downloadpath);
	    //PrintWriter out = response.getWriter();
	    //out.print(downloadpath +"<br>");
	    //out.print(FilePath); //실경로 + 파일명
		byte[] b;
		FileInputStream in;
		
	    try {
			//IO작업 하기
	    //파일을 읽어서 출력
	    b = new byte[4096]; //4kb  //요기는 필요에 따라 조정 가능
	    in = new FileInputStream(downloadpath); //실 저장 경로에서
	    
	    //실제 인지할수 있는 타입의 파일들이면 자신의 => contentType
	    	    //application이 인지할 수 없는 확장자 파일은 null 경우에 형식을 잡아준다
	    		String sMimeType = context.getMimeType(downloadpath); //파일의 타입 정보
	    		if (sMimeType == null) {
	    			//알수 없는 형식의 파일은 
	    			//application/octet-stream 기본값으로 (알수 없는 파일 형식)
	    			sMimeType = "application/octet-stream";
	    		}
	    		//2. client 전달되는 형식을 지정(Type)
	    		//response.setContentType("text/html;charset=UTF-8");  html 파일인 경우
	    		response.setContentType(sMimeType);
	    
	    		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859_1")); 
	    		//filename.getBytes(),"ISO8859_1")
	    		//문서를 읽어 들여서 ISO8859_1 형식으로 변환 ......
	    
	    		//5. 파일 출력하기
	    		ServletOutputStream out2 = response.getOutputStream();
	    		int numread;
	    		while ((numread = in.read(b, 0, b.length)) != -1) {
	    			out2.write(b, 0, numread);
	    		}

	    		out2.flush();
	    		out2.close();
	    		in.close();
	    		
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/web/redirect.jsp");

		return forward;
	}

}
