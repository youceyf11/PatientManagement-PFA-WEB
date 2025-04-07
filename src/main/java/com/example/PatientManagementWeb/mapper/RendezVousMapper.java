package com.example.PatientManagementWeb.mapper;

import com.example.PatientManagementWeb.DTO.RendezVousDTO;
import com.example.PatientManagementWeb.Entity.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface RendezVousMapper {

    RendezVousDTO toDTO(RendezVous rendezVous);
    RendezVous toEntity(RendezVousDTO rendezVousDTO);

    @Mapping(target = "id", ignore = true)
    void updateRendezVousFromDTO(RendezVousDTO rendezVousDTO , @MappingTarget RendezVous rendezVous);
}
