package com.sensei.apitabibiadminpanel.services;



import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final JdbcTemplate jdbcTemplate;

    // دالة جلب كل العيادات مع بيانات الطبيب والمدينة
    public String getAllClinics() {
        String sql = "EXEC sp_GetAllClinics";

        return jdbcTemplate.queryForObject(sql, String.class);
    }

    // دالة البحث الشامل عن العيادات
    public String searchClinics(String searchTerm) {
        String sql = "EXEC sp_SearchClinics @SearchTerm = ?";

        // لو الـ searchTerm مبعوث بـ null هنحط مكانه string فاضي عشان يجيب الكل
        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";

        return jdbcTemplate.queryForObject(sql, String.class, safeSearchTerm);
    }

    // دالة حذف عيادة
    public String deleteClinic(String id) {
        String sql = "EXEC sp_DeleteClinic @Id = ?";

        // تمرير الـ ID للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    // دالة جلب مواعيد عمل عيادة معينة
    public String getClinicSchedules(String clinicId) {
        String sql = "EXEC sp_GetClinicSchedules @ClinicId = ?";

        // بنمرر الـ ClinicId للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, clinicId);
    }
}