package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.admin.MemberActivateService;
import kr.or.kosa.service.admin.MemberEditService;
import kr.or.kosa.service.admin.MemberStopService;
import kr.or.kosa.service.common.LoginCheckService;


@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminController() {
        super();
        
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
       	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	Action action=null;
    	ActionForward forward=null;
    	
    	
    	if(urlcommand.equals("/edit.admin")) {
    		 action = new MemberEditService();
    		 forward = action.execute(request, response);   
    	}else if(urlcommand.equals("/stop.admin")) {   //계정정지
	    	action = new MemberStopService();
	  		forward = action.execute(request, response);
	    }else if(urlcommand.equals("/activate.admin")) {   //계정정지
	    	action = new MemberActivateService();
	  		forward = action.execute(request, response);
	    }
    	
    
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
