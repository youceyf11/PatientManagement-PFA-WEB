package com.example.PatientManagementWeb.mapper;

import com.example.PatientManagementWeb.DTO.SecretaryDTO;
import com.example.PatientManagementWeb.entity.Secretary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SecretaryMapper {
    SecretaryDTO toDTO(Secretary secretary);
    Secretary toEntity(SecretaryDTO secretaryDTO);

    @Mapping(target = "id", ignore = true)
    void updateSecretaryFromDTO(SecretaryDTO secretaryDTO, @MappingTarget Secretary secretary);
}
