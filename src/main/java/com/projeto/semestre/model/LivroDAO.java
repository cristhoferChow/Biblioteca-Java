package com.projeto.semestre.model;

import jakarta.annotation.PostConstruct;



import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LivroDAO {
   @Autowired
   DataSource dataSource;
   JdbcTemplate jdbc;


   @PostConstruct
   private void initialize() {
      this.jdbc = new JdbcTemplate(this.dataSource);
   }

   public void inserirLivro(Livro li) {
    String sql = "INSERT INTO livro(nome,ano,autor,editora) VALUES (?,?,?,?);";
    jdbc.update(sql, li.getNome(), li.getAno(), li.getAutor(), li.getEditora());
   }

   public List<Map<String, Object>> procurarLivros(){
      String sql = "SELECT * FROM livro";
      return jdbc.queryForList(sql);
   }

   public Livro procurarLivro(int id){
      String sql = "SELECT * FROM livro where id = ?";
      return Tool.converterLivro(jdbc.queryForMap(sql, id));
   }

   public void atualizarLivro(int id, Livro li) {
      String sql = "UPDATE livro SET nome = ?, ano = ?, autor = ?, editora = ? WHERE id = ?";
      jdbc.update(sql, li.getNome(), li.getAno(), li.getAutor(), li.getEditora(), id); // Use o parâmetro id
  }

   public void deletarLivro(int id){
      String sql = "DELETE FROM livro WHERE id = ?";
      jdbc.update(sql, id);
   }

}