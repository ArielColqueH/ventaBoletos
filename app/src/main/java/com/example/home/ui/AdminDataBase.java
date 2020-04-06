package com.example.home.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.home.ui.buses.ModeloBus;
import com.example.home.ui.home.ModeloSalida;
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
        db.execSQL(sqlSocios);
        db.execSQL(sqlBuses);
        db.execSQL(sqlSalidas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS socios");
        db.execSQL("DROP TABLE IF EXISTS buses");
        db.execSQL("DROP TABLE IF EXISTS salidas");
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
        String sql = "SELECT s.nomSoc FROM socios s,bus b where s.idSocio=b.idSocio and b.idBus="+idBus;
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
        String sql = "SELECT placa FROM bus  where idBus="+idBus;
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
    //-----------//SALIDAS ---------------------


}
