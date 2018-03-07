package com.example.paulo_000.readerdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.objectbox.Box;

public class AddCapituloActivity extends AppCompatActivity {

    EditText editTitulo, editComentario;

    private Livro livro;
    private Capitulos capitulo;

    private Box<Livro> livroBox;
    private Box<Capitulos> capitulosBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_capitulo);

        livroBox = ((App) getApplication()).getBoxStore().boxFor(Livro.class);
        capitulosBox = ((App) getApplication()).getBoxStore().boxFor(Capitulos.class);

        Intent intent = getIntent();
        long capituloId = intent.getLongExtra("capituloId", -1);

        livro = obtemLivro();



        if (capituloId == -1) {
            capitulo = new Capitulos();
        } else {
            capitulo = capitulosBox.get(capituloId);
            carregaCapitulo(capitulo);
        }

        setupViews();

    }



    public void setupViews(){
        editTitulo = findViewById(R.id.add_cap_titulo);
        editComentario = findViewById(R.id.add_comentario_titulo);


    }
    private Livro obtemLivro() {
        final SharedPreferences preferences = getSharedPreferences("readerdiary", MODE_PRIVATE);
        Intent intent = getIntent();
        final long id = intent.getLongExtra("livroId", -1);
        return livroBox.get(id);
    }


    public void carregaCapitulo(Capitulos capitulos){
        editTitulo.setText(capitulos.getTitulo().toString());
        editComentario.setText(capitulos.getComentario().toString());

    }

    public void salvarCapitulo(View view) {

        String titulo = editTitulo.getText().toString();
        String comentario = editComentario.getText().toString();


        capitulo.setTitulo(titulo);
        capitulo.setComentario(comentario);
        capitulo.getLivro().setTarget(livro);


        capitulosBox.put(capitulo);

        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        finish();

    }



    public void cancelarCapitulo(View view) {
        finish();
    }
}
