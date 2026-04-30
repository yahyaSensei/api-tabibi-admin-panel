package com.sensei.apitabibiadminpanel.services;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final JdbcTemplate jdbcTemplate;

    // دالة البحث الشامل عن الحجوزات
    public String searchBookings(String searchTerm) {
        String sql = "EXEC sp_SearchBookings @SearchTerm = ?";

        // لو مفيش كلمة بحث، بنبعت String فاضي عشان نجيب كل الحجوزات
        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";

        return jdbcTemplate.queryForObject(sql, String.class, safeSearchTerm);
    }



    // دالة إضافة حجز جديد
    public String addBooking(String patientId, String doctorId, String appointmentDate,
                             int status, int type, BigDecimal pricePaid, String paymentIntentId) {

        String sql = "EXEC sp_AddBooking @PatientId = ?, @DoctorId = ?, @AppointmentDate = ?, @Status = ?, @Type = ?, @PricePaid = ?, @PaymentIntentId = ?";

        return jdbcTemplate.queryForObject(sql, String.class,
                patientId, doctorId, appointmentDate, status, type, pricePaid, paymentIntentId);
    }
    // دالة جلب تفاصيل حجز معين
    public String getBookingDetails(String id) {
        String sql = "EXEC sp_GetBookingDetails @Id = ?";

        // تمرير الـ ID للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    // دالة تحديث حالة الحجز (للأدمن)
    public String updateBookingStatus(String id, int status) {
        String sql = "EXEC sp_UpdateBookingStatus_Admin @Id = ?, @NewStatus = ?";

        // تمرير الـ ID والحالة الجديدة للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, id, status);
    }
    // دالة جلب الروشتة الخاصة بحجز معين
    public String getPrescriptionByBooking(String bookingId) {
        String sql = "EXEC sp_GetPrescriptionByBooking @BookingId = ?";

        // تمرير الـ BookingId للـ Procedure
        return jdbcTemplate.queryForObject(sql, String.class, bookingId);
    }

    // ==============================================================================
    // New Methods for Appointments Page
    // ==============================================================================

    public String getBookingsPageList(int pageNumber, int pageSize, String searchTerm, Integer statusFilter) {
        String sql = "EXEC sp_GetBookingsPage_List @PageNumber = ?, @PageSize = ?, @SearchTerm = ?, @StatusFilter = ?";
        String safeSearchTerm = (searchTerm != null) ? searchTerm : "";
        return jdbcTemplate.queryForObject(sql, String.class, pageNumber, pageSize, safeSearchTerm, statusFilter);
    }

    public String getBookingsPageDetails(String id) {
        String sql = "EXEC sp_GetBookingsPage_Details @BookingId = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}