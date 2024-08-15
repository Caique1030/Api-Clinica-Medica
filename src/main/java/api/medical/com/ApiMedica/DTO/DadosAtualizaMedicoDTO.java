package api.medical.com.ApiMedica.DTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedicoDTO(
                                    @NotNull
                                      Long id,
                                      String nome,
                                      String telefone,
                                      DadosEnderecosDTO endereco) {
}
