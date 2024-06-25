package com.example.currencyconverter.data

import com.example.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/fc8ab3c17c854f10c745ac16/latest/USD")
    suspend fun getRates(
        @Query("base_code") base:String
    ): Response<CurrencyResponse>
}