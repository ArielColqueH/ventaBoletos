package com.example.home.ui.liquidaciones;

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
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.boleto.ModeloBoleto;
import com.example.home.ui.home.ModeloSalida;
import com.example.home.ui.socios.ModeloSocio;
import com.example.home.ui.socios.SocioAdapter;
import com.example.home.ui.socios.SociosViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LiquidacionFragment extends Fragment {

    private LiquidacionViewModel liquidacionViewModel;
    List<ModeloSalida> listaBase;
    List <String> listaSpinner= new ArrayList<>();
    AdminDataBase adb;
    ModeloSalida aux;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    List <ModeloBoleto> listafdb = new ArrayList<ModeloBoleto>();
    HashMap<Integer,Integer> spinnerMap = new HashMap<Integer, Integer>();

    private AppCompatRadioButton actbMale;
    private AppCompatRadioButton actbFemale;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        liquidacionViewModel =
                ViewModelProviders.of(this).get(LiquidacionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_liquidaciones, container, false);
        adb = new AdminDataBase(getActivity(),"empresaDeTransporte.db",null, 1);
//        recyclerView = (RecyclerView)  root.findViewById(R.id.my_recycler_socios);
//        mAdapter = new SocioAdapter(getActivity(),(ArrayList<ModeloSocio>)adb.listaSocios());
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = root.findViewById(R.id.fabhome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
   //             try{
                    realizarLiquidacion();
//                }
//                catch (Exception e){
//                    Toast.makeText(getActivity(),
//                "Error", Toast.LENGTH_SHORT).show();
//                };
            }
        });

        //recyclerView.invalidate();
        return root;
    }

    void realizarLiquidacion(){

        LayoutInflater inflador = LayoutInflater.from(getActivity());
        View view = inflador.inflate(R.layout.dialog_liquidaciones ,null, false);
        final Spinner idSalidaLiquidaciones;
        idSalidaLiquidaciones = (Spinner) view.findViewById(R.id.spinnerSalidasLiquidacionesD);
//
        final AppCompatEditText acetStatus;
        //radiobuttons
//          final AppCompatRadioButton acrbMale;
//          final AppCompatRadioButton acrbFemale;
//          acrbMale=(AppCompatRadioButton)view.findViewById(R.id.acrb_male);
//          acrbFemale=(AppCompatRadioButton)view.findViewById(R.id.acrb_female);
//        acrbFemale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),
//                        "female", Toast.LENGTH_SHORT).show();
//            }
//        });
//        acrbMale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),
//                        "male", Toast.LENGTH_SHORT).show();
//            }
//        });
        //------radiobuttons
        AlertDialog.Builder alertAlta = new AlertDialog.Builder(getActivity());
        alertAlta.setTitle("Realizar liquidacion");
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
        listaSpinner.add(0,"Salidas Programadas");
        final Spinner spinnerDestinoOpciones = view.findViewById(R.id.spinnerSalidasLiquidacionesD);
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


        alertAlta.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        alertAlta.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                String id,nom, ape;
//                int estado=0;
////                id = etid.getText().toString().trim();
//                nom = nomb.getText().toString().trim();
//                ape = apel.getText().toString().trim();
//                if(nom.length() > 0 && ape.length()>0) {
//                    adb.altaSocio(new ModeloSocio(nom,ape,estado));
//                    Toast.makeText(getActivity(), "El registro se grabo con exito", Toast.LENGTH_SHORT).show();
//                    listafdb = adb.listaSocios();
//                    mAdapter = new SocioAdapter(getActivity(),(ArrayList<ModeloSocio>)listafdb);
//                    recyclerView.setAdapter(mAdapter);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                }else{
//                    Toast.makeText(getActivity(), "Error, campos vacios", Toast.LENGTH_SHORT).show();
//                }


            }
        });
        alertAlta.show();

    }

}