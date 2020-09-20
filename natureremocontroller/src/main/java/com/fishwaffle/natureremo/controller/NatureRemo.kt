/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller


import android.text.TextUtils.join
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fishwaffle.natureremo.controller.exception.AuthenticationException
import com.fishwaffle.natureremo.controller.exception.OtherException
import com.fishwaffle.natureremo.controller.exception.RequestLimitException
import com.fishwaffle.natureremo.controller.models.*
import java.io.IOException
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


object NatureRemo {
    /** jsonパース時に存在しないプロパティがある場合無視するか否か*/
    const val isIgnoreUnknown = true

    /**
     * Fetch the authenticated user’s information.
     * 認証されたユーザーの情報を取得します。
     * @return ユーザーの情報
     */
    fun usersMeGet(token: String): User? {
        return get(token, "https://api.nature.global/1/users/me")
    }

    /**
     * Update authenticated user’s information.
     * 認証されたユーザーの情報を更新します。
     * @param nickname ユーザー名
     * @return 更新後のユーザーの情報
     */
    fun usersMePOST(token: String, nickname: String): User? {
        return post(token, "https://api.nature.global/1/users/me", "nickname=$nickname")
    }

    /**
     * Find the air conditioner best matching the provided infrared signal.
     * 提供された赤外線信号に最も適合するエアコンを探します。
     * @param message JSON serialized object describing infrared signals. Includes "data", “freq” and “format” keys.
     * @return
     */
    fun detectAppliancePOST(token: String, message: String): ApplianceModelAndParam? {
        return post(token, "https://api.nature.global/1/detectappliance", "message =$message")
    }

    /**
     * Fetch the list of Remo devices the user has access to.
     * ユーザーがアクセスできるRemoデバイスのリストを取得します。
     * @return デバイスリスト
     */
    fun devicesGet(token: String): Array<Device>? {
        return get(token, "https://api.nature.global/1/devices")
    }

    /**
     * Fetch the list of appliances.
     * アプライアンスのリストを取得します。
     * @return アプライアンスリスト
     */
    fun appliancesGet(token: String): Array<Appliance>? {
        return get(token, "https://api.nature.global/1/appliances")
    }


    /**
     * Reorder appliances.
     * アプライアンスの並べ替え。
     * すべてのアンプライアンスを指定する必要がある。
     * @param appliances List of all appliances’ IDs comma separated
     * アプライアンスIDをカンマ区切りで指定
     */
    fun applianceOrdersPOST(token: String, appliances: String) {
        post<Any?>(token, "https://api.nature.global/1/appliance_orders", "appliances=$appliances")
    }

    /**
     * Create a new appliance.
     * 新しいアプライアンスを作成します。
     * @param nickname アプライアンス名
     * @param model    プリセットのID?(省略可)
     * @param device   デバイスID(紐付けるデバイスのID？)
     * @param image    アプリのメイン画面に表示されるアイコン
     * @return 新しいアプライアンス
     */
    fun appliancesPost(token: String, nickname: String, model: String?, device: String, image: ApplianceImage): Appliance? {
        val query = HashSet<CharSequence>()
        query.add("nickname=$nickname")
        if (model != null) query.add("model=$model")
        query.add("device=$device")
        query.add("image=" + image.toString())

        return post(token, "https://api.nature.global/1/appliances", join("&", query))
    }

    /**
     * Delete appliance.
     * アプライアンスを削除します。
     * @param appliance アプライアンスID
     */
    fun appliancesApplianceDeletePost(token: String, appliance: String) {
        post<Any?>(token, "https://api.nature.global/1/appliances/$appliance/delete", null)
    }

    /**
     * Update appliance.
     * アプライアンスを更新します。
     * @param appliance アプライアンスID
     * @param image     アプリのメイン画面に表示されるアイコン
     * @param nickname  アプライアンス名
     * @return 更新後のアプライアンス
     */
    fun appliancesAppliancePost(token: String, appliance: String, image: ApplianceImage, nickname: String): Appliance? {
        val query = HashSet<CharSequence>()
        query.add("nickname=$nickname")
        query.add("image=" + image.toString())

        return post(token, "https://api.nature.global/1/appliances/$appliance", join("&", query))
    }

    /**
     * Update air conditioner settings.
     * エアコンの設定を更新します。
     * @param appliance      アプライアンスID
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
    fun appliancesApplianceAirConSettingsPost(token: String, appliance: String, temperature: String?, operation_mode: String?, air_volume: String?, air_direction: String?, button: String?): AirConParams? {
        val query = HashSet<CharSequence>()
        if (temperature != null) query.add("temperature=$temperature")
        if (operation_mode != null) query.add("operation_mode=$operation_mode")
        if (air_volume != null) query.add("air_volume=$air_volume")
        if (air_direction != null) query.add("air_direction=$air_direction")
        if (button != null) query.add("button=$button")

        return post(token, "https://api.nature.global/1/appliances/$appliance/aircon_settings", join("&", query))
    }

    /**
     * Send tv infrared signal.
     * テレビの赤外線信号を送信します。
     * @param appliance アプライアンスID
     * @param button    TVボタン名
     * 指定可能な範囲はButtons参照
     * @return 更新した設定
     */
    fun appliancesApplianceTvPost(token: String, appliance: String, button: String): Tv.State? {
        val query = HashSet<CharSequence>()
        query.add("button=$button")

        return post(token, "https://api.nature.global/1/appliances/$appliance/tv", join("&", query))
    }

