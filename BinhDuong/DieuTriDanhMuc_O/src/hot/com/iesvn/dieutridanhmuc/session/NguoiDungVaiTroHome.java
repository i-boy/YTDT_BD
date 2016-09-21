package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("nguoiDungVaiTroHome")
public class NguoiDungVaiTroHome extends EntityHome<NguoiDungVaiTro> {

	@In(create = true)
	NguoiDungHome nguoiDungHome;
	@In(create = true)
	VaiTroHome vaiTroHome;

	public void setNguoiDungVaiTroNdvaitroMaso(Integer id) {
		setId(id);
	}

	public Integer getNguoiDungVaiTroNdvaitroMaso() {
		return (Integer) getId();
	}

	@Override
	protected NguoiDungVaiTro createInstance() {
		NguoiDungVaiTro nguoiDungVaiTro = new NguoiDungVaiTro();
		return nguoiDungVaiTro;
	}

	public void wire() {
		getInstance();
		NguoiDung nguoiDung = nguoiDungHome.getDefinedInstance();
		if (nguoiDung != null) {
			getInstance().setNguoiDung(nguoiDung);
		}
		VaiTro vaiTro = vaiTroHome.getDefinedInstance();
		if (vaiTro != null) {
			getInstance().setVaiTro(vaiTro);
		}
	}

	public boolean isWired() {
		if (getInstance().getNguoiDung() == null)
			return false;
		if (getInstance().getVaiTro() == null)
			return false;
		return true;
	}

	public NguoiDungVaiTro getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
