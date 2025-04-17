package com.example.PatientManagementWeb.mapper;

import com.example.PatientManagementWeb.DTO.UserDTO;
import com.example.PatientManagementWeb.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface UserMapper {
    UserDTO ToDTO(User user);
    User ToEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDTO(UserDTO userDTO,@MappingTarget User user);
}
