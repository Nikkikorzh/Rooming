package com.example.rooming;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

public class Dialog extends DialogFragment {
    TextView name;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String parameter = getArguments().getString("param");
        String imageparametr = getArguments().getString("image");
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
                .setMessage("Для закрытия окна нажмите ОК")
                .setPositiveButton("Подробнее", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(),DetailsActivity.class);
                        intent.putExtra("code",parameter);
                    }
                })
                .setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        co
                    }
                })
                .create();
    }

}
