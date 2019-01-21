package com.mm.dell.cardmaker2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SeekBar;

import com.mm.dell.cardmaker2.Adapters.ComponetAdapater;
import com.mm.dell.cardmaker2.Main2Activity;
import com.mm.dell.cardmaker2.Nev1_Item;
import com.mm.dell.cardmaker2.R;

import java.util.ArrayList;

public class CardMainActivity extends AppCompatActivity {
    RecyclerView recyclerlist;
    ListView listView;
    int containerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_main);
        initView();
    }

    private void initView() {
        containerId = R.id.root_container;
        listView = findViewById(R.id.listview);
        setComponentAdapter();

    }

    private void setComponentAdapter() {
        ArrayList<Nev1_Item> componetlist = new ArrayList<>();
        componetlist.add(new Nev1_Item(getResources().getString(R.string.bg_layer1), R.drawable.ic_bg_layer));
        componetlist.add(new Nev1_Item(getResources().getString(R.string.text), R.drawable.ic_text));
        componetlist.add(new Nev1_Item(getResources().getString(R.string.material), R.drawable.ic_material));
        componetlist.add(new Nev1_Item(getResources().getString(R.string.symbol), R.drawable.ic_symbols));
        ComponetAdapater adapater = new ComponetAdapater(CardMainActivity.this, componetlist);
        listView.setAdapter(adapater);
    }

}
