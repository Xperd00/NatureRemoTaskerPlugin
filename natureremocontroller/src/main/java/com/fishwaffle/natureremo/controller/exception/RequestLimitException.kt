/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.exception

class RequestLimitException(msg: String? = "リクエスト制限エラー") : RuntimeException(msg)