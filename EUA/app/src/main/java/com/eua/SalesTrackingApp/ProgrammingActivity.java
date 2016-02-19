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
    private GetAgenciesVisitTask mActivTask;
    private ListView programmedAgencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming);
        getSupportActionBar().setHomeButtonEnabled(true);
        UserSessionManager usm = new UserSessionManager(getApplicationContext());
        setTitle("Programación");
        final TextView today = (TextView) findViewById(R.id.todayText);
        programmedAgencies = (ListView)findViewById(R.id.programmedAgencies);
        mActivTask = new GetAgenciesVisitTask(usm.getLoggedUserId());
        mActivTask.execute((Void) null);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd/MM/yyyy");
        String dayOfTheWeek = sdf.format(new Date());
        today.setText(dayOfTheWeek.substring(0,1).toUpperCase()+dayOfTheWeek.substring(1));
    }

    public class GetAgenciesVisitTask extends AsyncTask<Void, Void, Boolean> {
        final Context context = getApplicationContext();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        final String startDate = sdf.format(date);
        final String endDate = sdf.format(date);
        String promotorId = "";


        GetAgenciesVisitTask(String loggedUserId) {
            promotorId = loggedUserId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                final Call<AgencyVisitResponse> call = apiService.getVisitResponse(startDate, endDate, "9");
                call.enqueue(new Callback<AgencyVisitResponse>() {
                    @Override
                    public void onResponse(Response<AgencyVisitResponse> response, Retrofit retrofit) {
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            AgencyVisitResponse agencyVisitResponse = response.body();
                            agenciesList = agencyVisitResponse.Visita_PromotorListadoResult;
                            Log.e("AGENCIEs", String.valueOf(agenciesList.size()));
                        } else {
                            error = "No se pudo obtener datos del servidor. Status " + String.valueOf(statusCode);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        error = t.getMessage();
                    }
                });
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                error = e.getMessage();
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                programmedAgencies.setAdapter(new CustomAgenciesAdapter(AgencyReportActivity.class, agenciesList, true));
            } else {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();;
            }
        }

        @Override
        protected void onCancelled() {
        }
    }
}
