package com.example.rooming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    List<Country> states = new ArrayList<Country>();
    ListView countriesList;

    CountrApi countrApi;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();

        countriesList = findViewById(R.id.list);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        countrApi = retrofit.create(CountrApi.class);

        countrApi.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countries = response.body();
                states.addAll(countries);
                Log.d("test", "getAllCountries() SUCCESS: found = " + countries.size() + " countries");
                for (Country country : countries) {
                    Log.d("test", " - " + country.code + " = " + country.name.common + "  " + country.flags.png);
                }
                if (!states.isEmpty()) {
                    Adapter adapter = new Adapter(getApplicationContext(),states);
                    countriesList.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d("test", "getAllCountries() ERROR");
            }
        });

        countriesList.setOnItemClickListener(this);

    }
    private void setInitialData(){

//        states.add(new Country ("Индия","https://flagcdn.com/w80/in.png","Нью-Дели",4200));
//        states.add(new Country ("Молдова", "https://flagcdn.com/w80/md.png","Кишинёв",1020));
//        states.add(new Country ("Казахстан", "https://flagcdn.com/w80/kz.png","Астана",3400));
//        states.add(new Country ("Украина","https://flagcdn.com/w80/ua.png","Киев",2800));
//        states.add(new Country ("Германия", "https://flagcdn.com/w80/de.png","Берлин",2700));
//        states.add(new Country ("Франция","https://flagcdn.com/w80/fr.png","Париж",2400));
//        states.add(new Country ("Беларусь","https://flagcdn.com/w80/by.png","Минск",1200));
//        states.add(new Country ("Румыния", "https://flagcdn.com/w80/ro.png","Бухарест",1870));
//        states.add(new Country ("США", "https://flagcdn.com/w80/us.png","Вашингтон",4300));
//        states.add(new Country ("Китай","https://flagcdn.com/w80/cn.png","Пекин",4500));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Country country = (Country) parent.getItemAtPosition(position);

        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("code",country.code);

        startActivity(intent);
    }
}