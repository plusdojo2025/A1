package dto;

public class PrefectureDTO {
	private int prefectureId;   // 都道府県ID（主キー）
    private String name;        // 都道府県名
	
    // コンストラクタ
    public PrefectureDTO(int prefectureId, String name) {
		super();
		this.prefectureId = prefectureId;
		this.name = name;
	}
    
    // getterとsetter
	public int getPrefectureId() {
		return prefectureId;
	}

	public void setPrefectureId(int prefectureId) {
		this.prefectureId = prefectureId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
