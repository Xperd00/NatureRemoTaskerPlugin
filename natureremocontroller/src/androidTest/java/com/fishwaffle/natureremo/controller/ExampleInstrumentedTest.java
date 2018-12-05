/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fishwaffle.natureremo.controller.models.Appliance;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void test() {}
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
//        Log.d("★", "addition_isCorrect: " + new Request().getGET());

        assertEquals("com.fishwaffle.natureremo.controller.test", appContext.getPackageName());
    }
    @Test
    public void Appliances_Post() {
        final Appliance data = NatureRemo.Appliances_Post("Test", null, "3faaa7c5-7869-4235-87df-a6432a22f595", ApplianceImage.ico_ac_0);
        System.out.println(data);
        assertNotNull(data);
    }
}
