package dto;

import java.sql.Timestamp;

public class HoldsDTO {
	private int holdId;                // 保持ID（主キー）
    private int badgeId;              // バッジID（badgesテーブルと関連）
    private String userId;            // ユーザーID（usersテーブルと関連）
    private Timestamp dateAcquisition; // 獲得日時
	
    // コンストラクタ
    public HoldsDTO(int holdId, int badgeId, String userId, Timestamp dateAcquisition) {
		super();
		this.holdId = holdId;
		this.badgeId = badgeId;
		this.userId = userId;
		this.dateAcquisition = dateAcquisition;
	}
    
    // getterとsetter
    public int getHoldId() {
		return holdId;
	}

	public void setHoldId(int holdId) {
		this.holdId = holdId;
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getDateAcquisition() {
		return dateAcquisition;
	}

	public void setDateAcquisition(Timestamp dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	} 
}
