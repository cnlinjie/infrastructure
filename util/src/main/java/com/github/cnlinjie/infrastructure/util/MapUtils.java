package com.github.cnlinjie.infrastructure.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Map帮助类，用于帮助构建Map，流式API
 * Created by tminglei on 5/28/15.
 */
public class MapUtils {

    public static MapBuilder<String, Object> builder() {
        return new MapBuilder(new HashMap<String,Object>());
    }

    public static <K, V> MapBuilder<K,V> builder(Map<K, V> map) {
        return new MapBuilder<K, V>(map);
    }

    ///////////////////////////////////////////////

    public static class MapBuilder<K,V> {
        private Map<K,V> map;

        MapBuilder(Map<K,V> map) {
            this.map = map;
        }
        public MapBuilder<K, V> put(K key, V value) {
            map.put(key, value);
            return this;
        }
        public Map<K,V> get() {
            return map;
        }
    }

    public static void insertParamFilterNull(Map<String, Object> params, String key, Object value){
    	if(value == null){
    		return ;
    	}
    	params.put(key, value);
    }
}
