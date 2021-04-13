package com.target.targetcasestudy.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.BaseResponse
import com.target.targetcasestudy.data.Product
import com.target.targetcasestudy.data.Status
import com.target.targetcasestudy.viewModels.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loading.*
import java.util.*

@AndroidEntryPoint
class DealsListFragment : Fragment(), ProductSelectedCallback {

    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var productAdapter: DealItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        productViewModel.productsList.observe(viewLifecycleOwner, {
            parseProductsAndUpdateUi(it)
        })
    }

    private fun initAdapter() {
        productAdapter = DealItemAdapter()
        val mLayoutManager = LinearLayoutManager(context)
        rv_products.layoutManager = mLayoutManager
        rv_products.itemAnimator = DefaultItemAnimator()
        rv_products.adapter = productAdapter
        productAdapter.setOnProductSelectedCallback(this)
    }

    private fun parseProductsAndUpdateUi(baseResponse: BaseResponse<ArrayList<Product>>?) {
        when (baseResponse?.status) {
            Status.LOADING -> showLoadingScreen()
            Status.ERROR -> showErrorScreen(baseResponse.message)
            Status.SUCCESS -> showProductsInUi(baseResponse.data)
        }
    }

    private fun showProductsInUi(data: ArrayList<Product>?) {
        layout_list_loading.visibility = View.GONE
        shimmer_container.visibility = View.GONE
        cl_error_layout.visibility = View.GONE
        shimmer_container.stopShimmer()
        rv_products.visibility = View.VISIBLE
        productAdapter.submitList(data)
    }

    private fun showErrorScreen(message: String?) {
        layout_list_loading.visibility = View.GONE
        cl_error_layout.visibility = View.VISIBLE
        tv_err_msg.text = message
        shimmer_container.visibility = View.GONE
        shimmer_container.stopShimmer()
        rv_products.visibility = View.GONE
    }

    private fun showLoadingScreen() {
        layout_list_loading.visibility = View.VISIBLE
        shimmer_container.visibility = View.VISIBLE
        shimmer_container.startShimmer()
        rv_products.visibility = View.GONE
        cl_error_layout.visibility = View.GONE
    }

    override fun onProductSelected(product: Product) {

    }
}
