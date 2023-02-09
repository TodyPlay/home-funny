package com.home.funny.constant.wrapeper;

public record KVWrapper<K, V>(K key, V val) {
    public static <K, V> KVWrapper<K, V> valueOf(KVEnum<K, V> kv) {
        return new KVWrapper<>(kv.key(), kv.val());
    }
}
