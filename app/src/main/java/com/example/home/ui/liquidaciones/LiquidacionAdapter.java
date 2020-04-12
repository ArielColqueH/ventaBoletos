package com.example.home.ui.liquidaciones;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.boleto.BoletoAdapter;
import com.example.home.ui.boleto.ModeloBoleto;
import com.example.home.ui.home.ModeloSalida;
import com.example.home.ui.socios.ModeloSocio;

import java.util.ArrayList;

public class LiquidacionAdapter extends RecyclerView.Adapter<LiquidacionAdapter.MyViewHolder> {
    ArrayList<ModeloLiquidacion> lista;
    Context context;
    ModeloSalida b ;
    AdminDataBase adb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

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
        Button btnInformacion;
        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoLiquidacion=itemView.findViewById(R.id.codigoLiquidacionC);
            nombreChofer=itemView.findViewById(R.id.nombreSocioLiquidacionC);
            placaBus=itemView.findViewById(R.id.placaLiquidacionC);
            fechaSalida=itemView.findViewById(R.id.fechaLiquidacionC);
            btnInformacion=itemView.findViewById(R.id.btnVerDetallesLiquidacion);
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
        holder.btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = item.getIdLiquidacion();
                Log.d("position",":"+pos);
                LayoutInflater inflador = LayoutInflater.from(context);
                View view = inflador.inflate(R.layout.dialog_liquidacion_final ,null, false);
                //adb = new AdminDataBase(context,"empresaDeTransporte.db",null, 1);
                recyclerView = (RecyclerView)  view.findViewById(R.id.my_recycler_liquidaciones_final);
                mAdapter = new LiquidacionBoletosAdapter(context,(ArrayList<ModeloBoleto>)adb.listaBoletosLiquidacion(String.valueOf(pos)));
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(context);
                alertAlta.setTitle("Reporte de Liquidacion Final");
                alertAlta.setCancelable(false);
                alertAlta.setView(view);
                //c = lista.get(pos);
                TextView totBol,totVen,ivaDesc,itDesct,ofiDesc,totDesc,egrT,ingTot,totPagable;
                totBol=(TextView)view.findViewById(R.id.totalBoletosD);
                totVen=(TextView)view.findViewById(R.id.totalVentaD);
                ivaDesc=(TextView)view.findViewById(R.id.ivaDescD);
                itDesct=(TextView)view.findViewById(R.id.itDescD);
                ofiDesc=(TextView)view.findViewById(R.id.desOfiDescD);
                totDesc=(TextView)view.findViewById(R.id.totalDescD);
                egrT=(TextView)view.findViewById(R.id.egreTotalFinalD);
                ingTot=(TextView)view.findViewById(R.id.liqTotFinalD);
                totPagable=(TextView)view.findViewById(R.id.pagTotalFinalD);

                String totalPasajeros=adb.getTotalPasajerosFromLiquidacion(String.valueOf(pos));
                String totalVentaBoletos=adb.getTotalVentaBoletosFromLiquidacion(String.valueOf(pos));
                double ivaD=Double.parseDouble(totalVentaBoletos)*0.13;
                double itD=Double.parseDouble(totalVentaBoletos)*0.03;
                double ofiD=Double.parseDouble(totalVentaBoletos)*0.10;
                double totalDes=ivaD+itD+ofiD;
                totBol.setText(totalPasajeros);
                totVen.setText(totalVentaBoletos);
                ivaDesc.setText(String.format("%.2f", ivaD));
                itDesct.setText(String.format("%.2f",itD));
                ofiDesc.setText(String.format("%.2f",ofiD));
                totDesc.setText(String.format("%.2f",totalDes));
                ingTot.setText(totalVentaBoletos);
                egrT.setText(String.valueOf(totalDes));
                double totalaPagar=Double.parseDouble(totalVentaBoletos)-totalDes;
                totPagable.setText(String.valueOf(totalaPagar));

                alertAlta.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertAlta.show();
            }
        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}