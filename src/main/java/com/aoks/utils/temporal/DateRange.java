package com.aoks.utils.temporal;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Diego Pereira
 * @site aoks.com.br
 *
 */
@Embeddable
public class DateRange implements Range<Calendar> {

	@Temporal(TemporalType.DATE)
	@Column(name="cStart")
    private Calendar start;

	@Temporal(TemporalType.DATE)
	@Column(name="cEnd")
    private Calendar end;

    public DateRange() {
    }

    public DateRange(Calendar start) {
        this.start = start;
    }

    public DateRange(Calendar start, Calendar end) {
        this.start = start;
        this.end = end;
    }

    @Override
	public DateRange start(Calendar start) {
        this.start = start;
        return this;
    }

    @Override
	public DateRange end(Calendar end) {
        this.end = end;
        return this;
    }

    @Override
	public Calendar start() {
        return start;
    }

    @Override
	public Calendar end() {
        return end;
    }

    @Override
    public boolean includes(Calendar date) {
        return (start.equals(date) || start.before(date)) && (end == null || end.after(date));
    }

}
