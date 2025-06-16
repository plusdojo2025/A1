package dto;

import java.sql.Timestamp;
import java.util.Date;

public class AllDTO {

    // UserDTO.java
	private String userId;      // ユーザーID（主キー）
    private String password;    // パスワード
    private String name;        // ユーザー名
    private int live;           // 居住地の都道府県ID（外部キー）

    // PrefectureDTO.java
	private int prefectureId;   // 都道府県ID（主キー）
    private String name;        // 都道府県名

    // EmotionDTO.java
	private int emotionId;   // 感情ID
    private String name;     // 感情名称
    private String emoji;    // 絵文字

    // BadgeDTO.java 
	private int badgeId;          // バッジID
    private String name;          // バッジ名
    private String badgeImage;    // バッジ画像

    // HoldDTO.java
	private int holdId;                // 保持ID（主キー）
    private int badgeId;              // バッジID（badgesテーブルと関連）
    private String userId;            // ユーザーID（usersテーブルと関連）
    private Timestamp dateAcquisition; // 獲得日時

    // PickupDTO.java
    private int pickupId;		// 候補地ID（主キー）
    private int userId;  		// ユーザーID（外部キー）
    private int prefectureId; 	// 都道府県（外部キー）
    private String place;   	// 場所
    private String remarks;		// 備考（コメント）

    // VisitorDTO.java
	private int visitorId;         // 訪問地ID（主キー）
    private String userId;         // ユーザーID（外部キー）
    private String title;          // タイトル
    private String componion;      // 同行者
    private Date startDate;        // 開始日
    private Date endDate;          // 終了日
    private int prefectureId;      // 都道府県ID（外部キー）
    private String place;          // 場所
    private String thought;        // 感想
    private int emotionId;         // 感情ID（外部キー）
    private String photo1;         // 写真1
    private String photo2;         // 写真2
    private String photo3;         // 写真3
    private String photo4;         // 写真4
    private String photo5;         // 写真5

    // GachaDTO.java
	private int gachaId;           // ガチャID
    private String userId;         // ユーザーID（usersテーブルと関連）
    private Timestamp turnedDate;  // ガチャを回した日時
    private int pickupId;          // 選ばれた候補地ID（pickupsテーブルと関連）

    // AreaDTO.java
	private int areaId;			// 地方ID
	private String name;		// 地方名称(10文字以内)
	private int prefectureId;	// 	都道府県ID(外部キー)
}
