package com.android.yapp.scenetalker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {

    private int mPageNumber;
    DramaInfo item;

    public static PageFragment create(int pageNumber) {
        PageFragment fragment = new PageFragment();
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

    public void setItem(DramaInfo item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_page, container, false);
        if(item.getPoster_url() != null&& !item.getPoster_url().equals("")){
            //image.setVisibility(View.VISIBLE);
            //image.setImageURI(Uri.parse("file://"+picturepath));
            Glide.with(getContext()).load(item.getPoster_url()).into((ImageView)rootView.findViewById(R.id.poster));
        }
        ((TextView)rootView.findViewById(R.id.episodeIndex)).setText(item.getEpisode()+" 방영중");
        ((ImageView)rootView.findViewById(R.id.poster)).setClipToOutline(true);
        Button btn_to_feed=rootView.findViewById(R.id.feed_button);
        btn_to_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FeedPage.class);
                startActivity(intent);
            }
        });

        return rootView;
    }


}