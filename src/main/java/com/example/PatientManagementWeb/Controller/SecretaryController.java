package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;
import com.example.PatientManagementWeb.Service.SecretaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secretaries")
public class SecretaryController {

    private final SecretaryService secretaryService;

    @GetMapping
    public ResponseEntity<List<SecretaryDTO>> getAllSecretaries() {
        List<SecretaryDTO> secretaries = secretaryService.getAllSecretaries();
        return new ResponseEntity<>(secretaries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretaryDTO> getSecretary(@PathVariable String id) {
        return new ResponseEntity<>(secretaryService.getSecretary(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createSecretary(@RequestBody @Valid SecretaryDTO secretaryDTO) {
        secretaryService.createSecretary(secretaryDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecretary(@PathVariable String id) {
        secretaryService.deleteSecretary(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSecretary(@PathVariable String id, @RequestBody @Valid  SecretaryDTO secretaryDTO) {
        secretaryService.updateSecretary(secretaryDTO,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
