package com.github.cnlinjie.infrastructure.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 辅助工具
 * @author linjie
 * @version 0.0.1
 * @date 16-5-17
 */
public class AListUtils {

    public AListUtils () {
    }

    public static <K>  AListUtils.AListBuilder<K> builder(ArrayList<K> arrayList) {
        return new AListUtils.AListBuilder(arrayList);
    }

    public static <K>  AListUtils.AListBuilder<K> builder(K k) {
        return new AListUtils.AListBuilder(new ArrayList<K>());
    }

    public static AListUtils.AListBuilder<Object> builder() {
        return new AListUtils.AListBuilder(Lists.newArrayList());
    }

    public static class  AListBuilder<K> {

        private List<K> kList ;

        public AListBuilder(ArrayList<K> arrayList) {
            this.kList = arrayList;
        }

        public AListBuilder add (K k) {
            kList.add(k);
            return this;
        }

        public List<K> get() {
            return kList;
        }

    }

}
