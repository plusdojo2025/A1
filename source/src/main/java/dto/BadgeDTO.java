package dto;

public class BadgeDTO {
	private int badge_id;          // バッジID
    private String badge_name;          // バッジ名
    private String badge_image;    // バッジ画像
	
    // コンストラクタ
    public BadgeDTO(int badge_id, String badge_name, String badge_image) {
		super();
		this.badge_id = badge_id;
		this.badge_name = badge_name;
		this.badge_image = badge_image;
	}
    
    // getterとsetter
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
}
