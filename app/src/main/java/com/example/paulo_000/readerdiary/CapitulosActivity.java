package com.example.paulo_000.readerdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.icu.text.UnicodeSetSpanner;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.QueryBuilder;

import static com.example.paulo_000.readerdiary.R.*;
import static com.example.paulo_000.readerdiary.R.mipmap.ic_gostei;

public class CapitulosActivity extends AppCompatActivity {

    ImageView avaliar;

    private Box<Livro> livroBox;
    private Box<Capitulos> capitulosBox;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_capitulos);

        BoxStore store = ((App)getApplication()).getBoxStore();
        livroBox = store.boxFor(Livro.class);
        capitulosBox = store.boxFor(Capitulos.class);

        if(obtemLivro().equals(-1))
            livro = new Livro();

        else{
            livro = obtemLivro();

        }

        setupViews();
    }

    protected void onResume() {
        super.onResume();
        QueryBuilder<Capitulos> builder = capitulosBox.query();
        builder.equal(Capitulos_.livroId,livro.getId());

        List<Capitulos> capitulosDoLivro = builder.build().find();

        loadData(capitulosDoLivro);
    }

    private void loadData(List<Capitulos> data){
        List<Capitulos> capitulos = data;

        CapitulosAdapter adapter = new CapitulosAdapter(this, capitulos);

        RecyclerView rvCapitulos = findViewById(id.rv_capitulos);

        rvCapitulos.setAdapter(adapter);
        rvCapitulos.setLayoutManager(new LinearLayoutManager(this));
        rvCapitulos.setHasFixedSize(true);
    }


    public void setupViews(){
        avaliar = findViewById(id.avaliação);

    }


    private Livro obtemLivro() {
        final SharedPreferences preferences = getSharedPreferences("readerdiary", MODE_PRIVATE);
        Intent intent = getIntent();
        final long id = intent.getLongExtra("livroId", -1);
        return livroBox.get(id);
    }

    public void addLivro(View view) {
        Intent intent = getIntent();
        final long id = intent.getLongExtra("livroId", -1);
        Intent intent1 = new Intent(this, AddCapituloActivity.class);
        intent1.putExtra("livroId",id);
        startActivity(intent1);

    }

    public void gostei(View view) {
        this.avaliar.setImageResource(mipmap.ic_gostei);

    }

    public void nGostei(View view) {
        this.avaliar.setImageResource(mipmap.ic_ngostei);
    }
}
