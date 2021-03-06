package com.eua.SalesTrackingApp.models;

import com.eua.SalesTrackingApp.AgenciesActivity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rubymobile on 2/2/16.
 */
public class Agency {
    private String AgenciaID = "";
    private String AgenciaNombre = "";
    private String AgenciaDireccion = "";
    private String AgenciaRUC = "";
    private String AgenciaTelefono = "";
    private String AgenciaEmail = "";
    private String AgenciaComision = "";
    private String AgenciaCredito = "";
    private String agenciaFechaProgramada = "";

    public String getAgenciaVisitasID() {
        return agenciaVisitasID;
    }

    public void setAgenciaVisitasID(String agenciaVisitasID) {
        this.agenciaVisitasID = agenciaVisitasID;
    }

    private String agenciaVisitasID = "";

    public String  getAgenciaFechaProgramada() {
        String dateReceived = agenciaFechaProgramada;
        if (dateReceived.equals("0")){
            return dateReceived;
        }else {
//            String dateFormatted = dateReceived.substring(6,17) + "-" + dateReceived.substring(20,23);
            return  dateReceived;
        }
    }

    public void setAgenciaFechaProgramada(String agenciaFechaProgramada) {
        this.agenciaFechaProgramada = agenciaFechaProgramada;
    }

    public String getAgenciaID() {
        return AgenciaID;
    }

    public void setAgenciaID(String agenciaID) {
        AgenciaID = agenciaID;
    }

    public String getAgenciaNombre() {
        return AgenciaNombre;
    }

    public void setAgenciaNombre(String agenciaNombre) {
        AgenciaNombre = agenciaNombre;
    }

    public String getAgenciaDireccion() {
        return AgenciaDireccion;
    }

    public void setAgenciaDireccion(String agenciaDireccion) {
        AgenciaDireccion = agenciaDireccion;
    }

    public String getAgenciaRUC() {
        return AgenciaRUC;
    }

    public void setAgenciaRUC(String agenciaRUC) {
        AgenciaRUC = agenciaRUC;
    }

    public String getAgenciaTelefono() {
        return AgenciaTelefono;
    }

    public void setAgenciaTelefono(String agenciaTelefono) {
        AgenciaTelefono = agenciaTelefono;
    }

    public String getAgenciaEmail() {
        return AgenciaEmail;
    }

    public void setAgenciaEmail(String agenciaEmail) {
        AgenciaEmail = agenciaEmail;
    }

    public String getAgenciaComision() {
        return AgenciaComision;
    }

    public void setAgenciaComision(String agenciaComision) {
        AgenciaComision = agenciaComision;
    }

    public String getAgenciaCredito() {
        return AgenciaCredito;
    }

    public void setAgenciaCredito(String agenciaCredito) {
        AgenciaCredito = agenciaCredito;
    }


}
