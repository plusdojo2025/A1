package dto;

public class EmotionDTO {
	private int emotion_id;   		// 感情ID
    private String emotion_name;    // 感情名称
    private String emoji;    		// 絵文字
	
    // コンストラクタ
    public EmotionDTO(int emotion_id, String emotion_name, String emoji) {
		super();
		this.emotion_id = emotion_id;
		this.emotion_name = emotion_name;
		this.emoji = emoji;
	}
    
    // getterとsetter
	public int getEmotion_id() {
		return emotion_id;
	}

	public void setEmotion_id(int emotion_id) {
		this.emotion_id = emotion_id;
	}

	public String getEmotion_name() {
		return emotion_name;
	}

	public void setEmotion_name(String emotion_name) {
		this.emotion_name = emotion_name;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}
}
