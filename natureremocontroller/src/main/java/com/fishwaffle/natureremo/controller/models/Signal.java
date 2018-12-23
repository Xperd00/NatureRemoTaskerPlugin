/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import com.fishwaffle.natureremo.controller.NatureRemo;
import com.fishwaffle.natureremo.controller.SignalImage;

import java.io.Serializable;

import static com.fishwaffle.natureremo.controller.Util.requireNonNull;

public class Signal implements Serializable {
    public String id;
    public String name;
    public String image;

    public Signal Update(String token, SignalImage image, String name) {
        return NatureRemo.Signals_Signal_Post(token, id, requireNonNull(image, SignalImage.valueOf(this.image)), requireNonNull(name, this.name));
    }

    public void Send(String token) {
        NatureRemo.Signals_Signal_Send_Post(token, id);
    }

    public void Delete(String token) {
        NatureRemo.Signals_Signal_Delete_Post(token, id);
    }

    @NonNull
    @Override
    public String toString() {
        return "Signal{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", image='" + image + '\'' + '}';
    }
}
