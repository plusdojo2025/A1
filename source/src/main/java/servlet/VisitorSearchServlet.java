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

import dao.PrefectureDAO;
import dao.VisitorDAO;
import dto.PrefectureDTO;
import dto.VisitorDTO;

/**
 * Servlet implementation class VisitorSearchServlet
 */
@WebServlet("/VisitorSearchServlet")
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
		/*HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webappAns/LoginServlet");
			return;
		}*/
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
		/*HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A1/LoginServlet");
			return;
		}*/
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
	    String user_id = request.getParameter("user_id");
	    String start_date_str = request.getParameter("start_date");  // String型として取得
	    String end_date_str = request.getParameter("end_date");      // String型として取得
	    String title = request.getParameter("title");
	    String prefecture_id_str = request.getParameter("prefecture_id");  // String型
	    String place = request.getParameter("place");
	    String componion = request.getParameter("componion");
	    String emotion_id_str = request.getParameter("emotion_id");   // String型
	    String thought = request.getParameter("thought");
	    String photo = request.getParameter("photo");

	    // start_date と end_date を Date 型に変換
	    Date start_date = null;
	    Date end_date = null;

	    if (start_date_str != null && !start_date_str.isEmpty()) {
	        start_date = Date.valueOf(start_date_str);  // yyyy-mm-dd形式
	    }

	    if (end_date_str != null && !end_date_str.isEmpty()) {
	        end_date = Date.valueOf(end_date_str);  // yyyy-mm-dd形式
	    }

	    // emotion_id を int 型に変換
	    int emotion_id = Integer.parseInt(emotion_id_str);

	    // prefecture_id を int 型に変換
	    int prefecture_id = Integer.parseInt(prefecture_id_str);

		// 検索処理を行う
	    // DTOにセット
	    VisitorDTO dto = new VisitorDTO(0, user_id, title, componion, start_date, end_date, prefecture_id, place, thought, emotion_id, photo, null, null, null, null);
		// DB検索処理
		VisitorDAO dao = new VisitorDAO();
		List<VisitorDTO> cardList = dao.search(dto);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorList.jsp");
		dispatcher.forward(request, response);
	}

}
