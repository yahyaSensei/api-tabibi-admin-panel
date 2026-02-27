package com.sensei.apitabibiadminpanel.controllers;


import com.sensei.apitabibiadminpanel.DTO.CityRequestDto;
import com.sensei.apitabibiadminpanel.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCity(@RequestBody CityRequestDto request) {

        // استدعاء الخدمة لإضافة المدينة
        String jsonResult = cityService.addCity(request.name());

        // إرجاع 201 Created مع بيانات المدينة الجديدة
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonResult);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCity(
            @PathVariable("id") String id,
            @RequestBody CityRequestDto request) {

        // استدعاء الخدمة لتعديل المدينة
        String jsonResult = cityService.updateCity(id, request.name());

        // لو المدينة مش موجودة (الـ JSON راجع فاضي)
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"City not found\"}");
        }

        // إرجاع 200 OK مع بيانات المدينة بعد التعديل
        return ResponseEntity.ok(jsonResult);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCity(@PathVariable("id") String id) {

        // استدعاء الخدمة لحذف المدينة
        String jsonResult = cityService.deleteCity(id);

        // لو الـ JSON الراجع عبارة عن {} ده معناه إن المدينة مش موجودة
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"City not found\"}");
        }

        // لو الـ JSON فيه كلمة conflict، يعني المدينة مرتبطة بعيادات
        if (jsonResult.contains("\"error\": \"conflict\"")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResult);
        }

        // إرجاع 200 OK مع رسالة التأكيد
        return ResponseEntity.ok(jsonResult);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchCities(
            @RequestParam(value = "search", required = false, defaultValue = "") String search) {

        // استدعاء الخدمة للبحث (أو جلب الكل)
        String jsonResult = cityService.searchCities(search);

        // إرجاع 200 OK مع المصفوفة
        return ResponseEntity.ok(jsonResult);
    }
}