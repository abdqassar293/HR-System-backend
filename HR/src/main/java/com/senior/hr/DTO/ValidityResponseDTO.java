package com.senior.hr.DTO;

import lombok.Data;

@Data
public class ValidityResponseDTO {
    private Boolean isValid;
    private String username;
    private String Role;
}
