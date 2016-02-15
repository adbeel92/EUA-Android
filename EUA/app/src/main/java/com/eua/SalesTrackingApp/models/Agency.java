package com.eua.SalesTrackingApp.models;

import com.eua.SalesTrackingApp.AgenciesActivity;

import java.util.ArrayList;

/**
 * Created by rubymobile on 2/2/16.
 */
public class Agency {
    private String name = "";
    private String email = "";
    private String phone = "";

    public Agency(String name, String email){
        this.name = name;
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static Agency[] generatedAgencies(){
        Agency[] agencies = new Agency[15];
        agencies[0]=(new Agency("Agency 1", "agency1@example.com"));
        agencies[1]=(new Agency("Agency 2", "agency2@example.com"));
        agencies[2]=(new Agency("Agency 3", "agency3@example.com"));
        agencies[3]=(new Agency("Agency 4", "agency4@example.com"));
        agencies[4]=(new Agency("Agency 5", "agency5@example.com"));
        agencies[5]=(new Agency("Agency 6", "agency6@example.com"));
        agencies[6]=(new Agency("Agency 7", "agency7@example.com"));
        agencies[7]=(new Agency("Agency 8", "agency8@example.com"));
        agencies[8]=(new Agency("Agency 9", "agency9@example.com"));
        agencies[9]=(new Agency("Agency 10", "agency10@example.com"));
        agencies[10]=(new Agency("Agency 11", "agency11@example.com"));
        agencies[11]=(new Agency("Agency 12", "agency12@example.com"));
        agencies[12]=(new Agency("Agency 13", "agency13@example.com"));
        agencies[13]=(new Agency("Agency 14", "agency14@example.com"));
        agencies[14]=(new Agency("Agency 15", "agency15@example.com"));
        return agencies;
    }

}
