package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VisitorDAO;
import dto.PrefectureFootprintDTO;
import dto.UserDTO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// [ リソース ] home.jsp
		String view = "/WEB-INF/jsp/home.jsp";
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user_id") == null) {
			String url = request.getContextPath()+"/LoginServlet";
			response.sendRedirect(url);
			return;
		} 
		
		// [ Entity ] ログイン中のユーザー
		UserDTO userdto = (UserDTO) session.getAttribute("user_id");
		// [ 宣言 ] 使用するモデルのインスタンス
		VisitorDAO visitordao = new VisitorDAO();
		// [ 取得 ] ユーザーの各都道府県に訪れた回数（色クラス名対応済み）
		Map<String, PrefectureFootprintDTO> preFootsMap = visitordao.prefectureFootprint(userdto.getUser_id());
		// [ セット ] 取得した訪れた回数に応じた色クラス表
		request.setAttribute("preFoots", preFootsMap);
		
		// ログイン済みなのでhome.jspにフォワード
		RequestDispatcher dispatcher = request.
				getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}