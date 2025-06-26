package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import dto.PickupDTO;

public class GachaDAO extends DAO {

	// 1日1回制限チェック
    public boolean hasDrawnToday(String userId) {
        boolean result = false;
        
        try {
            access();

            String sql = "SELECT COUNT(*) FROM gacha WHERE user_id = ? AND turned_date BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            // 今日の00:00:00 ～ 23:59:59 を指定
            LocalDate today = LocalDate.now();
            Timestamp start = Timestamp.valueOf(today.atStartOfDay());
            Timestamp end = Timestamp.valueOf(today.atTime(LocalTime.MAX));

            ps.setString(1, userId);
            ps.setTimestamp(2, start);
            ps.setTimestamp(3, end);
            
            System.out.println("一日一回チェック通りました。");
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

    // ガチャ結果を保存
    public void saveResult(String userId, int pickupId) {
        try {
            access();

            //gachaテーブルに新しい1行のデータを追加（INSERT） する
            String sql = "INSERT INTO gacha (user_id, turned_date, pickup_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, userId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, pickupId);

            ps.executeUpdate();
            
            System.out.println("ガチャ結果を保存するところ通りました～");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // 今日引いたガチャ結果（最新）を取得
    public PickupDTO getTodayResult(String userId) {
        PickupDTO result = null;

        try {
            access();

            String sql = """
                SELECT
				    p.pickup_id,
				    p.prefecture_id,
				    pf.prefecture_name,
				    p.pickup_place,
				    p.remarks
				FROM
				    gacha g
				JOIN
				    pickups p ON g.pickup_id = p.pickup_id
				JOIN
				    prefectures pf ON p.prefecture_id = pf.prefecture_id
				WHERE
				    g.user_id = ?
				    AND DATE(g.turned_date) = CURDATE()
				ORDER BY
				    g.turned_date DESC
				LIMIT 1;
            """;
            
            System.out.println("UserDAOで取得したデータ"+sql);
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new PickupDTO(
                    rs.getInt("pickup_id"),
                    rs.getInt("prefecture_id"),
                    rs.getString("prefecture_name"),
                    rs.getString("pickup_place"),
                    rs.getString("remarks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }
}
