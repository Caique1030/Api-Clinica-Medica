package api.medical.com.ApiMedica.DTO.ConsultaDTO;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultaDTO(@NotNull
                                           Long idConsulta,

                                           @NotNull
                                           MotivoCancelamento motivo) {
}
