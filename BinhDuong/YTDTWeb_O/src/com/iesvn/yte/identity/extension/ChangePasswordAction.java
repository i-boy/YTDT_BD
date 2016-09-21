package com.iesvn.yte.identity.extension;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.NguoiDungDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("changepassword")
@Synchronized(timeout = 6000000)
public class ChangePasswordAction  implements Serializable {
	private static final long serialVersionUID = 10L;
	private static Logger log = Logger.getLogger(ChangePasswordAction.class);
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
//	private String username;
	private Integer nhanVienMaso;
	private String nhanVienMa;
	private String password;
	private String passwordConfirm;
	private String currentUserName;
	
	private String loaiNguoiDung;
	
	private String maNguoiDung;
	@Begin(join = true)
	public String init(String loaiNguoiDung) { // '' or admin
		log.info("-----init()-----");
		reset();
		log.info("identity.getUsername():"+identity.getUsername());
		this.loaiNguoiDung = loaiNguoiDung;
		if ("admin".equals(loaiNguoiDung)){
			nhanVienMaso = null;
			nhanVienMa = null;
			maNguoiDung = "admin";
			return "ChangePassword";
		}
		
		DtDmNhanVien nhanvien =  DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
		if (nhanvien == null){
			return "";
		}
		nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
		nhanVienMa = nhanvien.getDtdmnhanvienMa();
		
		maNguoiDung = nhanvien.getNdMaso(true).getNdTendangnhap();
		return "ChangePassword";
		
	}
	@End
	public void end() {
		
	}
	public String reset(){
		if ("admin".equals(loaiNguoiDung)){			
			maNguoiDung = "admin";		
		}
		password ="";
		passwordConfirm ="";
		return null;
	}
	
//	private NguoiDung ndTest = null;	
//	public void test1(){
//		if ("admin".equals(loaiNguoiDung)){
//			NguoiDungDelegate ndDelegate = NguoiDungDelegate.getInstance();
//			ndTest = ndDelegate.findByMa("admin");
//			System.out.println("ndTest.getNdMaso():"+ndTest.getNdMaso());
//		} 
//	}
//	public void test2(){
//		String tendangnhaptest = ndTest.getNdTendangnhap();
//		System.out.print("tendangnhaptest:"+tendangnhaptest);
//	}
	

	
//	public void displayInfor(){
//		log.info("-----displayInfor()-----");
//		username = nhanVienMa;
//		DtDmNhanVienDelegate delegate = DtDmNhanVienDelegate.getInstance();
//		DtDmNhanVien nhanvien = null;
//		if (nhanVienMaso != null) {
//			try {
//				nhanvien = delegate.find(nhanVienMaso);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		log.info("-----nhanvien: " + nhanvien);
//		NguoiDung nd = null;
//		if (nhanvien == null){
//			//ko tim thay nhan vien nay,
//			reset();
//			FacesMessages.instance().add(IConstantsRes.NHAN_VIEN_KHONG_TIM_THAY, username);
//		}else{
//			nd = nhanvien.getNdMaso();
//			if (nd != null) {
//				
//				username = nd.getNdTendangnhap();
//				currentUserName = username;
//				password = nd.getNdMadangnhap();
//			}
//		}
//		
//	}
	
	public String register(){
		log.info("-----register()-----");
		
        
//		DtDmNhanVienDelegate delegate = DtDmNhanVienDelegate.getInstance();
//		DtDmNhanVien nhanvien = null;
//		if (nhanVienMaso != null) {
//			try {
//				nhanvien = delegate.find(nhanVienMaso);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		log.info("-----nhanvien: " + nhanvien);
//		
//		if (nhanvien == null){
//			//ko tim thay nhan vien nay,
//			log.info("-----nhan vien is null");
//			reset();
//			FacesMessages.instance().add(IConstantsRes.NHAN_VIEN_KHONG_TIM_THAY, username);
//			return null;
//		} else {
		NguoiDung nd = null;
		if ("admin".equals(loaiNguoiDung)){
			NguoiDungDelegate ndDelegate = NguoiDungDelegate.getInstance();
			nd = ndDelegate.findByMa("admin");
		}else{
			DtDmNhanVien nhanvien =  DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
			if (nhanvien == null){
				return "";
			}
			nd = nhanvien.getNdMaso();
		}
		
			log.info("-----nhan vien is not null");
			log.info("-----maNguoiDung: " + maNguoiDung);
			log.info("-----password: " + password);
			
			NguoiDungDelegate ndDelegate = NguoiDungDelegate.getInstance();
			
			
//			try {
//				nd = ndDelegate.findByMa(username);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
//			if (nd == null) {
//				log.info("-----nguoi dung is null");
//				nd = new NguoiDung();
//				nd.setNdTendangnhap(username);
//				nd.setNdMadangnhap(password);
//				ndDelegate.createNguoiDung(nd, nhanvien);
//				reset();
//				FacesMessages.instance().add(IConstantsRes.REGISTER_SUCCESS);
//			} else {
				log.info("-----nguoi dung null");
				nd.setNdTendangnhap(maNguoiDung);
				nd.setNdMadangnhap(password);
				ndDelegate.edit(nd);
				reset();
				FacesMessages.instance().add(IConstantsRes.CHANGEPASS_SUCCESS);
				
//			}
//		}
		
		return null;
	}



	public Integer getNhanVienMaso() {
		return nhanVienMaso;
	}

	public void setNhanVienMaso(Integer nhanVienMaso) {
		this.nhanVienMaso = nhanVienMaso;
	}

	public String getNhanVienMa() {
		return nhanVienMa;
	}

	public void setNhanVienMa(String nhanVienMa) {
		this.nhanVienMa = nhanVienMa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public String getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(String maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

}
