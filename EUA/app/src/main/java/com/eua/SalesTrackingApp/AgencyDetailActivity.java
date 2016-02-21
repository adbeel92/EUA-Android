package com.eua.SalesTrackingApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;
import com.eua.SalesTrackingApp.models.AgencyVisit;
import com.google.gson.Gson;

import junit.framework.Test;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AgencyDetailActivity extends AppManager {
    private TextView agencyName;
    private TextView agencyAddress;
    private TextView agencyLegalId;
    private TextView agencyPhone;
    private TextView agencyEmail;
    private TextView agencyComision;
    private TextView agencyCredit;
    private Agency agency;
    private String agencyId = "";
    final private String agencyProfileId = "0";
    final private String agencyActive = "1";
    final private String agencyCountryId = "0";
    private String promotorId = "";
    private String error = "";
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_detail);
        Intent intent = getIntent();
        setTitle(gson.fromJson(intent.getStringExtra("agency"), Agency.class).getAgenciaNombre());
        agencyId = intent.getStringExtra("id");
        agencyName = (TextView) findViewById(R.id.nameTag);
        agencyAddress = (TextView) findViewById(R.id.addressTag);
        agencyLegalId = (TextView) findViewById(R.id.legalIdTag);
        agencyPhone = (TextView) findViewById(R.id.phoneTag);
        agencyEmail = (TextView) findViewById(R.id.emailTag);
        agencyComision = (TextView) findViewById(R.id.comisionTag);
        agencyCredit = (TextView) findViewById(R.id.creditTag);
        getSupportActionBar().setHomeButtonEnabled(true);
        agency = gson.fromJson(intent.getStringExtra("agency"), Agency.class);
        setValues(agency);
    }

    private void setValues(Agency agency){
        agencyName.setText(agencyName.getText().toString() + " " + agency.getAgenciaNombre());
        agencyAddress.setText(agencyAddress.getText().toString() + " " + agency.getAgenciaDireccion());
        agencyLegalId.setText(agencyLegalId.getText().toString() + " " + agency.getAgenciaRUC());
        agencyPhone.setText(agencyPhone.getText().toString() + " " + agency.getAgenciaTelefono());
        agencyEmail.setText(agencyEmail.getText().toString() + " " + agency.getAgenciaEmail());
        agencyComision.setText(agencyComision.getText().toString() + " " + agency.getAgenciaComision());
        agencyCredit.setText(agencyCredit.getText().toString() + " " + agency.getAgenciaCredito());
    }
}
