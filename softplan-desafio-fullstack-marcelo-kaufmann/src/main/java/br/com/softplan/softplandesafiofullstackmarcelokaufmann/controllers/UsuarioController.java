package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Usuario;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Método para preenchimento da grid de usuários - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        try {
            List<Usuario> usuarios = new ArrayList<Usuario>();
            usuarioRepository.findAll().forEach(usuarios::add);

            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para preenchimento da visualização de usuário em específico - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @GetMapping("/usuarios/{login")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("login") String login) {
        Optional<Usuario> usuario = usuarioRepository.findById(login);

        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método para inclusão de usuário - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario _usuario = usuarioRepository.save(new Usuario(usuario.getLogin(), usuario.getNomeCompleto(), new BCryptPasswordEncoder().encode(usuario.getSenha())));
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para alteração de usuário - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @PutMapping("/usuarios/{login}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("login") String login, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(login);

        if (usuarioData.isPresent()) {
            Usuario _usuario = usuarioData.get();
            _usuario.setNomeCompleto(usuario.getNomeCompleto());
            _usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método para exclusão de usuário - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   31/01/2021
     * @version 1.0
     *
     */
    @DeleteMapping("/usuarios/{login}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("login") String login) {
        try {
            usuarioRepository.deleteById(login);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para alterar senha de usuários ainda sem criptografia
     *
     * @author Marcelo Augusto Kaufmann
     * @since   01/02/2021
     * @version 1.0
     *
     */
    @PutMapping("/iniciarUsuarios/{login}")
    public ResponseEntity<Usuario> criptograrSenhaUsuario(@PathVariable("login") String login, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(login);

        if (usuarioData.isPresent()) {
            Usuario _usuario = usuarioData.get();
            _usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
