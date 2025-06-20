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
import dto.UserDTO;

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
		
		// セッションからログインユーザー情報を取得
				HttpSession session = request.getSession(false);
		        if (session == null || session.getAttribute("user_id") == null) {
		        	System.out.println("セッションまたは user_id が null です。ログインしていない状態です。");
		            // ログインしていない場合はログイン画面へ
		            response.sendRedirect(request.getContextPath() + "/LoginServlet");
		            return;
		        }
        
        // セッションからユーザー情報の取得
        UserDTO loginUser = (UserDTO) session.getAttribute("user_id");
        	if (loginUser == null) {
        		System.out.println("セッションから取得した loginUser が null です。");
        	} else {
        		System.out.println("ログインユーザーID: " + loginUser.getUser_id());
        	}
        	String user_id = loginUser.getUser_id();
      
        String prefecture_id = request.getParameter("prefecture_id"); // 絞り込み用パラメータ
        System.out.println("リクエストパラメータ prefecture_id = " + prefecture_id);
        

        try {
            List<PickupDTO> pickupList;
            
            if (prefecture_id != null && !prefecture_id.isEmpty()) {
            	System.out.println("都道府県で絞り込み検索を実行（user_id: " + user_id + ", prefecture_id: " + prefecture_id + "）");
            	// ユーザーID + 都道府県IDで絞り込み
            	pickupList = PickupDAO.findByUserAndPrefecture(user_id, prefecture_id);
            } else {
            	System.out.println("都道府県指定なしで全件検索を実行（user_id: " + user_id + "）");
                // ユーザーIDのみで全件取得
                pickupList = PickupDAO.findByUser(user_id);
            }
            
            System.out.println("取得した訪問地件数: " + pickupList.size());
            
            // リクエストにセットしてJSPへ
            request.setAttribute("pickupList", pickupList);
            request.setAttribute("selectedPrefecture", prefecture_id);
            
            request.getRequestDispatcher("/WEB-INF/jsp/visitorList.jsp").forward(request, response);
        
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "候補地の情報が見つかりませんでした。");
        }
    }
}
