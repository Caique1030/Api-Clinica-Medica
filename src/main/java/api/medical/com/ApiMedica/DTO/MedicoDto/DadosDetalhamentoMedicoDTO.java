package api.medical.com.ApiMedica.DTO.MedicoDto;

import api.medical.com.ApiMedica.ENUM.Especialidade;
import api.medical.com.ApiMedica.Modals.Enderecos.Endereco;
import api.medical.com.ApiMedica.Modals.Medico.Medico;

public record DadosDetalhamentoMedicoDTO(Long id,
                                         String nome,
                                         String email,
                                         String crm,
                                         String telefone,
                                         Especialidade especialidade,
                                         Endereco endereco) {

    public DadosDetalhamentoMedicoDTO (Medico medico) {
        this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}