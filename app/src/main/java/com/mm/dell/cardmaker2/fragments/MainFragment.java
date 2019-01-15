package com.mm.dell.cardmaker2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mm.dell.cardmaker2.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class MainFragment extends Fragment {
    TabLayout indicator;
    ViewPager pager;
    MyPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        adapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
        indicator.setupWithViewPager(pager);
//        indicator.setViewPager(pager);
        return view;
    }

    private void initView(View view) {
        pager = view.findViewById(R.id.pager);
        indicator = view.findViewById(R.id.dots_indicator);

    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:

                    Fragment fragment = new ResizeRotateOpacityFragment();
                    ((ResizeRotateOpacityFragment) fragment).setListner((SeekBarChange) getActivity());

                    return fragment;
                case 1:
                    Fragment fragment1 = new ColorPickerFragment();
                    ((ColorPickerFragment) fragment1).setListner((SeekBarChange) getActivity());
                    return fragment1;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "as";
            } else if (position == 1) {
                return "DD";
            }
            return null;
        }
    }
}
