package com.kite.joco.asyncprogressp1.rest;

/**
 * Created by Joco on 2016.01.01..
 */

import com.kite.joco.asyncprogressp1.db.Partner;
import com.kite.joco.asyncprogressp1.db.Sorszamok;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Mester József on 2015.12.22..
 */
public interface PartnerApi {

    @GET("/entities.partner")
    public void getAsyncListofPartner(Callback<List<Partner>> callback);

    @GET("/entities.partner")
    public List<Partner> getSyncListOfPartner();

    //http://localhost:8080/NyomtServ2-1.0/webresources/com.joco.nyomtserv2.sorszamok/count
    @POST("/entities.sorszamok")
    void sendujSorszam(@Body Sorszamok sorszamok, Callback<String> callback);


}