package com.android.yapp.scenetalker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OnAirFragment extends Fragment {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.mPagerAdapter = new PagerAdapter(getChildFragmentManager());

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_onair, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        this.mViewPager = (ViewPager) view.findViewById(R.id.pager);
        this.mViewPager.setAdapter(mPagerAdapter);
        this.mViewPager.setClipToPadding(false);
        this.mViewPager.setPageMargin(60);

    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            // 해당하는 page의 Fragment 생성
            return PageFragment.create(position);
        }


        @Override
        public int getCount() {
            return 5;  // 보여지는 페이지 개수
        }

        @Override
        public float getPageWidth (int position) {
            return 0.85f;
        }
    }

}
