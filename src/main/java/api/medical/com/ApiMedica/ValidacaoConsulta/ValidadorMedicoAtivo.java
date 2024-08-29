package api.medical.com.ApiMedica.ValidacaoConsulta;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import api.medical.com.ApiMedica.Repositorio.Medico.MedicoRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements Validador{
    @Autowired
    private MedicoRepositorio repositorio;
    public void validar(DadosAgendamentoConsultaDTO dados){
        if(dados.idMedico() == null){
            return;
        }
        var medicoEstaAtivo = repositorio.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidationException("Não e possivel agendar consulta com um medico inativo");
        }

    }
}
