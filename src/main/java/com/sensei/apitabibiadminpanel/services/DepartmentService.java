package com.sensei.apitabibiadminpanel.services;



import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final JdbcTemplate jdbcTemplate;

    // دالة إضافة قسم جديد
    public String addDepartment(String name, String description, String imageUrl) {
        String sql = "EXEC sp_AddDepartment @Name = ?, @Description = ?, @ImageUrl = ?";

        // بنباصي المتغيرات للـ Procedure بالترتيب
        return jdbcTemplate.queryForObject(sql, String.class, name, description, imageUrl);
    }

    // دالة تعديل قسم موجود
    public String updateDepartment(String id, String name, String description, String imageUrl) {
        String sql = "EXEC sp_UpdateDepartment @Id = ?, @Name = ?, @Description = ?, @ImageUrl = ?";

        // بنباصي الـ ID والبيانات الجديدة
        return jdbcTemplate.queryForObject(sql, String.class, id, name, description, imageUrl);
    }
    // دالة حذف قسم
    public String deleteDepartment(String id) {
        String sql = "EXEC sp_DeleteDepartment @Id = ?";

        // تمرير الـ ID فقط للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    // دالة جلب كل الأقسام مع الإحصائيات (عدد الأطباء)
    public String getAllDepartmentsWithStats() {
        String sql = "EXEC sp_GetAllDepartmentsWithStats";

        // بترجع مصفوفة JSON فيها كل الأقسام
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}