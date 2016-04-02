package com.example.joanna.fin;

/**
 * Created by jerry on 02/04/16.
 */
public class Task {
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

}
