package dto;

public class BadgeDTO {
	private int badge_id;
    private String badge_name;
    private String badge_image;
    //JavaBeans 規約によりこの書き方に
    private String badgeAcquiredDate;
    
    //コンストラクタ
    public BadgeDTO() {
    	
    }
	
    //バッジ情報を保持するDTO
    public BadgeDTO(int badge_id, String badge_name, String badge_image, String badgeAcquiredDate) {
        this.badge_id = badge_id;
        this.badge_name = badge_name;
        this.badge_image = badge_image;
        this.badgeAcquiredDate = badgeAcquiredDate;
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
    
    public String getBadgeAcquiredDate() {
    	return badgeAcquiredDate;
   }
    
    public void setBadgeAcquiredDate(String badgeAcquiredDate) {
    	this.badgeAcquiredDate = badgeAcquiredDate;
    }
}
