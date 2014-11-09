package com.aoks.budget.control.bean;

import java.math.BigDecimal;
import java.util.Calendar;

import com.aoks.banking.operation.control.bean.OperationAccountBean;
import com.aoks.budget.model.FlowItem;
import com.aoks.budget.model.FlowItemInfo;
import com.aoks.budget.model.FlowItemType;
import com.aoks.budget.model.InstalmentsInfo;
import com.aoks.budget.model.Periodicity;
import com.aoks.budget.model.RecurrentInfo;
import com.aoks.enterprise.control.bean.PartyBean;
import com.aoks.enterprise.model.entities.Party;
import com.aoks.utils.category.control.bean.CategoryBean;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class FlowItemBean implements GenericBean<FlowItem>, Comparable<FlowItemBean>{

	private static final long serialVersionUID = 1L;

	private FlowItem model;
	private long id;
	
	private String name;
	private String description;
	private String type;
	private String amount;
	private Calendar start;
	private OperationAccountBean<?> origin;
	private OperationAccountBean<?> target;
	private boolean transfer;
	private String periodicity;
	private Calendar end;
	private String maturityDay;
	private String instalments;
	private boolean credit;
	private boolean operateSaturday;
	private boolean operateSunday;

	private CategoryBean category;
	private CategoryBean subCategory;
	private PartyBean payee;
	

	public boolean isRecurrentType() {
		try {
			FlowItemType t = FlowItemType.valueOf(type);
			if (t == FlowItemType.RECURRENT) return true;
		} catch (Exception e) {}
		return false;
	}
	public boolean isInstalmentsType(){
		try {
			FlowItemType t = FlowItemType.valueOf(type);
			if (t == FlowItemType.INSTALMENTS) return true;
		} catch (Exception e) {}
		return false;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getCategoryDescription(){
		return (category != null ? category.getName() : "");
	}
	public CategoryBean getCategory() {
		return category;
	}
	public void setCategory(CategoryBean category) {
		this.category = category;
	}
	
	
	public String getSubCategoryDescription(){
		return (subCategory != null ? subCategory.getName() : "");
	}
	public CategoryBean getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(CategoryBean subCategory) {
		this.subCategory = subCategory;
	}
	
	
	public String getPayeeDescription(){
		return (payee != null ? payee.getName() : "");
	}
	public PartyBean getPayee() {
		return payee;
	}
	public void setPayee(PartyBean payee) {
		this.payee = payee;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	public void setStart(Calendar start) {
		this.start = start;
	}
	public Calendar getStart() {
		return start;
	}
	
	
	public void setOrigin(OperationAccountBean<?> origin) {
		this.origin = origin;
	}
	public OperationAccountBean<?> getOrigin() {
		return origin;
	}
	
	
	public void setTarget(OperationAccountBean<?> target) {
		this.target = target;
	}
	public OperationAccountBean<?> getTarget() {
		return target;
	}
	

	public boolean isTransfer() {
		return transfer;
	}
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	
	
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	
	
	public Calendar getEnd() {
		return end;
	}
	public void setEnd(Calendar end) {
		this.end = end;
	}
	
	
	public String getMaturityDay() {
		return maturityDay;
	}
	public void setMaturityDay(String maturityDay) {
		this.maturityDay = maturityDay;
	}
	
	
	public String getInstalments() {
		return instalments;
	}
	public void setInstalments(String instalments) {
		this.instalments = instalments;
	}

	
	public boolean isCredit() {
		return credit;
	}
	public void setCredit(boolean credit) {
		this.credit = credit;
	}
	
	
	public boolean isOperateSunday() {
		return operateSunday;
	}
	public void setOperateSunday(boolean operateSunday) {
		this.operateSunday = operateSunday;
	}
	
	
	public boolean isOperateSaturday() {
		return operateSaturday;
	}
	public void setOperateSaturday(boolean operateSaturday) {
		this.operateSaturday = operateSaturday;
	}
	
	
	@Override
	public long getId() {
		return id;
	}
	@Override
	public FlowItem build(GenericFactory<FlowItem> factory) {
		
		if (model == null)
			model = new FlowItem();
		
		model.setName(name);
		model.setAmount(new BigDecimal(amount.replace(",", "")));
		model.setCredit(credit);
		model.setDescription(description);
		model.setTransfer(transfer);
		
		if (origin != null)
			model.setOrigin(origin.getModel());
		
		if (target != null)
			model.setTarget(target.getModel());
		
		if (category != null)
			model.setCategory(category.getModel());
		
		if (subCategory != null)
			model.setSubCategory(subCategory.getModel());
		
		if (payee != null)
			model.setPayee(payee.getModel());
		
		try {
			model.setType(FlowItemType.valueOf(type));
		} catch (Exception e) {}
		
		FlowItemType fit = model.getType();
		FlowItemInfo info = model.getInfo();
		
		switch (fit) {
			
			case INSTALMENTS:
				
				if (info == null || !(info instanceof InstalmentsInfo)){
					info = new InstalmentsInfo();
					model.setInfo(info);
				}
				
				((InstalmentsInfo)info).setInstalments(Integer.valueOf(instalments));
				
				break;
			
			case RECURRENT:
				
				if (info == null || !(info instanceof RecurrentInfo)){
					info = new RecurrentInfo();
					model.setInfo(info);
				}
				
				((RecurrentInfo)info).setEnd(end);
				
				break;
			
			case TRANSFER:
				break;

		}
	
		if (info != null){
			
			if(maturityDay != null)
				info.setMaturityDay(Integer.valueOf(maturityDay));
			
			info.setOperateSaturday(operateSaturday);
			info.setOperateSunday(operateSunday);
			
			try {
				
				if(periodicity != null)
					info.setPeriodicity(Periodicity.valueOf(periodicity));
			
			} catch (Exception e) {}
			
			info.setStart(start);
		}
		
		return model;
	}
	@Override
	public FlowItem getModel() {
		return model;
	}
	@Override
	public FlowItemBean load(FlowItem model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		this.id = model.getId();
		
		this.name = model.getName();
		this.amount = model.getAmount().toString();
		this.credit = model.isCredit();
		this.description = model.getDescription();
		this.transfer = model.isTransfer();
		this.type = model.getType().name();
		this.origin = OperationAccountBean.create(model.getOrigin());
		this.target = OperationAccountBean.create(model.getTarget());
		
		Category c = model.getCategory();
		if(c != null)	{ this.category = new CategoryBean().load(c); }
		
		Category sc = model.getSubCategory();
		if(sc != null)	{ this.subCategory = new CategoryBean().load(sc); }
		
		Party py = model.getPayee();
		if(py != null){
			this.payee = new PartyBean().load(py);
		}
		
		FlowItemInfo info = model.getInfo();
		
		if (info != null){
			
			this.maturityDay = String.valueOf(info.getMaturityDay());
			this.operateSaturday = info.isOperateSaturday();
			this.operateSunday = info.isOperateSunday();
			this.periodicity = (info.getPeriodicity() != null ? info.getPeriodicity().name() : "");
			this.start = info.getStart();
			
			if (info instanceof InstalmentsInfo)
				this.instalments = String.valueOf(((InstalmentsInfo)info).getInstalments());
			else if (info instanceof RecurrentInfo)
				this.end = ((RecurrentInfo)info).getEnd();
			
		}
		
		return this;
	}
	
	
	@Override
	public int compareTo(FlowItemBean _bean){
		return name.compareTo(_bean.name);
	}

}
