/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import java.io.Serializable;

public class ApplianceModelAndParam  implements Serializable {
    /**プリセット情報*/
    public ApplianceModel model;
    /**エアコンの設定状態*/
    public AirConParams params;
}
