package com.android.yapp.scenetalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {

    private int mPageNumber;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_page, container, false);
        ((ImageView)rootView.findViewById(R.id.poster)).setImageResource(R.drawable.img_1);
        ((ImageView)rootView.findViewById(R.id.poster)).setClipToOutline(true);
        Button btn_to_feed=((Button)rootView.findViewById(R.id.feed_button));
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