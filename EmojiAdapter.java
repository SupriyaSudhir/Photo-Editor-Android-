package com.example.mca.androidinstagramfilters.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mca.androidinstagramfilters.R;

import java.util.List;

import io.github.rockerhieu.emojicon.EmojiconTextView;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {

    Context context;
    List<String> emojiList;
    EmojiAdapterListener listener;

    public EmojiAdapter(Context context, List<String> emojiList, EmojiAdapterListener listener) {
        this.context = context;
        this.emojiList = emojiList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public EmojiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.emoji_item,parent,false);
        return new EmojiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiViewHolder holder, int position) {
      holder.emoji_text_view.setText(emojiList.get(position));
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }


    public class EmojiViewHolder extends RecyclerView.ViewHolder {
        EmojiconTextView emoji_text_view;
        public EmojiViewHolder(View itemView) {
            super(itemView);
            emoji_text_view = (EmojiconTextView)itemView.findViewById(R.id.emoji_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEmojiItemSelected(emojiList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface EmojiAdapterListener{
        void onEmojiItemSelected(String emoji);
    }
}
