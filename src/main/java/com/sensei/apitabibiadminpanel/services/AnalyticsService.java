package com.sensei.apitabibiadminpanel.services;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // دي بتعمل Constructor تلقائي عشان تحقن الـ JdbcTemplate
public class AnalyticsService {

    private final JdbcTemplate jdbcTemplate;

    // دي الدالة اللي بتسحب الداتا من الـ SQL Server كـ JSON جاهز
    public String getDashboardSummaryStats() {
        String sql = "EXEC sp_GetDashboardSummaryStats";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    // تقدر تزود باقي دوال الإحصائيات هنا بنفس الطريقة
    public String getRevenueAnalytics() {
        String sql = "EXEC sp_GetRevenueAnalytics";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    // دالة إحصائيات الحجوزات بناءً على الحالة
    public String getBookingsCountByStatus() {
        String sql = "EXEC sp_GetBookingsCountByStatus";
        // هترجع String جواه مصفوفة JSON
        return jdbcTemplate.queryForObject(sql, String.class);
    }
    // دالة جلب أفضل الأطباء أداءً
    public String getTopPerformingDoctors(int topN) {
        // بنمرر الرقم للـ Stored Procedure
        String sql = "EXEC sp_GetTopPerformingDoctors @TopN = ?";

        return jdbcTemplate.queryForObject(sql, String.class, topN);
    }

    // دالة جلب الأقسام الأكثر طلباً
    public String getMostDemandedDepartments() {
        String sql = "EXEC sp_GetMostDemandedDepartments";
        return jdbcTemplate.queryForObject(sql, String.class);
    }
    // دالة جلب توزيع العيادات جغرافياً على المدن
    public String getClinicsDistributionByCity() {
        String sql = "EXEC sp_GetClinicsDistributionByCity";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    // الدالة التي ترجع الداشبورد كلها في خبطة واحدة لتوليد //
    public String getAdminDashboardSummary() {
        String sql = "EXEC sp_GetAdminDashboardSummary";

        // 1. استخدم queryForList لاستقبال كافة الصفوف التي يرجعها SQL Server
        List<String> jsonChunks = jdbcTemplate.queryForList(sql, String.class);

        // 2. قم بدمج هذه الصفوف (القطع) لتكوين نص الـ JSON النهائي
        if (jsonChunks == null || jsonChunks.isEmpty()) {
            return "{}"; // أو التعامل مع الحالة فارغة كما تفضل
        }

        return String.join("", jsonChunks);
    }
}