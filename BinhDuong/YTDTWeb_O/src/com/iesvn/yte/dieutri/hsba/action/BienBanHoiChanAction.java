/*
 * author : Bao
 */
package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBienBanHoiChanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChan;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Scope(CONVERSATION)
@Name("B239_Bienbanhoichan")
@Synchronized(timeout = 6000000)
public class BienBanHoiChanAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(BienBanHoiChanAction.class);
	
	private HsbaBienBanHoiChan hsbaBienBanHoiChan;
	private DtDmNhanVien newNhanVien;
	@DataModel
	private List<DtDmNhanVien> thanhVienList;
	@In(required = false)
	@Out(required = false)
	@DataModelSelection
	private DtDmNhanVien thanhVienSelected;
	private Integer gioHoichan;
	private String donViTuoi;
	private String ghiNhanException;
	private String maPhu;
	
	
	@Create
	@Begin(join=true)
	public String init() throws Exception {
		log.info("***Starting init ***");
		thanhVienList = new ArrayList<DtDmNhanVien>();
		getHsbaBienBanHoiChan(true).setHsbabbhcNgaygiocn(new java.util.Date());
		log.info("***Finished init **");
		return "DieuTri_LapVanBanTheoMau_BienBanHoiChan";
		
	}
	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		
		log.info("***Finished back **");
		return "MyMainForm";
		
	}
	public void layTheoSoVaoVien() throws Exception {
		log.info("***Starting layTheoSoVaoVien ***");
		log.info("--hsbaBienBanHoiChan.getHsbacmMa()=" + hsbaBienBanHoiChan.getHsbacmMa());
		this.thanhVienList.clear();
		this.newNhanVien = null;
		if (hsbaBienBanHoiChan != null && hsbaBienBanHoiChan.getHsbacmMa() != null) {
			log.info("hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa()=" + hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa());
			if(hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa() != null) {
				String khoa = hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa().getDmkhoaMa();
				log.info("khoa=" + khoa);				
				if(StringUtils.isNotBlank(khoa)) {
					log.info("hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien()=" + hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien());
					if(hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien() != null) {
						String soVaoVien = hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien();
						if(StringUtils.isNotBlank(soVaoVien)) {
							try {
								HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(soVaoVien, khoa);
								if (hsbaChuyenMon != null) {
									int dvt = hsbaChuyenMon.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDonvituoi();
									donViTuoi = com.iesvn.yte.util.Utils.taoDonViTuoi(dvt);
									hsbaBienBanHoiChan.setHsbacmMa(hsbaChuyenMon);
								}
							}catch(Exception e) {
								FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien);
								log.error(e.toString());
							}
						}
					}	
				}
			}
		}
		String khoa = hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa().getDmkhoaMa();
		String soVaoVien = hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien();
		if (getHsbaBienBanHoiChan().getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten() == null){
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien);
			
		}
		log.info("---- Ho ten=" + getHsbaBienBanHoiChan().getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten());
		
		log.info("***Finished layTheoSoVaoVien **");
		
	}
	public void layTheoHsbabbhcMa() throws Exception {
		log.info("***Starting layTheoHsbabbhcMa ***");
		try {
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			hsbaBienBanHoiChan = (HsbaBienBanHoiChan)dieuTriUtilDelegate.findByMaWithFormatMaPhieu(hsbaBienBanHoiChan.getHsbabbhcMa(), "HsbaBienBanHoiChan", "hsbabbhcMa");
			if (hsbaBienBanHoiChan == null) {
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " {0} {1}.", IConstantsRes.MA_BIEN_BAN, "null");
				return;
			}
			int dvt = hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanDonvituoi();
			donViTuoi = com.iesvn.yte.util.Utils.taoDonViTuoi(dvt);
			HsbaBienBanHoiChanDelegate hsbaBienBanHoiChanDelegate = HsbaBienBanHoiChanDelegate.getInstance();
			log.info("hsbaBienBanHoiChan=" + hsbaBienBanHoiChan);
			log.info("hsbaBienBanHoiChan.getHsbabbhcMa()=" + hsbaBienBanHoiChan.getHsbabbhcMa());
			thanhVienList = hsbaBienBanHoiChanDelegate.findThanhVienByHsbabbhcMa(hsbaBienBanHoiChan.getHsbabbhcMa());
			if (getHsbaBienBanHoiChan() != null && getHsbaBienBanHoiChan().getHsbabbhcNgaygiohoichan() != null) {
				gioHoichan = getSeconds(getHsbaBienBanHoiChan().getHsbabbhcNgaygiohoichan());
			} else {
				gioHoichan = Integer.valueOf(0);
			}
		} catch(Exception e) {	
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " {0} {1}.", IConstantsRes.MA_BIEN_BAN, hsbaBienBanHoiChan.getHsbabbhcMa());
			log.error(e.toString());
		}	
		log.info("***Finished layTheoHsbabbhcMa **");
		
	}
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT = null;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT = null;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT = null; 
	
	private int index=0;
	
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		
		
		loaiBCDT="bienbanhoichanphauthuat";
		log.info("Vao Method XuatReport bienbanhoichan_phauthuat");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/bienbanhoichan_phauthuat.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
			
			params.put("hoTen", getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanHoten());
			params.put("gioi", getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen());
			
			String tuoi =  getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanTuoi() + "" ;
			short dvt = getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanDonvituoi();
			if (dvt == 1){
				
			}else if (dvt ==2){
				tuoi = tuoi + "T";
			}else if (dvt == 3){
				tuoi = tuoi + "N";
			}
			params.put("tuoi",tuoi);
			// phuc.lc 19-03-2011 : begin fix bug 975 (phan thong tin dia chi), con thong tin tai khoa chua biet lay o dau
			StringBuffer strBuf = new StringBuffer("");
			BenhNhan bn = getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true);
			if (bn != null) {
				strBuf.append(bn.getBenhnhanDiachi());
				strBuf.append(bn.getXaMa() != null ? ", " + bn.getXaMa().getDmxaTen() : "");
				strBuf.append(bn.getHuyenMa() != null ? ", " + bn.getHuyenMa().getDmhuyenTen() : "");
				strBuf.append(bn.getTinhMa() != null ? ", " + bn.getTinhMa().getDmtinhTen() : "");
			}			
			//params.put("diaChi", getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanDiachi());
			params.put("diaChi", strBuf.toString());
			// phuc.lc 19-03-2011 : End fix bug 975
			params.put("soVaoVien", getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getHsbaSovaovien());
			
			DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
			
			
			if (getHsbaBienBanHoiChan(true).getHsbacmMa(true).getKhoaMa(true).getDmkhoaMa() != null &&
					!getHsbaBienBanHoiChan(true).getHsbacmMa(true).getKhoaMa(true).getDmkhoaMa().equals("")
			){
				DmKhoa khoa = (DmKhoa)dele.findByMa(getHsbaBienBanHoiChan(true).getHsbacmMa(true).getKhoaMa(true).getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
				if (khoa != null){
					params.put("tenkhoa", khoa.getDmkhoaTen());
				}else{
					params.put("tenkhoa", "");
				}
			}else{
				params.put("tenkhoa", "");
			}
			
			
			
			params.put("tomtat", getHsbaBienBanHoiChan(true).getHsbabbhcTomtat());
			
			Date dVaov = getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getHsbaNgaygiovaov();
			
			params.put("ngaygiovaovien", dVaov);
			
			params.put("ngaygiohoichan", DateUtils.addSeconds(DateUtils.truncate(hsbaBienBanHoiChan.getHsbabbhcNgaygiohoichan(), java.util.Calendar.DAY_OF_MONTH), gioHoichan));
			
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","bienbanhoichan_phauthuat");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		
		
		String khoa = hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa().getDmkhoaMa();
		String soVaoVien = hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien();
		if (getHsbaBienBanHoiChan().getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten() == null){
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien);
			return;
		}
		
		
		if (thanhVienList != null) {
			log.info("thanhVienList.size()=" + thanhVienList.size());
			
		}
	
		ghiNhanException = null;
		HsbaBienBanHoiChanDelegate hsbaBienBanHoiChanDelegate = HsbaBienBanHoiChanDelegate.getInstance();
			
		if (hsbaBienBanHoiChan != null) {	
			if (hsbaBienBanHoiChan.getHsbabbhcChutoa() != null && hsbaBienBanHoiChan.getHsbabbhcChutoa().getDtdmnhanvienMaso() == null) {
				hsbaBienBanHoiChan.setHsbabbhcChutoa(null);
			}
			if (hsbaBienBanHoiChan.getHsbabbhcThuky() != null && hsbaBienBanHoiChan.getHsbabbhcThuky().getDtdmnhanvienMaso() == null) {
				hsbaBienBanHoiChan.setHsbabbhcThuky(null);
			}
			try {
				if (hsbaBienBanHoiChan != null && hsbaBienBanHoiChan.getHsbabbhcNgaygiohoichan() != null) {
					hsbaBienBanHoiChan.setHsbabbhcNgaygiohoichan(DateUtils.addSeconds(DateUtils.truncate(hsbaBienBanHoiChan.getHsbabbhcNgaygiohoichan(), java.util.Calendar.DAY_OF_MONTH), gioHoichan));
				}
				if(StringUtils.isBlank(hsbaBienBanHoiChan.getHsbabbhcMa())) {					
					log.info("createHsbaBienBanHoiChan ...");
					
					hsbaBienBanHoiChan = hsbaBienBanHoiChanDelegate.createHsbaBienBanHoiChan(hsbaBienBanHoiChan, this.thanhVienList);
					log.info("createHsbaBienBanHoiChan ... end.");
					FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.MA_BIEN_BAN, hsbaBienBanHoiChan.getHsbabbhcMa());
				} else {
					log.info("editHsbaBienBanHoiChan ...");
					hsbaBienBanHoiChanDelegate.editHsbaBienBanHoiChan(hsbaBienBanHoiChan, this.thanhVienList);
					log.info("editHsbaBienBanHoiChan ... end");
					FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.MA_BIEN_BAN, hsbaBienBanHoiChan.getHsbabbhcMa());
				}
			} catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.FAIL);
				ghiNhanException = e.getClass().getName();
				log.error("Ghi nhan khong thanh cong");
				log.error("Loi : " + e);
			}
				
		}
		
		log.info("***Finished ghinhan **");
	}

	// Ham khi nhan nut nhap lai
	public void nhaplai() throws Exception {
		try {
			resetForm();			
		} catch (Exception e) {
			log.error("Loi reset form : " + e.toString());
		}
	}
	// Ham reset form 
	private void resetForm() {
		log.info("Begining ResetForm(): ");
		hsbaBienBanHoiChan = null;
		getHsbaBienBanHoiChan(true).setHsbabbhcNgaygiocn(new java.util.Date());
		thanhVienList.clear();
		newNhanVien = null;
		ghiNhanException = null;
		this.donViTuoi = null;
		log.info("End ResetForm(): ");
	}
	public Integer getSeconds(java.util.Date d) {
		long seconds = DateUtils.getFragmentInHours(d, java.util.Calendar.DATE)  * 60 * 60  + DateUtils.getFragmentInMinutes(d, java.util.Calendar.HOUR_OF_DAY) * 60;
		return Integer.valueOf(seconds + "");
	}
	/*
	@Observer("org.jboss.seam.validationFailed")
	public void _validationFailed() {
		ghiNhanException = "_validationFailed";
		log.info("_validationFailed ----------11");
	}
	*/
	public HsbaBienBanHoiChan getHsbaBienBanHoiChan(boolean create) {
		if (create && hsbaBienBanHoiChan == null) {
			hsbaBienBanHoiChan = new HsbaBienBanHoiChan();
		}
		return hsbaBienBanHoiChan;
	}
	public HsbaBienBanHoiChan getHsbaBienBanHoiChan() {
		return hsbaBienBanHoiChan;
	}
	public void setHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan) {
		this.hsbaBienBanHoiChan = hsbaBienBanHoiChan;
	}
	public String getGhiNhanException() {
		return ghiNhanException;
	}
	public void setGhiNhanException(String ghiNhanException) {
		this.ghiNhanException = ghiNhanException;
	}
	
	public void themThanhVien() {
		if (thanhVienList == null) thanhVienList = new ArrayList<DtDmNhanVien>();
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		DtDmNhanVien nv = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(newNhanVien.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
		//DtDmNhanVien nv = new DtDmNhanVien(newNhanVien.getDtdmnhanvienMaso(), newNhanVien.getDtdmnhanvienMa());
		
		log.info("newNhanVien.getDtdmnhanvienTen()=" + newNhanVien.getDtdmnhanvienTen());
		log.info("nv.getDtdmnhanvienTen()=" + nv.getDtdmnhanvienTen());
		log.info("thanhVienList.contains(nv)=" + thanhVienList.contains(nv));
		if (!this.thanhVienList.contains(nv)){
			this.thanhVienList.add(nv);
		}
	}
	
	public void deleteCurrentRow() {
		if (this.thanhVienList == null || this.thanhVienList.size() == 0) {
			return;
		}		
		this.thanhVienList.remove(this.thanhVienSelected);
		thanhVienSelected = null;
	}
	
	public DtDmNhanVien getNewNhanVien(boolean create) {
		if (create && newNhanVien == null) {
			newNhanVien = new DtDmNhanVien();
		}
		return newNhanVien;
	}
	public DtDmNhanVien getNewNhanVien() {
		return newNhanVien;
	}
	public void setNewNhanVien(DtDmNhanVien newNhanVien) {
		this.newNhanVien = newNhanVien;
	}
	public List<DtDmNhanVien> getThanhVienList() {
		return thanhVienList;
	}
	public void setThanhVienSet(List<DtDmNhanVien> thanhVienList) {
		this.thanhVienList = thanhVienList;
	}
	public String getDonViTuoi() {
		return donViTuoi;
	}
	public void setDonViTuoi(String donViTuoi) {
		this.donViTuoi = donViTuoi;
	}
	public Integer getGioHoichan() {
		return gioHoichan;
	}
	public void setGioHoichan(Integer gioHoichan) {
		this.gioHoichan = gioHoichan;
	}
}
