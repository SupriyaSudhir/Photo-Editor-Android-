package com.example.mca.androidinstagramfilters;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mca.androidinstagramfilters.Adapter.EmojiAdapter;
import com.example.mca.androidinstagramfilters.Interface.EmojiFragmentListener;

import ja.burhanrashid52.photoeditor.PhotoEditor;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmojiFragment extends BottomSheetDialogFragment implements EmojiAdapter.EmojiAdapterListener {

    RecyclerView recyler_emoji;
    static  EmojiFragment instance;

    EmojiFragmentListener listener;

    public void setListener(EmojiFragmentListener listener) {
        this.listener = listener;
    }

    public static EmojiFragment getInstance() {
        if(instance == null)
            instance = new EmojiFragment();
        return instance;
    }

    public EmojiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_emoji, container, false);

        recyler_emoji = (RecyclerView)itemView.findViewById(R.id.recycler_emoji);
        recyler_emoji.setHasFixedSize(true);
        recyler_emoji.setLayoutManager(new GridLayoutManager(getActivity(),5));

        EmojiAdapter adapter = new EmojiAdapter(getContext(), PhotoEditor.getEmojis(getContext()),this);
        recyler_emoji.setAdapter(adapter);
        return  itemView;
    }

    @Override
    public void onEmojiItemSelected(String emoji) {
        listener.onEmojiSelected(emoji);
    }
}
