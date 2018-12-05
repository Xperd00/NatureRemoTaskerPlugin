/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller;

import java.util.Iterator;

public class Util {

    public static <T> T requireNonNull(T obj, T def) {
        if (obj != null) {
            return obj;
        } else {
            return def;
        }

    }

    public static String join(CharSequence delimiter, Iterable tokens) {
        final Iterator<?> it = tokens.iterator();
        if (!it.hasNext()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(delimiter);
            sb.append(it.next());
        }
        return sb.toString();
    }
}
