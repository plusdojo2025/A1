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
		public List<PickupDTO> findByUser(String user_id) {
		    Connection conn = null;
		    List<PickupDTO> pickupList = new ArrayList<>();
		    System.out.println("PickupDAO: findByUser() 開始 - user_id = " + user_id);
		    
		    try {
		        // JDBCドライバを読み込む
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        // データベースに接続する
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
		        		+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
		                "root", "password");

		        // MySQL文を準備する（user_id のみで絞り込み）
		        String sql = "SELECT v.pickup_id, v.user_id, v.prefecture_id, v.pickup_place, v.remarks, p.prefecture_name "
		        		+ "FROM pickups v " +
		        		"JOIN prefectures p ON v.prefecture_id = p.prefecture_id " +
	                    "WHERE v.user_id = ?";
		        		
		        PreparedStatement pStmt = conn.prepareStatement(sql);
		        pStmt.setString(1, user_id);  // ユーザーID

		        // MySQLを実行し、結果を取得
		        ResultSet rs = pStmt.executeQuery();

		        // 結果の取得とVisitorリスト（DTO）への格納
		        while (rs.next()) {
		        	PickupDTO pickup = new PickupDTO(
<<<<<<< Updated upstream
		        		    rs.getInt("pickup_id"),
		        		    rs.getString("user_id"),
		        		    rs.getInt("prefecture_id"),
		        		    rs.getString("prefecture_name"),
		        		    rs.getString("pickup_place"),
		        		    rs.getString("remarks")
		        		);
=======
		                rs.getInt("pickup_id"),
		                rs.getString("user_id"),
		                rs.getInt("prefecture_id"),
		                rs.getString("pickup_place"),
		                rs.getString("remarks")
		            );
>>>>>>> Stashed changes
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

		
		
		// ユーザーID＋都道府県IDでの絞り込み
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		public List<PickupDTO> findByUserAndPrefecture(String user_id, String prefecture_id) {
=======
=======
>>>>>>> Stashed changes
		public List<PickupDTO> findByUserAndPrefecture(String userId, String prefectureId) {
>>>>>>> Stashed changes
			Connection conn = null;
			List<PickupDTO> pickupList = new ArrayList<>();
			System.out.println("PickupDAO: findByUserAndPrefecture() 開始 - user_id = " + user_id);
		
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
						"root","password");
				
				// MySQL文を準備する
<<<<<<< Updated upstream
<<<<<<< Updated upstream
				String sql = "SELECT p.pickup_id, p.user_id, p.prefecture_id, pf.prefecture_name, " +
                        "p.pickup_place, p.remarks " +
                        "FROM pickups p JOIN prefectures pf ON p.prefecture_id = pf.prefecture_id " +
                        "WHERE p.user_id = ? AND p.prefecture_id = ? ORDER BY p.pickup_id";
				
=======
				String sql = "SELECT * FROM pickups WHERE user_id = ? AND prefecture_id = ? ORDER BY pickup_id";
>>>>>>> Stashed changes
=======
				String sql = "SELECT * FROM pickups WHERE user_id = ? AND prefecture_id = ? ORDER BY pickup_id";
>>>>>>> Stashed changes
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user_id);
				pStmt.setString(2, prefecture_id);
				
				
				// 実行
				ResultSet rs = pStmt.executeQuery();
				
				// 結果の取得とVisitorリストへの格納
	            while (rs.next()) {
	            	PickupDTO pickup = new PickupDTO(
<<<<<<< Updated upstream
	            		    rs.getInt("pickup_id"),
	            		    rs.getString("user_id"),
	            		    rs.getInt("prefecture_id"),
	            		    rs.getString("prefecture_name"),
	            		    rs.getString("pickup_place"),
	            		    rs.getString("remarks")
	            		);
=======
	            			rs.getInt("pickup_id"),
			                rs.getString("user_id"),
			                rs.getInt("prefecture_id"),
			                rs.getString("pickup_place"),
			                rs.getString("remarks")
			            );
>>>>>>> Stashed changes
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
		  
		// 検索用メソッド
		public List<PickupDTO> search(PickupDTO dto) {
		    List<PickupDTO> pickupList = new ArrayList<>();
		    Connection conn = null;

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conn = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/a1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
<<<<<<< Updated upstream
		            "root", "password");
=======
		            "root", "password"
		        );

		        String sql = "SELECT * FROM pickups WHERE "
		                   + " (user_id = ? OR ? IS NULL OR ? = 0) "
		                   + " AND (prefecture_id = ? OR ? = 0) "
		                   + " AND (pickup_place LIKE CONCAT('%', ?, '%') OR ? IS NULL OR ? = '') "
		                   + " AND (remarks LIKE CONCAT('%', ?, '%') OR ? IS NULL OR ? = '') "
		                   + " ORDER BY pickup_id";
