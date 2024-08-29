package api.medical.com.ApiMedica.DTO.ConsultaDTO;

import java.time.LocalDateTime;

import api.medical.com.ApiMedica.ENUM.Especialidade;
import api.medical.com.ApiMedica.Modals.Consulta.Consulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record  DadosAgendamentoConsultaDTO(
    Long idMedico,

    @NotNull
    Long idPaciente,

    @NotNull
    @Future
    LocalDateTime data,
    Especialidade especialidade
) {


}
