/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import com.fishwaffle.natureremo.controller.ApplianceImage;
import com.fishwaffle.natureremo.controller.NatureRemo;
import com.fishwaffle.natureremo.controller.SignalImage;

import java.io.Serializable;
import java.util.Arrays;

import static com.fishwaffle.natureremo.controller.Util.requireNonNull;

public class Appliance implements Serializable {
    /** アプライアンスID */
    public String id;
    /** デバイス */
    public DeviceCore device;
    /** Model(プリセット情報) */
    public ApplianceModel model;
    /**
     * AC:エアコンのプリセット利用
     * TV:テレビのプリセット利用
     * IR:その他
     */
    public String type;
    /** アプライアンス名 */
    public String nickname;
    /** アイコン */
    public String image;
    /** エアコンの設定状態 */
    public AirConParams settings;
    public AirCon aircon;
    /** シグナルリスト */
    public Signal[] signals;
    /** テレビリモコンの情報 */
    public Tv tv;


    /**
     * シグナル取得
     * @return シグナルリスト
     */
    public Signal[] GetSignals(String token) {
        return NatureRemo.Appliances_Appliance_Signals_Get(token, id);
    }

    /**
     * アイコン/アプライアンス名を更新する
     * @param image    アイコン
     * @param nickname アプライアンス名
     * @return 更新後のアプライアンス
     */
    public Appliance Update(String token, ApplianceImage image, String nickname) {
        return NatureRemo.Appliances_Appliance_Post(token, id, requireNonNull(image, ApplianceImage.valueOf(this.image)), requireNonNull(nickname, this.nickname));
    }

    /**
     * アプライアンスを削除する
     */
    public void Delete(String token) {
        NatureRemo.Appliances_Appliance_Delete_Post(token, id);
    }

    public Signal AddSignal(String token, String message, SignalImage image, String name) {
        return NatureRemo.Appliances_Appliance_Signals_POST(token, id, message, image, name);
    }

    /**
     * Update air conditioner settings.
     * エアコンの設定を更新します。
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
    public AirConParams AirconSettings(String token, String temperature, String operation_mode, String air_volume, String air_direction, String button) {
        return NatureRemo.Appliances_Appliance_AirConSettings_Post(token, id, requireNonNull(temperature, settings.temp), requireNonNull(operation_mode, settings.mode), requireNonNull(air_volume, settings.vol), requireNonNull(air_direction, settings.dir), requireNonNull(button, settings.button));
    }

    @NonNull
    @Override
    public String toString() {
        return "Appliance{" + "id='" + id + '\'' + ", device=" + device + ", model=" + model + ", type='" + type + '\'' + ", nickname='" + nickname + '\'' + ", image='" + image + '\'' + ", settings=" + settings + ", aircon=" + aircon + ", signals=" + Arrays.toString(signals) + ", tv=" + tv + '}';
    }
}






