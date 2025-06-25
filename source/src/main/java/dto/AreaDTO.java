package dto;

public class AreaDTO {
	private int area_id;			// 地方ID
	private String area_name;		// 地方名称(10文字以内)
	private int prefecture_id;	// 	都道府県ID(外部キー)
	
	// コンストラクタ
	public AreaDTO(int area_id, String area_name, int prefecture_id) {
		super();
		this.area_id = area_id;
		this.area_name = area_name;
		this.prefecture_id = prefecture_id;
	}
	
	// [ コンストラクタ ] 空引数
	public AreaDTO() {
		this.area_id 		= -1;
		this.area_name 	= "";
		this.prefecture_id = -1;
	}
	
	// getterとsetter
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

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
}
