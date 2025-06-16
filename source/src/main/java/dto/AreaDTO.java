package dto;

public class AreaDTO {
	private int areaId;			// 地方ID
	private String name;		// 地方名称(10文字以内)
	private int prefectureId;	// 	都道府県ID(外部キー)
	
	// コンストラクタ
	public AreaDTO(int areaId, String name, int prefectureId) {
		super();
		this.areaId = areaId;
		this.name = name;
		this.prefectureId = prefectureId;
	}
	
	// getterとsetter
	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrefectureId() {
		return prefectureId;
	}

	public void setPrefectureId(int prefectureId) {
		this.prefectureId = prefectureId;
	}
}
