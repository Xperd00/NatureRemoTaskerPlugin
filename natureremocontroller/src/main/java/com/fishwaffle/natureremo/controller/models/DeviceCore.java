/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class DeviceCore implements Serializable {
    /**デバイス名*/
    public String name;
    /**デバイスID*/
    public String id;
    /** 作成日時 */
    public String created_at;
    /** 更新日時 */
    public String updated_at;
    /** Macアドレス */
    public String mac_address;
    /** シリアルナンバー */
    public String serial_number;
    /** ファームウェアバージョン */
    public String firmware_version;
    /** 温度校正値 */
    public int temperature_offset;
    /** 湿度校正値 */
    public int humidity_offset;

    @NonNull
    @Override
    public String toString() {
        return "DeviceCore{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", firmware_version='" + firmware_version + '\'' +
                ", temperature_offset=" + temperature_offset +
                ", humidity_offset=" + humidity_offset +
                '}';
    }
}