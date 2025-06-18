package dto;

public class PickupDTO {
	    private int pickup_id;		// 候補地ID（主キー）
	    private String user_id;  		// ユーザーID（外部キー）
	    private int prefecture_id; 	// 都道府県（外部キー）
	    private String pickup_place;   	// 場所
	    private String remarks;		// 備考（コメント）
		
	 // コンストラクタ
	 public PickupDTO(int pickup_id, String user_id, int prefecture_id, String pickup_place, String remarks) {
		super();
		this.pickup_id = pickup_id;
		this.user_id = user_id;
		this.prefecture_id = prefecture_id;
		this.pickup_place = pickup_place;
		this.remarks = remarks;
	}
	 
	// getterとsetter
	public int getPickup_id() {
		return pickup_id;
	}

	public void setPickup_id(int pickup_id) {
		this.pickup_id = pickup_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}

	public String getPickup_place() {
		return pickup_place;
	}

	public void setPickup_place(String pickup_place) {
		this.pickup_place = pickup_place;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
