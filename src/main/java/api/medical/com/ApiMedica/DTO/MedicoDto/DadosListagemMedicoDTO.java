package api.medical.com.ApiMedica.DTO.MedicoDto;


import api.medical.com.ApiMedica.ENUM.Especialidade;
import api.medical.com.ApiMedica.Modals.Medico.Medico;

public record DadosListagemMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}