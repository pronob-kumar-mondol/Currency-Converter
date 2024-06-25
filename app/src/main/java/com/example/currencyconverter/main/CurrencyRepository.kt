package com.example.currencyconverter.main

import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.util.Response

interface CurrencyRepository {

    suspend fun getRates(base:String): Response<CurrencyResponse>
}