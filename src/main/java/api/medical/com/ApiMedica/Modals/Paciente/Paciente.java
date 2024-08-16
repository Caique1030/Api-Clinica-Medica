package api.medical.com.ApiMedica.Modals.Paciente;

import api.medical.com.ApiMedica.DTO.MedicoDto.DadosAtualizaMedicoDTO;
import api.medical.com.ApiMedica.DTO.PacienteDTO.DadosAtualizaPacienteDTO;
import api.medical.com.ApiMedica.DTO.PacienteDTO.DadosCadastraPacienteDTO;
import api.medical.com.ApiMedica.Modals.Enderecos.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Paciente")
@Table(name = "Pacientes") //cria tabela no banco
@Getter /*gera metodos getter*/
@NoArgsConstructor // gera os construtores default
@AllArgsConstructor // gera construtores em todos os campos
@EqualsAndHashCode(of = "id") //gera os equals e os hashcode
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;
    public Paciente(DadosCadastraPacienteDTO dados) {
        this.ativo=true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizaPacienteDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.cpf() != null && !dados.cpf().equals(this.cpf)) {
            throw new IllegalArgumentException("O CPF não pode ser alterado.");
        }

        if (dados.email() != null && !dados.email().equals(this.email)) {
            throw new IllegalArgumentException("O email não pode ser alterado.");
        }

        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo=false;
    }
}
