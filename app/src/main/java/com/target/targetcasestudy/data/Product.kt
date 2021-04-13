package com.target.targetcasestudy.data

import com.google.gson.annotations.SerializedName

data class Product(
  var id: Int,
  var title: String,
  var aisle: String,
  var description: String,
  @SerializedName("image_url")
  var imageUrl: String,
  @SerializedName("regular_price")
  var regularPrice: RegularPrice
)

data class RegularPrice(
  @SerializedName("amount_in_cents")
  var amountInCents: Int,
  @SerializedName("currency_symbol")
  var currencySymbol: String,
  @SerializedName("display_string")
  var displayString: String
)

data class DealsResponse(@SerializedName("products") val products: ArrayList<Product>)

