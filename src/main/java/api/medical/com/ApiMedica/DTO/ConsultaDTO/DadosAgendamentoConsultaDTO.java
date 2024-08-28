package api.medical.com.ApiMedica.DTO.ConsultaDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record  DadosAgendamentoConsultaDTO(
    
    Long idMedico,
    @NotNull
    Long idPaciente,
    @NotNull
    @Future
    LocalDateTime data
) {
    
}
