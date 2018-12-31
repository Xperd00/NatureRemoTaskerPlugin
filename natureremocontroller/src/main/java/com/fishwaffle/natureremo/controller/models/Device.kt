/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models


import com.fishwaffle.natureremo.controller.NatureRemo
import java.io.Serializable
import java.util.*

class Device : DeviceCore(), Serializable {

    /** ユーザー情報  */
    var users: Array<Users>? = null
    /** 最新イベント  */
    var newest_events: NewestEvents? = null


    fun updateName(token: String, name: String): Device? {
        return NatureRemo.devicesDevicePost(token, id!!, name)
    }

    fun updateHumidityOffset(token: String, offset: Int): Device? {
        return NatureRemo.devicesDeviceHumidityOffsetPost(token, id!!, offset)
    }

    fun updateTemperatureOffset(token: String, offset: Int): Device? {
        return NatureRemo.devicesDeviceTemperatureOffsetPost(token, id!!, offset)
    }


    override fun toString(): String {
        return "Devices{" + "name='" + name + '\''.toString() + ", id='" + id + '\''.toString() + ", created_at='" + created_at + '\''.toString() + ", updated_at='" + updated_at + '\''.toString() + ", firmware_version='" + firmware_version + '\''.toString() + ", temperature_offset=" + temperature_offset + ", humidity_offset=" + humidity_offset + ", users=" + Arrays.toString(users) + ", newest_events=" + newest_events + '}'.toString()
    }

    /** ユーザー情報  */
    class Users : Serializable {

        /** ユーザーID  */
        var id: String? = null
        /** ユーザー名  */
        var nickname: String? = null
        /** スーパーユーザー  */
        var superuser: Boolean = false

        override fun toString(): String {
            return "Users{" + "id='" + id + '\''.toString() + ", nickname='" + nickname + '\''.toString() + ", superuser=" + superuser + '}'.toString()
        }

    }


    /** 最新イベント  */
    class NewestEvents : Serializable {
        /** 湿度  */
        var hu: SensorValue? = null
        /** 照度  */
        var il: SensorValue? = null
        /** 温度  */
        var te: SensorValue? = null


        override fun toString(): String {
            return "NewestEvents{" + ", hu=" + hu + ", il=" + il + ", te=" + te + '}'.toString()
        }
    }
}







