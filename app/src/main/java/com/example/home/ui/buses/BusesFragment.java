package com.example.home.ui.buses;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.socios.ModeloSocio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusesFragment extends Fragment {

    private BusesViewModel slideshowViewModel;
    List <ModeloSocio> listaBase;
    List <String> listaSpinner= new ArrayList<>();
    AdminDataBase adb;
    ModeloSocio aux;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    List <ModeloBus> listafdb = new ArrayList<ModeloBus>();
    HashMap<Integer,Integer> spinnerMap = new HashMap<Integer, Integer>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(BusesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buses, container, false);
        adb = new AdminDataBase(getActivity(),"empresaDeTransporte.db",null, 1);
        recyclerView = (RecyclerView)  root.findViewById(R.id.my_recycler_buses);
        mAdapter = new BusAdapter(getActivity(),(ArrayList<ModeloBus>)adb.listaBuses());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = root.findViewById(R.id.fabhome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(),
//                        "Home", Toast.LENGTH_SHORT).show();
                try{
                    agregarBus();
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),
                            "Error", Toast.LENGTH_SHORT).show();
                };
            }
        });
        return root;
    }

    void agregarBus(){
        LayoutInflater inflador = LayoutInflater.from(getActivity());
        final View view = inflador.inflate(R.layout.dialog_buses ,null, false);

        final EditText placaBus, capacidadBus;
        final Spinner tipoBus,nombreDuenoBus;
        placaBus = (EditText)view.findViewById(R.id.placaBus);
        capacidadBus = (EditText)view.findViewById(R.id.capacidadBus);
        tipoBus = (Spinner) view.findViewById(R.id.spinnerTipoBus);
        nombreDuenoBus = (Spinner) view.findViewById(R.id.spinnerSocios);

        androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(getActivity());
        alertAlta.setTitle("Agregar Bus");
        alertAlta.setCancelable(false);
        alertAlta.setView(view);
        //primer spinner
        final Spinner spinnerTipoBus = view.findViewById(R.id.spinnerTipoBus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.tipoBus,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTipoBus.setAdapter(adapter);
        spinnerTipoBus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String text = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //segundo spinner

        listaBase = adb.getListaSocios();

        for(int i=0;i<listaBase.size();i++){
            aux=listaBase.get(i);
            Log.d("idSocio",":"+aux.getIdSoc());
            spinnerMap.put(i+1,aux.getIdSoc());
            String spnNombre = aux.getNomSoc();
            String spnApel = aux.getApeSoc();
            String nombreCompleto = spnNombre+" "+spnApel;
            //Log.d("spinner","y :"+nombreCompleto );
            listaSpinner.add(nombreCompleto);
        }
        listaSpinner.add(0,"Nombre de Socio");
        final Spinner spinnerSocios = view.findViewById(R.id.spinnerSocios);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listaSpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSocios.setAdapter(adapter2);
        spinnerSocios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String text = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        alertAlta.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertAlta.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!spinnerTipoBus.getSelectedItem().toString().equalsIgnoreCase("Elija un tipo de Bus")){
                    Toast.makeText(getActivity(),spinnerTipoBus.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                    String placa;
                    int capacidad;
                    int estado=0;
                    String tipoB = tipoBus.getSelectedItem().toString();
                    int duenioPosition = nombreDuenoBus.getSelectedItemPosition();
                    Log.d("duenio POSITION",":"+duenioPosition);
                    Log.d("duenio key",":"+spinnerMap.get(nombreDuenoBus.getSelectedItemPosition()));
                    int duenioId = spinnerMap.get(nombreDuenoBus.getSelectedItemPosition());
                    placa = placaBus.getText().toString().trim();
                    //Log.d("ID SOCIO","VALOR :"+duenioId);
                    capacidad = Integer.parseInt(capacidadBus.getText().toString());
                    if(placa.length() > 0 ) {
                        adb.altaBus(new ModeloBus(placa,capacidad,tipoB,estado,duenioId));
                        Toast.makeText(getActivity(), "El registro se grabo con exito", Toast.LENGTH_SHORT).show();
                        listafdb = adb.listaBuses();
                        mAdapter = new BusAdapter(getActivity(),(ArrayList<ModeloBus>)listafdb);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }else{
                        Toast.makeText(getActivity(), "Error, campos vacios", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        alertAlta.show();

    }
}