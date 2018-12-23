/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;

/** テレビリモコンの情報 */
public class Tv implements Serializable {
    /** ボタンリスト */
    public Button[] buttons;

    public State state;

    public static class Button implements Serializable {
        /** ボタン名 */
        public String name;
        /** ボタンアイコン */
        public String image;
        /** ラベル */
        public String label;

        @NonNull
        @Override
        public String toString() {
            return "Button{" + "name='" + name + '\'' + ", image='" + image + '\'' + ", label='" + label + '\'' + '}';
        }
    }

    public static class State implements Serializable {
        /**
         * TODO APIリファレンスに無いので分からない
         * TOSHIBAのリモコンで「t」が取得できた
         * ボタン表示のレイアウトの識別記号？
         */
        public String input;

        @NonNull
        @Override
        public String toString() {
            return "State{" + "input='" + input + '\'' + '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "TV{" + "buttons=" + Arrays.toString(buttons) + ", state=" + state + '}';
    }
}
