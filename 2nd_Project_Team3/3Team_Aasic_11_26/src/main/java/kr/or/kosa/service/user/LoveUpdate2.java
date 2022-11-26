package kr.or.kosa.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemberDao;
import kr.or.kosa.dto.Love;

public class LoveUpdate2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;

		try {

			// System.out.println("loveupdate 서비스 진입");

			MemberDao member = new MemberDao();
			Love love = new Love();
			String userid = request.getParameter("userid");
			int idx = Integer.parseInt(request.getParameter("idx"));

			love.setIdx(idx);
			love.setUserid(userid);
			
			System.out.println("set  love: " + love.toString());


			int resultrow = member.loveCheck(love);

			if (resultrow == 0) {
				// 추천하기
				member.loveUpdate(love);

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/board/board_content.jsp");

			} else if (resultrow == 1) {
				// 이미 추천한 경우
				member.loveUpdate(love);

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/board/board_content.jsp");
			}

			System.out.println("count 도는중. ..");

			System.out.println("카운트 이후 love: " + love.toString());
			
			member.loveCount(idx);
			System.out.println("idx parameter: " + idx);
			int lovecount = member.loveCount(idx);
			request.setAttribute("lovecount: ", lovecount);
			System.out.println(lovecount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;

	}
}
