package dto;

import java.sql.Date;

public class VisitorDTO {
	private int visitorId;         // 訪問地ID（主キー）
    private String userId;         // ユーザーID（外部キー）
    private String title;          // タイトル
    private String componion;      // 同行者
    private Date startDate;        // 開始日
    private Date endDate;          // 終了日
    private int prefectureId;      // 都道府県ID（外部キー）
    private String place;          // 場所
    private String thought;        // 感想
    private int emotionId;         // 感情ID（外部キー）
    private String photo1;         // 写真1
    private String photo2;         // 写真2
    private String photo3;         // 写真3
    private String photo4;         // 写真4
    private String photo5;         // 写真5
	
	// コンストラクタ
	public VisitorDTO(int visitorId, String userId, String title, String componion, Date startDate, Date endDate,
			int prefectureId, String place, String thought, int emotionId, String photo1, String photo2, String photo3,
			String photo4, String photo5) {
		super();
		this.visitorId = visitorId;
		this.userId = userId;
		this.title = title;
		this.componion = componion;
		this.startDate = startDate;
		this.endDate = endDate;
		this.prefectureId = prefectureId;
		this.place = place;
		this.thought = thought;
		this.emotionId = emotionId;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.photo4 = photo4;
		this.photo5 = photo5;
	}
	
	// getterとsetter
	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPrefectureId() {
		return prefectureId;
	}

	public void setPrefectureId(int prefectureId) {
		this.prefectureId = prefectureId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getThought() {
		return thought;
	}

	public void setThought(String thought) {
		this.thought = thought;
	}

	public int getEmotionId() {
		return emotionId;
	}

	public void setEmotionId(int emotionId) {
		this.emotionId = emotionId;
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
}