>>>>>>> Stashed changes

		        String sql = "SELECT p.pickup_id, p.user_id, p.prefecture_id, pf.prefecture_name, " +
	                     "p.pickup_place, p.remarks " +
	                     "FROM pickups p " +
	                     "JOIN prefectures pf ON p.prefecture_id = pf.prefecture_id " +
	                     "WHERE (p.user_id LIKE ? OR ? IS NULL OR ? = '') " +
	                     "AND (p.prefecture_id = ? OR ? = 0) " +
	                     "AND (p.pickup_place LIKE ? OR ? IS NULL OR ? = '') " +
	                     "AND (p.remarks LIKE ? OR ? IS NULL OR ? = '') " +
	                     "ORDER BY p.pickup_id";
		        
		        PreparedStatement pStmt = conn.prepareStatement(sql);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		        	pStmt.setString(1, "%" + dto.getUser_id()+ "%");
		        	pStmt.setString(2, dto.getUser_id());
		        	pStmt.setString(3, dto.getUser_id());

		        	pStmt.setInt(4, dto.getPrefecture_id());
		        	pStmt.setInt(5, dto.getPrefecture_id());
		        	pStmt.setString(6, "%" + dto.getPickup_place() + "%");
		        	pStmt.setString(7, dto.getPickup_place());
		        	pStmt.setString(8, dto.getPickup_place());
		        	pStmt.setString(9, "%" + dto.getRemarks() + "%");
=======
=======
>>>>>>> Stashed changes
		        	pStmt.setString(1, dto.getUser_id());
		        	pStmt.setString(2, dto.getUser_id());
		        	pStmt.setString(3, dto.getUser_id());
		        	pStmt.setInt(4, dto.getPrefecture_id());
		        	pStmt.setInt(5, dto.getPrefecture_id());
		        	pStmt.setString(6, dto.getPickup_place());
		        	pStmt.setString(7, dto.getPickup_place());
		        	pStmt.setString(8, dto.getPickup_place());
		        	pStmt.setString(9, dto.getRemarks());
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
		        	pStmt.setString(10, dto.getRemarks());
		        	pStmt.setString(11, dto.getRemarks());
		        	
		        	// SQL文を実行し、結果表を取得する
		        	ResultSet rs = pStmt.executeQuery();
		        
		        		while (rs.next()) {
		        			PickupDTO pickup = new PickupDTO(
		        				rs.getInt("pickup_id"),
		        				rs.getString("user_id"),
		        				rs.getInt("prefecture_id"),
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		        				rs.getString("prefecture_name"),
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
	            String sql = "INSERT INTO pickups "
	            		+ "(user_id, prefecture_id, pickup_place, remarks) VALUES (?, ?, ?, ?)";
=======
	            String sql = "INSERT INTO pickups (user_id, prefecture_id, pickup_place, remarks) VALUES (?, ?, ?, ?)";
>>>>>>> Stashed changes
=======
	            String sql = "INSERT INTO pickups (user_id, prefecture_id, pickup_place, remarks) VALUES (?, ?, ?, ?)";
>>>>>>> Stashed changes
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