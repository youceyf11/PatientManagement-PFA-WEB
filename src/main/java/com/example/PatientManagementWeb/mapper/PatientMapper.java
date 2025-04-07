package com.example.PatientManagementWeb.mapper;

import com.example.PatientManagementWeb.DTO.PatientDTO;
import com.example.PatientManagementWeb.Entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PatientMapper {
    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);

    @Mapping(target = "id", ignore = true)
    void updatePatientFromDTO(PatientDTO patientDTO, @MappingTarget Patient patient);
}
