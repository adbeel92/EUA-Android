package com.eua.SalesTrackingApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.eua.SalesTrackingApp.models.Agency;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProgrammingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming);
        getSupportActionBar().setHomeButtonEnabled(true);
        UserSessionManager usm = new UserSessionManager(getApplicationContext());
        setTitle("Programación");
        final TextView today = (TextView) findViewById(R.id.todayText);
        final ListView programmedAgencies = (ListView)findViewById(R.id.programmedAgencies);
        final Agency[] agenciesList = Agency.generatedAgencies();
        programmedAgencies.setAdapter(new CustomAgenciesAdapter(AgencyReportActivity.class, agenciesList, true));
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd/MM/yyyy");
        String dayOfTheWeek = sdf.format(new Date());
        today.setText(dayOfTheWeek.substring(0,1).toUpperCase()+dayOfTheWeek.substring(1));
    }
}
