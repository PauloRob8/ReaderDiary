package com.example.paulo_000.readerdiary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import io.objectbox.Box;

public class AddLivroActivity extends AppCompatActivity {

    EditText editTitulo, editGenero, editAutor, editAno, editQtdpg,editPaginaAtual;
    TextView editStatus, editData,editData2;

    private Livro livro;

    private Box<Usuario> usuarioBox;
    private Box<Livro> livrosBox;
    private Usuario usuario;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_livro);

        livrosBox = ((App) getApplication()).getBoxStore().boxFor(Livro.class);
        usuarioBox = ((App) getApplication()).getBoxStore().boxFor(Usuario.class);

        Intent intent = getIntent();
        long livroId = intent.getLongExtra("livroId", -1);

        usuario = obterUsuarioLogado();
        setupViews();

        if (livroId == -1) {
            livro = new Livro();
        } else {
            livro = livrosBox.get(livroId);
            carregaLivro(livro);
        }
    }

    private Usuario obterUsuarioLogado() {
        final SharedPreferences preferences = getSharedPreferences("readerdiary", MODE_PRIVATE);
        final long id = preferences.getLong("usuarioId", -1);
        return usuarioBox.get(id);
    }

    public void carregaLivro(Livro livro) {
        editTitulo.setText(livro.getTitulo());
        editAutor.setText(livro.getAutor());
        editGenero.setText(livro.getGenero());
        editAno.setText(livro.getAno());
        editStatus.setText(livro.getStatus());
        editQtdpg.setText(livro.getQtdPg());
        editPaginaAtual.setText(livro.getPgAtual());
        editData.setText(livro.getDataInicial());
        editData2.setText(livro.getDataFinal());


    }

    public void AddCancelar(View view) {
        startActivity(new Intent(this, ListaLivrosActivity.class));
    }

    private void setupViews() {
        editTitulo = findViewById(R.id.add_titulo);
        editAutor = findViewById(R.id.add_autor);
        editGenero = findViewById(R.id.add_genero);
        editAno = findViewById(R.id.add_ano);
        editQtdpg = findViewById(R.id.add_pg);
        editStatus = findViewById(R.id.add_status);
        editData = findViewById(R.id.data_inicial);
        editData2 = findViewById(R.id.data_final);
        editPaginaAtual = findViewById(R.id.pagina_atual);

    }

    public void salvarLivro(View view) {

        String titulo = editTitulo.getText().toString();
        String autor = editAutor.getText().toString();
        String ano = editAno.getText().toString();
        String genero = editGenero.getText().toString();
        String numeroDePg = editQtdpg.getText().toString();
        String status = editStatus.getText().toString();
        String dataInicial = editData.getText().toString();
        String dataFinal = editData2.getText().toString();
        String pgAtual = editPaginaAtual.getText().toString();

        livro.setTitulo(titulo);
        livro.setAno(ano);
        livro.setGenero(genero);
        livro.setAutor(autor);
        livro.setStatus(status);
        livro.setQtdPg(numeroDePg);
        livro.getDono().setTarget(usuario);
        livro.setDataInicial(dataInicial);
        livro.setPgAtual(pgAtual);
        livro.setDataFinal(dataFinal);

        livrosBox.put(livro);

        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        finish();

    }


    public boolean status(View view) {

        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.status,popup.getMenu());
        popup.setOnMenuItemClickListener((menuItem -> {
            if (menuItem.getItemId() == R.id.lido) {
                editData.setVisibility(View.VISIBLE);
                editData2.setVisibility(View.VISIBLE);
                editPaginaAtual.setVisibility(View.INVISIBLE);
                editStatus.setText("Lido");
                editStatus.setTextColor(getResources().getColor(R.color.green));

            }if (menuItem.getItemId() == R.id.lendo) {
                editStatus.setText("Lendo");
                editData.setVisibility(View.VISIBLE);
                editData2.setVisibility(View.INVISIBLE);
                editPaginaAtual.setVisibility(View.VISIBLE);
                editStatus.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            if (menuItem.getItemId() == R.id.interessado) {
                editData.setVisibility(View.INVISIBLE);
                editData2.setVisibility(View.INVISIBLE);
                editPaginaAtual.setVisibility(View.INVISIBLE);
                editStatus.setText("Desejo ler");
            }
            return false;
        }));
        popup.show();
        return true;
    }

    public void calendarioDataInicial(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        editData.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void calendarioDataFinal(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        editData2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}