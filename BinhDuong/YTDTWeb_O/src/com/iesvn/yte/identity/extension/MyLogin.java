package com.iesvn.yte.identity.extension;

import java.io.Serializable;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.entity.VaiTro;
import com.iesvn.yte.util.Utils;

@Scope(ScopeType.SESSION)
@Name("mylogin")
public class MyLogin  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Logger
	private Log log;

	@In(required = false)
	@Out(required = false)
	Identity identity;
	

	public Boolean myAuthenticate() {
		try {
			
			log.info("*** myLogin *** Username: " + identity.getUsername());
			
			DieuTriUtilDelegate delegate  = DieuTriUtilDelegate.getInstance();
			
			NguoiDung nguoidung = (NguoiDung) delegate.findByMa(identity.getUsername(),	"NguoiDung", "ndTendangnhap");
			//log.info("*** myLogin *** Nguoi dung: " + nguoidung);
			
			if (nguoidung == null) {
				return false;
			}

			if (nguoidung.getNdMadangnhap() == null) {
				if (!Utils.reFactorString(identity.getPassword()).equals("")) {
					return false;
				}
			} else if (!nguoidung.getNdMadangnhap().equals(identity.getPassword())) {
				return false;
			}

			List<VaiTro> lstVaiTro = delegate.getVaiTroByNguoiDungTenDangNhap(identity.getUsername());
			 
			if (lstVaiTro != null) {
				for (VaiTro vt : lstVaiTro) {
					
					identity.addRole(vt.getVaitroMa());
					//boolean role = identity.hasRole(vt.getVaitroMa());
				}
			}
		
			return true;

		} catch (Exception ex) {
			// FacesMessages.instance().add(
			// com.iesvn.yte.util.IConstantsRes.INVALID_LOGIN);
			//System.out.println("have no role NV_KhoaNoiTru");
			return false;
		}
	}


	public Log getLog() {
		return log;
	}


	public void setLog(Log log) {
		this.log = log;
	}


	public Identity getIdentity() {
		return identity;
	}


	public void setIdentity(Identity identity) {
		this.identity = identity;
	}




	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}


