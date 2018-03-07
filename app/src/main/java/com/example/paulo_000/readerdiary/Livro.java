package com.example.paulo_000.readerdiary;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Livro {


    private ToOne<Usuario> dono;
    @Id
    private long id;
    private String titulo;
    private String autor;
    private String genero;
    private String ano;
    private String qtdPg;
    private String pgAtual;
    private String status;

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    private String dataInicial;
    private String dataFinal;

    public String getPgAtual() {
        return pgAtual;
    }

    public void setPgAtual(String pgAtual) {
        this.pgAtual = pgAtual;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Livro(){

    }

    public String getQtdPg() {
        return qtdPg;
    }

    public void setQtdPg(String qtdPg) {
        this.qtdPg = qtdPg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public ToOne<Usuario> getDono() {
        return dono;
    }

    public void setDono(ToOne<Usuario> dono) {
        this.dono = dono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
