package com.eua.SalesTrackingApp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;
import com.eua.SalesTrackingApp.models.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AgenciesActivity extends AppManager {
    private ArrayList<Agency> agenciesList;
    private ListView generalAgencies;
    private String error = "nothing";
    final private String agencyId = "0";
    final private String agencyProfileId = "0";
    final private String agencyActive = "1";
    final private String agencyCountryId = "0";
    private String promotorId = "";
    boolean success = false;
    private GetAgenciesTask agenciesTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agencias");
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_agencies);
        generalAgencies = (ListView)findViewById(R.id.generalAgencies);
        mProgressView = findViewById(R.id.login_progress);
        mEmtpyText = (TextView) findViewById(R.id.emptyResultsText);
        mEmtpyText.setVisibility(View.INVISIBLE);
        promotorId = UserSessionManager.getInstance(getApplicationContext()).getLoggedUserId();
        agenciesTask = new GetAgenciesTask();
        agenciesTask.execute((Void) null);
        showProgress(true);
    }



    public class GetAgenciesTask extends AsyncTask<Void, Void, Boolean> {
        final Context context = getApplicationContext();

        GetAgenciesTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final Call<AgencyResponse> call = apiService.getAgencyResponse(agencyId, agencyProfileId, promotorId, agencyActive, agencyCountryId);
            try {
                Response<AgencyResponse> response = call.execute();
                agenciesList = response.body().Visita_VisitaAppsAgenciasResult;
                success = true;
            } catch (IOException e) {
                success = false;
                error = e.getMessage();
            }
            return success;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);
            if (success) {
                if (agenciesList.size() == 0){
                    mEmtpyText.setText("No hay agencias");
                    mEmtpyText.setVisibility(View.VISIBLE);
                }else{
                    generalAgencies.setAdapter(new CustomAgenciesAdapter(AgencyDetailActivity.class, agenciesList, false));
                }
            } else{
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
        }
    }
}
