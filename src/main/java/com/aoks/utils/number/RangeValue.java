package com.aoks.utils.number;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RangeValue implements Range<Long> {

    @Column(name = "cBegin")
    private long begin;
    
    @Column(name = "cEnd")
    private long end;

    @Column(name="cRangeValue")
    private BigDecimal value;
    
    @Column(name="cName")
    private String name;

    public RangeValue() {
    }

    public RangeValue(long begin, long end, BigDecimal value) {
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    public void setDoubleValue(double value){
    	this.value = new BigDecimal(value);
    }
    
    public double getDoubleValue(){
    	return value.doubleValue();
    }

    @Override
    public Range<Long> end(Long unit) {
        end = unit;
        return this;
    }

    @Override
    public Long end() {
        return end;
    }

    @Override
    public boolean includes(Long unit) {
        return (unit >= begin && unit < end);
    }

    @Override
    public Range<Long> start(Long unit) {
        begin = unit;
        return this;
    }

    @Override
    public Long start() {
        return begin;
    }
    
    public String getName() {
		return name;
	}
    public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" init: ").append(begin).append(",");
        builder.append(" end: ").append(end).append(",");
        builder.append(" value: ").append(value);
        return builder.toString();
    }
}
