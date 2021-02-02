package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.UsuarioProcesso;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository.UsuarioProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioProcessoController {

    @Autowired
    UsuarioProcessoRepository usuarioProcessoRepository;

    @GetMapping("/usuarioprocessos")
    public ResponseEntity<List<UsuarioProcesso>> getAllUsuarioProcesso() {
        try {
            List<UsuarioProcesso> usuarioProcessos = new ArrayList<UsuarioProcesso>();
            usuarioProcessoRepository.findAll().forEach(usuarioProcessos::add);

            if (usuarioProcessos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usuarioProcessos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para criação de vinculo entre usuário e processo - perfil TRIADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @PostMapping("/usuarioprocessos")
    public ResponseEntity<UsuarioProcesso> createUsuarioProcesso(@RequestBody UsuarioProcesso usuarioProcesso) {
        try {
            UsuarioProcesso _usuarioProcesso = usuarioProcessoRepository.save(new UsuarioProcesso(usuarioProcesso.getUsuario(), usuarioProcesso.getProcesso(), usuarioProcesso.getData()));
            return new ResponseEntity<>(_usuarioProcesso, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
