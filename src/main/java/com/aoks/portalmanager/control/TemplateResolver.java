package com.aoks.portalmanager.control;

import java.io.IOException;
import java.net.URL;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ResourceResolver;

public class TemplateResolver extends ResourceResolver {

    public URL resolveUrl(String path) {
    	
        URL url = _resolveUrl(path);
        
        if (url == null) {
            if (path.startsWith("/"))
                path = path.substring(1);
            
            url = Thread.currentThread().getContextClassLoader().getResource(path);
            
        } 
        
        return url;
    }
    
    private URL _resolveUrl(String path){
    	
    	try {
    		return PortalResource.getResourceUrl(FacesContext.getCurrentInstance(), path);
    	} catch (IOException e) {
    		throw new FacesException(e);
    	}
    }
    
}
