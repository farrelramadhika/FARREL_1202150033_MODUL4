package com.example.android.farrel_1202150033_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listNamaMahasiswa(View view) {
        Intent a = new Intent(MainActivity.this,ListNamaActivity.class);
        startActivity(a);
    }

    public void pencarianGambar(View view) {
        Intent b = new Intent(MainActivity.this,PencarianGambarActivity.class);
        startActivity(b);
    }
}
