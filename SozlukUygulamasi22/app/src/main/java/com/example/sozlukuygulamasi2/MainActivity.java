package com.example.sozlukuygulamasi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView rv;
    private Toolbar toolbar;
    private ArrayList<Kelimeler> kelimelerListe;
    private KelimelerAdapter adapter;
    private VeriTabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Sözlük Uygulaması");
        setSupportActionBar(toolbar);

        vt = new VeriTabani(this);

        VeriTabaniKopyala();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        kelimelerListe = new KelimelerDAO().tumKelimeler(vt);

        adapter = new KelimelerAdapter(this,kelimelerListe);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Harfe basıldıkça: ",query);
        aramaYap(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Harfe basıldıkça: ",newText);
        aramaYap(newText);
        return false;
    }

    public void VeriTabaniKopyala(){
        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        databaseCopyHelper.openDataBase();
    }

    public void aramaYap(String arananKelime){

        kelimelerListe = new KelimelerDAO().kelimeAra(vt,arananKelime);

        adapter = new KelimelerAdapter(this,kelimelerListe);
        rv.setAdapter(adapter);
    }
}