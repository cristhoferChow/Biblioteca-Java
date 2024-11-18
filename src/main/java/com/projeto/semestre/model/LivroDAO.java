package com.projeto.semestre.model;

import jakarta.annotation.PostConstruct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LivroDAO {
   @Autowired
   DataSource dataSource;
   JdbcTemplate jdbc;

   public LivroDAO() {
   }

   @PostConstruct
   private void initialize() {
      this.jdbc = new JdbcTemplate(this.dataSource);
   }

   public void inserirLivro(Livro li) {
    String sql = "INSERT INTO livro(nome,ano,autor,editora) VALUES (?,?,?,?);";
    jdbc.update(sql, li.getNome(), li.getAno(), li.getAutor(), li.getEditora());
   }

   public List<Livro> procurarLivros(){
      String sql = "SELECT id, nome, ano, autor, editora FROM livro";
      return jdbc.query(sql, new RowMapper<Livro>(){
         @Override
         public Livro mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new Livro(
               rs.getInt("id"),
               rs.getString("nome"),
               rs.getInt("ano"),
               rs.getString("autor"),
               rs.getString("editora")
            );
         }
      });
   }

   public void atualizarLivro(Livro li){
      String sql = "UPDATE livro SET nome = ?, ano = ?, autor = ?, editora = ? WHERE id = ?";
      jdbc.update(sql, li.getNome(), li.getAno(), li.getAutor(), li.getEditora(), li.getId());
   }

   public void deletarLivro(int id){
      String sql = "DELETE FROM livro WHERE id = ?";
      jdbc.update(sql, id);
   }

}