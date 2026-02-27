package com.sensei.apitabibiadminpanel.DTO;

import java.math.BigDecimal;

public record BookingRequestDto(
        String patientId,
        String doctorId,
        String appointmentDate, // هتتبعت بصيغة ISO: "2026-03-01T14:30:00"
        int status,
        int type,
        BigDecimal pricePaid,
        String paymentIntentId
) {}