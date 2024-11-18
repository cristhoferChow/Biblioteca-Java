package com.projeto.semestre.controller;


import com.projeto.semestre.model.Livro;
import com.projeto.semestre.model.LivroService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LivroController {

    @Autowired
    private ApplicationContext context; // Mantendo o uso do ApplicationContext

    // Método principal
    @GetMapping("/")
    public String principal() {
        return "principal";
    }

    // Método para listar livros
    @GetMapping("/pesquisar")
    public String listaLivro(Model model) {
        // Usando ApplicationContext para obter o LivroService
        LivroService livroService = context.getBean(LivroService.class);
        
        // Obtém a lista de livros
        List<Livro> livros = livroService.procurarLivros();
        model.addAttribute("livro", livros);
        return "pesquisar"; // Template para exibição
    }

    // Método para excluir livro
    @GetMapping("/excluir/{id}")
    public String deletarLivro(@PathVariable int id) {
        LivroService livroService = context.getBean(LivroService.class);
        livroService.deletarLivro(id);
        return "redirect:/pesquisar"; // Redirecionando para a lista de livros após a exclusão
    }

    // Método para atualizar livro (GET)
    @GetMapping("/atualizar/{id}")
    public String atualizarLivro(@PathVariable int id, Model model) {
        LivroService livroService = context.getBean(LivroService.class);
        
        // Buscando o livro pelo ID
        Livro livro = livroService.procurarLivros().stream()
                                  .filter(l -> l.getId() == id)
                                  .findFirst()
                                  .orElse(null);
        
        if (livro != null) {
            model.addAttribute("livro", livro);
            return "atualizar"; // Template para atualizar o livro
        }
        return "redirect:/pesquisar"; // Caso não encontre o livro
    }

    // Método para atualizar livro (POST)
    @PostMapping("/atualizar")
    public String atualizarLivro(@ModelAttribute Livro livro) {
        LivroService livroService = context.getBean(LivroService.class);
        livroService.atualizarLivro(livro); // Atualiza o livro no serviço
        return "redirect:/pesquisar"; // Redireciona para a lista de livros
    }

    // Método para cadastro de livro (GET)
    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("livro", new Livro()); // Cria um novo objeto Livro
        return "cadastro"; // Template para cadastrar o livro
    }

    // Método para cadastro de livro (POST)
    @PostMapping("/cadastro")
    public String sucesso(@ModelAttribute Livro livro) {
        LivroService livroService = context.getBean(LivroService.class);
        livroService.inserirLivro(livro); // Insere o livro no serviço
        return "sucesso"; // Template de sucesso após o cadastro
    }
}