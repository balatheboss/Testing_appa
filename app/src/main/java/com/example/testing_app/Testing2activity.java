package com.example.testing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Testing2activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    BeanDemo beanDemo;
    Spinner spinner1,spinner2;
    String Dinner [] = {"Light","Normal","Heavy"};
    String inter [] = {"None","Yes"};
    String inter1 [] = {"None","No"};
    String inter2 [] = {"Yep","No"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing2activity);
        spinner1 = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Dinner);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<BeanDemo> object = (ArrayList<BeanDemo>) args.getSerializable("ARRAYLIST");
        String  name = object.get(1).getName();
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;

        if(spin.getId() == R.id.spinner)
        {
            String diner=Dinner[position];
            Toast.makeText(this, diner, Toast.LENGTH_SHORT).show();
            //t1.setText(diner.toString());
            if(diner.equals("Light")){
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,inter);
                spinner2.setAdapter(adapter1);
                spinner2.setOnItemSelectedListener(this);
            }else if(diner.equals("Normal")){
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,inter1);
                spinner2.setAdapter(adapter1);
                spinner2.setOnItemSelectedListener(this);
            }
            else{
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,inter2);
                spinner2.setAdapter(adapter1);
                spinner2.setOnItemSelectedListener(this);
            }

        }
        else if(spin2.getId() == R.id.spinner2)
        {

            String option=inter[position];
            Toast.makeText(this, option, Toast.LENGTH_SHORT).show();
           // t2.setText(option.toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
