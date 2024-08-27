package api.medical.com.ApiMedica.Repositorio.Usuario;

import api.medical.com.ApiMedica.Modals.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
