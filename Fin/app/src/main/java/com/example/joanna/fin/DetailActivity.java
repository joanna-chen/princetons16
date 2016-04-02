package com.example.joanna.fin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class DetailActivity extends AppCompatActivity{

    private TaskAdapter adapter;
    public static final String FIREBASE = "https://dazzling-heat-9788.firebaseio.com/activities";
    private static HashMap<String, ArrayList<Task>> hashMap;
    private static String key;
    private static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase(FIREBASE);

        setContentView(R.layout.activity_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview);
        //listView.setAdapter(new ImageAdapter(this));

        Intent intent = getIntent();
        key = (String)intent.getSerializableExtra("key");
        hashMap = (HashMap<String, ArrayList<Task>>)intent.getSerializableExtra("map");
//        Log.v("HashMapTest", hashMap.get(key.intValue()).toString());
        updateAdapter();

//        myFirebaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                //Log.v("vector?", hashMap.get(key.intValue()).toString());
//                updateAdapter();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//
//        });



    }

    private void updateAdapter() {
        adapter = new TaskAdapter(this, hashMap.get(key));
        listView.setAdapter(adapter);
    }


}
