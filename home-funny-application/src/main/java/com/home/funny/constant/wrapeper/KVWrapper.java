package com.home.funny.constant.wrapeper;

public record KVWrapper(String key, String val) {
    public static KVWrapper valueOf(KVEnum<?, ?> kv) {
        return new KVWrapper(String.valueOf(kv.key()), String.valueOf(kv.val()));
    }
}
