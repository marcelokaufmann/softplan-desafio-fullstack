package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Processo;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProcessoController {

    @Autowired
    ProcessoRepository processoRepository;

    /**
     * Método para preenchimento da grid de processos - perfil TRIADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @GetMapping("/processos")
    public ResponseEntity<List<Processo>> getAllProcessos() {
        try {
            List<Processo> processos = new ArrayList<Processo>();
            processoRepository.findAll().forEach(processos::add);

            if (processos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(processos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para visualização de processo - perfil TRIADOR ou FINALIZADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @GetMapping("/processos/{id}")
    public ResponseEntity<Processo> getProcessoById(@PathVariable("id") long id) {
        Optional<Processo> processo = processoRepository.findById(id);

        if (processo.isPresent()) {
            return new ResponseEntity<>(processo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método para criação de processo - perfil TRIADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @PostMapping("/processos")
    public ResponseEntity<Processo> createProcesso(@RequestBody Processo processo) {
        try {
            Processo novoProcesso = processoRepository.save(new Processo(processo.getDescricaoProcesso(), processo.getParecerProcesso(), true));
            return new ResponseEntity<>(novoProcesso, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para alteração de processo - perfil FINALIZADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @PutMapping("/processos/{id}")
    public ResponseEntity<Processo> updateProcesso(@PathVariable("id") long id, @RequestBody Processo processo) {
        Optional<Processo> processoData = processoRepository.findById(id);

        if (processoData.isPresent()) {
            Processo _processo = processoData.get();
            _processo.setDescricaoProcesso(processo.getDescricaoProcesso());
            _processo.setParecerProcesso(processo.getParecerProcesso());
            _processo.setPendenteParecer(false);
            return new ResponseEntity<>(processoRepository.save(_processo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
