package dto;

import java.sql.Timestamp;

public class HoldsDTO {
	private int hold_id;                // 保持ID（主キー）
    private int badge_id;              // バッジID（badgesテーブルと関連）
    private String user_id;            // ユーザーID（usersテーブルと関連）
    private Timestamp date_acquisition; // 獲得日時
	
    // getterとsetter
    public HoldsDTO(int hold_id, int badge_id, String user_id, Timestamp date_acquisition) {
		super();
		this.hold_id = hold_id;
		this.badge_id = badge_id;
		this.user_id = user_id;
		this.date_acquisition = date_acquisition;
	}
    
    // getterとsetter
	public int getHold_id() {
		return hold_id;
	}

	public void setHold_id(int hold_id) {
		this.hold_id = hold_id;
	}

	public int getBadge_id() {
		return badge_id;
	}

	public void setBadge_id(int badge_id) {
		this.badge_id = badge_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Timestamp getDate_acquisition() {
		return date_acquisition;
	}

	public void setDate_acquisition(Timestamp date_acquisition) {
		this.date_acquisition = date_acquisition;
	}
}
