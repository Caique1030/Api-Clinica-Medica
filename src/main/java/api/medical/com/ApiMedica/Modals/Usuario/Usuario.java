package api.medical.com.ApiMedica.Modals.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@Table(name = "Usuarios") //cria tabela no banco
@Getter /*gera metodos getter*/
@NoArgsConstructor // gera os construtores default
@AllArgsConstructor // gera construtores em todos os campos
@EqualsAndHashCode(of = "id") //gera os equals e os hashcode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String login;
    private  String senha;
}
