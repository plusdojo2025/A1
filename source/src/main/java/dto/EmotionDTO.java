package dto;

public class EmotionDTO {
	private int emotionId;   // 感情ID
    private String name;     // 感情名称
    private String emoji;    // 絵文字
	
    // コンストラクタ
    public EmotionDTO(int emotionId, String name, String emoji) {
		super();
		this.emotionId = emotionId;
		this.name = name;
		this.emoji = emoji;
	}
    
    // getterとsetter
	public int getEmotionId() {
		return emotionId;
	}

	public void setEmotionId(int emotionId) {
		this.emotionId = emotionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}  
}
