package dto;

import java.sql.Timestamp;
import java.util.Date;

public class AllDTO {

    // UserDTO.java
	private String user_id;		// ユーザーID（主キー）
    private String password;    // パスワード
    private String nickname;    // ニックネーム

    // PrefectureDTO.java
	private int prefecture_id;   		// 都道府県ID（主キー）
    private String prefecture_name;     // 都道府県名

    // EmotionDTO.java
	private int emotion_id;   		// 感情ID
    private String emotion_name;	// 感情名称
    private String emoji;    		// 絵文字

    // BadgeDTO.java 
	private int badge_id;          // バッジID
    private String badge_name;          // バッジ名
    private String badge_image;    // バッジ画像

    // HoldDTO.java
	private int hold_id;                // 保持ID（主キー）
    private Timestamp date_acquisition; // 獲得日時

    // PickupDTO.java
    private int pickup_id;			// 候補地ID（主キー）
    private String pickup_place;   	// 場所
    private String remarks;			// 備考（コメント）

    // VisitorDTO.java
	private int visitor_id;        // 訪問地ID（主キー）
    private String title;          // タイトル
    private String componion;      // 同行者
    private Date start_date;       // 開始日
    private Date end_date;         // 終了日
    private String visitor_place;  // 場所
    private String thought;        // 感想
    private String photo1;         // 写真1
    private String photo2;         // 写真2
    private String photo3;         // 写真3
    private String photo4;         // 写真4
    private String photo5;         // 写真5

    // GachaDTO.java
	private int gacha_id;           // ガチャID
    private Timestamp turned_date;  // ガチャを回した日時

    // AreaDTO.java
	private int area_id;			// 地方ID
	private String area_name;		// 地方名称(10文字以内)
	
	
	// コンストラクタ
	public AllDTO(String user_id, String password, String nickname, int prefecture_id, String prefecture_name,
			int emotion_id, String emotion_name, String emoji, int badge_id, String badge_name, String badge_image,
			int hold_id, Timestamp date_acquisition, int pickup_id, String pickup_place, String remarks, int visitor_id,
			String title, String componion, Date start_date, Date end_date, String visitor_place, String thought,
			String photo1, String photo2, String photo3, String photo4, String photo5, int gacha_id,
			Timestamp turned_date, int area_id, String area_name) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.nickname = nickname;
		this.prefecture_id = prefecture_id;
		this.prefecture_name = prefecture_name;
		this.emotion_id = emotion_id;
		this.emotion_name = emotion_name;
		this.emoji = emoji;
		this.badge_id = badge_id;
		this.badge_name = badge_name;
		this.badge_image = badge_image;
		this.hold_id = hold_id;
		this.date_acquisition = date_acquisition;
		this.pickup_id = pickup_id;
		this.pickup_place = pickup_place;
		this.remarks = remarks;
		this.visitor_id = visitor_id;
		this.title = title;
		this.componion = componion;
		this.start_date = start_date;
		this.end_date = end_date;
		this.visitor_place = visitor_place;
		this.thought = thought;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.photo4 = photo4;
		this.photo5 = photo5;
		this.gacha_id = gacha_id;
		this.turned_date = turned_date;
		this.area_id = area_id;
		this.area_name = area_name;
	}
	
	// // getterとsetter
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


	public String getPrefecture_name() {
		return prefecture_name;
	}


	public void setPrefecture_name(String prefecture_name) {
		this.prefecture_name = prefecture_name;
	}


	public int getEmotion_id() {
		return emotion_id;
	}


	public void setEmotion_id(int emotion_id) {
		this.emotion_id = emotion_id;
	}


	public String getEmotion_name() {
		return emotion_name;
	}


	public void setEmotion_name(String emotion_name) {
		this.emotion_name = emotion_name;
	}


	public String getEmoji() {
		return emoji;
	}


	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}


	public int getBadge_id() {
		return badge_id;
	}


	public void setBadge_id(int badge_id) {
		this.badge_id = badge_id;
	}


	public String getBadge_name() {
		return badge_name;
	}


	public void setBadge_name(String badge_name) {
		this.badge_name = badge_name;
	}


	public String getBadge_image() {
		return badge_image;
	}


	public void setBadge_image(String badge_image) {
		this.badge_image = badge_image;
	}


	public int getHold_id() {
		return hold_id;
	}


	public void setHold_id(int hold_id) {
		this.hold_id = hold_id;
	}


	public Timestamp getDate_acquisition() {
		return date_acquisition;
	}


	public void setDate_acquisition(Timestamp date_acquisition) {
		this.date_acquisition = date_acquisition;
	}


	public int getPickup_id() {
		return pickup_id;
	}


	public void setPickup_id(int pickup_id) {
		this.pickup_id = pickup_id;
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


	public int getVisitor_id() {
		return visitor_id;
	}


	public void setVisitor_id(int visitor_id) {
		this.visitor_id = visitor_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getComponion() {
		return componion;
	}


	public void setComponion(String componion) {
		this.componion = componion;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public String getVisitor_place() {
		return visitor_place;
	}


	public void setVisitor_place(String visitor_place) {
		this.visitor_place = visitor_place;
	}


	public String getThought() {
		return thought;
	}


	public void setThought(String thought) {
		this.thought = thought;
	}


	public String getPhoto1() {
		return photo1;
	}


	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}


	public String getPhoto2() {
		return photo2;
	}


	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}


	public String getPhoto3() {
		return photo3;
	}


	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}


	public String getPhoto4() {
		return photo4;
	}


	public void setPhoto4(String photo4) {
		this.photo4 = photo4;
	}


	public String getPhoto5() {
		return photo5;
	}


	public void setPhoto5(String photo5) {
		this.photo5 = photo5;
	}


	public int getGacha_id() {
		return gacha_id;
	}


	public void setGacha_id(int gacha_id) {
		this.gacha_id = gacha_id;
	}


	public Timestamp getTurned_date() {
		return turned_date;
	}


	public void setTurned_date(Timestamp turned_date) {
		this.turned_date = turned_date;
	}


	public int getArea_id() {
		return area_id;
	}


	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}


	public String getArea_name() {
		return area_name;
	}


	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
}
