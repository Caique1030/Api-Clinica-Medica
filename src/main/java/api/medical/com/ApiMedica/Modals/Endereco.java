package api.medical.com.ApiMedica.Modals;

import api.medical.com.ApiMedica.DTO.DadosEnderecosDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter //gera metodos getter
@NoArgsConstructor // gera os construtores default
@AllArgsConstructor // gera construtur em todos os campos
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEnderecosDTO dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarInformacoes(DadosEnderecosDTO dados) {

            if (dados.logradouro() != null) {
                this.logradouro = dados.logradouro();
            }
            if (dados.bairro() != null) {
                this.bairro = dados.bairro();
            }
            if (dados.cep() != null) {
                this.cep = dados.cep();
            }
            if (dados.uf() != null) {
                this.uf = dados.uf();
            }
            if (dados.cidade() != null) {
                this.cidade = dados.cidade();
            }
            if (dados.numero() != null) {
                this.numero = dados.numero();
            }
            if (dados.complemento() != null) {
                this.complemento = dados.complemento();
            }
        }
    }
