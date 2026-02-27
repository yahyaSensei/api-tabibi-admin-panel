package com.sensei.apitabibiadminpanel.DTO;

import lombok.Data;

@Data
public class UserSummaryDto {
    private String id;
    private String name;
    private String email;
    private String avatarUrl;
    private String discriminator; // لمعرفة هل هو مريض أم طبيب
}