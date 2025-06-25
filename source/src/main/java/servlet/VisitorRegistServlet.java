package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.EmotionDAO;
import dao.PrefectureDAO;
import dao.VisitorDAO;
import dto.EmotionDTO;
import dto.PrefectureDTO;
import dto.UserDTO;
import dto.VisitorDTO;

/**
 * Servlet implementation class VisitorRegistServlet
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,      // 1MB
	    maxFileSize = 1024 * 1024 * 5,        // 5MBまでのファイル
	    maxRequestSize = 1024 * 1024 * 25     // 合計25MB
	)

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
			
			// 候補地の移行データ
			VisitorDTO visitordto = (VisitorDTO) session.getAttribute("visitor");
			if (Objects.isNull(visitordto) == false) {
				request.setAttribute("prefecture_id", 
						visitordto.getPrefecture_id());
				request.setAttribute("place",
						visitordto.getVisitor_place());
				request.setAttribute("thought", 
						visitordto.getThought());
				// [ 破棄 ] 用済みになった移行データ
				session.removeAttribute("visitor");
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

			    System.out.println("ログインユーザーID: " + user_id);
			    System.out.println("都道府県ID: " + start_date_str);
			    System.out.println("場所: " + end_date_str);
			    System.out.println("場所: " + title);
			    System.out.println("場所: " + prefecture_id_str);
			    System.out.println("場所: " + visitor_place);
			    System.out.println("場所: " + componion);
			    System.out.println("場所: " + emotion_id_str);
			    System.out.println("場所: " + thought);

			 // 入力値の保持用
			    request.setAttribute("user_id", user_id);
			    request.setAttribute("title", title);
			    request.setAttribute("start_date", start_date_str);
			    request.setAttribute("end_date", end_date_str);
			    request.setAttribute("componion", componion);
			    request.setAttribute("prefecture_id", prefecture_id_str);
			    request.setAttribute("visitor_place", visitor_place);
			    request.setAttribute("emotion_id", emotion_id_str);
			    request.setAttribute("thought", thought);
			    
			 // 画像保存処理ここから
			    String uploadPath = getServletContext().getRealPath("/uploads");
			    File uploadDir = new File(uploadPath);
			    if (!uploadDir.exists()) {
			        uploadDir.mkdirs();
			    }

			    String[] savedFileNames = new String[5];
			    Part[] parts = {
			        request.getPart("photo1"),
			        request.getPart("photo2"),
			        request.getPart("photo3"),
			        request.getPart("photo4"),
			        request.getPart("photo5")
			    };

			    for (int i = 0; i < parts.length; i++) {
			        Part part = parts[i];
			        if (part != null && part.getSize() > 0) {
			            String submittedFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			            String extension = submittedFileName.substring(submittedFileName.lastIndexOf("."));
			            String fileName = user_id + "_" + System.currentTimeMillis() + "_" + (i + 1) + extension;

			            part.write(uploadPath + File.separator + fileName);
			            savedFileNames[i] = fileName;
			        } else {
			            savedFileNames[i] = null;
			        }
			    }
			    // 画像保存処理ここまで

			    // start_date と end_date を Date 型に変換
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
			    int emotion_id = 0;
			    if (emotion_id_str != null && !emotion_id_str.isEmpty()) {
			        try {
			            emotion_id = Integer.parseInt(emotion_id_str);
			        } catch (NumberFormatException e) {
			            emotion_id = 0;
			        } 
			    }

			    //int emotion_id = Integer.parseInt(emotion_id_str);

			    // prefecture_id を int 型に変換
			    int prefecture_id = 0;
			    if (prefecture_id_str != null && !prefecture_id_str.trim().isEmpty()) {
			        try {
			            prefecture_id = Integer.parseInt(prefecture_id_str);
			        } catch (NumberFormatException e) {
			            request.setAttribute("error", "都道府県の選択が正しくありません。");
			            request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp").forward(request, response);
			            return;
			        }
			    } else {
			        request.setAttribute("error", "都道府県を選択してください。");
			        request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp").forward(request, response);
			        return;
			    }

			   // int prefecture_id = Integer.parseInt(prefecture_id_str);
			    
				
			    // DTOにセット
			    VisitorDTO dto = new VisitorDTO(0, user_id, title, componion, start_date, end_date, prefecture_id, visitor_place, thought, emotion_id,
			    	    savedFileNames[0], savedFileNames[1], savedFileNames[2], savedFileNames[3], savedFileNames[4]);

				// DB登録処理
				VisitorDAO dao = new VisitorDAO();
				boolean success = dao.insert(dto);

				// ★デバッグ出力（visitor登録内容確認用）
				System.out.println("★Visitor登録内容確認：");
				System.out.println("user_id       = " + dto.getUser_id());
				System.out.println("title         = " + dto.getTitle());
				System.out.println("componion     = " + dto.getComponion());
				System.out.println("start_date    = " + dto.getStart_date());
				System.out.println("end_date      = " + dto.getEnd_date());
				System.out.println("prefecture_id = " + dto.getPrefecture_id());
				System.out.println("place         = " + dto.getVisitor_place());
				System.out.println("thought       = " + dto.getThought());
				System.out.println("emotion_id    = " + dto.getEmotion_id());
				System.out.println("photo1        = " + dto.getPhoto1());
				System.out.println("photo2        = " + dto.getPhoto2());
				System.out.println("photo3        = " + dto.getPhoto3());
				System.out.println("photo4        = " + dto.getPhoto4());
				System.out.println("photo5        = " + dto.getPhoto5());


			    if (success) {
			        // 成功：登録画面へリダイレクト（リセットされた状態）
					response.sendRedirect(request.getContextPath() + "/PickupRegistServlet");
			    } else {
			        // 失敗：入力値保持して戻る
			        request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp");
			        dispatcher.forward(request, response);
			    }
			}

}
