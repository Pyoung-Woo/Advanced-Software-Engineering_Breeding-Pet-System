package com.example.hj.product_160416;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heo on 2016-07-04.
 */
public class ListViewArrayAdapter extends ArrayAdapter<ResultListItem> {

    ArrayList<ResultListItem> items;
    Context mCtx;
    int layout;

    public ListViewArrayAdapter(Context context, int layout, int textViewResourceId, ArrayList<ResultListItem> datas) {
        super(context, layout, textViewResourceId, datas);
        this.items = datas;
        this.mCtx = context;
        this.layout = layout;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layout, null);
        }
        ResultListItem list_items = items.get(position);
        if (list_items != null) {
            ((TextView)v.findViewById(R.id.res_name)).setText(list_items.getName());
            ((TextView)v.findViewById(R.id.res_result)).setText(list_items.getResult());
        }
        return v;
    }
}
