package com.example.mypc.androidinstagramfilter.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.androidinstagramfilter.Interface.FiltersListFragmentListener;
import com.example.mypc.androidinstagramfilter.R;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.MyViewHolder> {
private List<ThumbnailItem> thumbnailItemList;
private FiltersListFragmentListener listener;
private Context context;
private int selectedIndex = 0;

    public ThumbnailAdapter(List<ThumbnailItem> thumbnailItemList, FiltersListFragmentListener listener, Context context) {
        this.thumbnailItemList = thumbnailItemList;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.thumbnail_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
      final ThumbnailsItem thumbnailsItem = thumbnailItemList.get(position);

      holder.thumbnail.setImageBitmap(thumbnailsItem.image);
      holder.thumbnail.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              listener.onFilterSelected(thumbnailsItem.filter);
              selectedIndex = position;
              notifyDataSetChanged();

          }
      });

      holder.filter_name.setText(thumbnailsItem.filterName);

      if(selectedIndex == position)
      {
          holder.filter_name.setTextColor(ContextCompat.getColor(context,R.color.selected_filter));
      }
      else
          holder.filter_name.setTextColor(ContextCompat.getColor(context,R.color.normal_filter));

    }

    @Override
    public int getItemCount() {
        return thumbnailItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView filter_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
            filter_name = (TextView)itemView.findViewById(R.id.filter_name);
        }
    }


}
