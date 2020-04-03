package com.example.home.ui.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;



public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private HomeViewModel homeViewModel;
    String s1[],s2[],s3[];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        s1=getResources().getStringArray(R.array.codigo);
        s2=getResources().getStringArray(R.array.destinos);
        s3=getResources().getStringArray(R.array.fecha);
        //------------------------------------------------
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fabhome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Toast.makeText(getApplicationContext(),
//                        "Home", Toast.LENGTH_SHORT).show();
                altaSalidaBus();

            }
        });
        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_home);
        mAdapter = new MyAdapter(getActivity(),s1,s2,s3);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }
    void altaSalidaBus(){
//        Toast.makeText(getActivity(),
//                "Toast por defecto", Toast.LENGTH_SHORT).show();

        LayoutInflater inflador = LayoutInflater.from(getActivity());
        View view = inflador.inflate(R.layout.dialog_salidas ,null, false);

        final EditText etid, etnom,etst;
//        etid = (EditText)view.findViewById(R.id.etIdProd);
//        etnom = (EditText)view.findViewById(R.id.etNomProd);
//        etst = (EditText)view.findViewById(R.id.etStock);

        androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(getActivity());
        alertAlta.setTitle("Agregar nueva Salida de Bus");
        alertAlta.setCancelable(false);
        alertAlta.setView(view);

        alertAlta.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertAlta.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertAlta.show();

    }
}