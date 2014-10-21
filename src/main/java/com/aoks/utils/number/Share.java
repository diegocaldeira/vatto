package com.aoks.utils.number;

public interface Share<T> {

    void set(int share, T shared);

    T get();

    int getShare();
}
