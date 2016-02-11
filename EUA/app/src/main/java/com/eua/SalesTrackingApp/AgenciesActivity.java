package com.eua.SalesTrackingApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.eua.SalesTrackingApp.models.Agency;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgenciesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agencias");
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_agencies);
        final ListView generalAgencies = (ListView)findViewById(R.id.generalAgencies);
        final Agency[] agenciesList = Agency.generatedAgencies();
        generalAgencies.setAdapter(new CustomAgenciesAdapter(AgencyDetailActivity.class, agenciesList, false));
    }
}
