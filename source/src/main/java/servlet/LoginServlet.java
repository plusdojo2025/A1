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
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = {"","/LoginServlet"})
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
		if (isAuth) { // ログイン成功
		 UserDTO loginUser = userdao.getLoginUser(userId); 
		 
			
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("user_id",loginUser);
			
			// メニューサーブレットにリダイレクトする
			String url = request.getContextPath() + "/HomeServlet";
			response.sendRedirect (url);
		}else {//ログイン失敗
			request.setAttribute("errorMessage",
					"IDまたはパスワードに間違いがあります。");
			String view = "/WEB-INF/jsp/login.jsp";
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
			
		
			

			
	}
}

