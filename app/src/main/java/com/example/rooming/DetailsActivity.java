package com.example.rooming;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView name;
        ImageView image;
        TextView codesView;
        TextView size;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountrApi countrApi;
        countrApi = retrofit.create(CountrApi.class);

        name = (TextView) findViewById(R.id.textView);
        image = (ImageView) findViewById(R.id.imageView);
        codesView = (TextView) findViewById(R.id.capital);
        //size = (TextView) findViewById(R.id.sizes);

        String codeId = getIntent().getStringExtra("code");

        countrApi.getCountry(codeId).enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                Country checkedCountry = response.body();
                if (!(checkedCountry == null)) {
                    name.setText(checkedCountry.name.common);
                    Glide.with(DetailsActivity.this).load(checkedCountry.flags.png).into(image);
                    codesView.setText(checkedCountry.code.toString());
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable throwable) {
                Log.d("test", "getAllCountries() ERROR");
            }
        });


    }
}