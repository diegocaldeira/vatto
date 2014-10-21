package com.aoks.utils.temporal;

import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Diego Pereira
 * @site aoks.com.br
 *
 */
@Embeddable
@MappedSuperclass
public class AbstractEffectivity implements Effectivity {

    @Embedded
    protected DateRange dateRange;

    public AbstractEffectivity() {
        dateRange = new DateRange();
        dateRange.start(Calendar.getInstance());
    }

    public AbstractEffectivity(Calendar start) {
        this();
        dateRange.start(start);
    }

    @Override
	public void create(Calendar start) {
        dateRange.start(start);
    }

    @Override
	public void end(Calendar end) {
        dateRange.end(end);
    }

    @Override
	public boolean isEffectiveOnDate(Calendar calendar) {
        return dateRange.includes(calendar);
    }
}
