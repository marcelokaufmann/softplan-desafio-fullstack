package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Usuario;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value="/usuarios/cadastrarUsuario", method=RequestMethod.GET)
    public String form() {
        return "usuario/formUsuario";
    }

    /**
     * Método para inclusão de usuário - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   02/02/2021
     * @version 1.0
     *
     */
    @RequestMapping(value="/usuarios/cadastrarUsuario", method=RequestMethod.POST)
    public String form(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/usuarios/cadastrarUsuario";
        }
        Usuario usuarioExiste = usuarioRepository.findByLogin(usuario.getLogin());
        if (usuarioExiste != null) {
            attributes.addFlashAttribute("mensagem", "Login já cadastrado, favor alterar!");
            return "redirect:/usuarios/cadastrarUsuario";
        }
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/usuarios/cadastrarUsuario";
    }

    /**
     * Método para preenchimento da grid de usuários - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   02/02/2021
     * @version 1.0
     *
     */
    @RequestMapping("/usuarios")
    public ModelAndView listaUsuarios() {
        ModelAndView mv = new ModelAndView("usuarios");
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    /**
     * Método para preenchimento da visualização de usuário em específico - perfil ADMIN
     *
     * @author Marcelo Augusto Kaufmann
     * @since   02/02/2021
     * @version 1.0
     *
     */
    @RequestMapping(value="/usuarios/{login}", method=RequestMethod.GET)
    public ModelAndView detalhesUsuario(@PathVariable("login") String login) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        ModelAndView mv = new ModelAndView("usuario/detalhesUsuario");
        mv.addObject("usuario", usuario);
        return mv;
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
     * @since   03/02/2021
     * @version 1.0
     *
     */
    @RequestMapping("/usuarios/deletarUsuario")
    public String deletarUsuario(String login){
        Usuario usuario = usuarioRepository.findByLogin(login);
        usuarioRepository.delete(usuario);
        return "redirect:/usuarios";
    }

    /**
     * Método para alterar senha de usuários ainda sem criptografia
     *
     * @author Marcelo Augusto Kaufmann
     * @since   01/02/2021
     * @version 1.0
     *
     */
    @RequestMapping(value="/iniciarUsuarios", method=RequestMethod.PUT)
    public String criptograrSenhaUsuario(RedirectAttributes attributes) {

        Usuario usuarioAdmin = usuarioRepository.findByLogin("marcelo");
        usuarioAdmin.setSenha(new BCryptPasswordEncoder().encode("123"));
        usuarioRepository.save(usuarioAdmin);
        Usuario usuarioTriador = usuarioRepository.findByLogin("augusto");
        usuarioTriador.setSenha(new BCryptPasswordEncoder().encode("456"));
        usuarioRepository.save(usuarioTriador);
        Usuario usuarioFinalizador = usuarioRepository.findByLogin("kaufmann");
        usuarioFinalizador.setSenha(new BCryptPasswordEncoder().encode("789"));
        usuarioRepository.save(usuarioFinalizador);
        attributes.addFlashAttribute("mensagem", "Senhas iniciais criptografadas com sucesso!");
        return "redirect:/";
    }
}
