//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.utils;

public class SingleValue<T> {
    private T value;

    public SingleValue() {
    }

    public boolean isNull() {
        return null == this.value || "".equals(this.value.toString().trim());
    }

    public boolean isNotNull() {
        return !this.isNull();
    }

    public T getValue() {
        return this.value;
    }

    public T getAndSet(T t) {
        T oldValue = this.value;
        this.value = t;
        return oldValue;
    }

    public SingleValue<T> setValue(T value) {
        this.value = value;
        return this;
    }
}
