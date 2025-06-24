package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.UserDTO;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		// 設定画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//リクエストパラメータを取得する。
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String nickname = request.getParameter("nickname");
		int prefecture_id = Integer.parseInt(request.getParameter("prefecture_id"));
		
		System.out.println("ユーザーID"+user_id);
		System.out.println("現在のパスワード"+password1);
		System.out.println("変更後のパスワード"+password2);
		System.out.println("ニックネーム"+nickname);
		System.out.println("都道府県ID"+prefecture_id);
		
		UserDAO uDAO = new UserDAO();
	
	if (password1 != ""){
		//現在のパスワードがあっているかを確認して、合っていれば変更処理を行う。
			boolean isAuth;
			
			isAuth = uDAO.isAuth(user_id,password1);
		
			if (isAuth) { // ログイン成功

				//更新処理を行う。
				if (uDAO.update(new UserDTO(user_id,password2,nickname,prefecture_id))) { // 更新成功
					System.out.println("更新処理に成功しました。");
					UserDTO loginUser = uDAO.getLoginUser(user_id); 
					// セッションスコープにIDを格納する
					HttpSession session = request.getSession();
					session.setAttribute("user_id",loginUser);
				} else { // 更新失敗
					System.out.println("更新に失敗しました。");
				}
			}else {//ログイン失敗
				request.setAttribute("errorMessage", // エラーメッセージを出す
						"現在のパスワードが間違っています。");
				String view = "/WEB-INF/jsp/setting.jsp";
				RequestDispatcher dispatcher =   // ログイン画面へ戻す
						request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
				
			}
		}else {
			if (uDAO.update(new UserDTO(user_id,password2,nickname,prefecture_id))) { // 更新成功
				System.out.println("更新処理に成功しました。");
				UserDTO loginUser = uDAO.getLoginUser(user_id); 
				// セッションスコープにIDを格納する
				HttpSession session = request.getSession();
				session.setAttribute("user_id",loginUser);
			} else { // 更新失敗
				System.out.println("更新に失敗しました。");
			}
		}

		// 設定ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
		dispatcher.forward(request, response);
	}
}

