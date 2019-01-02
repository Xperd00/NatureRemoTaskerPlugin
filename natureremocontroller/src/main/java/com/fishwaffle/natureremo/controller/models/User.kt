/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fishwaffle.natureremo.controller.isIgnoreUnknown
import java.io.Serializable

/**
 * ユーザー情報
 * @param id ユーザーID
 * @param nickname ユーザー名
 * @param superuser スーパーユーザー
 */
@JsonIgnoreProperties(ignoreUnknown = isIgnoreUnknown)
data class User(var id: String?, var nickname: String?, var superuser: Boolean) : Serializable
