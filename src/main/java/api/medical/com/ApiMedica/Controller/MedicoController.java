package api.medical.com.ApiMedica.Controller;

import api.medical.com.ApiMedica.DTO.*;
import api.medical.com.ApiMedica.Modals.Medico;
import api.medical.com.ApiMedica.Repositorio.MedicoRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
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
    public ResponseEntity Detalhar(@PathVariable Long id){
        var medico = repositorio.getReferenceById(id);
        return  ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

}
