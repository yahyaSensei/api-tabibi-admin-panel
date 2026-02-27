package com.sensei.apitabibiadminpanel.controllers;


import com.sensei.apitabibiadminpanel.DTO.AddUserRequestDto;
import com.sensei.apitabibiadminpanel.DTO.UpdateUserRequestDto;
import com.sensei.apitabibiadminpanel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllUsers(
            @RequestParam(value = "role", required = false) String role) {

        // استدعاء الخدمة لجلب المستخدمين
        String jsonResult = userService.getAllUsers(role);

        // إرجاع 200 OK
        return ResponseEntity.ok(jsonResult);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(
            @PathVariable("id") String id,
            @RequestBody UpdateUserRequestDto request) {

        // استدعاء الخدمة للتحديث
        String jsonResult = userService.updateUser(id, request);

        // لو الـ JSON راجع فاضي {}، يعني المستخدم مش موجود
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"User not found\"}");
        }

        // إرجاع 200 OK مع تفاصيل المستخدم بعد التعديل
        return ResponseEntity.ok(jsonResult);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody AddUserRequestDto request) {

        // استدعاء الخدمة للإضافة
        String jsonResult = userService.addUser(request);

        // إرجاع 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonResult);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {

        // استدعاء الخدمة لحذف المستخدم
        String jsonResult = userService.deleteUser(id);

        // لو الـ JSON راجع فاضي {}، يعني المستخدم مش موجود
        if ("{}".equals(jsonResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"User not found\"}");
        }

        // لو الداتا بيز اعترضت عشان المستخدم مرتبط بحجوزات (بنرجع 409 Conflict)
        if (jsonResult.contains("\"success\": false")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResult);
        }

        // إرجاع 200 OK مع رسالة التأكيد
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchUsers(
            @RequestParam(value = "q", defaultValue = "") String q) {

        // استدعاء الخدمة للبحث في المستخدمين
        String jsonResult = userService.searchUsers(q);

        // إرجاع 200 OK مع مصفوفة النتائج
        return ResponseEntity.ok(jsonResult);
    }
}