package dto;

public class BadgeDTO {
	private int badgeId;          // バッジID
    private String name;          // バッジ名
    private String badgeImage;    // バッジ画像
	
    // コンストラクタ
    public BadgeDTO(int badgeId, String name, String badgeImage) {
		super();
		this.badgeId = badgeId;
		this.name = name;
		this.badgeImage = badgeImage;
	}
    
    // getterとsetter
	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBadgeImage() {
		return badgeImage;
	}

	public void setBadgeImage(String badgeImage) {
		this.badgeImage = badgeImage;
	} 
}
