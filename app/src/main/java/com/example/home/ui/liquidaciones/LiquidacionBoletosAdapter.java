package com.example.home.ui.liquidaciones;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.boleto.ModeloBoleto;
import com.example.home.ui.home.ModeloSalida;

import java.util.ArrayList;

public class LiquidacionBoletosAdapter extends RecyclerView.Adapter<LiquidacionBoletosAdapter.MyViewHolder> {
    ArrayList<ModeloBoleto> lista;
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
        TextView codBol,nomPas,preBol;
        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codBol=(TextView)itemView.findViewById(R.id.codBolLiqC);
            nomPas=(TextView)itemView.findViewById(R.id.nomPasLiqC);
            preBol=(TextView)itemView.findViewById(R.id.preBolLiqC);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LiquidacionBoletosAdapter(Context ct, ArrayList<ModeloBoleto> lista) {
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
        View view  =inflater.inflate(R.layout.pasajeliquidacioncardfinal,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int posi = position;
        String idBoleto="";
        String nombrePasajero="";
        String precioBoleto="";
        final ModeloBoleto item = lista.get(position);
//        idBoleto=adb.getIdBoletoFromLiquidacionFinal(String.valueOf(item.getIdLiquidacion()));
//        holder.codBol.setText(idBoleto);
//        nombrePasajero=adb.getNombrePasajeroFromLiquidacionFinal(String.valueOf(item.getIdLiquidacion()));
//        holder.nomPas.setText(nombrePasajero);
//        precioBoleto=adb.getPrecioBoletoFromLiquidacionFinal(String.valueOf(item.getIdLiquidacion()));
//        holder.preBol.setText(precioBoleto);
          holder.codBol.setText(String.valueOf(item.getIdBoleto()));
          holder.nomPas.setText(String.valueOf(item.getNombrePasajero()));
          holder.preBol.setText(String.valueOf(item.getPrecio()));
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}