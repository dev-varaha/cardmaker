package com.mm.dell.cardmaker2.activities;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mm.dell.cardmaker2.Adapters.ComponetAdapater;
import com.mm.dell.cardmaker2.Nev1_Item;
import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.RecyclerOnItemClickListner;
import com.mm.dell.cardmaker2.Utils.OntextChange;
import com.mm.dell.cardmaker2.fragments.BackGroundFragment;
import com.mm.dell.cardmaker2.Dialogs.EditTextViewDialog;
import com.mm.dell.cardmaker2.fragments.MaterialsFragment;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;
import com.mm.dell.cardmaker2.fragments.ShapeFragment;
import com.mm.dell.cardmaker2.fragments.SymbolsFragment;
import com.mm.dell.cardmaker2.fragments.TextFragmentFragment;
import com.mm.dell.cardmaker2.layout.EffectiveShapeView;

import java.util.ArrayList;

import static com.mm.dell.cardmaker2.Constants.NOTDEFINE_SIDE;

public class CardMainActivity extends AppCompatActivity implements SeekBarChange, View.OnTouchListener, OntextChange, RecyclerOnItemClickListner {
    RecyclerView recyclerlist;
    ListView listView;
    int containerId;
    public View selecteview;
    ViewGroup root;
    private int _xDelta;
    private int _yDelta;
    CardView card_root;
    ImageView layerone;


    /**
     * layout  parameter
     */
    ViewGroup.LayoutParams params;
    ViewGroup.LayoutParams params_symbols;
    ViewGroup.LayoutParams params_material;
    ViewGroup.LayoutParams params_shapimage;

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
     * shape view
     */
    EffectiveShapeView iv_pic1;
    EffectiveShapeView iv_pic2;
    EffectiveShapeView iv_pic3;
    EffectiveShapeView iv_pic4;
    EffectiveShapeView iv_pic5;

