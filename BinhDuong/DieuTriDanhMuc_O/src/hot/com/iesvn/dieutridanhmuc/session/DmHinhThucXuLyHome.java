package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmHinhThucXuLyHome")
public class DmHinhThucXuLyHome extends EntityHome<DmHinhThucXuLy> {

	public void setDmHinhThucXuLyDmhinhthucxulyMaso(Integer id) {
		setId(id);
	}

	public Integer getDmHinhThucXuLyDmhinhthucxulyMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmHinhThucXuLy createInstance() {
		DmHinhThucXuLy dmHinhThucXuLy = new DmHinhThucXuLy();
		return dmHinhThucXuLy;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmHinhThucXuLy getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
