package com.example.PatientManagementWeb.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String createdAt;
    private String updatedAt;

}
