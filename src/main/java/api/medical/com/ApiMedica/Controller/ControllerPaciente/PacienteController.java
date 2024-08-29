package api.medical.com.ApiMedica.Controller.ControllerPaciente;

import api.medical.com.ApiMedica.DTO.PacienteDTO.DadosAtualizaPacienteDTO;
import api.medical.com.ApiMedica.DTO.PacienteDTO.DadosCadastraPacienteDTO;
import api.medical.com.ApiMedica.DTO.PacienteDTO.DadosDetalhamentoPacienteDTO;
import api.medical.com.ApiMedica.DTO.PacienteDTO.DadosListagemPacienteDTO;
import api.medical.com.ApiMedica.Modals.Paciente.Paciente;
import api.medical.com.ApiMedica.Repositorio.Paciente.PacienteRepositorio;
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
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepositorio repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastraPacienteDTO dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        repositorio.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDTO>> ListarPacientes(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        var page = repositorio.findAllByAtivoTrue(paginacao)
                .map(DadosListagemPacienteDTO::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizaPacienteDTO dados){
        var paciente = repositorio.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
    }
    @GetMapping("/{id}")
    public ResponseEntity Detalhar(@PathVariable Long id){
        var medico = repositorio.getReferenceById(id);
        return  ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(medico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repositorio.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }
}
