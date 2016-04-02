package com.example.joanna.fin;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.content.Intent;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import com.firebase.client.Firebase;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    public static final String FIREBASE = "https://dazzling-heat-9788.firebaseio.com/activities";
    private static String[] stringList = {"abc", "ahh", "joanna"};
    private HashSet<String> typeSet;
    private HashSet<Type> typeSetObj;
    private ArrayAdapter<String> adapter;
    private GridView gridview;
    private Hashtable<String, Vector<Task>> typeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase(FIREBASE);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridview = (GridView) findViewById(R.id.gridview);

        typeSet = new HashSet<>();
        typeSetObj = new HashSet<>();
        typeMap = new Hashtable<String, Vector<Task>>();


        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.v("there are ", snapshot.getChildrenCount() + " children");

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String newType = postSnapshot.child("type").getValue().toString();
                    Task newTask = new Task(postSnapshot.child("name").getValue().toString());
                    if (typeMap.containsKey(newType)) {
                        typeMap.get(newType).add(newTask);
                    } else {
                        typeMap.put(newType, new Vector<Task>());
                        typeMap.get(newType).add(newTask);
                    }
//                    if (!typeSet.contains(newType)) {
//                        typeSet.add(newType);
//                        typeSetObj.add(new Type(newType));
//                        typeMap.put(newType, first.add(new Task(postSnapshot.child("name").getValue().toString())));
//                    }

                    Log.v(newType, "added");

                }
                updateAdapter();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, stringList/*typeSet.toArray(new String[typeSet.size()])*/);
//        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                sendMessage(gridview, parent, position);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view, AdapterView<?> parent, int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("map", typeMap);
        intent.putExtra("tasks", parent.getSelectedItem().toString());
        Log.v("extra", parent.getSelectedItem().toString());
        startActivity(intent);
    }

    public void updateAdapter() {
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, typeMap.keySet().toArray(new String[typeSet.size()]));
        gridview.setAdapter(adapter);
    }
}
