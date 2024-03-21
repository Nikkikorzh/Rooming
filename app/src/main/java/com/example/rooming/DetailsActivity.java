package com.example.rooming;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView name;
        ImageView image;


        name = (TextView) findViewById(R.id.textView);
        image = (ImageView) findViewById(R.id.imageView);

        String id = getIntent().getStringExtra("id");
        Glide.with(this).load(id).into(image);
        name.setText(getIntent().getStringExtra("name"));

    }
}