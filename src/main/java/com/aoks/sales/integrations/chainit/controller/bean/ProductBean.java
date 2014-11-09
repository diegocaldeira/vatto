package com.aoks.sales.integrations.chainit.controller.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.aoks.sales.integrations.chainit.model.Product;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class ProductBean implements GenericBean<Product>{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore 				  		private long id;
	@JsonProperty("id")			  		private String key;
	@JsonProperty("code") 		  		private String code;
	@JsonProperty("name")		  		private String name;
	@JsonProperty("description")  		private String description;
	@JsonIgnore 				  		private long quantity;
	@JsonProperty("unitPriceAvg") 		private BigDecimal price 	= new BigDecimal(0);
	@JsonIgnore				 			private BigDecimal discount = new BigDecimal(0);
	@JsonProperty("unityMeasureType")	private String unityMeasureType;
	@JsonProperty("unityMeasureValue")	private String unityMeasureValue;
	
	
	@JsonIgnore 				  private BigDecimal totalPrice;
	
	private Product model;


	@Override
	public GenericBean<Product> load(Product model) {
		
		if (model == null)
			throw new IllegalStateException();

		this.id = model.getId();
		this.code = model.getCode();
		this.name = model.getName();
		this.description = model.getDescription();
		this.price = model.getPrice();
		this.discount = model.getDiscount();
		
		return this;
	}

	@Override
	public Product build(GenericFactory<Product> factory) {
	
		if (model == null)
			model = new Product();
		
		model.setId(id);
		model.setCode(code);
		model.setName(name);
		model.setDescription(description);
		model.setPrice(price);
		model.setDiscount(discount);
		
		return model;
	}

	
	@Override public long getId() 								{ return id; }
	@Override public Product getModel() 						{ return model; }
	
	public String getKey() 										{ return key; }
	public void setKey(String key) 								{ this.key = key; }

	public String getCode() 									{ return code; }
	public void setCode(String code) 							{ this.code = code; }

	public String getName() 									{ return name; }
	public void setName(String name) 							{ this.name = name; }

	public String getDescription() 								{ return description; }
	public void setDescription(String description)				{ this.description = description; }

	public long getQuantity() 									{ return quantity; }
	public void setQuantity(long quantity) 						{ this.quantity = quantity; }

	public BigDecimal getPrice() 								{ return price; }
	public void setPrice(BigDecimal price)						{ this.price = price; }
	
	public BigDecimal getDiscount() 							{ return discount = (discount == null) ? new BigDecimal(0) : discount; }
	public void setDiscount(BigDecimal discount) 				{ this.discount = discount; }

	public String getUnityMeasureType() 					 	{ return unityMeasureType; }
	public void setUnityMeasureType(String unityMeasureType) 	{ this.unityMeasureType = unityMeasureType; }

	public String getUnityMeasureValue() 						{ return unityMeasureValue; }
	public void setUnityMeasureValue(String unityMeasureValue)  { this.unityMeasureValue = unityMeasureValue; }

	
	public BigDecimal getTotalPrice() {
		totalPrice = price.multiply(new BigDecimal(quantity));
		
		double total = totalPrice.doubleValue() * (100 - discount.doubleValue()) / 100;
		
		String priceFormated = new DecimalFormat("0.00").format(total).replace(",", "."); 
		
		totalPrice = new BigDecimal(priceFormated);
		
		return totalPrice;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ProductBean)) return false;
        ProductBean that = (ProductBean) obj;

        if (this.getId() > 0 && this.getId() == that.getId())
        	return true;

        return false;
    }

    @Override
    public int hashCode() {
        int result = 13;
        if (getId() > 0)
        	result = result * 31 + (int)(getId()^(getId()>>>32));
        return result;
    }
	
}
