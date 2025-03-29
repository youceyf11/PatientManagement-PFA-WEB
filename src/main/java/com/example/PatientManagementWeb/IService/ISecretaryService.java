package com.example.PatientManagementWeb.IService;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;


public interface ISecretaryService {


    SecretaryDTO getSecretary(String id);
    void createSecretary(SecretaryDTO secretaryDTO);
    void updateSecretary(SecretaryDTO secretaryDTO, String id);
    void deleteSecretary(String id);


}
