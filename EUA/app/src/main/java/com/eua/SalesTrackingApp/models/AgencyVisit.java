package com.eua.SalesTrackingApp.models;

/**
 * Created by rubymobile on 16/02/16.
 */
public class AgencyVisit {
    private static AgencyVisit ourInstance = new AgencyVisit();

    public static AgencyVisit getInstance() {
        return ourInstance;
    }

    private AgencyVisit() {
    }
}