    public int width;
    public int height;
    private final String TAG = CardMainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_main);
        initView();
        getWidthHeight();
        initparam();
        getPermission();
        listView.setOnItemClickListener(onItemClickListener);
    }

    private void getPermission() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    LinearLayout ll_util1;

    private void getWidthHeight() {
        ll_util1.post(() -> {
            width = ll_util1.getWidth();
            height = ll_util1.getHeight();
        });
    }

    private void initView() {
        containerId = R.id.root_container;
        ll_util1 = findViewById(R.id.ll_util1);
        listView = findViewById(R.id.listview);
        card_root = findViewById(R.id.card_root);
        layerone = findViewById(R.id.layerone);
        root = findViewById(R.id.root);
        setComponentAdapter();

    }

    private void setComponentAdapter() {
        ArrayList<Nev1_Item> componetlist = new ArrayList<>();
        componetlist.add(new Nev1_Item(getResources().getString(R.string.bg_layer1), R.drawable.ic_bg_layer));
        componetlist.add(new Nev1_Item(getResources().getString(R.string.text), R.drawable.ic_text));
        componetlist.add(new Nev1_Item(getResources().getString(R.string.material), R.drawable.ic_material));
        componetlist.add(new Nev1_Item(getResources().getString(R.string.symbol), R.drawable.ic_symbols));
        componetlist.add(new Nev1_Item(getResources().getString(R.string.symbol), R.drawable.ic_symbols));
        ComponetAdapater adapater = new ComponetAdapater(CardMainActivity.this, componetlist);
        listView.setAdapter(adapater);
    }


    AdapterView.OnItemClickListener onItemClickListener = (parent, view, position, id) -> {
        switch (position) {
            case 0:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_left);
                ft.replace(containerId, new BackGroundFragment()).commit();

                break;
            case 1:

                try {
                    FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_left);
                    ft1.replace(containerId, new TextFragmentFragment()).commit();

                } catch (Exception e) {
                    Log.e("Exception", " while adding textview e - " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_left);
                    ft2.replace(containerId, new MaterialsFragment()).commit();

                } catch (Exception e) {
                    Log.e("Exception", " while adding materials e - " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_left);
                    ft3.replace(containerId, new SymbolsFragment()).commit();

                } catch (Exception e) {
                    Log.e("Exception", " while adding symbols e - " + e.getMessage());
                    e.printStackTrace();
                }
                break;

            case 4:
                try {
                    FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_left);
                    ft4.replace(containerId, new ShapeFragment()).commit();

                } catch (Exception e) {
                    Log.e("Exception", " while adding symbols e - " + e.getMessage());
                    e.printStackTrace();
                }
                break;
        }
    };

    @Override
    public void OnRotate(int r) {
        new Handler().post(() -> {
            if (selecteview != null) {
                selecteview.setRotation(r);
            }
        });
    }

    @Override
    public void OnWidth(int w) {

    }

    @Override
    public void OnHeigth(int h) {

    }

    @Override
    public void OnTextSizeChange(int ts) {
        new Handler().post(() -> {
            if (selecteview instanceof TextView) {
                TextView textView = (TextView) selecteview;
                textView.setTextSize(ts);
            }
        });
    }

    @Override
    public void OnBrigthness(float b) {
        new Handler().post(() -> {
            selecteview.setAlpha(b);
        });
    }

    @Override
    public void OnColorChanges(int i, int i2, int i3) {
        new Handler().post(() -> {
            if (selecteview instanceof TextView) {
                TextView textView = (TextView) selecteview;
                textView.setTextColor(i3);
            } else if (selecteview instanceof ImageView) {
                ImageView mv = (ImageView) selecteview;
                mv.setColorFilter(i3, PorterDuff.Mode.SRC_IN);

            }
        });
    }

    @Override
    public void getBitmapImage(Bitmap bitmap) {
        if (selecteview != null) {
            if (selecteview instanceof ImageView) {
                ImageView imageView = (ImageView) selecteview;
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void setShapeImageView(int side, int shapeType) {
        if (selecteview != null) {
            selecteview.post(() -> {
                if (selecteview instanceof EffectiveShapeView) {
                    EffectiveShapeView shapeView = (EffectiveShapeView) selecteview;
                    if (side == NOTDEFINE_SIDE) {
                        shapeView.changeShapeType(shapeType);
                    } else {
                        shapeView.changeShapeType(shapeType, side);
                    }
                }
            });
        }
    }

    @Override
    public void ImageResize(int size) {
        try {
            if (selecteview != null) {
                selecteview.post(() -> {
                    if (selecteview instanceof EffectiveShapeView) {
                        Log.e(TAG, "selecteview instanceof EffectiveShapeView");
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) selecteview.getLayoutParams();
                        params.height = size;
                        params.width = size;
                        EffectiveShapeView shapeview = (EffectiveShapeView) selecteview;
                        Bitmap bitmap = ((BitmapDrawable) shapeview.getDrawable()).getBitmap();
                        shapeview.setScaleType(ImageView.ScaleType.FIT_XY);
                        shapeview.setImageBitmap(bitmap);
                        shapeview.setLayoutParams(params);
                        shapeview.invalidate();

                    } else if (selecteview instanceof ImageView) {
                        Log.e(TAG, "selecteview instanceof ImageView");

                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) selecteview.getLayoutParams();
                        params.height = size;
                        params.width = size;
                        ImageView imageView = (ImageView) selecteview;
                        imageView.setLayoutParams(params);
                    }


                });
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void DeleteView() {
        if (selecteview != null) {
            try {
                selecteview.setVisibility(View.GONE);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void ImageBitmap(Bitmap bitmap) {
        if (selecteview instanceof EffectiveShapeView) {
            EffectiveShapeView shapeView = (EffectiveShapeView) selecteview;
            shapeView.setImageBitmap(bitmap);
        }
    }

    public void AddMaterialsView() {
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

    private void AddSymbolsViews() {
        iv_s1 = new ImageView(CardMainActivity.this);
        iv_s2 = new ImageView(CardMainActivity.this);
        iv_s3 = new ImageView(CardMainActivity.this);
        iv_s4 = new ImageView(CardMainActivity.this);
        iv_s5 = new ImageView(CardMainActivity.this);
        iv_s6 = new ImageView(CardMainActivity.this);
        iv_s7 = new ImageView(CardMainActivity.this);
        iv_s8 = new ImageView(CardMainActivity.this);
        iv_s9 = new ImageView(CardMainActivity.this);
        iv_s10 = new ImageView(CardMainActivity.this);
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


    public void AddTextViewsText() {

        if (tv1.getText().toString().isEmpty()) {
            new EditTextViewDialog(this, null, this).show();
            selecteview = tv1;
            selecteview.bringToFront();
        } else if (tv2.getText().toString().isEmpty()) {
            selecteview = tv2;
            selecteview.bringToFront();
            new EditTextViewDialog(this, null, this).show();

        } else if (tv3.getText().toString().isEmpty()) {
            selecteview = tv3;
            selecteview.bringToFront();
            new EditTextViewDialog(this, null, this).show();

        } else if (tv4.getText().toString().isEmpty()) {
            selecteview = tv4;
            selecteview.bringToFront();
            new EditTextViewDialog(this, null, this).show();

        } else if (tv5.getText().toString().isEmpty()) {
            selecteview = tv5;
            selecteview.bringToFront();
            new EditTextViewDialog(this, null, this).show();

        } else if (tv6.getText().toString().isEmpty()) {
            selecteview = tv6;
            selecteview.bringToFront();
            new EditTextViewDialog(this, null, this).show();

        } else if (tv7.getText().toString().isEmpty()) {
            new EditTextViewDialog(this, null, this).show();
            selecteview = tv7;
            selecteview.bringToFront();
        } else if (tv8.getText().toString().isEmpty()) {
            new EditTextViewDialog(this, null, this).show();
            selecteview = tv8;
            selecteview.bringToFront();
        } else if (tv9.getText().toString().isEmpty()) {
            new EditTextViewDialog(this, null, this).show();
            selecteview = tv9;
            selecteview.bringToFront();
        } else if (tv10.getText().toString().isEmpty()) {
            new EditTextViewDialog(this, null, this).show();
            selecteview = tv10;
            selecteview.bringToFront();

        }

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

     /*   TextViewCompat.setAutoSizeTextTypeWithDefaults(tv1, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
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

    public void addMaterialtoImageview() throws Exception {
        Log.e("addImagetoImageview", "addImagetoImageview   ivs_1 " + iv_s1.getDrawable());
        if (iv_m1.getVisibility() == View.GONE) {
            iv_m1.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            selecteview = iv_m1;
        } else if (iv_m2.getVisibility() == View.GONE) {
            iv_m2.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            selecteview = iv_m2;
        } else if (iv_m3.getVisibility() == View.GONE) {
            iv_m3.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            selecteview = iv_m3;
        } else if (iv_m4.getVisibility() == View.GONE) {
            iv_m4.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            selecteview = iv_m4;
        } else if (iv_m5.getVisibility() == View.GONE) {
            iv_m5.setVisibility(View.VISIBLE);
            iv_m1.bringToFront();
            selecteview = iv_m5;
        }


    }


    public void addSymbolstoImageview() throws Exception {
        Log.e("addImagetoImageview", "addImagetoImageview   ivs_1 " + iv_s1.getDrawable());
        if (iv_s1.getVisibility() == View.GONE) {
            iv_s1.setVisibility(View.VISIBLE);
            iv_s1.setImageResource(R.drawable.ic_symbols);
            selecteview = iv_s1;
            selecteview.bringToFront();
        } else if (iv_s2.getVisibility() == View.GONE) {
            iv_s2.setVisibility(View.VISIBLE);
            iv_s2.setImageResource(R.drawable.ic_symbols);
            selecteview = iv_s2;
            selecteview.bringToFront();
        } else if (iv_s3.getVisibility() == View.GONE) {
            iv_s3.setVisibility(View.VISIBLE);
            iv_s3.setImageResource(R.drawable.ic_symbols);
            selecteview = iv_s3;
            selecteview.bringToFront();
        } else if (iv_s4.getVisibility() == View.GONE) {
            iv_s4.setVisibility(View.VISIBLE);
            iv_s4.setImageResource(R.drawable.ic_symbols);
            selecteview.bringToFront();
            selecteview = iv_s4;
        } else if (iv_s5.getVisibility() == View.GONE) {
            iv_s5.setVisibility(View.VISIBLE);
            iv_s5.setImageResource(R.drawable.ic_symbols);
            selecteview.bringToFront();
            selecteview = iv_s5;
        } else if (iv_s6.getVisibility() == View.GONE) {
            iv_s6.setVisibility(View.VISIBLE);
            iv_s6.setImageResource(R.drawable.ic_symbols);
            selecteview.bringToFront();
            selecteview = iv_s6;
        } else if (iv_s7.getVisibility() == View.GONE) {
            iv_s7.setVisibility(View.VISIBLE);
            iv_s7.setImageResource(R.drawable.ic_symbols);
            selecteview.bringToFront();
            selecteview = iv_s7;
        } else if (iv_s8.getVisibility() == View.GONE) {
            iv_s8.setVisibility(View.VISIBLE);
            iv_s8.setImageResource(R.drawable.ic_symbols);
            selecteview.bringToFront();
            selecteview = iv_s8;
        } else if (iv_s9.getVisibility() == View.GONE) {
            iv_s9.setVisibility(View.VISIBLE);
            iv_s9.setImageResource(R.drawable.ic_symbols);
            selecteview.bringToFront();
            selecteview = iv_s9;
        } else if (iv_s10.getVisibility() == View.GONE) {
            iv_s10.setVisibility(View.VISIBLE);
            iv_s10.setImageResource(R.drawable.ic_symbols);
            selecteview.bringToFront();
            selecteview = iv_s10;
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.selecteview = v;
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


    private void initparam() {

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_symbols = new ViewGroup.LayoutParams(60, 60);
        params_material = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_shapimage = new ViewGroup.LayoutParams(90, 90);

        AddTextViews();
        AddSymbolsViews();
        AddMaterialsView();
        AddImages();
    }

    public void saveBitmap() {
        try {
            card_root.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(card_root.getDrawingCache());
            card_root.setDrawingCacheEnabled(false);
            Log.e("saveBitmap", "saveBitmap   bitmap   " + bitmap);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTextChanged(String text) {
        if (selecteview != null) {
            TextView tv = (TextView) selecteview;
            tv.setText(text);
        } else {

        }
    }

    @Override
    public void OnItemClickLister(int position, Typeface typeface) {
        new Handler().post(() -> {
            try {
                if (selecteview instanceof TextView) {
                    TextView tv = (TextView) selecteview;
                    tv.setTypeface(typeface);
                }
            } catch (Exception e) {
            }
        });
    }

    @Override
    public void getLayerOneImage(Bitmap bitmap) {
        layerone.setImageBitmap(bitmap);
    }

    public View addImagetoImageview() throws Exception {
        Log.e("addImagetoImageview", "addImagetoImageview   ivs_1 " + iv_s1.getDrawable());
        if (iv_pic1.getVisibility() == View.GONE) {
            iv_pic1.setVisibility(View.VISIBLE);
            iv_pic1.changeShapeType(EffectiveShapeView.Shape.CIRCLE);

            selecteview = iv_pic1;
            selecteview.bringToFront();
            return iv_pic1;
        } else if (iv_pic2.getVisibility() == View.GONE) {
            iv_pic2.setVisibility(View.VISIBLE);
            iv_pic2.changeShapeType(EffectiveShapeView.Shape.CIRCLE);

            selecteview = iv_pic2;
            selecteview.bringToFront();
            return iv_pic2;
        } else if (iv_pic3.getVisibility() == View.GONE) {
            iv_pic3.setVisibility(View.VISIBLE);
            iv_pic3.changeShapeType(EffectiveShapeView.Shape.CIRCLE);

            selecteview = iv_pic3;
            selecteview.bringToFront();
            return iv_pic3;
        } else if (iv_pic4.getVisibility() == View.GONE) {
            iv_pic4.setVisibility(View.VISIBLE);
            iv_pic4.changeShapeType(EffectiveShapeView.Shape.CIRCLE);

            selecteview = iv_pic4;
            selecteview.bringToFront();
            return iv_pic4;
        } else if (iv_pic5.getVisibility() == View.GONE) {
            iv_pic5.setVisibility(View.VISIBLE);
            iv_pic5.changeShapeType(EffectiveShapeView.Shape.CIRCLE);

            selecteview = iv_pic5;
            selecteview.bringToFront();
            return iv_pic5;
        } else {
            return null;
        }
    }

}
