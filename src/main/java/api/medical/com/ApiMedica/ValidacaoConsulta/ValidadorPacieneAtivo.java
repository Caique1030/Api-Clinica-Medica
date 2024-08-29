package api.medical.com.ApiMedica.ValidacaoConsulta;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import api.medical.com.ApiMedica.Repositorio.Paciente.PacienteRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacieneAtivo implements Validador{

    @Autowired
    private PacienteRepositorio repositorio;
    public void validar(DadosAgendamentoConsultaDTO dados){
        if(dados.idPaciente() == null){
            return;
        }
        var pacienteEstaAtivo = repositorio.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidationException("Não e possivel agendar consulta com um medico inativo");
        }
    }
}
