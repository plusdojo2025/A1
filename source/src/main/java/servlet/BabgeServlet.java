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

@WebServlet("/BabgeServlet")
public class BabgeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BabgeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
	    
        //ログインしていないときはログイン画面へ
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        /// ユーザーIDを取り出す
     	String userId = loginUser.getUser_id();
     	System.out.println("[BabgeServlet] ログインユーザーID: " + userId);

     	
     	// バッジ一覧を取得
        BadgeDAO dao = new BadgeDAO();
        List<BadgeDTO> badgeList = dao.getAllBadgesWithUserStatus(userId);
        System.out.println("[BabgeServlet] 取得したバッジ数: " + badgeList.size());

        // セッションにバッジ情報を保存
        session.setAttribute("badgeList", badgeList);
        
        // 最終的にホーム画面へ遷移
        String url = request.getContextPath() + "/HomeServlet";
		response.sendRedirect (url);
    }
}
