package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PrefectureDAO;
import dao.VisitorDAO;
import dto.PrefectureDTO;
import dto.VisitorDTO;

/**
 * Servlet implementation class VisitorRegistServlet
 */
@WebServlet("/VisitorRegistServlet")
public class VisitorRegistServlet extends HttpServlet {
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
			
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();
			if (session.getAttribute("user_id") == null) {
				response.sendRedirect("/A1/LoginServlet");
				return;
			}
	
			// 登録ページにフォワードする
			String view = "/WEB-INF/jsp/visitorRegist.jsp";
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(view);
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
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/A1/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
			    String user_id = request.getParameter("user_id");
			    String start_date_str = request.getParameter("start_date");  // String型として取得
			    String end_date_str = request.getParameter("end_date");      // String型として取得
			    String title = request.getParameter("title");
			    String prefecture_id_str = request.getParameter("prefecture_id");  // String型
			    String place = request.getParameter("place");
			    String componion = request.getParameter("componion");
			    String emotion_id_str = request.getParameter("emotion_id");   // String型
			    String thought = request.getParameter("thought");
			    String photo = request.getParameter("photo");

			 // 入力値の保持用
			    request.setAttribute("user_id", user_id);
			    request.setAttribute("title", title);
			    request.setAttribute("start_date", start_date_str);
			    request.setAttribute("end_date", end_date_str);
			    request.setAttribute("componion", componion);
			    request.setAttribute("prefecture_id", prefecture_id_str);
			    request.setAttribute("place", place);
			    request.setAttribute("emotion_id", emotion_id_str);
			    request.setAttribute("thought", thought);
			    
			    // start_date と end_date を Date 型に変換
			    Date start_date = null;
			    Date end_date = null;

			    if (start_date_str != null && !start_date_str.isEmpty()) {
			        start_date = Date.valueOf(start_date_str);  // yyyy-mm-dd形式
			    }

			    if (end_date_str != null && !end_date_str.isEmpty()) {
			        end_date = Date.valueOf(end_date_str);  // yyyy-mm-dd形式
			    }

			    // emotion_id を int 型に変換
			    int emotion_id = Integer.parseInt(emotion_id_str);

			    // prefecture_id を int 型に変換
			    int prefecture_id = Integer.parseInt(prefecture_id_str);
			    
				
			    // DTOにセット
			    VisitorDTO dto = new VisitorDTO(0, user_id, title, componion, start_date, end_date, prefecture_id, place, thought, emotion_id, photo, null, null, null, null);

				// DB登録処理
				VisitorDAO dao = new VisitorDAO();
				boolean success = dao.insert(dto);


				if (success) {
			        // 成功 → リダイレクトや再フォワードで登録画面に戻る
			        request.setAttribute("message", "登録が完了しました！");
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp");
			        dispatcher.forward(request, response);
			    } else {
			        // 失敗 → 入力値保持したまま再表示
			        request.setAttribute("error", "登録に失敗しました。再度確認してください。");
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp");
			        dispatcher.forward(request, response);
			    }
			}

}
