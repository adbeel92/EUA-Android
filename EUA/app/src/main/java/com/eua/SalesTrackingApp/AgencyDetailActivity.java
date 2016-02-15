package com.eua.SalesTrackingApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AgencyDetailActivity extends AppManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_detail);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("title"));
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
