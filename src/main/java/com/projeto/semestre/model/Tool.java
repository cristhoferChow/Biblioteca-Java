package com.projeto.semestre.model;

import java.util.Map;

public class Tool {
    public static Livro converterLivro(Map<String, Object> cadastro){
        return new Livro( 
            (int)    cadastro.get("id"),
            (String) cadastro.get("nome"),
            (int)    cadastro.get("ano"),
            (String) cadastro.get("autor"),
            (String) cadastro.get("editora"));
    }
    
}
