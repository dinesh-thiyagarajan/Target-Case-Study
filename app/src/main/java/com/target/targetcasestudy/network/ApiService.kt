package com.target.targetcasestudy.network


import com.target.targetcasestudy.data.DealsResponse
import com.target.targetcasestudy.helpers.AppConstants
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(AppConstants.END_POINT_DEALS)
    fun getDeals(): Call<DealsResponse>
}