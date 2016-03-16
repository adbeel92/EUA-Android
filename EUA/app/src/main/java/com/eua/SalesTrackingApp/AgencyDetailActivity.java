package com.eua.SalesTrackingApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eua.SalesTrackingApp.models.Agency;
import com.eua.SalesTrackingApp.models.AgencyVisit;
import com.google.gson.Gson;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AgencyDetailActivity extends AppManager {
    private AgencyDetailActivity agencyDetailActivity;
    private Integer position;
    private TextView agencyName;
    private TextView agencyAddress;
    private TextView agencyLegalId;
    private TextView agencyPhone;
    private TextView agencyEmail;
    private TextView agencyComision;
    private TextView agencyCredit;
    private TextView programmedVisit;
    private TextView programmedVisitDate;
    private Agency agency;
    private String agencyId = "";
    final private String agencyProfileId = "0";
    final private String agencyActive = "1";
    final private String agencyCountryId = "0";
    private String programmedDate;
    private Date date;
    private String promotorId = "";
    private String error = "";
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agencyDetailActivity = this;
        setContentView(R.layout.activity_agency_detail);
        Intent intent = getIntent();
        setTitle(gson.fromJson(intent.getStringExtra("agency"), Agency.class).getAgenciaNombre());
        this.position = Integer.valueOf(intent.getIntExtra("position", -1));
        programmedDate = gson.fromJson(intent.getStringExtra("agency"), Agency.class).getAgenciaFechaProgramada();
        agencyId = intent.getStringExtra("id");
        agencyName = (TextView) findViewById(R.id.nameTag);
        agencyAddress = (TextView) findViewById(R.id.addressTag);
        agencyLegalId = (TextView) findViewById(R.id.legalIdTag);
        agencyPhone = (TextView) findViewById(R.id.phoneTag);
        agencyEmail = (TextView) findViewById(R.id.emailTag);
        agencyComision = (TextView) findViewById(R.id.comisionTag);
        agencyCredit = (TextView) findViewById(R.id.creditTag);
        programmedVisit = (TextView) findViewById(R.id.programmedVisitTag);
        programmedVisitDate = (TextView) findViewById(R.id.programmedVisitDateTag);
        getSupportActionBar().setHomeButtonEnabled(true);
        agency = gson.fromJson(intent.getStringExtra("agency"), Agency.class);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
        setValues(agency);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Integer position = data.getIntExtra("position", -1);
                Log.d("position -> ", position.toString());
                agency.setAgenciavisitasIDVisitado("1");
            }
        }
    }

    private void setValues(final Agency agency){
        agencyName.setText(agencyName.getText().toString() + " " + agency.getAgenciaNombre());
        agencyAddress.setText(agencyAddress.getText().toString() + " " + agency.getAgenciaDireccion());
        agencyLegalId.setText(agencyLegalId.getText().toString() + " " + agency.getAgenciaRUC());
        agencyPhone.setText(agencyPhone.getText().toString() + " " + agency.getAgenciaTelefono());
        agencyEmail.setText(agencyEmail.getText().toString() + " " + agency.getAgenciaEmail());
        agencyComision.setText(agencyComision.getText().toString() + " " + agency.getAgenciaComision());
        agencyCredit.setText(agencyCredit.getText().toString() + " " + agency.getAgenciaCredito());
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyMMddHHmmssZ");
        try {
            date = sdf2.parse(programmedDate);
            programmedVisitDate.setText(sdf2.format(date));
            Date today = new Date();
//            today.setTime(0);
            if (date.getDay() == today.getDay() && date.getMonth() == today.getMonth() && date.getYear() == today.getYear()){
                programmedVisitDate.setTextColor(Color.BLUE);
                programmedVisitDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (agency.getAgenciavisitasIDVisitado().equals("0")) {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent(v.getContext(), AgencyReportActivity.class);
                            intent.putExtra("position", position);
                            intent.putExtra("title", agency.getAgenciaNombre());
                            intent.putExtra("id", agency.getAgenciaVisitasID());
                            agencyDetailActivity.startActivityForResult(intent, 1);
                        } else {
                            Toast.makeText(getApplicationContext(), "Esta agencia ha sido visitada anteriormente.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (ParseException e) {
            programmedVisitDate.setText("No tiene visita programada");
            e.printStackTrace();
        }
    }


}
