package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.BadgeDTO;

public class BadgeDAO {

	// データベース接続
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/a1";
    private final String DB_USER = "root";
    private final String DB_PASS = "password";
    
    // DB接続取得用メソッド
    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    /**
     * 1. 指定ユーザーが獲得したバッジと未獲得の全バッジを一覧取得
     * userId ユーザーID
     * バッジDTOのリスト
     */
    public List<BadgeDTO> getAllBadgesWithUserStatus(String userId) {
        List<BadgeDTO> badgeList = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT b.badge_id, b.badge_name, b.badge_image, " +
                         "h.date_acquisition AS badgeAcquiredDate " +
                         "FROM badges b " +
                         "LEFT JOIN holds h ON b.badge_id = h.badge_id AND h.user_id = ? " +
                         "ORDER BY b.badge_id";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        BadgeDTO badge = new BadgeDTO();
                        badge.setBadge_id(rs.getInt("badge_id"));
                        badge.setBadge_name(rs.getString("badge_name"));
                        badge.setBadge_image(rs.getString("badge_image"));
                        Timestamp acquired = rs.getTimestamp("badgeAcquiredDate");
                        badge.setBadgeAcquiredDate(acquired != null ? acquired.toString() : null);
                        badgeList.add(badge);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badgeList;
    }

    /**
     * 2. ユーザーの訪問情報と保持バッジから、自動的に地方・全国制覇バッジを付与
     * userId ユーザーID
     */
    public void checkAndGrantBadges(String userId) {
        Map<Integer, List<Integer>> areaPrefMap = Map.of(
            1, List.of(1),
            2, List.of(2, 3, 4, 5, 6, 7),
            3, List.of(8, 9, 10, 11, 12, 13, 14),
            4, List.of(15,16,17,18,19,20,21,22,23),
            5, List.of(24,25,26,27,28,29,30),
            6, List.of(31,32,33,34,35),
            7, List.of(36,37,38,39),
            8, List.of(40,41,42,43,44,45,46,47)
        );

        List<Integer> visited = getVisitedPrefectureIds(userId);
        List<Integer> owned = getOwnedBadgeIds(userId);

        try {
            for (Map.Entry<Integer, List<Integer>> entry : areaPrefMap.entrySet()) {
                int badgeId = entry.getKey();
                List<Integer> required = entry.getValue();
                if (visited.containsAll(required) && !owned.contains(badgeId)) {
                    insertBadge(userId, badgeId);
                }
            }
            if (visited.size() == 47 && !owned.contains(9)) {
                insertBadge(userId, 9);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 3. ユーザーが保有しているバッジID一覧を取得
     * userId ユーザーID
     * badge_idのリスト
     */
    public List<Integer> getOwnedBadgeIds(String userId) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT badge_id FROM holds WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("badge_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 4. ユーザーが訪問した都道府県ID一覧を取得
     * userId ユーザーID
     * 都道府県IDのリスト
     */
    public List<Integer> getVisitedPrefectureIds(String userId) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT prefecture_id FROM visitors WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("prefecture_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 5. 日時とともに称号バッジを新たに付与）
     * userId ユーザーID
     * badgeId バッジID
     */
    public void insertBadge(String userId, int badgeId) {
        String sql = "INSERT INTO holds (badge_id, user_id, date_acquisition) VALUES (?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, badgeId);
            ps.setString(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
