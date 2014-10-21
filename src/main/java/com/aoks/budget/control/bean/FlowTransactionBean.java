package com.aoks.budget.control.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.aoks.banking.account.model.Entry;
import com.aoks.banking.operation.control.bean.OperationAccountBean;
import com.aoks.banking.operation.control.bean.OperationEntryBean;
import com.aoks.banking.operation.model.OperationAccount;
import com.aoks.banking.operation.model.OperationEntry;
import com.aoks.budget.model.FlowStatus;
import com.aoks.budget.model.FlowTransaction;
import com.aoks.enterprise.control.bean.PartyBean;
import com.aoks.enterprise.model.entities.Party;
import com.aoks.utils.category.control.bean.CategoryBean;
import com.aoks.utils.category.model.Category;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class FlowTransactionBean implements GenericBean<FlowTransaction>, Comparable<FlowTransactionBean> {

	private static final long serialVersionUID = 1L;
	
	private FlowTransaction model;
	
	private long id;
	private List<OperationEntryBean> entries;
	private Calendar ocurrence;
	private String status;
	private BigDecimal amount;
	private int number;
	private String description;
	private boolean credit;
	private OperationAccountBean<?> account;

	private CategoryBean category;
	private CategoryBean subCategory;
	private PartyBean payee;

	
	public FlowTransactionBean() {
		entries = new ArrayList<OperationEntryBean>();
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
	
	
	public List<OperationEntryBean> getEntries() {
		return entries;
	}
	public void setEntries(List<OperationEntryBean> entries) {
		this.entries = entries;
	}
	
	
	public Calendar getOcurrence() {
		return ocurrence;
	}
	public void setOcurrence(Calendar ocurrence) {
		this.ocurrence = ocurrence;
	}
	
	
	public String getStatusDescription(){
		return (status == null ? "" : status.equalsIgnoreCase("OPEN") ? "Aberto" : 
				status.equalsIgnoreCase("CLOSED") ? "Fechado" : status.equalsIgnoreCase("CANCELED") ? "Cancelado" : ""); 
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getOperationDescription(){
		return (credit ? "Crédito" : "Débito"); 
	}
	public String getOperation(){
		return (credit ? "CREDIT" : "DEBIT");
	}
	public void setOperation(String operation){
		// do nothing
	}
	
	
	public boolean isCredit() {
		return credit;
	}
	public void setCredit(boolean credit) {
		this.credit = credit;
	}
	
	
	public String getAccountDescription(){
		return (account != null ? account.getDescription() : "");
	}
	public OperationAccountBean<?> getAccount() {
		return account;
	}
	public void setAccount(OperationAccountBean<?> account) {
		this.account = account;
	}
	
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public FlowTransaction build(GenericFactory<FlowTransaction> factory) {
		
		if (model == null)
			model = new FlowTransaction();
		
		if (account != null)
			model.setAccount(account.getModel());
		
		model.setAmount(amount);
		model.setCredit(credit);
		model.setDescription(description);
		model.setNumber(number);
		model.setOcurrence(ocurrence);
		
		if (category != null)
			model.setCategory(category.getModel());
		
		if (subCategory != null)
			model.setSubCategory(subCategory.getModel());
		
		if (payee != null)
			model.setPayee(payee.getModel());
		
		try {
			model.setStatus(FlowStatus.valueOf(status));
		} catch (Exception e) {}

		return model;
	}

	@Override
	public FlowTransaction getModel() {
		return model;
	}

	@Override
	public GenericBean<FlowTransaction> load(FlowTransaction model) {
		
		if (model == null)
			throw new IllegalArgumentException();

		this.model = model;
		this.id = model.getId();
		
		this.amount = model.getAmount();
		this.credit = model.isCredit();
		this.description = model.getDescription();
		this.number = model.getNumber();
		this.ocurrence = model.getOcurrence();
		this.status = (model.getStatus() != null ? model.getStatus().name() : "");
		
		OperationAccount ac = model.getAccount();
		if (ac != null)
			this.account = OperationAccountBean.create(model.getAccount());

		List<Entry> ens = model.getTxAccount().getEntries();
		for (Entry entry : ens) {
			this.entries.add((OperationEntryBean) new OperationEntryBean().load((OperationEntry) entry));
		}
		
		Category c = model.getCategory();
		if(c != null){
			this.category = new CategoryBean().load(c);
		}
		Category sc = model.getSubCategory();
		if(sc != null){
			this.subCategory = new CategoryBean().load(sc);
		}
		
		Party py = model.getPayee();
		if(py != null){
			this.payee = new PartyBean().load(py);
		}
		
		return this;
	}
	
	
	@Override
	public int compareTo(FlowTransactionBean _bean){
		return description.compareTo(_bean.description);
	}

}
