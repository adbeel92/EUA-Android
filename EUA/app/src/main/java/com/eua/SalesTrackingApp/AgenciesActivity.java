package com.eua.SalesTrackingApp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;
import com.eua.SalesTrackingApp.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
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
    private String promotorId = "15";
    boolean success = false;

    //private GetAgenciesTask mAgencyTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agencias");
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_agencies);
        generalAgencies = (ListView)findViewById(R.id.generalAgencies);
//        mAgencyTask = new GetAgenciesTask(UserSessionManager.getInstance(getApplicationContext()).getLoggedUserId());
//        mAgencyTask.execute((Void) null);
        final Call<AgencyResponse> call = apiService.getAgencyResponse(agencyId, agencyProfileId, promotorId, agencyActive, agencyCountryId);
        call.enqueue(new Callback<AgencyResponse>() {
            @Override
            public void onResponse(Response<AgencyResponse> response, Retrofit retrofit) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    success = true;
                    AgencyResponse agencyResponse = response.body();
                    agenciesList = agencyResponse.Visita_VisitaAppsAgenciasResult;
                    generalAgencies.setAdapter(new CustomAgenciesAdapter(AgencyDetailActivity.class, agenciesList, false));
                    Log.e("AGENCIEs", String.valueOf(agenciesList.size()));
                } else {
                    success = true;
                    error = "No se pudo obtener datos del servidor. Status " + String.valueOf(statusCode);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                error = t.getMessage();
            }
        });
    }
}
