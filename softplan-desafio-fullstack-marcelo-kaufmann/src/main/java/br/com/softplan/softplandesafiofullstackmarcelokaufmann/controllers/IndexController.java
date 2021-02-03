package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller para página de informação do sistema
 *
 * @author Marcelo Augusto Kaufmann
 * @since   01/02/2021
 * @version 1.0
 *
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
