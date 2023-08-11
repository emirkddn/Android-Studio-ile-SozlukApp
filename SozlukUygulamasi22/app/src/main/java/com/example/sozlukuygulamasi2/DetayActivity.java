package com.example.sozlukuygulamasi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {

    private Kelimeler kelime;
    private TextView textViewTurkce,textViewIngilizce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        textViewIngilizce = findViewById(R.id.textViewIngilizceDetay);
        textViewTurkce = findViewById(R.id.textViewTurkceDetay);

        kelime = (Kelimeler) getIntent().getSerializableExtra("nesne");

        textViewIngilizce.setText(kelime.getIngilizce());
        textViewTurkce.setText(kelime.getTurkce());
    }
}