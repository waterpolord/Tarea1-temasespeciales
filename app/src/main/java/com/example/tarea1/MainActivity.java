package com.example.tarea1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView txtFecha,lbLenguaje;
    EditText txtNombre,txtApellido;
    Spinner cbxGenero;
    RadioButton rbnSi,rbnNo;


    ArrayList<CheckBox> languages = new ArrayList<>();
    RadioGroup radioGroup;

    private int dia,mes,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFecha = findViewById(R.id.txtfecha);
        txtFecha.setOnClickListener(this);
        cbxGenero = findViewById(R.id.cbxGenero);
        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this,R.array.genders, android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cbxGenero.setAdapter(Adapter);
        cbxGenero.setOnItemSelectedListener(this);

        txtNombre = findViewById(R.id.txtName);

        txtApellido = findViewById(R.id.txtApellido);

        rbnSi = findViewById(R.id.rbnSi);
        rbnNo = findViewById(R.id.rbnNo);

        lbLenguaje = findViewById(R.id.lbLenguaje);

        radioGroup = findViewById(R.id.group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                System.out.println("checked is "+rbnNo.getId()+" and "+checkedId);
                if(rbnSi.getId() == checkedId){
                    invisible(false);
                }
                else {
                    invisible(true);
                }


            }
        });

        languages.add(this.<CheckBox>findViewById(R.id.checkBox));
        languages.add(this.<CheckBox>findViewById(R.id.checkBox2));
        languages.add(this.<CheckBox>findViewById(R.id.checkBox3));
        languages.add(this.<CheckBox>findViewById(R.id.checkBox4));
        languages.add(this.<CheckBox>findViewById(R.id.checkBox5));
        languages.add(this.<CheckBox>findViewById(R.id.checkBox6));


    }



    public void ClearAll(View view){
        ViewGroup viewgroup=(ViewGroup)view.getParent();

        for (int i=0;i<viewgroup.getChildCount();i++) {
            View aux=viewgroup.getChildAt(i);

            if(aux instanceof EditText){
                ((EditText) aux).setText("");
            }

            if(aux instanceof Spinner){
                ((Spinner) aux).setSelection(0);
            }
            if(aux instanceof CheckBox){
                ((CheckBox) aux).setChecked(false);
            }



        }

        txtFecha.setText("");
        rbnNo.setChecked(false);
        rbnSi.setChecked(true);
    }

    public void invisible(Boolean hide){

        int visible = (hide)?View.INVISIBLE:View.VISIBLE;

        for(CheckBox aux:languages){
            aux.setVisibility(visible);
            aux.setChecked(false);
        }
    }

    public void valid(View view){

        Boolean val = true;
        String lenN = "";
        if(txtNombre.getText().toString().isEmpty()){
            txtNombre.setError("Debe ingresar el nombre");
            val = false;
        }
        if(txtApellido.getText().toString().isEmpty()){
            txtApellido.setError("Debe ingresar el apellido");
            val = false;
        }
        if(cbxGenero.getSelectedItemPosition() == 0){
            TextView errorText = (TextView)cbxGenero.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Seleccionar Genero");
            val = false;
        }
        /*if(txtFecha.getHint().toString().equalsIgnoreCase("Seleccionar Fecha")){
            txtFecha.setError("");
            txtFecha.setText("Seleccionar Fecha");
            txtFecha.setTextColor(Color.RED);
            val = false;
        }*/
        if(rbnSi.isChecked()){
            String len = "";
            for(CheckBox aux:languages){
                if(aux.isChecked()){
                    len += " "+aux.getText().toString()+",";
                }
            }
            if(len.length() > 2)
                lenN = len.substring(0,len.length()-1);
            if(len.length() <= 1){
               // lbLenguaje.setError("Debe seleccionar al menos un lenguaje");
                Toast.makeText(MainActivity.this,"Debe seleccionar al menos un lenguaje",Toast.LENGTH_SHORT).show();
                val = false;
            }

        }

        if(val){
            Intent intent = new Intent(this,ShowInfoActivity.class);
            intent.putExtra("NOMBRE",this.txtNombre.getText().toString());
            intent.putExtra("APELLIDO",this.txtApellido.getText().toString());
            intent.putExtra("GENERO",this.cbxGenero.getSelectedItem().toString());
            intent.putExtra("FECHA",this.txtFecha.getText().toString());
            intent.putExtra("LENGUAJES",lenN);
            //startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            startActivity(intent);

        }
    }






    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                txtFecha.setText(dayOfMonth+"/"+month+"/"+year);
            }
        },year,mes,dia);
        datePicker.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String gender = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),gender,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(0);
    }





}