package com.android.yapp.scenetalker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.yapp.scenetalker.FeedPage.drama_id;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int resourceId;
    private FragmentManager fm;
    private List<FeedInfo> dataList;
    private final int TYPE_HEADER =0;
    private final int TYPE_ITEM=1;
    ViewPager fViewPager;
    private FeedPage currentView;
    FeedPage fp=new FeedPage();
    int page;
    String nextPage;
    TextView episode_result,potato_percent,cider_percent;
    int potato_count,cider_count;
    String episode_num;
    PagerAdapter2 pagerAdapter2;
    public FeedAdapter(Context context,int resourceId,List<FeedInfo>dataList,FragmentManager fm){
        this.context=context;
        this.resourceId=resourceId;
        this.dataList=dataList;
        this.fm=fm;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_header, parent, false);
            holder = new HeaderViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
            holder = new ItemViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //fPagerAdapter pagerAdapter=new fPagerAdapter(context);

        if (holder instanceof HeaderViewHolder) {

        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.onBind(dataList.get(position - 1));
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView feed_post;
        TextView feed_time;
        TextView comment_num;
        TextView heart_num;

        public ItemViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.username);
            feed_post=itemView.findViewById(R.id.feed_post);
            feed_time=itemView.findViewById(R.id.feed_time);
            comment_num=itemView.findViewById(R.id.comment_num);
            heart_num=itemView.findViewById(R.id.heart_num);

        }

        void onBind(FeedInfo dataList){
            name.setText(dataList.getUsername());
            feed_post.setText(dataList.getComment());
            feed_time.setText(dataList.getComment_time());
            comment_num.setText(Integer.toString(dataList.getComment_num()));
            heart_num.setText(Integer.toString(dataList.getHeart_num()));
        }

    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderViewHolder(View headerView) {
            super(headerView);
            page = 1;
            getItems(page);
            fViewPager=headerView.findViewById(R.id.fviewPager);
            pagerAdapter2=new PagerAdapter2(fm);
            fViewPager.setAdapter(pagerAdapter2);
        }
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

                    System.out.println(episode_num+potato_count+"/"+cider_count);

                    if(info != null){
                        counts.add(info);
                    }

                }
                page++;
                pagerAdapter2.setItems(counts);

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("err",t.getMessage());
                call.cancel();
            }
        });

    }

}
