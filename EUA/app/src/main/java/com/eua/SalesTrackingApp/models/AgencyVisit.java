package com.eua.SalesTrackingApp.models;

/**
 * Created by rubymobile on 16/02/16.
 */
public class AgencyVisit {
    private String visitasAgenciaId;

    private String visitasAgenciaNombre;

    private String visitasId;

    public void setVisitasAgenciaId(String id){
        this.visitasAgenciaId = id;
    }

    public void setVisitasAgenciaNombre(String nombre){
        this.visitasAgenciaNombre = nombre;
    }

    public void setVisitasId(String id){
        this.visitasId = id;
    }

    public String getVisitasAgenciaId(){
        return this.visitasAgenciaId;
    }

    public String getVisitasAgenciaNombre(){
        return this.visitasAgenciaNombre;
    }

    public String getVisitasId(){
        return this.visitasId;
    }
    private static AgencyVisit ourInstance = new AgencyVisit();

    public static AgencyVisit getInstance() {
        return ourInstance;
    }

    private AgencyVisit() {
    }
}
