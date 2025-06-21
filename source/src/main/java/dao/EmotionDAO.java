package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.EmotionDTO;

public class EmotionDAO extends DAO {
	
	/**
	 * <h3>全感情を取得します。</h3>
	 * 
	 * @return [ ArrayList ] 感情のリスト
	 */
	public ArrayList<EmotionDTO> selectAll() {
		// DB接続
		super.access();
		
		// 感情のリスト
		ArrayList<EmotionDTO> emoList = new ArrayList<EmotionDTO>();
		
		try {
			// [ 準備 ] SQL文
			String sql = """
					SELECT
						*
					FROM
						emotions
					ORDER BY
						emotion_id ASC;
					""";
			
			// [ 予約 ] SQL文をセット
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// [ 問い合わせ ] SQL文を実行し、結果を取得する
			ResultSet rs = pStmt.executeQuery();
			
			// 結果をコレクションにコピーする
			while (rs.next()) {
				//  [ Entity ] 感情
				EmotionDTO emotiondto = new EmotionDTO();
				// 感情ID
				emotiondto.setEmotion_id(rs.getInt(
						"emotion_id"));
				// 感情名
				emotiondto.setEmotion_name(rs.getString(
						"emotion_name"));
				// 絵文字
				emotiondto.setEmoji(rs.getString(
						"emoji"));
				// 感情リストに追加
				emoList.add(emotiondto);
			}
		} 
		catch (SQLException e) {
			// TODO: handle exception
			// 例外を表示
			e.printStackTrace();
			// 例外なら空値に
			emoList = null;
		}
		finally {
			// DB切断
			super.close();
		}
		
		// 感情リストを返す
		return emoList;
	}
}
