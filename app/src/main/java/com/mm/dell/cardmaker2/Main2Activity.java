package com.mm.dell.cardmaker2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mm.dell.cardmaker2.Adapters.BackGroundAdapter;
import com.mm.dell.cardmaker2.Adapters.ComponetAdapater;
import com.mm.dell.cardmaker2.Dialogs.ColorPickerDialog;
import com.mm.dell.cardmaker2.Dialogs.GalleryDialog;
import com.mm.dell.cardmaker2.Dialogs.HeightDialog;
import com.mm.dell.cardmaker2.Dialogs.HeightWidthDialog;
import com.mm.dell.cardmaker2.Dialogs.OpacityDialog;
import com.mm.dell.cardmaker2.Dialogs.RotateDialog;
import com.mm.dell.cardmaker2.Dialogs.ShapedImageViewDialog;
import com.mm.dell.cardmaker2.Dialogs.TextViewSizeDialog;
import com.mm.dell.cardmaker2.Dialogs.WidthDialog;
import com.mm.dell.cardmaker2.fragments.MainFragment;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;
import com.mm.dell.cardmaker2.layout.EffectiveShapeView;

import java.io.IOException;
import java.util.ArrayList;

import static com.mm.dell.cardmaker2.Constants.LAYERTWO_BITMAP_CODE;
import static com.mm.dell.cardmaker2.Constants.NOTDEFINE_BORDER_SIZE;
import static com.mm.dell.cardmaker2.Constants.NOTDEFINE_SIDE;
import static com.mm.dell.cardmaker2.Constants.REQUEST_CODE;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, RecyclerOnItemClickListner, SeekBarChange, TouchedViewFind, View.OnTouchListener {

    /**
     * Util Imageview those provide us multiple functionality
     */

    ImageView iv_rotate;
    ImageView iv_width;
    ImageView iv_height;
    ImageView iv_save;
    ImageView iv_colorpicker;
    ImageView iv_gradiantspicker;
    ImageView iv_gellary;
    ImageView iv_opacity;
    ImageView iv_textsize;
    ImageView iv_deleteview;
    ImageView iv_height_width;


    /**
     * Util Dialog those provide us multiple functionality
     */
    HeightDialog heightDialog;
    WidthDialog widthDialog;
    RotateDialog rotateDialog;
    OpacityDialog opacityDialog;
    ColorPickerDialog colorPickerDialog;
    TextViewSizeDialog textViewSizeDialog;
    HeightWidthDialog heightWidthDialog;

    private int _xDelta;
    private int _yDelta;
    DrawerLayout drawerLayout;
    NavigationView nav1;
    NavigationView nav2;
    ListView listViewnav1;
    ImageView iv_menu1;
    ImageView iv_menu2;
    RecyclerView recyclerView;
    ComponetAdapater adapater;
    Bitmap layertwo;
    ImageView iv_nav2_back;
    ImageView iv_download;
    LinearLayout iv_layerone;
    ImageView layerone;
    AssetManager assetManager;
    LinearLayout ll_util;
    ViewGroup root;
    //    ColorPickerView colorPickerView;
    CardView card_main;


    ArrayList<String> backgroundLists = new ArrayList<String>();
    ArrayList<String> symbolsLists = new ArrayList<String>();


    /**
     * textview
     */
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    TextView tv10;

    /**
     * Symbols imageview
     */

    ImageView iv_s1;
    ImageView iv_s2;
    ImageView iv_s3;
    ImageView iv_s4;
    ImageView iv_s5;
    ImageView iv_s6;
    ImageView iv_s7;
    ImageView iv_s8;
    ImageView iv_s9;
    ImageView iv_s10;

    /**
     * materials imageview
     */

    ImageView iv_m1;
    ImageView iv_m2;
    ImageView iv_m3;
    ImageView iv_m4;
    ImageView iv_m5;


    /**
     * materials imageview
     */

    EffectiveShapeView iv_pic1;
    EffectiveShapeView iv_pic2;
    EffectiveShapeView iv_pic3;
    EffectiveShapeView iv_pic4;
    EffectiveShapeView iv_pic5;


    private View view;
    public static int width = 0;
    public static int height = 0;
    ViewGroup.LayoutParams params;
    ViewGroup.LayoutParams params_symbols;
    ViewGroup.LayoutParams params_material;
    ViewGroup.LayoutParams params_shapimage;


    /**
     * this value show which layer is editing
     */
    private int WorkOnLayerOne = 0;
    private int WorkOnLayerTwo = 0;
    private int WorkOnLayerThree = 0;
    private int WorkOnLayerFour = 0;
    private int WorkOnLayerFive = 0;
    private int WorkOnLayerSix = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initparam();
        assetManager = getAssets();
        setLeftDrawer();
        setRightDrawer();
        initdailogs();
        ll_util.post(() -> {
            width = ll_util.getWidth();
            height = ll_util.getHeight();
        });

    }

    private void initparam() {

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_symbols = new ViewGroup.LayoutParams(40, 40);
        params_material = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_shapimage = new ViewGroup.LayoutParams(90, 90);
        AddTextViews();
        AddSymbolsViews();
        AddMaterialsView();
        AddImages();
    }

    private void initdailogs() {
        heightDialog = new HeightDialog(Main2Activity.this, this);
        widthDialog = new WidthDialog(Main2Activity.this, this);
        rotateDialog = new RotateDialog(Main2Activity.this, this);
        opacityDialog = new OpacityDialog(Main2Activity.this, this);
        colorPickerDialog = new ColorPickerDialog(Main2Activity.this, this);
        textViewSizeDialog = new TextViewSizeDialog(Main2Activity.this, this);
        heightWidthDialog = new HeightWidthDialog(Main2Activity.this, this);

    }

    private void setRightDrawer() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.container2, new MainFragment()).commit();

    }

    @SuppressLint("WrongViewCast")
    private void initView() {

        card_main = findViewById(R.id.card_main);
        ll_util = (LinearLayout) findViewById(R.id.ll_util1);
        root = findViewById(R.id.root);
        iv_layerone = findViewById(R.id.iv_layerone);
        layerone = findViewById(R.id.layerone);
        drawerLayout = findViewById(R.id.drawer);
        nav1 = findViewById(R.id.nav_view1);
        nav2 = findViewById(R.id.nav_view2);
        listViewnav1 = findViewById(R.id.list_navi1);
        listViewnav1.setOnItemClickListener(this);
        iv_nav2_back = findViewById(R.id.iv_backnav2);
        iv_download = findViewById(R.id.iv_download);
        iv_menu1 = findViewById(R.id.iv_menu);
        iv_menu2 = findViewById(R.id.iv_menu2);
        iv_menu1.setOnClickListener(this);
        iv_menu2.setOnClickListener(this);
        iv_download.setOnClickListener(this);
        iv_nav2_back.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerview);

        /**
         * util imageview find*/

        iv_rotate = findViewById(R.id.iv_rotate);
        iv_width = findViewById(R.id.iv_width);
        iv_height = findViewById(R.id.iv_height);
        iv_save = findViewById(R.id.iv_save);
        iv_opacity = findViewById(R.id.iv_opacity);
        iv_textsize = findViewById(R.id.iv_textsized);
        iv_textsize.setOnClickListener(this);
        iv_colorpicker = findViewById(R.id.iv_colorpicker);
        iv_gradiantspicker = findViewById(R.id.iv_gradiantspicker);
        iv_gellary = findViewById(R.id.iv_gellary);
        iv_deleteview = findViewById(R.id.iv_deleteview);
        iv_height_width = findViewById(R.id.iv_height_width);
        iv_height_width.setOnClickListener(this);
        iv_deleteview.setOnClickListener(this);

        iv_rotate.setOnClickListener(this);
        iv_width.setOnClickListener(this);
        iv_height.setOnClickListener(this);
        iv_save.setOnClickListener(this);
        iv_opacity.setOnClickListener(this);
        iv_colorpicker.setOnClickListener(this);
        iv_gradiantspicker.setOnClickListener(this);
        iv_gellary.setOnClickListener(this);

    }

    private void AddMaterialsView() {
        iv_m1 = new ImageView(this);
        iv_m2 = new ImageView(this);
        iv_m3 = new ImageView(this);
        iv_m4 = new ImageView(this);
        iv_m5 = new ImageView(this);
        iv_m1.setLayoutParams(params_material);
        iv_m2.setLayoutParams(params_material);
        iv_m3.setLayoutParams(params_material);
        iv_m4.setLayoutParams(params_material);
        iv_m5.setLayoutParams(params_material);
        iv_m1.setOnTouchListener(this);
        iv_m2.setOnTouchListener(this);
        iv_m3.setOnTouchListener(this);
        iv_m4.setOnTouchListener(this);
        iv_m5.setOnTouchListener(this);
        iv_m1.setImageResource(R.drawable.ic_symbols);
        iv_m2.setImageResource(R.drawable.ic_symbols);
        iv_m3.setImageResource(R.drawable.ic_symbols);
        iv_m4.setImageResource(R.drawable.ic_symbols);
        iv_m5.setImageResource(R.drawable.ic_symbols);
        iv_m1.setVisibility(View.GONE);
        iv_m2.setVisibility(View.GONE);
        iv_m3.setVisibility(View.GONE);
        iv_m4.setVisibility(View.GONE);
        iv_m5.setVisibility(View.GONE);
        root.addView(iv_m1);
        root.addView(iv_m2);
        root.addView(iv_m3);
        root.addView(iv_m4);
        root.addView(iv_m5);
    }

    private void AddImages() {
        iv_pic1 = new EffectiveShapeView(this);
        iv_pic2 = new EffectiveShapeView(this);
        iv_pic3 = new EffectiveShapeView(this);
        iv_pic4 = new EffectiveShapeView(this);
        iv_pic5 = new EffectiveShapeView(this);
        iv_pic1.setLayoutParams(params_shapimage);
        iv_pic2.setLayoutParams(params_shapimage);
        iv_pic3.setLayoutParams(params_shapimage);
        iv_pic4.setLayoutParams(params_shapimage);
        iv_pic5.setLayoutParams(params_shapimage);
        iv_pic1.setOnTouchListener(this);
        iv_pic2.setOnTouchListener(this);
        iv_pic3.setOnTouchListener(this);
        iv_pic4.setOnTouchListener(this);
        iv_pic5.setOnTouchListener(this);
        iv_pic1.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_pic2.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_pic3.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_pic4.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_pic5.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_pic1.setImageResource(R.drawable.background);
        iv_pic2.setImageResource(R.drawable.background);
        iv_pic3.setImageResource(R.drawable.background);
        iv_pic4.setImageResource(R.drawable.background);
        iv_pic5.setImageResource(R.drawable.background);
        iv_pic1.setVisibility(View.GONE);
        iv_pic2.setVisibility(View.GONE);
        iv_pic3.setVisibility(View.GONE);
        iv_pic4.setVisibility(View.GONE);
        iv_pic5.setVisibility(View.GONE);
        root.addView(iv_pic1);
        root.addView(iv_pic2);
        root.addView(iv_pic3);
        root.addView(iv_pic4);
        root.addView(iv_pic5);
    }

    private void AddSymbolsViews() {
        iv_s1 = new ImageView(Main2Activity.this);
        iv_s2 = new ImageView(Main2Activity.this);
        iv_s3 = new ImageView(Main2Activity.this);
        iv_s4 = new ImageView(Main2Activity.this);
        iv_s5 = new ImageView(Main2Activity.this);
        iv_s6 = new ImageView(Main2Activity.this);
        iv_s7 = new ImageView(Main2Activity.this);
        iv_s8 = new ImageView(Main2Activity.this);
        iv_s9 = new ImageView(Main2Activity.this);
        iv_s10 = new ImageView(Main2Activity.this);

        /*
        iv_s1 = new MultiColorIconView(Main2Activity.this, Utils.getAttributeset());
        iv_s2 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s3 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s4 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s5 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s6 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s7 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s8 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s9 = new MultiColorIconView(this, Utils.getAttributeset());
        iv_s10 = new MultiColorIconView(this, Utils.getAttributeset());*/
        iv_s1.setLayoutParams(params_symbols);
        iv_s2.setLayoutParams(params_symbols);
        iv_s3.setLayoutParams(params_symbols);
        iv_s4.setLayoutParams(params_symbols);
        iv_s5.setLayoutParams(params_symbols);
        iv_s6.setLayoutParams(params_symbols);
        iv_s7.setLayoutParams(params_symbols);
        iv_s8.setLayoutParams(params_symbols);
        iv_s9.setLayoutParams(params_symbols);
        iv_s10.setLayoutParams(params_symbols);
        iv_s1.setOnTouchListener(this);
        iv_s2.setOnTouchListener(this);
        iv_s3.setOnTouchListener(this);
        iv_s4.setOnTouchListener(this);
        iv_s5.setOnTouchListener(this);
        iv_s6.setOnTouchListener(this);
        iv_s7.setOnTouchListener(this);
        iv_s8.setOnTouchListener(this);
        iv_s9.setOnTouchListener(this);
        iv_s10.setOnTouchListener(this);
        iv_s1.setImageResource(R.drawable.ic_symbols);
        iv_s2.setImageResource(R.drawable.ic_symbols);
        iv_s3.setImageResource(R.drawable.ic_symbols);
        iv_s4.setImageResource(R.drawable.ic_symbols);
        iv_s5.setImageResource(R.drawable.ic_symbols);
        iv_s6.setImageResource(R.drawable.ic_symbols);
        iv_s7.setImageResource(R.drawable.ic_symbols);
        iv_s8.setImageResource(R.drawable.ic_symbols);
        iv_s9.setImageResource(R.drawable.ic_symbols);
        iv_s10.setImageResource(R.drawable.ic_symbols);
        iv_s1.setVisibility(View.GONE);
        iv_s2.setVisibility(View.GONE);
        iv_s3.setVisibility(View.GONE);
        iv_s4.setVisibility(View.GONE);
        iv_s5.setVisibility(View.GONE);
        iv_s6.setVisibility(View.GONE);
        iv_s7.setVisibility(View.GONE);
        iv_s8.setVisibility(View.GONE);
        iv_s9.setVisibility(View.GONE);
        iv_s10.setVisibility(View.GONE);
        root.addView(iv_s1);
        root.addView(iv_s2);
        root.addView(iv_s3);
        root.addView(iv_s4);
        root.addView(iv_s5);
        root.addView(iv_s6);
        root.addView(iv_s7);
        root.addView(iv_s8);
        root.addView(iv_s9);
        root.addView(iv_s10);

    }

    private void AddTextViews() {
        tv1 = new TextView(this);
        tv2 = new TextView(this);
        tv3 = new TextView(this);
        tv4 = new TextView(this);
        tv5 = new TextView(this);
        tv6 = new TextView(this);
        tv7 = new TextView(this);
        tv8 = new TextView(this);
        tv9 = new TextView(this);
        tv10 = new TextView(this);
        tv1.setLayoutParams(params);
        tv2.setLayoutParams(params);
        tv3.setLayoutParams(params);
        tv4.setLayoutParams(params);
        tv5.setLayoutParams(params);
        tv6.setLayoutParams(params);
        tv7.setLayoutParams(params);
        tv8.setLayoutParams(params);
        tv9.setLayoutParams(params);
        tv10.setLayoutParams(params);

       /* TextViewCompat.setAutoSizeTextTypeWithDefaults(tv1, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv2, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv3, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv4, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv5, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv6, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv7, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv8, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv9, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tv10, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
      */
        tv1.setOnTouchListener(this);
        tv2.setOnTouchListener(this);
        tv3.setOnTouchListener(this);
        tv4.setOnTouchListener(this);
        tv5.setOnTouchListener(this);
        tv6.setOnTouchListener(this);
        tv7.setOnTouchListener(this);
        tv8.setOnTouchListener(this);
        tv9.setOnTouchListener(this);
        tv10.setOnTouchListener(this);
        root.addView(tv1);
        root.addView(tv2);
        root.addView(tv3);
        root.addView(tv4);
        root.addView(tv5);
        root.addView(tv6);
        root.addView(tv7);
        root.addView(tv8);
        root.addView(tv9);
        root.addView(tv10);
    }

    private void setLeftDrawer() {

        ArrayList<Nev1_Item> listnav1 = new ArrayList<>();
        listnav1.add(new Nev1_Item(getResources().getString(R.string.bg_layer1), R.drawable.ic_bg_layer));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.bg_layer2), R.drawable.ic_layer2));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.image), R.drawable.ic_image));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.text), R.drawable.ic_text));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.material), R.drawable.ic_material));
        listnav1.add(new Nev1_Item(getResources().getString(R.string.symbol), R.drawable.ic_symbols));
        adapater = new ComponetAdapater(Main2Activity.this, listnav1);
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

            case R.id.iv_backnav2:
                if (drawerLayout.isDrawerVisible(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;

            /**
             * util imageview clickes*/

            case R.id.iv_width:
                widthDialog.show();
                widthDialog.setMax_Progress(width, 10);

                break;

            case R.id.iv_height:
                heightDialog.show();
                heightDialog.setMax_Progress(height, 10);
                break;
            case R.id.iv_colorpicker:
                colorPickerDialog.show();
                break;
            case R.id.iv_gradiantspicker:

                break;
            case R.id.iv_rotate:
                rotateDialog.show();
                break;
            case R.id.iv_save:
                saveBitmap();
                break;
            case R.id.iv_gellary:
                GalleryDialog galleryDialog = new GalleryDialog(Main2Activity.this, this);
                galleryDialog.show();
                break;

            case R.id.iv_opacity:
                opacityDialog.show();
                break;

            case R.id.iv_textsized:
                textViewSizeDialog.show();
                break;

            case R.id.iv_deleteview:
                deleteView();
                break;

            case R.id.iv_height_width:
                heightWidthDialog.show();
                heightWidthDialog.setMax_Progress(height, 40);
                break;
        }
    }

    private void deleteView() {
        try {
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            /**
             * bg layer one*/
            case 0:
//                setDialodlayerOne();
                WorkOnLayerOne = 1;
                WorkOnLayerThree = 0;
                WorkOnLayerTwo = 0;

                WorkOnLayerFour = 0;
                WorkOnLayerFive = 0;
                WorkOnLayerSix = 0;

                HideNavigationLeft();
                break;
            /**
             *bg layer two
             */
            case 1:
                //startActivityForResult(new Intent(Main2Activity.this, LayerTwoActivity.class), REQUEST_CODE);
                WorkOnLayerOne = 0;
                WorkOnLayerTwo = 1;
                WorkOnLayerThree = 0;
                WorkOnLayerFour = 0;
                WorkOnLayerFive = 0;
                WorkOnLayerSix = 0;

                try {
                    addListToLayerOne();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HideNavigationLeft();
                break;
            /**
             * image*/
            case 2:
                WorkOnLayerOne = 0;
                WorkOnLayerTwo = 0;
                WorkOnLayerThree = 1;
                WorkOnLayerFour = 0;
                WorkOnLayerFive = 0;
                WorkOnLayerSix = 0;
                ShapedImageViewDialog shapedImageViewDialog = new ShapedImageViewDialog(Main2Activity.this, this);
                shapedImageViewDialog.show();
                HideNavigationLeft();
                break;
            /**
             * text*/
            case 3:
                try {

                    WorkOnLayerTwo = 0;
                    WorkOnLayerThree = 0;

                    WorkOnLayerOne = 0;
                    WorkOnLayerFour = 1;
                    WorkOnLayerFive = 0;
                    WorkOnLayerSix = 0;

                    LoadFontList();
                    AddTextViewsText();
                    HideNavigationLeft();
                } catch (Exception e) {
                }

                break;
            /**
             *  material*/
            case 4:
                WorkOnLayerThree = 0;
                WorkOnLayerTwo = 0;
                WorkOnLayerOne = 0;
                WorkOnLayerFour = 0;
                WorkOnLayerFive = 5;
                WorkOnLayerSix = 0;
                try {
                    addMaterialtoImageview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HideNavigationLeft();
                break;
            /**
             * symbols*/
            case 5:
                WorkOnLayerThree = 0;
                WorkOnLayerTwo = 0;
                WorkOnLayerFour = 0;
                WorkOnLayerFive = 0;
                WorkOnLayerSix = 1;
                WorkOnLayerOne = 0;
                try {
                    addSymbolstoImageview();
                    addListToLayerSix();
                    HideNavigationLeft();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    private void addMaterialtoImageview() throws Exception {
        Log.e("addImagetoImageview", "addImagetoImageview   ivs_1 " + iv_s1.getDrawable());
        if (iv_m1.getVisibility() == View.GONE) {
            iv_m1.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            view = iv_m1;
        } else if (iv_m2.getVisibility() == View.GONE) {
            iv_m2.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            view = iv_m2;
        } else if (iv_m3.getVisibility() == View.GONE) {
            iv_m3.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            view = iv_m3;
        } else if (iv_m4.getVisibility() == View.GONE) {
            iv_m4.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            view = iv_m4;
        } else if (iv_m5.getVisibility() == View.GONE) {
            iv_m5.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            view = iv_m5;
        }


    }


    private void addSymbolstoImageview() throws Exception {
        Log.e("addImagetoImageview", "addImagetoImageview   ivs_1 " + iv_s1.getDrawable());
        if (iv_s1.getVisibility() == View.GONE) {
            iv_s1.setVisibility(View.VISIBLE);
            view = iv_s1;
            view.bringToFront();
        } else if (iv_s2.getVisibility() == View.GONE) {
            iv_s2.setVisibility(View.VISIBLE);
            view = iv_s2;
            view.bringToFront();

        } else if (iv_s3.getVisibility() == View.GONE) {
            iv_s3.setVisibility(View.VISIBLE);
            view = iv_s3;
            view.bringToFront();

        } else if (iv_s4.getVisibility() == View.GONE) {
            iv_s4.setVisibility(View.VISIBLE);
            view.bringToFront();
            view = iv_s4;
        } else if (iv_s5.getVisibility() == View.GONE) {
            iv_s5.setVisibility(View.VISIBLE);
            view.bringToFront();
            view = iv_s5;
        } else if (iv_s6.getVisibility() == View.GONE) {
            iv_s6.setVisibility(View.VISIBLE);
            view.bringToFront();
            view = iv_s6;
        } else if (iv_s7.getVisibility() == View.GONE) {
            iv_s7.setVisibility(View.VISIBLE);
            view.bringToFront();
            view = iv_s7;
        } else if (iv_s8.getVisibility() == View.GONE) {
            iv_s8.setVisibility(View.VISIBLE);
            view.bringToFront();
            view = iv_s8;
        } else if (iv_s9.getVisibility() == View.GONE) {
            iv_s9.setVisibility(View.VISIBLE);
            view.bringToFront();
            view = iv_s9;
        } else if (iv_s10.getVisibility() == View.GONE) {
            iv_s10.setVisibility(View.VISIBLE);
            view.bringToFront();
            view = iv_s10;
        }


    }

    private View addImagetoImageview() throws Exception {
        Log.e("addImagetoImageview", "addImagetoImageview   ivs_1 " + iv_s1.getDrawable());
        if (iv_pic1.getVisibility() == View.GONE) {
            iv_pic1.setVisibility(View.VISIBLE);
            view = iv_pic1;
            view.bringToFront();
            return iv_pic1;
        } else if (iv_pic2.getVisibility() == View.GONE) {
            iv_pic2.setVisibility(View.VISIBLE);
            view = iv_pic2;
            view.bringToFront();
            return iv_pic2;
        } else if (iv_pic3.getVisibility() == View.GONE) {
            iv_pic3.setVisibility(View.VISIBLE);
            view = iv_pic3;
            view.bringToFront();
            return iv_pic3;
        } else if (iv_pic4.getVisibility() == View.GONE) {
            iv_pic4.setVisibility(View.VISIBLE);
            view = iv_pic4;
            view.bringToFront();
            return iv_pic4;
        } else if (iv_pic5.getVisibility() == View.GONE) {
            iv_pic5.setVisibility(View.VISIBLE);
            view = iv_pic5;
            view.bringToFront();
            return iv_pic5;
        } else {
            return null;
        }
    }


    private void HideNavigationLeft() {
        try {
            if (drawerLayout.isDrawerVisible(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        } catch (Exception e) {
        }
    }

    private void AddTextViewsText() throws Exception {

        if (tv1.getText().toString().isEmpty()) {
            EditTextViewDialog(tv1);
            view.bringToFront();
            view = tv1;
        } else if (tv2.getText().toString().isEmpty()) {
            view = tv2;
            view.bringToFront();
            EditTextViewDialog(tv2);
        } else if (tv3.getText().toString().isEmpty()) {
            view = tv3;
            view.bringToFront();
            EditTextViewDialog(tv3);
        } else if (tv4.getText().toString().isEmpty()) {
            view = tv4;
            view.bringToFront();
            EditTextViewDialog(tv4);
        } else if (tv5.getText().toString().isEmpty()) {
            view = tv5;
            view.bringToFront();
            EditTextViewDialog(tv5);
        } else if (tv6.getText().toString().isEmpty()) {
            view = tv6;
            view.bringToFront();
            EditTextViewDialog(tv6);
        } else if (tv7.getText().toString().isEmpty()) {
            EditTextViewDialog(tv7);
            view.bringToFront();
            view = tv7;
        } else if (tv8.getText().toString().isEmpty()) {
            EditTextViewDialog(tv8);
            view.bringToFront();
            view = tv8;
        } else if (tv9.getText().toString().isEmpty()) {
            EditTextViewDialog(tv9);
            view.bringToFront();
            view = tv9;
        } else if (tv10.getText().toString().isEmpty()) {
            EditTextViewDialog(tv10);
            view.bringToFront();
            view = tv10;
        }

    }

    private void EditTextViewDialog(TextView tv) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.edittextview);
        EditText et_entertext = dialog.findViewById(R.id.et_enter);
        TextView tv_ok = dialog.findViewById(R.id.tv_ok);
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_ok.setOnClickListener(v -> {
            if (!et_entertext.getText().toString().isEmpty()) {
                tv.setLayoutParams(param);
                tv.setText(et_entertext.getText().toString());
            }
            dialog.dismiss();
        });
        tv_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    private void addListToLayerOne() throws IOException {
        backgroundLists.clear();
        BackGroundAdapter adapter = new BackGroundAdapter(backgroundLists, this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        String[] list = assetManager.list("backpics");
        for (String file : list) {
            backgroundLists.add("backpics/" + file);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * add list of symbols
     */

    private void addListToLayerSix() throws IOException {

        symbolsLists.clear();
        BackGroundAdapter adapter = new BackGroundAdapter(symbolsLists, this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        String[] list = assetManager.list("symbols");
        for (String file : list) {
            symbolsLists.add("symbols/" + file);
        }
        adapter.notifyDataSetChanged();
    }

    private void setDialodlayerOne() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.layerone);
        ImageView iv_rect = dialog.findViewById(R.id.iv_rounded);
        ImageView iv_rectround = dialog.findViewById(R.id.iv_roundedrect);
        iv_rect.setOnClickListener(v -> {
            //layerone.setRadius(10f);
            dialog.dismiss();
        });
        iv_rectround.setOnClickListener(v -> {
            //layerone.setRadius(5f);
            dialog.dismiss();
        });

        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            layertwo = data.getParcelableExtra(LAYERTWO_BITMAP_CODE);

        }
    }


    private void LoadFontList() {
        ArrayList<FontModel> fontList = new ArrayList<>();
        fontList.add(new FontModel("abeezee", ResourcesCompat.getFont(getApplicationContext(), R.font.abeezee)));
        fontList.add(new FontModel("abhaya_libre", ResourcesCompat.getFont(getApplicationContext(), R.font.abhaya_libre)));
        fontList.add(new FontModel("abril_fatface", ResourcesCompat.getFont(getApplicationContext(), R.font.abril_fatface)));
        fontList.add(new FontModel("aclonica", ResourcesCompat.getFont(getApplicationContext(), R.font.aclonica)));
        fontList.add(new FontModel("acme", ResourcesCompat.getFont(getApplicationContext(), R.font.acme)));
        fontList.add(new FontModel("advent_pro_thin", ResourcesCompat.getFont(getApplicationContext(), R.font.advent_pro_thin)));
        fontList.add(new FontModel("aguafina_script", ResourcesCompat.getFont(getApplicationContext(), R.font.aguafina_script)));
        fontList.add(new FontModel("akronim", ResourcesCompat.getFont(getApplicationContext(), R.font.akronim)));
        fontList.add(new FontModel("aladin", ResourcesCompat.getFont(getApplicationContext(), R.font.aladin)));
        fontList.add(new FontModel("aldrich", ResourcesCompat.getFont(getApplicationContext(), R.font.aldrich)));
        fontList.add(new FontModel("alfa_slab_one", ResourcesCompat.getFont(getApplicationContext(), R.font.alfa_slab_one)));
        fontList.add(new FontModel("allan", ResourcesCompat.getFont(getApplicationContext(), R.font.allan)));
        fontList.add(new FontModel("allura", ResourcesCompat.getFont(getApplicationContext(), R.font.allura)));
        fontList.add(new FontModel("almendra_display", ResourcesCompat.getFont(getApplicationContext(), R.font.almendra_display)));
        fontList.add(new FontModel("architects_daughter", ResourcesCompat.getFont(getApplicationContext(), R.font.architects_daughter)));
        fontList.add(new FontModel("arizonia", ResourcesCompat.getFont(getApplicationContext(), R.font.arizonia)));
        fontList.add(new FontModel("astloch", ResourcesCompat.getFont(getApplicationContext(), R.font.astloch)));
        fontList.add(new FontModel("bangers", ResourcesCompat.getFont(getApplicationContext(), R.font.bangers)));
        fontList.add(new FontModel("bonbon", ResourcesCompat.getFont(getApplicationContext(), R.font.bonbon)));
        fontList.add(new FontModel("bungee_hairline", ResourcesCompat.getFont(getApplicationContext(), R.font.bungee_hairline)));
        fontList.add(new FontModel("bungee_inline", ResourcesCompat.getFont(getApplicationContext(), R.font.bungee_inline)));
        fontList.add(new FontModel("bungee_shade", ResourcesCompat.getFont(getApplicationContext(), R.font.bungee_shade)));
        fontList.add(new FontModel("butcherman", ResourcesCompat.getFont(getApplicationContext(), R.font.butcherman)));
        fontList.add(new FontModel("butterfly_kids", ResourcesCompat.getFont(getApplicationContext(), R.font.butterfly_kids)));
        fontList.add(new FontModel("codystar_light", ResourcesCompat.getFont(getApplicationContext(), R.font.codystar_light)));
        fontList.add(new FontModel("diplomata_sc", ResourcesCompat.getFont(getApplicationContext(), R.font.diplomata_sc)));
        fontList.add(new FontModel("ewert", ResourcesCompat.getFont(getApplicationContext(), R.font.ewert)));
        fontList.add(new FontModel("faster_one", ResourcesCompat.getFont(getApplicationContext(), R.font.faster_one)));
        fontList.add(new FontModel("fontdiner_swanky", ResourcesCompat.getFont(getApplicationContext(), R.font.fontdiner_swanky)));
        fontList.add(new FontModel("monoton", ResourcesCompat.getFont(getApplicationContext(), R.font.monoton)));
        fontList.add(new FontModel("waiting_for_the_sunrise", ResourcesCompat.getFont(getApplicationContext(), R.font.waiting_for_the_sunrise)));
        fontList.add(new FontModel("warnes", ResourcesCompat.getFont(getApplicationContext(), R.font.warnes)));
        RecylerBotttomAdapter adapter = new RecylerBotttomAdapter(this, fontList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemClickLister(int position, Typeface typeface) {
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        if (view != null) {
            view.post(() -> {
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    tv.setTypeface(typeface);
                }
            });
        }

    }

    @Override
    public void getLayerOneImage(Bitmap bitmap) {
        //  iv_layerone.setImageBitmap(bitmap);
        Log.e("getLayerOneImage", "        bitmap  " + bitmap);
        Log.e("getLayerOneImage", "        WorkOnLayerOne  " + WorkOnLayerOne);
        Log.e("getLayerOneImage", "        WorkOnLayerSix  " + WorkOnLayerSix);

        if (WorkOnLayerTwo == 1) {
            iv_layerone.post(() -> {
                Drawable drawable = new BitmapDrawable(bitmap);
                iv_layerone.setBackground(drawable);
            });
        } else if (WorkOnLayerSix == 1) {
            if (view != null) {
                view.post(() -> {
                    if (view instanceof ImageView) {
                        ImageView iv = (ImageView) view;
                        iv.setImageBitmap(bitmap);
                    }
                });
            }
        }
    }

    @Override
    public void OnRotate(int r) {
        if (view != null) {
            view.post(() -> {
                if (WorkOnLayerOne == 1 || WorkOnLayerTwo == 1) {
                    Toast.makeText(this, "Layerone and layertwo can't rotate!", Toast.LENGTH_SHORT).show();
                } else {
                    view.setRotation(r);
                }
            });
        }
    }

    @Override
    public void OnWidth(int w) {
        try {
            if (view != null) {
                view.post(() -> {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    params.width = w;
                    view.setLayoutParams(params);
                });
            }
        } catch (Exception e) {
        }
    }


    @Override
    public void OnHeigth(int h) {
        Log.d("OnHeigth", "   >     " + h);

        try {
            if (view != null) {
                if (!(view instanceof TextView)) {
                    view.post(() -> {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        params.height = h;
                        view.setLayoutParams(params);
                    });
                }
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void OnTextSizeChange(int ts) {
        Log.d("OnTextSizeChange", "   >     " + ts);
        try {
            if (view instanceof TextView) {
                view.post(() -> {
                    TextView tv = (TextView) view;
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, ts);
                    tv.invalidate();
                });
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void OnBrigthness(float b) {
        Log.d("OnBrigthness", "   >     " + b);

        if (WorkOnLayerOne == 1) {

        } else {
            if (view != null) {
                view.post(() -> {
                    view.setAlpha(b);
                });
            }
        }
    }

    @Override
    public void OnColorChanges(int i, int i2, int i3) {
        Log.e("OnColorChanges", ">> i3   " + i3);
        if (WorkOnLayerOne == 1) {
            try {

                layerone.post(() -> {
                    //   Toast.makeText(this, "" + i3, Toast.LENGTH_SHORT).show();
                    layerone.setBackgroundColor(i3);
                });
            } catch (Exception e) {
            }

        } else if (WorkOnLayerSix == 1) {
            try {
                view.post(() -> {
                    if (view instanceof ImageView) {
                        ImageView mv = (ImageView) view;
                        mv.setColorFilter(i3, PorterDuff.Mode.SRC_IN);
                    }
                });
            } catch (Exception e) {
            }
        } else {
            if (view instanceof TextView && WorkOnLayerFour == 1) {
                view.post(() -> {
                    try {
                        TextView tv = (TextView) view;
                        tv.setTextColor(i3);
                    } catch (Exception e) {
                    }
                });
            } else if (view instanceof EffectiveShapeView) {
                view.post(() -> {
                    EffectiveShapeView shapeView = (EffectiveShapeView) view;
                    // shapeView.setImageDrawable(R.drawable);
                });
            }
        }

    }

    @Override
    public void getBitmapFromGallery(Bitmap bitmap) {
        Log.e("getBitmapFromGallery", "getBitmapFromGallery is called");
        if (view != null) {
            Log.e("getBitmapFromGallery", "getBitmapFromGallery is called");
            EffectiveShapeView shapeView = (EffectiveShapeView) view;
            shapeView.setImageBitmap(getRoundedCornerBitmap(bitmap, 0));
        }
    }

    @Override
    public void setShapeImageView(int color, int side, int borderwidth, int shapeType) {

        Log.e("setShapeImageView", "side>>  -  " + side);
        Log.e("setShapeImageView", "shapeType>>  -  " + shapeType);
        Log.e("setShapeImageView", "setShapeImageView>>  -  " + color);
        try {
            view = addImagetoImageview();
            if (view != null) {
                view.post(() -> {
                    if (view instanceof EffectiveShapeView) {
                        EffectiveShapeView shape = (EffectiveShapeView) view;

                        if (side == NOTDEFINE_SIDE) {
                            shape.changeShapeType(shapeType);
                            if (borderwidth != NOTDEFINE_BORDER_SIZE) {
                                shape.setBorderColor(color);
                                shape.setBorderWidth(borderwidth);

                            }
                        } else {
                            shape.changeShapeType(shapeType, side);
                            if (borderwidth != NOTDEFINE_BORDER_SIZE) {
                                shape.setBorderColor(color);
                                shape.setBorderWidth(borderwidth);
                            }
                        }
                    }

                });
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void ImageResize(int size) {
        Log.e("ImageResize", "ImageResize is called");

        if (view != null && (WorkOnLayerFive == 1 || WorkOnLayerSix == 1 || WorkOnLayerThree == 1)) {
            view.post(() -> {
                if (view instanceof EffectiveShapeView) {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    params.height = size;
                    params.width = size;
                    EffectiveShapeView shapeview = (EffectiveShapeView) view;
                    Bitmap bitmap = ((BitmapDrawable) shapeview.getDrawable()).getBitmap();
                    shapeview.setScaleType(ImageView.ScaleType.FIT_XY);
                    shapeview.setImageBitmap(bitmap);
                    shapeview.setLayoutParams(params);
                    shapeview.invalidate();

                } else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    params.height = size;
                    params.width = size;
                    view.setLayoutParams(params);
                }
            });
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void getLastTouchedItem(View view) {
        Log.e("getLastTouchedItem", "view   " + view);
        if (view instanceof TextView) {
            LoadFontList();
        } else if (view instanceof ImageView) {
        }
        this.view = view;
//         paramtoresize = (LinearLayout.LayoutParams) this.view.getLayoutParams();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.view = v;
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                //  prepareTouch(X, Y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                root.bringChildToFront(v);
                v.setLayoutParams(layoutParams);
                break;
        }
        root.invalidate();
        return true;
    }


    public void saveBitmap() {
        try {
            card_main.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(card_main.getDrawingCache());
            card_main.setDrawingCacheEnabled(false);
            Log.e("saveBitmap", "saveBitmap   bitmap   " + bitmap);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    class SaveBitmap extends AsyncTask {

        Bitmap bitmap;
        String status;


        public SaveBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            status = "Try Again!";

            /*try {
                String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
                File imageFile = new File(mPath);
                FileOutputStream outputStream = null;

                outputStream = new FileOutputStream(imageFile);

                int quality = 100;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (FileNotFoundException e) {
                status = "Failed to save";
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                status = "Failed to save";
            }*/
            //   openScreenshot(imageFile);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Toast.makeText(Main2Activity.this, "" + status, Toast.LENGTH_SHORT).show();
        }
    }


    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}
