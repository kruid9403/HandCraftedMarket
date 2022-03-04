package com.handcraftedmarket.handcraftedmarket.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.managers.FirebaseManager
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.squareup.okhttp.MediaType
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.text.NumberFormat

class CartProductAdapter(val context: Context, val productList: ArrayList<Product>):
    RecyclerView.Adapter<CartProductAdapter.ViewHolder>() {

    val firebaseManager = FirebaseManager()
    var list = productList

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(prod: Product, position: Int){

            val img = itemView.findViewById<CircleImageView>(R.id.item_cart_product_img)
            Glide.with(context).load(prod.imgUrl[0]).into(img)

            val prodName = itemView.findViewById<TextView>(R.id.item_cart_product_name)
            prodName.text = prod.name

            val qty = itemView.findViewById<EditText>(R.id.item_cart_product_quantity)
            qty.setText(prod.purchaseQty.toString())

            val buyOneBtn = itemView.findViewById<Button>(R.id.item_cart_product_buy_single)
            buyOneBtn.setOnClickListener {
                //TODO: BUY SINGLE ITEM
            }

            val buyCreatorBtn = itemView.findViewById<Button>(R.id.item_cart_product_buy_creator)

            val totals = itemView.findViewById<LinearLayout>(R.id.item_cart_product_total_layout)
            if (position == productList.size -1){
                totals.visibility = View.VISIBLE
                buyCreatorBtn.visibility = View.VISIBLE

                val subTotal = itemView.findViewById<TextView>(R.id.item_cart_subtotal_number)
                val tax = itemView.findViewById<TextView>(R.id.item_cart_tax_number)
                val shipping = itemView.findViewById<TextView>(R.id.item_cart_shipping_number)
                val total = itemView.findViewById<TextView>(R.id.item_cart_total_number)

                var subTotalDouble = 0.0
                productList.forEach {
                    subTotalDouble += it.price
                }

                var taxRate = 0.07
                var taxTotal = subTotalDouble * taxRate
                //TODO: Get Tax Rates


                var shippingRate = 0.0
                //TODO: Get Shipping Rates

                getShipping()
                val format = NumberFormat.getCurrencyInstance()
                format.maximumFractionDigits = 2
                subTotal.text = format.format(subTotalDouble)
                tax.text = format.format(taxTotal)
                shipping.text = format.format(shippingRate)
                total.text = format.format(subTotalDouble + taxTotal + shippingRate)


                buyCreatorBtn.setOnClickListener {
                    //TODO: BUY CREATORS ITEMS
                }
            }
        }
    }

    fun updateData(newList: ArrayList<Product>){
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_cart_product, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        holder.bind(product, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getShipping(){
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .build()
        val mediaType = "application/json".toMediaTypeOrNull()
        val body: RequestBody = ("{\n  \"" +
                "rate_options\": {\n    \"" +
                "carrier_ids\": [\n      \"" +
                "se-1584387\"\n    ]\n  " +
                "},\n  \"" +
                "shipment\": {\n    \"" +
                "validate_address\": \"no_validation\",\n    \"" +
                "ship_to\": {\n      \"" +
                "name\": \"Amanda Miller\",\n      \"" +
                "phone\": \"555-555-5555\",\n      \"" +
                "address_line1\": \"525 S Winchester Blvd\",\n      \"" +
                "city_locality\": \"San Jose\",\n      \"" +
                "state_province\": \"CA\",\n      \"" +
                "postal_code\": \"95128\",\n      \"" +
                "country_code\": \"US\",\n      \"" +
                "address_residential_indicator\": \"yes\"\n    },\n    \"" +
                "ship_from\": {\n      \"" +
                "company_name\": \"Example Corp.\",\n      \"" +
                "name\": \"John Doe\",\n      \"" +
                "phone\": \"111-111-1111\",\n      \"" +
                "address_line1\": \"4009 Marathon Blvd\",\n      \"" +
                "address_line2\": \"Suite 300\",\n      \"" +
                "city_locality\": \"Austin\",\n      \"" +
                "state_province\": \"TX\",\n      \"" +
                "postal_code\": \"78756\",\n      \"" +
                "country_code\": \"US\",\n      \"" +
                "address_residential_indicator\": \"no\"\n    },\n    \"" +
                "packages\": [\n      {\n        \"" +
                "weight\": {\n          \"" +
                "value\": 1.0,\n          \"" +
                "unit\": \"ounce\"\n        }\n      }\n    ]\n  }\n}"
                ).toRequestBody(mediaType)
        val request: Request = Request.Builder()
            .url("https://api.shipengine.com/v1/rates")
            .method("POST", body)
            .addHeader("Host", "api.shipengine.com")
            .addHeader("API-Key","TEST_evPIs0R2LkAq0Z9/rvv4HItM7o6NVI+U6lVdnrb6sRY")
            .addHeader("Content-Type", "application/json")
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            val response: Response = client.newCall(request).execute()
            Log.e("CartProductAdapter", response.toString())
        }

    }
}