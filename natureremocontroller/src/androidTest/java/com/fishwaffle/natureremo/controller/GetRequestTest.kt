/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller

import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class GetRequestTest {

    @Test
    fun users_Me_Get() {
        val data = NatureRemo.usersMeGet(TOKEN)
        println(data)
        assertNotNull(data)
    }

    @Test
    fun devices_Get() {
        val data = NatureRemo.devicesGet(TOKEN)
        println(Arrays.toString(data))
        assertNotNull(data)
    }

    @Test
    fun appliances_Get() {
        val data = NatureRemo.appliancesGet(TOKEN)
        println(Arrays.toString(data))
        println(Arrays.toString(data!![0].getSignals(TOKEN)))
        assertNotNull(data)
    }

    @Test
    fun appliances_Signals_Get() {
        val data = NatureRemo.appliancesApplianceSignalsGet(TOKEN, "7735a677-3c91-4b42-80f9-9fad34db4b17")
        println(Arrays.toString(data))
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
