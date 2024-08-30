package api.medical.com.ApiMedica.Repositorio.Medico;

import api.medical.com.ApiMedica.DTO.EnderecosDto.DadosEnderecosDTO;
import api.medical.com.ApiMedica.DTO.MedicoDto.DadosCadastroMedicoDTO;
import api.medical.com.ApiMedica.DTO.PacienteDTO.DadosCadastraPacienteDTO;
import api.medical.com.ApiMedica.ENUM.Especialidade;
import api.medical.com.ApiMedica.Modals.Consulta.Consulta;
import api.medical.com.ApiMedica.Modals.Medico.Medico;
import api.medical.com.ApiMedica.Modals.Paciente.Paciente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositorioTest {

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deveria null quando unico medico cadastrado não esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico= cadastrarMedico("Medico","medico@voll.com","458659",Especialidade.CLINICO);
        var paciente =cadastrarPaciente("Paciente","paciente@gmail.com","002.745.748-02");
        cadastrarConsulta(medico,paciente,proximaSegundaAs10);


        var medicoLivre = medicoRepositorio.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA , proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico= cadastrarMedico("Medico","medico@voll.com","458659",Especialidade.CLINICO);

        var medicoLivre = medicoRepositorio.escolherMedicoAleatorioLivreNaData(Especialidade.CLINICO , proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastraPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastraPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEnderecosDTO dadosEndereco() {
        return new DadosEnderecosDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }


}