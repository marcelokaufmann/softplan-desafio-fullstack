package br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Processo;
import org.springframework.data.repository.CrudRepository;

public interface ProcessoRepository extends CrudRepository<Processo, String> {
    Processo findById(long id);
}
