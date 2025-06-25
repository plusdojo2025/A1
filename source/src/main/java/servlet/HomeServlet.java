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
		
		String view = "/WEB-INF/jsp/home.jsp";
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user_id") == null) {
			String url = request.getContextPath()+"/LoginServlet";
			response.sendRedirect(url);
			return;
		} 
		
		UserDTO userdto = (UserDTO) session.getAttribute("user_id");
		VisitorDAO visitordao = new VisitorDAO();
		Map<String, PrefectureFootprintDTO> prefectureFootprintList = visitordao.prefectureFootprint(userdto.getUser_id());
			
		for(Map.Entry<String, PrefectureFootprintDTO> data : prefectureFootprintList.entrySet()) {
			PrefectureFootprintDTO dto = data.getValue();
			String colorCN = null;
			
			switch (dto.getArea_name()) {
				case "北海道地方":
					// 北海道地方 + 訪問レベル
					colorCN = String.format("hokkaido-%d", 
							dto.getFootprint_level());
					break;
				
				case "東北地方":
					// 東北地方 + 訪問レベル
					colorCN = String.format("touhoku-%d", 
							dto.getFootprint_level());
					break;
				
				case "関東地方":
					// 関東地方 + 訪問レベル
					colorCN = String.format("kantou-%d", 
							dto.getFootprint_level());
					colorCN = "kantou-3";
					break;
				
				case "中部地方":
					// 中部地方 + 訪問レベル
					colorCN = String.format("tyubu-%d", 
							dto.getFootprint_level());
					break;
				
				case "近畿地方":
					// 近畿地方 + 訪問レベル
					colorCN = String.format("kinki-%d", 
							dto.getFootprint_level());
					break;
				
				case "中国地方":
					// 中国地方 + 訪問レベル
					colorCN = String.format("tyugoku-%d", 
							dto.getFootprint_level());
					break;
				
				case "四国地方":
					// 四国地方 + 訪問レベル
					colorCN = String.format("sikoku-%d", 
							dto.getFootprint_level());
					break;
				
				case "九州地方（沖縄含む）":
					// 地方 + 訪問レベル
					colorCN = String.format("kyusyu-%d", 
							dto.getFootprint_level());
					break;
				
				default:
					break;
			}
			// 色クラスを格納
			dto.setFootprint_color(colorCN);
		}
		
		request.setAttribute("preFoots", prefectureFootprintList);
		
		// ログイン済みなのでhome.jspにフォワード
		RequestDispatcher dispatcher = request.
				getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}