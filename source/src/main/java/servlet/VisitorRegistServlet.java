package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		//アップロードされたファイルが
		// 一時的に保存されるディレクトリのパス。
	  location=""
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
			
			// 候補地に切り替え
			request.setAttribute("activeTab", "tab1");
	
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
			    System.out.println("初日: " + start_date_str);
			    System.out.println("終日: " + end_date_str);
			    System.out.println("お題: " + title);
			    System.out.println("都道府県ID: " + prefecture_id_str);
			    System.out.println("場所: " + visitor_place);
			    System.out.println("同行者: " + componion);
			    System.out.println("感情ID: " + emotion_id_str);
			    System.out.println("感想: " + thought);

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
			    // フルパス指定
			    String fileFullPath;
			    
			    // [ 本番 ] フルパス指定
			    String uploadPath = getServletContext().getRealPath(
			    		Env.MEDIA_DIR);
			    
			    // アップロードするファイル名がここに
			    String[] savedFileNames = new String[5];
			    
			    // [ 取得 ] ファイルデータ
			    Part[] parts = {
			        request.getPart("photo1"),
			        request.getPart("photo2"),
			        request.getPart("photo3"),
			        request.getPart("photo4"),
			        request.getPart("photo5")
			    };

				// [ media ] 複数のファイル保存処理
				System.out.println("----------------------------------");
				System.out.println("■ [ media ] 複数ファイルの処理");
				System.out.println("----------------------------------");
			    for (int i = 0; i < parts.length; i++) {
					// [ 短縮 ] ファイルオブジェの取得
					Part part = parts[i];
					// 初期化（空文字）
					savedFileNames[i] = "";
					
					
					System.out.println("----------------------------------");
					System.out.println("● [ part ] " + part.getName());
					System.out.println("----------------------------------");
					
					// [ 抽出 ] ファイル名.拡張子
					String fileName = this.getFileName(part);
					
					// [ 判定 ] ファイル名が無いなら
					if (fileName.isEmpty()) {
						System.out.println("ファイルデータはありません...\n");
						continue;
					}
					
					// ----------------------------------
					// ★ 画像ファイルの作成の準備
					// ----------------------------------
					// [ 抽出 ] ドット付き拡張子
					fileName = fileName.substring(
							fileName.lastIndexOf("."));
					System.out.printf(
							"拡張子「%s」を抽出しました...\n",
							fileName);
					
					// [ 取得 ] タイムスタンプ（ソルト値に該当）
					String salt = this.getSalt();
					
					// [ 連結 ] タイムスタンプ + 拡張子
					fileName = user_id + salt + fileName;
					System.out.println("ファイル名をソルト値に変更しました...");
					System.out.println("fileName: " + fileName);
					
					try {
						// [ 調整 ] ファイル書き込み時のソルト値の為に
						Thread.sleep(1500);
						System.out.println("時間の調整が終わりました...");
						
						// 新規ファイルフルパス
						fileFullPath = uploadPath + fileName;
						
						// 場所（location） は フルパス で指定してある
						System.out.println("ファイル書き起こし中...");
						part.write(fileFullPath);
						System.out.println("ファイルの書き込みが終了しました。\n");
						
						// [ セット ] 更新するファイル名
						savedFileNames[i] = fileName;
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
					}
			    }
				System.out.println("----------------------------------");
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
			    int emotion_id = -1;
			    if (emotion_id_str != null && !emotion_id_str.isEmpty()) {
			        try {
			            emotion_id = Integer.parseInt(emotion_id_str);
			        } catch (NumberFormatException e) {
			        	System.out.println(e.getMessage());
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
					response.sendRedirect(request.getContextPath() + "/VisitorRegistServlet");
			    } else {
			        // 失敗：入力値保持して戻る
			        request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visitorRegist.jsp");
			        dispatcher.forward(request, response);
			    }
			}

	
	
	

	/**
	 * ファイルの名前を取得する
	 * @param [ Part ] 受信したファイルデータ
	 * @return [ String ] ファイル名.拡張子
	 */
	private String getFileName(Part part) {
        String name = null;
		System.out.println("ファイル名.拡張子 を取得します...");
        // Content-Disposition: form-data; name="fieldName"; filename="filename.jpg"
        // 複数の引数は　セミコロン で区切ります。
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
        	// filename パラメーター なら
            if (dispotion.trim().startsWith("filename")) {
				System.out.println("ファイル名.拡張子 の抽出中...");
            	// パラメータから filename.jpg を抽出する
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                // ファイル名までのパスを切り捨てる
                name = name.substring(name.lastIndexOf("\\") + 1);
				System.out.println("抽出完了しました...");
                break;
            }
        }
        System.out.println("filename: " + name);
        System.out.println();
        
		return name;
	}
	
	/**
	 * 取得したタイムスタンプをソルト値として返す
	 * 目的: ファイルアクセスを困難にすること
	 * @return [ String ] タイムスタンプ文字列
	 */
	private String getSalt() {
		// ソルト値
		String salt;
		
		// [ 取得 ] ローカル時間
		LocalDateTime now  = LocalDateTime.now();
		
		// [ 設定 ] 日時の表示形式を指定
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern(
        		"yyyy_MM_dd_HH_mm_ss");
        
        // [ デバッグ ] 日時を取得
        System.out.println("現在時刻: " + now);
        
        // [ 変換 ] 日時を文字列に
        salt = dtf.format(now);
		System.out.println("ソルト: " + salt);
		System.out.println();
        
		// ソルト値としてタイムスタンプ文字列を返す
		return salt;
	}
	
	
	
	
}
