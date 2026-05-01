package com.sensei.apitabibiadminpanel.controllers;

import com.sensei.apitabibiadminpanel.DTO.BookingRequestDto;
import com.sensei.apitabibiadminpanel.DTO.UpdateBookingStatusRequestDto;
import com.sensei.apitabibiadminpanel.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchBookings(
            @RequestParam(value = "q", defaultValue = "") String q) {
        String jsonResult = bookingService.searchBookings(q);
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
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonResult);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookingDetails(@PathVariable("id") String id) {
        String jsonResult = bookingService.getBookingDetails(id);
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Booking not found\"}");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBookingStatus(
            @PathVariable("id") String id,
            @RequestBody UpdateBookingStatusRequestDto request) {
        String jsonResult = bookingService.updateBookingStatus(id, request.status());
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Booking not found\"}");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/{id}/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPrescriptionByBooking(@PathVariable("id") String bookingId) {
        String jsonResult = bookingService.getPrescriptionByBooking(bookingId);
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"لم يتم كتابة روشتة لهذا الحجز بعد\"}");
        }
        return ResponseEntity.ok(jsonResult);
    }

    // ==============================================================================
    // Endpoints for Appointments Page (List & Details)
    // ==============================================================================

    @GetMapping(value = "/page/overview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookingsPageList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {

        // تنظيف التواريخ لو مبعوتة فاضية بدل ما تضرب Error في الـ SQL
        String finalStartDate = (startDate != null && !startDate.trim().isEmpty()) ? startDate : null;
        String finalEndDate = (endDate != null && !endDate.trim().isEmpty()) ? endDate : null;

        String jsonResult = bookingService.getBookingsPageList(page, size, search, status, type, finalStartDate, finalEndDate);

        // Remove the extra array brackets added by JSON PATH at the top level of the SP output
        if(jsonResult != null && jsonResult.startsWith("[") && jsonResult.endsWith("]")) {
            jsonResult = jsonResult.substring(1, jsonResult.length() - 1);
        }

        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/page/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookingsPageDetails(@PathVariable("id") String id) {
        String jsonResult = bookingService.getBookingsPageDetails(id);

        if ("{}".equals(jsonResult) || jsonResult == null || jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Booking details not found\"}");
        }

        return ResponseEntity.ok(jsonResult);
    }
}