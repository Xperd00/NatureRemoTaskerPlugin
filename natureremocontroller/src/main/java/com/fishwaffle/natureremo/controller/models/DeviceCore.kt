/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable

open class DeviceCore : Serializable {
    /**デバイス名 */
    var name: String? = null
    /**デバイスID */
    var id: String? = null
    /** 作成日時  */
    var created_at: String? = null
    /** 更新日時  */
    var updated_at: String? = null
    /** Macアドレス  */
    var mac_address: String? = null
    /** シリアルナンバー  */
    var serial_number: String? = null
    /** ファームウェアバージョン  */
    var firmware_version: String? = null
    /** 温度校正値  */
    var temperature_offset: Int = 0
    /** 湿度校正値  */
    var humidity_offset: Int = 0

    override fun toString(): String {
        return "DeviceCore{" +
                "name='" + name + '\''.toString() +
                ", id='" + id + '\''.toString() +
                ", created_at='" + created_at + '\''.toString() +
                ", updated_at='" + updated_at + '\''.toString() +
                ", firmware_version='" + firmware_version + '\''.toString() +
                ", temperature_offset=" + temperature_offset +
                ", humidity_offset=" + humidity_offset +
                '}'.toString()
    }
}