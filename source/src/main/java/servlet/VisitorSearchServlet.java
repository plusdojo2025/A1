package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmotionDAO;
import dao.PrefectureDAO;
import dao.VisitorDAO;
import dto.EmotionDTO;
import dto.PrefectureDTO;
import dto.UserDTO;
import dto.VisitorDTO;

/**
 * Servlet implementation class VisitorSearchServlet
 */
@WebServlet(urlPatterns = {"/VisitorSearchServlet"})

//@WebServlet("/VisitorSearchServlet")
public class VisitorSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// インスタンス化
		PrefectureDAO preDao = new PrefectureDAO();
		// DBから都道府県一覧を取得
		ArrayList<PrefectureDTO> prefectureList = preDao.selectAll();
		System.out.println("size: " + prefectureList.size());

		// JSPに渡すためリクエスト属性にセット
		request.setAttribute("prefectureList", prefectureList);
		
		//インスタンス化
		EmotionDAO emoDao = new EmotionDAO();
		//DBから感情一覧を取得
		ArrayList<EmotionDTO> emoList = emoDao.selectAll();
		System.out.println("size: " + emoList.size());
		//JSPに渡すためリクエスト属性にセット
		request.setAttribute("emoList", emoList);

		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		// 検索ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorSearch.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		
		
		//ユーザーIDを取得している
		UserDTO userdto = (UserDTO)session.getAttribute("user_id");
		
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
	    String user_id = userdto.getUser_id();
	    String start_date_str = request.getParameter("start_date");  // String型として取得
	    String end_date_str = request.getParameter("end_date");      // String型として取得
	    String title = request.getParameter("title");
	    String prefecture_id_str = request.getParameter("prefecture_id");  // String型
	    String visitor_place = request.getParameter("visitor_place");
	    String componion = request.getParameter("componion");
	    String emotion_id_str = request.getParameter("emotion_id");   // String型
	    String thought = request.getParameter("thought");

	    // start_date と end_date を Date 型に変換
//	    VisitorDTO visitordto = (VisitorDTO)setStart_date(start_date_str);
//	    VisitorDTO visidto = (VisitorDTO)setEnd_date(end_date_str);
	    //utilldate型にしている
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    
	    java.sql.Date start_date = null;
	    java.sql.Date end_date = null;

	    //Date start_date = null ;
	    //Date end_date = null;
		try { //DTOがSQLDATE型だからutilldate型をSQLDATE型にしている
		    if (start_date_str != null && !start_date_str.isEmpty()) {
		        start_date = new java.sql.Date(sdf.parse(start_date_str).getTime());
		    }
		    if (end_date_str != null && !end_date_str.isEmpty()) {
		        end_date = new java.sql.Date(sdf.parse(end_date_str).getTime());
		    }
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

	    // emotion_id を int 型に変換
	 // 感情ID：nullや空文字なら 0（=条件なし）
	    int emotion_id = 0;
	    if (emotion_id_str != null && !emotion_id_str.isEmpty()) {
	        try {
	            emotion_id = Integer.parseInt(emotion_id_str);
	        } catch (NumberFormatException e) {
	            emotion_id = 0;
	        }
	    }
	    

	    // prefecture_id を int 型に変換
	 // 都道府県ID：同様に 0 を条件なしとする
	    int prefecture_id = 0;
	    if (prefecture_id_str != null && !prefecture_id_str.isEmpty()) {
	        try {
	            prefecture_id = Integer.parseInt(prefecture_id_str);
	        } catch (NumberFormatException e) {
	            prefecture_id = 0;
	        }
	    }
	    
		// 検索処理を行う
	    // DTOにセット
	    VisitorDTO dto = new VisitorDTO(0, user_id, title, componion, start_date, end_date, prefecture_id, visitor_place, thought, emotion_id, null, null, null, null, null);
		// DB検索処理
		VisitorDAO dao = new VisitorDAO();
		List<VisitorDTO> visitorList = dao.search(dto);
		// 検索結果をリクエストスコープに格納する
		request.setAttribute("visitorList", visitorList);
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorSearchresult.jsp");
		dispatcher.forward(request, response);
	}

}
