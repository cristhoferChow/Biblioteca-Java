package com.projeto.semestre.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    LivroDAO ldao;

    public void inserirLivro(Livro li) {
        this.ldao.inserirLivro(li);
    }

    public List<Livro> procurarLivros(){
        return ldao.procurarLivros();
    }

    public void atualizarLivro(Livro li){
        ldao.atualizarLivro(li);
    }

    public void deletarLivro(int id){
        this.ldao.deletarLivro(id);
    }
}

