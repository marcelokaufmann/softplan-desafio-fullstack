package br.com.softplan.softplandesafiofullstackmarcelokaufmann.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para página de informação do sistema
 *
 * @author Marcelo Augusto Kaufmann
 * @since   01/02/2021
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Sistema desenvolvido por Marcelo Augusto Kaufmann para teste da empresa Softplan.";
    }
}
