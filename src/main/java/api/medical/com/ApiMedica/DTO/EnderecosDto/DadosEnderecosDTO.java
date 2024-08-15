package api.medical.com.ApiMedica.DTO.EnderecosDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecosDTO(
        @NotBlank(message = "Logradouro Obrigatorio")
        String logradouro,
        @NotBlank(message = "Bairro Obrigatorio")
        String bairro,
        @NotBlank(message = "Cep Obrigatorio")
                @Pattern(regexp = "\\d{8}",message = "Cep invalido")
        String cep,
        @NotBlank(message = "Cidade Obrigatoria")
        String cidade,
        @NotBlank(message = "UF Obrigatorio")
        String uf,
        String complemento,
        String numero
) {
}
