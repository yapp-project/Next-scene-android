package com.android.yapp.scenetalker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChattingActivity extends AppCompatActivity {
   private RecyclerView recyclerView=null;
   private ChattingAdapter chattingAdapter=null;
   private List<ChattingInfo> dataList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chattingroom);
        init();
        add();
        setRecyclerView();
        FloatingActionButton sweetpotato = findViewById(R.id.sweetpotato);
        sweetpotato.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {

            }
        });
        FloatingActionButton cider = findViewById(R.id.cider);
        cider.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {

            }
        });


    }
    private void init(){
        recyclerView = findViewById(R.id.recyclerview2);
        dataList = new ArrayList<ChattingInfo>();
    }
    private void add(){
        dataList.add(new ChattingInfo("만두먹고싶다","자꾸 사람이어주길래 전생이 반딧불인줄ㅋㅋㅋ"));
        dataList.add(new ChattingInfo("빵먹자","수지 너모 예뻐..ㅜ"));
        dataList.add(new ChattingInfo("짱절미","구찬성이 이렇게 엮이냐"));
        dataList.add(new ChattingInfo("빵먹자","수지 너모 예뻐..ㅜ"));
        dataList.add(new ChattingInfo("빵먹자","수지 너모 예뻐..ㅜ"));


    }
    private void setRecyclerView(){
        chattingAdapter = new ChattingAdapter(getApplicationContext(),R.layout.chattingitem,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chattingAdapter);
    }

    class FABClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

        }
    }
}
