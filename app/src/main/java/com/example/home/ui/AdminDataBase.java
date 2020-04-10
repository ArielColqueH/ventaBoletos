package com.example.home.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.home.ui.boleto.ModeloBoleto;
import com.example.home.ui.buses.ModeloBus;
import com.example.home.ui.home.ModeloSalida;
import com.example.home.ui.liquidaciones.ModeloLiquidacion;
import com.example.home.ui.socios.ModeloSocio;

import java.util.ArrayList;
import java.util.List;

public class AdminDataBase extends SQLiteOpenHelper {
    public AdminDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlSocios = "create table socios(idSocio INTEGER PRIMARY KEY AUTOINCREMENT, nomSoc TEXT, apeSoc TEXT ,estSoc INTEGER)";
        String sqlBuses = "create table buses(idBus INTEGER PRIMARY KEY AUTOINCREMENT, placa TEXT, capacidad INTEGER ,tipobus TEXT ,estado INTEGER,idSocio INTEGER)";
        String sqlSalidas = "create table salidas(idSalida INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, hora TEXT ,destino TEXT ,estado INTEGER,idBus INTEGER)";
        String sqlBoletos = "create table boletos(idBoleto INTEGER PRIMARY KEY AUTOINCREMENT, nombrePasajero TEXT, nitPasajero TEXT ,asientoBoleto INTEGER, precioBoleto REAL ,estado INTEGER,idSalida INTEGER)";
        String sqlLiquidacion = "create table liquidaciones(idLiquidacion INTEGER PRIMARY KEY AUTOINCREMENT, estado TEXT, idSalida INTEGER)";
        db.execSQL(sqlSocios);
        db.execSQL(sqlBuses);
        db.execSQL(sqlSalidas);
        db.execSQL(sqlBoletos);
        db.execSQL(sqlLiquidacion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS socios");
        db.execSQL("DROP TABLE IF EXISTS buses");
        db.execSQL("DROP TABLE IF EXISTS salidas");
        db.execSQL("DROP TABLE IF EXISTS boletos");
        db.execSQL("DROP TABLE IF EXISTS liquidaciones");
        onCreate(db);
    }
    //------------------------------SOCIOS------------------------------
    public void altaSocio(ModeloSocio op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("nomSoc", op.getNomSoc());
            cv.put("apeSoc",op.getApeSoc());
            cv.put("estSoc",op.getEstSoc());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.insert("socios",null,cv);

        }catch (Exception e){
//            Toast.makeText(context,
//                    "Error al aniadir", Toast.LENGTH_SHORT).show();
        }
    }

    // listado de una tabla
    public List<ModeloSocio> listaSocios(){
        String sql = "SELECT * FROM socios where estSoc==0";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloSocio> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idSocio = registros.getInt(0);
                    String nomSocio = registros.getString(1);
                    String apeSocio = registros.getString(2);
                    int estSocio = registros.getInt(3);
                    lista.add(new ModeloSocio(idSocio,nomSocio,apeSocio,estSocio));
                }while(registros.moveToNext());
            }
        }
        registros.close();
        return lista;
    }

    public void editar(ModeloSocio op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("nomSoc", op.getNomSoc());
            cv.put("apeSoc",op.getApeSoc());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.update("socios",cv,"idSocio ="+ op.getIdSoc(),null);

        }catch (Exception e){
//            Toast.makeText(context,
//                    "Error al aniadir", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar(ModeloSocio op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("estSoc", op.getEstSoc());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.update("socios",cv,"idSocio ="+ op.getIdSoc(),null);
        }catch (Exception e){
        }
    }

    public List<ModeloSocio> getListaSocios(){
        String sql = "SELECT idSocio,nomSoc,apeSoc FROM socios where estSoc==0";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloSocio> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idSoc = registros.getInt(0);
                    String nomSocio = registros.getString(1);
                    String apeSocio = registros.getString(2);
                    lista.add(new ModeloSocio(idSoc,nomSocio,apeSocio));
                }while(registros.moveToNext());
            }
        }
        registros.close();
        return lista;
}
//------------------------------//SOCIOS------------------------------
    //-----------BUSES---------------------

    public void altaBus(ModeloBus op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("placa", op.getPlaca());
            cv.put("capacidad",op.getCapacidad());
            cv.put("tipobus",op.getTipoBus());
            cv.put("estado",op.getEstado());
            cv.put("idSocio",op.getDuenio());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.insert("buses",null,cv);
            Log.d("db","ALTA");
        }catch (Exception e){
            Log.d("db","ERROR ALTA");
        }
    }
    public List<ModeloBus> listaBuses(){
        String sql = "SELECT a.idBus,a.placa,a.capacidad,a.tipobus,a.idSocio FROM buses a";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloBus> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idBus = registros.getInt(0);
                    String placaBus = registros.getString(1);
                    int capacidad = registros.getInt(2);
                    String tipoBus = registros.getString(3);
                    int idSocioBus = registros.getInt(4);
                    lista.add(new ModeloBus(idBus,placaBus,capacidad,tipoBus,idSocioBus));
                }while(registros.moveToNext());
            }
        }else{
            if(registros.getCount()==0){
                Log.d("error db","registros=0");
            }else{
                Log.d("error db","error al ingresar a db");
            }

        }
        registros.close();
        return lista;
    }
    public String getNombreSocio(String idSoc) {
        String sql = "SELECT nomSoc,apeSoc FROM socios where idSocio="+idSoc;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String noSocio = "";
        String apSocio = "";
        String nc = "prueba";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    noSocio = registros.getString(0);
                    apSocio = registros.getString(1);
                    nc=noSocio+" "+apSocio;
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return nc;
    }

    public List<ModeloBus> getListaPlacas(){
        String sql = "SELECT idBus,placa FROM buses where estado==0";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloBus> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idBus = registros.getInt(0);
                    String placa = registros.getString(1);
                    lista.add(new ModeloBus(idBus,placa));
                }while(registros.moveToNext());
            }
        }
        registros.close();
        return lista;
    }
    //-----------//BUSES ---------------------
    //-----------SALIDAS ---------------------

    public void altaSalida(ModeloSalida op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("fecha", op.getFechaSalida());
            cv.put("hora",op.getHoraSalida());
            cv.put("destino",op.getDestino());
            cv.put("estado",op.getEstado());
            cv.put("idBus",op.getIdBus());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.insert("salidas",null,cv);
            Log.d("db","ALTA");
        }catch (Exception e){
            Log.d("db","ERROR ALTA");
        }
    }
    public List<ModeloSalida> listaSalidas(){
        String sql = "SELECT a.idSalida,a.destino,a.fecha,a.hora,a.idBus FROM salidas a";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloSalida> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idSalida = registros.getInt(0);
                    String destino = registros.getString(1);
                    String fecha = registros.getString(2);
                    String hora = registros.getString(3);
                    int idBus = registros.getInt(4);
                    lista.add(new ModeloSalida(idSalida,destino,fecha,hora,idBus));
                }while(registros.moveToNext());
            }
        }else{
            if(registros.getCount()==0){
                Log.d("error db","registros=0");
            }else{
                Log.d("error db","error al ingresar a db");
            }

        }
        registros.close();
        return lista;
    }
    public String getNombreSocioFromBus(String idBus) {
        String sql = "SELECT s.nomSoc FROM socios s,buses b where s.idSocio=b.idSocio and b.idBus="+idBus;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String noSocio = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    noSocio = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return noSocio;
    }
    public String getPlacaBus(String idBus) {
        String sql = "SELECT placa FROM buses where idBus="+idBus;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String placa = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    placa = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return placa;
    }

    public List<ModeloSalida> getListaSalidas(){
        String sql = "SELECT s.idSalida,s.destino,s.fecha,s.hora FROM salidas s where estado=0";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloSalida> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idSalida = registros.getInt(0);
                    String destino = registros.getString(1);
                    String fecha = registros.getString(2);
                    String hora = registros.getString(3);
                    lista.add(new ModeloSalida(idSalida,destino,fecha,hora));
                }while(registros.moveToNext());
            }
        }
        registros.close();
        return lista;
    }
    //-----------//SALIDAS ---------------------
    //-------------BOLETOS-----------------------

    public void altaBoleto(ModeloBoleto op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("nombrePasajero", op.getNombrePasajero());
            cv.put("nitPasajero",op.getNitPasajero());
            cv.put("asientoBoleto",op.getAsiento());
            cv.put("precioBoleto",op.getPrecio());
            cv.put("estado",op.getEstado());
            cv.put("idSalida",op.getIdSalida());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.insert("boletos",null,cv);

        }catch (Exception e){
//            Toast.makeText(context,
//                    "Error al aniadir", Toast.LENGTH_SHORT).show();
        }
    }

    // listado de una tabla
    public List<ModeloBoleto> listaBoletos(){
        String sql = "SELECT b.idBoleto,b.nombrePasajero,b.nitPasajero,b.asientoBoleto,b.precioBoleto,b.idSalida FROM boletos b where estado=0";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloBoleto> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idBoleto = registros.getInt(0);
                    String nomP = registros.getString(1);
                    String nitP = registros.getString(2);
                    int asientoB = registros.getInt(3);
                    double precioB = registros.getDouble(4);
                    int idSal = registros.getInt(5);
                    lista.add(new ModeloBoleto(idBoleto,nomP,nitP,asientoB,precioB,idSal));
                }while(registros.moveToNext());
            }
        }
        registros.close();
        return lista;
    }


    public String getTipoBusFromBoleto(String idSalida) {
        String sql = "SELECT b.tipobus FROM salidas s ,buses b where s.idBus=b.idBus and s.idSalida="+idSalida;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String tipoBus = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    tipoBus = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return tipoBus;
    }
    public String getDestinoFromBoleto(String idSalida) {
        String sql = "SELECT s.destino FROM salidas s where s.idSalida="+idSalida;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String destino = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    destino = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return destino;
    }


    public String getFechaFromBoleto(String idSalida) {
        String sql = "SELECT s.fecha FROM salidas s where s.idSalida="+idSalida;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String fecha = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    fecha = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return fecha;
    }
    public String getHoraFromBoleto(String idSalida) {
        String sql = "SELECT s.hora FROM salidas s where s.idSalida="+idSalida;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String hora = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    hora = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return hora;
    }



    //-------------//BOLETOS-----------------------
    //---------------LIQUIDACION-----------------------------
    public void altaLiquidacion(ModeloLiquidacion op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("estado", op.getEstado());
            cv.put("idSalida",op.getIdSalida());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.insert("liquidaciones",null,cv);
        }catch (Exception e){
//            Toast.makeText(context,
//                    "Error al aniadir", Toast.LENGTH_SHORT).show();
        }
    }

    public List<ModeloLiquidacion> listaLiquidaciones(){
        String sql = "SELECT l.idLiquidacion,l.estado,l.idSalida FROM liquidaciones l where estado=0";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloLiquidacion> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idLiquidacion = registros.getInt(0);
                    int estado = registros.getInt(1);
                    int idSalida = registros.getInt(2);
                    lista.add(new ModeloLiquidacion(idLiquidacion,estado,idSalida));
                }while(registros.moveToNext());
            }
        }
        registros.close();
        return lista;
    }
    public String getFechaSalidaFromLiquidacion(String idSalida) {
        String sql = "SELECT s.fecha FROM salidas s where s.idSalida="+idSalida;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String fecha = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    fecha = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return fecha;
    }
    public String getPlacaFromLiquidacion(String idSalida) {
        String sql = "SELECT b.placa FROM salidas s ,buses b where s.idBus=b.idBus and s.idSalida="+idSalida;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String placa = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    placa = registros.getString(0);
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return placa;
    }
    public String getNombreChoferSalidaFromLiquidacion(String idSalida) {
        String sql = "SELECT so.nomSoc,so.apeSoc FROM salidas sa ,buses b ,socios so where sa.idBus=b.idBus and b.idSocio=so.idSocio and sa.idSalida="+idSalida;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        String nombreCompleto = "";
        String nombre = "";
        String apellido = "";
        if (registros != null && registros.getCount() > 0) {
            if (registros.moveToFirst()) {
                do {
                    nombre = registros.getString(0);
                    apellido = registros.getString(1);
                    nombreCompleto=nombre+" "+apellido;
                } while (registros.moveToNext());
            }
        }
        registros.close();
        return nombreCompleto;
    }
    //---------------//LIQUIDACION----------------------------

    //---------------LIQUIDACION FINAL--------------------=
    public List<ModeloBoleto> listaBoletosLiquidacion(String idLiquidacion){
        String sql = "SELECT b.idBoleto,b.nombrePasajero,b.precioBoleto FROM boletos b ,salidas s, liquidaciones l where b.idSalida=s.idSalida and l.idSalida=s.idSalida and l.idLiquidacion="+idLiquidacion;
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloBoleto> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idBoleto = registros.getInt(0);
                    String nomP = registros.getString(1);
                    double precio = registros.getDouble(2);
                    lista.add(new ModeloBoleto(idBoleto,nomP,precio));
                }while(registros.moveToNext());
            }
        }else{
            Log.d("id",":"+idLiquidacion);
            Log.d("db","0 rows");
        }
        registros.close();
        return lista;
    }


    public String getTotalPasajerosFromLiquidacion(String idLiquidacion) {
        String sql = "SELECT * FROM boletos b , salidas s , liquidaciones l where b.idSalida=s.idSalida and l.idSalida=s.idSalida and l.idLiquidacion="+idLiquidacion;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor registros = sdb.rawQuery(sql, null);
        int count = registros.getCount();
        Log.d("sqltotal",":"+count);
        String total = String.valueOf(count);
        registros.close();
        return total;
    }
    public String getTotalVentaBoletosFromLiquidacion(String idLiquidacion) {
        String sql = "SELECT b.precioBoleto FROM boletos b , salidas s , liquidaciones l where b.idSalida=s.idSalida and l.idSalida=s.idSalida and l.idLiquidacion="+idLiquidacion;
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloBoleto> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        int sum=0;
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int precio = registros.getInt(0);
                    sum+=precio;
                }while(registros.moveToNext());
            }
        }else{
            Log.d("id",":"+idLiquidacion);
            Log.d("db","0 rows");
        }
        String total=String.valueOf(sum);
        registros.close();
        return total;
    }
    //------------------//LIQUIDACION FINAL----------------
}
