package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.VisitorDTO;

public class VisitorDAO {
	
	// ユーザーIDに紐づく訪問地一覧を取得
	public List<VisitorDTO> findByUser(String userId) {
	    Connection conn = null;
	    List<VisitorDTO> visitorList = new ArrayList<>();

	    try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");

	        // MySQL文を準備する（user_id のみで絞り込み）
	        String sql = "SELECT * FROM visitor WHERE user_id = ? ORDER BY visitor_id";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, userId);  // ユーザーID

	        // MySQLを実行し、結果を取得
	        ResultSet rs = pStmt.executeQuery();

	        // 結果の取得とVisitorリスト（DTO）への格納
	        while (rs.next()) {
	            VisitorDTO visitor = new VisitorDTO(
	                rs.getInt("visitor_id"),
	                rs.getInt("user_id"),
	                rs.getString("title"),
	                rs.getString("componion"),
	                rs.getDate("start_date"),
	                rs.getDate("end_date"),
	                rs.getInt("prefecture_id"),
	                rs.getString("place"),
	                rs.getString("thought"),
	                rs.getInt("emotion_id"),
	                rs.getString("photo1"),
	                rs.getString("photo2"),
	                rs.getString("photo3"),
	                rs.getString("photo4"),
	                rs.getString("photo5")
	            );
	            visitorList.add(visitor);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        visitorList = null;
	    } finally {
	        // DB切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                visitorList = null;
	            }
	        }
	    }

	    return visitorList;
	}

	
	
	// 
	public List<VisitorDTO> findByUserAndPrefecture(String userId, String prefectureId) {
		Connection conn = null;
		List<VisitorDTO> visitorList = new ArrayList<>();
	
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root","password");
			
			// MySQL文を準備する
			String sql = "SELECT * FROM visitor WHERE user_id = ? AND prefecture_id = ? ORDER BY visitor_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			pStmt.setString(2, prefectureId);
			
			
			// 実行
			ResultSet rs = pStmt.executeQuery();
			
			// 結果の取得とVisitorリストへの格納
            while (rs.next()) {
            	VisitorDTO visitor = new VisitorDTO(
                    rs.getInt("visitor_id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("componion"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getInt("prefecture_id"),
                    rs.getString("place"),
                    rs.getString("thought"),
                    rs.getInt("emotion_id"),
                    rs.getString("photo1"),
                    rs.getString("photo2"),
                    rs.getString("photo3"),
                    rs.getString("photo4"),
                    rs.getString("photo5")
                );
                visitorList.add(visitor);
            }
		 } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            visitorList = null;
	        } finally {
	            // DB切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    visitorList = null;
	                }
	            }
	        }

	        // リストを返す
	        return visitorList;
	    }
	}