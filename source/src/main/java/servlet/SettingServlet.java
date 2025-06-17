package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.UserDTO;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータを取得する。
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");	
		int prefecture_id = Integer.parseInt(request.getParameter("prefecture_id"));

		
		UserDAO uDAO = new UserDAO();
		if (uDAO.update(new UserDTO(user_id,password,nickname,prefecture_id))) { // 登録成功
			
		} else { // 登録失敗

		}
		
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
		dispatcher.forward(request, response);
	}

}
