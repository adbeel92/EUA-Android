package com.eua.SalesTrackingApp.models;

import android.widget.EditText;

import com.orm.SugarRecord;

/**
 * Created by rubymobile on 18/02/16.
 */
public class VisitReport extends SugarRecord{
    private String visitId;
    private String loggedUserId;
    private String interviewerName;
    private String stock;

    public String getVisitId() {
        return visitId;
    }

    public String getLoggedUserId() {
        return loggedUserId;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public String getStock() {
        return stock;
    }

    public String getBrochureQty() {
        return brochureQty;
    }

    public String getComments() {
        return comments;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    private String brochureQty;
    private String comments;
    private String latitude = "";
    private String longitude = "";

    public VisitReport(){
    }

    public static VisitReport makeInstance(String visitId, String loggedUserId, String interviewerName, String stock, String brochureQty, String comments, String latitude, String longitude){
        VisitReport vr = new VisitReport();
        vr.visitId = visitId;
        vr.loggedUserId = loggedUserId;
        vr.interviewerName = stock;
        vr.brochureQty = brochureQty;
        vr.comments = comments;
        vr.latitude = latitude;
        vr.longitude = longitude;
        return vr;
    }

}
