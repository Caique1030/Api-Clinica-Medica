package api.medical.com.ApiMedica.DTO.PacienteDTO;

import api.medical.com.ApiMedica.DTO.EnderecosDto.DadosEnderecosDTO;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPacienteDTO(
        @NotNull(message = "Id obrigatorio")
        Long id,
        String nome,
        String telefone,
        String cpf,
        String email,
        DadosEnderecosDTO endereco) {


}

