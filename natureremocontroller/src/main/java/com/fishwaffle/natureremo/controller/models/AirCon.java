/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;

public class AirCon implements Serializable {
    /** 指定可能は値範囲の情報 */
    public Range range;
    /**
     * 温度の単位
     * c:摂氏
     * f:華氏
     */
    public String tempUnit;

    @NonNull
    @Override
    public String toString() {
        return "AirCon{" + "range=" + range + ", tempUnit='" + tempUnit + '\'' + '}';
    }

    public static class Range {
        /** 各モード */
        public Modes modes;
        public String[] fixedButtons;

        @NonNull
        @Override
        public String toString() {
            return "Range{" + "modes=" + modes + ", fixedButtons=" + Arrays.toString(fixedButtons) + '}';
        }
    }

    public static class Modes implements Serializable {
        /** 冷房 */
        public AirConRangeMode cool;
        /** 暖房 */
        public AirConRangeMode warm;
        /** ドライ */
        public AirConRangeMode dry;
        /** 送風? */
        public AirConRangeMode blow;
        /** オート */
        public AirConRangeMode auto;

        @NonNull
        @Override
        public String toString() {
            return "Modes{" + "cool=" + cool + ", warm=" + warm + ", dry=" + dry + ", blow=" + blow + ", auto=" + auto + '}';
        }
    }

}



