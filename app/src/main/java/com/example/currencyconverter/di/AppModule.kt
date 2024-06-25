package com.example.currencyconverter.di

import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.main.CurrencyRepository
import com.example.currencyconverter.main.DefaultCurrencyRepository
import com.example.currencyconverter.util.DispacherProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://v6.exchangerate-api.com/v6/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideCurrencyRepository(api: CurrencyApi): CurrencyRepository =
        DefaultCurrencyRepository(api)

    @Singleton
    @Provides
    fun provideDispatcher(): DispacherProvider = object : DispacherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}