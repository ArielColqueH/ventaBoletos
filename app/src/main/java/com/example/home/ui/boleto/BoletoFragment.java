package com.example.home.ui.boleto;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.MainActivity;
import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.buses.BusAdapter;
import com.example.home.ui.buses.ModeloBus;
import com.example.home.ui.home.ModeloSalida;
import com.example.home.ui.socios.ModeloSocio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoletoFragment extends Fragment {

    private BoletoViewModel homeViewModel;
    List<ModeloSalida> listaBase;
    List <String> listaSpinner= new ArrayList<>();
    AdminDataBase adb;
    ModeloSalida aux;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    List <ModeloBoleto> listafdb = new ArrayList<ModeloBoleto>();
    HashMap<Integer,Integer> spinnerMap = new HashMap<Integer, Integer>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(BoletoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_boletos, container, false);
        adb = new AdminDataBase(getActivity(),"empresaDeTransporte.db",null, 1);
        recyclerView = (RecyclerView)  root.findViewById(R.id.my_recycler_boletos);
        mAdapter = new BoletoAdapter(getActivity(),(ArrayList<ModeloBoleto>)adb.listaBoletos());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = root.findViewById(R.id.fabhome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Toast.makeText(getApplicationContext(),
//                        "Home", Toast.LENGTH_SHORT).show();
                agregarPasajero();

            }
        });
        return root;
    }
    void agregarPasajero(){
        listaSpinner.clear();
//        Toast.makeText(getActivity(),
//                "Toast por defecto", Toast.LENGTH_SHORT).show();
        LayoutInflater inflador = LayoutInflater.from(getActivity());
        final View view = inflador.inflate(R.layout.dialog_boletos ,null, false);

        final EditText nombreB,nitB,destinoB,asientoB,precioB;
        final Spinner idSalida;
        nombreB = (EditText)view.findViewById(R.id.nombrePasajeroD);
        nitB = (EditText)view.findViewById(R.id.nitPasajeroD);
        asientoB = (EditText)view.findViewById(R.id.asientoD);
        precioB = (EditText)view.findViewById(R.id.precioD);
        idSalida = (Spinner) view.findViewById(R.id.spinnerBoletosSalidasD);

        androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(getActivity());
        alertAlta.setTitle("Agregar pasajero a Bus");
        alertAlta.setCancelable(false);
        alertAlta.setView(view);

        listaBase = adb.getListaSalidas();
        for(int i=0;i<listaBase.size();i++){
            aux=listaBase.get(i);
            Log.d("idsalida",":"+aux.getIdSalida());
            spinnerMap.put(i+1,aux.getIdSalida());
            String spnDestino = aux.getDestino();
            String spnFecha = aux.getFechaSalida();
            String spnHora = aux.getHoraSalida();
            String nombreCompleto = spnDestino+"   |   "+spnFecha+"   |   "+spnHora;
            //Log.d("spinner","y :"+nombreCompleto );
            listaSpinner.add(nombreCompleto);
        }
        listaSpinner.add(0,"Destinos disponibles");
        final Spinner spinnerDestinoOpciones = view.findViewById(R.id.spinnerBoletosSalidasD);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listaSpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDestinoOpciones.setAdapter(adapter2);
        spinnerDestinoOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String text = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        alertAlta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertAlta.setPositiveButton("Realizar Venta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!spinnerDestinoOpciones.getSelectedItem().toString().equalsIgnoreCase("Destinos disponibles")) {
                    Toast.makeText(getActivity(), spinnerDestinoOpciones.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    String idBoletoAux;
                    String nombrePasajeroBoletoAux;
                    String nitPasajeroBoletoAux;
                    double precioAux;
                    int asientoAux;
                    int estadoAux = 0;
                    int salidaPosition = spinnerDestinoOpciones.getSelectedItemPosition();
                    Log.d("duenio POSITION", ":" + salidaPosition);
                    Log.d("duenio key", ":" + spinnerMap.get(spinnerDestinoOpciones.getSelectedItemPosition()));
                    int salidaIdAux = spinnerMap.get(spinnerDestinoOpciones.getSelectedItemPosition());
                    //Log.d("ID SOCIO","VALOR :"+duenioId);
                    nombrePasajeroBoletoAux = nombreB.getText().toString();
                    nitPasajeroBoletoAux = nitB.getText().toString();
                    precioAux = Double.parseDouble(precioB.getText().toString());
                    asientoAux = Integer.parseInt(asientoB.getText().toString());
                    if (nombrePasajeroBoletoAux.length() > 0 && nitPasajeroBoletoAux.length() > 0) {
                        adb.altaBoleto(new ModeloBoleto(nombrePasajeroBoletoAux, nitPasajeroBoletoAux, asientoAux, precioAux, estadoAux, salidaIdAux));
                        Toast.makeText(getActivity(), "El registro se grabo con exito", Toast.LENGTH_SHORT).show();
                        listafdb = adb.listaBoletos();
                        mAdapter = new BoletoAdapter(getActivity(), (ArrayList<ModeloBoleto>) listafdb);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    } else {
                        Toast.makeText(getActivity(), "Error, campos vacios", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        alertAlta.show();

    }
}