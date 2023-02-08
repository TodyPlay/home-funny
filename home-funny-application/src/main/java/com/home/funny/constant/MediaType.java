package com.home.funny.constant;

import com.home.funny.constant.wrapeper.KVEnum;

/**
 * 多媒体类别
 */
public enum MediaType implements KVEnum<String, String> {
    VIDEO("视频"), NOVEL("小说"), ATLAS("图集"),
    ;

    private final String val;

    MediaType(String val) {
        this.val = val;
    }

    @Override
    public String key() {
        return name();
    }

    @Override
    public String val() {
        return val;
    }
}
