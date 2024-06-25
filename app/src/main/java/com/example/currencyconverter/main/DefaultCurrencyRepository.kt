package com.example.currencyconverter.main

import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.util.Response
import javax.inject.Inject

class DefaultCurrencyRepository @Inject constructor(
    private val api: CurrencyApi
) :CurrencyRepository {

    override suspend fun getRates(base: String): Response<CurrencyResponse> {
        return try {
            val response=api.getRates(base)
            val result=response.body()
            if(response.isSuccessful && result!=null){
                Response.success(result)
            }else{
                Response.eror(response.message())
            }
        }
        catch (e: Exception){
            Response.eror(e.message?:"An Eror Occured")
        }
    }

}