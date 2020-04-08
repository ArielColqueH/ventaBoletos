package com.example.home.ui.boleto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.home.ModeloSalida;

import java.util.ArrayList;

public class BoletoAdapter extends RecyclerView.Adapter<BoletoAdapter.MyViewHolder> {
    ArrayList<ModeloBoleto> lista;
    Context context;
    ModeloBoleto b ;
    AdminDataBase adb;

    int id=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView codigoBoleto,nombrePasajero,nitPasajero,asientoBoleto,tipoBusBoleto,destinoBoleto,fechaBoleto,horaBoleto,precioBoleto;

        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoBoleto=itemView.findViewById(R.id.codigoBoletoC);
            nombrePasajero=itemView.findViewById(R.id.nombreBoletoC);
            nitPasajero=itemView.findViewById(R.id.nitBoletoC);
            asientoBoleto=itemView.findViewById(R.id.asientoBoletoC);
            tipoBusBoleto=itemView.findViewById(R.id.tipoBusBoletoC);
            destinoBoleto=itemView.findViewById(R.id.destinoBoletoC);
            fechaBoleto=itemView.findViewById(R.id.fechaBoletoC);
            horaBoleto=itemView.findViewById(R.id.horarioBoletoC);
            precioBoleto=itemView.findViewById(R.id.precioBoletoC);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public BoletoAdapter(Context ct, ArrayList<ModeloBoleto> lista) {
            context=ct;
            this.lista=lista;
             adb = new AdminDataBase(ct,"empresaDeTransporte.db",null, 1);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  =inflater.inflate(R.layout.boletocard,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int posi = position;
        String tipoBus="d";
        String destino="d";
        String hora="d";
        String fecha="d";
        final ModeloBoleto item = lista.get(position);
        holder.codigoBoleto.setText(String.valueOf(item.getIdBoleto()));
        holder.nombrePasajero.setText(item.getNombrePasajero());
        holder.nitPasajero.setText(String.valueOf(item.getNitPasajero()));
        holder.asientoBoleto.setText(String.valueOf(item.getAsiento()));
        tipoBus=adb.getTipoBusFromBoleto(String.valueOf(item.getIdSalida()));
        holder.tipoBusBoleto.setText(tipoBus);
        destino=adb.getDestinoFromBoleto(String.valueOf(item.getIdSalida()));
        holder.destinoBoleto.setText(destino);
        hora=adb.getHoraFromBoleto(String.valueOf(item.getIdSalida()));
        holder.horaBoleto.setText(hora);
        fecha=adb.getFechaFromBoleto(String.valueOf(item.getIdSalida()));
        holder.fechaBoleto.setText(fecha);
        //Log.d("id duenio",":"+item.getDuenio());
        holder.precioBoleto.setText(String.valueOf(item.getPrecio()));
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}