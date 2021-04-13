package com.target.targetcasestudy.network


import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override fun getDeals() = apiService.getDeals()

}