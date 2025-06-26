package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BadgeDAO;
import dao.UserDAO;
import dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");

		
		// ログイン処理を行う
		UserDAO userdao = new UserDAO();
		//ログイン認証
		boolean isAuth = userdao.isAuth(userId,password);
		System.out.println("ログイン認証結果: " + isAuth);
		if (isAuth) { // ログイン成功
		 UserDTO loginUser = userdao.getLoginUser(userId); 
		 
			
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("user_id",loginUser);
			
			// バッジチェック＆セッションにバッジ一覧格納
		    BadgeDAO badgeDao = new BadgeDAO();
		    badgeDao.checkAndGrantBadges(userId); // バッジ自動付与
		    session.setAttribute("badgeList", badgeDao.getAllBadgesWithUserStatus(userId));
			
			// バッジサーブレットにリダイレクトする(画面遷移)
			String url = request.getContextPath() + "/BabgeServlet";
			response.sendRedirect (url);
		}else {//ログイン失敗
			System.out.println("ログイン失敗：ID/PWが一致しない");
			request.setAttribute("errorMessage", // エラーメッセージを出す
					"IDまたはパスワードに間違いがあります。");
			String view = "/WEB-INF/jsp/login.jsp";
			RequestDispatcher dispatcher =   // ログイン画面へ戻す
					request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
}

