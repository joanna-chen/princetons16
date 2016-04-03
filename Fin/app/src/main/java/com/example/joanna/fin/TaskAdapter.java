package com.example.joanna.fin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by jerry on 02/04/16.
 */
public class TaskAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Task> task;

    public TaskAdapter(Context context, ArrayList<Task> task) {
        this.context = context;
        this.task = task;
    }

    private class ViewHolder {
        TextView taskName;
        ImageView progress;
        ImageButton start;
        ImageButton stop;
        TextView timeElapsed;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.taskName = (TextView) convertView.findViewById(R.id.task_name);
            holder.progress = (ImageView) convertView.findViewById(R.id.progress);
            holder.start = (ImageButton) convertView.findViewById(R.id.start);
            holder.stop = (ImageButton) convertView.findViewById(R.id.stop);
            holder.timeElapsed = (TextView) convertView.findViewById(R.id.time_elapsed);


            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // set data
        final Task task = (Task) getItem(position);
        holder.taskName.setText(task.getName());
        holder.progress.setImageResource(R.drawable.sample_0);
        holder.start.setImageResource(android.R.drawable.ic_media_play);
        holder.stop.setImageResource(android.R.drawable.ic_media_pause);
        // hhmmss calculations
        if (task.getRunning()) {
            long diff = System.currentTimeMillis() / 1000 - task.getStart();
            long hr = diff / 3600;
            long rem = diff % 3600;
            long mn = rem/60;
            long sec = rem%60;
            String hrStr = (hr<10 ? "0" : "")+hr;
            String mnStr = (mn<10 ? "0" : "")+mn;
            String secStr = (sec<10 ? "0" : "")+sec;
            holder.timeElapsed.setText(hrStr + ":" + mnStr + ":" + secStr);
        } else {
            holder.timeElapsed.setText("");
        }




        //setting on click listener for isCheckedCheckBox
        holder.start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Toast.makeText(this, "" + task.getName(), Toast.LENGTH_SHORT).show();
                MainActivity.startTask(task);
                task.setRunning(true);
                task.setStart(System.currentTimeMillis() / 1000);
            }
        });

        //setting on click listener for isCheckedCheckBox
        holder.stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.endTask(task);
                task.setRunning(false);
                Toast.makeText(context, task.getName() + " data recorded.", Toast.LENGTH_SHORT).show();

            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return task.size();
    }

    @Override
    public Object getItem(int position) {
        return task.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }


}
