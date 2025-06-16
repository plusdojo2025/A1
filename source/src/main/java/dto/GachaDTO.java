package dto;

import java.sql.Timestamp;

public class GachaDTO {
	private int gachaId;           // ガチャID
    private String userId;         // ユーザーID（usersテーブルと関連）
    private Timestamp turnedDate;  // ガチャを回した日時
    private int pickupId;          // 選ばれた候補地ID（pickupsテーブルと関連）
	
    // コンストラクタ
    public GachaDTO(int gachaId, String userId, Timestamp turnedDate, int pickupId) {
		super();
		this.gachaId = gachaId;
		this.userId = userId;
		this.turnedDate = turnedDate;
		this.pickupId = pickupId;
	}
    
    // getterとsetter
	public int getGachaId() {
		return gachaId;
	}

	public void setGachaId(int gachaId) {
		this.gachaId = gachaId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getTurnedDate() {
		return turnedDate;
	}

	public void setTurnedDate(Timestamp turnedDate) {
		this.turnedDate = turnedDate;
	}

	public int getPickupId() {
		return pickupId;
	}

	public void setPickupId(int pickupId) {
		this.pickupId = pickupId;
	}
}
