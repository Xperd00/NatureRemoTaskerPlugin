/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.controller.SignalImage
import com.fishwaffle.natureremo.controller.Util.requireNonNull
import com.fishwaffle.natureremo.controller.isIgnoreUnknown
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class Signal(var id: String?, var name: String?, var image: String?) : Serializable {

    fun update(token: String, image: SignalImage, name: String): Signal? {
        return NatureRemo.signalsSignalPost(token, id!!, requireNonNull(image, SignalImage.valueOf(this.image!!)), requireNonNull(name, this.name!!))
    }

    fun send(token: String) {
        NatureRemo.signalsSignalSendPost(token, id!!)
    }

    fun delete(token: String) {
        NatureRemo.signalsSignalDeletePost(token, id!!)
    }


}
