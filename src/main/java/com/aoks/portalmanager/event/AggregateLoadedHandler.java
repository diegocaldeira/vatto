package com.aoks.portalmanager.event;

import com.aoks.portalmanager.model.PortalAggregate;

public interface AggregateLoadedHandler {

	void handleAggregateLoading(PortalAggregate aggregate);
	
}
