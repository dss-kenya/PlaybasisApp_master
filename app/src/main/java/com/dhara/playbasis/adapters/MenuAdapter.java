package com.dhara.playbasis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dhara.playbasis.R;

import java.util.List;

/**
 * Created by USER on 07-02-2016.
 */
public class MenuAdapter extends ArrayAdapter<String>{
    private Context mContext;
    private String[] mMenuItems;
    private int RESOURCE;

    public MenuAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        mContext = context;
        RESOURCE = resource;
        mMenuItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh = null;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(RESOURCE, parent,false);
            vh = new ViewHolder();
            vh.txtMenuItem = (TextView)view.findViewById(R.id.txtMenuItem);
            view.setTag(vh);
        }else{
            vh = (ViewHolder)view.getTag();
        }

        vh.txtMenuItem.setText(mMenuItems[position]);
        return view;
    }

    static class ViewHolder  {
        TextView txtMenuItem;
    }
}
