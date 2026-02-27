package com.sensei.apitabibiadminpanel.DTO;

import lombok.Data;
import java.util.UUID;

@Data
public class DepartmentDto {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
}