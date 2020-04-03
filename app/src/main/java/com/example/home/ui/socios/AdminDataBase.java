package com.example.home.ui.socios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminDataBase extends SQLiteOpenHelper {
    public AdminDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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

//    ModeloProducto buscaProducto(int idSeek){
//        String sql = "SELECT * FROM producto WHERE idprod = "+idSeek;
//        SQLiteDatabase db = this.getReadableDatabase();
//        ModeloProducto itemRes = null;
//        Cursor cursor = db.rawQuery(sql, null);
//        if(cursor.moveToFirst()){
//            int id = cursor.getInt(0);
//            String nombre = cursor.getString(1);
//            float stock = cursor.getFloat(2);
//            itemRes = new ModeloProducto(id,nombre,stock);
//        }
//        cursor.close();
//        return itemRes;
//    }
//
//    public void cambiaProducto(ModeloProducto producto){
//        ContentValues values = new ContentValues();
//        values.put("nomprod", producto.getNomProd());
//        values.put("stock", producto.getStock());
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.update("producto", values,  "idprod = ?", new String[] { String.valueOf(producto.getIdProd())});
//    }
//
//    public void bajaProducto(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete("producto",  "idprod = ?", new String[] {String.valueOf(id)} );
//    }
    public List<String> getListaSocios(){
        String sql = "SELECT nomSoc FROM socios";
        SQLiteDatabase sdb = this.getReadableDatabase();
        List<String> lista = new ArrayList<>();
        Cursor registros = sdb.rawQuery(sql,null);
        if(registros!=null && registros.getCount()>0){
            if(registros.moveToFirst()){
                do{
                    String nomSocio = registros.getString(0);
                    lista.add(nomSocio);
                }while(registros.moveToNext());
            }
        }
        registros.close();
        return lista;
}



}
