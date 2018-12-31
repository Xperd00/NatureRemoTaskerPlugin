/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller


import android.text.TextUtils.join
import com.fasterxml.jackson.databind.ObjectMapper
import com.fishwaffle.natureremo.controller.models.*
import java.io.IOException
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


object NatureRemo {


    /**
     * Fetch the authenticated user’s information.
     * 認証されたユーザーの情報を取得します。
     * @return ユーザーの情報
     */
    fun usersMeGet(token: String): User? {
        return get(token, "https://api.nature.global/1/users/me", User::class.java)
    }

    /**
     * Update authenticated user’s information.
     * 認証されたユーザーの情報を更新します。
     * @param nickname ユーザー名
     * @return 更新後のユーザーの情報
     */
    fun usersMePOST(token: String, nickname: String): User? {
        return post(token, "https://api.nature.global/1/users/me", User::class.java, "nickname=$nickname")
    }

    /**
     * Find the air conditioner best matching the provided infrared signal.
     * 提供された赤外線信号に最も適合するエアコンを探します。
     * @param message JSON serialized object describing infrared signals. Includes "data", “freq” and “format” keys.
     * @return
     */
    fun detectAppliancePOST(token: String, message: String): ApplianceModelAndParam? {
        return post(token, "https://api.nature.global/1/detectappliance", ApplianceModelAndParam::class.java, "message =$message")
    }

    /**
     * Fetch the list of Remo devices the user has access to.
     * ユーザーがアクセスできるRemoデバイスのリストを取得します。
     * @return デバイスリスト
     */
    fun devicesGet(token: String): Array<Device>? {
        return get(token, "https://api.nature.global/1/devices", Array<Device>::class.java)
    }

    /**
     * Fetch the list of appliances.
     * アプライアンスのリストを取得します。
     * @return アプライアンスリスト
     */
    fun appliancesGet(token: String): Array<Appliance>? {
        return get(token, "https://api.nature.global/1/appliances", Array<Appliance>::class.java)
    }


    /**
     * Reorder appliances.
     * アプライアンスの並べ替え。
     * すべてのアンプライアンスを指定する必要がある。
     * @param appliances List of all appliances’ IDs comma separated
     * アプライアンスIDをカンマ区切りで指定
     */
    fun applianceOrdersPOST(token: String, appliances: String) {
        post(token, "https://api.nature.global/1/appliance_orders", Any::class.java, "appliances=$appliances")
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

        return post(token, "https://api.nature.global/1/appliances", Appliance::class.java, join("&", query))
    }

    /**
     * Delete appliance.
     * アプライアンスを削除します。
     * @param appliance アプライアンスID
     */
    fun appliancesApplianceDeletePost(token: String, appliance: String) {
        post(token, "https://api.nature.global/1/appliances/$appliance/delete", Any::class.java, null)
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

        return post(token, "https://api.nature.global/1/appliances/$appliance", Appliance::class.java, join("&", query))
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

        return post(token, "https://api.nature.global/1/appliances/$appliance/aircon_settings", AirConParams::class.java, join("&", query))
    }


