package com.iesvn.yte.extendeddatamodel;

import java.util.List;

public interface BaseDataProvider<T> {
	
	//public BaseDataProvider getDataProvider();
		
	public void update();
	
	public boolean hasItemByPk(Integer pk);
	
	public T getItemByPk(Integer pk);
	
	public int getRowCount();
	
	public List<T> getItemsByrange(Integer startPk, int numberOfRows, String sortField, boolean ascending);

}


