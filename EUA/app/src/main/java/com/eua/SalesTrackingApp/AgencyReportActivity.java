package com.eua.SalesTrackingApp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.VisitReport;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AgencyReportActivity extends AppManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private String visitId;
    private String loggedUserId;
    private EditText interviewerName;
    private EditText stock;
    private EditText brochureQty;
    private EditText comments;
    private String latitude = "";
    private String longitude = "";
    private String error = "";
    private SendReport mReportTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_report);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("title"));
        getSupportActionBar().setHomeButtonEnabled(true);
        interviewerName = (EditText) findViewById(R.id.interviewer_name);
        brochureQty = (EditText)findViewById(R.id.brochure_quantity);
        stock = (EditText)findViewById(R.id.stock_quantity);
        comments = (EditText) findViewById(R.id.comments_edit_view);
        Button reportButton = (Button)findViewById(R.id.report);
        visitId = intent.getStringExtra("id");
        loggedUserId = UserSessionManager.getInstance(getApplicationContext()).getLoggedUserId();
        reportButton.setOnClickListener(reportInterview);
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    private View.OnClickListener reportInterview = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            String dateHour = sdf.format(date);
            String name = interviewerName.getText().toString();
            String stockQty = stock.getText().toString();
            String brochures = brochureQty.getText().toString();
            String commentsText = comments.getText().toString();
            String lat = latitude;
            String lng = longitude;
            if (isNetworkConnected()){
                mReportTask = new SendReport(visitId, loggedUserId, name, stockQty, brochures, commentsText, dateHour, lat, lng);
                mReportTask.execute((Void) null);
            }else{
                VisitReport vr = new VisitReport(visitId, loggedUserId, name, stockQty, brochures, commentsText, lat, lng);
                vr.save();
                Toast.makeText(getApplicationContext(), "No estás conectado a Internet, inténtalo más tarde", Toast.LENGTH_LONG).show();
                List<VisitReport> vrpts = VisitReport.listAll(VisitReport.class);
                Log.e("INFO:","Acabas de guardar " + String.valueOf(vrpts.size()) + "reporte");
            }
        }
    };

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public class SendReport extends AsyncTask<Void, Void, Boolean> {
        String reportResponse = "";
        String visitId;
        String loggedUserId;
        String interviewerName;
        String stockQty;
        String brochureQty;
        String commentsText;
        String date;
        String lat;
        String lng;

        SendReport(String visit, String userId, String intName, String stock, String broch, String comments, String dateTime, String latitude, String longitude) {
            visitId = visit;
            loggedUserId = userId;
            interviewerName = intName;
            stockQty = stock;
            brochureQty = broch;
            commentsText = comments;
            date = dateTime;
            lat = latitude;
            lng = longitude;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                final Call<ReportResponse> call = apiService.sendReport(visitId, loggedUserId, interviewerName, stockQty, brochureQty, commentsText, date, lat, lng);
                call.enqueue(new Callback<ReportResponse>() {
                    @Override
                    public void onResponse(Response<ReportResponse> response, Retrofit retrofit) {
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            ReportResponse serverResponse = response.body();
                            reportResponse = serverResponse.Visita_VisitaAppsGuardaActualizaResult;
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
                Toast.makeText(getApplicationContext(), reportResponse, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                Log.e("AAAAAAAAAA", error);
            }
        }

        @Override
        protected void onCancelled() {
        }
    }

    @Override
    public void onStart(){
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location != null) {
            //TODO How to send location parameters to webservice
            latitude = String.valueOf(location.getLatitude());  //Text.setText(String.valueOf(location.getLatitude()));
            longitude = String.valueOf(location.getLongitude()); //mLongitudeText.setText(String.valueOf(location.getLongitude()));
        }else{
            Log.e("GPSing", "null location");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onPause(){
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
