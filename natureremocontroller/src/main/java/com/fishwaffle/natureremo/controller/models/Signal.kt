/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.controller.SignalImage
import com.fishwaffle.natureremo.controller.Util.requireNonNull
import java.io.Serializable

class Signal : Serializable {
    var id: String? = null
    var name: String? = null
    var image: String? = null

    fun update(token: String, image: SignalImage, name: String): Signal? {
        return NatureRemo.signalsSignalPost(token, id!!, requireNonNull(image, SignalImage.valueOf(this.image!!)), requireNonNull(name, this.name!!))
    }

    fun send(token: String) {
        NatureRemo.signalsSignalSendPost(token, id!!)
    }

    fun delete(token: String) {
        NatureRemo.signalsSignalDeletePost(token, id!!)
    }

    override fun toString(): String {
        return "Signal{" + "id='" + id + '\''.toString() + ", name='" + name + '\''.toString() + ", image='" + image + '\''.toString() + '}'.toString()
    }
}
