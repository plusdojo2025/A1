package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [ 画面 ] 詳細（候補地）
 * Servlet implementation class PickupServlet
 */
@WebServlet("/PickupServlet")
public class PickupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		// [ 取得 ] 候補地
		// 都道府県
		int prefectureId = 9;
		String prefecture = "栃木県";
		request.setAttribute("prefectureId", prefectureId);
		request.setAttribute("prefecture", prefecture);
		// 場所
		String place = "餃子通り";
		request.setAttribute("place", place);
		// 備考
		String remarks = """
				餃子巡りしたい
				""";
		request.setAttribute("remarks", remarks);
		
		// [ リソース ] pickup.jsp
		String view = "/WEB-INF/jsp/pickup.jsp";
		// [ 準備 ] 送信するリソースのオブジェクトを定義
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(view);
		// [ 転送 ] pickup.jsp に
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
