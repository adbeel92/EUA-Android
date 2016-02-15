package com.eua.SalesTrackingApp;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by unobtainium on 11/02/16.
 */
public interface WSInterface {
    @GET("Usuario_ValidarAcceso")
    Call<UserResponse> getUserResponse(@Query("strUsuario") String username, @Query("strClave") String clave);
}
