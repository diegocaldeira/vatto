package com.aoks.utils.temporal;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface Temporal<V> {

    V getTemporal(Calendar when);
    V getTemporal();
    void putTemporal(Calendar at, V item);
    void removeTemporal(V item);
    
    Map<Calendar, V> getContents();
    void setContents(Map<Calendar, V> contents);

    List<V> getAllAsList();

    Calendar lastDate();
}
