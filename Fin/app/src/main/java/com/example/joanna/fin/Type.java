package com.example.joanna.fin;

import java.util.Vector;

/**
 * Created by jerry on 02/04/16.
 */
public class Type {
    public Type() {

    }

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<Task> getFirst() {
        return first;
    }

    public void setFirst(Vector<Task> first) {
        this.first = first;
    }

    private String name;
    private Vector<Task> first;
}
