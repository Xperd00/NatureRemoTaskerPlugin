/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.ApplianceImage
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import com.fishwaffle.natureremo.controller.SignalImage
import java.io.Serializable

/**
 *家電情報
 * @param id アプライアンスID
 * @param device デバイス
 * @param model Model(プリセット情報)
 * @param type AC:エアコンのプリセット利用
 *              TV:テレビのプリセット利用
 *              IR:その他
 * @param nickname アプライアンス名
 * @param image アイコン
 * @param settings エアコンの設定状態
 * @param aircon エアコン情報
 * @param signals シグナルリスト
 * @param tv テレビリモコンの情報
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class Appliance(var id: String?, var device: Device?, var model: ApplianceModel?, var type: String?, var nickname: String? = null,
                     var image: String?, var settings: AirConParams?, var aircon: AirCon?, var signals: List<Signal>?, var tv: Tv?, var light: Light?) : Serializable {


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
        return NatureRemo.appliancesAppliancePost(token, id!!, image, nickname)
    }

    /**
     * アプライアンスを削除する
     */
    fun delete(token: String) {
        NatureRemo.appliancesApplianceDeletePost(token, id!!)
    }

    fun addSignal(token: String, message: String, image: SignalImage, name: String): Signal? {
        return NatureRemo.appliancesApplianceSignalsPost(token, id!!, message, image, name)
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
        return NatureRemo.appliancesApplianceAirConSettingsPost(token, id!!, temperature, operation_mode, air_volume, air_direction, button)
    }

}






