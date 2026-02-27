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
}