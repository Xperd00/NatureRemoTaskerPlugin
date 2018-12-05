/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.controller.models;

import java.util.Arrays;

/** テレビリモコンの情報 */
public class Tv {
    /** ボタンリスト */
    public Button[] buttons;

    public State state;

    public static class Button {
        /** ボタン名 */
        public String name;
        /** ボタンアイコン */
        public String image;
        /** ラベル */
        public String label;

        @Override
        public String toString() {
            return "Button{" + "name='" + name + '\'' + ", image='" + image + '\'' + ", label='" + label + '\'' + '}';
        }
    }

    public static class State {
        /**
         * TODO APIリファレンスに無いので分からない
         * TOSHIBAのリモコンで「t」が取得できた
         * ボタン表示のレイアウトの識別記号？
         */
        public String input;

        @Override
        public String toString() {
            return "State{" + "input='" + input + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "TV{" + "buttons=" + Arrays.toString(buttons) + ", state=" + state + '}';
    }
}
