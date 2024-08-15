package api.medical.com.ApiMedica.DTO.MedicoDto;

import api.medical.com.ApiMedica.DTO.EnderecosDto.DadosEnderecosDTO;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedicoDTO(
                                    @NotNull(message = "Id obrigatorio")
                                      Long id,
                                      String nome,
                                      String telefone,
                                      DadosEnderecosDTO endereco) {
}
