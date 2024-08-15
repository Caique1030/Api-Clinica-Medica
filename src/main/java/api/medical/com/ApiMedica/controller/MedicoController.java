package api.medical.com.ApiMedica.controller;

import api.medical.com.ApiMedica.DTO.DadosAtualizaMedicoDTO;
import api.medical.com.ApiMedica.DTO.DadosCadastroMedicoDTO;
import api.medical.com.ApiMedica.DTO.DadosEnderecosDTO;
import api.medical.com.ApiMedica.DTO.DadosListagemMedicoDTO;
import api.medical.com.ApiMedica.Modals.Medico;
import api.medical.com.ApiMedica.Repositorio.MedicoRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepositorio repositorio;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados){
        repositorio.save(new Medico(dados));
    }
    @GetMapping
    public Page<DadosListagemMedicoDTO> listarMedicos(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        return  repositorio.findAllByAtivoTrue(paginacao)
                                            .map(DadosListagemMedicoDTO::new);
    }
    @PutMapping
    @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizaMedicoDTO dados){
        var medico = repositorio.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repositorio.getReferenceById(id);
        medico.excluir();
    }

}
