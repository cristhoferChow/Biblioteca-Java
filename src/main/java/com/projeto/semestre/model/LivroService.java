package com.projeto.semestre.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    LivroDAO ldao;

    public void inserirLivro(Livro li) {
        this.ldao.inserirLivro(li);
    }

    public List<Map<String, Object>> procurarLivros(){
        return ldao.procurarLivros();
    }

    public Livro procurarLivro(int id){
        return ldao.procurarLivro(id);
    }

    public void atualizarLivro(int id, Livro li){
        ldao.atualizarLivro(id, li);
    }

    public void deletarLivro(int id){
        this.ldao.deletarLivro(id);
    }
}

