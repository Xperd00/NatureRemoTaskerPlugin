/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import java.io.Serializable

class User : Serializable {
    /** ユーザーID  */
    var id: String? = null
    /** ユーザー名  */
    var nickname: String? = null

    override fun toString(): String {
        return "User{" + "id='" + id + '\''.toString() + ", nickname='" + nickname + '\''.toString() + '}'.toString()
    }
}
