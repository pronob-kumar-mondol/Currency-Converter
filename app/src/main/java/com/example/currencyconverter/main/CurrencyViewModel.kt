package com.example.currencyconverter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.models.ConversionRates
import com.example.currencyconverter.util.DispacherProvider
import com.example.currencyconverter.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round
import kotlin.time.times


class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    private val dispatchers: DispacherProvider
):ViewModel() {

    sealed class CurrencyEvent{

        class Success(val result: String):CurrencyEvent()
        class Failure(val error:String):CurrencyEvent()
        object Loading:CurrencyEvent()
        object Empty:CurrencyEvent()
    }

    private val _conversion= MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion

    fun convert(
        ammountStr:String,
        fromCurrency:String,
        toCurrency:String
    ){
        val fromAmmount= ammountStr.toDoubleOrNull()
        if (fromAmmount==null){
            _conversion.value=CurrencyEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch(dispatchers.io) {
            _conversion.value=CurrencyEvent.Loading

            when(val ratesResponse=repository.getRates(fromCurrency)){

                is Response.eror->{
                    _conversion.value=CurrencyEvent.Failure(ratesResponse.massage!!)
                }
                is Response.success->{
                    val rates= ratesResponse.data!!.conversionRates
                    val rate=getRateForCurrency(toCurrency, rates) as Double

                    if (rate==null){
                        _conversion.value=CurrencyEvent.Failure("Unexpected error")
                    }else{
                        val convertedCurrency= round(fromAmmount * rate * 100)/100
                        _conversion.value=CurrencyEvent.Success("$fromAmmount $fromCurrency = $convertedCurrency $toCurrency")
                    }
                }


            }
        }


    }

    private fun getRateForCurrency(currency: String, rates: ConversionRates) = when (currency) {
        "AED" -> rates.aED
        "AFN" -> rates.aFN
        "ALL" -> rates.aLL
        "AMD" -> rates.aMD
        "ANG" -> rates.aNG
        "AOA" -> rates.aOA
        "ARS" -> rates.aRS
        "AUD" -> rates.aUD
        "AWG" -> rates.aWG
        "AZN" -> rates.aZN
        "BAM" -> rates.bAM
        "BBD" -> rates.bBD
        "BDT" -> rates.bDT
        "BGN" -> rates.bGN
        "BHD" -> rates.bHD
        "BIF" -> rates.bIF
        "BMD" -> rates.bMD
        "BND" -> rates.bND
        "BOB" -> rates.bOB
        "BRL" -> rates.bRL
        "BSD" -> rates.bSD
        "BTN" -> rates.bTN
        "BWP" -> rates.bWP
        "BYN" -> rates.bYN
        "BZD" -> rates.bZD
        "CAD" -> rates.cAD
        "CDF" -> rates.cDF
        "CHF" -> rates.cHF
        "CLP" -> rates.cLP
        "CNY" -> rates.cNY
        "COP" -> rates.cOP
        "CRC" -> rates.cRC
        "CUP" -> rates.cUP
        "CVE" -> rates.cVE
        "CZK" -> rates.cZK
        "DJF" -> rates.dJF
        "DKK" -> rates.dKK
        "DOP" -> rates.dOP
        "DZD" -> rates.dZD
        "EGP" -> rates.eGP
        "ERN" -> rates.eRN
        "ETB" -> rates.eTB
        "EUR" -> rates.eUR
        "FJD" -> rates.fJD
        "FKP" -> rates.fKP
        "FOK" -> rates.fOK
        "GBP" -> rates.gBP
        "GEL" -> rates.gEL
        "GGP" -> rates.gGP
        "GHS" -> rates.gHS
        "GIP" -> rates.gIP
        "GMD" -> rates.gMD
        "GNF" -> rates.gNF
        "GTQ" -> rates.gTQ
        "GYD" -> rates.gYD
        "HKD" -> rates.hKD
        "HNL" -> rates.hNL
        "HRK" -> rates.hRK
        "HTG" -> rates.hTG
        "HUF" -> rates.hUF
        "IDR" -> rates.iDR
        "ILS" -> rates.iLS
        "IMP" -> rates.iMP
        "INR" -> rates.iNR
        "IQD" -> rates.iQD
        "IRR" -> rates.iRR
        "ISK" -> rates.iSK
        "JEP" -> rates.jEP
        "JMD" -> rates.jMD
        "JOD" -> rates.jOD
        "JPY" -> rates.jPY
        "KES" -> rates.kES
        "KGS" -> rates.kGS
        "KHR" -> rates.kHR
        "KID" -> rates.kID
        "KMF" -> rates.kMF
        "KRW" -> rates.kRW
        "KWD" -> rates.kWD
        "KYD" -> rates.kYD
        "KZT" -> rates.kZT
        "LAK" -> rates.lAK
        "LBP" -> rates.lBP
        "LKR" -> rates.lKR
        "LRD" -> rates.lRD
        "LSL" -> rates.lSL
        "LYD" -> rates.lYD
        "MAD" -> rates.mAD
        "MDL" -> rates.mDL
        "MGA" -> rates.mGA
        "MKD" -> rates.mKD
        "MMK" -> rates.mMK
        "MNT" -> rates.mNT
        "MOP" -> rates.mOP
        "MRU" -> rates.mRU
        "MUR" -> rates.mUR
        "MVR" -> rates.mVR
        "MWK" -> rates.mWK
        "MXN" -> rates.mXN
        "MYR" -> rates.mYR
        "MZN" -> rates.mZN
        "NAD" -> rates.nAD
        "NGN" -> rates.nGN
        "NIO" -> rates.nIO
        "NOK" -> rates.nOK
        "NPR" -> rates.nPR
        "NZD" -> rates.nZD
        "OMR" -> rates.oMR
        "PAB" -> rates.pAB
        "PEN" -> rates.pEN
        "PGK" -> rates.pGK
        "PHP" -> rates.pHP
        "PKR" -> rates.pKR
        "PLN" -> rates.pLN
        "PYG" -> rates.pYG
        "QAR" -> rates.qAR
        "RON" -> rates.rON
        "RSD" -> rates.rSD
        "RUB" -> rates.rUB
        "RWF" -> rates.rWF
        "SAR" -> rates.sAR
        "SBD" -> rates.sBD
        "SCR" -> rates.sCR
        "SDG" -> rates.sDG
        "SEK" -> rates.sEK
        "SGD" -> rates.sGD
        "SHP" -> rates.sHP
        "SLE" -> rates.sLE
        "SLL" -> rates.sLL
        "SOS" -> rates.sOS
        "SRD" -> rates.sRD
        "SSP" -> rates.sSP
        "STN" -> rates.sTN
        "SYP" -> rates.sYP
        "SZL" -> rates.sZL
        "THB" -> rates.tHB
        "TJS" -> rates.tJS
        "TMT" -> rates.tMT
        "TND" -> rates.tND
        "TOP" -> rates.tOP
        "TRY" -> rates.tRY
        "TTD" -> rates.tTD
        "TVD" -> rates.tVD
        "TWD" -> rates.tWD
        "TZS" -> rates.tZS
        "UAH" -> rates.uAH
        "UGX" -> rates.uGX
        "USD" -> rates.uSD
        "UYU" -> rates.uYU
        "UZS" -> rates.uZS
        "VES" -> rates.vES
        "VND" -> rates.vND
        "VUV" -> rates.vUV
        "WST" -> rates.wST
        "XAF" -> rates.xAF
        "XCD" -> rates.xCD
        "XDR" -> rates.xDR
        "XOF" -> rates.xOF
        "XPF" -> rates.xPF
        "YER" -> rates.yER
        "ZAR" -> rates.zAR
        "ZMW" -> rates.zMW
        "ZWL" -> rates.zWL
        else -> null
    }

}