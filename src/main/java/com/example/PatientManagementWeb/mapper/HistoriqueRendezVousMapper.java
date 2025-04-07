package com.example.PatientManagementWeb.mapper;

import com.example.PatientManagementWeb.DTO.HistoriqueRendezVousDTO;
import com.example.PatientManagementWeb.Entity.HistoriqueRendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface HistoriqueRendezVousMapper {
    HistoriqueRendezVousDTO toDTO(HistoriqueRendezVous historiqueRendezVous);
    HistoriqueRendezVous toEntity(HistoriqueRendezVousDTO historiqueRendezVousDTO);

    @Mapping(target = "id", ignore = true)
    void updateRendezVousFromDTO(HistoriqueRendezVousDTO historiqueRendezVousDTO, @MappingTarget HistoriqueRendezVous historiqueRendezVous);
}
