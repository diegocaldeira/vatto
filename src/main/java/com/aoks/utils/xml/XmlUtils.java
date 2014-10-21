/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aoks.utils.xml;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
 
/**
 * 
 * @author Diego Pereira
 * 
 */
public class XmlUtils {

//	final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
	
	
    @SuppressWarnings("unchecked")
	public static <T>  T fromXml(File xmlAsFile, Class<T> pojoClass) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(pojoClass);
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(xmlAsFile);
        } catch (JAXBException e) {
//            logger.error("", e);
        }

        
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
   	public static <T>  T fromXml(InputStream xmlAsStream, Class<T> pojoClass) throws JAXBException{
           JAXBContext context = JAXBContext.newInstance(pojoClass);
           try {
               Unmarshaller unmarshaller = context.createUnmarshaller();
               return (T) unmarshaller.unmarshal(xmlAsStream);
           } catch (JAXBException e) {
//               logger.error("", e);
           }

           
           
           return null;
       }

}
