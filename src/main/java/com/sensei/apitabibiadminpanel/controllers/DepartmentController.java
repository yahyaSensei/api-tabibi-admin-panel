package com.sensei.apitabibiadminpanel.controllers;



import com.sensei.apitabibiadminpanel.DTO.DepartmentRequestDto;
import com.sensei.apitabibiadminpanel.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentRequestDto request) {

        // استدعاء الخدمة لإضافة القسم
        String jsonResult = departmentService.addDepartment(
                request.name(),
                request.description(),
                request.imageUrl()
        );

        // إرجاع 201 Created مع بيانات القسم الجديد
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonResult);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateDepartment(
            @PathVariable("id") String id,
            @RequestBody DepartmentRequestDto request) {

        // استدعاء الخدمة لتعديل القسم
        String jsonResult = departmentService.updateDepartment(
                id,
                request.name(),
                request.description(),
                request.imageUrl()
        );

        // لو الـ JSON الراجع عبارة عن {} ده معناه إن القسم مش موجود أصلاً
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Department not found\"}");
        }

        // إرجاع 200 OK مع بيانات القسم بعد التعديل
        return ResponseEntity.ok(jsonResult);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") String id) {

        // استدعاء الخدمة لحذف القسم
        String jsonResult = departmentService.deleteDepartment(id);

        // لو الـ JSON الراجع عبارة عن {} ده معناه إن القسم مش موجود
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Department not found\"}");
        }

        // إرجاع 200 OK مع رسالة التأكيد
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllDepartments() {

        // استدعاء الخدمة
        String jsonResult = departmentService.getAllDepartmentsWithStats();

        // إرجاع 200 OK مع مصفوفة الأقسام
        return ResponseEntity.ok(jsonResult);
    }
}