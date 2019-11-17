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

    private ViewPager fViewPager;
    private PagerAdapter2 fPagerAdapter;

    int page;
    String nextPage;
    static int p;

    TextView episode_result,potato_percent,cider_percent;
    int potato_count,cider_count;
    String episode_num;
    int potato_pc,cider_pc;
    FeedPage fp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        Intent intent=getIntent();
        drama_title = intent.getExtras().getString("name");
        episode=intent.getExtras().getString("episode");
        drama_id=intent.getExtras().getInt("id");

        this.fPagerAdapter = new PagerAdapter2(getSupportFragmentManager());

        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        close=(ImageButton)findViewById(R.id.feed_close_btn);
        search=(ImageButton)findViewById(R.id.feed_search_btn);
        write_btn=(FloatingActionButton)findViewById(R.id.write_btn);

        drama_name_title=(TextView)findViewById(R.id.drama_title);
        drama_name_title.setText(drama_title+" 게시판");
        page = 1;
        getItems(page);

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
                intent.putExtra("name",drama_title);
                startActivity(intent);

            }
        });


        //fViewPager = (ViewPager) findViewById(R.id.fviewPager);
        //fViewPager.setAdapter(fPagerAdapter);
        //fViewPager.setClipToPadding(false);
        //fViewPager.setPageMargin(60);

    }
    public class PagerAdapter2 extends FragmentPagerAdapter {


        FeedFragment adapter;
        ArrayList<CountInfo> countitems;

        public PagerAdapter2(FragmentManager fm) {
            super(fm);
            countitems = new ArrayList<>();
        }

        public void setItems(ArrayList<CountInfo> countitems) {
            for(int i=0;i<countitems.size();i++){
                this.countitems.add(countitems.get(i));
            }
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            adapter = FeedFragment.create(position);
            adapter.setItem(countitems.get(position));
            // 해당하는 page의 Fragment 생성
            if(position == countitems.size()-1 && nextPage != null){
                getItems(page);
            }
            return adapter;
        }

        @Override
        public int getCount() {
            return countitems.size();  // 보여지는 페이지 개수

        }

        @Override
        public float getPageWidth (int position) {
            return 0.85f;
        }
    }

    public void getItems(int itempage){
        Call<JsonArray> call2 = NetRetrofit.getInstance().getDramaCount(drama_id);
        call2.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Gson gson = new Gson();
                if(response.body()==null)
                    return;
                JsonArray array = response.body().getAsJsonArray();

                ArrayList<CountInfo> counts=new ArrayList<>();

                for(int i=0;i<array.size();i++){
                    CountInfo info = gson.fromJson(array.get(i),CountInfo.class);
                    episode_num=info.getEpisode();
                    potato_count=info.getSweet_potato_count();
                    cider_count=info.getSoda_count();
                    //potato_pc=(int)((double)potato_count/(double)(potato_count+cider_count)*100);
                   // cider_pc=100-potato_pc;
                    System.out.println(episode_num+potato_count+"/"+cider_count);

                    if(info != null){
                        counts.add(info);
                    }

                }

                page++;
                fPagerAdapter.setItems(counts);

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("err",t.getMessage());
                call.cancel();
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
