package com.android.yapp.scenetalker;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OffAirFragment extends Fragment {

    RecyclerView recyclerView;
    OffAirAdapter adapter;
    Context context;
    OnTabItemSelectedListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
        if(context instanceof OnTabItemSelectedListener){
            listener = (OnTabItemSelectedListener)context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();

        if(context != null) {
            context = null;
            listener = null;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_offair, container, false);
        initUi(rootView);
        return rootView;
    }
    private void initUi(ViewGroup rootView) {
//        Button gotofeed = rootView.findViewById(R.id.gotofeed);
//        gotofeed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        recyclerView = rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new OffAirAdapter();

        adapter.addItem(new DramaInfo("iu.jpg","tvN","호텔델루나",
                "평균시청률","10.0%","|","토일 오후 9시","피드 보러가기>","1"));
        adapter.addItem(new DramaInfo("iu.jpg","tvN","호텔델루나",
                "평균시청률","10.0%","|","토일 오후 9시","피드 보러가기>","2"));
        adapter.addItem(new DramaInfo("iu.jpg","tvN","호텔델루나",
                "평균시청률","10.0%","|","토일 오후 9시","피드 보러가기>","3"));
        adapter.addItem(new DramaInfo("iu.jpg","tvN","호텔델루나",
                "평균시청률","10.0%","|","토일 오후 9시","피드 보러가기>","4"));
        recyclerView.setAdapter(adapter);

    }

}
