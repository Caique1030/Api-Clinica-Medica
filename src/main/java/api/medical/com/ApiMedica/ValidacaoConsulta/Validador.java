package api.medical.com.ApiMedica.ValidacaoConsulta;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;

public interface Validador {
    void validar(DadosAgendamentoConsultaDTO dados);
}
