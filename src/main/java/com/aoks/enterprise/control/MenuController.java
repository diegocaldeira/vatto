package com.aoks.enterprise.control;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("enterpriseMenuCTL")
@SessionScoped
public class MenuController implements Serializable{

	private static final long serialVersionUID = 1L;

}

enum MenuType {
	COMPANY, COSTUMER;
}
