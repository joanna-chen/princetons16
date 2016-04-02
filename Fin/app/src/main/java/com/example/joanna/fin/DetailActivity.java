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
import java.util.HashMap;
import java.util.Vector;

public class DetailActivity extends AppCompatActivity{

    private ArrayAdapter<String> adapter;
    public static final String FIREBASE = "https://dazzling-heat-9788.firebaseio.com/activities";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase(FIREBASE);

        setContentView(R.layout.activity_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ImageAdapter(this));

        Intent intent = getIntent();
        final Long key = (Long)intent.getSerializableExtra("key");
        HashMap<String, Vector<Task>> hashMap = (HashMap<String, Vector<Task>>)intent.getSerializableExtra("map");
        Log.v("HashMapTest", key.toString());

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.v("vector?", snapshot.child(key.toString()).toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });

//        adapter = new TaskAdapter(this, hashMap.get(findViewById(R.id.key));
//        listView.setAdapter(adapter);

    }


}
