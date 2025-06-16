package dto;

public class PickupDTO {
	    private int pickupId;		// 候補地ID（主キー）
	    private int userId;  		// ユーザーID（外部キー）
	    private int prefectureId; 	// 都道府県（外部キー）
	    private String place;   	// 場所
	    private String remarks;		// 備考（コメント）
		
	    // コンストラクタ
	    public PickupDTO(int pickupId, int userId, int prefectureId, String place, String remarks) {
			super();
			this.pickupId = pickupId;
			this.userId = userId;
			this.prefectureId = prefectureId;
			this.place = place;
			this.remarks = remarks;
		}
	    
	    // getterとsetter
		public int getPickupId() {
			return pickupId;
		}

		public void setPickupId(int pickupId) {
			this.pickupId = pickupId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
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

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}  
}
