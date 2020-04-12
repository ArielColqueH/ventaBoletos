package com.example.home.ui.socios;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SociosFragment extends Fragment {

    private SociosViewModel toolsViewModel;
    AdminDataBase adb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    List <ModeloSocio> listafdb = new ArrayList<ModeloSocio>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(SociosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_socios, container, false);
        adb = new AdminDataBase(getActivity(),"empresaDeTransporte.db",null, 1);
        recyclerView = (RecyclerView)  root.findViewById(R.id.my_recycler_socios);
        mAdapter = new SocioAdapter(getActivity(),(ArrayList<ModeloSocio>)adb.listaSocios());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = root.findViewById(R.id.fabhome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    agregarSocio();
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),
                "Error", Toast.LENGTH_SHORT).show();
                };
            }
        });

        //recyclerView.invalidate();
        return root;
    }

    void agregarSocio(){
//        Toast.makeText(getActivity(),
//                "Toast por defecto", Toast.LENGTH_SHORT).show();
        LayoutInflater inflador = LayoutInflater.from(getActivity());
        View view = inflador.inflate(R.layout.dialog_socios ,null, false);

        final EditText nomb, apel;
        nomb = (EditText)view.findViewById(R.id.socioName);
        apel = (EditText)view.findViewById(R.id.socioApellido);

        androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(getActivity());
        alertAlta.setTitle("Agregar Socio");
        alertAlta.setCancelable(false);
        alertAlta.setView(view);

        alertAlta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertAlta.setPositiveButton("Agregar Socio", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String id,nom, ape;
                int estado=0;
//                id = etid.getText().toString().trim();
                nom = nomb.getText().toString().trim();
                ape = apel.getText().toString().trim();
                if(nom.length() > 0 && ape.length()>0) {
                    adb.altaSocio(new ModeloSocio(nom,ape,estado));
                    Toast.makeText(getActivity(), "El registro se grabo con exito", Toast.LENGTH_SHORT).show();
                    listafdb = adb.listaSocios();
                    mAdapter = new SocioAdapter(getActivity(),(ArrayList<ModeloSocio>)listafdb);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }else{
                    Toast.makeText(getActivity(), "Error, campos vacios", Toast.LENGTH_SHORT).show();
                }


            }
        });
        alertAlta.show();

    }

    void lista(){
        //recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_socios);
        listafdb = adb.listaSocios();
        mAdapter = new SocioAdapter(getActivity(),(ArrayList<ModeloSocio>)listafdb);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}