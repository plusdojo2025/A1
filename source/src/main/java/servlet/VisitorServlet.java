package servlet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import dto.UserDTO;
import dto.VisitorDTO;

/**
 * [ 画面 ] 詳細（訪問地）
 * Servlet implementation class VisitorServlet
 */
@MultipartConfig(
		//アップロードされたファイルが
		// 一時的に保存されるディレクトリのパス。
	  location=""
)
@WebServlet("/VisitorServlet")
public class VisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int pk = -1;
	private static VisitorDTO visitordto = null;

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
		// [ 設定 ] リクエスト / UTF8
		request.setCharacterEncoding("UTF-8");
		
		// [ リソース ] visitor.jsp
		String view = "/WEB-INF/jsp/visitor.jsp";
		
		// [ 取得 ] セッション
		HttpSession session = request.getSession();
		// [ 取得 ] ログインユーザー
		UserDTO loginUser = (UserDTO) session.getAttribute("user_id");
		
		// [ 判定 ] 未ログイン
		if (Objects.isNull(loginUser)) {
			// [ ロケーション ] LoginServlet
			String url = request.getContextPath() + "/LoginServlet";
			// [ 転送 ] 代替の場所（URL）に
			response.sendRedirect(url);
			return;
		}
		// ログイン済みのユーザー名を表示
		System.out.println("----------------------------------");
		System.out.println("● [ login ] " + loginUser.getNickname());
		System.out.println("----------------------------------");
		
		
		try {
			// [ GET ] 訪問地ID
			String pkStr = request.getParameter("pk");
			System.out.println("pk: " + pkStr);
			pk = Integer.parseInt(pkStr);
			System.out.println();
			
			// [ 宣言 ] 使用するモデルのインスタンス
			VisitorDAO visitordao = new VisitorDAO();
			PrefectureDAO prefdao = new PrefectureDAO();
			EmotionDAO emotiondao = new EmotionDAO();
			
			// [ 取得 ] 訪問地の詳細
			visitordto = visitordao.select(loginUser, pk);
			
			// [ 判定 ] ユーザーの登録範囲外IDか
			if (Objects.isNull(visitordto)) {
				throw new NullPointerException(
						"ユーザーの登録範囲外IDです...\n");
			}
			
			// [ 取得 ] 全都道府県
			request.setAttribute("prefList", prefdao.selectAll());
			// [ 取得 ] 全感情
			request.setAttribute("emoList", emotiondao.selectAll());
			// [ セット ] 訪問地の詳細
			request.setAttribute("visitor", visitordto);
			
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println(e.getMessage());
			
			// [ リソース ] notFound.jsp
			view  = "/WEB-INF/jsp/notFound.jsp";
			// エラー表示
			request.setAttribute("errorMessage", 
					"""
					<h1>見つかりません</h1>
					<p>指定されたページは存在しません。</p>
					""");
		}
		
		// [ 準備 ] 送信するリソースのオブジェクトを定義
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(view);
		// [ 転送 ] 指定されたリソースに
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// [ 設定 ] UTF-8 で受け取る
		request.setCharacterEncoding("UTF-8");
		
		// [ ロケーション ] VisitorServlet?pk=訪問地ID
		String url = request.getContextPath() + "/VisitorServlet" + String.format("?pk=%s", pk);
		
		
		// [ 取得 ] セッション
		HttpSession session = request.getSession();
		// [ 取得 ] ログインユーザー
		UserDTO loginUser = (UserDTO) session.getAttribute("user_id");
		System.out.println("----------------------------------");
		System.out.println("● [ login ] " + loginUser.getNickname());
		System.out.println("----------------------------------");
		
		// [ 取得 ] 更新 / 削除 のどちらをするか
		String execution = request.getParameter("execution");
		System.out.println("execution: "+ execution);
		System.out.println();
		
		// [ 宣言 ] 使用するモデルのインスタンス
		VisitorDAO visitordao = new VisitorDAO(); 
		
		// [ 設定 ] フルパス指定
		String fileFullPath;
		
//		// [ 本番 ] フルパス指定
		String proPath = getServletContext().getRealPath(
				Env.MEDIA_DIR);
        System.out.println("本番環境: ");
        System.out.println(proPath);
		
