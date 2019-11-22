package com.android.yapp.scenetalker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedPage extends AppCompatActivity {

    ProgressBar progressBar;
    private RecyclerView recyclerView=null;
    private FeedAdapter feedAdapter=null;
    private List<FeedInfo> dataList=null;
    ImageButton close,search;
    FloatingActionButton write_btn;
    TextView drama_name_title;
    public static String drama_title,episode;
    public static int drama_id;
    int dramas;
    String contents;

    int page;
    String nextPage;
    static int p;

    TextView episode_result,potato_percent,cider_percent;
    int potato_count,cider_count;
    String episode_num;
    int potato_pc,cider_pc;
    FeedPage fp;

    @Override
    protected void onResume() {
        super.onResume();
//        Call<JsonArray> call2 = NetRetrofit.getInstance().getFeed("44");
//        System.out.println("어이1");
//        call2.enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                Gson gson = new Gson();
//                if(response.body()==null)
//                    return;
//                JsonArray array = response.body().getAsJsonArray();
//                System.out.println("어이2");
//
//                ArrayList<GetPostInfo> posts=new ArrayList<>();
//                for(int i=0;i<array.size();i++){
//                    GetPostInfo info = gson.fromJson(array.get(i),GetPostInfo.class);
//                    contents=info.getContent();
//                    dataList.add(new FeedInfo("hsg",contents,"방금 전",1,1));
//
//
//                    System.out.println("받아와"+contents);
//
//                    if(info != null){
//                        posts.add(info);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//                Log.e("err",t.getMessage());
//                call.cancel();
//            }
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        Intent intent=getIntent();
        drama_title = intent.getExtras().getString("name");
        episode=intent.getExtras().getString("episode");
        drama_id=intent.getExtras().getInt("id");

        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        close=(ImageButton)findViewById(R.id.feed_close_btn);
        search=(ImageButton)findViewById(R.id.feed_search_btn);
        write_btn=(FloatingActionButton)findViewById(R.id.write_btn);

        drama_name_title=(TextView)findViewById(R.id.drama_title);
        drama_name_title.setText(drama_title+" 게시판");

        init();
        add();
        getfeed();



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
                intent.putExtra("name",drama_title);
                // startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                Log.i("result", result);
                finish();
                startActivity(getIntent());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //만약 반환값이 없을 경우의 코드를 여기에 작성하세요.
            }
        }
    }//onActivityResult

    private void init(){
        recyclerView = findViewById(R.id.recyclerview3);
        dataList = new ArrayList<FeedInfo>();
    }
    private void add(){
        //dataList.add(new FeedInfo("hsg",contents,"방금 전",1,1));



    }

    private void setRecyclerView(){

        feedAdapter = new FeedAdapter(getApplicationContext(),R.layout.item_feed,dataList,getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(feedAdapter);
    }
    private void getfeed(){
        Call<JsonArray> call2 = NetRetrofit.getInstance().getFeed("44");
        System.out.println("어이1");
        call2.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Gson gson = new Gson();
                if(response.body()==null)
                    return;
                JsonArray array = response.body().getAsJsonArray();
                System.out.println("어이2");

                ArrayList<GetPostInfo> posts=new ArrayList<>();
                for(int i=0;i<array.size();i++){
                    GetPostInfo info = gson.fromJson(array.get(i),GetPostInfo.class);
                    contents=info.getContent();


                    System.out.println("받아와"+contents);

                    if(info != null){
                        posts.add(info);
                        dataList.add(new FeedInfo("hsg",contents,"방금 전",1,1));
                    }

                }
                setRecyclerView();

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("err",t.getMessage());
                call.cancel();
            }
        });
    }

}
