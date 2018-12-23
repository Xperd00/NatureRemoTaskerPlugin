/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

/** センサー値 */
public class SensorValue  implements Serializable {
    /** センサー値 */
    public float val;
    /** 作成日時 */
    public String created_at;

    @NonNull
    @Override
    public String toString() {
        return "SensorValue{" +
                "val=" + val +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}