//		// [ 開発 ] フルパス指定
//		String devPath = Env.devPath + Env.MEDIA_DIR;
//		System.out.println("開発環境: ");
//		System.out.println(devPath);
        System.out.println();
        
		
		// [ 判定 ] 削除の要求か
		if (execution.equals("DeleteButton")) {
			// 詳細地の削除
			visitordao.delete(pk);
			
			// [ ロケーション ] VisitorListServlet
			url = request.getContextPath() + "/VisitorListServlet";
			// [ 転送 ]  代替の場所（URL）に
			response.sendRedirect (url);
			return;
		}
		
		
		
		// ----------------------------------
		// ● 更新の要求
		// ----------------------------------
		System.out.println("入力欄を取得します...\n");
		// [ Entity ] 訪問地
		VisitorDTO updatevisitordto = new VisitorDTO();
		updatevisitordto.setVisitor_id(pk);
		
		// 開始日
		String start_date = request.getParameter(
				"start_date");
		updatevisitordto.setStart_date(start_date);
		// 終了日
		String end_date = request.getParameter(
				"end_date");
		updatevisitordto.setEnd_date(end_date);
		// お題
		String title = request.getParameter(
				"title");
		updatevisitordto.setTitle(title);
		// 都道府県
		String prefectureId = request.getParameter(
				"prefecture");
		updatevisitordto.setPrefecture_id(Integer.parseInt(
				prefectureId));
		// 場所
		String place = request.getParameter(
				"place");
		updatevisitordto.setVisitor_place(place);
		// 同行者
		String componion = request.getParameter(
				"componion");
		updatevisitordto.setComponion(componion);
		// 感情
		String emotionId = request.getParameter(
				"emotion");
		updatevisitordto.setEmotion_id(Integer.parseInt(
				emotionId));
		// 感想
		String thought = request.getParameter(
				"thought");
		updatevisitordto.setThought(thought);
		
		
		// [ 取得 ] ファイルデータ
		Part[] parts = {
				request.getPart("photo1"),
				request.getPart("photo2"),
				request.getPart("photo3"),
				request.getPart("photo4"),
				request.getPart("photo5")
		};
		
		// [ 取得 ] 保存されたファイル名
		String[] files = {
				visitordto.getPhoto1(),
				visitordto.getPhoto2(),
				visitordto.getPhoto3(),
				visitordto.getPhoto4(),
				visitordto.getPhoto5()
		};
		
		// [ 取得 ] 削除要求がされたか
		String[] deleteRequests = {
				request.getParameter("delReq1"),
				request.getParameter("delReq2"),
				request.getParameter("delReq3"),
				request.getParameter("delReq4"),
				request.getParameter("delReq5"),
		};
		
		
