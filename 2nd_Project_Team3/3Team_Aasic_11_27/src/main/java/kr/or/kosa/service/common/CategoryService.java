package kr.or.kosa.service.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CommonDao;
import kr.or.kosa.dto.Category;

public class CategoryService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			CommonDao commonDao = new CommonDao();
			
			List<Category> categoryList;
			categoryList = commonDao.getAllCategory();
			System.out.println(categoryList);
//			System.exit(0);
			request.setAttribute("categoryList", categoryList);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/include/userHeader.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
