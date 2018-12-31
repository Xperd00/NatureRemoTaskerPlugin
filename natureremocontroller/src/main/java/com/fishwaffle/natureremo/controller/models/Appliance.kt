/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fishwaffle.natureremo.controller.ApplianceImage
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.controller.SignalImage
import com.fishwaffle.natureremo.controller.Util.requireNonNull
import java.io.Serializable
import java.util.*

class Appliance : Serializable {
    /** アプライアンスID  */
    var id: String? = null
    /** デバイス  */
    var device: DeviceCore? = null
    /** Model(プリセット情報)  */
    var model: ApplianceModel? = null
    /**
     * AC:エアコンのプリセット利用
     * TV:テレビのプリセット利用
     * IR:その他
     */
    var type: String? = null
    /** アプライアンス名  */
    var nickname: String? = null
    /** アイコン  */
    var image: String? = null
    /** エアコンの設定状態  */
    var settings: AirConParams? = null
    var aircon: AirCon? = null
    /** シグナルリスト  */
    var signals: Array<Signal>? = null
    /** テレビリモコンの情報  */
    var tv: Tv? = null


    /**
     * シグナル取得
     * @return シグナルリスト
     */
    fun getSignals(token: String): Array<Signal>? {
        return NatureRemo.appliancesApplianceSignalsGet(token, id!!)
    }

    /**
     * アイコン/アプライアンス名を更新する
     * @param image    アイコン
     * @param nickname アプライアンス名
     * @return 更新後のアプライアンス
     */
    fun update(token: String, image: ApplianceImage, nickname: String): Appliance? {
        return NatureRemo.appliancesAppliancePost(token, id!!, requireNonNull(image, ApplianceImage.valueOf(this.image!!)), requireNonNull(nickname, this.nickname!!))
    }

    /**
     * アプライアンスを削除する
     */
    fun delete(token: String) {
        NatureRemo.appliancesApplianceDeletePost(token, id!!)
    }

    fun addSignal(token: String, message: String, image: SignalImage, name: String): Signal? {
        return NatureRemo.appliancesApplianceSignalsPOST(token, id!!, message, image, name)
    }

    /**
     * Update air conditioner settings.
     * エアコンの設定を更新します。
     * @param temperature    温度(省略可)
     * 指定可能な範囲はAirConRangeMode参照
     * @param operation_mode モード(省略可)
     * 冷房    :cool
     * 暖房    :warm
     * ドライ  :dry
     * 送風?   :blow
     * オート  :auto
     * 指定可能なモードはAirConRangeMode参照
     * @param air_volume     風量(省略可)
     * 指定可能な範囲はAirConRangeMode参照
     * @param air_direction  風向き(省略可)
     * 指定可能な範囲はAirConRangeMode参照
     * @param button         電源(省略可)
     * ON :空文字
     * OFF:power-off
     * @return 更新した設定
     */
    fun airconSettings(token: String, temperature: String, operation_mode: String, air_volume: String, air_direction: String, button: String): AirConParams? {
        return NatureRemo.appliancesApplianceAirConSettingsPost(token, id!!, requireNonNull(temperature, settings!!.temp), requireNonNull(operation_mode, settings!!.mode), requireNonNull(air_volume, settings!!.vol), requireNonNull(air_direction, settings!!.dir), requireNonNull(button, settings!!.button))
    }

    override fun toString(): String {
        return "Appliance{" + "id='" + id + '\''.toString() + ", device=" + device + ", model=" + model + ", type='" + type + '\''.toString() + ", nickname='" + nickname + '\''.toString() + ", image='" + image + '\''.toString() + ", settings=" + settings + ", aircon=" + aircon + ", signals=" + Arrays.toString(signals) + ", tv=" + tv + '}'.toString()
    }
}






