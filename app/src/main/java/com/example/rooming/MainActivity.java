package com.example.rooming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    List<Country> states = new ArrayList<Country>();
    ListView countriesList;

    MyDataBase myDataBase;

    Boolean addedNew = true;
    Integer counter = 0;
    CountryDao countryDao;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();

        myDataBase = App.instance.getDataBase();
        countryDao = myDataBase.countryDao();

        countriesList = findViewById(R.id.list);
        Button get = findViewById(R.id.adding);
        Button add = findViewById(R.id.getting);
        EditText input = findViewById(R.id.editText);
        EditText inputId = findViewById(R.id.editText2);
        EditText inputCapital = findViewById(R.id.textViewCapital);
        EditText inputSize = findViewById(R.id.sizeText);

        adapter = new Adapter(getApplicationContext(), states);


        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //states.clear();
                //setInitialData();
                List<Country> countries = countryDao.getAll();
                states.addAll(countries);
                Adapter adapter = new Adapter(getApplicationContext(),states);
                countriesList.setAdapter(adapter);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countryName = input.getText().toString();
                String countryId = inputId.getText().toString();
                String countryCapital = inputCapital.getText().toString();
                Integer countrySize = Integer.parseInt(inputSize.getText().toString());
                if (countryName.length() > 0) {
                    countryDao.insert(new Country(countryName,countryId,countryCapital,countrySize));
                    addedNew = true;
                    counter++;
                }
            }
        });


        countriesList.setOnItemClickListener(this);

    }
    private void setInitialData(){

        states.add(new Country ("Индия","https://flagcdn.com/w80/in.png","Нью-Дели",4200));
        states.add(new Country ("Молдова", "https://flagcdn.com/w80/md.png","Кишинёв",1020));
        states.add(new Country ("Казахстан", "https://flagcdn.com/w80/kz.png","Астана",3400));
        states.add(new Country ("Украина","https://flagcdn.com/w80/ua.png","Киев",2800));
        states.add(new Country ("Германия", "https://flagcdn.com/w80/de.png","Берлин",2700));
        states.add(new Country ("Франция","https://flagcdn.com/w80/fr.png","Париж",2400));
        states.add(new Country ("Беларусь","https://flagcdn.com/w80/by.png","Минск",1200));
        states.add(new Country ("Румыния", "https://flagcdn.com/w80/ro.png","Бухарест",1870));
        states.add(new Country ("США", "https://flagcdn.com/w80/us.png","Вашингтон",4300));
        states.add(new Country ("Китай","https://flagcdn.com/w80/cn.png","Пекин",4500));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Country country = (Country) parent.getItemAtPosition(position);

        Dialog mdialog = new Dialog();

        Bundle args = new Bundle();
        args.putString("param", country.getName());
        args.putString("image", country.getFlagId());
        args.putString("capital", country.getCapital());
        args.putInt("size", country.getSize());
        mdialog.setArguments(args);
        deleted(country);
        Adapter adapter = new Adapter(getApplicationContext(),states);
        countriesList.setAdapter(adapter);
        mdialog.show(getSupportFragmentManager(), "test");
    }

    public List<Country> deleted(Country country) {
        countryDao.delete(country);
        List <Country> updated = countryDao.getAll();
        return updated;
    }
}