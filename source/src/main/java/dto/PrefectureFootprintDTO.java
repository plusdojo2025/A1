package dto;

public class PrefectureFootprintDTO {
	private int prefecture_id;   			// 都道府県ID（主キー）
	private String area_name;				// 地方名
    private String prefecture_name;		// 都道府県名
	private int prefecture_footprint;		// 訪れた回数
	private int footprint_level;				// 訪れた水準
	private String footprint_color;		// 対応する色クラス名
	
	// コンストラクタ
	public PrefectureFootprintDTO() {
		this.prefecture_id 	 = -1;
		this.area_name 		 = "";
		this.prefecture_name = "";
		this.prefecture_footprint   = -1;
	}

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getPrefecture_name() {
		return prefecture_name;
	}

	public void setPrefecture_name(String prefecture_name) {
		this.prefecture_name = prefecture_name;
	}

	public int getPrefecture_footprint() {
		return prefecture_footprint;
	}

	public void setPrefecture_footprint(int prefecture_footprint) {
		this.prefecture_footprint = prefecture_footprint;
	}

	public int getFootprint_level() {
		return footprint_level;
	}

	public void setFootprint_level() {
		int footprint_level = 0;
		
		if (this.prefecture_footprint >= 10) {
			// 10 以上
			footprint_level = 10;
		}
		else if (this.prefecture_footprint >= 5) {
			// 5 以上
			footprint_level = 5;
		}
		else if (this.prefecture_footprint >= 3) {
			// 3 以上
			footprint_level = 3;
		}
		else if (this.prefecture_footprint >= 1) {
			// 1 以上
			footprint_level = 1;
		}
		else {
			// 0以下
			footprint_level = 0;
		}
		
		this.footprint_level = footprint_level;
	}

	public String getFootprint_color() {
		return footprint_color;
	}

	public void setFootprint_color(String footprint_color) {
		this.footprint_color = footprint_color;
	}
	
}
