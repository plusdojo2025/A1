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
			response.sendRedirect("/A1/LoginServlet");
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
			response.sendRedirect("/A1/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
	    String user_id = request.getParameter("user_id");
	    String prefecture_id_str = request.getParameter("prefecture_id");
	    String place = request.getParameter("place");
	    String remarks = request.getParameter("remarks");

	    // prefecture_id を int 型に変換
	    int prefecture_id = Integer.parseInt(prefecture_id_str);

		// 検索処理を行う
	    // DTOにセット
	    PickupDTO dto = new PickupDTO(0, user_id, prefecture_id, place, remarks);
		// DB検索処理
	    PickupDAO dao = new PickupDAO();
		List<PickupDTO> cardList = dao.search(dto);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pickupList.jsp");
		dispatcher.forward(request, response);
	}

}
