package com.example.home.ui.buses;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.buses.busesDataBase;
import com.example.home.ui.socios.ModeloSocio;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.MyViewHolder> {
    ArrayList<ModeloBus> lista;
    Context context;
    ModeloBus b ;
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
        TextView codigoBus,capacidadBus,placaBus, nombreSocio,tipoBus;

        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoBus=itemView.findViewById(R.id.codigoBus);
            placaBus=itemView.findViewById(R.id.numeroPlaca);
            capacidadBus=itemView.findViewById(R.id.numeroAsientos);
            nombreSocio=itemView.findViewById(R.id.socioBus);
            tipoBus=itemView.findViewById(R.id.tipoBus);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BusAdapter(Context ct, ArrayList<ModeloBus> lista) {
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
        View view  =inflater.inflate(R.layout.busescard,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int posi = position;
        String nombreSocio="prueba";
        final ModeloBus item = lista.get(position);
        holder.codigoBus.setText(String.valueOf(item.getIdBus()));
        holder.placaBus.setText(item.getPlaca());
        holder.capacidadBus.setText(String.valueOf(item.getCapacidad()));
        Log.d("id duenio",":"+item.getDuenio());
        nombreSocio=adb.getNombreSocio(String.valueOf(item.getDuenio()));
        holder.nombreSocio.setText(nombreSocio);
        holder.tipoBus.setText(item.getTipoBus());
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}