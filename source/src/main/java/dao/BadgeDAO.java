package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BadgeDTO;

public class BadgeDAO {
	// ユーザーが持っているバッジを全バッジと照合して取得
    public List<BadgeDTO> getAllBadgesWithUserStatus(String user_id) {
        List<BadgeDTO> badgeList = new ArrayList<>();
        Connection conn = null;

        try {
        	//JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        //データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
	        		+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");

            //全バッジと対応する取得日をIDに応じてLEFT JOINで取得
            String sql = """
                SELECT 
				    b.badge_id, 
				    b.badge_name, 
				    b.badge_image,
				    h.date_acquisition 
				FROM 
				    badges AS b 
				LEFT JOIN 
				    holds AS h 
				ON 
				    b.badge_id = h.badge_id 
				    AND h.user_id = ?
				WHERE
            		h.date_acquisition is not null
				ORDER BY 
				    b.badge_id ASC;
            """;

            //SQL文を準備
            PreparedStatement ps = conn.prepareStatement(sql);
            //ログインユーザーのIDを指定
            ps.setString(1, user_id);

            // 実行
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	System.out.println("バッジID: " + rs.getInt("badge_id"));
                System.out.println("バッジ名: " + rs.getString("badge_name"));
                System.out.println("画像: " + rs.getString("badge_image"));
                System.out.println("取得日: " + rs.getString("date_acquisition"));
                
                // 例：JDBCで取得した日付を整形してDTOにセット
                String dateAcquisition = rs.getString("date_acquisition");
                if (dateAcquisition != null && dateAcquisition.contains(" ")) {
                	 // 「2025-06-24 00:00:00」→「2025-06-24」
                    dateAcquisition = dateAcquisition.split(" ")[0];
                }

            	//DTO
                BadgeDTO badge = new BadgeDTO(
                    rs.getInt("badge_id"),
                    rs.getString("badge_name"),
                    rs.getString("badge_image"),
                    rs.getString("date_acquisition")
                );
                badgeList.add(badge);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            badgeList = null;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                badgeList = null;
            }
        }

        return badgeList;
        
    }
}

