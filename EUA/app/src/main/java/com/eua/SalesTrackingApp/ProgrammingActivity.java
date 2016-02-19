package com.eua.SalesTrackingApp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;
import com.eua.SalesTrackingApp.models.AgencyVisit;
import com.eua.SalesTrackingApp.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ProgrammingActivity extends AppManager {

    private ArrayList<AgencyVisit> agenciesList = new ArrayList<>();
    private String error = "";
    private ListView programmedAgencies;
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    final String startDate = sdf.format(date);
    final String endDate = sdf.format(date);
    String promotorId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming);
        getSupportActionBar().setHomeButtonEnabled(true);
        UserSessionManager usm = new UserSessionManager(getApplicationContext());
        setTitle("Programación");
        final TextView today = (TextView) findViewById(R.id.todayText);
        programmedAgencies = (ListView)findViewById(R.id.programmedAgencies);
        promotorId = usm.getLoggedUserId();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd/MM/yyyy");
        String dayOfTheWeek = sdf.format(new Date());
        today.setText(dayOfTheWeek.substring(0, 1).toUpperCase() + dayOfTheWeek.substring(1));

        final Call<AgencyVisitResponse> call = apiService.getVisitResponse("02/13/2016", "02/13/2016", "9");
        call.enqueue(new Callback<AgencyVisitResponse>() {
            @Override
            public void onResponse(Response<AgencyVisitResponse> response, Retrofit retrofit) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    agenciesList = response.body().Visita_PromotorListadoResult;
                    programmedAgencies.setAdapter(new CustomAgenciesVisitAdapter(AgencyReportActivity.class, agenciesList, false));
                    if (agenciesList.size()==0){
                        Toast.makeText(getApplicationContext(), "No hay visitas programadas", Toast.LENGTH_LONG).show();
                    }
                } else {
                    error = "No se pudo obtener datos del servidor. Status " + String.valueOf(statusCode);
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
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
