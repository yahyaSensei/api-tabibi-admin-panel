package com.sensei.apitabibiadminpanel.DTO;

import lombok.Data;

@Data
public class ClinicDto {
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private String description;

    // بدلاً من إرجاع كائن City كامل، نرجع اسم المدينة فقط أو الـ DTO الخاص بها
    private String cityName;

    // بيانات الطبيب صاحب العيادة
    private UserSummaryDto doctor;
}