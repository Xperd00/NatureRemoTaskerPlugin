/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class PostRequestTest {

    @Test
    fun users_Me_Post() {
        val data = NatureRemo.usersMePOST(TOKEN, "FishWaffle")
        println(data)
        assertNotNull(data)
    }

    @Test
    fun appliances_Post() {
        val data = NatureRemo.appliancesPost(TOKEN, "Test", null, "3faaa7c5-7869-4235-87df-a6432a22f595", ApplianceImage.CURTAIN)
        println(data)
        data!!.delete(TOKEN)
        assertNotNull(data)
    }

    @Test
    fun appliances_Appliance_Post() {
        val data = NatureRemo.appliancesGet(TOKEN)
        println(Arrays.toString(data))
        val aaa = data!![0].update(TOKEN, ApplianceImage.AV, "aaa")
        println(aaa)
        assertNotNull(aaa)
    }

    @Test
    fun signals_Signal_Send_Post() {
        NatureRemo.signalsSignalSendPost(TOKEN, "5910c606-8db0-4a62-86ea-5e1b24783a14")

    }

    @Test
    fun signals_Signal_Delete_Post() {
        NatureRemo.signalsSignalDeletePost(TOKEN, "5910c606-8db0-4a62-86ea-5e1b24783a14")

    }

    @Test
    fun appliances_Appliance_Signals_POST() {
        val data = NatureRemo.appliancesApplianceSignalsPOST(TOKEN, "ccf7276f-31b7-4aad-b3b6-69ec127e4628", "{\"freq\":38,\"data\":[2523,2717,786,903,777,924,763,1964,768,1958,769,1955,772,1953,773,914,770,902,786,54147,2523,2721,782,920,761,924,773,1936,779,1948,784,1957,768,1942,784,921,758,924,772],\"format\":\"us\"}", SignalImage.AC0, "てすと")
        println(data)
        assertNotNull(data)
    }

    companion object {
        /**
         * 下記でアクセストークンを取得して設定する
         * https://home.nature.global/
         */
        internal const val TOKEN = ""
    }

}
