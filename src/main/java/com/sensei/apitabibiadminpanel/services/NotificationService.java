package com.sensei.apitabibiadminpanel.services;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JdbcTemplate jdbcTemplate;

    // دالة إرسال إشعار عام
    public String sendGlobalNotification(String title, String message, String targetRole) {
        String sql = "EXEC sp_SendGlobalNotification @Title = ?, @Message = ?, @TargetRole = ?";

        return jdbcTemplate.queryForObject(sql, String.class, title, message, targetRole);
    }
    // دالة جلب أحدث إشعارات النظام
    public String getSystemNotifications() {
        String sql = "EXEC sp_GetSystemNotifications";

        // بترجع مصفوفة JSON فيها أحدث الإشعارات
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}