package com.target.targetcasestudy.repository

import com.target.targetcasestudy.network.ApiHelper
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiHelper: ApiHelper) {

    //Get all products
    fun getDeals() = apiHelper.getDeals()

}