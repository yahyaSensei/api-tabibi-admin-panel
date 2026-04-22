package com.sensei.apitabibiadminpanel.controllers;

import com.sensei.apitabibiadminpanel.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDashboardSummary() {
        // بنستدعي الدالة من الـ Service
        String jsonResult = analyticsService.getDashboardSummaryStats();

        // بنرجع النتيجة مباشرة كـ JSON
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/revenue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRevenue() {
        String jsonResult = analyticsService.getRevenueAnalytics();
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/bookings-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookingsStatus() {
        // بننادي على الـ Service
        String jsonResult = analyticsService.getBookingsCountByStatus();

        // بنرجع النتيجة للـ Frontend
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/top-doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTopDoctors(
            @RequestParam(value = "limit", defaultValue = "5") int limit) {

        // بننادي على الـ Service ونبعتله الرقم
        String jsonResult = analyticsService.getTopPerformingDoctors(limit);

        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/demanded-departments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDemandedDepartments() {
        String jsonResult = analyticsService.getMostDemandedDepartments();
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/clinics-distribution", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getClinicsDistribution() {
        // استدعاء الدالة من الـ Service
        String jsonResult = analyticsService.getClinicsDistributionByCity();

        // إرجاع مصفوفة الـ JSON مباشرة
        return ResponseEntity.ok(jsonResult);
    }

    // الـ Endpoint المجمعة الخاصة بشاشة الداشبورد الرئيسية
    @GetMapping(value = "/admin-dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAdminDashboard(
            @RequestParam(value = "period", defaultValue = "monthly") String period) {

        // بننادي على الـ Service ونبعتلها المدة (monthly, weekly, daily, yearly)
        String jsonResult = analyticsService.getAdminDashboardSummary(period);

        // بنرجع النتيجة المجمعة للـ Frontend
        return ResponseEntity.ok(jsonResult);
    }
}