package com.example.sozlukuygulamasi2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class KelimelerDAO {

    public ArrayList<Kelimeler> tumKelimeler(VeriTabani vt){
        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kelimeler",null);

        while (c.moveToNext()){
            Kelimeler k = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
                    ,c.getString(c.getColumnIndex("ingilizce"))
                    ,c.getString(c.getColumnIndex("turkce")));
            kelimelerArrayList.add(k);
        }

        return kelimelerArrayList;
    }

    public ArrayList<Kelimeler> kelimeAra(VeriTabani vt, String arananKelime){
        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce LIKE  '%"+arananKelime+"%'",null);

        while (c.moveToNext()){
            Kelimeler k = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
                    ,c.getString(c.getColumnIndex("ingilizce"))
                    ,c.getString(c.getColumnIndex("turkce")));
            kelimelerArrayList.add(k);
        }

        return kelimelerArrayList;
    }



}
