package com.eua.SalesTrackingApp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.eua.SalesTrackingApp.models.Agency;

import java.util.List;

public class ChoosingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);
        UserSessionManager usm = new UserSessionManager(getApplicationContext());
        setTitle(usm.getLoggedUserName());
        final ImageView iconA = (ImageView) findViewById(R.id.programmingIcon);
        final ImageView iconB = (ImageView) findViewById(R.id.agenciesIcon);
        iconA.setOnClickListener(nextActivity);
        iconB.setOnClickListener(nextActivity);
    }

    private View.OnClickListener nextActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageView icon = (ImageView)v;
            int iconId = icon.getId();
            Class activity = Activity.class;
            if (iconId==R.id.programmingIcon){
                activity = ProgrammingActivity.class;
            }else if(iconId==R.id.agenciesIcon){
                activity = AgenciesActivity.class;
            }
            Intent intent = new Intent(getApplicationContext(), activity);
            startActivity(intent);
            Log.e("Test", Agency.generatedAgencies()[4].getName());
            //finish();
        }
    };

    @Override
    protected void onStop(){
        AppManager am = new AppManager();
        UserSessionManager usm = new UserSessionManager(getApplicationContext());
        usm.logoutUser();
        super.onStop();
    }
}
