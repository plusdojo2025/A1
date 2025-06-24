package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BadgeDAO;
import dto.BadgeDTO;
import dto.UserDTO;
/**
 * Servlet implementation class BabgeServlet
 */
@WebServlet("/BabgeServlet")
public class BabgeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public BabgeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	//セッションを取得
        HttpSession session = request.getSession(false);
        
	    //セッションが存在していれば、その中から"user_id"というキーで保存されているオブジェクトを取り出す
	    //そのオブジェクトはUserDTO型であることがわかっているので、キャストする
	    UserDTO loginUser = null;
	    if (session != null) {
	    	//セッションからObject型を取得
	        Object obj = session.getAttribute("user_id");
	        //型がUserDTOか確認
	        if (obj instanceof UserDTO) {
	        	//UserDTO型にキャストして代入
	            loginUser = (UserDTO) obj;
	        }
	    }
	    
        //ログインしていないときはログイン画面へリダイレクト
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        //user_id を取得
     	String userId = loginUser.getUser_id();
     	System.out.println("[BabgeServlet] ログインユーザーID: " + userId);

        //バッジDAOでユーザーのバッジ情報取得
        BadgeDAO dao = new BadgeDAO();
        List<BadgeDTO> badgeList = dao.getAllBadgesWithUserStatus(userId);
        System.out.println("[BabgeServlet] 取得したバッジ数: " + badgeList.size());

        //バッジ情報をリクエストスコープに格納
        session.setAttribute("badgeList", badgeList);

        //badge.jsp へフォワード
		/*
		 * RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		 */
        String url = request.getContextPath() + "/HomeServlet";
		response.sendRedirect (url);
		/* dispatcher.forward(request, response); */
    }
}