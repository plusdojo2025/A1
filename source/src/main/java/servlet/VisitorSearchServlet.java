package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PrefectureDAO;
import dao.VisitorDAO;
import dto.PrefectureDTO;
import dto.VisitorDTO;

/**
 * Servlet implementation class VisitorSearchServlet
 */
@WebServlet(urlPatterns = {"/VisitorSearchServlet"})

//@WebServlet("/VisitorSearchServlet")
public class VisitorSearchServlet extends HttpServlet {
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
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
	    String user_id = request.getParameter("user_id");
	    String start_date_str = request.getParameter("start_date");  // String型として取得
	    String end_date_str = request.getParameter("end_date");      // String型として取得
	    String title = request.getParameter("title");
	    String prefecture_id_str = request.getParameter("prefecture_id");  // String型
	    String visitor_place = request.getParameter("visitor_place");
	    String componion = request.getParameter("componion");
	    String emotion_id_str = request.getParameter("emotion_id");   // String型
	    String thought = request.getParameter("thought");
	    String photo = request.getParameter("photo");

	    // start_date と end_date を Date 型に変換
	    Date start_date = null;
	    Date end_date = null;
	 // 日付（yyyy-MM-dd 形式）を null 許容で変換
	    if (start_date_str != null && !start_date_str.isEmpty()) {
	        try {
	            start_date = Date.valueOf(start_date_str);
	        } catch (IllegalArgumentException e) {
	            // 日付フォーマットエラー時もnull扱いに
	            start_date = null;
	        }
	    }

	    if (end_date_str != null && !end_date_str.isEmpty()) {
	        try {
	            end_date = Date.valueOf(end_date_str);
	        } catch (IllegalArgumentException e) {
	            end_date = null;
	        }
	    }

	   /* if (start_date_str != null && !start_date_str.isEmpty()) {
	        start_date = Date.valueOf(start_date_str);  // yyyy-mm-dd形式
	    }

	    if (end_date_str != null && !end_date_str.isEmpty()) {
	        end_date = Date.valueOf(end_date_str);  // yyyy-mm-dd形式
	    }*/

	    // emotion_id を int 型に変換
	 // 感情ID：nullや空文字なら 0（=条件なし）
	    int emotion_id = 0;
	    if (emotion_id_str != null && !emotion_id_str.isEmpty()) {
	        try {
	            emotion_id = Integer.parseInt(emotion_id_str);
	        } catch (NumberFormatException e) {
	            emotion_id = 0;
	        }
	    }

	   // int emotion_id = Integer.parseInt(emotion_id_str);
	    

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
	    VisitorDTO dto = new VisitorDTO(0, user_id, title, componion, start_date, end_date, prefecture_id, visitor_place, thought, emotion_id, photo, null, null, null, null);
		// DB検索処理
		VisitorDAO dao = new VisitorDAO();
		List<VisitorDTO> visitorList = dao.search(dto);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("visitorList", visitorList);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorSearchresult.jsp");
		dispatcher.forward(request, response);
	}

}
