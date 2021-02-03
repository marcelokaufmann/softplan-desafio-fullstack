package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.UsuarioProcesso;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository.UsuarioProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
