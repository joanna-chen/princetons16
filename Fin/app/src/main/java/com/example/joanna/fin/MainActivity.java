package com.example.joanna.fin;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Intent;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import com.firebase.client.Firebase;
import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    public static final String FIREBASE = "https://dazzling-heat-9788.firebaseio.com/activities/";
    private static String[] stringList = {"abc", "ahh", "joanna"};
    private HashSet<String> typeSet;
    private HashSet<Type> typeSetObj;
    private ArrayAdapter<String> adapter;
    private GridView gridview;
    private Hashtable<String, ArrayList<Task>> typeMap;
    private String addActivityName;
    private String addCategoryName;
    private Context context;
    private static ArrayList<RunningTask> running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Firebase.setAndroidContext(this);
        final Firebase myFirebaseRef = new Firebase(FIREBASE);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridview = (GridView) findViewById(R.id.gridview);

        typeSet = new HashSet<>();
        typeSetObj = new HashSet<>();
        typeMap = new Hashtable<String, ArrayList<Task>>();

        addActivityName = "";
        addCategoryName = "";


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
                        typeMap.put(newType, new ArrayList<Task>());
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

//        myFirebaseRef.addChildEventListener(new ChildEventListener() {
//            // Retrieve new posts as they are added to the database
//            @Override
//            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
//                String newType = snapshot.child("type").getValue().toString();
//                Task newTask = new Task(snapshot.child("name").getValue().toString());
//                if (typeMap.containsKey(newType)) {
//                    typeMap.get(newType).add(newTask);
//                } else {
//                    typeMap.put(newType, new ArrayList<Task>());
//                    typeMap.get(newType).add(newTask);
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot snapshot) {
//                Log.v("removed", "child");
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot snapshot, String string) {
//                Log.v("changed", "child");
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot snapshot, String string) {
//                Log.v("changed", "child");
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//            //... ChildEventListener also defines onChildChanged, onChildRemoved,
//            //    onChildMoved and onCanceled, covered in later sections.
//        });

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, stringList/*typeSet.toArray(new String[typeSet.size()])*/);
//        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                sendMessage(gridview, parent, position, id);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("New Activity");

                // Set up the input
                final EditText input_activity = new EditText(context);
                final EditText input_category = new EditText(context);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input_activity.setInputType(InputType.TYPE_CLASS_TEXT);
                input_activity.setHint(Html.fromHtml("<small style=\"text-color: gray;\"><i>" + "Activity" + "</i></small>"));
                input_category.setInputType(InputType.TYPE_CLASS_TEXT);
                input_category.setHint(Html.fromHtml("<small style=\"text-color: gray;\"><i>" + "Category" + "</i></small>"));
                // add to layout
                layout.addView(input_activity);
                layout.addView(input_category);
                // add to builder
                builder.setView(layout);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addActivityName = input_activity.getText().toString();
                        addCategoryName = input_category.getText().toString();
                        // add to firebase
                        Map<String, String> post1 = new HashMap<String, String>();
                        post1.put("name", addActivityName);
                        post1.put("type", addCategoryName);
                        myFirebaseRef.push().setValue(post1);
                        Log.v(addActivityName, addCategoryName);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isConnected = PebbleKit.isWatchConnected(this);
        Toast.makeText(this, "Pebble " + (isConnected ? "is" : "is not") + " connected!", Toast.LENGTH_LONG).show();
        // Create a new dictionary
        PebbleDictionary dict = new PebbleDictionary();
        // The key representing a contact name is being transmitted
        final int AppKeyContactName = 0;
        final int AppKeyAge = 1;

        // Get data from the app
        final String contactName = "joanna";
        final int age = 7;

        // Add data to the dictionary
        dict.addString(AppKeyContactName, contactName);
        dict.addInt32(AppKeyAge, age);

        Log.v("before", "sending");
        final UUID appuuid = UUID.fromString("469f68b9-5c53-4d0c-b15f-06400e491b73");
        // send the dictionary
        PebbleKit.sendDataToPebble(getApplicationContext(), appuuid, dict);
        Log.v("message", "sent");

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

    public void sendMessage(View view, AdapterView<?> parent, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("map", typeMap);
        intent.putExtra("key", (String) parent.getItemAtPosition(position));
//        Log.v("bloop", parent.getSelectedItem().getClass().getName());
        Log.v("extra", id + "");
        startActivity(intent);
    }

    public void updateAdapter() {
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, typeMap.keySet().toArray(new String[typeSet.size()]));
        gridview.setAdapter(adapter);
    }

    public static RunningTask findInRunning(String name) {
        for (int i = 0; i < running.size(); i++) {
            if (running.get(i).getTask().getName().equals(name)) {
                return running.get(i);
            }
        }
        return null;
    }

    public static int findIndexInRunning(String name) {
        for (int i = 0; i < running.size(); i++) {
            if (running.get(i).getTask().getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void startTask(Task task) {
        RunningTask newRunning = new RunningTask(task);
        newRunning.start();
        running.add(newRunning);
    }

    public void endTask(final Task task) {
        RunningTask found = findInRunning(task.getName());
        if (found == null) return;
        found.end();
        final String endTime = found.getEnd() + "";
        final String startTime = found.getStart() + "";

        // put into firebase
        final Firebase myFirebaseRef = new Firebase(FIREBASE);

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.v("there are ", snapshot.getChildrenCount() + " children");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String newType = postSnapshot.child("type").getValue().toString();
                    String newTask = postSnapshot.child("name").getValue().toString();

                    if (newTask.equals(task.getName())) {
                        Firebase refChild = myFirebaseRef.child("time");
                        Map<String, String> post1 = new HashMap<String, String>();
                        post1.put("Start", startTime);
                        post1.put("End", endTime);
                        refChild.push().setValue(post1);
                        Log.v(startTime, endTime);

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

        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("name", addActivityName);
        post1.put("type", addCategoryName);
        myFirebaseRef.push().setValue(post1);
        Log.v(addActivityName, addCategoryName);

    }
}
