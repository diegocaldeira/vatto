package com.aoks.budget.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.event.ToggleEvent;

import com.aoks.budget.control.bean.FlowItemBean;
import com.aoks.budget.control.bean.FlowTransactionBean;
import com.aoks.budget.control.bean.dataModel.FlowTransactionDataModel;
import com.aoks.budget.model.FlowTransaction;

/**
 * 
 * @author Diego Pereira
 *
 */
@Named("flowItemPreviewController")
@RequestScoped
public class FlowItemPreviewController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private FlowTransactionDataModel dataModel;

	private List<FlowTransaction> itens;
	private FlowItemBean item;
	
	public FlowItemPreviewController(){
		dataModel = new FlowTransactionDataModel(new ArrayList<FlowTransactionBean>());
	}
	
	/**
	 * 
	 */
	private void makeItens(){
		if (item == null)
			return;

		itens = item.getModel().generateTransactions();
		for(FlowTransaction transaction : itens){
			dataModel.getFilteredBeans().add((FlowTransactionBean) new FlowTransactionBean().load(transaction));
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void onRowToggle(ToggleEvent event) {  
       item = (FlowItemBean) event.getData();  
       makeItens();
    }  

	
	/**
	 * 
	 * @return
	 */
	public FlowTransactionDataModel getDataModel(){ 
		return dataModel; 
	}
}
