package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AreaDTO;

public class AreaDAO extends DAO {
	
	/**
	 * 47都道府県の地方を取得します。
	 * @return [ ArrayList ] 47都道府県の地方リスト
	 */
	public List<AreaDTO> selectAll() {
		// DB接続
		super.access();
		
		// 地方のリスト
		List<AreaDTO> areaList = new ArrayList<AreaDTO>();
		System.out.println("47都道府県の地方のレコードを取得します。");
		
		try {
			String sql = """
					SELECT
						prefecture_id,
						area_name
					FROM
						areas;
					""";
			
			// [ 予約 ] SQL文セット
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			System.out.println("問い合わせ（取得）を実行しました。");
			
			// 結果をコレクションにコピーする
			while (rs.next()) {
				// [ Entity ] 地方
				AreaDTO area = new AreaDTO();
				// 都道府県 ID
				area.setPrefecture_id(rs.getInt(
						"prefecture_id"));
				// 地方名
				area.setArea_name(rs.getString(
						"area_name"));
				
				// 地方のリストに追加
				areaList.add(area);
			}
			System.out.println("取得データをパックしました...");
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			areaList = null;
		} finally {
			// DB切断
			super.close();
			System.out.println();
		}
		
		// 地方のリストを返します。
		return areaList;
	}
}
