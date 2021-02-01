package br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findByLogin(String login);
}
