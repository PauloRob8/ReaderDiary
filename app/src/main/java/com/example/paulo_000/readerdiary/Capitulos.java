package com.example.paulo_000.readerdiary;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Capitulos {
    @Id
    private long   id;
    private String titulo;
    private ToOne<Livro> livro;

    public ToOne<Livro> getLivro() {
        return livro;
    }

    public void setLivro(ToOne<Livro> livro) {
        this.livro = livro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String comentario;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
