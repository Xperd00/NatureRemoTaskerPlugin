/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller;


import com.fishwaffle.natureremo.controller.models.Appliance;
import com.fishwaffle.natureremo.controller.models.Signal;
import com.fishwaffle.natureremo.controller.models.User;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;


public class PostRequestUnitTest {
    /**
     * 下記でアクセストークンを取得して設定する
     * https://home.nature.global/
     */
    static final String TOKEN = "";


    @Test
    public void Users_Me_Post() {
        final User data = NatureRemo.Users_Me_POST(TOKEN,"FishWaffle");
        System.out.println(data);
        assertNotNull(data);
    }

    @Test
    public void Appliances_Post() {
        final Appliance data = NatureRemo.Appliances_Post(TOKEN,"Test", null, "3faaa7c5-7869-4235-87df-a6432a22f595", ApplianceImage.ico_curtain);
        System.out.println(data);
        data.Delete(TOKEN);
        assertNotNull(data);
    }

    @Test
    public void Appliances_Appliance_Post() {
        final Appliance[] data = NatureRemo.Appliances_Get(TOKEN);
        System.out.println(Arrays.toString(data));
        final Appliance aaa = data[0].Update(TOKEN,ApplianceImage.ico_av, "aaa");
        System.out.println(aaa);
        assertNotNull(aaa);
    }

    @Test
    public void Signals_Signal_Send_Post() {
        NatureRemo.Signals_Signal_Send_Post(TOKEN,"5910c606-8db0-4a62-86ea-5e1b24783a14");

    }

    @Test
    public void Signals_Signal_Delete_Post() {
        NatureRemo.Signals_Signal_Delete_Post(TOKEN,"5910c606-8db0-4a62-86ea-5e1b24783a14");

    }

    @Test
    public void Appliances_Appliance_Signals_POST() {
        final Signal data = NatureRemo.Appliances_Appliance_Signals_POST(TOKEN,"ccf7276f-31b7-4aad-b3b6-69ec127e4628", "{\"freq\":38,\"data\":[2523,2717,786,903,777,924,763,1964,768,1958,769,1955,772,1953,773,914,770,902,786,54147,2523,2721,782,920,761,924,773,1936,779,1948,784,1957,768,1942,784,921,758,924,772],\"format\":\"us\"}", SignalImage.ico_ac0, "てすと");
        System.out.println(data);
        assertNotNull(data);
    }

}