package com.android.yapp.scenetalker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int resourceId;
    private List<FeedInfo> dataList;
    private final int TYPE_HEADER =0;
    private final int TYPE_ITEM=1;
    ViewPager fViewPager;

    fPagerAdapter fPagerAdapter;

    public FeedAdapter(Context context,int resourceId,List<FeedInfo>dataList){
        this.context=context;
        this.resourceId=resourceId;
        this.dataList=dataList;

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

        if (holder instanceof HeaderViewHolder) {

        } else {
            fPagerAdapter pagerAdapter=new fPagerAdapter(context);
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
            fViewPager=headerView.findViewById(R.id.fviewPager);
            fPagerAdapter=new fPagerAdapter(context);
            fViewPager.setAdapter(fPagerAdapter);
        }
    }


}
