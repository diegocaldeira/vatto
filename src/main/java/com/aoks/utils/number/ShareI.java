package com.aoks.utils.number;

import java.util.Map;

import javax.persistence.Embeddable;

@Embeddable
public class ShareI<T> implements Share<T> {

    protected Map<Integer, T> contents;

    @Override
    public void set(int share, T shared) {
    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public int getShare() {
        return 0;
    }
}
