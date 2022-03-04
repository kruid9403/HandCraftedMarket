package com.handcraftedmarket.handcraftedmarket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.handcraftedmarket.handcraftedmarket.BuildConfig
import com.handcraftedmarket.handcraftedmarket.service.ApiService
import com.handcraftedmarket.handcraftedmarket.utils.coroutines.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.handcraftedmarket.handcraftedmarket.model.CartList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseFragment : Fragment() {

    lateinit var auth: FirebaseAuth
    private lateinit var uid: String
    private lateinit var currentUser: FirebaseUser
    private lateinit var shipEngineService: ApiService
    private lateinit var client: OkHttpClient

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            currentUser = auth.currentUser!!
            uid = auth.uid.toString()
        }

        client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS) // write timeout
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    fun getShipService(): Retrofit {
        if (BuildConfig.DEBUG) {
            return Retrofit.Builder()
                .baseUrl(Constants.SHIP_ENGINE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }else{
            return  Retrofit.Builder()
                .baseUrl(Constants.SHIP_ENGINE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}