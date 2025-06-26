package servlet;

public class Env {
	
	/**
	 * <h3>ユーザーがアップロードしたファイルの保存先です。</h3>
	 * 
	 * <h4>Tomcat: </h4>
	 * <p>サーバーの絶対パスを取得するメソッドと文字列連結を行うこと。</p>
	 * <p>media フォルダは連結を行うことで、自動生成されます。</p>
	 * 
	 * <h4>local: </h4>
	 * <p>保存先までの絶対パスを直書きして文字列連結を行うこと。</p>
	 * <p>各々のPC環境に合わせて media フォルダを作成してください。</p>
	 * <p>作成場所は webapp 直下です。</p> 
	 * 
	 * <h4>JSTLで画像ファイルパスを取得するには... </h4>
	 * <p>< c:url value="/media/ファイル名.拡張子" /></p>
	 */
	public static final String MEDIA_DIR = "/assets/imgs/";
	
	/**
	 * <h3>テスト環境（PC）</h3>
	 * <p>media フォルダまでの絶対パス</p>
	 * 
	 * <h4>パス指定 とは</h4>
	 * <img width="500" src="https://images.prismic.io/and-engineer/00634997-474f-4b33-be33-03e930dc9c06_img_soutaizettaipath_02.png?auto=compress%2Cformat&w=768" />
	 */
	public static final String devPath = "C:\\[ここからパスを入力してください]"; 
//	public static final String devPath = "C:\\Users\\user\\Documents\\workspace\\plusdojo2025\\A1\\source\\src\\main\\webapp";
	
}


