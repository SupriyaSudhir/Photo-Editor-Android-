package com.example.mca.androidinstagramfilters.Interface;

public interface EditImageFragmentListener  {
    void onBrightnessChanged(int brightness);
    void onSaturationChanged(float saturation);
    void onConstrantChanged(float constrant);
    void onEditStarted();
    void onEditCompleted();
}
