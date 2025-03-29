package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;
import com.example.PatientManagementWeb.Service.SecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secretaries")
public class SecretaryController {

    private final SecretaryService secretaryService;

    @GetMapping("/{id}")
    public ResponseEntity<SecretaryDTO> getSecretary(@PathVariable String id) {
        return new ResponseEntity<>(secretaryService.getSecretary(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SecretaryDTO> createSecretary(@RequestBody SecretaryDTO secretaryDTO) {
        secretaryService.createSecretary(secretaryDTO);
        return new ResponseEntity<>(secretaryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SecretaryDTO> deleteSecretary(@PathVariable String id) {
        secretaryService.deleteSecretary(id);
        return new ResponseEntity<>(secretaryService.getSecretary(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecretaryDTO> updateSecretary(@PathVariable String id, @RequestBody SecretaryDTO secretaryDTO) {
        secretaryService.updateSecretary(secretaryDTO,id);
        return new ResponseEntity<>(secretaryDTO, HttpStatus.NO_CONTENT);
    }




}
