package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PickupDTO;

public class PickupDAO {
	
	// ユーザーIDに紐づく訪問地一覧を取得
		public List<PickupDTO> findByUser(String userId) {
		    Connection conn = null;
		    List<PickupDTO> pickupList = new ArrayList<>();

		    try {
		        // JDBCドライバを読み込む
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        // データベースに接続する
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
		        		+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
		                "root", "password");

		        // MySQL文を準備する（user_id のみで絞り込み）
		        String sql = "SELECT * FROM pickup WHERE user_id = ? ORDER BY pickup_id";
		        PreparedStatement pStmt = conn.prepareStatement(sql);
		        pStmt.setString(1, userId);  // ユーザーID

		        // MySQLを実行し、結果を取得
		        ResultSet rs = pStmt.executeQuery();

		        // 結果の取得とVisitorリスト（DTO）への格納
		        while (rs.next()) {
		        	PickupDTO pickup = new PickupDTO(
		                rs.getInt("pickup_id"),
		                rs.getString("user_id"),
		                rs.getInt("prefecture_id"),
		                rs.getString("place"),
		                rs.getString("remarks")
		            );
		        	pickupList.add(pickup);
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		        pickupList = null;
		    } finally {
		        // DB切断
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		                pickupList = null;
		            }
		        }
		    }

		    return pickupList;
		}

		
		
		// 
		public List<PickupDTO> findByUserAndPrefecture(String userId, String prefectureId) {
			Connection conn = null;
			List<PickupDTO> pickupList = new ArrayList<>();
		
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
						"root","password");
				
				// MySQL文を準備する
				String sql = "SELECT * FROM pickup WHERE user_id = ? AND prefecture_id = ? ORDER BY pickup_id";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, userId);
				pStmt.setString(2, prefectureId);
				
				
				// 実行
				ResultSet rs = pStmt.executeQuery();
				
				// 結果の取得とVisitorリストへの格納
	            while (rs.next()) {
	            	PickupDTO pickup = new PickupDTO(
	            			rs.getInt("pickup_id"),
			                rs.getString("user_id"),
			                rs.getInt("prefecture_id"),
			                rs.getString("place"),
			                rs.getString("remarks")
			            );
	                pickupList.add(pickup);
	            }
			 } catch (SQLException | ClassNotFoundException e) {
		            e.printStackTrace();
		            pickupList = null;
		        } finally {
		            // DB切断
		            if (conn != null) {
		                try {
		                    conn.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                    pickupList = null;
		                }
		            }
		        }

		        // リストを返す
		        return pickupList;
		}
		  
		// PickupDAOクラス内に追加するメソッド例

		public List<PickupDTO> search(PickupDTO dto) {
		    List<PickupDTO> pickupList = new ArrayList<>();
		    Connection conn = null;

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conn = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/a1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
		            "root", "password"
		        );

		        String sql = "SELECT * FROM pickup WHERE "
		                   + " (user_id = ? OR ? IS NULL OR ? = 0) "
		                   + " AND (prefecture_id = ? OR ? = 0) "
		                   + " AND (place LIKE CONCAT('%', ?, '%') OR ? IS NULL OR ? = '') "
		                   + " AND (remarks LIKE CONCAT('%', ?, '%') OR ? IS NULL OR ? = '') "
		                   + " ORDER BY pickup_id";

		        PreparedStatement pStmt = conn.prepareStatement(sql);
			     // SQL文を実行し、結果表を取得する
		        ResultSet rs = pStmt.executeQuery();
		        while (rs.next()) {
		            PickupDTO pickup = new PickupDTO(
		                rs.getInt("pickup_id"),
		                rs.getString("user_id"),
		                rs.getInt("prefecture_id"),
		                rs.getString("pickup_place"),
		                rs.getString("remarks")
		            );
		            pickupList.add(pickup);
		        }

			} catch (SQLException e) {
				e.printStackTrace();
				pickupList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				pickupList  = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						pickupList  = null;
					}
				}
			}

			// 結果を返す
			return pickupList ;
		}

		
	    // 登録処理
	    public boolean insert(PickupDTO dto) {
	        Connection conn = null;
	        PreparedStatement ps = null;

	        try {
	            // JDBCドライバのロード（MySQLの場合）
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // DBに接続
	            conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/a1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password"
	            );

	            // SQL文の準備
	            String sql = "INSERT INTO pickup (user_id, prefecture_id, pickup_place, remarks) VALUES (?, ?, ?, ?)";
	            ps = conn.prepareStatement(sql);
	            ps.setString(1, dto.getUser_id());
	            ps.setInt(2, dto.getPrefecture_id());
	            ps.setString(3, dto.getPickup_place());
	            ps.setString(4, dto.getRemarks());

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