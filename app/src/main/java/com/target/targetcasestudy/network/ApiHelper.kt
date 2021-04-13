package com.target.targetcasestudy.network

import com.target.targetcasestudy.data.DealsResponse
import retrofit2.Call

interface ApiHelper {
    fun getDeals(): Call<DealsResponse>
}