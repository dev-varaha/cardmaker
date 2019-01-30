package com.mm.dell.cardmaker2.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;
import com.mm.dell.cardmaker2.layout.EffectiveShapeView;

import static com.mm.dell.cardmaker2.Constants.DEFAULT_BORDER_COLOR;
import static com.mm.dell.cardmaker2.Constants.DEFAULT_BORDER_SIZE;
import static com.mm.dell.cardmaker2.Constants.NOTDEFINE_BORDER_SIZE;
import static com.mm.dell.cardmaker2.Constants.NOTDEFINE_SIDE;
import static com.mm.dell.cardmaker2.Constants.UNDEFINE_SHAPE_TYPE;

public class ShapedImageViewDialog extends Dialog implements View.OnClickListener, ColorPicker2dialog.colorpickercallback {
    Context context;
    SeekBarChange seekBarChange;
    TextView tv_ok;
    TextView tv_cancel;
    RecyclerView recyclerView;
    LinearLayout ll_colorpicker;
    CheckBox checkBox;
    private int shapeType;
    private int bordercolor = DEFAULT_BORDER_COLOR;
    private int side = NOTDEFINE_SIDE;
    private int lshapeType = UNDEFINE_SHAPE_TYPE;

    public ShapedImageViewDialog(Context context, SeekBarChange seekBarChange) {
        super(context);
        this.context = context;
        this.seekBarChange = seekBarChange;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.shapephotoslayout);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_ok = findViewById(R.id.tv_ok);
        ll_colorpicker = findViewById(R.id.ll_colorpicker);
        checkBox = findViewById(R.id.checkbox);
        recyclerView = findViewById(R.id.rv);
        tv_cancel.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
        ll_colorpicker.setOnClickListener(this);
        ShapeImageAdapter adapter = new ShapeImageAdapter(context);
        LinearLayoutManager manager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                this.dismiss();
                break;
            case R.id.tv_ok:
                Log.e("TV_OK","<<<<<<>>>>>>> bordercolor   "+bordercolor);
                Log.e("TV_OK","<<<<<<>>>>>>> side   "+side);
                Log.e("TV_OK","<<<<<<>>>>>>>  DEFAULT_BORDER_SIZE  "+DEFAULT_BORDER_SIZE);
                Log.e("TV_OK","<<<<<<>>>>>>>  shapeType  "+shapeType);
                if (checkBox.isChecked()) {
                //             seekBarChange.setShapeImageView(bordercolor,side,DEFAULT_BORDER_SIZE,shapeType);
                } else {
             //       seekBarChange.setShapeImageView(bordercolor,side,NOTDEFINE_BORDER_SIZE,shapeType);
               }
                this.dismiss();
                break;
            case R.id.ll_colorpicker:
                ColorPicker2dialog dialog = new ColorPicker2dialog(context, this);
                dialog.show();
                break;
        }
    }

    @Override
    public void getColor(int i, int i1, int i2) {
        bordercolor = i2;
    }

    class ShapeImageAdapter extends RecyclerView.Adapter<ShapeImageAdapter.ImageHolder> {
        Context context;


        public ShapeImageAdapter(Context context) {
            this.context = context;
        }


        @NonNull
        @Override
        public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.imagelayout1, viewGroup, false);
            return new ImageHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageHolder imageHolder, int i) {
            try {
                imageHolder.shapeView.setOnClickListener(v -> {
                    switch (i) {
                        case 0:
                            shapeType = EffectiveShapeView.Shape.CIRCLE;
                            side = NOTDEFINE_SIDE;
                            break;
                        case 1:
                            shapeType = EffectiveShapeView.Shape.RECTANGLE;
                            side = NOTDEFINE_SIDE;
                            break;
                        case 2:
                            shapeType = EffectiveShapeView.Shape.ROUND_RECTANGLE;
                            side = NOTDEFINE_SIDE;
                            break;
                        case 3:
                            shapeType = EffectiveShapeView.Shape.SQUARE;
                            side = NOTDEFINE_SIDE;
                            break;
                        case 4:
                            shapeType = EffectiveShapeView.Shape.POLYGON;
                            side = 3;
                            break;
                        case 5:
                            shapeType = EffectiveShapeView.Shape.POLYGON;
                            side = 5;
                            break;
                        case 6:
                            shapeType = EffectiveShapeView.Shape.POLYGON;
                            side = 6;

                            break;
                        case 7:
                            shapeType = EffectiveShapeView.Shape.POLYGON;
                            side = 7;
                            break;
                    }
                });
                switch (i) {
                    case 0:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.CIRCLE);
                        break;

                    case 1:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.RECTANGLE);
                        break;
                    case 2:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.ROUND_RECTANGLE);
                        imageHolder.shapeView.setDegreeForRoundRectangle(32, 32);
                        imageHolder.shapeView.setBorderColor(-16777216);
                        imageHolder.shapeView.setBorderWidth(18);
                        break;
                    case 3:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.SQUARE);
                        break;
                    case 4:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 3);
                        break;
                   /* case 5:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 4);
                        break;*/
                    case 5:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 5);
                        break;
                    case 6:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 6);
                        break;
                    case 7:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 7);
                        break;
                }



               /* switch (i % 9) {
                    case 0:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.RECTANGLE);
                        break;
                    case 1:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.ROUND_RECTANGLE);
                        imageHolder.shapeView.setDegreeForRoundRectangle(32, 32);
                        break;
                    case 2:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.CIRCLE);
                        break;
                    case 3:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.SQUARE);
                        break;
                    case 4:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 3);
                        break;
                    case 5:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 4);
                        break;
                    case 6:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 5);
                        break;
                    case 7:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 6);
                        break;
                    case 8:
                        imageHolder.shapeView.changeShapeType(EffectiveShapeView.Shape.POLYGON, 7);
                        break;
                }

                if (i < 10) {
                    imageHolder.shapeView.setBorderWidth(0);
                } else if (i < 20) {
                    imageHolder.shapeView.setBorderWidth(2);
                } else if (i < 30) {
                    imageHolder.shapeView.setBorderWidth(4);
                } else if (i < 40) {
                    imageHolder.shapeView.setBorderWidth(6);
                } else if (i < 50) {
                    imageHolder. shapeView.setBorderWidth(8);
                } else if (i < 60) {
                    imageHolder.shapeView.setBorderWidth(10);
                } else if (i < 70) {
                    imageHolder. shapeView.setBorderWidth(12);
                } else if (i < 80) {
                    imageHolder.  shapeView.setBorderWidth(14);
                } else if (i < 70) {
                    imageHolder.shapeView.setBorderWidth(16);
                } else if (i < 100) {
                    imageHolder.shapeView.setBorderWidth(18);
                }*/
            } catch (Exception e) {
            }

        }

        @Override
        public int getItemCount() {
            return 8;
        }

        class ImageHolder extends RecyclerView.ViewHolder {
            EffectiveShapeView shapeView;

            public ImageHolder(@NonNull View itemView) {
                super(itemView);
                shapeView = itemView.findViewById(R.id.iv);
            }
        }

    }
}
