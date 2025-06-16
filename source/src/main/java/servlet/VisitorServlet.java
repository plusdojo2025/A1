package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [ 画面 ] 詳細（訪問地）
 * Servlet implementation class VisitorServlet
 */
@WebServlet("/VisitorServlet")
public class VisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// [ 取得 ] 訪問地
		// 開始日
		String startDate = "2025-05-03";
		request.setAttribute("startDate", startDate);
		// 終了日
		String endDate = "2025-05-03";
		request.setAttribute("endDate", endDate);
		// タイトル
		String title = "長野・富山旅行";
		request.setAttribute("title", title);
		// 都道府県
		int prefectureId = 20;
		String prefecture = "長野県";
		request.setAttribute("prefectureId", prefectureId);
		request.setAttribute("prefecture", prefecture);
		// 場所
		String place = "野辺山";
		request.setAttribute("place", place);
		// 同行者
		String componion = "家族";
		request.setAttribute("componion", componion);
		// 感情
		int emotionId = 1;
		String emotion = "😊";
		request.setAttribute("emotionId", emotionId);
		request.setAttribute("emotion", emotion);
		// 感想
		String thought = """
				2025年コナン映画の聖地巡礼に出かけた
				映像内に登場したものを、目にすることができて楽しかった
				""";
		request.setAttribute("thought", thought);
		// 画像ファイル名
		String fileName = ""; 
		request.setAttribute("fileName", fileName);
		
		// [ リソース ] visitor.jsp
		String view = "/WEB-INF/jsp/visitor.jsp";
		// [ 準備 ] 送信するリソースのオブジェクトを定義
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(view);
		// [ 転送 ] visitor.jsp に
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
