package com.example.mypc.androidinstagramfilter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.mypc.androidinstagramfilter.Interface.EditImageFragmentListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditImageFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private EditImageFragmentListener listener;
    SeekBar seekbar_britghtness, seekbar_constrant,seekbar_saturation;


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
        seekbar_britghtness = (SeekBar)itemView.findViewById(R.id.seekbar_brightness);
        seekbar_constrant = (SeekBar)itemView.findViewById(R.id.seekbar_constraint);

        seekbar_saturation = (SeekBar)itemView.findViewById(R.id.seekbar_saturation);
        seekbar_britghtness.SetMax(200);
        seekbar_britghtness.SetProgress(100);


        seekbar_constrant.SetMax(220);
        seekbar_constrant.SetProgress(0);

        seekbar_saturation.SetMax(30);
        seekbar_saturation.SetProgress(10);

        seekbar_saturation.setOnSeekBarChangeListener(this);
        seekbar_constrant.setOnSeekBarChangeListener(this);
        seekbar_britghtness.setOnSeekBarChangeListener(this);






return itemView;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(listener != null)
        {
            if(seekBar.getId() == R.id.seekbar_brightness)
            {
                listener.onBrightnessChanged(progress-100);
            }
            else if(seekBar.getId() == R.id.seekbar_constraint)
            {
                progress+=10;
                float value = .10f*progress;
                listener.onConstraintChanged(value);
            }
            else if(seekBar.getId() == R.id.seekbar_saturation)
            {
                float value = .10f*progress;
                listener.onSaturationChanged();Changed(value);
            }

            }
        }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if(listener!=null)
        {
          listener.onEditStarted();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(listener!=null)
        {
            listener.onEditCompleted();
        }
    }

    public void resetControls()
    {
        seekbar_britghtness.SetProgress(100);
        seekbar_constrant.SetProgress(0);
        seekbar_saturation.SetProgress(10);

    }
}
