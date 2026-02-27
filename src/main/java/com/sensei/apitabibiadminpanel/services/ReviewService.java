package com.sensei.apitabibiadminpanel.services;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final JdbcTemplate jdbcTemplate;

    // دالة جلب كل التقييمات
    public String getAllReviews() {
        String sql = "EXEC sp_GetAllReviews";

        // بترجع مصفوفة JSON فيها كل التقييمات
        return jdbcTemplate.queryForObject(sql, String.class);
    }
    // دالة البحث في التقييمات
    public String searchReviews(String searchTerm) {
        String sql = "EXEC sp_SearchReviews @SearchTerm = ?";

        // لو الـ searchTerm مبعوث بـ null هنحط مكانه string فاضي عشان يجيب الكل
        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";

        return jdbcTemplate.queryForObject(sql, String.class, safeSearchTerm);
    }

    // دالة حذف التقييم (للأدمن)
    public String deleteReview(String id) {
        String sql = "EXEC sp_DeleteReview_Admin @Id = ?";

        // تمرير الـ ID للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}