package com.example.PatientManagementWeb.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordDTO{
    @NotBlank(message = "old password is mandatory")
    private String oldPassword;

    @NotBlank(message = "new password is mandatory")
    private String newPassword;

}
