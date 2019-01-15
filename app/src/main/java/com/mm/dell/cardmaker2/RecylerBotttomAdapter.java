package com.mm.dell.cardmaker2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecylerBotttomAdapter extends RecyclerView.Adapter<RecylerBotttomAdapter.MyHolder> {
    Context context;
    ArrayList<FontModel> fontlist = new ArrayList<>();
    ArrayList<String> backgroundlist = new ArrayList<>();
    RecyclerOnItemClickListner listner;
    private int size = 0;
    AssetManager manager;

    public RecylerBotttomAdapter(Context context, ArrayList<FontModel> fontlist, RecyclerOnItemClickListner listner) {
        this.context = context;
        this.fontlist = fontlist;
        this.listner = listner;
        size = fontlist.size();
        backgroundlist.clear();
        notifyDataSetChanged();
    }

    public RecylerBotttomAdapter(ArrayList<String> backgroundlist, Context context, RecyclerOnItemClickListner listner) {
        this.context = context;
        this.backgroundlist = backgroundlist;
        this.listner = listner;
        size = backgroundlist.size();
        fontlist.clear();
        manager = context.getAssets();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottomlistraw, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.imageView.setVisibility(View.GONE);
        myHolder.textView.setTypeface(fontlist.get(i).getTypeface());

        myHolder.layout.setOnClickListener(v -> {
            listner.OnItemClickLister(i, fontlist.get(i).getTypeface());
        });
    }

    @Override
    public int getItemCount() {
        Log.e("SIZE", ">>>>     " + size);
        return size;

    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        RelativeLayout layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.root);
            textView = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
