package api.medical.com.ApiMedica.ValidacaoConsulta;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica  implements Validador {

    public void validar (DadosAgendamentoConsultaDTO dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbeturaDaClinica = dataConsulta.getHour()<7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour()>18;

        if (domingo || antesDaAbeturaDaClinica||depoisDoEncerramentoDaClinica){
            throw new ValidationException("Consulta fora do horario de funcionamento da clinica!");
        }
    }


}
