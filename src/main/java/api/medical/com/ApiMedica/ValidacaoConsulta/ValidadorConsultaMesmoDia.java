package api.medical.com.ApiMedica.ValidacaoConsulta;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import api.medical.com.ApiMedica.Repositorio.Consulta.ConsultaRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaMesmoDia implements Validador{
    @Autowired
    private ConsultaRepositorio repositorio;

    public void validar(DadosAgendamentoConsultaDTO dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNaMesmaData = repositorio.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if (pacientePossuiOutraConsultaNaMesmaData){
            throw  new ValidationException("Paciente já possui outra consulta nesse mesmo Dia");
        }
    }
}
