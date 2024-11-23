package com.projeto.semestre.model;

public class Livro {
    private int id;
    private String nome;
    private int ano;
    private String autor;
    private String editora;

    public Livro(){

    }

    public Livro(int id, String nome, int ano, String autor, String editora) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.autor = autor;
        this.editora = editora;
    }
    
    public Livro(String nome, int ano, String autor, String editora) {
        this.nome = nome;
        this.ano = ano;
        this.autor = autor;
        this.editora = editora;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return this.editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }


}