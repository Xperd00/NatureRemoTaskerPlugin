/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

public class User {
    /** ユーザーID */
    public String id;
    /** ユーザー名 */
    public String nickname;

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", nickname='" + nickname + '\'' + '}';
    }
}
