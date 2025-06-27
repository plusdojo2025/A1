package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GachaDAO;
import dao.PickupDAO;
import dto.PickupDTO;
import dto.UserDTO;

/**
 * Servlet implementation class GachaServlet
 */
@WebServlet("/GachaServlet")
public class GachaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GachaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
    	// ガチャページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gacha.jsp");
		dispatcher.forward(request, response);
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからログインユーザー情報を取得
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user_id");
		
		GachaDAO gachaDao = new GachaDAO();
		
		// 1日1回制限の確認
        boolean hasDrawnToday = gachaDao.hasDrawnToday(user.getUser_id());
        if (hasDrawnToday) {
//        	System.out.println("ガチャサーブレットからgachaResult.jspに飛ぶよ");
//            request.setAttribute("errorMessage", "本日はすでにガチャを引いています。");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gachaResult.jsp");
//            dispatcher.forward(request, response);
//            return;
        	session.setAttribute("alreadyPickedToday", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gachaLoading.jsp");
            dispatcher.forward(request, response);
            return;
        }

		// ユーザーの候補地一覧を取得
		PickupDAO pickupDao = new PickupDAO();
		List<PickupDTO> pickupList = pickupDao.findByUser(user.getUser_id());

		// 候補地がない場合はエラーページへ
		if (pickupList == null || pickupList.isEmpty()) {
			System.out.println("ガチャサーブレットからgachaError.jspに飛ぶよ");
			request.setAttribute("errorMessage", "行きたい場所が登録されていません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gachaError.jsp");
            dispatcher.forward(request, response);
            return;
		}

		// 候補地からランダムで1件選出
		Random rand = new Random();
		PickupDTO selected = pickupList.get(rand.nextInt(pickupList.size()));
		
		System.out.println(selected.getPrefecture_name());
		// 結果をDBに保存
		/*
		 * gachaDao.saveResult(user.getUser_id(), selected.getPickup_id());
		 * 
		 * // 選ばれたPickUpをセッションに保存（JSPで表示用） session.setAttribute("selectedPickup",
		 * selected);
		 * 
		 * // 結果画面に遷移 RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("/WEB-INF/jsp/gachaLoading.jsp");
		 * dispatcher.forward(request, response);
		 */
		
		// ガチャ結果を保存
        gachaDao.saveResult(user.getUser_id(), selected.getPickup_id());

        // セッションに選ばれた場所を保存し、ローディング画面へ
        System.out.println("ガチャサーブレットからgachaLoading.jspに飛ぶよ");
        session.setAttribute("selectedPickup", selected);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gachaLoading.jsp");
        dispatcher.forward(request, response);
	}
}
