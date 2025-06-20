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
	public List<VisitorDTO> findByUser(String user_id) {
	    Connection conn = null;
	    List<VisitorDTO> visitorList = new ArrayList<>();
	    System.out.println("VisitorDAO: findByUser() 開始 - userId = " + user_id);

	    try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");

	        // MySQL文を準備する（user_id のみで絞り込み）
	        String sql =  "SELECT v.*, p.prefecture_name FROM visitors v " +
                    "JOIN prefectures p ON v.prefecture_id = p.prefecture_id " +
                    "WHERE v.user_id = ? ORDER BY v.visitor_id";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, user_id);  // ユーザーID

	        // MySQLを実行し、結果を取得
	        ResultSet rs = pStmt.executeQuery();

	        // 結果の取得とVisitorリスト（DTO）への格納
	        while (rs.next()) {
	            VisitorDTO visitor = new VisitorDTO(
	                rs.getInt("visitor_id"),
	                rs.getString("user_id"),
	                rs.getString("title"),
	                rs.getString("componion"),
	                rs.getDate("start_date"),
	                rs.getDate("end_date"),
	                rs.getInt("prefecture_id"),
	                rs.getString("visitor_place"),
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

	
	
	// ユーザーIDと都道府県ID
	public List<VisitorDTO> findByUserAndPrefecture(String user_id, String prefecture_id) {
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
			String sql = "SELECT v.*, p.prefecture_name FROM visitors v " +
                    "JOIN prefectures p ON v.prefecture_id = p.prefecture_id " +
                    "WHERE v.user_id = ? AND v.prefecture_id = ? ORDER BY v.visitor_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, prefecture_id);
			
			// 実行
			ResultSet rs = pStmt.executeQuery();
			
			// 結果の取得とVisitorリストへの格納
            while (rs.next()) {
            	VisitorDTO visitor = new VisitorDTO(
                    rs.getInt("visitor_id"),
                    rs.getString("user_id"),
                    rs.getString("title"),
                    rs.getString("componion"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getInt("prefecture_id"),
                    rs.getString("visitor_place"),
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
		
	//検索用メソッド
	public List<VisitorDTO> search(VisitorDTO dto) {
	    Connection conn = null;
	    List<VisitorDTO> visitorList = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/a1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password");
	        
	        //SQLを準備
	        String sql = "SELECT v.*, p.prefecture_name FROM visitors v " +
                    "JOIN prefectures p ON v.prefecture_id = p.prefecture_id " +
                    "WHERE v.user_id LIKE ? AND v.title LIKE ? AND v.componion LIKE ? " +
                    "AND (v.start_date >= ? OR ? IS NULL) AND (v.end_date <= ? OR ? IS NULL) " +
                    "AND (v.prefecture_id = ? OR ? = 0) AND v.visitor_place LIKE ? AND v.thought LIKE ? " +
                    "AND (v.emotion_id = ? OR ? = 0) ORDER BY v.visitor_id";

	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        	pStmt.setString(1, "%" + dto.getUser_id() + "%");
	        	pStmt.setString(2, "%" + dto.getTitle() + "%");
	        	pStmt.setString(3, "%" + dto.getComponion() + "%");
	        	pStmt.setDate(4, dto.getStart_date());
	        	pStmt.setDate(5, dto.getStart_date());
	        	pStmt.setDate(6, dto.getEnd_date());
	        	pStmt.setDate(7, dto.getEnd_date());
	        	pStmt.setInt(8, dto.getPrefecture_id());
	        	pStmt.setInt(9, dto.getPrefecture_id());
	        	pStmt.setString(10, "%" + dto.getVisitor_place() + "%");
	        	pStmt.setString(11, "%" + dto.getThought() + "%");
	        	pStmt.setInt(12, dto.getEmotion_id());
	        	pStmt.setInt(13, dto.getEmotion_id());

            	// SQL文を実行し、結果表を取得する
	     		ResultSet rs = pStmt.executeQuery();
	     	        
	     			while (rs.next()) {
	     				VisitorDTO visitor = new VisitorDTO(
	     	                rs.getInt("visitor_id"),
	     	                rs.getString("user_id"),
	     	                rs.getString("title"),
	     	                rs.getString("componion"),
	     	                rs.getDate("start_date"),
	     	                rs.getDate("end_date"),
	     	                rs.getInt("prefecture_id"),
	     	                rs.getString("visitor_place"),
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
		} catch (SQLException e) {
			e.printStackTrace();
			visitorList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			visitorList  = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					visitorList  = null;
				}
			}
		}

		// 結果を返す
		return visitorList ;
	}

	
    // 登録処理
    public boolean insert(VisitorDTO dto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // JDBCドライバのロード（MySQLの場合）
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DBに接続
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root","password");

            // SQL文の準備
            String sql = "INSERT INTO visitors "
                    + "(user_id, title, componion, start_date, end_date, prefecture_id, visitor_place, thought, emotion_id, photo1, photo2, photo3, photo4, photo5) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getUser_id());
            ps.setString(2, dto.getTitle());
            ps.setString(3, dto.getComponion());
            ps.setDate(4, dto.getStart_date());
            ps.setDate(5, dto.getEnd_date());
            ps.setInt(6, dto.getPrefecture_id());
            ps.setString(7, dto.getVisitor_place());
            ps.setString(8, dto.getThought());
            ps.setInt(9, dto.getEmotion_id());
            ps.setString(10, dto.getPhoto1());
            ps.setString(11, dto.getPhoto2());
            ps.setString(12, dto.getPhoto3());
            ps.setString(13, dto.getPhoto4());
            ps.setString(14, dto.getPhoto5());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // リソースのクローズ
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}