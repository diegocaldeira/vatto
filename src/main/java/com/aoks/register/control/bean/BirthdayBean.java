package com.aoks.register.control.bean;

import com.aoks.register.model.Birthday;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 *
 */
public class BirthdayBean implements GenericBean<Birthday> {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String name;
	private Integer age;
	private String category;
	private String date;
	
	private Birthday model;
	
	public BirthdayBean() {
	}

	
	@Override
	public Birthday getModel() {
		return model;
	}
	@Override
	public BirthdayBean load(Birthday model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.model = model;
		
		this.id = model.getId();
		
		return this;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
	public void setModel(Birthday model) {
		this.model = model;
	}

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof BirthdayBean)) return false;
		BirthdayBean that = (BirthdayBean) obj;
		if (this.id > 0 && this.id == that.id)
			return true;
		else if ((this.name == null ? that.name == null : this.name.equals(that.name)))
				return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		if (id > 0)
			result = result * 31 + (int)(id^(id>>>32));
		else {
			result = result + 31 * (name != null ? name.hashCode() : 0);
		}
		return result;
	}

	@Override
	public Birthday build(GenericFactory<Birthday> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
