package api.medical.com.ApiMedica.DTO.ConsultaDTO;

import api.medical.com.ApiMedica.Modals.Consulta.Consulta;

import java.time.LocalDateTime;

public record  DadosDetalhamentoConsultaDTO(Long id, Long idMedico,Long idPaciente, LocalDateTime data) {

    public DadosDetalhamentoConsultaDTO(Consulta consulta) {
        this(consulta.getId(),consulta.getMedico().getId(),
                consulta.getPaciente().getId(),consulta.getData());
    }
}
