package com.sensei.apitabibiadminpanel.DTO;

public record NotificationRequestDto(
        String title,
        String message,
        String targetRole // "All", "Doctor", or "Patient"
) {}