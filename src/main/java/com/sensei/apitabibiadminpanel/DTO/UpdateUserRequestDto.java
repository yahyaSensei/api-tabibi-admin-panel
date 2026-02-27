package com.sensei.apitabibiadminpanel.DTO;


import java.math.BigDecimal;

public record UpdateUserRequestDto(
        String name,
        String phoneNumber,
        Integer gender,
        String dateOfBirth, // "YYYY-MM-DD"
        String cityId,
        String bio,
        BigDecimal consultationFee,
        String departmentId,
        Integer yearsOfExperience,
        Integer status
) {}