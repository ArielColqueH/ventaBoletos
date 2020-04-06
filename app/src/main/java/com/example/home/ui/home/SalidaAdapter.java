package com.example.home.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.buses.ModeloBus;

import java.util.ArrayList;

public class SalidaAdapter extends RecyclerView.Adapter<SalidaAdapter.MyViewHolder> {
    ArrayList<ModeloSalida> lista;
    Context context;
    ModeloSalida b ;
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
        TextView codigoSalida,fechaSalida,horaSalida,nombreChofer,placaSalida,destinoSalida;

        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoSalida=itemView.findViewById(R.id.codigoSalidaC);
            fechaSalida=itemView.findViewById(R.id.fechaSalidaC);
            horaSalida=itemView.findViewById(R.id.horaSalidaC);
            placaSalida=itemView.findViewById(R.id.placaBusC);
            destinoSalida=itemView.findViewById(R.id.destinoC);
            nombreChofer=itemView.findViewById(R.id.nombreChoferC);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SalidaAdapter(Context ct, ArrayList<ModeloSalida> lista) {
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
        View view  =inflater.inflate(R.layout.homecard,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int posi = position;
        String nombreSocio="pruebaNombre";
        String placaBus="pruebaPlaca";
        final ModeloSalida item = lista.get(position);
        holder.codigoSalida.setText(String.valueOf(item.getIdSalida()));
        holder.fechaSalida.setText(item.getFechaSalida());
        holder.horaSalida.setText(String.valueOf(item.getHoraSalida()));
        holder.destinoSalida.setText(item.getDestino());
        //Log.d("id duenio",":"+item.getDuenio());
        nombreSocio=adb.getNombreSocioFromBus(String.valueOf(item.getIdBus()));
        holder.nombreChofer.setText(nombreSocio);
        placaBus=adb.getPlacaBus(String.valueOf(item.getIdBus()));
        holder.placaSalida.setText(placaBus);
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}