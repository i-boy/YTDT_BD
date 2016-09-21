/*
 * author : Bao
 */
package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Identity;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate; //bao.ttc
import com.iesvn.yte.dieutri.entity.HsThtoank; //bao.ttc
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.TamUngKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3234_CapNhatTienTamUngPCC")
@Synchronized(timeout = 6000000)
public class CapNhatTienTamUngPCCAction implements Serializable {
	private static final long serialVersionUID = 10L;
	public transient static Logger log = Logger.getLogger(CapNhatTienTamUngPCCAction.class);
	@In(required = false)
	private Identity identity;
	private TamUngKham tamUngKham;
	//private HsThtoank hsThtoank;
	private String ghiNhanException;
	private String maPhu;
	private String soDu;
	private String tamUng_HoanUng;
	private String soTheBH_SoTheNgheo;
	private String tienBangChu;
	private String donViTuoi;
	private String sLiDoNop;
	private String lanTamung="";
	private DtDmCum cum = null;
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	private int index=0;
	private boolean disabledPrinting = true;
	public void refreshnhanvienthungan(){
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
	}
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Create
	@Begin(join=true)
	public void init() throws Exception {
		log.info("***Starting init ***");
		refreshnhanvienthungan();
		sLiDoNop=IConstantsRes.LI_DO_NOP_THU_TAM_UNG_PCC;
		DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
		nhanVienThungan = nvDelegate.findByNd(identity.getUsername());
		if (nhanVienThungan == null) {
			nhanVienThungan = new DtDmNhanVien();
		}
		log.info("nhanVienThungan1"+nhanVienThungan);
		PcCumThuPhiDelegate pcCumThuPhiDelegate  = PcCumThuPhiDelegate.getInstance();
		PcCumThuPhi pc =  pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), nhanVienThungan.getDtdmnhanvienMa());
		
		log.info("PcCumThuPhi"+pc);
		
		
		if (pc!=null && pc.getDtdmnhanvienMa() != null){
			
			//nhanVienThungan = pc.getDtdmnhanvienMa();
			log.info("nhanVienThungan:"+nhanVienThungan);
			cum = pc.getDtdmcumMa();
		}
		
		
		this.maPhu = new java.util.Date().getTime() + "";
		this.getTamUngKham(true).setTamungkhamNgay(new java.util.Date());
		log.info("***Finished init **");
		
	}
	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		
		log.info("***Finished back **");
		return "MyMainForm";
		
	}
	/**
	 * xuat report 
	 */
	public String xuatReport() {
		String gt=null;
		String diachi="";
		String baocao1=null;
		try {
			if(tamUngKham == null) return null;
			if(tamUngKham.getTiepdonMa() == null) return null;
			if(tamUngKham.getTiepdonMa().getBenhnhanMa() == null) return null;
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/Phieutamung.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			//TINH  getTiepDon(true).getBenhnhanMa(true).getTinhMa(true).dmtinhMa
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM );
			params.put("NGAYLAP", DateFormatUtils.format(getTamUngKham().getTamungkhamNgay(), Utils.FORMAT_DATE));
			log.info("Ten benh nhan " + tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());
			params.put("TEN", tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());
			params.put("TUOI", tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi());
			if(tamUngKham.getTiepdonMa().getBenhnhanMa().getDmgtMaso() != null) {
				gt = tamUngKham.getTiepdonMa().getBenhnhanMa().getDmgtMaso().getDmgtTen();
			}
			log.info("Gioi tinh " + gt);
			params.put("GT", gt);
			if(tamUngKham.getTiepdonMa().getBenhnhanMa().getDantocMa() != null) {
				log.info("Dan toc " + tamUngKham.getTiepdonMa().getBenhnhanMa().getDantocMa().getDmdantocTen());
				params.put("DANTOC", tamUngKham.getTiepdonMa().getBenhnhanMa().getDantocMa().getDmdantocTen() );
			}
			String sonha;
			String xa;
			String huyen;
			String tinh;
			if (tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() == null) {
				sonha = "";
			} else {
				sonha = tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ",";
			}
			if (tamUngKham.getTiepdonMa().getBenhnhanMa().getXaMa().getDmxaTen() == null) {
				xa = "";
			} else {
				xa = tamUngKham.getTiepdonMa().getBenhnhanMa().getXaMa().getDmxaTen() + ",";
			}
			if (tamUngKham.getTiepdonMa().getBenhnhanMa().getHuyenMa().getDmhuyenTen() == null) {
				huyen = "";
			} else {
				huyen = tamUngKham.getTiepdonMa().getBenhnhanMa().getHuyenMa().getDmhuyenTen() + ",";
			}
			if (tamUngKham.getTiepdonMa().getBenhnhanMa().getTinhMa().getDmtinhTen() == null) {
				tinh = "";
			} else {
				tinh = tamUngKham.getTiepdonMa().getBenhnhanMa().getTinhMa().getDmtinhTen();
			}
			
			diachi = sonha + xa + huyen + tinh;
			
			log.info("Dia chi " + diachi);
			params.put("DIACHI", diachi);
			//KHOA 
			if (soTheBH_SoTheNgheo == null) {
				params.put("SOTHEBH", "");
			} else {
				params.put("SOTHEBH", soTheBH_SoTheNgheo);
			}
			if (getTiepDon().getKcbbhytMa() != null) {
				if (getTiepDon().getKcbbhytMa().getDmbenhvienTen() == null) {
					params.put("KCB", "");
				}else {
					params.put("KCB", getTiepDon().getKcbbhytMa().getDmbenhvienTen());
				}
			}  else {
				params.put("KCB", "");
			}
			log.info("Tuyen " + getTiepDon().getTiepdonTuyen());
			if(getTiepDon().getTiepdonTuyen() == null)
				params.put("TUYEN", "");
			else
				params.put("TUYEN", "" + getTiepDon().getTiepdonTuyen());
			log.info("ta mung " + getTamUngKham().getTamungkhamSotien());
			params.put("TAMUNG", getTamUngKham().getTamungkhamSotien());
			log.info("tam ung bang chu " + tienBangChu);
			params.put("BANGCHU", tienBangChu );
			
			if(tamUngKham.getTamungkhamLydo()!=null)
			params.put("LIDONOP", tamUngKham.getTamungkhamLydo());
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			// phuc.lc 21-04-2011 : Fix bug 2655
			int lanIn = tamUngKham.getTamungkhamInphieu() == null ? 1 : (Integer.valueOf(tamUngKham.getTamungkhamInphieu()).intValue() + 1);
			tamUngKham.setTamungkhamInphieu("" + lanIn);
			params.put("LANIN", "" + lanIn);
			
			reportTypeVP="capnhattientamung_pcc";
			log.info("Vao Method XuatReport giaytamung");
			log.info("================= ");
		    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params);
		    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP, index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","capnhattientamung_pcc");
		    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
		    log.info("duong dan file xuat report :" + baocao1);
		    log.info("duong dan -------------------- :"+reportPathVP);
		    index +=1;
		 // phuc.lc 21-04-2011 : Fix bug 2655
		    TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
			tamUngKhamDelegate.edit(tamUngKham);
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		
	    log.info("Thoat Method XuatReport");
	    return "B3360_Chonmenuxuattaptin";
	}	
	public void layTheoTiepDonMa() throws Exception {
		log.info("***Starting layTheoTiepDonMa ");
		
		if(tamUngKham.getTiepdonMa() != null && tamUngKham.getTiepdonMa().getTiepdonMa() != null) {
			try {
				qryBenhNhanBhtyThanhToan(this.tamUngKham.getTiepdonMa().getTiepdonMa());
				TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
				lanTamung = "" + tamUngKhamDelegate.countSolanTamUngByTiepdon(tamUngKham.getTiepdonMa().getTiepdonMa());
			} catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.MA_TIEP_DON, tamUngKham.getTiepdonMa().getTiepdonMa());
				log.error(e.toString());
			}	
			
			this.maPhu = new java.util.Date().getTime() + "";
		}
		tamUngKham.setTamungkhamMa(null);
		tamUngKham.setTamungkhamSotien(null);
		tienBangChu = "";
		disabledPrinting = true;
		log.info("***Finished layTheoTiepDonMa **");
		
	}
	public void layTheoTamUngMa() throws Exception {
		log.info("***Starting layTheoTamUngMa ***");
		//disabledPrinting = true;		
		if (tamUngKham == null) return;
		
		String stemp="";
		stemp=tamUngKham.getTamungkhamMa();
		TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
		try {
			tamUngKham = tamUngKhamDelegate.find(tamUngKham.getTamungkhamMa());
			if (tamUngKham != null && tamUngKham.getTiepdonMa() != null) {
				int dvt = tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi();
				donViTuoi = com.iesvn.yte.util.Utils.taoDonViTuoi(dvt);
				//qryBenhNhanBhtyThanhToan(tamUngKham.getTiepdonMa().getTiepdonMa());
				if(tamUngKham.getTamungkhamThungan(true)!=null)
				{
					nhanVienThungan = tamUngKham.getTamungkhamThungan(true);
					if(nhanVienThungan==null){
						refreshnhanvienthungan();
					}
				}
				sLiDoNop = tamUngKham.getTamungkhamLydo();
				lanTamung = "" + tamUngKhamDelegate.countSolanTamUngByTiepdon(tamUngKham.getTiepdonMa().getTiepdonMa());
				log.info("****LIDO: "+tamUngKham.getTamungkhamLydo());
				disabledPrinting = false;
			}else{
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, stemp);
				tamUngKham = null;
				//hsThtoank = null;
			}
		} catch(Exception e) {
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, tamUngKham.getTamungkhamMa());
			log.error(e.toString());
		}
		if (tamUngKham != null && tamUngKham.getTamungkhamMa() != null) {
			this.maPhu = tamUngKham.getTamungkhamMa();
		} else {
			this.maPhu = new java.util.Date().getTime() + ""; 
		}
		if(nhanVienThungan==null){
			refreshnhanvienthungan();
		}
		log.info("***Finished layTheoTamUngMa **");
		
	}
	private void qryBenhNhanBhtyThanhToan(String maTiepDon) {
		TiepDonDelegate tiepdonDelegate = TiepDonDelegate.getInstance();
		//TiepDon tiepDon = tiepdonDelegate.findBenhNhanByTiepdonMa(maTiepDon);
		TiepDon tiepDon = tiepdonDelegate.find(maTiepDon);
		if (tiepDon == null) {
			log.info("########## tiepDon = null ##########");
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.MA_TIEP_DON, maTiepDon);
			return;
		} else {// Kiem tra co phai la benh nhan cap cuu hay khong
			if (tiepDon.getTiepdonBankham() != null) {
				if(tiepDon.getTiepdonBankham().getDtdmbankhamMa() == null || 
						(! (tiepDon.getTiepdonBankham().getDtdmbankhamMa().equalsIgnoreCase("CCL")) && ! tiepDon.getTiepdonBankham().getDtdmbankhamMa().equalsIgnoreCase("CCN"))){
					FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU);					
					return;
				}
			} else {
				// Khong co thong tin ban kham
				FacesMessages.instance().add(IConstantsRes.KHONG_PHAI_DOI_TUONG_CAP_CUU);				
				return;
			}
		}
		this.tamUngKham.setTiepdonMa(tiepDon);
		int dvt = tamUngKham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi();
		donViTuoi = com.iesvn.yte.util.Utils.taoDonViTuoi(dvt);
		log.info("tamUngKham.getTiepdonMa().getTiepdonMa()=" + tamUngKham.getTiepdonMa().getTiepdonMa());
		if (getTiepDon() == null) {			
			//this.hsThtoank = null;
			this.tamUngKham = null;
			return;
		}
		log.info("getTiepDon().getTiepdonTuyen()=" + getTiepDon().getTiepdonTuyen());
		log.info("getTiepDon().getKcbbhytMa()=" + getTiepDon().getKcbbhytMa());
		if (getTiepDon().getKcbbhytMa() != null) {
			log.info("getTiepDon().getKcbbhytMa().getDtdmkcbbhytMa() = " + getTiepDon().getKcbbhytMa().getDmbenhvienMa());
			log.info("getTiepDon().getKcbbhytMa().getDtdmkcbbhytTen() = " + getTiepDon().getKcbbhytMa().getDmbenhvienTen());
		}	
		log.info("getTiepDon().getTiepdonSothebh()=" + getTiepDon().getTiepdonSothebh());
		this.soTheBH_SoTheNgheo = getTiepDon().getTiepdonSothebh();
		if (StringUtils.isBlank(soTheBH_SoTheNgheo)) {
			log.info("getTiepDon().getTiepdonThengheo()=" + getTiepDon().getTiepdonThengheo());
			this.soTheBH_SoTheNgheo = getTiepDon().getTiepdonThengheo();
		}
		disabledPrinting = false;
		//bao.ttc: dung ham findBytiepdonMaLast
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		HsThtoank hsttk = hsttkDelegate.findBytiepdonMaLast(maTiepDon);
		if (hsttk != null) {
			Double tamUng = hsttk.getHsthtoankTamung();
			Double hoanUng = hsttk.getHsthtoankHoanung();
			if (tamUng == null) {
				tamUng = 0.0;
			}
			if (hoanUng == null) {
				hoanUng = 0.0;
			}
			log.info("############# !null ###### Tam ung    :" + tamUng);
			log.info("############# !null ###### Hoan ung   :" + hoanUng);
			log.info("############# !null ###### Thanh toan :" + hsttk.getHsthtoankThtoan());
			tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(tamUng - hoanUng);
			soDu = com.iesvn.yte.util.Utils.formatLongDouble(hsttk.getHsthtoankThtoan());
			
		} else {
			hsttk = new HsThtoank();
			hsttk.setTiepdonMa(tiepDon);
			
			HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(tiepDon);
			hsthtoankUtil.tinhToanChoHSTKKham(hsttk);
			//Utils.setInforForHsThToan(hsttk);
			tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hsttk.getHsthtoankTamung() - hsttk.getHsthtoankHoanung());
			soDu = com.iesvn.yte.util.Utils.formatLongDouble(hsttk.getHsthtoankThtoan());
			log.info("########################## So du   :" + soDu);
			log.info("############ Tam ung - Hoan ung    :" + tamUng_HoanUng);
		} // bao.ttc

		
		/* bao.ttc
		//HoSoThanhToanKhamUtil hoSoThanhToanKhamUtil = new HoSoThanhToanKhamUtil(getTiepDon());
		HoSoThanhToanKhamUtil hoSoThanhToanKhamUtil = new HoSoThanhToanKhamUtil(tiepDon);
		if (hoSoThanhToanKhamUtil != null) {
			log.info("hoSoThanhToanKhamUtil.getUng()=" + hoSoThanhToanKhamUtil.getUng());
			log.info("hoSoThanhToanKhamUtil.getTra()=" + hoSoThanhToanKhamUtil.getTra());
			//tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hoSoThanhToanKhamUtil.getUng()) + "-" + com.iesvn.yte.util.Utils.formatLongDouble(hoSoThanhToanKhamUtil.getTra());
			if ( hoSoThanhToanKhamUtil.getUng() == null && hoSoThanhToanKhamUtil.getTra() == null) {
				tamUng_HoanUng = "0";
			} else if ( hoSoThanhToanKhamUtil.getUng() != null && hoSoThanhToanKhamUtil.getTra() != null) {
				tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hoSoThanhToanKhamUtil.getUng() - hoSoThanhToanKhamUtil.getTra());
			} else if ( hoSoThanhToanKhamUtil.getUng() != null) {
				tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hoSoThanhToanKhamUtil.getUng());
			} else {
				tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(- hoSoThanhToanKhamUtil.getTra());
			}
			soDu = com.iesvn.yte.util.Utils.formatLongDouble(hoSoThanhToanKhamUtil.getSoDu());
		}    bao.ttc */ 
	}
	
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		log.info(tamUngKham.getTamungkhamSotien());
		ghiNhanException = null;
		refreshnhanvienthungan();
		TamUngKhamDelegate tamUngKhamDelegate = TamUngKhamDelegate.getInstance();
		if (tamUngKham != null) {
			try {	
				DtDmNhanVien userNv = DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
				if (userNv != null) {
					PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
					PcCumThuPhi pcCumThuPhi = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(tamUngKham.getTamungkhamNgay(), userNv.getDtdmnhanvienMa());
					if (pcCumThuPhi != null){
						tamUngKham.setTamungkhamCum(pcCumThuPhi.getDtdmcumMa());
					}
				}
				if(StringUtils.isBlank(tamUngKham.getTamungkhamMa())) {		
					tamUngKham.setTamungkhamThungan(nhanVienThungan);
					tamUngKham.setTamungkhamLydo(sLiDoNop);
					tamUngKham.setTamungkhamNgay(Calendar.getInstance().getTime());
					tamUngKham = tamUngKhamDelegate.create(tamUngKham);
					
				} else {
					tamUngKham.setTamungkhamLydo(sLiDoNop);
					tamUngKham.setTamungkhamNgay(Calendar.getInstance().getTime());
					tamUngKhamDelegate.edit(tamUngKham);
				}
				FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.PHIEU_SO, tamUngKham.getTamungkhamMa());
				disabledPrinting = false;
			}catch(Exception e) {
				e.printStackTrace();
				FacesMessages.instance().add(IConstantsRes.FAIL);
				ghiNhanException = e.getClass().getName();
				log.error("Ghi nhan khong thanh cong");
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
		refreshnhanvienthungan();
		sLiDoNop=IConstantsRes.LI_DO_NOP_THU_TAM_UNG_PCC;
		this.tamUngKham = null;
		this.getTamUngKham(true).setTamungkhamNgay(new java.util.Date());
		//this.hsThtoank = null;
		this.soDu = null;
		this.tamUng_HoanUng = null;
		this.soTheBH_SoTheNgheo = null;
		this.tienBangChu = null;
		this.donViTuoi = null;
		disabledPrinting = true;
		this.maPhu = new java.util.Date().getTime() + ""; 
		lanTamung="";
		log.info("End ResetForm(): ");
	}
	
	public TamUngKham getTamUngKham() {
		return tamUngKham;
	}
	public TamUngKham getTamUngKham(boolean create) {
		if(create && tamUngKham == null) {
			tamUngKham = new TamUngKham();
		}
		return tamUngKham;
	}

	public void setTamUng(TamUngKham tamUng) {
		this.tamUngKham = tamUng;
	}

	public String getGhiNhanException() {	
		return ghiNhanException;
	}

	public void setGhiNhanException(String ghiNhanException) {
		this.ghiNhanException = ghiNhanException;
	}
	/*
	@Observer("org.jboss.seam.validationFailed")
	public void _validationFailed() {
		setGhiNhanException("_validationFailed");
		log.info("_validationFailed ----------11");
	}
*/
	public String getMaPhu() {
		return maPhu;
	}

	public void setMaPhu(String maPhu) {
		this.maPhu = maPhu;
	}

	public String getTamUng_HoanUng() {
		return tamUng_HoanUng;
	}

	public void setTamUng_HoanUng(String tamUng_HoanUng) {
		this.tamUng_HoanUng = tamUng_HoanUng;
	}
	public String getSoTheBH_SoTheNgheo() {
		return soTheBH_SoTheNgheo;
	}
	public void setSoTheBH_SoTheNgheo(String soTheBH_SoTheNgheo) {
		this.soTheBH_SoTheNgheo = soTheBH_SoTheNgheo;
	}
	public String getTienBangChu() {
		return tienBangChu;
	}
	public void setTienBangChu(String tienBangChu) {
		this.tienBangChu = tienBangChu;
	}
	public TiepDon getTiepDon(boolean create) {
		if(create && getTiepDon() == null) getTamUngKham().getTiepdonMa(create);
		return getTiepDon();
	}
	public TiepDon getTiepDon() {
		return getTamUngKham().getTiepdonMa();
	}
	public void setTiepDon(TiepDon tiepDon) {
		getTamUngKham().setTiepdonMa(tiepDon);
	}
	public String getDonViTuoi() {
		return donViTuoi;
	}
	public void setDonViTuoi(String donViTuoi) {
		this.donViTuoi = donViTuoi;
	}
	public String getSoDu() {
		return soDu;
	}
	public void setSoDu(String soDu) {
		this.soDu = soDu;
	}
	
	public DtDmCum getCum() {
		return cum;
	}
	public void setCum(DtDmCum cum) {
		this.cum = cum;
	}
	public DtDmNhanVien getNhanVienThungan() {
		return nhanVienThungan;
	}
	public void setNhanVienThungan(DtDmNhanVien nhanVienThungan) {
		this.nhanVienThungan = nhanVienThungan;
	}
	
	public void setTamUngKham(TamUngKham tamUngKham) {
		this.tamUngKham = tamUngKham;
	}
	public boolean isDisabledPrinting() {
		return disabledPrinting;
	}
	public void setDisabledPrinting(boolean disabledPrinting) {
		this.disabledPrinting = disabledPrinting;
	}
	public String getLiDoNop()
	{
		return sLiDoNop;
	}
	public void setLiDoNop(String liDoNop)
	{
		sLiDoNop = liDoNop;
	}
	public String getLanTamung() {
		return lanTamung;
	}
	public void setLanTamung(String lanTamung) {
		this.lanTamung = lanTamung;
	}
	
	
}
