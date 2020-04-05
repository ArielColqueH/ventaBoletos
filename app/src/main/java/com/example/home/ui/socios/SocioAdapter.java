package com.example.home.ui.socios;

import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

public class SocioAdapter extends RecyclerView.Adapter<SocioAdapter.MyViewHolder> {
    ArrayList<ModeloSocio> lista;
    Context context;
    ModeloSocio c ;
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
        TextView nombre,apellido,codigo;
        Button bEditar , bBorrar;
        // each data item is just a string in this case
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo=itemView.findViewById(R.id.codigo_socio);
            nombre=itemView.findViewById(R.id.nombre_socio);
            apellido=itemView.findViewById(R.id.apellido_socio);
            bEditar=itemView.findViewById(R.id.btnEditar);
            bBorrar=itemView.findViewById(R.id.btnBorrar);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SocioAdapter(Context ct, ArrayList<ModeloSocio> lista) {
            context=ct;
            this.lista=lista;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  =inflater.inflate(R.layout.socioscard,parent,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int posi = position;
        final ModeloSocio item = lista.get(position);
        holder.codigo.setText(String.valueOf(item.getIdSoc()));
        holder.nombre.setText(item.getNomSoc());
        holder.apellido.setText(item.getApeSoc());
        holder.bEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,
//                "Editar", Toast.LENGTH_SHORT).show();
                int pos = posi;
                LayoutInflater inflador = LayoutInflater.from(context);
                View view = inflador.inflate(R.layout.dialog_socios ,null, false);
                final EditText nomb, apel;
                nomb = (EditText)view.findViewById(R.id.socioName);
                apel = (EditText)view.findViewById(R.id.socioApellido);
                androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(context);
                alertAlta.setTitle("Editar Socio");
                alertAlta.setCancelable(false);
                alertAlta.setView(view);
                c = lista.get(pos);
                setId(c.getIdSoc());
                nomb.setText(c.getNomSoc());
                apel.setText(c.getApeSoc());
                alertAlta.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertAlta.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String id,nom, ape;
//                id = etid.getText().toString().trim();
                        nom = nomb.getText().toString().trim();
                        ape = apel.getText().toString().trim();
                        if(nom.length() > 0 && ape.length()>0) {
                            adb = new AdminDataBase(context,"empresaDeTransporte.db",null, 1);
                            adb.editar(new ModeloSocio(getId(),nom,ape));
                            Toast.makeText(context, "El cambio el dato con exito", Toast.LENGTH_SHORT).show();
                            lista= (ArrayList<ModeloSocio>)adb.listaSocios();
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Error, campos vacios", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertAlta.show();
            }
        });

        holder.bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int poss = posi;

                c=lista.get(poss);
                setId(c.getIdSoc());
                AlertDialog.Builder del = new AlertDialog.Builder(context);
                del.setMessage("Esta seguro de querer eliminar al socio?");
                del.setCancelable(false);
                del.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("mytag","x :"+getId() ); int estado=1;
                        Log.d("mytag","x :"+getId() );
                        adb = new AdminDataBase(context,"empresaDeTransporte.db",null, 1);
                        adb.eliminar(new ModeloSocio(getId(),estado));
                        lista= (ArrayList<ModeloSocio>)adb.listaSocios();
                        notifyDataSetChanged();

                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                del.show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lista.size();
    }
}