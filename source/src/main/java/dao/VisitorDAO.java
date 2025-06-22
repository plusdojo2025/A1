package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;
import dto.VisitorDTO;


public class VisitorDAO extends DAO {
	
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
			// エイリアス（別名）は 
			// AS句 で指定すると見やすい
	        String sql = """
					SELECT 
						v.*, 
						p.prefecture_name 
					FROM 
						visitors AS v
					JOIN 
						prefectures AS p 
					ON 
						v.prefecture_id = p.prefecture_id
					WHERE 
						v.user_id = ? 
					ORDER BY 
						v.visitor_id ASC;
	        		""";
	        
	        // [ 予約 ] SQL文セット
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
		System.out.println("VisitorDAO: findByUserAndPrefecture() 開始 - user_id = " + user_id);
	
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
	     	               rs.getString("prefecture_name"),
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
    
    
    /**
     * ユーザーID と 訪問地ID を使用して、
     * 訪問地のレコードを1件返します。
     * 
     * @return [ VisitorDTO ] 訪問地の詳細
     */
    public VisitorDTO select(UserDTO loginUser, int visitor_id) {
    	// DB接続
    	super.access();
    	
    	// [ Entity ] 訪問地
    	VisitorDTO visitordto = new VisitorDTO();
    	
    	System.out.println("訪問地のレコードを1件取得します。");
    	
    	try {
    		// [ 準備 ] SQL文の記述
			String sql = """
					SELECT
						*
					FROM
						visitors
					WHERE
						user_id = ?
					AND
						visitor_id = ?;
					""";
			
			// [ 予約 ] SQL文セット
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// [ バインド ] ユーザーID
			pStmt.setString(1, 
					loginUser.getUser_id());
			// [ バインド ] 訪問地ID
			pStmt.setInt(2, visitor_id);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			System.out.println("問い合わせ（取得）を実行しました。");
			
			// 取得したレコードが0件なら
			if (rs.next() == false) {
				throw new SQLException("""
						取得したレコードの件数は0件でした。
						バインドの入力値を変えてみてください。
						""");
			}
			
			// 結果をコレクションにコピーする
			// 訪問地ID
			visitordto.setVisitor_id(
					visitor_id);
			// ユーザーID
			visitordto.setUser_id(
					loginUser.getUser_id());
			// お題
			visitordto.setTitle(rs.getString(
					"title"));
			// 同伴者
			visitordto.setComponion(rs.getString(
					"componion"));
			// 開始日
			visitordto.setStart_date(rs.getDate(
					"start_date"));
			// 終了日
			visitordto.setEnd_date(rs.getDate(
					"end_date"));
			// 都道府県ID
			visitordto.setPrefecture_id(
					rs.getInt("prefecture_id"));
			// 場所
			visitordto.setVisitor_place(rs.getString(
					"visitor_place"));
			// 感想
			visitordto.setThought(rs.getString(
					"thought"));
			// 感情ID
			visitordto.setEmotion_id(
					rs.getInt("emotion_id"));
			// 写真１
			visitordto.setPhoto1(
					rs.getString("photo1"));
			// 写真２
			visitordto.setPhoto2(
					rs.getString("photo2"));
			// 写真３
			visitordto.setPhoto3(
					rs.getString("photo3"));
			// 写真４
			visitordto.setPhoto4(
					rs.getString("photo4"));
			// 写真５
			visitordto.setPhoto5(
					rs.getString("photo5"));
			
			System.out.println("取得データをパックしました...");
		} catch (SQLException e) {
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println(e.getMessage());
			visitordto = null;
		} finally {
			// DB切断
			super.close();
			System.out.println();
		}
    	
    	// 訪問地の詳細を返す
    	return visitordto;
    }
    

    /**
     * ユーザーID と 訪問地ID を使用して、
     * 訪問地のレコードを1件更新します。
     * 
     * @return [ boolean ] 更新できたか
     */
    public boolean update(String user_id, VisitorDTO visitordto) {
    	// DB接続
    	super.access();
    	
    	// 更新が成功したか
    	boolean isSuccess = false;
    	// 成功の値
    	int success = 1;
    	
    	System.out.printf("%s ～ %s \n", 
    			visitordto.getStart_date(),
    			visitordto.getEnd_date());
    	System.out.println("都道府県ID: " + visitordto.getPrefecture_id());
    	System.out.println("以上の内容を更新します...");
    	
    	try {
    		// [ 準備 ] SQL文
    		String sql = """
    				UPDATE
    					visitors
    				SET
    					title = ?,
    					componion = ?,
    					start_date = ?,
    					end_date = ?,
    					prefecture_id = ?,
    					visitor_place = ?,
    					thought = ?,
    					emotion_id = ?,
    					photo1 = ?,
    					photo2 = ?,
    					photo3 = ?,
    					photo4 = ?,
    					photo5 = ?
					WHERE
						user_id = ?
					AND
						visitor_id = ?;
    				""";
			
    		// [ 予約 ] SQL文セット
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// [ バインド ] 更新する列
			pStmt.setString(1, 
					visitordto.getTitle());
			pStmt.setString(2, 
					visitordto.getComponion());
			pStmt.setDate(3, 
					visitordto.getStart_date());
			pStmt.setDate(4, 
					visitordto.getEnd_date());
			pStmt.setInt(5, 
					visitordto.getPrefecture_id());
			pStmt.setString(6, 
					visitordto.getVisitor_place());
			pStmt.setString(7, 
					visitordto.getThought());
			pStmt.setInt(8, 
					visitordto.getEmotion_id());
			pStmt.setString(9, 
					visitordto.getPhoto1());
			pStmt.setString(10, 
					visitordto.getPhoto2());
			pStmt.setString(11, 
					visitordto.getPhoto3());
			pStmt.setString(12, 
					visitordto.getPhoto4());
			pStmt.setString(13, 
					visitordto.getPhoto5());
			
			// [ バインド ] 対象のレコードを指定
			pStmt.setString(14, 
					user_id);
			pStmt.setInt(15, 
					visitordto.getVisitor_id());
			
			// [ 実行 ] SQLの更新
			isSuccess = pStmt.executeUpdate() == success;
			System.out.println("問い合わせ（更新）を実行しました...");
			
		} catch (SQLException e) {
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			// DB切断
			super.close();
			System.out.println();
		}
    	
    	// 更新できたか
    	return isSuccess;
	}
    
    /**
     * 
     * 訪問地ID を使用して、
     * 訪問地のレコード1件を削除します。
     * @param visitor_id
     * @return [ boolean ] 削除できたか
     */
    public boolean delete(int visitor_id) {
    	// DB接続
    	super.access();
    	
    	// 削除成功か
		boolean isSuccess = false;

		// 成功の値
		int success = 1;
		
		System.out.println("訪問地ID: " + visitor_id);
		System.out.println("以上の内容を削除します。");
		
		try {
			String sql = """
					DELETE
					FROM
						visitors
					WHERE
						visitor_id = ?;
					""";
			
    		// [ 予約 ] SQL文セット
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// [ バインド ] 対象のレコードを指定
			pStmt.setInt(1, 
					visitor_id);
			
			// [ 実行 ] SQLの更新
			isSuccess = pStmt.executeUpdate() == success;
			System.out.println("問い合わせ（削除）を実行しました...");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			// DB切断
			super.close();
			System.out.println();
		}
		
		// 削除できたか
    	return isSuccess;
	}

}