package api.medical.com.ApiMedica.DTO;

import api.medical.com.ApiMedica.ENUM.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.format.annotation.NumberFormat;

public record DadosCadastroMedicoDTO(
        @NotBlank //valida se esta nulo ou vazio
        String nome,

        @NotBlank
        @Email //valida email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //valida o crm
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull @Valid DadosEnderecosDTO endereco

) {
}
