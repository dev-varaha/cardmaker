package com.mm.dell.cardmaker2.fragments;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mm.dell.cardmaker2.Adapters.BackGroundAdapter;
import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.RecyclerOnItemClickListner;

import java.io.IOException;
import java.util.ArrayList;

public class BackGroundFragment extends Fragment {
    TextView tv_choosefromgallery;
    RecyclerView recyclerView;
    private AssetManager assetManager;
    RecyclerOnItemClickListner recyclerOnItemClickListner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            recyclerOnItemClickListner = (RecyclerOnItemClickListner) getActivity();
        } catch (Exception e) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back_ground, container, false);
        tv_choosefromgallery = view.findViewById(R.id.tv_choosefromgallery);
        recyclerView = view.findViewById(R.id.listtempletes);
        assetManager = getActivity().getAssets();
        try {
            addListToLayerOne();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tv_choosefromgallery.setOnClickListener(v -> {
            /**choose from gallery*/

        });

        return view;
    }


    private void addListToLayerOne() throws IOException {
        ArrayList<String> backgroundLists = new ArrayList<>();
        BackGroundAdapter adapter = new BackGroundAdapter(backgroundLists, getActivity(), recyclerOnItemClickListner);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        String[] list = assetManager.list("backpics");
        for (String file : list) {
            backgroundLists.add("backpics/" + file);
        }
        adapter.notifyDataSetChanged();
    }
}
