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
				// エイリアス（別名）は 
				// AS句 で指定すると見やすい
		        String sql = """
		        		SELECT
		        			pic.pickup_id, 
		        			pic.user_id, 
		        			pic.prefecture_id, 
		        			pref.prefecture_name,
		        			pic.pickup_place, 
		        			pic.remarks
	        			FROM 
	        				pickups AS pic
		        		JOIN 
		        			prefectures AS pref 
	        			ON 
	        				pic.prefecture_id = pref.prefecture_id
	        			WHERE 
	        				pic.user_id = ?;
		        		""";
		        
		        // [ 予約 ] SQL文セット
		        PreparedStatement pStmt = conn.prepareStatement(sql);
		        pStmt.setString(1, user_id);  // ユーザーID

		        // MySQLを実行し、結果を取得
		        ResultSet rs = pStmt.executeQuery();

		        // 結果の取得とVisitorリスト（DTO）への格納
		        while (rs.next()) {
		        	// [ Entity ] 候補地
		        	PickupDTO pickup = new PickupDTO(
						rs.getInt("pickup_id"),
						rs.getString("user_id"),
						rs.getInt("prefecture_id"),
						rs.getString("prefecture_name"),
						rs.getString("pickup_place"),
						rs.getString("remarks")
		        		);
		        	// 候補地リストに追加
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
		public List<PickupDTO> findByUserAndPrefecture(String user_id, String prefecture_id) {
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
				// エイリアス（別名）は 
				// AS句 で指定すると見やすい
				String sql = """
						SELECT 
							p.pickup_id, 
							p.user_id, 
							p.prefecture_id, 
							pf.prefecture_name, 
							p.pickup_place, 
							p.remarks
						FROM 
							pickups AS p 
						JOIN 
							prefectures AS pf 
						ON 
							p.prefecture_id = pf.prefecture_id 
						WHERE 
							p.user_id = ? 
						AND 
							p.prefecture_id = ? 
						ORDER BY 
							p.pickup_id ASC;
						""";
				
				// [ 予約 ] SQL文セット
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// [ 穴埋め ] ユーザーID
				pStmt.setString(1, user_id);
				// [ 穴埋め ] 候補地ID
				pStmt.setString(2, prefecture_id);
				
				// 実行
				ResultSet rs = pStmt.executeQuery();
				
				// 結果の取得とVisitorリストへの格納
	            while (rs.next()) {
	            	// [ Entity ] 候補地
	            	PickupDTO pickup = new PickupDTO(
	            		    rs.getInt("pickup_id"),
	            		    rs.getString("user_id"),
	            		    rs.getInt("prefecture_id"),
	            		    rs.getString("prefecture_name"),
	            		    rs.getString("pickup_place"),
	            		    rs.getString("remarks")
	            		);
	            	// 候補地リストに追加
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
		            "root", "password");

		        String sql = """
		        		ここにSQL文を入力してください...
		        		""";
		        
		        // [ 異常 ] 意図が汲み取れない為、両方を残しました。
		        // ご注意ください。ただ、テキストブロックで整理しただけです。
		        // また AS 句が追加されていますが、読みやすさが向上するだけです。
//		        sql = """
//		        		SELECT 
//		        			* 
//	        			FROM 
//	        				pickups 
//        				WHERE
//        					(user_id = ? 
//        						OR ? IS NULL 
//        						OR ? = 0)
//        				AND 
//        					(prefecture_id = ? 
//        					OR ? = 0)
//        				AND
//        				 	(pickup_place LIKE CONCAT('%', ?, '%') 
//    				 			OR ? IS NULL 
//    				 			OR ? = '')
//    				 	AND 
//    				 		(remarks LIKE CONCAT('%', ?, '%') 
//				 				OR ? IS NULL 
//				 				OR ? = '')
//				 		ORDER BY 
//				 			pickup_id ASC;
//		        		""";
		        
		        
//		        sql = """
//		        		SELECT 
//		        			p.pickup_id, 
//		        			p.user_id, 
//		        			p.prefecture_id, 
//		        			pf.prefecture_name, 
//		        			p.pickup_place, 
//		        			p.remarks 
//		        		FROM 
//		        			pickups AS p 
//		        		JOIN 
//		        			prefectures AS pf 
//	        			ON 
//	        				p.prefecture_id = pf.prefecture_id
//		        		WHERE 
//		        			(p.user_id LIKE ? 
//	        					OR ? IS NULL 
//	        					OR ? = '')
//		        		AND 
//		        			(p.prefecture_id = ? 
//		        				OR ? = 0)
//		        		AND 
//		        			(p.pickup_place LIKE ? 
//		        				OR ? IS NULL 
//		        				OR ? = '') 
//		        		AND 
//		        			(p.remarks LIKE ? 
//		        				OR ? IS NULL 
//		        				OR ? = '') 
//		        		ORDER BY 
//		        			p.pickup_id ASC;
//		        		""";
		        
		        // [ 予約 ] SQL文セット
		        PreparedStatement pStmt = conn.prepareStatement(sql);


//		        	pStmt.setString(1, "%" + dto.getUser_id()+ "%");
//		        	pStmt.setString(2, dto.getUser_id());
//		        	pStmt.setString(3, dto.getUser_id());
//
//		        	pStmt.setInt(4, dto.getPrefecture_id());
//		        	pStmt.setInt(5, dto.getPrefecture_id());
//		        	pStmt.setString(6, "%" + dto.getPickup_place() + "%");
//		        	pStmt.setString(7, dto.getPickup_place());
//		        	pStmt.setString(8, dto.getPickup_place());
//		        	pStmt.setString(9, "%" + dto.getRemarks() + "%");
//
//
//		        	pStmt.setString(1, dto.getUser_id());
//		        	pStmt.setString(2, dto.getUser_id());
//		        	pStmt.setString(3, dto.getUser_id());
//		        	pStmt.setInt(4, dto.getPrefecture_id());
//		        	pStmt.setInt(5, dto.getPrefecture_id());
//		        	pStmt.setString(6, dto.getPickup_place());
//		        	pStmt.setString(7, dto.getPickup_place());
//		        	pStmt.setString(8, dto.getPickup_place());
//		        	pStmt.setString(9, dto.getRemarks());
//
//		        	pStmt.setString(10, dto.getRemarks());
//		        	pStmt.setString(11, dto.getRemarks());
		        	
		        	// SQL文を実行し、結果表を取得する
		        	ResultSet rs = pStmt.executeQuery();
		        
		        		while (rs.next()) {
		        			// [ Entity ] 候補地
		        			PickupDTO pickup = new PickupDTO(
		        				rs.getInt("pickup_id"),
		        				rs.getString("user_id"),
		        				rs.getInt("prefecture_id"),
		        				rs.getString("prefecture_name"),
		        				rs.getString("pickup_place"),
		        				rs.getString("remarks")
		        			);
		        			// 候補地リストに追加
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
	            String sql = """
	            		INSERT INTO 
	            			pickups (
	            				user_id, 
	            				prefecture_id, 
	            				pickup_place, 
	            				remarks
            				) 
        				VALUES 
        					(?, ?, ?, ?);
	            		""";
	            
	            // [ 予約 ] SQL文セット
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