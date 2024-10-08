package api.medical.com.ApiMedica.Controller.ConsultaController;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosCancelamentoConsultaDTO;
import api.medical.com.ApiMedica.Modals.Consulta.Consulta;
import api.medical.com.ApiMedica.Service.AgendamentoConsulta.AgendaDeConsultas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosDetalhamentoConsultaDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultas agendamento;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dados) {
        var dto = agendamento.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsultaDTO dados) {
        agendamento.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
