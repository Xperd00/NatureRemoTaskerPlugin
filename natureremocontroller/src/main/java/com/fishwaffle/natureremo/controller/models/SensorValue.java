/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

/** センサー値 */
public class SensorValue {
    /** センサー値 */
    public float val;
    /** 作成日時 */
    public String created_at;

    @Override
    public String toString() {
        return "SensorValue{" +
                "val=" + val +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}