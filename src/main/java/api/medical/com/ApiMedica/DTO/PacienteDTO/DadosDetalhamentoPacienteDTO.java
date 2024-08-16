package api.medical.com.ApiMedica.DTO.PacienteDTO;

import api.medical.com.ApiMedica.ENUM.Especialidade;
import api.medical.com.ApiMedica.Modals.Enderecos.Endereco;
import api.medical.com.ApiMedica.Modals.Paciente.Paciente;

public record DadosDetalhamentoPacienteDTO(Long id,
                                           String nome,
                                           String email,
                                           String cpf,
                                           String telefone,
                                           Endereco endereco) {

    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEndereco());
    }
}
