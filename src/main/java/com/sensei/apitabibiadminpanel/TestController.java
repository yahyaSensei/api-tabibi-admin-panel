package com.sensei.apitabibiadminpanel;

import com.sensei.apitabibiadminpanel.DTO.CityDto;
import com.sensei.apitabibiadminpanel.DTO.ClinicDto;
import com.sensei.apitabibiadminpanel.DTO.DepartmentDto;
import com.sensei.apitabibiadminpanel.DTO.UserSummaryDto;
import com.sensei.apitabibiadminpanel.entities.core.Clinic;
import com.sensei.apitabibiadminpanel.entities.shared.City;
import com.sensei.apitabibiadminpanel.entities.shared.Department;
import com.sensei.apitabibiadminpanel.repository.core.ClinicRepository;
import com.sensei.apitabibiadminpanel.repository.shared.CityRepository;
import com.sensei.apitabibiadminpanel.repository.shared.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final CityRepository cityRepository;
    private final DepartmentRepository departmentRepository;
    private final ClinicRepository clinicRepository;

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityRepository.findAll().stream()
                .map(this::mapToCityDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentRepository.findAll().stream()
                .map(this::mapToDepartmentDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/clinics")
    public ResponseEntity<List<ClinicDto>> getAllClinics() {
        List<ClinicDto> clinics = clinicRepository.findAll().stream()
                .map(this::mapToClinicDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clinics);
    }

    // ==========================================
    // دوال التحويل (Mappers) من Entity إلى DTO
    // ==========================================

    private CityDto mapToCityDto(City city) {
        CityDto dto = new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }

    private DepartmentDto mapToDepartmentDto(Department dept) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(dept.getId());
        dto.setName(dept.getName());
        dto.setDescription(dept.getDescription());
        dto.setImageUrl(dept.getImageUrl());
        return dto;
    }

    private ClinicDto mapToClinicDto(Clinic clinic) {
        ClinicDto dto = new ClinicDto();
        dto.setId(clinic.getId());
        dto.setName(clinic.getName());
        dto.setAddress(clinic.getAddress());
        dto.setPhoneNumber(clinic.getPhoneNumber());
        dto.setLatitude(clinic.getLatitude());
        dto.setLongitude(clinic.getLongitude());
        dto.setDescription(clinic.getDescription());

        // جلب اسم المدينة بأمان
        if (clinic.getCity() != null) {
            dto.setCityName(clinic.getCity().getName());
        }

        // جلب بيانات الطبيب صاحب العيادة
        if (clinic.getDoctor() != null) {
            UserSummaryDto doctorDto = new UserSummaryDto();
            doctorDto.setId(clinic.getDoctor().getId());
            doctorDto.setName(clinic.getDoctor().getName());
            doctorDto.setEmail(clinic.getDoctor().getEmail());
            doctorDto.setAvatarUrl(clinic.getDoctor().getAvatarUrl());
            doctorDto.setDiscriminator(clinic.getDoctor().getDiscriminator());
            dto.setDoctor(doctorDto);
        }

        return dto;
    }
}