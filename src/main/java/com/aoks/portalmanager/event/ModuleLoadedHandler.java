package com.aoks.portalmanager.event;

import com.aoks.portalmanager.model.PortalModule;

public interface ModuleLoadedHandler {
	
	void handleModuleLoading(PortalModule module);

}
