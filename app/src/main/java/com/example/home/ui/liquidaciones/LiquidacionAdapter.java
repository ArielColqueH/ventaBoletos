package com.example.home.ui.liquidaciones;

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

public class LiquidacionAdapter extends RecyclerView.Adapter<LiquidacionAdapter.MyViewHolder> {
    ArrayList<ModeloLiquidacion> lista;
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
        TextView codigoLiquidacion,nombreChofer,placaBus,fechaSalida;
        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoLiquidacion=itemView.findViewById(R.id.codigoLiquidacionC);
            nombreChofer=itemView.findViewById(R.id.nombreSocioLiquidacionC);
            placaBus=itemView.findViewById(R.id.placaLiquidacionC);
            fechaSalida=itemView.findViewById(R.id.fechaLiquidacionC);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LiquidacionAdapter(Context ct, ArrayList<ModeloLiquidacion> lista) {
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
        View view  =inflater.inflate(R.layout.liquidacionescard,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int posi = position;
        String fecha="";
        String placa="";
        String nombreChofer="";
        final ModeloLiquidacion item = lista.get(position);
        holder.codigoLiquidacion.setText(String.valueOf(item.getIdLiquidacion()));
        //Log.d("id duenio",":"+item.getDuenio());
        fecha=adb.getFechaSalidaFromLiquidacion(String.valueOf(item.getIdSalida()));
        holder.fechaSalida.setText(fecha);
        placa=adb.getPlacaFromLiquidacion(String.valueOf(item.getIdSalida()));
        holder.placaBus.setText(placa);
        nombreChofer=adb.getNombreChoferSalidaFromLiquidacion(String.valueOf(item.getIdSalida()));
        holder.nombreChofer.setText(nombreChofer);
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}