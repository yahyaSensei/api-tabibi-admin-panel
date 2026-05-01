package com.sensei.apitabibiadminpanel.services;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // ==============================================================================
    // New Methods for Reviews Page (Pagination, Filter, Search, Sort)
    // ==============================================================================
    public String getReviewsPageList(int pageNumber, int pageSize, String searchTerm,
                                     String doctorId, String patientId,
                                     String sortBy, String sortOrder) {

        String sql = "EXEC sp_GetReviewsPage_List @PageNumber = ?, @PageSize = ?, @SearchTerm = ?, @DoctorId = ?, @PatientId = ?, @SortBy = ?, @SortOrder = ?";

        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";
        String safeDoctorId = (doctorId != null && !doctorId.trim().isEmpty()) ? doctorId : null;
        String safePatientId = (patientId != null && !patientId.trim().isEmpty()) ? patientId : null;
        String safeSortBy = (sortBy != null && !sortBy.trim().isEmpty()) ? sortBy : "CreatedAt";
        String safeSortOrder = (sortOrder != null && !sortOrder.trim().isEmpty()) ? sortOrder : "DESC";

        List<String> jsonChunks = jdbcTemplate.queryForList(sql, String.class,
                pageNumber, pageSize, safeSearchTerm, safeDoctorId, safePatientId, safeSortBy, safeSortOrder);

        if (jsonChunks == null || jsonChunks.isEmpty()) {
            return "{\"totalCount\": 0, \"pageNumber\": " + pageNumber + ", \"pageSize\": " + pageSize + ", \"data\": []}";
        }

        return String.join("", jsonChunks);
    }
}