package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.UserDTO;

public class UserDAO extends DAO{
	
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
			String sql = "UPDATE users SET password = ?, nickname = ?, live = ? WHERE user_id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//パスワードに対する処理
			pStmt.setString(1,info.getPassword());

			//ニックネームに対する処理
			pStmt.setString(2,info.getNickname());
			
			//ユーザーの居住地に対する処理
			pStmt.setInt(3, info.getPrefecture_id());
			
			//ユーザーIDに対する処理
			pStmt.setString(4, info.getUser_id());
			
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
