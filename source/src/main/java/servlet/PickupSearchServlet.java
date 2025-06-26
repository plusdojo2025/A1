package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PickupDAO;
import dao.PrefectureDAO;
import dto.PickupDTO;
import dto.PrefectureDTO;
import dto.UserDTO;

/**
 * Servlet implementation class PickupSearchServlet
 */
@WebServlet("/PickupSearchServlet")
public class PickupSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// インスタンス化
		PrefectureDAO preDao = new PrefectureDAO();
		// DBから都道府県一覧を取得
		ArrayList<PrefectureDTO> prefectureList = preDao.selectAll();
		System.out.println("size: " + prefectureList.size());

		// JSPに渡すためリクエスト属性にセット
		request.setAttribute("prefectureList", prefectureList);

		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		// 検索ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorSearch.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		
		//ユーザーIDを取得している
		UserDTO userdto = (UserDTO)session.getAttribute("user_id");

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
	    String user_id = userdto.getUser_id();
	    String prefecture_id_str = request.getParameter("prefecture_id");
	    String pickup_place = request.getParameter("pickup_place");
	    String remarks = request.getParameter("remarks");

	    // prefecture_id を int 型に変換
	 // 都道府県ID：同様に 0 を条件なしとする
	    int prefecture_id = 0;
	    if (prefecture_id_str != null && !prefecture_id_str.isEmpty()) {
	        try {
	            prefecture_id = Integer.parseInt(prefecture_id_str);
	        } catch (NumberFormatException e) {
	            prefecture_id = 0;
	        }
	    }

	   // int prefecture_id = Integer.parseInt(prefecture_id_str);

		// 検索処理を行う
	    // DTOにセット
	    PickupDTO dto = new PickupDTO(0, user_id, prefecture_id, pickup_place, remarks);
		// DB検索処理
	    PickupDAO dao = new PickupDAO();
		List<PickupDTO> pickupList = dao.search(dto);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("pickupList", pickupList);
		
		// --- ページング処理ここから ---
		// ページング処理ここから
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
		
		// ページング処理
		int totalPickups = pickupList.size();
		int totalPagesPickup = (int) Math.ceil((double) totalPickups / pageSize);
		int startIndex = (page - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalPickups);

		List<PickupDTO> pagedPickupList = new ArrayList<>();
		if (startIndex < totalPickups) {
			pagedPickupList = pickupList.subList(startIndex, endIndex);
		}	
		request.setAttribute("pickupList", pagedPickupList);
		
		
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("pickupList", pagedPickupList);
		request.setAttribute("currentPage", page);			// 現在ページ
		request.setAttribute("totalPagesPickup", totalPagesPickup);		// 総ページ数
		// --- ページング処理ここまで ---

		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pickupSearchresult.jsp");
		dispatcher.forward(request, response);
	}

}
