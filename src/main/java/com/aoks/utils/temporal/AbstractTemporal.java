package com.aoks.utils.temporal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Transient;

/**
 * Uma classe abstrata genérica para propriedades temporais de objetos 
 * (cf. padrão Temporal Property de Martin Fowler).
 * 
 * @author Diego Pereira
 *
 * @param <V>
 */
public abstract class AbstractTemporal<V> implements Temporal<V> {

//    final Logger logger = LoggerFactory.getLogger("com.aoks.utils.temporal.Temporal");
    @Transient
    private volatile List<Calendar> _milestoneCache;

    @Override
    public V getTemporal(Calendar when) {

        /** returns the value that was effective on the given date */
        Iterator<Calendar> it = myMilestones().iterator();
        while (it.hasNext()) {
            Calendar thisDate = it.next();
            if (thisDate.before(when) || thisDate.equals(when)) {
                return getContents().get(thisDate);
            }
        }
        return null;
    }

    @Override
    public Calendar lastDate() {
        Collections.sort(myMilestones(), Collections.reverseOrder());
        if (myMilestones().isEmpty())
            return null;
        return myMilestones().get(0);
    }

    @Override
    public V getTemporal() {
        /** returns the value that was effective on the actual date */
        return getTemporal(Calendar.getInstance());
    }

    @Override
    public List<V> getAllAsList() {
        return new ArrayList<V>(getContents().values());
    }

    @Override
    public void putTemporal(Calendar at, V item) {
        /** the item is valid from the supplied date onwards */
        if (at == null)
            return;
//        logger.debug("Adding temporal on date " + DateUtils.format8(at));

        if (getContents().containsKey(at))
        	getContents().remove(at);
        
        getContents().put(at, item);
        clearMilestoneCache();
    }

    @Override
    public void removeTemporal(V item) {

//        logger.debug("Removing temporal");

        Map<Calendar, V> contents = getContents();
        Calendar cal = null;
        for (Entry<Calendar, V> i : contents.entrySet()) {
            if (i.getValue().equals(item)) {
                cal = i.getKey();
            }
        }
        if (cal != null) {
            contents.remove(cal);
        }
        clearMilestoneCache();

    }

    private synchronized List<Calendar> myMilestones() {
        /** a list of all the dates where the value changed, returned in order latest first */
        if (_milestoneCache == null) {
            calculateMilestones();
        }
        return _milestoneCache;
    }

    private void calculateMilestones() {
        _milestoneCache = new ArrayList<Calendar>(getContents().size());
        _milestoneCache.addAll(getContents().keySet());
        Collections.sort(_milestoneCache, Collections.reverseOrder());
    }

    private void clearMilestoneCache() {
        _milestoneCache = null;
    }

	@Override
	public abstract Map<Calendar, V> getContents();

	@Override
	public abstract void setContents(Map<Calendar, V> contents);
}
