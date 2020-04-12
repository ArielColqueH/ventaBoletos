package com.example.home.ui.home;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.ui.AdminDataBase;
import com.example.home.ui.buses.BusAdapter;
import com.example.home.ui.buses.ModeloBus;
import com.example.home.ui.socios.ModeloSocio;
import com.example.home.ui.socios.SocioAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private HomeViewModel homeViewModel;
    AdminDataBase adb;
    ModeloBus aux;
    List<ModeloSalida> listafdb = new ArrayList<ModeloSalida>();
    List <ModeloBus> listaBase;
    List <String> listaSpinner= new ArrayList<>();
    HashMap<Integer,Integer> spinnerMap = new HashMap<Integer, Integer>();



    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

    private AppCompatRadioButton radioButton;

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    EditText etFecha;
    ImageButton ibObtenerFecha;

    EditText etHora;
    ImageButton ibObtenerHora;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        adb = new AdminDataBase(getActivity(),"empresaDeTransporte.db",null, 1);
        recyclerView = (RecyclerView)  root.findViewById(R.id.my_recycler_home);
        mAdapter = new SalidaAdapter(getActivity(),(ArrayList<ModeloSalida>)adb.listaSalidas());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FloatingActionButton fab = root.findViewById(R.id.fabhome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    altaSalidaBus();
            }
        });

        return root;
    }
    void altaSalidaBus(){
        listaSpinner.clear();

//        Toast.makeText(getActivity(),
//                "Toast por defecto", Toast.LENGTH_SHORT).show();

        LayoutInflater inflador = LayoutInflater.from(getActivity());
        final View view = inflador.inflate(R.layout.dialog_salidas ,null, false);

        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) view.findViewById(R.id.et_mostrar_fecha_picker);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (ImageButton) view.findViewById(R.id.ib_obtener_fecha);
        //Evento setOnClickListener - clic

        etHora = (EditText) view.findViewById(R.id.et_mostrar_hora_picker);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        ibObtenerHora = (ImageButton) view.findViewById(R.id.ib_obtener_hora);
        //Evento setOnClickListener - clic
        ibObtenerHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog recogerHora = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Formateo el hora obtenido: antepone el 0 si son menores de 10
                        String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                        //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                        String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                        //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                        String AM_PM;
                        if(hourOfDay < 12) {
                            AM_PM = "a.m.";
                        } else {
                            AM_PM = "p.m.";
                        }
                        //Muestro la hora con el formato deseado
                        etHora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                    }
                    //Estos valores deben ir en ese orden
                    //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
                    //Pero el sistema devuelve la hora en formato 24 horas
                }, hora, minuto, false);

                recogerHora.show();
            }
        });

        ibObtenerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog recogerFecha = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                        final int mesActual = month + 1;
                        //Formateo el día obtenido: antepone el 0 si son menores de 10
                        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        //Formateo el mes obtenido: antepone el 0 si son menores de 10
                        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                        //Muestro la fecha con el formato deseado
                        etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                    }
                    //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                    /**
                     *También puede cargar los valores que usted desee
                     */
                },anio, mes, dia);
                //Muestro el widget
                recogerFecha.show();
            }
        });
        //-----------------------------------------------

        final String nombreDestinoRbn="";
        final EditText fecha, hora;
        final Spinner placa;

        fecha = (EditText)view.findViewById(R.id.et_mostrar_fecha_picker);
        hora = (EditText)view.findViewById(R.id.et_mostrar_hora_picker);
        placa = (Spinner) view.findViewById(R.id.spinnerPlacas);
        //segundo spinner

        listaBase = adb.getListaPlacas();

        for(int i=0;i<listaBase.size();i++){
            aux=listaBase.get(i);
            Log.d("idSocio",":"+aux.getIdBus());
            spinnerMap.put(i+1,aux.getIdBus());
            String spnPlaca = aux.getPlaca();
            listaSpinner.add(spnPlaca);
        }
        listaSpinner.add(0,"Placas de buses para Salidas");
        final Spinner spinnerPlacas = view.findViewById(R.id.spinnerPlacas);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listaSpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPlacas.setAdapter(adapter2);
        spinnerPlacas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String text = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        androidx.appcompat.app.AlertDialog.Builder alertAlta = new AlertDialog.Builder(getActivity());
        alertAlta.setTitle("Agregar nueva Salida de Bus");
        alertAlta.setCancelable(false);
        alertAlta.setView(view);

        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup1);

        alertAlta.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertAlta.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                Log.d("radio",":"+selectedId+" 1");

                // find the radiobutton by returned id
                radioButton = (AppCompatRadioButton) view.findViewById(selectedId);
//                Toast.makeText(getActivity(),
//                        radioButton.getText(), Toast.LENGTH_SHORT).show();

                if(!spinnerPlacas.getSelectedItem().toString().equalsIgnoreCase("Placas de buses para Salidas"
                )){

                    String h,f;
                    int duenioPosition = placa.getSelectedItemPosition();
                    Log.d("placa POSITION",":"+duenioPosition);
                    Log.d("placa key",":"+spinnerMap.get(placa.getSelectedItemPosition()));
                    int duenioPlaca = spinnerMap.get(placa.getSelectedItemPosition());
                    int estado=0;
                    //String dest = destino.getSelectedItem().toString();
                    String dest=String.valueOf(radioButton.getText());
                    h = hora.getText().toString().trim();
                    f = fecha.getText().toString().trim();
                    if(h.length() > 0 && f.length()>0) {
                        adb.altaSalida(new ModeloSalida(dest,f,h,estado,duenioPlaca));
                        Toast.makeText(getActivity(), "El registro se grabo con exito", Toast.LENGTH_SHORT).show();
                        listafdb = adb.listaSalidas();
                        mAdapter = new SalidaAdapter(getActivity(),(ArrayList<ModeloSalida>)listafdb);
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