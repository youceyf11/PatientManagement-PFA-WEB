package com.example.PatientManagementWeb.IService;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;

import java.util.List;


public interface ISecretaryService {


    List<SecretaryDTO> getAllSecretaries();
    SecretaryDTO getSecretary(String id);
    void createSecretary(SecretaryDTO secretaryDTO);
    void updateSecretary(SecretaryDTO secretaryDTO, String id);
    void deleteSecretary(String id);


}
