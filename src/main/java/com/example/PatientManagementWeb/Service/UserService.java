package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.DTO.PasswordDTO;
import com.example.PatientManagementWeb.DTO.UsernameDTO;
import com.example.PatientManagementWeb.Entity.User;
import com.example.PatientManagementWeb.Enum.ErrorCode;
import com.example.PatientManagementWeb.Exceptions.ProfessionalException;
import com.example.PatientManagementWeb.IService.IUserService;
import com.example.PatientManagementWeb.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void updatePassword(String id, PasswordDTO passwordDTO) {
        User user= userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ProfessionalException(ErrorCode.USER_NOT_FOUND));
        System.out.println("mal9a walo hna ");
        if(passwordDTO.getNewPassword() ==null || passwordDTO.getNewPassword().trim().isEmpty()){
            throw new ProfessionalException(ErrorCode.VALIDATION_ERROR);
        }
        if(passwordDTO.getOldPassword() ==null || passwordDTO.getOldPassword().trim().isEmpty()){
            throw new ProfessionalException(ErrorCode.VALIDATION_ERROR);
        }

        if (!passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            throw new ProfessionalException(ErrorCode.AUTHENTICATION_FAILED, "L'ancien mot de passe est incorrect");
        }
        if(passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
        }else {
            throw new ProfessionalException(ErrorCode.PASSWORD_ENCODING_ERROR);
        }
    }

    @Override
    @Transactional
    public void updateUserName(String id, UsernameDTO usernameDTO) {
        User user= userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ProfessionalException(ErrorCode.USER_NOT_FOUND));
        if(userRepository.existsByUsername(usernameDTO.getNewUserName())){
            throw new ProfessionalException(ErrorCode.USER_ALREADY_EXISTS);
        }else {
            user.setUsername(usernameDTO.getNewUserName());
            userRepository.save(user);
        }
    }
}
