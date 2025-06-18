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
        if(session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        String user_id = (String) session.getAttribute("user_id");
        String prefecture_id = request.getParameter("prefecture_id");

        try {
            List<PickupDTO> pickupList;
            if (prefecture_id != null && !prefecture_id.isEmpty()) {
                pickupList = PickupDAO.findByUserAndPrefecture(user_id, prefecture_id);
            } else {
                pickupList = PickupDAO.findByUser(user_id);
            }
            request.setAttribute("pickupList", pickupList);
            request.setAttribute("selectedPrefecture", prefecture_id);
            request.getRequestDispatcher("/WEB-INF/jsp/visitorList.jsp").forward(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "候補地の情報が見つかりませんでした。");
        }
    }
}
