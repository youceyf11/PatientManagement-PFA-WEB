package com.example.PatientManagementWeb.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "firstname is mandatory")
    private String firstName;

    @NotBlank(message = "lastname is mandatory")
    private String lastName;

    @NotBlank(message = "phone is mandatory")
    private String phone;

    private String createdAt;
    private String updatedAt;

}
