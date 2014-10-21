package com.aoks.utils.temporal;

public interface Range<T> {

    Range<?> start(T unit);

    T start();

    Range<?> end(T unit);

    T end();

    boolean includes(T unit);
}
