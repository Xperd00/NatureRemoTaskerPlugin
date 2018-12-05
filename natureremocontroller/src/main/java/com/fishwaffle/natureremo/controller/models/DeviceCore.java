/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

public class DeviceCore {
    /**デバイス名*/
    public String name;
    /**デバイスID*/
    public String id;
    /** 作成日時 */
    public String created_at;
    /** 更新日時 */
    public String updated_at;
    /** ファームウェアバージョン */
    public String firmware_version;
    /** 温度校正値 */
    public int temperature_offset;
    /** 湿度校正値 */
    public int humidity_offset;

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