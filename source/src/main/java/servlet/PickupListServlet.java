package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PickupDAO;
import dto.PickupDTO;

/**
 * Servlet implementation class PickupListServlet
 */
@WebServlet("/PickupListServlet")
public class PickupListServlet extends HttpServlet {
	
	// 候補地用のDAOをインスタンス化
	private PickupDAO PickupDAO = new PickupDAO();
	
	// GETリクエストで一覧表示・絞り込み処理を行う
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// セッションチェック
		HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        String userId = (String) session.getAttribute("userId");
        String prefectureId = request.getParameter("prefectureId");

        try {
            List<PickupDTO> pickupList;
            if (prefectureId != null && !prefectureId.isEmpty()) {
                pickupList = PickupDAO.findByUserAndPrefecture(userId, prefectureId);
            } else {
                pickupList = PickupDAO.findByUser(userId);
            }
            request.setAttribute("pickupList", pickupList);
            request.setAttribute("selectedPrefecture", prefectureId);
            request.getRequestDispatcher("/WEB-INF/view/pickup_list.jsp").forward(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "候補地の取得に失敗しました");
        }
    }
}
