package com.android.yapp.scenetalker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OffAirAdapter extends RecyclerView.Adapter<OffAirAdapter.ViewHolder> implements OnInfoItemClickListener  {
    ArrayList<DramaInfo> items = new ArrayList<DramaInfo>();
    OnInfoItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_offair,parent,false);
        return new ViewHolder (itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        DramaInfo item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(DramaInfo item){
        items.add(item);
    }

    public void setItems(ArrayList<DramaInfo> items){
        this.items = items;
    }

    public DramaInfo getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnInfoItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder,View view,int position){
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView production;
        TextView dname;
        TextView ratingtext;
        TextView rating;
        TextView bar;
        TextView time;
        Button count;
        Button gotofeed;

        public ViewHolder(View itemView,final OnInfoItemClickListener listener){
            super(itemView);
            image = itemView.findViewById(R.id.image);
            production = itemView.findViewById(R.id.production);
            dname = itemView.findViewById(R.id.dramaname);
            ratingtext = itemView.findViewById(R.id.ratingtext);
            rating = itemView.findViewById(R.id.rating);
            bar = itemView.findViewById(R.id.bar);
            time = itemView.findViewById(R.id.time);
            count = itemView.findViewById(R.id.count);
            gotofeed = itemView.findViewById(R.id.gotofeed);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });
        }
        public void setItem(DramaInfo item){
            String picturepath = item.getPicture();
            if(picturepath != null&& !picturepath.equals("")){
                //image.setVisibility(View.VISIBLE);
                //image.setImageURI(Uri.parse("file://"+picturepath));
                image.setImageResource(R.drawable.iu);
            }

            production.setText(item.getProduction());
            dname.setText(item.getDname());
            ratingtext.setText(item.ratingtext);
            rating.setText(item.rating);
            bar.setText(item.bar);
            time.setText(item.time);
            count.setText(item.count);
            gotofeed.setText(item.gotofeed);
        }
    }
}
