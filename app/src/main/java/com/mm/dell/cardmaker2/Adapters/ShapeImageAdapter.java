package com.mm.dell.cardmaker2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;
import com.mm.dell.cardmaker2.layout.EffectiveShapeView;

import static com.mm.dell.cardmaker2.Constants.NOTDEFINE_SIDE;

public class ShapeImageAdapter extends RecyclerView.Adapter<ShapeImageAdapter.ImageHolder> {
    Context context;
    private int shapeType = NOTDEFINE_SIDE;
    private int side = 0;
    SeekBarChange seekBarChange;

    public ShapeImageAdapter(Context context, SeekBarChange seekBarChange) {
        this.context = context;
        this.seekBarChange = seekBarChange;
    }


    @NonNull
    @Override
    public ShapeImageAdapter.ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.imagelayout1, viewGroup, false);
        return new ShapeImageAdapter.ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShapeImageAdapter.ImageHolder imageHolder, int i) {
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
                seekBarChange.setShapeImageView(side, shapeType);

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
                    imageHolder.shapeView.setDegreeForRoundRectangle(10, 10);

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