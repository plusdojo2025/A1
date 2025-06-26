package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GachaDAO;
import dto.PickupDTO;
import dto.UserDTO;

/**
 * Servlet implementation class GachaServlet
 */
@WebServlet("/GachaResultServlet")
public class GachaResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GachaResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user_id");
        
        //ログインしていなかったらログインページに遷移する。
        if (user == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        
        //ガチャ結果を取得するためのDAOを実行する
        GachaDAO dao = new GachaDAO();
        PickupDTO result = dao.getTodayResult(user.getUser_id());

        if (result != null) {
            session.setAttribute("selectedPickup", result);
            System.out.println("ガチャ結果サーブレットからgachaResult.jspに飛ぶよ");
            request.getRequestDispatcher("/WEB-INF/jsp/gachaResult.jsp").forward(request, response);
        } else {
        	System.out.println("ガチャ結果サーブレットからgachaError.jspに飛ぶよ");
            request.setAttribute("errorMessage", "本日のガチャ結果が見つかりませんでした。");
            request.getRequestDispatcher("/WEB-INF/jsp/gachaError.jsp").forward(request, response);
        }
    }
}
