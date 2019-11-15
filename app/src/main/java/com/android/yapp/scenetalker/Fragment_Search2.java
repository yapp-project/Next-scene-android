package com.android.yapp.scenetalker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Search2 extends Fragment {
    private RecyclerView recyclerView=null;
    private Fragment_Search2_Adapter feedAdapter2=null;
    private  List<FeedInfo> dataList=null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_search2,container,false);
        init(rootView);
//        add();
        setRecyclerView();
        return rootView;
    }

    private void init(View rootView){
        recyclerView = rootView.findViewById(R.id.match_recyclerview);
        dataList = new ArrayList<FeedInfo>();
    }
//    private void add(){
//        dataList.add(new FeedInfo("배가수지","수지가 쓴 선글라스 어디꺼죠? 너무 예뻐요! 당장살래..","3분전",1,2));
//        dataList.add(new FeedInfo("만두 먹고 싶다","수지 천재 얼굴 천재 연기 천재 너무 좋아 수지 최고","1분전",5,8));
//        dataList.add(new FeedInfo("달건아 달려","고해리 정체가 무예요?? 왜 비행기 추락시킨 회사 회장한테 보고하는 거에요? 이승기랑 ..","방금전",3,5));
//        dataList.add(new FeedInfo("달건아 달려","고해리 정체가 무예요?? 왜 비행기 추락시킨 회사 회장한테 보고하는 거에요? 이승기랑 ..","방금전",3,5));
//        dataList.add(new FeedInfo("달건아 달려","고해리 정체가 무예요?? 왜 비행기 추락시킨 회사 회장한테 보고하는 거에요? 이승기랑 ..","방금전",3,5));
//
//
//    }
    private void setRecyclerView(){
        feedAdapter2 = new Fragment_Search2_Adapter(getActivity().getApplicationContext(), R.layout.item_feed,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(feedAdapter2);
    }

}
