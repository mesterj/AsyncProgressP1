package com.kite.joco.asyncprogressp1.rest;

/**
 * Created by Joco on 2016.01.01..
 */

import com.kite.joco.asyncprogressp1.db.Partner;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Mester József on 2015.12.22..
 */
public interface PartnerApi {

    @GET("/com.joco.nyomtserv2.partner")
    public void getAsyncListofPartner(Callback<List<Partner>> callback);

    @GET("/com.joco.nyomtserv2.partner")
    public List<Partner> getSyncListOfPartner();


}