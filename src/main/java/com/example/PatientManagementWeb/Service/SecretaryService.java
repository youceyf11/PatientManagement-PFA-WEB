package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.Entity.Secretary;
import com.example.PatientManagementWeb.IService.ISecretaryService;
import com.example.PatientManagementWeb.Repository.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SecretaryService implements ISecretaryService {

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Override
    public List<Secretary> findAllSecretaries() {
        return secretaryRepository.findAll();
    }

    @Override
    public Secretary findSecretaryById(UUID id) {
        return secretaryRepository.findById(id).orElseThrow(() -> new RuntimeException("Secretary not found"));
    }


    @Override
    public Secretary createSecretary(Secretary secretary) {
        return secretaryRepository.save(secretary);
    }

    @Override
    public Secretary updateSecretary(Secretary secretary) {
        UUID id = secretary.getId();
        if(id == null || !secretaryRepository.existsById(id)) {
            throw new RuntimeException("Secretary not found with id:" + id);
        }
        return secretaryRepository.save(secretary);
    }

    @Override
    public void deleteSecretary(UUID id) {
        secretaryRepository.deleteById(id);
    }


}
