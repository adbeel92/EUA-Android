package com.eua.SalesTrackingApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;

import java.util.ArrayList;

/**
 * Created by rubymobile on 2/4/16.
 */
public class CustomAgenciesAdapter extends BaseAdapter {
    Agency[] agenciesList;
    Class nextActivity;
    Boolean numeration;

    public CustomAgenciesAdapter(Class activityClass, Agency[] agencies, boolean num) {
        // TODO Auto-generated constructor stub
        agenciesList=agencies;
        nextActivity =activityClass;
        numeration=num;
    }

    @Override
    public int getCount() {
        return agenciesList.length;
    }

    @Override
    public Object getItem(int position) {
        return agenciesList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView index;
        TextView text;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.agency_list, parent, false);
        if (numeration){
            holder.index=(TextView) rowView.findViewById(R.id.agencyIndex);
            holder.index.setText(String.valueOf(position+1));
        }
        holder.text=(TextView) rowView.findViewById(R.id.agencyName);
        holder.text.setText(agenciesList[position].getName());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), nextActivity);
                intent.putExtra("title", agenciesList[position].getName());
                v.getContext().startActivity(intent);
            }
        });
        return rowView;
    }
}
