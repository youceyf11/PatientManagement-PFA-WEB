package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.DTO.PasswordDTO;
import com.example.PatientManagementWeb.DTO.UsernameDTO;
import com.example.PatientManagementWeb.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable String id, @RequestBody @Valid PasswordDTO passwordDTO) {
        userService.updatePassword(id, passwordDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/username/{id}")
    public ResponseEntity<Void> updateUserName(@PathVariable String id, @RequestBody @Valid UsernameDTO usernameDTO) {
        userService.updateUserName(id, usernameDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
