package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BadgeDTO;

public class BadgeDAO {

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/a1";
    private final String DB_USER = "root";
    private final String DB_PASS = "password"; // 自分の設定に応じて変更

    public List<BadgeDTO> getAllBadgesWithUserStatus(String userId) {
        List<BadgeDTO> badgeList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

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
                        badge.setBadgeAcquiredDate(rs.getString("badgeAcquiredDate")); // null可
                        badgeList.add(badge);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return badgeList;
    }
}
