package dto;

public class UserDTO {
	private String user_id;      // ユーザーID（主キー）
    private String password;    // パスワード
    private String nickname;        // ユーザー名
    private int prefecture_id;           // 居住地の都道府県ID（外部キー）
	
    // 引数付きコンストラクタ
    public UserDTO(String user_id, String password, String nickname, int prefecture_id) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.nickname = nickname;
		this.prefecture_id = prefecture_id;
	}
    //引数なしコンストラクタ
    public UserDTO() {
    	this.user_id = "";
		this.password = "";
		this.nickname = "";
		this.prefecture_id = -1;
    }
    
    
    
    // getterとsetter
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
}