package com.example.PatientManagementWeb.mapper;

import com.example.PatientManagementWeb.DTO.MedecinDTO;
import com.example.PatientManagementWeb.entity.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface MedecinMapper {


    MedecinDTO toDTO(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);

    @Mapping(target = "id", ignore = true)
    void updateMedecinFromDTO(MedecinDTO medecinDTO, @MappingTarget Medecin medecin);
}
