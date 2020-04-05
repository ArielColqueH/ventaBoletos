package com.example.home.ui.socios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.home.ui.buses.ModeloBus;

import java.util.ArrayList;
import java.util.List;

public class AdminDataBase extends SQLiteOpenHelper {
    public AdminDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table socios(idSocio INTEGER PRIMARY KEY AUTOINCREMENT, nomSoc TEXT, apeSoc TEXT ,estSoc INTEGER)";
        String sql2 = "create table buses2(idBus INTEGER PRIMARY KEY AUTOINCREMENT, placa TEXT, capacidad INTEGER ,tipobus TEXT ,estado INTEGER,idSocio INTEGER)";
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS socios");
        db.execSQL("DROP TABLE IF EXISTS buses2");
        onCreate(db);
    }

    void altaSocio(ModeloSocio op){
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

    public void altaBus(ModeloBus op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("placa", op.getPlaca());
            cv.put("capacidad",op.getCapacidad());
            cv.put("tipobus",op.getTipoBus());
            cv.put("estado",op.getEstado());
            cv.put("idSocio",op.getDuenio());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.insert("buses2",null,cv);
            Log.d("db","ALTA");
        }catch (Exception e){
            Log.d("db","ERROR ALTA");
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

    void editar(ModeloSocio op){
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

    void eliminar(ModeloSocio op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("estSoc", op.getEstSoc());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.update("socios",cv,"idSocio ="+ op.getIdSoc(),null);
        }catch (Exception e){
        }
    }
//------------------------------externo------------------------------
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






    // listado de una tabla
    public List<ModeloBus> listaBuses(){
        String sql = "SELECT * FROM buses2";
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



}
