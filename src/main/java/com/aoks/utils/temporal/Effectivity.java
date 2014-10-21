package com.aoks.utils.temporal;

import java.util.Calendar;

/**
 * 
 * @author Diego Pereira
 * @site aoks.com.br
 *
 */
public interface Effectivity {

    void create(Calendar start);

    void end(Calendar end);

    boolean isEffectiveOnDate(Calendar calendar);
}
