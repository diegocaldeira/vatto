package com.aoks.portalmanager.event;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.aoks.portalmanager.model.PortalModule;


public class ModuleLoadedHandlerImpl implements ModuleLoadedHandler {

	@Inject
	private Event<PortalModule> moduleLoaded;
	
	@Override
	public void handleModuleLoading(PortalModule module) {
		moduleLoaded.fire(module);
	}

}
