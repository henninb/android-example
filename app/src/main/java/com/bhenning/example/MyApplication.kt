package com.bhenning.example

import android.app.Application
import android.util.Log
import com.perimeterx.mobile_sdk.PerimeterX
import com.perimeterx.mobile_sdk.PerimeterXDelegate
import com.perimeterx.mobile_sdk.main.PXPolicy
import com.perimeterx.mobile_sdk.main.PXPolicyUrlRequestInterceptionType
import com.perimeterx.mobile_sdk.main.PXStorageMethod

class MyApplication : Application(), PerimeterXDelegate {
    override fun onCreate() {
        super.onCreate()
        val policy = PXPolicy()
        val appId = "PXjJ0cYtn9"
        policy.setDomains(arrayListOf("www.bhenning.com"), appId)
        policy.storageMethod = PXStorageMethod.DATA_STORE
        policy.urlRequestInterceptionType = PXPolicyUrlRequestInterceptionType.INTERCEPT_AND_RETRY_REQUEST
        //policy.doctorCheckEnabled = true

        println("SDK version: ${PerimeterX.sdkVersion()}")
        Log.i("MyApplication", "SDK version: ${PerimeterX.sdkVersion()}")
        try {
            PerimeterX.start(this, appId, this, policy)
        }
        catch (exception: Exception) {
            Log.i("MyApplication", "failed to start. error: ${exception.message}")
        }

//        try {
//            PerimeterX.setCustomParameters(hashMapOf("custom_param1" to "hello", "custom_param2" to "world"))
//        }
//        catch (exception: Exception) {
//            println("error: ${exception.message}")
//        }
    }

    override fun perimeterxChallengeCancelledHandler(appId: String) {
        Log.i("MyApplication", "ChallengeCancelledHandler")
    }

    override fun perimeterxChallengeSolvedHandler(appId: String) {
        Log.i("MyApplication", "ChallengeSolvedHandler")
    }

    override fun perimeterxHeadersWereUpdated(headers: HashMap<String, String>, appId: String) {
        Log.i("MyApplication", "HeadersWereUpdated")
    }

    override fun perimeterxRequestBlockedHandler(url: String?, appId: String) {
        Log.i("MyApplication", "RequestBlockedHandler")
    }
}