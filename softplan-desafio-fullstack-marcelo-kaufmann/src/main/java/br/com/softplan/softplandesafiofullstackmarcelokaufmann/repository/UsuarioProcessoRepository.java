package br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Processo;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Usuario;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.UsuarioProcesso;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioProcessoRepository extends CrudRepository<UsuarioProcesso, Long> {
    Iterable<UsuarioProcesso> findByProcesso(Processo processo);
    Iterable<UsuarioProcesso> findByUsuario(Usuario usuario);
}
