package com.example.mca.androidinstagramfilters.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mca.androidinstagramfilters.R;

import java.util.ArrayList;
import java.util.List;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.FontViewAdapter> {

    Context context;
    FontAdapterClickListener listener;
    List<String> fontList;


    int row_selected = -1;

    public FontAdapter(Context context, FontAdapterClickListener listener) {
        this.context = context;
        this.listener = listener;
        fontList = loadFontList();
    }

    private List<String> loadFontList() {
        List<String> result = new ArrayList<>();
        result.add("Cheque-Black.otf");
        result.add("Cheque-Regular.otf");
        //cn add more fonts here
        return result;
    }

    @NonNull
    @Override
    public FontViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.font_item,parent,false);
        return new FontViewAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FontViewAdapter holder, int position) {
             if(row_selected == position)
                 holder.img_check.setVisibility(View.VISIBLE);
             else
                 holder.img_check.setVisibility(View.INVISIBLE);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),new StringBuilder("fonts/")
                .append(fontList.get(position)).toString());

        holder.txt_font_name.setText(fontList.get(position));
        holder.txt_font_demo.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return fontList.size();
    }

    public class FontViewAdapter extends RecyclerView.ViewHolder {
        TextView txt_font_name,txt_font_demo;
        ImageView img_check;
        public FontViewAdapter(View itemView) {
            super(itemView);

            txt_font_name = (TextView)itemView.findViewById(R.id.txt_font_name);
            txt_font_demo = (TextView)itemView.findViewById(R.id.txt_font_demo);

            img_check = (ImageView) itemView.findViewById(R.id.img_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFontSelected(fontList.get(getAdapterPosition()));
                    row_selected = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface FontAdapterClickListener{
        public void onFontSelected(String fontName);
    }
}
