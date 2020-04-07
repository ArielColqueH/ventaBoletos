package com.example.home.ui.boleto;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.home.MainActivity;
import com.example.home.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BoletoFragment extends Fragment {

    private BoletoViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(BoletoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_boletos, container, false);
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
//        Spinner spinner = root.findViewById(R.id.spinnerBoletos);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.numbers,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String text = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        return root;
    }
    void agregarPasajero(){
//        Toast.makeText(getActivity(),
//                "Toast por defecto", Toast.LENGTH_SHORT).show();
        LayoutInflater inflador = LayoutInflater.from(getActivity());
        final View view = inflador.inflate(R.layout.dialog_boletos ,null, false);

        final EditText etid, etnom,etst;
//        etid = (EditText)view.findViewById(R.id.etIdProd);
//        etnom = (EditText)view.findViewById(R.id.etNomProd);
//        etst = (EditText)view.findViewById(R.id.etStock);

        androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(getActivity());
        alertAlta.setTitle("Agregar pasajero a Bus");
        alertAlta.setCancelable(false);
        alertAlta.setView(view);
        final Spinner spinner = view.findViewById(R.id.spinnerBoletosSalidasD);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                if(!spinner.getSelectedItem().toString().equalsIgnoreCase("Elija un Destino")){
                    Toast.makeText(getActivity(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });
        alertAlta.show();

    }
}