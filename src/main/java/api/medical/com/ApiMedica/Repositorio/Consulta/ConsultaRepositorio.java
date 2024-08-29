package api.medical.com.ApiMedica.Repositorio.Consulta;

import api.medical.com.ApiMedica.Modals.Consulta.Consulta;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepositorio extends JpaRepository<Consulta,Long> {
    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
