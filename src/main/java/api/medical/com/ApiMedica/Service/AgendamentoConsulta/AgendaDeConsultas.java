package api.medical.com.ApiMedica.Service.AgendamentoConsulta;


import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosDetalhamentoConsultaDTO;
import api.medical.com.ApiMedica.ValidacaoConsulta.Validador;
import api.medical.com.ApiMedica.Modals.Consulta.Consulta;
import api.medical.com.ApiMedica.Modals.Medico.Medico;
import api.medical.com.ApiMedica.Repositorio.Consulta.ConsultaRepositorio;
import api.medical.com.ApiMedica.Repositorio.Medico.MedicoRepositorio;
import api.medical.com.ApiMedica.Repositorio.Paciente.PacienteRepositorio;
import api.medical.com.ApiMedica.ValidacaoException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepositorio consultaRepository;

    @Autowired
    private MedicoRepositorio medicoRepository;

    @Autowired
    private PacienteRepositorio pacienteRepository;

    @Autowired
    private List<Validador> validadores;

    public DadosDetalhamentoConsultaDTO agendar(DadosAgendamentoConsultaDTO dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsultaDTO(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

}
