package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VisitorDAO;
import dto.VisitorDTO;

@WebServlet("/VisitorListServlet")
public class VisitorListServlet extends HttpServlet {
	
	// 訪問地用のDAOをインスタンス化
	private VisitorDAO VisitorDAO = new VisitorDAO();
	
	
	// GETリクエストで一覧表示・絞り込み処理を行う
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// セッションチェック
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            // ログインしていない場合はログイン画面へ
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        String user_id = (String) session.getAttribute("user_id");

        String prefecture_id = request.getParameter("prefecture_id"); // 絞り込み用パラメータ

        try {
            List<VisitorDTO> visitorList;
            
            if (prefecture_id != null && !prefecture_id.isEmpty()) {
                // ユーザーID + 都道府県IDで絞り込み
                visitorList = VisitorDAO.findByUserAndPrefecture(user_id, prefecture_id);
            } else {
                // ユーザーIDのみで全件取得
                visitorList = VisitorDAO.findByUser(user_id);
            }

            // リクエストにセットしてJSPへ
            request.setAttribute("visitorList", visitorList);
            request.setAttribute("selectedPrefecture", prefecture_id);

            request.getRequestDispatcher("/WEB-INF/jsp/visitorList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "訪問地の情報が見つかりませんでした。");
        }
    }
}