package com.example.joanna.fin;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jerry on 02/04/16.
 */
public class Task implements Parcelable {
    public Task() {

    }

    public Task(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
