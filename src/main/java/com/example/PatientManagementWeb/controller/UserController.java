package com.example.PatientManagementWeb.controller;

import com.example.PatientManagementWeb.DTO.ApiResponse;
import com.example.PatientManagementWeb.DTO.PasswordDTO;
import com.example.PatientManagementWeb.DTO.UsernameDTO;
import com.example.PatientManagementWeb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @PutMapping("/password/{id}")
    public ResponseEntity<ApiResponse<Void>> updatePassword(@PathVariable String id, @RequestBody @Valid PasswordDTO passwordDTO) {
        userService.updatePassword(id, passwordDTO);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Mot de passe mis à jour avec succès",
                HttpStatus.NO_CONTENT
        );

        // Log avec conversion du timestamp
        LocalDateTime updateTime = response.getLocalDateTime();
        System.out.println("Mot de passe de l'utilisateur " + id + " mis à jour le " +
                updateTime.format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/username/{id}")
    public ResponseEntity<ApiResponse<Void>> updateUserName(@PathVariable String id, @RequestBody @Valid UsernameDTO usernameDTO) {
        userService.updateUserName(id, usernameDTO);
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Nom d'utilisateur mis à jour avec succès",
                HttpStatus.NO_CONTENT
        );

        // Log avec conversion du timestamp
        LocalDateTime updateTime = response.getLocalDateTime();
        System.out.println("Nom d'utilisateur de l'utilisateur " + id + " mis à jour le " +
                updateTime.format(formatter));

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}