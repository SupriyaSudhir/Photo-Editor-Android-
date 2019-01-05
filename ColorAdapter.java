package com.example.mca.androidinstagramfilters.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mca.androidinstagramfilters.R;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewholder> {
    Context context;
    List<Integer> colorList;
    ColorAdapterListener listener;


    public ColorAdapter(Context context,  ColorAdapterListener listener)
    {
        this.context = context;
        this.colorList = genColorList();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ColorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.color_item,parent,false);
        return new ColorViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewholder holder, int position) {
     holder.color_section.setCardBackgroundColor(colorList.get(position));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ColorViewholder extends  RecyclerView.ViewHolder  {

        public CardView color_section;

        public ColorViewholder(View itemView) {
            super(itemView);
            color_section = (CardView)itemView.findViewById(R.id.color_section);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onColorSelected(colorList.get(getAdapterPosition()));
                }
            });
        }
    }


    private List<Integer> genColorList() {
        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#131722"));
        colorList.add(Color.parseColor("#ff545e"));
        colorList.add(Color.parseColor("#57bb82"));
        colorList.add(Color.parseColor("#dbeeff"));
        colorList.add(Color.parseColor("#ba5796"));
        colorList.add(Color.parseColor("#bb349b"));
        colorList.add(Color.parseColor("#6e557c"));
        colorList.add(Color.parseColor("#5e40b2"));


        colorList.add(Color.parseColor("#8051cf"));
        colorList.add(Color.parseColor("#895adc"));
        colorList.add(Color.parseColor("#935da0"));
        colorList.add(Color.parseColor("#7a5e93"));
        colorList.add(Color.parseColor("#6c4475"));
        colorList.add(Color.parseColor("#403890"));
        colorList.add(Color.parseColor("#1b36eb"));
        colorList.add(Color.parseColor("#10d6a2"));

        return  colorList;
    }

    public interface ColorAdapterListener{
        void onColorSelected(int color);
    }
}
