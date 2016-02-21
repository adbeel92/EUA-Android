package com.eua.SalesTrackingApp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;
import com.eua.SalesTrackingApp.models.AgencyVisit;
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

public class ProgrammingActivity extends AppManager {

    private ArrayList<AgencyVisit> visitsList = new ArrayList<>();
    private String error = "";
    private Boolean success;
    private ListView programmedAgencies;
    private Date date = new Date();
    String promotorId = "";
    private GetVisitsTask visitsTask;


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
        String dayOfTheWeek = sdf.format(date);
        today.setText(dayOfTheWeek.substring(0, 1).toUpperCase() + dayOfTheWeek.substring(1));
        mProgressView = findViewById(R.id.login_progress);
        mEmtpyText = (TextView) findViewById(R.id.emptyResultsText);
        mEmtpyText.setVisibility(View.INVISIBLE);
        promotorId = UserSessionManager.getInstance(getApplicationContext()).getLoggedUserId();
        visitsTask = new GetVisitsTask(sdf.format(date), sdf.format(date));
        visitsTask.execute((Void) null);
        showProgress(true);
    }

    public class GetVisitsTask extends AsyncTask<Void, Void, Boolean> {
        final Context context = getApplicationContext();
        private String startDate;
        private String endDate;

        GetVisitsTask(String firstDate, String secondDate ) {
            //startDate = firstDate;
            //endDate = secondDate;
            startDate = "02/13/2016";
            endDate = "02/13/2016";
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final Call<AgencyVisitResponse> call = apiService.getVisitResponse(startDate, endDate , "9");
            //final Call<AgencyVisitResponse> call = apiService.getVisitResponse(agencyId, agencyProfileId, "15", agencyActive, agencyCountryId);
            try {
                Response<AgencyVisitResponse> response = call.execute();
                visitsList = response.body().Visita_PromotorListadoResult;
                success = true;
                if (visitsList.size() == 0) {
                    Toast.makeText(getApplicationContext(), "No hay visitas programadas", Toast.LENGTH_LONG).show();
                }
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
                if (visitsList.size()==0){
                    mEmtpyText.setText("No hay agencias");
                    mEmtpyText.setVisibility(View.VISIBLE);
                }else{
                    programmedAgencies.setAdapter(new CustomAgenciesVisitAdapter(AgencyReportActivity.class, visitsList, false));
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
