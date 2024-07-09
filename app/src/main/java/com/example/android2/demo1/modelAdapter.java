package com.example.android2.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.android2.R;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class modelAdapter extends ArrayAdapter<model> {
    public modelAdapter(@NonNull Context context, @NonNull List<model> models) {
        super(context,0, models);
    }

    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent){
        if (convertview == null){
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.demo11itemview,parent,false);
        }
        model model = getItem(position);
        TextView tvTitel = convertview.findViewById(R.id.demo11_item_tvTitel);
        TextView tvDes = convertview.findViewById(R.id.demo11_item_tvDes);
        tvTitel.setText(model.getTitel());
        tvDes.setText(model.getDes());
        return convertview;
    }
}
