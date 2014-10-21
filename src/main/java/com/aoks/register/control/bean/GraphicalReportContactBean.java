package com.aoks.register.control.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.primefaces.model.chart.DonutChartModel;

/**
 * 
 * @author Diego Pereira
 *
 */
public class GraphicalReportContactBean implements Serializable {

	private static final long serialVersionUID = 7062295600759223158L;
	
	private DonutChartModel donutModel;  
    
    public GraphicalReportContactBean() {  
        createDonutModel();  
    }  
      
    public DonutChartModel getDonutModel() {  
        return donutModel;  
    }  
   
    private void createDonutModel() {  
        donutModel = new DonutChartModel();  
          
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();  
        circle1.put("Brand 1", 150);  
        circle1.put("Brand 2", 400);  
        circle1.put("Brand 3", 200);  
        circle1.put("Brand 4", 10);  
        donutModel.addCircle(circle1);  
          
        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();  
        circle2.put("Brand 1", 540);  
        circle2.put("Brand 2", 125);  
        circle2.put("Brand 3", 702);  
        circle2.put("Brand 4", 421);  
        donutModel.addCircle(circle2);  
          
        Map<String, Number> circle3 = new LinkedHashMap<String, Number>();  
        circle3.put("Brand 1", 40);  
        circle3.put("Brand 2", 325);  
        circle3.put("Brand 3", 402);  
        circle3.put("Brand 4", 421);  
        donutModel.addCircle(circle3);  
    }  
    
}
