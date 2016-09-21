package com.iesvn.yte.util;

import java.util.AbstractList;
import java.util.List;

import com.iesvn.yte.dieutri.entity.KiemKeKho;

public final class PagedList extends AbstractList<KiemKeKho>{
	private final List<KiemKeKho> list; 
	private final int total;
	private final int pageLimit;
	
	public PagedList(List<KiemKeKho> list, int total, int pageLimit) {  
		this.list = list;  
		this.total = total;  
		this.pageLimit = pageLimit;  
	}  

	public int size() {  
		return total;  
	}  

	public KiemKeKho get(int i) {  
		i = i % this.pageLimit;  
		return list.get(i);  
	} 
}
