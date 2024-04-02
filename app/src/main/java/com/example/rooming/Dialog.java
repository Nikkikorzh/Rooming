package com.example.rooming;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

import java.util.List;

public class Dialog extends DialogFragment {
    TextView name;
    ImageView img;

    Country country;
    MyDataBase myDataBase = App.instance.getDataBase();
    CountryDao countryDao = myDataBase.countryDao();


    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String parameter = getArguments().getString("param");
        String imageparametr = getArguments().getString("image");
        String capitalparametr = getArguments().getString("capital");
        Integer sizeparametr = getArguments().getInt("size");
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
//скопировать детайлз и сделать второй иксмел

        name = dialogView.findViewById(R.id.textView);
        img = dialogView.findViewById(R.id.imageView);

        name.setText(parameter);
        Glide.with(this).load(imageparametr).into(img);
        builder.setView(dialogView);
        return builder
                .setTitle("Страны список")
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton("Подробнее", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(),DetailsActivity.class);
                        intent.putExtra("name",parameter);
                        intent.putExtra("image",imageparametr);
                        intent.putExtra("capital",capitalparametr);
                        intent.putExtra("size",sizeparametr);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        countryDao.delete(new Country(parameter,"1","2",0));
                    }
                })
                .create();
    }

}
