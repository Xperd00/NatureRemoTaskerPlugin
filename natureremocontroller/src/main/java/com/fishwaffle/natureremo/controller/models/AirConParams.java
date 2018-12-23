/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class AirConParams implements Serializable {
    /**
     * 温度
     * 指定可能な範囲はAirConRangeMode参照
     */
    public String temp;
    /**
     * モード
     * 冷房    :cool
     * 暖房    :warm
     * ドライ  :dry
     * 送風?   :blow
     * オート  :auto
     * 指定可能なモードはAirConRangeMode参照
     */
    public String mode;
    /**
     * 風量
     * 指定可能な範囲はAirConRangeMode参照
     */
    public String vol;
    /**
     * 風向き
     * 指定可能な範囲はAirConRangeMode参照
     */
    public String dir;
    /**
     * 電源
     * ON :空文字
     * OFF:power-off
     */
    public String button;
    /** 更新日時 */
    public String updated_at;

    @NonNull
    @Override
    public String toString() {
        return "AirConParams{" + "temp='" + temp + '\'' + ", mode='" + mode + '\'' + ", vol='" + vol + '\'' + ", dir='" + dir + '\'' + ", button='" + button + '\'' + ", updated_at='" + updated_at + '\'' + '}';
    }
}