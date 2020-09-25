package com.example.tarea1;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ShowInfoActivity extends AppCompatActivity {

   private TextView nombre,genero,lenguajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String fecha = intent.getStringExtra("FECHA"), lenN = intent.getStringExtra("LENGUAJES");
        nombre = findViewById(R.id.txtName);
        genero = findViewById(R.id.txtGender);
        lenguajes = findViewById(R.id.txtLanguaje);

        nombre.setText("Hola!, mi nombre es: "+intent.getStringExtra("NOMBRE").trim()+" "+intent.getStringExtra("APELLIDO").trim()+".");

        if( !(fecha.isEmpty()) ){
            genero.setText("Soy "+intent.getStringExtra("GENERO")+", y naci en fecha "+fecha+".");
        }
        else{
            genero.setText("Soy "+intent.getStringExtra("GENERO")+".");
        }

        if(lenN.isEmpty()){
            lenguajes.setText("No me gusta programar");
        }
        else{
            lenguajes.setText("Me gusta programar en: "+lenN+".");
        }







    }
}