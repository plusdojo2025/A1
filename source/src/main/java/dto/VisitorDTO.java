package dto;

import java.sql.Date;

public class VisitorDTO {
	private int visitor_id;         // 訪問地ID（主キー）
    private String user_id;         // ユーザーID（外部キー）
    private String title;          // タイトル
    private String componion;      // 同行者
    private Date start_date;        // 開始日
    private Date end_date;          // 終了日
    private int prefecture_id;      // 都道府県ID（外部キー）
    private String prefecture_name;
    private String visitor_place;          // 場所
    private String thought;        // 感想
    private int emotion_id;         // 感情ID（外部キー）
    private String photo1;         // 写真1
    private String photo2;         // 写真2
    private String photo3;         // 写真3
    private String photo4;         // 写真4
    private String photo5;         // 写真5
	
    // ★ prefecture_name「なし」バージョン（従来の登録・検索で使う）
    public VisitorDTO(int visitor_id, String user_id, String title, String componion, Date start_date, Date end_date,
			int prefecture_id, String visitor_place, String thought, int emotion_id, String photo1, String photo2,
			String photo3, String photo4, String photo5) {
		super();
		this.visitor_id = visitor_id;
		this.user_id = user_id;
		this.title = title;
		this.componion = componion;
		this.start_date = start_date;
		this.end_date = end_date;
		this.prefecture_id = prefecture_id;
		this.visitor_place = visitor_place;
		this.thought = thought;
		this.emotion_id = emotion_id;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.photo4 = photo4;
		this.photo5 = photo5;
		this.prefecture_name = null;
	}
    
    // ★都道府県名ありのコンストラクタ（JOIN時に使用）
    public VisitorDTO(int visitor_id, String user_id, String title, String componion, Date start_date, Date end_date,
            int prefecture_id, String prefecture_name, String visitor_place, String thought, int emotion_id,
            String photo1, String photo2, String photo3, String photo4, String photo5) {
        this.visitor_id = visitor_id;
        this.user_id = user_id;
        this.title = title;
        this.componion = componion;
        this.start_date = start_date;
        this.end_date = end_date;
        this.prefecture_id = prefecture_id;
        this.prefecture_name = prefecture_name;
        this.visitor_place = visitor_place;
        this.thought = thought;
        this.emotion_id = emotion_id;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.photo5 = photo5;
    }

    // getterとsetter
	public int getVisitor_id() {
		return visitor_id;
	}

	public void setVisitor_id(int visitor_id) {
		this.visitor_id = visitor_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public int getEmotion_id() {
		return emotion_id;
	}

	public void setEmotion_id(int emotion_id) {
		this.emotion_id = emotion_id;
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
