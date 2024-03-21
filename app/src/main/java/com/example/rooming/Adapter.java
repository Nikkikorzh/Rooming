package com.example.rooming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends ArrayAdapter<Country> {

    private LayoutInflater inflater;
    private int layout;
    private List<Country> countries;

    private Context context;


    public Adapter(Context context, List<Country> countries) {
        super(context, R.layout.list_item, countries);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.countries = countries;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =inflater.inflate(R.layout.list_item,parent,false);
        ImageView flagView = convertView.findViewById(R.id.flagItem);
        TextView textView = convertView.findViewById(R.id.nameItem);
        Country country = countries.get(position);
        textView.setText(country.getName());
        Glide.with(getContext()).load(country.getFlagId()).into(flagView);
        return convertView;
    }
}