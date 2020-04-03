package com.example.home.ui.buses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.home.ui.socios.ModeloSocio;

import java.util.ArrayList;
import java.util.List;

public class AdminDataBase2 extends SQLiteOpenHelper {
    public AdminDataBase2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table socios(idSocio INTEGER PRIMARY KEY AUTOINCREMENT, nomSoc TEXT, apeSoc TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS socios");
        onCreate(db);
    }

    void altaSocio(ModeloSocio op){
        try {
            ContentValues cv = new ContentValues();
            cv.put("nomSoc", op.getNomSoc());
            cv.put("apeSoc",op.getApeSoc());
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.insert("socios",null,cv);

        }catch (Exception e){
//            Toast.makeText(context,
//                    "Error al aniadir", Toast.LENGTH_SHORT).show();
        }
    }

    // listado de una tabla
    public List<ModeloSocio> listaSocios(){
        String sql = "SELECT * FROM socios";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<ModeloSocio> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    int idSocio = registros.getInt(0);
                    String nomSocio = registros.getString(1);
                    String apeSocio = registros.getString(2);
                    lista.add(new ModeloSocio(idSocio,nomSocio,apeSocio));
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

    void eliminar(int id){
        try {
            SQLiteDatabase sdb = this.getWritableDatabase();
            sdb.delete("socios","idSocio ="+ id,null);

        }catch (Exception e){
        }
    }


    // listado de una tabla



}
