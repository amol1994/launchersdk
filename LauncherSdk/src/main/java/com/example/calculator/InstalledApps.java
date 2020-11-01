package com.example.calculator;

import android.graphics.drawable.Drawable;

public class InstalledApps {
    String label;
    String pacakageName;
    String activityClassName;
    String versionCode;
    String versionName;
    Drawable icon;


    public String getPacakageName() {
        return pacakageName;
    }

    public void setPacakageName(String pacakageName) {
        this.pacakageName = pacakageName;
    }

    public String getActivityClassName() {
        return activityClassName;
    }

    public void setActivityClassName(String activityClassName) {
        this.activityClassName = activityClassName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }


}
