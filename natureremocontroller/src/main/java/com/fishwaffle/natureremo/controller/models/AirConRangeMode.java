/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import java.util.Arrays;

public class AirConRangeMode {
    /**温度*/
    public String[] temp;
    /**風量*/
    public String[] vol;
    /**風向き*/
    public String[] dir;

    @Override
    public String toString() {
        return "AirConRangeMode{" +
                "temp=" + Arrays.toString(temp) +
                ", vol=" + Arrays.toString(vol) +
                ", dir=" + Arrays.toString(dir) +
                '}';
    }
}
