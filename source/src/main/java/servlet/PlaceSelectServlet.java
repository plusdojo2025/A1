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
import dao.VisitorDAO;
import dto.PickupDTO;
import dto.UserDTO;
import dto.VisitorDTO;

@WebServlet("/PlaceSelectServlet")
public class PlaceSelectServlet extends HttpServlet {
	
	// 候補地用のDAOをインスタンス化
	private PickupDAO PickupDAO = new PickupDAO();
	private VisitorDAO VisitorDAO = new VisitorDAO();
	
	// GETリクエストで一覧表示・絞り込み処理を行う
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// セッションからログインユーザー情報を取得
				HttpSession session = request.getSession(false);
		        if (session == null || session.getAttribute("user_id") == null) {
		        	System.out.println("Pセッションまたは user_id が null です。ログインしていない状態です。");
		            // ログインしていない場合はログイン画面へ
		            response.sendRedirect(request.getContextPath() + "/LoginServlet");
		            return;
		        }
        
        // セッションからユーザー情報の取得
		UserDTO loginUser = (UserDTO) session.getAttribute("user_id");
			if (loginUser == null) {
				System.out.println("pセッションから取得した loginUser が null です。");
				response.sendRedirect(request.getContextPath() + "/LoginServlet");
				return;
			}

		String user_id = loginUser.getUser_id(); 
			System.out.println("pログインユーザーID: " + user_id);
      
        String prefecture_id = request.getParameter("prefecture_id"); // 絞り込み用パラメータ
        	System.out.println("Pリクエストパラメータ prefecture_id = " + prefecture_id);
        if (prefecture_id != null && !prefecture_id.isEmpty()) {
            session.setAttribute("selectedPrefecture", prefecture_id);
        } else {
            // 指定なしならセッションから削除
            session.removeAttribute("selectedPrefecture");
        }
        
        
        String selectedPrefecture = (String) session.getAttribute("selectedPrefecture");

        try {
            List<PickupDTO> pickupList;
            List<VisitorDTO> visitorList;

            if (selectedPrefecture != null && !selectedPrefecture.isEmpty()) {
                System.out.println("都道府県で絞り込み検索: " + selectedPrefecture);
                pickupList = PickupDAO.findByUserAndPrefecture(user_id, selectedPrefecture);
                visitorList = VisitorDAO.findByUserAndPrefecture(user_id, selectedPrefecture);
            } else {
                System.out.println("都道府県指定なしで全件表示");
                pickupList = PickupDAO.findByUser(user_id);
                visitorList = VisitorDAO.findByUser(user_id);
            }

            	
            // リクエストにセットしてJSPへ
            request.setAttribute("pickupList", pickupList);
            request.setAttribute("visitorList", visitorList);
            request.setAttribute("selectedPrefecture", prefecture_id);
            
            request.getRequestDispatcher("/WEB-INF/jsp/visitorList.jsp").forward(request, response);
        
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "候補地の情報が見つかりませんでした。");
        }
    }
}
