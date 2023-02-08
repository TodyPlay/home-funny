package com.home.funny.constant.wrapeper;

import java.security.PublicKey;

public record KVWrapper<K, V>(K key, V val) implements KVEnum<K, V> {

    public static <K, V> KVWrapper<K, V> valueOf(KVEnum<K, V> kv) {
        return new KVWrapper<>(kv.key(), kv.val());
    }
}
