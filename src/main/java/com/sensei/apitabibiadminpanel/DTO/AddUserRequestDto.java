package com.sensei.apitabibiadminpanel.DTO;

import java.math.BigDecimal;

public record AddUserRequestDto(
        String name,
        String email,
        String phoneNumber,
        String role, // "Doctor" or "Patient"
        Integer gender,
        String dateOfBirth,
        String cityId,
        String bio,
        BigDecimal consultationFee,
        String departmentId,
        Integer yearsOfExperience,
        Integer status
) {}