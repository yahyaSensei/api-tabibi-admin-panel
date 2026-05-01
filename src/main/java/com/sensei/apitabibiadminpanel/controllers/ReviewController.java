package com.sensei.apitabibiadminpanel.controllers;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

import com.sensei.apitabibiadminpanel.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllReviews() {

        // استدعاء الخدمة لجلب التقييمات
        String jsonResult = reviewService.getAllReviews();

        // إرجاع 200 OK مع مصفوفة التقييمات
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchReviews(
            @RequestParam(value = "q", defaultValue = "") String q) {

        // استدعاء الخدمة للبحث في التقييمات
        String jsonResult = reviewService.searchReviews(q);

        // إرجاع 200 OK مع مصفوفة النتائج
        return ResponseEntity.ok(jsonResult);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteReview(@PathVariable("id") String id) {

        // استدعاء الخدمة لحذف التقييم
        String jsonResult = reviewService.deleteReview(id);

        // لو الـ JSON راجع فاضي {}، يعني التقييم مش موجود
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Review not found\"}");
        }

        // إرجاع 200 OK مع رسالة التأكيد
        return ResponseEntity.ok(jsonResult);
    }

    // ==============================================================================
    // Endpoints for Reviews Overview
    // ==============================================================================
    @GetMapping(value = "/page/overview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getReviewsPageList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "doctorId", required = false) String doctorId,
            @RequestParam(value = "patientId", required = false) String patientId,
            @RequestParam(value = "sortBy", defaultValue = "CreatedAt") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder) {

        String jsonResult = reviewService.getReviewsPageList(page, size, search, doctorId, patientId, sortBy, sortOrder);

        // Remove the extra array brackets added by JSON PATH at the top level of the SP output
        if(jsonResult != null && jsonResult.startsWith("[") && jsonResult.endsWith("]")) {
            jsonResult = jsonResult.substring(1, jsonResult.length() - 1);
        }

        return ResponseEntity.ok(jsonResult);
    }
}