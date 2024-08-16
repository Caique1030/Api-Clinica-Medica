package api.medical.com.ApiMedica.DTO.PacienteDTO;

import api.medical.com.ApiMedica.ENUM.Especialidade;
import api.medical.com.ApiMedica.Modals.Paciente.Paciente;

public record DadosListagemPacienteDTO(Long id, String nome, String email, String cpf ) {
    public DadosListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
