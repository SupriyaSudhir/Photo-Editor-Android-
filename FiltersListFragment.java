package com.example.mca.androidinstagramfilters;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mca.androidinstagramfilters.Adapter.ThumbnailAdapter;
import com.example.mca.androidinstagramfilters.Interface.FiltersListFragmentListener;
import com.example.mca.androidinstagramfilters.Utils.BitmapUtils;
import com.example.mca.androidinstagramfilters.Utils.SpacesItemDecoration;
import com.zomato.photofilters.FilterPack;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.utils.ThumbnailItem;
import com.zomato.photofilters.utils.ThumbnailsManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FiltersListFragment extends BottomSheetDialogFragment implements FiltersListFragmentListener {
    RecyclerView recyclerView;
    ThumbnailAdapter adapter;
    List<ThumbnailItem> thumbnailItems;

    FiltersListFragmentListener listener;

   static FiltersListFragment instance;
   static Bitmap bitmap;

    public static FiltersListFragment getInstance(Bitmap bitmapSave) {
        bitmap = bitmapSave;
        if(instance == null) {
            instance = new FiltersListFragment();

        }
        return instance;
    }

    public void SetListener(FiltersListFragmentListener listener)
    { // this method provides callback method to mainactivity wnv new filter is selected
        this.listener = listener;

    }

    public FiltersListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_filters_list, container, false);
        thumbnailItems = new ArrayList<>();
        adapter = new ThumbnailAdapter(thumbnailItems, this, getActivity());


        recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(adapter);

        displayThumbnail(bitmap);
        return itemView;
    }

    public void displayThumbnail(final Bitmap bitmap) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Bitmap thumbImg;
                if (bitmap == null)

                    thumbImg = BitmapUtils.getBitmapFromAssets(getActivity(), MainActivity.pictureName, 100, 100);

                else

                    thumbImg = Bitmap.createScaledBitmap(bitmap, 100, 100, false);

                if(thumbImg == null)
                    return;
                ThumbnailsManager.clearThumbs();
                thumbnailItems.clear();

                // add normal bitmap first
                ThumbnailItem thumbnailItem = new ThumbnailItem();
                thumbnailItem.image = thumbImg;
                thumbnailItem.filterName = "Normal";
                ThumbnailsManager.addThumb(thumbnailItem);

                List<Filter> filters = FilterPack.getFilterPack(getActivity()); // provides list of all filters from liabrary

                for (Filter filter : filters) {
                    ThumbnailItem tI = new ThumbnailItem(); //object
                    tI.image = thumbImg; //image
                    tI.filter = filter; //filtertype
                    tI.filterName = filter.getName();//filtername
                    ThumbnailsManager.addThumb(tI); // manager will handle this
                }

                thumbnailItems.addAll(ThumbnailsManager.processThumbs(getActivity()));
                //each thumbnail item is added to ThumbnailManager to manage dem, this process thumbnails are added back to thumbnailitem

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged(); // all this done in background thread and we shouldnot block the main thread
                    }
                });

            }

        };
        new Thread(r).start();
    }


    @Override
    public void onFilterSelected(Filter filter) {
        if(listener != null)

            listener.onFilterSelected(filter);

    }
}
