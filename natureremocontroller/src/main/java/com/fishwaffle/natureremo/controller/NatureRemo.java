/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishwaffle.natureremo.controller.models.AirConParams;
import com.fishwaffle.natureremo.controller.models.Appliance;
import com.fishwaffle.natureremo.controller.models.ApplianceModelAndParam;
import com.fishwaffle.natureremo.controller.models.Device;
import com.fishwaffle.natureremo.controller.models.Signal;
import com.fishwaffle.natureremo.controller.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static com.fishwaffle.natureremo.controller.Util.join;


public class NatureRemo {


    /**
     * Fetch the authenticated user’s information.
     * 認証されたユーザーの情報を取得します。
     * @return ユーザーの情報
     */
    public static User Users_Me_Get(String token) {
        return Get(token, "https://api.nature.global/1/users/me", User.class);
    }

    /**
     * Update authenticated user’s information.
     * 認証されたユーザーの情報を更新します。
     * @param nickname ユーザー名
     * @return 更新後のユーザーの情報
     */
    public static User Users_Me_POST(String token, String nickname) {
        return Post(token, "https://api.nature.global/1/users/me", User.class, "nickname=" + nickname);
    }

    /**
     * Find the air conditioner best matching the provided infrared signal.
     * 提供された赤外線信号に最も適合するエアコンを探します。
     * @param message JSON serialized object describing infrared signals. Includes "data", “freq” and “format” keys.
     * @return
     */
    public static ApplianceModelAndParam Detectappliance_POST(String token, String message) {
        return Post(token, "https://api.nature.global/1/users/me", ApplianceModelAndParam.class, "message =" + message);
    }

    /**
     * Fetch the list of Remo devices the user has access to.
     * ユーザーがアクセスできるRemoデバイスのリストを取得します。
     * @return デバイスリスト
     */
    public static Device[] Devices_Get(String token) {
        return Get(token, "https://api.nature.global/1/devices", Device[].class);
    }

    /**
     * Fetch the list of appliances.
     * アプライアンスのリストを取得します。
     * @return アプライアンスリスト
     */
    public static Appliance[] Appliances_Get(String token) {
        return Get(token, "https://api.nature.global/1/appliances", Appliance[].class);
    }


