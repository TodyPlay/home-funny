package com.home.funny.constant;

/**
 * 多媒体类别
 */
public enum MediaType implements KVEnum<String, String> {
    VIDEO("视频"),
    NOVEL("小说"),
    atlas("图集"),
    ;

    private final String val;

    MediaType(String val) {
        this.val = val;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getVal() {
        return val;
    }
}
