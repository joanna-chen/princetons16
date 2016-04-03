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

/**
 * Created by jerry on 02/04/16.
 */
public class TypeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> type;

    public TypeAdapter(Context context, ArrayList<String> type) {
        this.context = context;
        this.type = type;
    }

    private class ViewHolder {
        TextView typeName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tile_item, null);
            holder = new ViewHolder();
            holder.typeName = (TextView) convertView.findViewById(R.id.type_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // set data
        holder.typeName.setText(type.get(position));
        //holder.typeName.setTag();

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return type.size();
    }

    @Override
    public Object getItem(int position) {
        return type.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }
}
