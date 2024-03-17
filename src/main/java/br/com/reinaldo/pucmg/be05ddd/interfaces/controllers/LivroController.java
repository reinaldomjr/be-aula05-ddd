package br.com.reinaldo.pucmg.be05ddd.interfaces.controllers;

import br.com.reinaldo.pucmg.be05ddd.application.services.LivroService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroController {
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }
}
