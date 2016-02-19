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
    private String promotorId = "";
    boolean success = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agencias");
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_agencies);
        generalAgencies = (ListView)findViewById(R.id.generalAgencies);
        promotorId = UserSessionManager.getInstance(getApplicationContext()).getLoggedUserId();
        final Call<AgencyResponse> call = apiService.getAgencyResponse(agencyId, agencyProfileId, "15", agencyActive, agencyCountryId);
        call.enqueue(new Callback<AgencyResponse>() {
            @Override
            public void onResponse(Response<AgencyResponse> response, Retrofit retrofit) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    agenciesList = response.body().Visita_VisitaAppsAgenciasResult;
                    generalAgencies.setAdapter(new CustomAgenciesAdapter(AgencyDetailActivity.class, agenciesList, false));
                    if (agenciesList.size() == 0){
                        Toast.makeText(getApplicationContext(), "No hay agencias", Toast.LENGTH_LONG).show();
                    }
                } else {
                    success = true;
                    error = "No se pudo obtener datos del servidor. Status " + String.valueOf(statusCode);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                error = t.getMessage();
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
