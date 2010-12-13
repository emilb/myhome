package se.greyzone.myhome.dao;

import org.springframework.beans.factory.FactoryBean;

import com.google.code.morphia.Morphia;

public class MorphiaFactoryBean implements FactoryBean<Morphia> {

	private Morphia morphia;
	private String mappedPackage;
	
	@Override
	public Morphia getObject() throws Exception {
		
		// This is not entirely thread safe.
		if (morphia != null)
			return morphia;
		
		morphia = new Morphia();
		morphia.mapPackage(mappedPackage);
		return morphia;
	}

	@Override
	public Class<?> getObjectType() {
		return Morphia.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setMappedPackage(String mappedPackage) {
		this.mappedPackage = mappedPackage;
	}
}
