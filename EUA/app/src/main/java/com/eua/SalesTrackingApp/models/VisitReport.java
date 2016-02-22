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
    private String brochureQty;
    private String comments;
    private String latitude;
    private String longitude;

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

    public VisitReport(){
    }

    public VisitReport(String visitId, String loggedUserId, String interviewerName, String stock, String brochureQty, String comments, String latitude, String longitude){
        this.visitId = visitId;
        this.loggedUserId = loggedUserId;
        this.interviewerName = interviewerName;
        this.stock = stock;
        this.brochureQty = brochureQty;
        this.comments = comments;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
