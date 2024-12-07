package com.se.ssps.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Long studentNumber; // MSSV
}
