package com.example.paulo_000.readerdiary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class CapitulosAdapter extends RecyclerView.Adapter<CapitulosAdapter.CapitulosViewHolder>{

    private Context context;
    private List<Capitulos> capitulos;

    public CapitulosAdapter(Context context, List<Capitulos> capitulos) {
        this.context = context;
        this.capitulos = capitulos;
    }

    @Override
    public CapitulosAdapter.CapitulosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item_capitulos,parent,false);
        CapitulosAdapter.CapitulosViewHolder holder = new CapitulosAdapter.CapitulosViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(CapitulosAdapter.CapitulosViewHolder holder, int position) {
        final Capitulos capitulos = this.capitulos.get(position);
        holder.capTitulo.setText(capitulos.getTitulo());
        holder.capComentario.setText(capitulos.getComentario());

    }

    @Override
    public int getItemCount() {
        return capitulos.size();
    }


    public static class CapitulosViewHolder extends RecyclerView.ViewHolder {

       private TextView capTitulo,capComentario;
       private CardView cardView;


        public CapitulosViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cap_card);
            capTitulo = itemView.findViewById(R.id.cap_titulo);
            capComentario = itemView.findViewById(R.id.cap_coment);

        }

    }

}

