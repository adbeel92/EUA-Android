package com.eua.SalesTrackingApp;

import com.eua.SalesTrackingApp.models.Agency;
import com.eua.SalesTrackingApp.models.VisitReport;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by unobtainium on 11/02/16.
 */
public interface WSInterface {
    @GET("Usuario_ValidarAcceso")
    Call<UserResponse> getUserResponse(@Query("strUsuario") String username, @Query("strClave") String clave);

    @GET("Visita_PromotorListado")
    Call<AgencyVisitResponse> getVisitResponse(@Query("dte_FechaInicio") String startDate, @Query("dte_FechaFin") String endDate, @Query("int_PromotorID") String promotorId);

    @GET("Visita_VisitaAppsAgencias")
    Call<AgencyResponse> getAgencyResponse(@Query("int_pAgenciaID") String agencyId,
                                           @Query("int_pAgenciaPerfilId") String agencyProfileId,
                                           @Query("int_pAgenciaPromotorId") String loggedUserId,
                                           @Query("int_pAgenciaActivo") String agencyActive,
                                           @Query("int_pAgenciaPaisId") String agencyCountryId);

    @GET("Visita_VisitaAppsGuardaActualiza")
    Call<ReportResponse> sendReport(@Query("int_visitasappsVisitasId") String visitaId,
                                 @Query("int_visitasappsLoginID") String loggedUserId,
                                 @Query("str_visitasappsDesAtencion") String interviewerName,
                                 @Query("int_visitasappsDejaStock") String stock,
                                 @Query("int_visitasappsDejaFolleteria") String brochureQty,
                                 @Query("str_visitasappsDesComentarios") String comments,
                                 @Query("dtt_visitasappsFechaHora") String dateAndHour,
                                 @Query("dbl_visitasappsXCoord") String latitude,
                                 @Query("dbl_visitasappsYCoord") String longitude);
}
