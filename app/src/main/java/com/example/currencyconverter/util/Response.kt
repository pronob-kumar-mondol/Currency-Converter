package com.example.currencyconverter.util

sealed class Response<T>(val data:T?,val massage:String?) {

    class success<T>(data: T?):Response<T>(data,null)
    class eror<T>(massage: String?):Response<T>(null,massage)

}