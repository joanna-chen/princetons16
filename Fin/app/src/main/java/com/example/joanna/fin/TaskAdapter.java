package com.example.joanna.fin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by jerry on 02/04/16.
 */
public class TaskAdapter extends BaseAdapter {
    private Context context;
    private Vector<Task> task;

    public TaskAdapter(Context context, Vector<Task> task) {
        this.context = context;
        this.task = task;
    }

    private class ViewHolder {
        TextView taskName;
        ImageView progress;
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // set data
        Task task = (Task) getItem(position);
        holder.taskName.setText(task.getName());
        holder.progress.setImageResource(R.drawable.sample_0);

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
