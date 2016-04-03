package com.example.joanna.fin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
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
    private Typeface mainFont = null;
    private Typeface awesomeFont = null;

    public TypeAdapter(Context context, ArrayList<String> type) {
        this.context = context;
        this.type = type;
        mainFont = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-SemiBold.ttf");
        awesomeFont = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
    }

    private class ViewHolder {
        TextView typeName;
        TextView typeIcon;
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
            holder.typeIcon = (TextView) convertView.findViewById(R.id.type_icon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // set data
        holder.typeName.setText(type.get(position));
        // which green to use
        int whichGreen = position % 3;
        switch(whichGreen) {
            case 0:
                holder.typeName.setBackgroundResource(R.color.green1);
                holder.typeIcon.setBackgroundResource(R.color.green1);
                break;
            case 1:
                holder.typeName.setBackgroundResource(R.color.green2);
                holder.typeIcon.setBackgroundResource(R.color.green2);
                break;
            case 2:
                holder.typeName.setBackgroundResource(R.color.green3);
                holder.typeIcon.setBackgroundResource(R.color.green3);
                break;
            default:
                holder.typeName.setBackgroundResource(R.color.green1);
                holder.typeIcon.setBackgroundResource(R.color.green1);
                break;
        }
        holder.typeName.setTypeface(mainFont);
        holder.typeIcon.setTypeface(FontManager.getTypeface(context, FontManager.FONTAWESOME));

        // which icon
        if (type.get(position).equals("Exercise")) {
            holder.typeIcon.setText(R.string.icon_ball);
            Log.v("Exercise", "icon");
        } else if (type.get(position).equals("School")) {
            holder.typeIcon.setText(R.string.icon_book);
        } else if (type.get(position).equals("Entertainment")) {
            holder.typeIcon.setText(R.string.icon_entertainment);
        } else if (type.get(position).equals("Living") || type.get(position).equals("Health")) {
            holder.typeIcon.setText(R.string.icon_heart);
        } else {
            holder.typeIcon.setText("");
        }
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
