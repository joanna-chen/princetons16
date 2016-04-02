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

    protected Task(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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
        dest.writeString(name);
    }
}
