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
import dto.UserDTO;

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
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
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
		
		//ユーザーIDを取得している
		UserDTO userdto = (UserDTO)session.getAttribute("user_id");

	// リクエストパラメータを取得する
	request.setCharacterEncoding("UTF-8");
    String user_id = userdto.getUser_id();
    String prefecture_id_str = request.getParameter("prefecture_id");
    String pickup_place = request.getParameter("pickup_place");
    String remarks = request.getParameter("remarks");
    
    System.out.println("ログインユーザーID: " + user_id);
    System.out.println("都道府県ID: " + prefecture_id_str);
    System.out.println("場所: " + pickup_place);
    System.out.println("備考: " + remarks);

    // 入力値保持用（戻ったときのため）
    request.setAttribute("user_id", user_id);
    request.setAttribute("prefecture_id", prefecture_id_str);
    request.setAttribute("pickup_place", pickup_place);
    request.setAttribute("remarks", remarks);
    
 // バリデーション（都道府県・場所は必須）
   /* if (prefecture_id_str == null || prefecture_id_str.isEmpty() || pickup_place == null || pickup_place.isEmpty()) {
        request.setAttribute("errorMessage", "都道府県と場所は必須項目です。");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pickupRegist.jsp");
        dispatcher.forward(request, response);
        return;
    }*/
    int prefecture_id = 0;
    if (prefecture_id_str != null && !prefecture_id_str.trim().isEmpty()) {
        try {
            prefecture_id = Integer.parseInt(prefecture_id_str);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "都道府県の選択が正しくありません。");
            request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp").forward(request, response);
            return;
        }
    } else {
        request.setAttribute("error", "都道府県を選択してください。");
        request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp").forward(request, response);
        return;
    }

    //int prefecture_id = Integer.parseInt(prefecture_id_str);

    PickupDTO dto = new PickupDTO(0, user_id, prefecture_id, pickup_place, remarks);
    PickupDAO dao = new PickupDAO();

    boolean result = dao.insert(dto);
    
    System.out.println("★登録内容確認：");
    System.out.println("user_id = " + dto.getUser_id());
    System.out.println("prefecture_id = " + dto.getPrefecture_id());
    System.out.println("pickup_place = " + dto.getPickup_place());
    System.out.println("remarks = " + dto.getRemarks());

    if (result) {
        // 成功：登録画面へリダイレクト（リセットされた状態）
		response.sendRedirect(request.getContextPath() + "/PickupRegistServlet");
    } else {
        // 失敗：入力値保持して戻る
        request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp");
        dispatcher.forward(request, response);
    }
}
	}

