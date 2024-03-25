package com.example.rooming;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView name;
        ImageView image;
        TextView capital;
        TextView size;

        name = (TextView) findViewById(R.id.textView);
        image = (ImageView) findViewById(R.id.imageView);
        capital = (TextView) findViewById(R.id.capital);
        size = (TextView) findViewById(R.id.sizes);

        String id = getIntent().getStringExtra("id");
        Glide.with(this).load(id).into(image);
        name.setText(getIntent().getStringExtra("name"));
        capital.setText(getIntent().getStringExtra("capital"));
        size.setText(getIntent().getIntExtra("size",0));

    }
}