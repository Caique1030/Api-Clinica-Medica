package api.medical.com.ApiMedica.Controller.ConsultaController;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosAgendamentoConsultaDTO;
import api.medical.com.ApiMedica.DTO.ConsultaDTO.DadosDetalhamentoConsultaDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dados){
        return  ResponseEntity.ok(new DadosDetalhamentoConsultaDTO(null,null,null,null));
    }


}
