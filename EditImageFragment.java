package com.example.mca.androidinstagramfilters;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.mca.androidinstagramfilters.Interface.EditImageFragmentListener;


/**
 * A simple {@link Fragment} subclass.
 */

//this fragment is used to display the image controls like b,s,c
public class EditImageFragment extends BottomSheetDialogFragment implements SeekBar.OnSeekBarChangeListener {

    private EditImageFragmentListener listener;
    SeekBar seekbar_brightness,seekbar_constrant,seekbar_saturation;
    //seekbar widget is used to control b,s,c


    //listner
    public void  setListener(EditImageFragmentListener listener)
    {
        this.listener = listener;
    }

    static EditImageFragment instance;

    public static EditImageFragment getInstance() {
        if(instance == null)
            instance = new EditImageFragment();
        return instance;
    }


    public EditImageFragment() {
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
       View itemView = inflater.inflate(R.layout.fragment_edit_image, container, false);

        seekbar_brightness = (SeekBar)itemView.findViewById(R.id.seekbar_brightness);
        seekbar_constrant = (SeekBar)itemView.findViewById(R.id.seekbar_constraint);
        seekbar_saturation = (SeekBar)itemView.findViewById(R.id.seekbar_saturation);

        //b values can be btwn -100 to +100
        seekbar_brightness.setMax(200);
        seekbar_brightness.setProgress(100);

        //contrast  and saturation in the form of float values
        // keeping contrast value b/w 1.0 - 3.0
        seekbar_constrant.setMax(20);
        seekbar_constrant.setProgress(0);

        // keeping saturation value b/w 0.0 - 3.0
        seekbar_saturation.setMax(30);
        seekbar_saturation.setProgress(10);

        seekbar_saturation.setOnSeekBarChangeListener(this);
        seekbar_constrant.setOnSeekBarChangeListener(this);
        seekbar_brightness.setOnSeekBarChangeListener(this);

        return itemView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(listener != null)
        {
            if(seekBar.getId() == R.id.seekbar_brightness)
            {
                // brightness values are b/w -100 to +100
                listener.onBrightnessChanged(progress-100);
            }
            else if(seekBar.getId() == R.id.seekbar_constraint)
            {
                // converting int value to float
                // contrast values are b/w 1.0f - 3.0f
                // progress = progress > 10 ? progress : 10;
                progress+=10;
                float value = .10f*progress;
                listener.onConstrantChanged(value);
            }
            else if(seekBar.getId() == R.id.seekbar_saturation)
            {
                // converting int value to float
                // saturation values are b/w 0.0f - 3.0f
                float value = .10f*progress;
                listener.onSaturationChanged(value);
            }

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if(listener!=null)
            listener.onEditStarted();

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(listener != null)
            listener.onEditCompleted();
    }

    public void resetControls()
    {
        seekbar_brightness.setProgress(100);
        seekbar_constrant.setProgress(0);
        seekbar_saturation.setProgress(10);

    }
}