    /**
     * Reorder appliances.
     * アプライアンスの並べ替え。
     * すべてのアンプライアンスを指定する必要がある。
     * @param appliances List of all appliances’ IDs comma separated
     *                   アプライアンスIDをカンマ区切りで指定
     */
    public static void Appliance_Orders_POST(String token, String appliances) {
        Post(token, "https://api.nature.global/1/appliance_orders", Object.class, "appliances=" + appliances);
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
    public static Appliance Appliances_Post(String token, String nickname, String model, String device, ApplianceImage image) {
        final Set<CharSequence> query = new HashSet<>();
        query.add("nickname=" + nickname);
        if (model != null) query.add("model=" + model);
        query.add("device=" + device);
        query.add("image=" + image.toString());

        return Post(token, "https://api.nature.global/1/appliances", Appliance.class, join("&", query));
    }

    /**
     * Delete appliance.
     * アプライアンスを削除します。
     * @param appliance アプライアンスID
     */
    public static void Appliances_Appliance_Delete_Post(String token, String appliance) {
        Post(token, "https://api.nature.global/1/appliances/" + appliance + "/delete", Object.class, null);
    }

    /**
     * Update appliance.
     * アプライアンスを更新します。
     * @param appliance アプライアンスID
     * @param image     アプリのメイン画面に表示されるアイコン
     * @param nickname  アプライアンス名
     * @return 更新後のアプライアンス
     */
    public static Appliance Appliances_Appliance_Post(String token, String appliance, ApplianceImage image, String nickname) {
        final Set<CharSequence> query = new HashSet<>();
        query.add("nickname=" + nickname);
        query.add("image=" + image.toString());

        return Post(token, "https://api.nature.global/1/appliances/" + appliance, Appliance.class, join("&", query));
    }

    /**
     * Update air conditioner settings.
     * エアコンの設定を更新します。
     * @param appliance      アプライアンスID
     * @param temperature    温度(省略可)
     *                       指定可能な範囲はAirConRangeMode参照
     * @param operation_mode モード(省略可)
     *                       冷房    :cool
     *                       暖房    :warm
     *                       ドライ  :dry
     *                       送風?   :blow
     *                       オート  :auto
     *                       指定可能なモードはAirConRangeMode参照
     * @param air_volume     風量(省略可)
     *                       指定可能な範囲はAirConRangeMode参照
     * @param air_direction  風向き(省略可)
     *                       指定可能な範囲はAirConRangeMode参照
     * @param button         電源(省略可)
     *                       ON :空文字
     *                       OFF:power-off
     * @return 更新した設定
     */
    public static AirConParams Appliances_Appliance_AirConSettings_Post(String token, String appliance, String temperature, String operation_mode, String air_volume, String air_direction, String button) {
        final Set<CharSequence> query = new HashSet<>();
        if (temperature != null) query.add("temperature=" + temperature);
        if (operation_mode != null) query.add("operation_mode=" + operation_mode);
        if (air_volume != null) query.add("air_volume=" + air_volume);
        if (air_direction != null) query.add("air_direction=" + air_direction);
        if (button != null) query.add("button=" + button);

        return Post(token, "https://api.nature.global/1/appliances/" + appliance + "/aircon_settings", AirConParams.class, join("&", query));
    }


    /**
     * Fetch signals registered under this appliance.
     * このアプライアンスで登録された信号を取得します。
     * @param appliance アプライアンスID
     * @return シグナルリスト
     */
    public static Signal[] Appliances_Appliance_Signals_Get(String token, String appliance) {
        return Get(token, "https://api.nature.global/1/appliances/" + appliance + "/signals", Signal[].class);
    }


    /**
     * Create a signal under this appliance.
     * このアプライアンスの下に信号を作成します。
     * @param appliance アプライアンスID
     * @param message   JSON serialized object describing infrared signals. Includes "data", “freq” and “format” keys.
     *                  赤外線信号を記述するJSON直列化オブジェクト。 "data"、 "freq"、 "format"キーが含まれています。
     * @param image     シグナルアイコン
     * @param name      シグナル名
     * @return 作成したシグナル
     */
    public static Signal Appliances_Appliance_Signals_POST(String token, String appliance, String message, SignalImage image, String name) {
        final Set<CharSequence> query = new HashSet<>();
        query.add("message=" + message);
        query.add("image=" + image.toString());
        query.add("name=" + name);
        return Post(token, "https://api.nature.global/1/appliances/" + appliance + "/signals", Signal.class, join("&", query));
    }

    /**
     * Reorder signals under this appliance.
     * シグナルの並べ替え。
     * すべてのシグナルを指定する必要がある。
     * @param appliance アプライアンスID
     * @param signals   List of all signals’ IDs comma separated
     *                  シグナルIDをカンマ区切りで指定
     */
    public static void Appliances_Appliance_Signals_Orders_POST(String token, String appliance, String signals) {
        Post(token, "https://api.nature.global/1/appliances/" + appliance + "/signal_orders", Object.class, "signals=" + signals);
    }

    /**
     * Update infrared signal.
     * 赤外線信号を更新します。
     * @param signal シグナルID
     * @param image  シグナルアイコン
     * @param name   シグナル名
     * @return 更新後のシグナル
     */
    public static Signal Signals_Signal_Post(String token, String signal, SignalImage image, String name) {
        final Set<CharSequence> query = new HashSet<>();
        query.add("image=" + image.toString());
        query.add("name=" + name);

        return Post(token, "https://api.nature.global/1/signals/" + signal, Signal.class, join("&", query));
    }

    /**
     * Send infrared signal.
     * 赤外線信号を送信します。
     * @param signal シグナルID
     */
    public static void Signals_Signal_Send_Post(String token, String signal) {
        Post(token, "https://api.nature.global/1/signals/" + signal + "/send", Object.class, null);
    }

    /**
     * Delete an infrared signal.
     * 赤外線信号を削除します。
     * @param signal シグナルID
     */
    public static void Signals_Signal_Delete_Post(String token, String signal) {
        Post(token, "https://api.nature.global/1/signals/" + signal + "/delete", Object.class, null);
    }

    /**
     * Update Remo.
     * デバイス名を更新する
     * @param device デバイスID
     * @param name   デバイス名
     * @return 更新後のデバイス情報
     */
    public static Device Devices_Device_Post(String token, String device, String name) {
        return Post(token, "https://api.nature.global/1/devices/" + device, Device.class, "name=" + name);
    }

    /**
     * Delete Remo.
     * デバイスの削除
     * @param device デバイスID
     */
    public static void Devices_Device_Delete_Post(String token, String device) {
        Post(token, "https://api.nature.global/1/devices/" + device + "/delete", Object.class, null);
    }

    /**
     * Update temperature offset.
     * 温度オフセットを更新します。
     * @param device デバイスID
     * @param offset 温度オフセット
     * @return 更新後のデバイス情報
     */
    public static Device Devices_Device_HumidityOffset_Post(String token, String device, int offset) {
        return Post(token, "https://api.nature.global/1/devices/" + device + "/humidity_offset", Device.class, "offset=" + offset);
    }

    /**
     * Update humidity offset.
     * 湿度オフセットを更新します。
     * @param device デバイスID
     * @param offset 湿度オフセット
     * @return 更新後のデバイス情報
     */
    public static Device Devices_Device_TemperatureOffset_Post(String token, String device, int offset) {
        return Post(token, "https://api.nature.global/1/devices/" + device + "/temperature_offset", Device.class, "offset=" + offset);
    }

    private static <T> T Get(String token, String url, Class<T> dataClass) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.connect();
            final int statusCode = conn.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK) {
                StringBuilder result = new StringBuilder();
                //responseの読み込み
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                }
                return new ObjectMapper().readValue(result.toString(), dataClass);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return null;
    }

    private static <T> T Post(String token, String url, Class<T> dataClass, String params) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "application/json");
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setDoInput(true);
            if (params != null) {
                conn.setDoOutput(true);
                try (PrintWriter out = new PrintWriter(conn.getOutputStream())) {
                    out.print(params);
                    out.flush();
                }
            }

            conn.connect();
            final int statusCode = conn.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK || statusCode == HttpURLConnection.HTTP_CREATED) {
                StringBuilder result = new StringBuilder();
                //responseの読み込み
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                }
                return new ObjectMapper().readValue(result.toString(), dataClass);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return null;
    }
}
