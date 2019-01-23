package com.mm.dell.cardmaker2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mm.dell.cardmaker2.Dialogs.ColorPicker2dialog;
import com.mm.dell.cardmaker2.Dialogs.EditTextViewDialog;
import com.mm.dell.cardmaker2.FontModel;
import com.mm.dell.cardmaker2.Main2Activity;
import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.RecyclerOnItemClickListner;
import com.mm.dell.cardmaker2.RecylerBotttomAdapter;
import com.mm.dell.cardmaker2.Root.Root;
import com.mm.dell.cardmaker2.Utils.OntextChange;
import com.mm.dell.cardmaker2.activities.CardMainActivity;

import java.util.ArrayList;

public class TextFragmentFragment extends Fragment implements View.OnClickListener, ColorPicker2dialog.colorpickercallback {

    ImageView iv_opacity;
    ImageView iv_size;
    ImageView iv_edittext;
    ImageView iv_deleteview;
    ImageView iv_colorpicker;
    ImageView iv_rotate;
    ImageView iv_addtext;

    SeekBarChange seekBarChange;
    RecyclerView recyclerview;
    SeekBar seek;
    int handle_seekbar = 7899;
    private String TAG = TextFragmentFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        initView(view);
        LoadFontList();
        seekBarChange = (SeekBarChange) getActivity();
        return view;

    }

    private void initView(View view) {
        seek = view.findViewById(R.id.seek_text);
        recyclerview = view.findViewById(R.id.recyclerview);
        iv_opacity = view.findViewById(R.id.iv_opacity);
        iv_opacity.setOnClickListener(this);
        iv_size = view.findViewById(R.id.iv_textsized);
        iv_size.setOnClickListener(this);

        iv_edittext = view.findViewById(R.id.iv_edittext);
        iv_edittext.setOnClickListener(this);

        iv_addtext = view.findViewById(R.id.iv_addtext);
        iv_addtext.setOnClickListener(this);

        iv_deleteview = view.findViewById(R.id.iv_deleteview);
        iv_deleteview.setOnClickListener(this);

        iv_colorpicker = view.findViewById(R.id.iv_colorpicker);
        iv_colorpicker.setOnClickListener(this);

        iv_rotate = view.findViewById(R.id.iv_rotate);
        iv_rotate.setOnClickListener(this);

    }


    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.e(TAG, "  seek progress change is being called");
            switch (handle_seekbar) {
                //rotate
                case 11:
                    seekBarChange.OnRotate(progress);
                    break;
                // size
                case 12:
                    seekBarChange.OnTextSizeChange(progress);
                    break;
                //opacity
                case 13:
                    float b = progress / 100f;
                    seekBarChange.OnBrigthness(b);

                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_rotate:
                try {
                    seek.setOnSeekBarChangeListener(null);
                    Log.e(TAG, "iv_rotate is being called");
                    seek.setMax(360);
                    handle_seekbar = 11;
                    seek.setOnSeekBarChangeListener(onSeekBarChangeListener);
                    if (seek.getVisibility() == View.GONE) {
                        seek.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                }

                break;
            case R.id.iv_textsized:
                try {
                    seek.setOnSeekBarChangeListener(null);
                    Log.e(TAG, "iv_textsized is being called");
                    CardMainActivity activity1 = (CardMainActivity) getActivity();
                    seek.setMax(activity1.width);
                    seek.setProgress(14);
                    handle_seekbar = 12;
                    seek.setOnSeekBarChangeListener(onSeekBarChangeListener);
                    if (seek.getVisibility() == View.GONE) {
                        seek.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                }
                break;

            case R.id.iv_opacity:
                try {
                    seek.setOnSeekBarChangeListener(null);
                    Log.e(TAG, "iv_opacity is being called");
                    seek.setMax(100);
                    seek.setProgress(99);
                    handle_seekbar = 13;
                    seek.setOnSeekBarChangeListener(onSeekBarChangeListener);
                    if (seek.getVisibility() == View.GONE) {
                        seek.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                }

                break;

            case R.id.iv_colorpicker:
                try {
                    ColorPicker2dialog dialog = new ColorPicker2dialog(getActivity(), this);
                    dialog.show();
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }

                break;
            case R.id.iv_addtext:
                try {
                    Log.e(TAG, "add text is being called");
                    CardMainActivity activity2 = (CardMainActivity) getActivity();
                    assert activity2 != null;
                    activity2.AddTextViewsText();
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }

                break;

            case R.id.iv_edittext:
                try {
                    CardMainActivity activity = (CardMainActivity) getActivity();
                    View view = activity.selecteview;
                    OntextChange ontextChange = (OntextChange) getActivity();
                    if (view instanceof TextView) {
                        TextView view1 = (TextView) view;
                        new EditTextViewDialog(getActivity(), view1.getText().toString(), ontextChange).show();
                    } else {
                        new EditTextViewDialog(getActivity(), null, ontextChange).show();
                    }
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }
                break;

            case R.id.iv_deleteview:
                try {
                    seekBarChange.DeleteView();
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }

                break;
        }

    }

    private void LoadFontList() {
        ArrayList<FontModel> fontList = new ArrayList<>();
        fontList.add(new FontModel("abeezee", ResourcesCompat.getFont(Root.getAppContext(), R.font.abeezee)));
        fontList.add(new FontModel("abhaya_libre", ResourcesCompat.getFont(Root.getAppContext(), R.font.abhaya_libre)));
        fontList.add(new FontModel("abril_fatface", ResourcesCompat.getFont(Root.getAppContext(), R.font.abril_fatface)));
        fontList.add(new FontModel("aclonica", ResourcesCompat.getFont(Root.getAppContext(), R.font.aclonica)));
        fontList.add(new FontModel("acme", ResourcesCompat.getFont(Root.getAppContext(), R.font.acme)));
        fontList.add(new FontModel("advent_pro_thin", ResourcesCompat.getFont(Root.getAppContext(), R.font.advent_pro_thin)));
        fontList.add(new FontModel("aguafina_script", ResourcesCompat.getFont(Root.getAppContext(), R.font.aguafina_script)));
        fontList.add(new FontModel("akronim", ResourcesCompat.getFont(Root.getAppContext(), R.font.akronim)));
        fontList.add(new FontModel("aladin", ResourcesCompat.getFont(Root.getAppContext(), R.font.aladin)));
        fontList.add(new FontModel("aldrich", ResourcesCompat.getFont(Root.getAppContext(), R.font.aldrich)));
        fontList.add(new FontModel("alfa_slab_one", ResourcesCompat.getFont(Root.getAppContext(), R.font.alfa_slab_one)));
        fontList.add(new FontModel("allan", ResourcesCompat.getFont(Root.getAppContext(), R.font.allan)));
        fontList.add(new FontModel("allura", ResourcesCompat.getFont(Root.getAppContext(), R.font.allura)));
        fontList.add(new FontModel("almendra_display", ResourcesCompat.getFont(Root.getAppContext(), R.font.almendra_display)));
        fontList.add(new FontModel("architects_daughter", ResourcesCompat.getFont(Root.getAppContext(), R.font.architects_daughter)));
        fontList.add(new FontModel("arizonia", ResourcesCompat.getFont(Root.getAppContext(), R.font.arizonia)));
        fontList.add(new FontModel("astloch", ResourcesCompat.getFont(Root.getAppContext(), R.font.astloch)));
        fontList.add(new FontModel("bangers", ResourcesCompat.getFont(Root.getAppContext(), R.font.bangers)));
        fontList.add(new FontModel("bonbon", ResourcesCompat.getFont(Root.getAppContext(), R.font.bonbon)));
        fontList.add(new FontModel("bungee_hairline", ResourcesCompat.getFont(Root.getAppContext(), R.font.bungee_hairline)));
        fontList.add(new FontModel("bungee_inline", ResourcesCompat.getFont(Root.getAppContext(), R.font.bungee_inline)));
        fontList.add(new FontModel("bungee_shade", ResourcesCompat.getFont(Root.getAppContext(), R.font.bungee_shade)));
        fontList.add(new FontModel("butcherman", ResourcesCompat.getFont(Root.getAppContext(), R.font.butcherman)));
        fontList.add(new FontModel("butterfly_kids", ResourcesCompat.getFont(Root.getAppContext(), R.font.butterfly_kids)));
        fontList.add(new FontModel("codystar_light", ResourcesCompat.getFont(Root.getAppContext(), R.font.codystar_light)));
        fontList.add(new FontModel("diplomata_sc", ResourcesCompat.getFont(Root.getAppContext(), R.font.diplomata_sc)));
        fontList.add(new FontModel("ewert", ResourcesCompat.getFont(Root.getAppContext(), R.font.ewert)));
        fontList.add(new FontModel("faster_one", ResourcesCompat.getFont(Root.getAppContext(), R.font.faster_one)));
        fontList.add(new FontModel("fontdiner_swanky", ResourcesCompat.getFont(Root.getAppContext(), R.font.fontdiner_swanky)));
        fontList.add(new FontModel("monoton", ResourcesCompat.getFont(Root.getAppContext(), R.font.monoton)));
        fontList.add(new FontModel("waiting_for_the_sunrise", ResourcesCompat.getFont(Root.getAppContext(), R.font.waiting_for_the_sunrise)));
        fontList.add(new FontModel("warnes", ResourcesCompat.getFont(Root.getAppContext(), R.font.warnes)));
        RecylerBotttomAdapter adapter = new RecylerBotttomAdapter(getActivity(), fontList, (RecyclerOnItemClickListner) getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void getColor(int i, int i1, int i2) {
        seekBarChange.OnColorChanges(i, i1, i2);
    }
}
