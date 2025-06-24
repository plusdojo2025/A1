package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAO extends DAO{
	
	// ログイン認証を行う
	public boolean isAuth(String userId, String password) {
		//ドライバの読み込みおよびデータベースの接続
		super.access();
		
		boolean loginResult = false;

		try {
			// SELECT文を準備する
			String sql = """
				SELECT 
					count(*) AS hit
				FROM 
					users 
				WHERE 
					user_id=? 
				AND
					password=?;
					""";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// ?に値をセット
			pStmt.setString(1, userId);
			pStmt.setString(2, password);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			rs.next();
			if (rs.getInt("hit") == 1) {
				loginResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			super.close();
		}
		// 結果を返す
		return loginResult;
	}
	// ログインした後ユーザーの情報を取得する
	public UserDTO getLoginUser(String userId) {
		//ドライバの読み込みおよびデータベースの接続
		super.access();
		UserDTO loginUser = new UserDTO();//インスタンス化 
		try {
			// SQL文を準備する
	        String sql = """
	            SELECT  
	             nickname,
	              prefecture_id
	            FROM 
	        		users
	            WHERE 
	        		user_id = ? 
	              """;
	        
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        // ?に値をセット
			pStmt.setString(1, userId);
	        
			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			// ユーザーが見つかればDTOに情報を返す
			if (rs.next()) {
				loginUser.setUser_id(userId); // ユーザーID
	            loginUser.setNickname(rs.getString(
	            		"nickname")); //ニックネーム 
	            loginUser.setPrefecture_id(rs.getInt(
	            		"prefecture_id"));// 都道府県ID
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        super.close(); // DB接続を切断
	    }
		// ユーザー情報を返す
	    return loginUser; // ヒットしなければ null
	
}
	//引数infoで指定されたユーザー情報を登録する。（新規登録画面に使用）
	public boolean insert(UserDTO info) {
		
		//ドライバの読み込みおよびデータベースの接続
		super.access();
		
		boolean result = false;
		
		try {

			// SQL文を準備する
			String sql = "INSERT INTO users VALUES (?, ?, ?, ?) ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//ユーザーIDに対する処理
			pStmt.setString(1, info.getUser_id());
			
			//パスワードに対する処理
			pStmt.setString(2,info.getPassword());

			//ニックネームに対する処理
			pStmt.setString(3,info.getNickname());
			
			//ユーザーの居住地に対する処理
			pStmt.setInt(4, info.getPrefecture_id());
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			super.close();
		}
	return result;
	}
	
	//引数infoで指定されたユーザー情報を更新する。（設定画面に使用）
	public boolean update(UserDTO info) {
		
		//ドライバの読み込みおよびデータベースの接続
		super.access();
		
		boolean result = false;
		
		try {
			// SQL文を準備する
			String sql = "UPDATE users SET  nickname = ?, password = ?, prefecture_id = ? WHERE user_id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//ニックネームに対する処理
			if (info.getNickname() != null) {
				pStmt.setString(1,info.getNickname());
			} else {
				pStmt.setString(1, "");
			}
			
			// パスワードに対する処理
			if (info.getPassword() != null) {
				pStmt.setString(2, info.getPassword());
			} else {
				pStmt.setString(2, "");
			}
			
			//都道府県IDに対する処理
			pStmt.setInt(3,info.getPrefecture_id());

			//ユーザーIDに対する処理
			if (info.getUser_id() != null) {
				pStmt.setString(4,info.getUser_id());
			} else {
				pStmt.setString(4, "");
			}
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			super.close();
		}
	return result;
	}
	
}
