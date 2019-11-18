package com.android.yapp.scenetalker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.yapp.scenetalker.FeedPage.drama_id;

public class FeedFragment extends Fragment {
    private int mPageNumber;
    CountInfo item;


    public static FeedFragment create(int pageNumber) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("page");
    }

    public void setItem(CountInfo item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        int potato_pc,cider_pc;
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_feed, container, false);
        potato_pc=(int)((double)item.getSweet_potato_count()/(double)(item.getSoda_count()+item.getSweet_potato_count())*100);
        cider_pc=100-potato_pc;
        ((TextView) rootView.findViewById(R.id.episode_result)).setText(item.getEpisode() + "차 결과");
        ((TextView) rootView.findViewById(R.id.cider_percent)).setText(cider_pc + "%");
        ((TextView) rootView.findViewById(R.id.potato_percent)).setText(potato_pc + "%");

        return rootView;
    }
}


