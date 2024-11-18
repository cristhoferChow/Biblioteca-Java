create table IF NOT EXISTS livro(
id serial PRIMARY KEY,
nome VARCHAR(45),
ano INT,
autor VARCHAR(30),
editora VARCHAR(30),

 CONSTRAINT chk_ano CHECK (ano >= 1900 AND ano <= EXTRACT(YEAR FROM CURRENT_DATE))
);