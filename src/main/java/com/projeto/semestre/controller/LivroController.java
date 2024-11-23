package com.projeto.semestre.controller;


import com.projeto.semestre.model.Livro;
import com.projeto.semestre.model.LivroService;
import com.projeto.semestre.model.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/livros")
    public String livros(Model model) {
        // Usando ApplicationContext para obter o LivroService
        LivroService ls = context.getBean(LivroService.class);
        
        // Obtém a lista de livros
        List<Map<String, Object>> lista = ls.procurarLivros();
        List<Livro> listaLivro = new ArrayList<Livro>();
        for(Map<String, Object> cadastro : lista){
            listaLivro.add(Tool.converterLivro(cadastro));
        }
        model.addAttribute("livros", listaLivro);
        return "livros"; // Template para exibição
    }

    // Método para excluir livro
    @PostMapping("/excluir/{id}")
    public String deletarLivro(@PathVariable int id) {
        LivroService livroService = context.getBean(LivroService.class);
        livroService.deletarLivro(id);
        return "redirect:/livros"; // Redirecionando para a lista de livros após a exclusão
    }

    // Método para atualizar livro (GET)
    @GetMapping("/atualizar/{id}")
    public String atualizarLivro(@PathVariable int id, Model model) {
        LivroService livroService = context.getBean(LivroService.class);
        // Buscando o livro pelo ID
        Livro livro = livroService.procurarLivro(id);
            model.addAttribute("id", id);
            model.addAttribute("livro", livro);
        return "atualizar"; // Caso não encontre o livro
    }

    // Método para atualizar livro (POST)
    @PostMapping("/atualizar/{id}")
    public String atualizarLivro(@PathVariable int id, @ModelAttribute Livro livro) {
        LivroService livroService = context.getBean(LivroService.class);
        livroService.atualizarLivro(id, livro); // Atualiza o livro no serviço
        return "redirect:/livros"; // Redireciona para a lista de livros
    }

    // Método para cadastro de livro (GET)
    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("livro", new Livro("", 0, "", "")); // Cria um novo objeto Livro
        return "cadastro"; // Template para cadastrar o livro
    }

    // Método para cadastro de livro (POST)
    @PostMapping("/cadastro")
    public String cadastrar( Model model, @ModelAttribute Livro li) {
        LivroService livroService = context.getBean(LivroService.class);
        livroService.inserirLivro(li); // Insere o livro no serviço
        return "sucesso"; // Template de sucesso após o cadastro
    }
}