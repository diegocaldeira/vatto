package com.aoks.register.control.bean;

import com.aoks.register.model.Phone;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

public class PhoneBean implements GenericBean<Phone>{

	private static final long serialVersionUID = 1L;

	private long id;
	
	private Phone model;
	
	private String countryCode;
	private String areaCode;
	private String number;
	private String extension;
	
	private String phoneNumber;
	
	public PhoneBean() {}
	public PhoneBean(Phone model) {
		this.model = model;
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	@Override
	public Phone build(GenericFactory<Phone> factory) {
		
		if (model == null)
			model = new Phone();
		
//		if (phoneNumber != null && phoneNumber.length() == 14){
//			
//			areaCode = phoneNumber.substring(1, 3);
//			number = (phoneNumber.substring(5, 9) + phoneNumber.substring(10, 14));
//			
//		}
		
		model.setAreaCode(areaCode);
		model.setCountryCode(countryCode);
		model.setExtension(extension);
		model.setNumber(phoneNumber);
		
		return model;
	}
	@Override
	public PhoneBean load(Phone model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.id = model.getId();
		
		this.areaCode = model.getAreaCode();
		this.countryCode = model.getCountryCode();
		this.extension = model.getExtension();
		this.number = model.getNumber();
		
		this.phoneNumber = model.getNumber();
		
//		if (areaCode != null && number != null && number.length() == 8)
//			this.phoneNumber = "(" + areaCode + ") " + number.substring(0,4) + "-" + number.substring(4, 8);
		
		return this;
	}
	
	
	public Phone getModel() {
		return model;
	}
	
	
	@Override
	public long getId() {
		return id;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof PhoneBean)) return false;
		PhoneBean that = (PhoneBean) obj;
		if (this.id > 0 && this.id == that.id)
    		return true;
    	else if ((this.areaCode == null ? that.areaCode == null : this.areaCode.equals(that.areaCode)) &&
    			(this.number == null ? that.number == null : this.number.equals(that.number)) &&
    			(this.extension == null ? that.extension == null : this.extension.equals(that.extension)))
    		return true;
    	return false;
    }
    
    @Override
    public int hashCode() {
    	int result = 17;
    	if (id > 0)
    		result = result * 31 + (int)(id^(id>>>32));
    	else {
    		result = result * 31 + (areaCode != null ? areaCode.hashCode() : 0);
    		result = result * 31 + (number != null ? number.hashCode() : 0);
    		result = result * 31 + (extension != null ? extension.hashCode() : 0);
    	}
    	return result;
    }

}
