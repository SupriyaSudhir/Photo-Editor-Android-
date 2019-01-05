package com.example.mypc.androidinstagramfilter.Interface;

public interface EditImageFragmentListener {
    void onBrightnessChanged(int brightness);
    void onSaturationChanged(float saturation);
    void onConstraintChanged(float constrant);
    void onEditStarted();
    void onEditCompleted();
}
