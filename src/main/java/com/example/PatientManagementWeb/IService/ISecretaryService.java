package com.example.PatientManagementWeb.IService;

import com.example.PatientManagementWeb.Entity.Medecin;
import com.example.PatientManagementWeb.Entity.Secretary;

import java.util.List;
import java.util.UUID;

public interface ISecretaryService {

    List<Secretary> findAllSecretaries();
    Secretary findSecretaryById(UUID id);
    Secretary createSecretary(Secretary secretary);
    Secretary updateSecretary(Secretary secretary);
    void deleteSecretary(UUID id);
}
