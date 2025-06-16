package dto;

public class UserDTO {
	private String userId;      // ユーザーID（主キー）
    private String password;    // パスワード
    private String name;        // ユーザー名
    private int live;           // 居住地の都道府県ID（外部キー）
	
    // コンストラクタ
    public UserDTO(String userId, String password, String name, int live) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.live = live;
	}
    
    // getterとsetter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}
}
