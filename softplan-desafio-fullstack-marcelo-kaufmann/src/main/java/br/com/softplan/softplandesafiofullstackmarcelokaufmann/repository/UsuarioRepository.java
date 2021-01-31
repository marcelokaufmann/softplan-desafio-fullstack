package br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
