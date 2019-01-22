package com.mm.dell.cardmaker2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.mm.dell.cardmaker2.Adapters.ComponetAdapater;

import com.mm.dell.cardmaker2.fragments.SeekBarChange;

import java.util.ArrayList;

import static com.mm.dell.cardmaker2.Constants.LAYERTWO_BITMAP_CODE;
import static com.mm.dell.cardmaker2.Constants.REQUEST_CODE;

public class LayerTwoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,SeekBarChange {
    DrawerLayout drawerLayout;
    NavigationView nav1;
    NavigationView nav2;
    ListView listViewnav1;
    ImageView iv_menu1;
    ImageView iv_menu2;
    RecyclerView recyclerView;
    ComponetAdapater adapater;
    Bitmap layertwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layer_two);
        initView();
        setLeftDrawer();
        setRightDrawer();
    }

    private void setRightDrawer() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer);
        nav1 = findViewById(R.id.nav_view1);
        nav2 = findViewById(R.id.nav_view2);
        listViewnav1 = findViewById(R.id.list_navi1);
        listViewnav1.setOnItemClickListener(this);
        iv_menu1 = findViewById(R.id.iv_menu);
        iv_menu2 = findViewById(R.id.iv_menu2);
        iv_menu1.setOnClickListener(this);
        iv_menu2.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerview);

    }

    private void setLeftDrawer() {

        ArrayList<Nev1_Item> listnav1 = new ArrayList<>();
        listnav1.add(new Nev1_Item(getResources().getString(R.string.line), R.drawable.ic_line));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.arc), R.drawable.ic_arc));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.rect), R.drawable.ic_rect));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.pentagon), R.drawable.ic_pentagon));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.diagonal_rect), R.drawable.ic_diagonal_rect));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.ring), R.drawable.ic_ring));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.rect), R.drawable.ic_rect_oval_corner));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.trangle), R.drawable.ic_trangle));

        adapater = new ComponetAdapater(LayerTwoActivity.this, listnav1);
        listViewnav1.setAdapter(adapater);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.iv_menu2:
                if (drawerLayout.isDrawerVisible(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            //line
            case 0:

                break;
            // arc
            case 1:

                break;
            // rect
            case 2:

                break;
            // pentagonal
            case 3:

                break;
            // diagonal
            case 4:

                break;
            // ring
            case 5:

                break;

            // trangle
            case 6:

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            layertwo = data.getParcelableExtra(LAYERTWO_BITMAP_CODE);

        }
    }

    @Override
    public void OnRotate(int r) {

    }

    @Override
    public void OnWidth(int w) {

    }

    @Override
    public void OnHeigth(int h) {

    }

    @Override
    public void OnTextSizeChange(int ts) {

    }

    @Override
    public void OnBrigthness(float b) {

    }

    @Override
    public void OnColorChanges(int i, int i2, int i3) {

    }

    @Override
    public void getBitmapFromGallery(Bitmap bitmap) {

    }

    @Override
    public void setShapeImageView(int color, int side, int borderwidth, int shapeType) {

    }

    @Override
    public void ImageResize(int size) {

    }

}
