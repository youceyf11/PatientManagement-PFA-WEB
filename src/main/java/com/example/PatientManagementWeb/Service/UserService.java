package com.example.PatientManagementWeb.Service;

import com.example.PatientManagementWeb.DTO.PasswordDTO;
import com.example.PatientManagementWeb.DTO.UsernameDTO;
import com.example.PatientManagementWeb.Entity.User;
import com.example.PatientManagementWeb.Exceptions.IncorrectPassword;
import com.example.PatientManagementWeb.Exceptions.InvalidUsername;
import com.example.PatientManagementWeb.Exceptions.UserNotFoundException;
import com.example.PatientManagementWeb.IService.IUserService;
import com.example.PatientManagementWeb.Repository.UserRepository;
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
    public void updatePassword(String id, PasswordDTO passwordDTO) {
        User user= userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        if(passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
        }else {
            throw new IncorrectPassword("Password not match");
        }
    }

    @Override
    public void updateUserName(String id, UsernameDTO usernameDTO) {
        User user= userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        if(userRepository.existsByUsername(usernameDTO.getNewUserName())){
            throw new InvalidUsername("Username already exists");
        }else {
            user.setUsername(usernameDTO.getNewUserName());
            userRepository.save(user);
        }
    }
}
