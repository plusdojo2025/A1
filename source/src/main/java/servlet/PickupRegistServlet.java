package servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class PickupRegistServlet
 */
@WebServlet("/PickupRegistServlet")
public class PickupRegistServlet extends HttpServlet {
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
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A1/LoginServlet");
			return;
		}

		// 登録ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp");
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
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A1/LoginServlet");
			return;
		}


	// リクエストパラメータを取得する
	request.setCharacterEncoding("UTF-8");
    String user_id = request.getParameter("user_id");
    String prefecture_id_str = request.getParameter("prefecture_id");
    String pickup_place = request.getParameter("place");
    String remarks = request.getParameter("remarks");

    // 入力値保持用（戻ったときのため）
    request.setAttribute("user_id", user_id);
    request.setAttribute("prefecture_id", prefecture_id_str);
    request.setAttribute("place", pickup_place);
    request.setAttribute("remarks", remarks);
    
 // バリデーション（都道府県・場所は必須）
   /* if (prefecture_id_str == null || prefecture_id_str.isEmpty() || pickup_place == null || pickup_place.isEmpty()) {
        request.setAttribute("errorMessage", "都道府県と場所は必須項目です。");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pickupRegist.jsp");
        dispatcher.forward(request, response);
        return;
    }*/
    
    int prefecture_id = Integer.parseInt(prefecture_id_str);

    PickupDTO dto = new PickupDTO(0, user_id, prefecture_id, pickup_place, remarks);
    PickupDAO dao = new PickupDAO();

    boolean result = dao.insert(dto);

    if (result) {
        // 成功：登録画面へリダイレクト（リセットされた状態）
        response.sendRedirect("/webapp/PickupRegistServlet");
    } else {
        // 失敗：入力値保持して戻る
        request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp");
        dispatcher.forward(request, response);
    }
}
	}

