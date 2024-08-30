package api.medical.com.ApiMedica.DTO.ConsultaDTO.Cancelamento;


import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosCancelamentoConsultaDTO;
import api.medical.com.ApiMedica.Repositorio.Consulta.ConsultaRepositorio;
import api.medical.com.ApiMedica.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepositorio repository;

    @Override
    public void validar(DadosCancelamentoConsultaDTO dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}