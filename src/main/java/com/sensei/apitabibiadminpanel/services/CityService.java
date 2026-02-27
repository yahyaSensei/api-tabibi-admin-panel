package com.sensei.apitabibiadminpanel.services;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final JdbcTemplate jdbcTemplate;

    // دالة إضافة مدينة جديدة
    public String addCity(String name) {
        String sql = "EXEC sp_AddCity @Name = ?";

        // بنباصي اسم المدينة للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, name);
    }
    // دالة تعديل مدينة موجودة
    public String updateCity(String id, String name) {
        String sql = "EXEC sp_UpdateCity @Id = ?, @Name = ?";

        // بنمرر الـ ID والاسم الجديد
        return jdbcTemplate.queryForObject(sql, String.class, id, name);
    }

    // دالة حذف مدينة
    public String deleteCity(String id) {
        String sql = "EXEC sp_DeleteCity @Id = ?";

        // تمرير الـ ID فقط للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    // دالة البحث عن المدن (أو جلب الكل لو كلمة البحث فاضية)
    public String searchCities(String searchTerm) {
        String sql = "EXEC sp_SearchCities @SearchTerm = ?";

        // لو الـ searchTerm بـ null هنبعت مكانه string فاضي عشان الـ SQL ميضربش
        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";

        return jdbcTemplate.queryForObject(sql, String.class, safeSearchTerm);
    }
}