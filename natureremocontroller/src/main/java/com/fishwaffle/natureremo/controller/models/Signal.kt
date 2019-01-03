/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.controller.NatureRemo.isIgnoreUnknown
import com.fishwaffle.natureremo.controller.SignalImage
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class Signal(var id: String?, var name: String?, var image: String?) : Serializable {

    fun update(token: String, image: SignalImage, name: String): Signal? {
        return NatureRemo.signalsSignalPost(token, id!!, image, name)
    }

    fun send(token: String) {
        NatureRemo.signalsSignalSendPost(token, id!!)
    }

    fun delete(token: String) {
        NatureRemo.signalsSignalDeletePost(token, id!!)
    }


}
