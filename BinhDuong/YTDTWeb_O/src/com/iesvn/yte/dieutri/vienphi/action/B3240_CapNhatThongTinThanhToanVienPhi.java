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
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Name("B3240_CapNhatThongTinThanhToanVienPhi")
@Scope(CONVERSATION)
public class B3240_CapNhatThongTinThanhToanVienPhi implements Serializable {
	@Logger
	private Log log;
			
	private String thoigian_thang=null;  
	private String thoigian_nam=null;
	private String tungay=null;
	private String denngay=null;
	private int loaicapnhat = 1;
	private String ngayhientai;
					
	public String init() {
		log.info("init()");
		ngayhientai = Utils.getCurrentDate();
		resetForm();
		return "/B3_Vienphi/ThuVienPhi/B3240_CapNhatThongTinThanhToanVienPhi.xhtml";
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
		loaicapnhat = 1;
		FacesMessages.instance().clear();
	}
	public void thuchienCapnhatAction() {
		if (loaicapnhat == 0) {
			thuchienAction();
		} else {
			thuchien2Action();
		}
	}
	public void thuchienAction() {
		log.info("Begin thuchienAction");		
		Calendar myCal = Calendar.getInstance();
		myCal.set(2011, 11, 1, 0, 0, 0);  // ngay 01/10/2011
		Date dateUpdate = myCal.getTime();
		//log.info("dateUpdate = " + dateUpdate.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tungayTmp = tungay.substring(6) + "-" + tungay.substring(3,5) + "-" + tungay.substring(0,2);
		String denngayTmp = denngay.substring(6) + "-" + denngay.substring(3,5) + "-" + denngay.substring(0,2);
		//log.info("tungayTmp = " + tungayTmp + ", denngayTmp = " + denngayTmp);
		
		try {
			Date dTungay = sdf.parse(tungayTmp);
			Date dDenngay = sdf.parse(denngayTmp);
			ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();
			// Set null field ThuocphongkhamDatt theo thoi gian tiep don
			int updateTpk = tpkDel.updateDaTT2Null(dTungay, dDenngay);
			log.info("after updateDaTT2Null, updateTpk = " + updateTpk);
			
			HsThtoankDelegate hsDel = HsThtoankDelegate.getInstance();
			// Set null field hsthtoank_ngaygiott va hsthtoank_datt
			int updateHsttk = hsDel.updateNgayGioTTAndDaTT2Null(dTungay, dDenngay);
			log.info("after updateNgayGioTTAndDaTT2Null, updateHsttk = " + updateHsttk);
			List<HsThtoank> listHs = hsDel.findByNgayTiepdon(dTungay, dDenngay);			
			//log.info("listHs.size() = " + listHs.size());				
			for(HsThtoank eachHs : listHs) {
				if(eachHs.getHsthtoankNgaygiott() == null) {
					eachHs.setHsthtoankNgaygiott(eachHs.getTiepdonMa().getTiepdonNgaygio());
					eachHs.setHsthtoankDatt(true);
					if(eachHs.getTiepdonMa().getTiepdonNgaygio().before(dateUpdate)) { // Neu tiep don truoc ngay 01/10/2011, thi cap nhat cac so lieu cho ho so thanh toan					
						HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(eachHs.getTiepdonMa());					
						hsthtoankUtilTmp.tinhToanChoHSTKKham(eachHs);
						
					} else { // Neu tiep don sau ngay 01/10/2011, thi chi cap nhat ngay gio thanh toan
						
						hsDel.edit(eachHs);
					}
				}
			}
			
			FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
		} catch (Exception ex) {
			FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
			ex.printStackTrace();
		}
		log.info("End thuchienAction");
	}
	public void thuchien2Action() {
		log.info("Begin thuchien2Action");		
		Calendar myCal = Calendar.getInstance();
		myCal.set(2011, 11, 1, 0, 0, 0);  // ngay 01/10/2011
		Date dateUpdate = myCal.getTime();
		//log.info("dateUpdate = " + dateUpdate.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tungayTmp = tungay.substring(6) + "-" + tungay.substring(3,5) + "-" + tungay.substring(0,2);
		String denngayTmp = denngay.substring(6) + "-" + denngay.substring(3,5) + "-" + denngay.substring(0,2);
		//log.info("tungayTmp = " + tungayTmp + ", denngayTmp = " + denngayTmp);
		
		try {
			Date dTungay = sdf.parse(tungayTmp);
			Date dDenngay = sdf.parse(denngayTmp);
			ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();
			// Set null field ThuocphongkhamDatt theo thoi gian tiep don
			int updateTpk = tpkDel.updateDaTT2Null(dTungay, dDenngay);
			log.info("after updateDaTT2Null, updateTpk = " + updateTpk);
			
			HsThtoankDelegate hsDel = HsThtoankDelegate.getInstance();
			// Lay danh sach ho so da thanh toan theo thoi gian tiep don da chon
			List<HsThtoank> listHs = hsDel.findByNgayTiepdonAndDaTT(dTungay, dDenngay);			
			log.info("listHs.size() = " + listHs.size());
			String maHs = "";
			TiepDon td = new TiepDon(); 
			HsThtoank hosoTTK = new HsThtoank();
			for(HsThtoank eachHs : listHs) {
				log.info("ma HS = " + eachHs.getHsthtoankMa() + ", maTD = " + eachHs.getTiepdonMa());
				if(eachHs.getHsthtoankNgaygiott() != null) {	
					maHs = eachHs.getHsthtoankMa();
					td = eachHs.getTiepdonMa();
					HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(eachHs.getTiepdonMa());					
					hsthtoankUtilTmp.tinhToanChoHSTKKham(eachHs);
					hosoTTK = hsthtoankUtilTmp.getHosoTTK();
					hosoTTK.setHsthtoankMa(maHs);
					hosoTTK.setTiepdonMa(td);
					hsDel.edit(hosoTTK);
				}
			}
			
			FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS);
		} catch (Exception ex) {
			FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL);
			ex.printStackTrace();
		}
		log.info("End thuchien2Action");
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
	public int getLoaicapnhat() {
		return loaicapnhat;
	}
	public void setLoaicapnhat(int loaicapnhat) {
		this.loaicapnhat = loaicapnhat;
	}
	
}
