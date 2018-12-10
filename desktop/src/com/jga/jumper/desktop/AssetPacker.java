package com.jga.jumper.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by goran on 11/10/2016.
 */

public class AssetPacker {

    private static final String RAW_ASSETS_PATH = "desktop/assets-raw";
    private static final String ASSETS_PATH = "android/assets";

    public static void main(String[] args) {

        TexturePacker.process(
                RAW_ASSETS_PATH + "/gameplay",
                ASSETS_PATH + "/gameplay",
                "gameplay");

        TexturePacker.process(
                RAW_ASSETS_PATH + "/ui",
                ASSETS_PATH + "/ui",
                "skin");
    }
}
