package com.aoks.utils.number;

public interface Range<T> {

	Range<T> start(T unit);

    T start();

    Range<T> end(T unit);

    T end();

    boolean includes(T unit);
}