/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import com.fishwaffle.natureremo.controller.NatureRemo;
import com.fishwaffle.natureremo.controller.SignalImage;

import static com.fishwaffle.natureremo.controller.Util.requireNonNull;

public class Signal {
    public String id;
    public String name;
    public String image;

    public Signal Update(SignalImage image, String name) {
        return NatureRemo.Signals_Signal_Post(id, requireNonNull(image, SignalImage.valueOf(this.image)), requireNonNull(name, this.name));
    }

    public void Send() {
        NatureRemo.Signals_Signal_Send_Post(id);
    }

    public void Delete() {
        NatureRemo.Signals_Signal_Delete_Post(id);
    }

    @Override
    public String toString() {
        return "Signal{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", image='" + image + '\'' + '}';
    }
}
