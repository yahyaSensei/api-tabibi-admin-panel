package com.sensei.apitabibiadminpanel.services;



import com.sensei.apitabibiadminpanel.DTO.AddUserRequestDto;
import com.sensei.apitabibiadminpanel.DTO.UpdateUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    // دالة جلب المستخدمين مع إمكانية الفلترة بالنوع
    public String getAllUsers(String role) {
        String sql = "EXEC sp_GetAllUsers @Role = ?";

        // لو مبعتوش role بنبعت null عشان הـ SQL يجيب الكل
        return jdbcTemplate.queryForObject(sql, String.class, role);
    }
    public String updateUser(String id, UpdateUserRequestDto request) {
        String sql = "EXEC sp_UpdateUser @Id = ?, @Name = ?, @PhoneNumber = ?, @Gender = ?, @DateOfBirth = ?, @CityId = ?, @Bio = ?, @ConsultationFee = ?, @DepartmentId = ?, @YearsOfExperience = ?, @Status = ?";

        return jdbcTemplate.queryForObject(sql, String.class,
                id,
                request.name(),
                request.phoneNumber(),
                request.gender(),
                request.dateOfBirth(),
                request.cityId(),
                request.bio(),
                request.consultationFee(),
                request.departmentId(),
                request.yearsOfExperience(),
                request.status()
        );
    }

    // دالة إضافة مستخدم جديد
    public String addUser(AddUserRequestDto request) {
        // غيرنا اسم الـ Procedure هنا لـ sp_AddNewUser
        String sql = "EXEC sp_AddNewUser @Name = ?, @Email = ?, @PhoneNumber = ?, @Role = ?, @Gender = ?, @DateOfBirth = ?, @CityId = ?, @Bio = ?, @ConsultationFee = ?, @DepartmentId = ?, @YearsOfExperience = ?, @Status = ?";

        return jdbcTemplate.queryForObject(sql, String.class,
                request.name(),
                request.email(),
                request.phoneNumber(),
                request.role(),
                request.gender(),
                request.dateOfBirth(),
                request.cityId(),
                request.bio(),
                request.consultationFee(),
                request.departmentId(),
                request.yearsOfExperience(),
                request.status()
        );
    }

    // دالة حذف المستخدم (بطريقة آمنة)
    public String deleteUser(String id) {
        String sql = "EXEC sp_DeleteUser @Id = ?";

        // تمرير الـ ID للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
    // دالة البحث في المستخدمين
    public String searchUsers(String searchTerm) {
        String sql = "EXEC sp_SearchUser @SearchTerm = ?";

        // لو الـ searchTerm مبعوث بـ null هنحط مكانه string فاضي عشان يجيب الكل
        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";

        return jdbcTemplate.queryForObject(sql, String.class, safeSearchTerm);
    }

    // ==============================================================================
    // New Methods for Doctors Page
    // ==============================================================================

    public String getDoctorsPageList(int pageNumber, int pageSize, String searchTerm,
                                     String cityId, String departmentId,
                                     String sortBy, String sortOrder) {

        String sql = "EXEC sp_GetDoctorsPage_List @PageNumber = ?, @PageSize = ?, @SearchTerm = ?, @CityId = ?, @DepartmentId = ?, @SortBy = ?, @SortOrder = ?";

        // تنظيف الـ Parameters
        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";
        String safeCityId = (cityId != null && !cityId.trim().isEmpty()) ? cityId : null;
        String safeDepartmentId = (departmentId != null && !departmentId.trim().isEmpty()) ? departmentId : null;
        String safeSortBy = (sortBy != null && !sortBy.trim().isEmpty()) ? sortBy : "CreatedAt";
        String safeSortOrder = (sortOrder != null && !sortOrder.trim().isEmpty()) ? sortOrder : "DESC";

        List<String> jsonChunks = jdbcTemplate.queryForList(sql, String.class,
                pageNumber, pageSize, safeSearchTerm, safeCityId, safeDepartmentId, safeSortBy, safeSortOrder);

        if (jsonChunks == null || jsonChunks.isEmpty()) {
            return "{\"totalCount\": 0, \"pageNumber\": " + pageNumber + ", \"pageSize\": " + pageSize + ", \"data\": []}";
        }
        return String.join("", jsonChunks);
    }

    public String getDoctorPageDetails(String id) {
        String sql = "EXEC sp_GetDoctorPage_Details @DoctorId = ?";
        List<String> jsonChunks = jdbcTemplate.queryForList(sql, String.class, id);

        if (jsonChunks == null || jsonChunks.isEmpty()) {
            return "{}";
        }
        return String.join("", jsonChunks);
    }

    // ==============================================================================
    // New Methods for Patients Page
    // ==============================================================================
    public String getPatientsPageList(int pageNumber, int pageSize, String searchTerm,
                                      String cityId, String sortBy, String sortOrder) {
        String sql = "EXEC sp_GetPatientsPage_List @PageNumber = ?, @PageSize = ?, @SearchTerm = ?, @CityId = ?, @SortBy = ?, @SortOrder = ?";

        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";
        String safeCityId = (cityId != null && !cityId.trim().isEmpty()) ? cityId : null;
        String safeSortBy = (sortBy != null && !sortBy.trim().isEmpty()) ? sortBy : "CreatedAt";
        String safeSortOrder = (sortOrder != null && !sortOrder.trim().isEmpty()) ? sortOrder : "DESC";

        List<String> jsonChunks = jdbcTemplate.queryForList(sql, String.class,
                pageNumber, pageSize, safeSearchTerm, safeCityId, safeSortBy, safeSortOrder);

        if (jsonChunks == null || jsonChunks.isEmpty()) {
            return "{\"totalCount\": 0, \"pageNumber\": " + pageNumber + ", \"pageSize\": " + pageSize + ", \"data\": []}";
        }

        return String.join("", jsonChunks);
    }

}