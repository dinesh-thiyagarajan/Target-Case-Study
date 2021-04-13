package com.target.targetcasestudy.ui.deals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.Product

class DealItemAdapter : ListAdapter<Product, DealItemViewHolder>(ProductDiffUtilCallback()) {

    lateinit var productSelectedCallback: ProductSelectedCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return DealItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DealItemViewHolder, position: Int) {
        if (position != -1) {
            val product = getItem(position)
            product.let {
                bindView(holder, product)
            }
            holder.itemView.setOnClickListener {
                productSelectedCallback.onProductSelected(product)
            }
        }
    }

    private fun bindView(holder: DealItemViewHolder, product: Product) {
        holder.tvProductName.text = product.title
        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.ivProduct)
        holder.tvProductPrice.text = product.regularPrice.displayString
        holder.tvShipHint.text = holder.itemView.context.getString(R.string.hint_ship_or)
        holder.tvAisle.text = product.aisle
    }

    fun setOnProductSelectedCallback(productSelectedCallback: ProductSelectedCallback) {
        this.productSelectedCallback = productSelectedCallback
    }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
    val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
    val tvProductPrice: TextView = itemView.findViewById(R.id.tv_price)
    val tvShipHint: TextView = itemView.findViewById(R.id.tv_ship)
    val tvAisle: TextView = itemView.findViewById(R.id.tv_aisle)
}

class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }
}

interface ProductSelectedCallback {
    fun onProductSelected(product: Product)
}