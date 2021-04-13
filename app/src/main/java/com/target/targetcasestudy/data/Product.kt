package com.target.targetcasestudy.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var id: Int,
    var title: String,
    var aisle: String,
    var description: String,
    @SerializedName("image_url")
    var imageUrl: String,
    @SerializedName("regular_price")
    var regularPrice: RegularPrice
) : Parcelable

@Parcelize
data class RegularPrice(
    @SerializedName("amount_in_cents")
    var amountInCents: Int,
    @SerializedName("currency_symbol")
    var currencySymbol: String,
    @SerializedName("display_string")
    var displayString: String
) : Parcelable

data class DealsResponse(@SerializedName("products") val products: ArrayList<Product>)

