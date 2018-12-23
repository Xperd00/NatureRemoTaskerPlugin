/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class User implements Serializable {
    /** ユーザーID */
    public String id;
    /** ユーザー名 */
    public String nickname;

    @NonNull
    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", nickname='" + nickname + '\'' + '}';
    }
}
