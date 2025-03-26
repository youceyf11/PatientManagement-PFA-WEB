package com.example.PatientManagementWeb.Controller;

import com.example.PatientManagementWeb.Entity.Secretary;
import com.example.PatientManagementWeb.IService.ISecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/secretaries")

public class SecretaryController {

    @Autowired
    private ISecretaryService secretaryService;

    @GetMapping
    public List<Secretary> getAllSecretaries(){
        return secretaryService.findAllSecretaries();
    }

    @GetMapping("/{id}")
    public Secretary getSecretaryById(@PathVariable UUID id){
        return secretaryService.findSecretaryById(id);
    }

    @PostMapping
    public Secretary createSecretary(@RequestBody Secretary secretary){
        return secretaryService.createSecretary(secretary);
    }

    @PutMapping
    public Secretary updateSecretary(@RequestBody Secretary secretary){
        return secretaryService.updateSecretary(secretary);
    }

    @DeleteMapping("/{id}")
    public void deleteSecretary(@PathVariable UUID id){
        secretaryService.deleteSecretary(id);
    }

}