    /**
     * Send light infrared signal.
     * 照明の赤外線信号を送信します。
     * @param appliance アプライアンスID
     * @param button    照明ボタン名
     * 指定可能な範囲はButtons参照
     * @return 更新した設定
     */
    fun appliancesApplianceLightPost(token: String, appliance: String, button: String): Light.State? {
        val query = HashSet<CharSequence>()
        query.add("button=$button")

        return post(token, "https://api.nature.global/1/appliances/$appliance/light", join("&", query))
    }


    /**
     * Fetch signals registered under this appliance.
     * このアプライアンスで登録された信号を取得します。
     * @param appliance アプライアンスID
     * @return シグナルリスト
     */
    fun appliancesApplianceSignalsGet(token: String, appliance: String): Array<Signal>? {
        return get(token, "https://api.nature.global/1/appliances/$appliance/signals")
    }


    /**
     * Create a signal under this appliance.
     * このアプライアンスの下に信号を作成します。
     * @param appliance アプライアンスID
     * @param message   JSON serialized object describing infrared signals. Includes "data", “freq” and “format” keys.
     * 赤外線信号を記述するJSON直列化オブジェクト。 "data"、 "freq"、 "format"キーが含まれています。
     * @param image     シグナルアイコン
     * @param name      シグナル名
     * @return 作成したシグナル
     */
    fun appliancesApplianceSignalsPost(token: String, appliance: String, message: String, image: SignalImage, name: String): Signal? {
        val query = HashSet<CharSequence>()
        query.add("message=$message")
        query.add("image=" + image.toString())
        query.add("name=$name")
        return post(token, "https://api.nature.global/1/appliances/$appliance/signals", join("&", query))
    }

    /**
     * Reorder signals under this appliance.
     * シグナルの並べ替え。
     * すべてのシグナルを指定する必要がある。
     * @param appliance アプライアンスID
     * @param signals   List of all signals’ IDs comma separated
     * シグナルIDをカンマ区切りで指定
     */
    fun appliancesApplianceSignalsOrdersPost(token: String, appliance: String, signals: String) {
        post<Any?>(token, "https://api.nature.global/1/appliances/$appliance/signal_orders", "signals=$signals")
    }

    /**
     * Update infrared signal.
     * 赤外線信号を更新します。
     * @param signal シグナルID
     * @param image  シグナルアイコン
     * @param name   シグナル名
     * @return 更新後のシグナル
     */
    fun signalsSignalPost(token: String, signal: String, image: SignalImage, name: String): Signal? {
        val query = HashSet<CharSequence>()
        query.add("image=" + image.toString())
        query.add("name=$name")

        return post(token, "https://api.nature.global/1/signals/$signal", join("&", query))
    }

    /**
     * Send infrared signal.
     * 赤外線信号を送信します。
     * @param signal シグナルID
     */
    fun signalsSignalSendPost(token: String, signal: String) {
        post<Any?>(token, "https://api.nature.global/1/signals/$signal/send", null)
    }

    /**
     * Delete an infrared signal.
     * 赤外線信号を削除します。
     * @param signal シグナルID
     */
    fun signalsSignalDeletePost(token: String, signal: String) {
        post<Any?>(token, "https://api.nature.global/1/signals/$signal/delete", null)
    }

    /**
     * Update Remo.
     * デバイス名を更新する
     * @param device デバイスID
     * @param name   デバイス名
     * @return 更新後のデバイス情報
     */
    fun devicesDevicePost(token: String, device: String, name: String): Device? {
        return post(token, "https://api.nature.global/1/devices/$device", "name=$name")
    }

    /**
     * Delete Remo.
     * デバイスの削除
     * @param device デバイスID
     */
    fun devicesDeviceDeletePost(token: String, device: String) {
        post<Any?>(token, "https://api.nature.global/1/devices/$device/delete", null)
    }

    /**
     * Update temperature offset.
     * 温度オフセットを更新します。
     * @param device デバイスID
     * @param offset 温度オフセット
     * @return 更新後のデバイス情報
     */
    fun devicesDeviceHumidityOffsetPost(token: String, device: String, offset: Int): Device? {
        return post(token, "https://api.nature.global/1/devices/$device/humidity_offset", "offset=$offset")
    }

    /**
     * Update humidity offset.
     * 湿度オフセットを更新します。
     * @param device デバイスID
     * @param offset 湿度オフセット
     * @return 更新後のデバイス情報
     */
    fun devicesDeviceTemperatureOffsetPost(token: String, device: String, offset: Int): Device? {
        return post(token, "https://api.nature.global/1/devices/$device/temperature_offset", "offset=$offset")
    }

    private inline fun <reified T> get(token: String, url: String): T? {
        try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("accept", "application/json")
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.connect()

            when (conn.responseCode) {
                HttpURLConnection.HTTP_OK -> {
                    //通信成功
                    return jacksonObjectMapper().readValue(conn.inputStream)
                }
                HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    //認証エラー
                    throw AuthenticationException()
                }
                429 -> {
                    //リクエスト制限
                    throw RequestLimitException()

                }
                else -> {
                    throw OtherException()
                }
            }
        } catch (e: IOException) {
            throw OtherException(e.message)
        }
    }

    private inline fun <reified T> post(token: String, url: String, params: String?): T? {
        try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("accept", "application/json")
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.doInput = true
            if (params is String) {
                conn.doOutput = true
                PrintWriter(conn.outputStream).use { out ->
                    out.print(params)
                    out.flush()
                }
            }

            conn.connect()

            when (conn.responseCode) {
                HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_CREATED -> {
                    //通信成功
                    return jacksonObjectMapper().readValue(conn.inputStream)
                }
                HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    //認証エラー
                    throw AuthenticationException()
                }
                429 -> {
                    //リクエスト制限
                    throw RequestLimitException()

                }
                else -> {
                    throw OtherException()
                }
            }
        } catch (e: IOException) {
            throw OtherException(e.message)
        }
    }

}

