package com.example.PatientManagementWeb.iservice;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;

import java.util.List;


public interface ISecretaryService {


    List<SecretaryDTO> getAllSecretaries();
    SecretaryDTO getSecretary(String id);
    String createSecretary(SecretaryDTO secretaryDTO);
    void updateSecretary(SecretaryDTO secretaryDTO, String id);
    void deleteSecretary(String id);


}
