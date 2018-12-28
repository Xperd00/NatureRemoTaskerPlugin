/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller;


public class Util {

    public static <T> T requireNonNull(T obj, T def) {
        if (obj != null) {
            return obj;
        } else {
            return def;
        }

    }

}
