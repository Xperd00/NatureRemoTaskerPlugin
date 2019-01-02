/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.controller.isIgnoreUnknown
import java.io.Serializable

/**
 * デバイス情報
 * @param name デバイス名
 * @param id デバイスID
 * @param created_at 作成日時
 * @param updated_at 更新日時
 * @param mac_address Macアドレス
 * @param serial_number シリアルナンバー
 * @param firmware_version ファームウェアバージョン
 * @param temperature_offset 温度校正値
 * @param humidity_offset 湿度校正値
 * @param users ユーザー情報
 * @param newest_events 最新イベント
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class Device(var name: String?, var id: String?,
                  var created_at: String?, var updated_at: String?,
                  var mac_address: String?, var serial_number: String?, var firmware_version: String?,
                  var temperature_offset: Int = 0, var humidity_offset: Int = 0,
                  var users: List<User>?, var newest_events: NewestEvents?) : Serializable {


    fun updateName(token: String, name: String): Device? {
        return NatureRemo.devicesDevicePost(token, id!!, name)
    }

    fun updateHumidityOffset(token: String, offset: Int): Device? {
        return NatureRemo.devicesDeviceHumidityOffsetPost(token, id!!, offset)
    }

    fun updateTemperatureOffset(token: String, offset: Int): Device? {
        return NatureRemo.devicesDeviceTemperatureOffsetPost(token, id!!, offset)
    }

    /** 最新イベント
     * @param hu 湿度
     * @param il 照度
     * @param te 温度
     */
    @JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
    data class NewestEvents(var hu: SensorValue?, var il: SensorValue?, var te: SensorValue?) : Serializable
}







