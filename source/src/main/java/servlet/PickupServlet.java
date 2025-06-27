package servlet;

import java.io.IOException;
import java.util.Objects;

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
import dto.UserDTO;
import dto.VisitorDTO;

/**
 * [ 画面 ] 詳細（候補地）
 * Servlet implementation class PickupServlet
 */
@WebServlet("/PickupServlet")
public class PickupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int pk = -1;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PickupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// [ 設定 ] UTF-8 で受け取る
		request.setCharacterEncoding("UTF-8");
		
		// [ リソース ] pickup.jsp
		String view = "/WEB-INF/jsp/pickup.jsp";
		
		// [ 取得 ] セッション
		HttpSession session = request.getSession();
		// [ 取得 ] ログインユーザー
		UserDTO loginUser = (UserDTO) session.getAttribute("user_id");
		
		// [ 判定 ] 未ログイン
		if (Objects.isNull(loginUser)) {
			// [ ロケーション ] LoginServlet
			String url = request.getContextPath() + "/LoginServlet";
			// [ 転送 ] 代替の場所（URL）に
			response.sendRedirect(url);
			return;
		}
		// ログイン済みのユーザー名を表示
		System.out.println("----------------------------------");
		System.out.println("● [ login ] " + loginUser.getNickname());
		System.out.println("----------------------------------");
		
		try {
			// [ GET ] 訪問地ID
			String pkStr = request.getParameter("pk");
			System.out.println("pk: " + pkStr);
			pk = Integer.parseInt(pkStr);
			System.out.println();
			
			// [ 宣言 ] 使用するモデルのインスタンス
			PickupDAO pickupdao = new PickupDAO();
			PrefectureDAO prefdao = new PrefectureDAO();
			
			// [ 取得 ] 候補地の詳細
			PickupDTO pickupdto = pickupdao.select(loginUser, pk);
			
			// [ 判定 ] ユーザーの登録範囲外IDか
			if (Objects.isNull(pickupdto)) {
				throw new NullPointerException(
						"ユーザーの登録範囲外IDです...\n");
			}
			
			// [ 取得 ] 全都道府県
			request.setAttribute("prefList", prefdao.selectAll());
			// [ セット ] 候補地の詳細
			request.setAttribute("pickup", pickupdto);
			
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println(e.getMessage());
			
			// [ リソース ] notFound.jsp
			view  = "/WEB-INF/jsp/notFound.jsp";
			// エラー表示
			request.setAttribute("errorMessage", 
					"""
					<h1>見つかりません</h1>
					<p>指定されたページは存在しません。</p>
					""");
		}
		
		// [ 準備 ] 送信するリソースのオブジェクトを定義
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(view);
		// [ 転送 ] 指定されたリソースに
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// [ 設定 ] UTF-8 で受け取る
		request.setCharacterEncoding("UTF-8");
		
		// [ ロケーション ] VisitorServlet?pk=訪問地ID
		String url = request.getContextPath() + "/PickupServlet" + String.format("?pk=%s", pk);

		// [ 取得 ] セッション
		HttpSession session = request.getSession();
		// [ 取得 ] ログインユーザー
		UserDTO loginUser = (UserDTO) session.getAttribute("user_id");
		System.out.println("----------------------------------");
		System.out.println("● [ login ] " + loginUser.getNickname());
		System.out.println("----------------------------------");
		
		// [ 取得 ] 更新 / 削除 / 訪問地登録 のいずれか
		String execution = request.getParameter("execution");
		System.out.println("execution: "+ execution);
		System.out.println();
		
		// [ 宣言 ] 使用するモデルのインスタンス
		PickupDAO pickupdao = new PickupDAO();
		
		// [ Entity ] 候補地の詳細
		PickupDTO pickupdto = new PickupDTO();
		pickupdto.setPickup_id(pk);
		
		// 都道府県 prefecture
		String prefecture = request.getParameter(
				"prefecture");
		pickupdto.setPrefecture_id(
				Integer.parseInt(prefecture));
		// 場所 place
		String place = request.getParameter(
				"place");
		pickupdto.setPickup_place(
				place);
		// 備考 remarks
		String remarks = request.getParameter(
				"remarks");
		pickupdto.setRemarks(
				remarks);
		
		// 以下のいずれかを実行する
		switch (execution) {
		case "MovedButton":
			// 訪問地に候補地の入力を移行する
			System.out.println("候補地のデータを訪問地の登録に移行");
			
			// [ ロケーション ] VisitorRegistServlet
			url = request.getContextPath() + "/VisitorRegistServlet";
			
			// [ Entity ] 訪問地
			VisitorDTO visitordto = new VisitorDTO();
			visitordto.setPrefecture_id(
					pickupdto.getPrefecture_id());
			visitordto.setVisitor_place(
					pickupdto.getPickup_place());
			visitordto.setThought(
					pickupdto.getRemarks());
			
			// [ 削除 ] 候補地のレコード
			pickupdao.delete(pk);
			
			// [ セット ] 訪問地の登録に渡す値
			session.setAttribute("visitor", visitordto);
			break;
		
		case "DeleteButton":
			// [ ロケーション ] ListServlet
			url = request.getContextPath() + "/ListServlet";
			
			// [ 削除 ] 候補地のレコード
			pickupdao.delete(pk);
			break;

		case "UpdateButton":
			// [ 更新 ] 候補地のレコード
			pickupdao.update(loginUser.getUser_id(), pickupdto);
			break;
		
		default:
			break;
		}
        
		// [ 転送 ]  代替の場所（URL）に
		response.sendRedirect (url);
	}

}
