/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;


import android.support.annotation.NonNull;

import com.fishwaffle.natureremo.controller.NatureRemo;

import java.io.Serializable;
import java.util.Arrays;

public class Device extends DeviceCore implements Serializable {

    /** ユーザー情報 */
    public Users[] users;
    /** 最新イベント */
    public NewestEvents newest_events;


    public Device UpdateName(String token, String name) {
        return NatureRemo.Devices_Device_Post(token, id, name);
    }

    public Device UpdateHumidityOffset(String token, int offset) {
        return NatureRemo.Devices_Device_HumidityOffset_Post(token, id, offset);
    }

    public Device UpdateTemperatureOffset(String token, int offset) {
        return NatureRemo.Devices_Device_TemperatureOffset_Post(token, id, offset);
    }


    @NonNull
    @Override
    public String toString() {
        return "Devices{" + "name='" + name + '\'' + ", id='" + id + '\'' + ", created_at='" + created_at + '\'' + ", updated_at='" + updated_at + '\'' + ", firmware_version='" + firmware_version + '\'' + ", temperature_offset=" + temperature_offset + ", humidity_offset=" + humidity_offset + ", users=" + Arrays.toString(users) + ", newest_events=" + newest_events + '}';
    }

    /** ユーザー情報 */
    public static class Users implements Serializable {

        /** ユーザーID */
        public String id;
        /** ユーザー名 */
        public String nickname;
        /** スーパーユーザー */
        public boolean superuser;

        @NonNull
        @Override
        public String toString() {
            return "Users{" + "id='" + id + '\'' + ", nickname='" + nickname + '\'' + ", superuser=" + superuser + '}';
        }

    }


    /** 最新イベント */
    public static class NewestEvents implements Serializable {
        /** 湿度 */
        public SensorValue hu;
        /** 照度 */
        public SensorValue il;
        /** 温度 */
        public SensorValue te;


        @NonNull
        @Override
        public String toString() {
            return "NewestEvents{" + ", hu=" + hu + ", il=" + il + ", te=" + te + '}';
        }
    }
}







