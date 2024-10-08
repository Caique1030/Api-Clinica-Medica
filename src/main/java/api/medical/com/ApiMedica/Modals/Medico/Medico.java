package api.medical.com.ApiMedica.Modals.Medico;

import api.medical.com.ApiMedica.DTO.MedicoDto.DadosAtualizaMedicoDTO;
import api.medical.com.ApiMedica.DTO.MedicoDto.DadosCadastroMedicoDTO;
import api.medical.com.ApiMedica.ENUM.Especialidade;
import api.medical.com.ApiMedica.Modals.Enderecos.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medico")
@Table(name = "Medicos") //cria tabela no banco
@Getter /*gera metodos getter*/
@NoArgsConstructor // gera os construtores default
@AllArgsConstructor // gera construtores em todos os campos
@EqualsAndHashCode(of = "id") //gera os equals e os hashcode
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;
    public Medico(DadosCadastroMedicoDTO dados) {
        this.ativo=true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
    public void atualizarInformacoes(DadosAtualizaMedicoDTO dados){
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo =false;
    }
}
