package br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
}
