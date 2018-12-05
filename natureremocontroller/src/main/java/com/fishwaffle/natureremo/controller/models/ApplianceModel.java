/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

/**
 * Model(プリセット情報)
 */
public class ApplianceModel {
    /** プリセットID */
    public String id;
    /** メーカー */
    public String manufacturer;
    /** リモコンの型番 */
    public String remote_name;
    /** シリーズ */
    public String series;
    /** プリセット名 */
    public String name;
    /** アイコン */
    public String image;

    @Override
    public String toString() {
        return "ApplianceModel{" + "id='" + id + '\'' + ", manufacturer='" + manufacturer + '\'' + ", remote_name='" + remote_name + '\'' + ", series='" + series + '\'' + ", name='" + name + '\'' + ", image='" + image + '\'' + '}';
    }
}