//		// 各ユーザーのフォルダを作成
//		System.out.println("----------------------------------");
//		System.out.println("● 各ユーザーのフォルダを作成");
//		System.out.println("----------------------------------");
//		System.out.println("ファイルの保存先が存在するか調べます...");
//		try {
//			// 対象となるフォルダパス
//			String target;
//			// [ 本番 ] ユーザーフォルダまでのパス
//			target = proPath;
////			// [ 開発 ] ユーザーフォルダまでのパス
////			target = devPath;
//			
//			// [ 取得 ] ファイルオブジェからフォルダ作成する
//			File user_dir = new File(target);
//			
//			// ユーザーのフォルダが無い場合
//			if (user_dir.isDirectory() == false) {
//				// フォルダを作成する
//				user_dir.mkdirs();
//				System.out.println("フォルダを作成しました。");
//			} else {
//				System.out.println("既にフォルダは存在します。");
//			}
//		} catch (SecurityException e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//			
//			System.out.println("フォルダが作成できない為、諸々の操作を却下します。");
//			
//			// [ ロケーション ] VisitorListServlet
//			url = request.getContextPath() + "/VisitorListServlet";
//			
//			// [ 転送 ]  代替の場所（URL）に
//			response.sendRedirect (url);
//		}
//		System.out.println();
		
		
		// [ media ] 複数のファイル保存処理
		System.out.println("----------------------------------");
		System.out.println("■ [ media ] 複数ファイルの処理");
		System.out.println("----------------------------------");
		for (int i = 0; i < parts.length; ++i) {
			// [ 短縮 ] ファイルオブジェの取得
			Part part = parts[i];
			// [ 短縮 ] 保存されているファイル名
			String old = files[i];
			
			System.out.println("----------------------------------");
			System.out.println("● [ part ] " + part.getName());
			System.out.println("----------------------------------");
			
			// [ 抽出 ] ファイル名.拡張子
			String fileName = this.getFileName(part);
			
			// [ 判定 ] 前に保存したファイルがあるか
			if (Objects.isNull(old) == false) {
				System.out.println("保存済みのファイルがあります。");
				
				// [ 取得 ] 削除するかの内容
				String deleteRequest = deleteRequests[i];
				// [ 削除要請 ] 画像が非表示になる「リセット」の表示時
				// 後ろで hidden に削除が入力されたら実行する
				boolean isDeleteRequest = deleteRequest.equals("削除");
				// 削除要請がないなら
				if (isDeleteRequest == false) {
					System.out.println("削除要請の対象外です...\n");
				} else {
					// 削除を行う
					System.out.println("ファイル削除を実行します。");
					// [ 本番 ] 旧ファイルフルパス
					fileFullPath = proPath + old;
					
//					// [ 開発 ] 旧ファイルフルパス
//					fileFullPath = devPath + old;
					
					// 対象のファイルを削除
					this.deleteMediaFile(fileFullPath);
					
					// ファイル名を削除
					files[i] = null;
				}
			}
			
			// [ 判定 ] ファイル名が無いなら
			if (fileName.isEmpty()) {
				System.out.println("ファイルデータはありません...\n");
				continue;
			}
			
			// [ 判定 ] 同じファイルなら
			if (fileName.equals(old)) {
				System.out.println("同ファイル名を検知しました。");
				System.out.println("更新の対象外です。");
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
			fileName = loginUser.getUser_id() + salt + fileName;
			System.out.println("ファイル名をソルト値に変更しました...");
			System.out.println("fileName: " + fileName);
			
				
			try {
				// [ 調整 ] ファイル書き込み時のソルト値の為に
				Thread.sleep(1500);
				System.out.println("時間の調整が終わりました...");
				
				// [ 判定 ] 旧ファイルが存在する場合
				if (Objects.isNull(old) == false) {
					// [ 本番 ] 旧ファイルフルパス
					fileFullPath = proPath + old;
					
//					// [ 開発 ] 旧ファイルフルパス
//					fileFullPath = devPath + old;
					
					// 対象のファイルを削除
					this.deleteMediaFile(fileFullPath);
				}
				
				// [ 本番 ] 新規ファイルフルパス
				fileFullPath = proPath + fileName;
				
//				// [ 開発 ] 新規ファイルフルパス
//				fileFullPath = devPath + fileName;
				
				// 場所（location） は フルパス で指定してある
				System.out.println("ファイル書き起こし中...");
				part.write(fileFullPath);
				System.out.println("ファイルの書き込みが終了しました。\n");
				
				// [ セット ] 更新するファイル名
				files[i] = fileName;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		System.out.println("----------------------------------");
		
		
		// [ セット ] 複数ファイル名
		updatevisitordto.setPhoto1(files[0]);
		updatevisitordto.setPhoto2(files[1]);
		updatevisitordto.setPhoto3(files[2]);
		updatevisitordto.setPhoto4(files[3]);
		updatevisitordto.setPhoto5(files[4]);
		
		// [ 更新 ] 訪問地のレコード
		visitordao.update(loginUser.getUser_id(), updatevisitordto);
        
		// [ 転送 ]  代替の場所（URL）に
		response.sendRedirect (url);
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
        
//		[ デバッグ ] 拡張子取得
//		System.out.println(name.lastIndexOf("."));
//		System.out.println(name.substring(name.lastIndexOf(".")));
        
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
	
	/**
	 * 
	 * <h3>メディアにあるファイルの削除</h3>
	 * 
	 * <p>対象ファイルのフルパスを指定して、
	 * 削除を実行します。</p>
	 * 
	 * <p>また、指定されたファイル名は
	 * テーブル上で上書きされる為、
	 * 確実に削除される必要があります。</p>
	 * 
	 * @param fullFilePath
	 */
	public void deleteMediaFile(String fullFilePath) {
		// [ インスタンス ] ファイルオブジェを取得
		// ファイルのフルパス指定
		File target = new File(fullFilePath);
		boolean isFileExists = true;
		
		System.out.println("対象: " + target.getName());
		
		while (isFileExists == true) {
			try {
				// [ スレッド ] 小休憩を挟んで再実行させる
				// 1500 ミリ秒 停止
				Thread.sleep(1500);
				System.out.println("1.5秒の休憩明け...");
				
				// 存在するかどうかを確認する
				isFileExists = target.isFile();
				System.out.println("対象のファイルが存在するか: " + isFileExists);
				
				// 削除を実行する
				if (isFileExists == true) {
					target.delete();
					System.out.println("対象のファイルを削除しました...");
				}
			} catch (SecurityException | InterruptedException e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		System.out.println();
	}

}
