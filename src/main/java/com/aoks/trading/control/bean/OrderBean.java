package com.aoks.trading.control.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.aoks.security.model.User;
import com.aoks.sells.integrations.chainit.controller.bean.ProductBean;
import com.aoks.sells.integrations.chainit.controller.bean.ProductBeanList;
import com.aoks.trading.model.Order;
import com.aoks.utils.webmvc.GenericBean;
import com.aoks.utils.webmvc.GenericFactory;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class OrderBean implements GenericBean<Order>{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String code;
	private String name;
	private String description;
	private BigDecimal price = new BigDecimal(0);
	private BigDecimal deliveryValue = new BigDecimal(0);
	private BigDecimal discount = new BigDecimal(0);
	private ProductBeanList products;
	private User seller;
	
	private BigDecimal amountReceived = new BigDecimal(0);
	private BigDecimal change = new BigDecimal(0);
	private String email;
	
	private Order model;
	
	@Override
	public GenericBean<Order> load(Order model) {
		
		if (model == null)
			throw new IllegalStateException();
		
		this.id = model.getId();
		this.code = model.getCode();
		this.name = model.getName();
		this.description = model.getDescription();
		this.price = model.getPrice();
		this.deliveryValue = model.getDeliveryValue();
		this.discount = model.getDiscount();
//		this.products = model.getProducts();
		this.seller = model.getSeller();
		
		return this;
	}

	@Override
	public Order build(GenericFactory<Order> factory) {
		
		if (model == null)
			model = new Order();
		
		model.setId(id);
		model.setCode(code);
		model.setName(name);
		model.setDescription(description);
		model.setPrice(price);
		model.setDeliveryValue(deliveryValue);
		model.setDiscount(discount);
//		model.setProducts(products);
		model.setSeller(seller);
		
		return model;
	}

	
	@Override public long getId() 								{ return id; }
	@Override public Order getModel() 							{ return model; }
	
	public void setId(long id) 									{ this.id = id; }
	
	public String getCode() 									{ return code; }
	public void setCode(String code) 							{ this.code = code; }
	
	public String getName() 									{ return name; }
	public void setName(String name) 							{ this.name = name; }
	
	public String getDescription() 								{ return description; }
	public void setDescription(String description) 				{ this.description = description; }
	
	public BigDecimal getPrice() 								{
		
		Double orderPrice = 0.0;
		for(ProductBean bean : products.getProductList())
			orderPrice += bean.getTotalPrice().doubleValue();
		
		orderPrice += deliveryValue.doubleValue();
		
		double total = orderPrice * (100 - discount.doubleValue()) / 100;
		
		String totalFormated = new DecimalFormat("0.00").format(total).replace(",", "."); 
		
		price = new BigDecimal(totalFormated);
		
		return price; 
	}
	
	public BigDecimal getDeliveryValue() 						{ return deliveryValue; }
	public void setDeliveryValue(BigDecimal deliveryValue) 		{ this.deliveryValue = deliveryValue; }
	
	public BigDecimal getDiscount() 							{ return discount; }
	public void setDiscount(BigDecimal discount) 				{ this.discount = discount; }
	
	public ProductBeanList getProducts() 						{ return products = (products == null) ? new ProductBeanList() : products; }
	public void setProducts(ProductBeanList products) 			{ this.products = products; }
	
	public User getSeller() 									{ return seller; }
	public void setSeller(User seller)							{ this.seller = seller; }

	public BigDecimal getAmountReceived() 						{ return amountReceived; }
	public void setAmountReceived(BigDecimal amountReceived) 	{ this.amountReceived = amountReceived; }

	public BigDecimal getChange() 								{ 
		change = price.subtract(amountReceived);
		return change; 
	}
	public void setChange(BigDecimal change) 					{ this.change = change; }

	public String getEmail() 									{ return email; }
	public void setEmail(String email) 							{ this.email = email; }

	@Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof OrderBean)) return false;
        OrderBean that = (OrderBean) obj;

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
