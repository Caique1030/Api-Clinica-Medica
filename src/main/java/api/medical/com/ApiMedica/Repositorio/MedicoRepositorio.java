package api.medical.com.ApiMedica.Repositorio;

import api.medical.com.ApiMedica.Modals.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepositorio extends JpaRepository<Medico,Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
