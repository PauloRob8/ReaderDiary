package com.example.paulo_000.readerdiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.objectbox.Box;


public class LivrosAdapter extends RecyclerView.Adapter<LivrosAdapter.LivrosViewHolder> {

    private Context context;
    private List<Livro> lista;
    private Box<Livro> box;


    public LivrosAdapter(Context context, List<Livro> lista, Box<Livro> b) {
        this.box = b;
        this.context = context;
        this.lista = lista;
    }

    @Override
    public LivrosAdapter.LivrosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item_livros,parent,false);
        LivrosViewHolder holder = new LivrosViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(LivrosAdapter.LivrosViewHolder holder, final int position) {
        final Livro livro = this.lista.get(position);
        holder.textViewTitulo.setText(livro.getTitulo());
        holder.textViewStatus.setText(livro.getStatus());
        holder.textViewData.setText(livro.getDataInicial());
        holder.textViewData2.setText(livro.getDataFinal());

        try {
            if (!livro.getQtdPg().isEmpty() && !livro.getPgAtual().isEmpty())
                holder.progressBar.setProgress(Integer.parseInt(livro.getPgAtual()));
                holder.progressBar.setMax(Integer.parseInt(livro.getQtdPg()));
        }catch (IllegalArgumentException e){
            Toast.makeText(context,"Você esqueceu de inserir as páginas!",Toast.LENGTH_SHORT).show();
        }

        try {
            if (holder.progressBar.getProgress() >= holder.progressBar.getMax())
                holder.progressBar.setProgress(100);
                livro.setStatus("Lido");
        }catch (IllegalArgumentException e){
            Toast.makeText(context,"Você esqueceu de inserir as páginas!",Toast.LENGTH_SHORT).show();
        }

        if(livro.getGenero().equals("Terror")){
            holder.itemView.setBackgroundResource(R.color.black);
        }

        if(livro.getGenero().equals("Fantasia")){
            holder.itemView.setBackgroundResource(R.color.green);
        }

        if(livro.getGenero().equals("Drama")){
            holder.itemView.setBackgroundResource(R.color.gray);
        }

        if(livro.getGenero().equals("Ficção")){
            holder.itemView.setBackgroundResource(R.color.blue);
        }

        if(livro.getGenero().equals("Romance")){
            holder.itemView.setBackgroundResource(R.color.colorAccent);
        }

        if(livro.getGenero().equals("Ação")){
            holder.itemView.setBackgroundResource(R.color.colorPrimary);
        }

        if(livro.getGenero().equals("Comedia")){
            holder.itemView.setBackgroundResource(R.color.aqua);
        }




        holder.cardView.setOnClickListener(view -> {
            final Intent intent = new Intent(context,CapitulosActivity.class);
            intent.putExtra("livroId",livro.getId());
            context.startActivity(intent);

        });

        holder.editarLivro.setOnClickListener(v -> {
            final Intent intent = new Intent(context,AddLivroActivity.class);
            intent.putExtra("livroId",livro.getId());
            context.startActivity(intent);

        });

        holder.removerLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmação");
                builder.setIcon(R.mipmap.ic_alert);
                builder.setMessage("Deseja mesmo remover o livro" + " " + livro.getTitulo());
                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lista.remove(livro);
                        box.remove(livro);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,getItemCount());
                    }
                });
                builder.setNegativeButton("NÃO",null);
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class LivrosViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitulo,textViewStatus,textViewData,textViewData2;
        FloatingActionButton editarLivro,removerLivro;
        CardView cardView;
        ProgressBar progressBar;

        public LivrosViewHolder(View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.l_titulo);
            textViewStatus = itemView.findViewById(R.id.add_status);
            textViewData = itemView.findViewById(R.id.data1);
            textViewData2 = itemView.findViewById(R.id.data2);
            editarLivro = itemView.findViewById(R.id.edit_livro);
            removerLivro = itemView.findViewById(R.id.remove_livro);
            cardView = itemView.findViewById(R.id.card);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        }

    }

