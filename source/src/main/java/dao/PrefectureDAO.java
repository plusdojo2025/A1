package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.PrefectureDTO;

public class PrefectureDAO extends DAO {
	
	/**
	 * 47都道府県を取得します。
	 * 
	 * @return [ ArrayList ] 都道府県のリスト
	 */
	public ArrayList<PrefectureDTO> selectAll() {
		// DB接続開始
		super.access();
		
		// 都道府県のリスト
		ArrayList<PrefectureDTO> prefectureList = new ArrayList<PrefectureDTO>();
		System.out.println("47都道府県のレコードを取得します。");

		try {
			String sql = """
					SELECT
						*
					FROM
						prefectures
					ORDER BY
						prefecture_id ASC;
					""";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			System.out.println("問い合わせ（取得）を実行しました。");
			
			
			// 結果をコレクションにコピーする
			while (rs.next()) {
				// [ Entity ] 都道府県 
				PrefectureDTO pr = new PrefectureDTO();
				// 都道府県 ID
				pr.setPrefecture_id(rs.getInt(
						"prefecture_id"));
				// 都道府県 名
				pr.setPrefecture_name(rs.getString(
						"prefecture_name"));
				// 都道府県のリストに追加
				prefectureList.add(pr);
			}
			System.out.println("取得データをパックしました...");
		}
		catch (SQLException e) { 
			// TODO: handle exception
			e.printStackTrace();
			prefectureList = null;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			// DB切断
			super.close();
			System.out.println();
		}
		
		// 都道府県のリストを返します
		return prefectureList;
	}
}
