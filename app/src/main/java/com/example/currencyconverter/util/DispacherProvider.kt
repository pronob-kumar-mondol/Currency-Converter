package com.example.currencyconverter.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispacherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}