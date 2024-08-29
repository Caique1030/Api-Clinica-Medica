package api.medical.com.ApiMedica.ValidacaoConsulta;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import api.medical.com.ApiMedica.Repositorio.Consulta.ConsultaRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaMesmaHora implements Validador{
    @Autowired
    private ConsultaRepositorio repositorio;

    public void validar(DadosAgendamentoConsultaDTO dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repositorio.existsByMedicoIdAndData(dados.idMedico(),dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario){
            throw  new ValidationException("Medico já possui outra consulta nesse mesmo horario");
        }
    }
}
