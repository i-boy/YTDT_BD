/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3247_CapNhatVienPhiNoiTru")
@Scope(CONVERSATION)
public class B3247_CapNhatVienPhiNoiTru implements Serializable {
	@Logger
	private Log log;
			
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private String ngayhientai;
					
	public String init() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
		return "/B3_Vienphi/ThuVienPhi/B3247_CapNhatVienPhiNoiTru.xhtml";
	}
	@End
	public void endFunc(){
		
	}
				
	/**
	 * Ham set gia tri cac field
	 */
	public void resetForm(){						
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		
		thoigian_thang=String.valueOf(currentDate.getMonth() +1);  
		thoigian_nam=String.valueOf(currentDate.getYear()+1900);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);				
		tungay = sdf.format(cal.getTime());
		denngay = sdf.format(currentDate);
		FacesMessages.instance().clear();
	}			
	public void thuchienAction() {
		log.info("Begin thuchienAction");		
		Calendar myCal = Calendar.getInstance();
		myCal.set(2011, 11, 15, 0, 0, 0);  // ngay 15/10/2011
		Date dateUpdate = myCal.getTime();
		//log.info("dateUpdate = " + dateUpdate.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tungayTmp = tungay.substring(6) + "-" + tungay.substring(3,5) + "-" + tungay.substring(0,2);
		String denngayTmp = denngay.substring(6) + "-" + denngay.substring(3,5) + "-" + denngay.substring(0,2);
		//log.info("tungayTmp = " + tungayTmp + ", denngayTmp = " + denngayTmp);
		
		try {
			Date dTungay = sdf.parse(tungayTmp);
			Date dDenngay = sdf.parse(denngayTmp);
			//ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();
			// Set null field ThuocphongkhamDatt theo thoi gian tiep don
			//int updateTpk = tpkDel.updateDaTT2Null(dTungay, dDenngay);
			//log.info("after updateDaTT2Null, updateTpk = " + updateTpk);
			
			HsThtoanDelegate hsDel = HsThtoanDelegate.getInstance();
			
			//List<HsThtoan> listHs = hsDel.findByNgayVaoVien(dTungay, dDenngay);			
			List<HsThtoan> listHs = hsDel.findByNgayRaVien(dTungay, dDenngay);
			log.info("listHs.size() = " + listHs.size());
			String hsttMa = "";
			for(HsThtoan eachHs : listHs) {				
				Hsba hsba = eachHs.getHsbaSovaovien();
				hsttMa = eachHs.getHsthtoanMa();
				HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
				hsthtoanUtil.tinhToanChoHSTT(eachHs);
				Utils.setInforForHsThToan(eachHs);
				log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				log.info("So vao vien = " + eachHs.getHsbaSovaovien());
				log.info("Tong chi phi = " + eachHs.getHsthtoanTongchi());
				log.info("Benh nhan Tra = " + eachHs.getHsthtoanBntra());
				log.info("Benh nhan Tra = " + eachHs.getHsthtoanBhyt());
				log.info("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				eachHs.setHsthtoanMa(hsttMa);
				hsDel.edit(eachHs);
				//hsDel.edit(hsThtoan)	
				
			}
			
			FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
		} catch (Exception ex) {
			FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
			ex.printStackTrace();
		}
		log.info("End thuchienAction");
	}
	public String getThoigian_thang() {
		return thoigian_thang;
	}
	public void setThoigian_thang(String thoigian_thang) {
		this.thoigian_thang = thoigian_thang;
	}
	public String getThoigian_nam() {
		return thoigian_nam;
	}
	public void setThoigian_nam(String thoigian_nam) {
		this.thoigian_nam = thoigian_nam;
	}
	public String getTungay() {
		return tungay;
	}
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	public String getDenngay() {
		return denngay;
	}
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	public void setNgayhientai(String ngayhientai) {
		this.ngayhientai = ngayhientai;
	}
	public String getNgayhientai() {
		return ngayhientai;
	}

}
