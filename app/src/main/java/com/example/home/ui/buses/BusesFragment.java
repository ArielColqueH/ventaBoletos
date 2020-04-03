package com.example.home.ui.buses;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.home.R;
import com.example.home.ui.socios.AdminDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class BusesFragment extends Fragment {

    private BusesViewModel slideshowViewModel;
    List <String> lista;
    AdminDataBase adb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(BusesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buses, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fabhome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(),
//                        "Home", Toast.LENGTH_SHORT).show();
                agregarBus();
            }
        });
        return root;
    }

    void agregarBus(){
        LayoutInflater inflador = LayoutInflater.from(getActivity());
        final View view = inflador.inflate(R.layout.dialog_buses ,null, false);

        final EditText etid, etnom,etst;
//        etid = (EditText)view.findViewById(R.id.etIdProd);
//        etnom = (EditText)view.findViewById(R.id.etNomProd);
//        etst = (EditText)view.findViewById(R.id.etStock);

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
        adb = new AdminDataBase(getActivity(),"empresaDeTransporte.db",null, 1);
        lista = adb.getListaSocios();
        lista.add(0,"Nombre de Socios");
        final Spinner spinnerSocios = view.findViewById(R.id.spinnerSocios);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, lista);
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
                }

            }
        });
        alertAlta.show();

    }
}