package com.home.funny.constant.wrapeper;

import java.util.Arrays;
import java.util.List;

public record KVWrapper(String key, String val) {
    public static KVWrapper valueOf(KVEnum<?, ?> kv) {
        return new KVWrapper(String.valueOf(kv.key()), String.valueOf(kv.val()));
    }

    public static <T extends Enum<T> & KVEnum<?, ?>> List<KVWrapper> values(Class<T> type) {
        return Arrays.stream(type.getEnumConstants()).map(KVWrapper::valueOf).toList();
    }
}
