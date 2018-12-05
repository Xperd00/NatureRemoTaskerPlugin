/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import java.util.Arrays;

public class AirCon {
    /** 指定可能は値範囲の情報 */
    public Range range;
    /**
     * 温度の単位
     * c:摂氏
     * f:華氏
     */
    public String tempUnit;

    @Override
    public String toString() {
        return "AirCon{" + "range=" + range + ", tempUnit='" + tempUnit + '\'' + '}';
    }

    public static class Range {
        /** 各モード */
        public Modes modes;
        public String[] fixedButtons;

        @Override
        public String toString() {
            return "Range{" + "modes=" + modes + ", fixedButtons=" + Arrays.toString(fixedButtons) + '}';
        }
    }

    public static class Modes {
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

        @Override
        public String toString() {
            return "Modes{" + "cool=" + cool + ", warm=" + warm + ", dry=" + dry + ", blow=" + blow + ", auto=" + auto + '}';
        }
    }

}



