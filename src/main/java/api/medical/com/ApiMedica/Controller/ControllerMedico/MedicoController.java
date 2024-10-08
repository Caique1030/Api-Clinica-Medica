package api.medical.com.ApiMedica.Controller.ControllerMedico;

import api.medical.com.ApiMedica.DTO.MedicoDto.DadosAtualizaMedicoDTO;
import api.medical.com.ApiMedica.DTO.MedicoDto.DadosCadastroMedicoDTO;
import api.medical.com.ApiMedica.DTO.MedicoDto.DadosDetalhamentoMedicoDTO;
import api.medical.com.ApiMedica.DTO.MedicoDto.DadosListagemMedicoDTO;
import api.medical.com.ApiMedica.Modals.Medico.Medico;
import api.medical.com.ApiMedica.Repositorio.Medico.MedicoRepositorio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepositorio repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repositorio.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));

    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicos(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        var page = repositorio.findAllByAtivoTrue(paginacao)
                                .map(DadosListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizaMedicoDTO dados){
        var medico = repositorio.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repositorio.getReferenceById(id);
        medico.excluir();
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repositorio.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

}
