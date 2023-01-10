package com.example.biitvirus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LabAdaptor extends ArrayAdapter {

    private Context context;
    private  List<LabModel> list;

    public LabAdaptor(@NonNull Context context, List<LabModel> list) {
        super(context, R.layout.row_item_labs,list);

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.row_item_labs, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        name.setText(list.get(position).name);
        return convertView;
    }
}
