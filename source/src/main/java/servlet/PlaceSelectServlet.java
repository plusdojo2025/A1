package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
	
	// DAOをインスタンス化
	private PickupDAO PickupDAO = new PickupDAO();
	private VisitorDAO VisitorDAO = new VisitorDAO();
	
	// GETリクエストで一覧表示・絞り込み処理を行う
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String pref = request.getParameter("pref");
	    	System.out.println("選択された都道府県: " + pref);

		
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
			
			
		// タブ情報
	    String tab = request.getParameter("tab");
	    if (tab == null || (!tab.equals("tab1") && !tab.equals("tab2"))) {
	        tab = "tab1"; // デフォルトは訪問地タブ
	    }
	    request.setAttribute("activeTab", tab);	
			
		// ページング処理
		int page = 1;
		int pageSize = 5;
			String pageParam = request.getParameter("page");
		if (pageParam != null) {
			try {
				page = Integer.parseInt(pageParam);
				if (page < 1) page = 1;
			} catch (NumberFormatException e) {
				page = 1;
			}
		}	
			
        try {
        	// 一覧を取得
            List<PickupDTO> pickupList;
            List<VisitorDTO> visitorList;
            
            // データ取得（絞り込み）
            if (pref != null && !pref.isEmpty()) {
                System.out.println("都道府県で絞り込み検索: " + pref);
                pickupList = PickupDAO.findByUserAndPrefecture(user_id, pref);
                visitorList = VisitorDAO.findByUserAndPrefecture(user_id, pref);
            } else {
                System.out.println("都道府県指定なしで全件表示");
                pickupList = PickupDAO.findByUser(user_id);
                visitorList = VisitorDAO.findByUser(user_id);
            }
            
         // ページング処理（訪問地）
         int totalVisitors = visitorList.size();
         int startIndex = (page - 1) * pageSize;
         int endIndex = Math.min(startIndex + pageSize, totalVisitors);
         				
         List<VisitorDTO> pagedVisitorList = new ArrayList<>();
         if (startIndex < totalVisitors) {
         	pagedVisitorList = visitorList.subList(startIndex, endIndex);
         }	
         request.setAttribute("visitorList", pagedVisitorList);
         	
		// ページング処理（候補地）
		int totalPickups = pickupList.size();
		int startPickupIndex = (page - 1) * pageSize;
		int endPickupIndex = Math.min(startPickupIndex + pageSize, totalPickups);

		List<PickupDTO> pagedPickupList = new ArrayList<>();
		if (startPickupIndex < totalPickups) {
		    pagedPickupList = pickupList.subList(startPickupIndex, endPickupIndex);
		}
		request.setAttribute("pickupList", pickupList);

         	// JSPに渡すデータ
         	request.setAttribute("visitorList", pagedVisitorList);
         	request.setAttribute("pickupList", pagedPickupList);
         	request.setAttribute("selectedPrefecture", pref);
         	request.setAttribute("currentPage", page);
         	request.setAttribute("totalPagesVisitor", (int) Math.ceil((double) totalVisitors / pageSize));
         	request.setAttribute("totalPagesPickup", (int) Math.ceil((double) totalPickups / pageSize));
         	
         	request.setAttribute("visitorServletName", "PlaceSelectServlet");
         	request.setAttribute("pickupServletName", "PlaceSelectServlet");
            
            request.getRequestDispatcher("/WEB-INF/jsp/visitorList.jsp").forward(request, response);
        
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "リストの取得に失敗しました。。");
        }
    }
}
