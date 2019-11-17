package com.android.yapp.scenetalker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class FeedPage extends AppCompatActivity {

    ProgressBar progressBar;
    private RecyclerView recyclerView=null;
    private FeedAdapter feedAdapter=null;
    private List<FeedInfo> dataList=null;
    ImageButton close,search;
    FloatingActionButton write_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        Intent intent=new Intent(this.getIntent());

        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        close=(ImageButton)findViewById(R.id.feed_close_btn);
        search=(ImageButton)findViewById(R.id.feed_search_btn);
        write_btn=(FloatingActionButton)findViewById(R.id.write_btn);

        init();
        add();
        setRecyclerView();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),WritePage.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerview3);
        dataList = new ArrayList<FeedInfo>();
    }
    private void add(){
        //    public FeedInfo(String name, String comment, String comment_time,int comment_num, int heart_num){
        dataList.add(new FeedInfo("배가수지","수지가 쓴 선글라스 어디꺼죠? 너무 예뻐요! 당장살래..","3분전",1,2));
        dataList.add(new FeedInfo("만두 먹고 싶다","수지 천재 얼굴 천재 연기 천재 너무 좋아 수지 최고","1분전",5,8));
        dataList.add(new FeedInfo("달건아 달려","고해리 정체가 무예요?? 왜 비행기 추락시킨 회사 회장한테 보고하는 거에요? 이승기랑 ..","방금전",3,5));
        dataList.add(new FeedInfo("달건아 달려","고해리 정체가 무예요?? 왜 비행기 추락시킨 회사 회장한테 보고하는 거에요? 이승기랑 ..","방금전",3,5));
        dataList.add(new FeedInfo("달건아 달려","고해리 정체가 무예요?? 왜 비행기 추락시킨 회사 회장한테 보고하는 거에요? 이승기랑 ..","방금전",3,5));



    }
    private void setRecyclerView(){
        feedAdapter = new FeedAdapter(getApplicationContext(),R.layout.item_feed,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(feedAdapter);
    }
}
