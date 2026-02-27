package com.sensei.apitabibiadminpanel.controllers;

import com.sensei.apitabibiadminpanel.services.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/clinics")
@RequiredArgsConstructor
public class ClinicController {

    private final ClinicService clinicService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllClinics() {

        // استدعاء الخدمة لجلب العيادات
        String jsonResult = clinicService.getAllClinics();

        // إرجاع 200 OK مع مصفوفة العيادات
        return ResponseEntity.ok(jsonResult);
    }



    // ... الأكواد القديمة ...

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchClinics(
            @RequestParam(value = "q", defaultValue = "") String q) {

        // استدعاء الخدمة للبحث في العيادات
        String jsonResult = clinicService.searchClinics(q);

        // إرجاع 200 OK مع مصفوفة النتائج
        return ResponseEntity.ok(jsonResult);
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteClinic(@PathVariable("id") String id) {

        // استدعاء الخدمة لحذف العيادة
        String jsonResult = clinicService.deleteClinic(id);

        // لو الـ JSON الراجع عبارة عن {} ده معناه إن العيادة مش موجودة
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Clinic not found\"}");
        }

        // لو الـ JSON فيه كلمة error (بسبب الـ CATCH)
        if (jsonResult.contains("\"error\"")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResult);
        }

        // إرجاع 200 OK مع رسالة التأكيد
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/{id}/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getClinicSchedules(@PathVariable("id") String clinicId) {

        // استدعاء الخدمة لجلب المواعيد
        String jsonResult = clinicService.getClinicSchedules(clinicId);

        // إرجاع 200 OK مع مصفوفة المواعيد
        return ResponseEntity.ok(jsonResult);
    }
}