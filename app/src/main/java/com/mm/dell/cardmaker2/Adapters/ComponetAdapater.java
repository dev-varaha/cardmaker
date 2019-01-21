package com.mm.dell.cardmaker2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.dell.cardmaker2.Nev1_Item;
import com.mm.dell.cardmaker2.R;

import java.util.ArrayList;

public class ComponetAdapater extends BaseAdapter {
    Context context;
    ArrayList<Nev1_Item> list = new ArrayList<>();
    LayoutInflater inflater;

    public ComponetAdapater(Context context, ArrayList<Nev1_Item> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.nav1_raw, parent, false);
        TextView tv_itemname = convertView.findViewById(R.id.tv_itemname);
        ImageView iv_item = convertView.findViewById(R.id.iv_item);
        tv_itemname.setText(list.get(position).getItem_name());
        iv_item.setImageResource(list.get(position).getDrawbleres());
        return convertView;
    }
}
