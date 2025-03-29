package com.example.PatientManagementWeb.IService;


import com.example.PatientManagementWeb.DTO.PasswordDTO;
import com.example.PatientManagementWeb.DTO.UsernameDTO;

public interface IUserService {

    void updatePassword(String id, PasswordDTO passwordDTO);
    void updateUserName(String id, UsernameDTO usernameDTO);
}
