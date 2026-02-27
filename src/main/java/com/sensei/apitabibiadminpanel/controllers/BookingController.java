package com.sensei.apitabibiadminpanel.controllers;

import com.sensei.apitabibiadminpanel.DTO.BookingRequestDto;
import com.sensei.apitabibiadminpanel.DTO.UpdateBookingStatusRequestDto;
import com.sensei.apitabibiadminpanel.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchBookings(
            @RequestParam(value = "q", defaultValue = "") String q) {

        // استدعاء الخدمة
        String jsonResult = bookingService.searchBookings(q);

        // إرجاع 200 OK
        return ResponseEntity.ok(jsonResult);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBooking(@RequestBody BookingRequestDto request) {

        String jsonResult = bookingService.addBooking(
                request.patientId(),
                request.doctorId(),
                request.appointmentDate(),
                request.status(),
                request.type(),
                request.pricePaid(),
                request.paymentIntentId()
        );

        // إرجاع 201 Created مع بيانات الحجز الجديد
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonResult);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookingDetails(@PathVariable("id") String id) {

        // استدعاء الخدمة
        String jsonResult = bookingService.getBookingDetails(id);

        // لو الـ JSON راجع فاضي {}، يعني الحجز مش موجود أو اتمسح
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Booking not found\"}");
        }

        // إرجاع 200 OK مع تفاصيل الحجز
        return ResponseEntity.ok(jsonResult);
    }



    // ... الأكواد القديمة ...

    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBookingStatus(
            @PathVariable("id") String id,
            @RequestBody UpdateBookingStatusRequestDto request) {

        // استدعاء الخدمة لتحديث الحالة
        String jsonResult = bookingService.updateBookingStatus(id, request.status());

        // لو الـ JSON راجع فاضي {}، يعني الحجز مش موجود
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Booking not found\"}");
        }

        // إرجاع 200 OK مع تفاصيل الحجز بعد التحديث
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/{id}/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPrescriptionByBooking(@PathVariable("id") String bookingId) {

        // استدعاء الخدمة لجلب الروشتة
        String jsonResult = bookingService.getPrescriptionByBooking(bookingId);

        // لو الـ JSON راجع فاضي {}، يعني مفيش روشتة اتكتبت للحجز ده لسه
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"لم يتم كتابة روشتة لهذا الحجز بعد\"}");
        }

        // إرجاع 200 OK مع بيانات الروشتة
        return ResponseEntity.ok(jsonResult);
    }
}