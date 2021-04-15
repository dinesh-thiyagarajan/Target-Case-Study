package com.target.targetcasestudy.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.data.BaseResponse
import com.target.targetcasestudy.data.DealsResponse
import com.target.targetcasestudy.data.Product
import com.target.targetcasestudy.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel(),
    LifecycleObserver {

    var productsList: MutableLiveData<BaseResponse<ArrayList<Product>>> = MutableLiveData()

    init {
        getProducts()
    }


    fun getProducts() = viewModelScope.launch {
        productsList.postValue(BaseResponse.loading(null))
        productRepository.getDeals().enqueue(object : Callback<DealsResponse?> {
            override fun onResponse(
                call: Call<DealsResponse?>,
                response: Response<DealsResponse?>
            ) {
                productsList.postValue(BaseResponse.success(response.body()?.products))
            }

            override fun onFailure(call: Call<DealsResponse?>, t: Throwable) {
                productsList.postValue(BaseResponse.error(t?.localizedMessage, null))
            }
        })
    }

}