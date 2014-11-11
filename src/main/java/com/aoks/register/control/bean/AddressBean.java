package com.aoks.register.control.bean;

import com.aoks.register.model.Address;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class AddressBean implements GenericBean<Address> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private Address model;
	
	private String city;
	private String completion;
	private String neighborhood;
	private String number;
	private String country;
	private String state;
	private String street;
	private String zipCode;
	private String addressType;
//	private List<CategoryBean> categories;
	
	public AddressBean() {
//		categories = new ArrayList<CategoryBean>();
	}
	
	@Override
	public Address build(GenericFactory<Address> factory) {
		return factory.create(this);
	}
	@Override
	public AddressBean load(Address model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.city = model.getCity();
		this.completion = model.getCompletion();
		this.id = model.getId();
		this.neighborhood = model.getNeighborhood();
		this.number = model.getNumber();
		this.country = model.getCountry();
		this.state = model.getState();
		this.street = model.getStreet();
		this.zipCode = model.getZipCode();
		this.id = model.getId();
		this.addressType = model.getAddressType();

//		Set<Category> cats = model.getCategorizedHelper().getCategories();
//		for (Category category : cats) {
//			this.addCategory(new CategoryBean().load(category));
//		}
		
		return this;
	}
	@Override
	public Address getModel() {
		return model;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public String getCompletion() {
		return completion;
	}
	public void setCompletion(String completion) {
		this.completion = completion;
	}
	
	
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
//	public List<CategoryBean> getCategories() {
//		return categories;
//	}
//	public void setCategories(List<CategoryBean> categories) {
//		this.categories = categories;
//	}
//	public void addCategory(CategoryBean bean){
//		if (categories.contains(bean)){
//			categories.remove(bean);
//        }
//		categories.add(bean);
//	}
//	public String getCategoriesResume(){
//		String ret = "";
//		for (CategoryBean cat : categories) {
//			ret = ret + cat.getName() + ", ";
//		}
//		if (ret.length() > 2)
//			ret = ret.substring(0, ret.length()-2);
//		return ret;
//	}
	
	public String getAddressType() { return addressType; }
	public void setAddressType(String addressType) { this.addressType = addressType;}
	

	@Override
	public long getId() {
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof AddressBean)) return false;
		AddressBean that = (AddressBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		return false;
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + (int)(id^(id>>>32));
		return result;
	}
	
	
}
