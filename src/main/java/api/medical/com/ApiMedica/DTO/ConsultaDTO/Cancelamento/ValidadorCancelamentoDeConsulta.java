package api.medical.com.ApiMedica.DTO.ConsultaDTO.Cancelamento;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosCancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsultaDTO dados);
}