    /**
     * Fetch signals registered under this appliance.
     * このアプライアンスで登録された信号を取得します。
     * @param appliance アプライアンスID
     * @return シグナルリスト
     */
    fun appliancesApplianceSignalsGet(token: String, appliance: String): Array<Signal>? {
        return get(token, "https://api.nature.global/1/appliances/$appliance/signals", Array<Signal>::class.java)
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
    fun appliancesApplianceSignalsPOST(token: String, appliance: String, message: String, image: SignalImage, name: String): Signal? {
        val query = HashSet<CharSequence>()
        query.add("message=$message")
        query.add("image=" + image.toString())
        query.add("name=$name")
        return post(token, "https://api.nature.global/1/appliances/$appliance/signals", Signal::class.java, join("&", query))
    }

    /**
     * Reorder signals under this appliance.
     * シグナルの並べ替え。
     * すべてのシグナルを指定する必要がある。
     * @param appliance アプライアンスID
     * @param signals   List of all signals’ IDs comma separated
     * シグナルIDをカンマ区切りで指定
     */
    fun appliancesApplianceSignalsOrdersPOST(token: String, appliance: String, signals: String) {
        post(token, "https://api.nature.global/1/appliances/$appliance/signal_orders", Any::class.java, "signals=$signals")
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

        return post(token, "https://api.nature.global/1/signals/$signal", Signal::class.java, join("&", query))
    }

    /**
     * Send infrared signal.
     * 赤外線信号を送信します。
     * @param signal シグナルID
     */
    fun signalsSignalSendPost(token: String, signal: String) {
        post(token, "https://api.nature.global/1/signals/$signal/send", Any::class.java, null)
    }

    /**
     * Delete an infrared signal.
     * 赤外線信号を削除します。
     * @param signal シグナルID
     */
    fun signalsSignalDeletePost(token: String, signal: String) {
        post(token, "https://api.nature.global/1/signals/$signal/delete", Any::class.java, null)
    }

    /**
     * Update Remo.
     * デバイス名を更新する
     * @param device デバイスID
     * @param name   デバイス名
     * @return 更新後のデバイス情報
     */
    fun devicesDevicePost(token: String, device: String, name: String): Device? {
        return post(token, "https://api.nature.global/1/devices/$device", Device::class.java, "name=$name")
    }

    /**
     * Delete Remo.
     * デバイスの削除
     * @param device デバイスID
     */
    fun devicesDeviceDeletePost(token: String, device: String) {
        post(token, "https://api.nature.global/1/devices/$device/delete", Any::class.java, null)
    }

    /**
     * Update temperature offset.
     * 温度オフセットを更新します。
     * @param device デバイスID
     * @param offset 温度オフセット
     * @return 更新後のデバイス情報
     */
    fun devicesDeviceHumidityOffsetPost(token: String, device: String, offset: Int): Device? {
        return post(token, "https://api.nature.global/1/devices/$device/humidity_offset", Device::class.java, "offset=$offset")
    }

    /**
     * Update humidity offset.
     * 湿度オフセットを更新します。
     * @param device デバイスID
     * @param offset 湿度オフセット
     * @return 更新後のデバイス情報
     */
    fun devicesDeviceTemperatureOffsetPost(token: String, device: String, offset: Int): Device? {
        return post(token, "https://api.nature.global/1/devices/$device/temperature_offset", Device::class.java, "offset=$offset")
    }

    private fun <T> get(token: String, url: String, dataClass: Class<T>): T? {
        try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("accept", "application/json")
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.connect()
            val statusCode = conn.responseCode

            if (statusCode == HttpURLConnection.HTTP_OK) {
//                val result = StringBuilder()
                val result = conn.inputStream.bufferedReader().use { it.readText() }
                //responseの読み込み
//                BufferedReader(InputStreamReader(conn.inputStream, StandardCharsets.UTF_8)).use { br ->
//                    var line: String
//                    br.useLines {  }
//                    while ((line = br.readLine()) != null) {
//                        result.append(line)
//                    }
//                }
                return ObjectMapper().readValue(result, dataClass)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null

        }

        return null
    }

    private fun <T> post(token: String, url: String, dataClass: Class<T>, params: String?): T? {
        try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("accept", "application/json")
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.doInput = true
            if (params != null) {
                conn.doOutput = true
                PrintWriter(conn.outputStream).use { out ->
                    out.print(params)
                    out.flush()
                }
            }

            conn.connect()
            val statusCode = conn.responseCode

            if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
//                val result = StringBuilder()
                val result = conn.inputStream.bufferedReader().use { it.readText() }

//                //responseの読み込み
//                BufferedReader(InputStreamReader(conn.inputStream, StandardCharsets.UTF_8)).use { br ->
//                    var line: String
//                    while ((line = br.readLine()) != null) {
//                        result.append(line)
//                    }
//                }
                return ObjectMapper().readValue(result, dataClass)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null

        }

        return null
    }
}
