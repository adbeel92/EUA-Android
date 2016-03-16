package com.eua.SalesTrackingApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.AgencyVisit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by unobtainium on 19/02/16.
 */
public class CustomAgenciesVisitAdapter extends BaseAdapter {
    List<AgencyVisit> agenciesList;
    Class nextActivity;
    Boolean numeration;

    public CustomAgenciesVisitAdapter(Class activityClass, List<AgencyVisit> agencies, boolean num) {
        // TODO Auto-generated constructor stub
        agenciesList=agencies;
        nextActivity =activityClass;
        numeration=num;
    }

    @Override
    public int getCount() {
        return agenciesList.size();
    }

    @Override
    public Object getItem(int position) {
        return agenciesList.get(position);
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder=new Holder();
        final View rowView;
        rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.agency_list, parent, false);
        if (numeration){
            holder.index=(TextView) rowView.findViewById(R.id.agencyIndex);
            holder.index.setText(String.valueOf(position+1));
        }
        if (position % 2 == 1){
            rowView.setBackgroundColor(Color.rgb(173, 173, 173));
        }else{
            rowView.setBackgroundColor(Color.rgb(205, 205, 205));
        }
        holder.text=(TextView) rowView.findViewById(R.id.agencyName);
        holder.text.setText(agenciesList.get(position).getVisitasAgenciaNombre());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (agenciesList.get(position).getVisitasIDVisitado() == "0") {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(v.getContext(), nextActivity);
                    intent.putExtra("title", agenciesList.get(position).getVisitasAgenciaNombre());
                    intent.putExtra("id", agenciesList.get(position).getVisitasId());
                    v.getContext().startActivity(intent);
                } else {
                   Toast.makeText(parent.getContext(), "Esta agencia ha sido visitada anteriormente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rowView;
    }
}
