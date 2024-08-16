package api.medical.com.ApiMedica.DTO.PacienteDTO;

import api.medical.com.ApiMedica.DTO.EnderecosDto.DadosEnderecosDTO;
import api.medical.com.ApiMedica.Infra.Unchangeable;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public record DadosAtualizaPacienteDTO(
        @NotNull(message = "Id obrigatorio")
        Long id,
        String nome,
        String telefone,
        @Unchangeable
        String cpf,
        @Unchangeable
        String email,
        DadosEnderecosDTO endereco) {


}

