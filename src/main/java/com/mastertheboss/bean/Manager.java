package com.mastertheboss.bean;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
 
import javax.inject.Inject;
import javax.inject.Named;

 
import com.mastertheboss.ejb.ServiceBean;
import com.mastertheboss.model.SimpleProperty;
import com.mastertheboss.producer.Producer;

@Model
public class Manager {
  
	@Inject
	ServiceBean ejb;
	
	@Produces
	@Named
	SimpleProperty property;
	
	@Inject
	Producer producer;
	
	@PostConstruct
	public void initNewProperty() {
                // Taken from configuration
                SimpleProperty configProperty = new SimpleProperty();
                configProperty.setKey("greeting");
                configProperty.setValue(System.getProperty("greeting"));
                ejb.put(configProperty);
       
		property = new SimpleProperty();
	}
	 
	public void save() {
		ejb.put(property);
		System.out.println("saved "+property);
		initNewProperty();
	}

	public void clear(SimpleProperty property) {
		ejb.delete(property);
		 
	}
 

}
