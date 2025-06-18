package dto;

public class PrefectureDTO {
	private int prefecture_id;   		// 都道府県ID（主キー）
    private String prefecture_name;		// 都道府県名
	
    // コンストラクタ
    public PrefectureDTO(int prefecture_id, String prefecture_name) {
		super();
		this.prefecture_id = prefecture_id;
		this.prefecture_name = prefecture_name;
	}
    
    // コンストラクタ
    public PrefectureDTO() {
    	prefecture_id = -1;
    	prefecture_name = "";
    }
    
    // getterとsetter
	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}

	public String getPrefecture_name() {
		return prefecture_name;
	}

	public void setPrefecture_name(String prefecture_name) {
		this.prefecture_name = prefecture_name;
	}
}
