package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Processo;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.Usuario;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.models.UsuarioProcesso;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository.ProcessoRepository;
import br.com.softplan.softplandesafiofullstackmarcelokaufmann.repository.UsuarioProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProcessoController {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioProcessoRepository usuarioProcessoRepository;

    @RequestMapping(value="/processos/cadastrarProcesso", method=RequestMethod.GET)
    public String form() {
        return "processo/formProcesso";
    }

    /**
     * Método para criação de processo - perfil TRIADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   02/02/2021
     * @version 1.0
     *
     */
    @RequestMapping(value="/processos/cadastrarProcesso", method=RequestMethod.POST)
    public String form(Processo processo, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/processos/{id}";
        }
        processo.setParecerProcesso(null);
        processo.setPendenteParecer("Sim");
        processoRepository.save(processo);
        attributes.addFlashAttribute("mensagem", "Processo cadastrado com sucesso!");
        return "redirect:/processos/cadastrarProcesso";
    }

    /**
     * Método para preenchimento da grid de processos - perfil TRIADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   02/02/2021
     * @version 1.0
     *
     */
    @RequestMapping("/processos")
    public ModelAndView listaProcessos() {
        ModelAndView mv = new ModelAndView("processos");
        Iterable<Processo> processos = processoRepository.findAll();
        mv.addObject("processos", processos);
        return mv;
    }

    /**
     * Método para visualização de processo - perfil TRIADOR ou FINALIZADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   02/02/2021
     * @version 1.0
     *
     */
    @RequestMapping(value="/processos/{id}", method=RequestMethod.GET)
    public ModelAndView detalhesProcesso(@PathVariable("id") long id) {
        Processo processo = processoRepository.findById(id);
        ModelAndView mv = new ModelAndView("processo/detalhesProcesso");
        mv.addObject("processo", processo);

        Iterable<UsuarioProcesso> usuarioProcessos = usuarioProcessoRepository.findByProcesso(processo);
        mv.addObject("usuarioProcessos", usuarioProcessos);
        return mv;
    }

    /**
     * Método para criação de vinculo entre usuário e processo - perfil TRIADOR
     *
     * @author Marcelo Augusto Kaufmann
     * @since   02/02/2021
     * @version 1.0
     *
     */
    @RequestMapping(value="/processos/{id}", method=RequestMethod.POST)
    public String detalhesProcessoPost(@PathVariable("id") long id, Usuario usuario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/processos/{id}";
        }
        Processo processo = processoRepository.findById(id);
        UsuarioProcesso usuarioProcesso = new UsuarioProcesso();
        usuarioProcesso.setProcesso(processo);
        usuarioProcesso.setUsuario(usuario);
        usuarioProcessoRepository.save(usuarioProcesso);
        attributes.addFlashAttribute("mensagem", "Usuário adicionado com sucesso!");
        return "redirect:/processos/{id}";
    }
}
