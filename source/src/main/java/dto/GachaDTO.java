package dto;

import java.sql.Timestamp;

public class GachaDTO {
	private int gacha_id;           // ガチャID
    private String user_id;         // ユーザーID（usersテーブルと関連）
    private Timestamp turned_date;  // ガチャを回した日時
    private int pickup_id;          // 選ばれた候補地ID（pickupsテーブルと関連）
	
    // コンストラクタ
    public GachaDTO(int gacha_id, String user_id, Timestamp turned_date, int pickup_id) {
		super();
		this.gacha_id = gacha_id;
		this.user_id = user_id;
		this.turned_date = turned_date;
		this.pickup_id = pickup_id;
	}
    
    // getterとsetter
	public int getGacha_id() {
		return gacha_id;
	}

	public void setGacha_id(int gacha_id) {
		this.gacha_id = gacha_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Timestamp getTurned_date() {
		return turned_date;
	}

	public void setTurned_date(Timestamp turned_date) {
		this.turned_date = turned_date;
	}

	public int getPickup_id() {
		return pickup_id;
	}

	public void setPickup_id(int pickup_id) {
		this.pickup_id = pickup_id;
	}
    
    
    
    
}
