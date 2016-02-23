package com.eua.SalesTrackingApp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;

import java.util.List;
public class ChoosingActivity extends AppManager {
    boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);
        setTitle(UserSessionManager.getInstance(getApplicationContext()).getLoggedUserName());
        final ImageView iconA = (ImageView) findViewById(R.id.programmingIcon);
        final ImageView iconB = (ImageView) findViewById(R.id.agenciesIcon);
        iconA.setOnClickListener(nextActivity);
        iconB.setOnClickListener(nextActivity);
        checkEnableGPS();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("test", "onResume");
        if (firstTime){
            Log.i("test", "it's the first time");
            firstTime = false;
        }

        else{
            Log.i("test", "it's not the first time");

        }

    }

    private void checkEnableGPS(){
        String provider = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(provider.equals("")){
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout_action){
            UserSessionManager.getInstance(getApplicationContext()).ultimateLogout();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            finish();
            startActivity(intent);
        }

        return false;
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
        }
    };
}
