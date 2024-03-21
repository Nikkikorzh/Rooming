package com.example.rooming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    CountryDao countryDao;
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
        //EditText inputId = findViewById(R.id.editText2);



        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Country> countries = countryDao.getAll();
                for (int i = 0;i < countries.size(); i++) {
                    states.add(countries.get(i));
                }
                Adapter adapter = new Adapter(getApplicationContext(),states);
                countriesList.setAdapter(adapter);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countryName = input.getText().toString();
                //int countryId = inputId.getText().toString();
                if (countryName.length() > 0) {
                    countryDao.insert(new Country(countryName,"https://flagcdn.com" +
                            "/" +
                            "16x12" +
                            "/" + "ua" + "." + "png"));
                }
            }
        });


        countriesList.setOnItemClickListener(this);

    }
    private void setInitialData(){

        states.add(new Country ("Индия","https://flagcdn.com/w80/in.png"));
        states.add(new Country ("Молдова", "https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "md\n" + ".\n" + "png"));
        states.add(new Country ("Казахстан", "https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "kz\n" + ".\n" + "png"));
        states.add(new Country ("Украина","https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "ua\n" + ".\n" + "png"));
        states.add(new Country ("Германия", "https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "de\n" + ".\n" + "png"));
        states.add(new Country ("Франция","https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "fr\n" + ".\n" + "png"));
        states.add(new Country ("Беларусь","https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "by\n" + ".\n" + "png"));
        states.add(new Country ("Румыния", "https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "ro\n" + ".\n" + "png"));
        states.add(new Country ("США", "https://flagcdn.com\n" +
                "/\n" + "w20\n" + "/\n" + "us\n" + ".\n" + "png"));
        states.add(new Country ("Китай","https://alt-center.ru/scripts/jquery/kcfinder/upload/images/Kitay_4.jpg"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Country country = (Country) parent.getItemAtPosition(position);

        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("name",country.getName());
        intent.putExtra("id",country.getFlagId());

        startActivity(intent);
    }
}