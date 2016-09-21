/*
 * author : Bao
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

import org.apache.commons.lang.StringUtils;
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
import com.iesvn.yte.dieutri.delegate.HoanUngDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.TamUngDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HoanUng;
import com.iesvn.yte.dieutri.entity.HsThtoan; //bao.ttc
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.TamUng;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3216_ChiTraTienTamUng")
@Synchronized(timeout = 6000000)
public class ChiTraTienTamUngAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(ChiTraTienTamUngAction.class);
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	private BenhNhan benhNhan;
	private HsbaBhyt hsbaBhyt;	
	private HoanUng hoanUng;
	//private HsThtoan hsThtoan;
	// bao.ttc: private HoSoThanhToanUtil hosoThanhToanUtil;
	private String ghiNhanException;
	private String maPhu;
	private String daUng; //bao.ttc
	private String daTra; //bao.ttc
	private String soDu;
	private String soTheBH_SoTheNgheo;
	private String donViTuoi;
	private int index=0;	
	private DtDmCum cum = null;
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	
	private boolean disabledPrinting = true;
	private boolean disabledGhinhan = false;
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Create
	@Begin(join=true)
	public void init() throws Exception {
		log.info("***Starting init ***");
		
		
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
			
			nhanVienThungan = pc.getDtdmnhanvienMa();
			log.info("nhanVienThungan:"+nhanVienThungan);
			cum = pc.getDtdmcumMa();
		}
		
		this.getHoanUng(true).setHoanungNgaygio(new java.util.Date());
		this.maPhu = new java.util.Date().getTime() + "";			
		log.info("***Finished init **");
		
	}
	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		
		log.info("***Finished back **");
		return "MyMainForm";
		
	}
	
	public String xuatReport() {
		XuatReportTmp();
		return "B3360_Chonmenuxuattaptin";
	}
	/**
	 * xuat report 
	 */
	public void XuatReportTmp() {
		reportTypeVP="chitrabottientamung";
		log.info("Vao Method XuatReport thuoc y dung cu phong kham");
		String baocao1=null;
		String pathTemplate=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/PhieuChi.jrxml";
			
			log.info("da thay file templete " + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			Map<String, Object> params = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("tenBenhNhan", benhNhan.getBenhnhanHoten());
			
			String diachistr="";
			if(benhNhan.getBenhnhanDiachi() != null)
				diachistr += benhNhan.getBenhnhanDiachi()+",";
			if(benhNhan.getXaMa(true).getDmxaTen() !=null)
				diachistr += benhNhan.getXaMa(true).getDmxaTen()+",";
			if(benhNhan.getHuyenMa(true).getDmhuyenTen() != null)
				diachistr += benhNhan.getHuyenMa(true).getDmhuyenTen()+",";
			if(benhNhan.getTinhMa(true).getDmtinhTen() != null)
				diachistr += benhNhan.getTinhMa(true).getDmtinhTen();
			params.put("diaChiBenhNhan", diachistr );
			
			params.put("tienThuoc", hoanUng.getHoanungSotien());
			params.put("TIENBANGCHU", Utils.NumberToString(hoanUng.getHoanungSotien()));
			
			// phuc.lc 21-04-2011 : Fix bug 2655
			int lanIn = hoanUng.getHoanungIn() == null ? 1 : (Integer.valueOf(hoanUng.getHoanungIn()).intValue() + 1);
			hoanUng.setHoanungIn("" + lanIn);
			params.put("LANIN", "" + lanIn);
			params.put("SOPHIEU", hoanUng.getHoanungMa());
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","chitrabottientamung");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+= 1;
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
			 // phuc.lc 21-04-2011 : Fix bug 2655
			HoanUngDelegate hoanUngDelegate = HoanUngDelegate.getInstance();
			hoanUngDelegate.edit(hoanUng);
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	/**
	 * xuat report 
	 */
	/*
	public String xuatReport() {
		String gt=null;
		String diachi="";
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/trabottientamung.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			log.info("Ten benh nhan " + benhNhan.getBenhnhanHoten());
			params.put("TEN", benhNhan.getBenhnhanHoten());
			if (benhNhan.getDmgtMaso() != null) {
				gt = benhNhan.getDmgtMaso().getDmgtTen();
				
				log.info("Gioi tinh " + gt);
				params.put("GT", gt);
			}
			
			if(benhNhan.getDantocMa() != null) {
				log.info("Dan toc " + benhNhan.getDantocMa().getDmdantocTen());
				params.put("DANTOC", benhNhan.getDantocMa().getDmdantocTen() );
			}
			if(benhNhan.getXaMa() != null && benhNhan.getHuyenMa() != null && benhNhan.getTinhMa() != null) {
				diachi=benhNhan.getBenhnhanDiachi()+","+benhNhan.getXaMa().getDmxaTen()+","+benhNhan.getHuyenMa().getDmhuyenTen()+","+benhNhan.getTinhMa().getDmtinhTen();
				log.info("Dia chi " + diachi);
			}	
			
			params.put("DIACHI", diachi );
			if(hoanUng.getHoanungKhoa() != null) {
				log.info("khoa " + hoanUng.getHoanungKhoa().getDmkhoaTen());
				params.put("KHOA", hoanUng.getHoanungKhoa().getDmkhoaTen() );
			}
			log.info("So the BHYT " + hsbaBhyt.getHsbabhytSothebh());
			params.put("SOTHEBH", hsbaBhyt.getHsbabhytSothebh());
			if (hsbaBhyt.getHsbabhytMakcb() != null) {
				log.info("KCB " + hsbaBhyt.getHsbabhytMakcb().getDtdmkcbbhytTen());
				params.put("KCB", hsbaBhyt.getHsbabhytMakcb().getDtdmkcbbhytTen());
			}
			log.info("Tuyen " + String.valueOf(hsbaBhyt.getHsbabhytTuyen()));
			if(hsbaBhyt.getHsbabhytTuyen()==null)
				params.put("TUYEN", "");
			else
				params.put("TUYEN", String.valueOf(hsbaBhyt.getHsbabhytTuyen()));
			log.info("tam mung " + String.valueOf(hosoThanhToanUtil.getUng()));
			params.put("TAMUNG", hosoThanhToanUtil.getUng() );
			log.info("da tra " + hosoThanhToanUtil.getTra());
			params.put("DATRA", hosoThanhToanUtil.getTra());
			log.info("chi tra " +hoanUng.getHoanungSotien());
			params.put("CHITRA", hoanUng.getHoanungSotien());
			//Double temp= hosoThanhToanUtil.getUng()-hoanUng.getHoanungSotien()-hosoThanhToanUtil.getTra();
			Double temp =  hosoThanhToanUtil.getSoDu();
			log.info("so du " + String.valueOf(temp));
			params.put("SODU", temp);
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM );
			reportTypeVP="chitrabottientamung";
			log.info("Vao Method XuatReport chi tra bot tien tam ung");
			log.info("================= ");
			    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","chitrabottientamung");
			    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathVP);
			    index+=1;
			    log.info("Index :" + getIndex());
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	    return "B3360_Chonmenuxuattaptin";
	}
	*/
	public void layTheoSoVaoVien() throws Exception {
		log.info("***Starting layTheoSoVaoVien ***");
		disabledPrinting = true;		
		if(hoanUng.getHoanungKhoa() != null && hoanUng.getHoanungKhoa().getDmkhoaMa() != null
				&& hoanUng.getHsbaSovaovien() != null && hoanUng.getHsbaSovaovien().getHsbaSovaovien() != null) {
			try {
				qryBenhNhanBhtyThanhToan(hoanUng.getHoanungKhoa().getDmkhoaMa(), hoanUng.getHsbaSovaovien().getHsbaSovaovien());
				if (benhNhan == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, hoanUng.getHoanungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, hoanUng.getHsbaSovaovien().getHsbaSovaovien());
					hoanUng.getHsbaSovaovien().setHsbaSovaovien("");
				} else if (disabledGhinhan) {
					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);					
				}
			} catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, hoanUng.getHoanungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, hoanUng.getHsbaSovaovien().getHsbaSovaovien());
				hoanUng.getHsbaSovaovien().setHsbaSovaovien("");
				log.error(e.toString());
			}	
			
			this.maPhu = new java.util.Date().getTime() + "";
			log.debug(hoanUng.getHsbaSovaovien());
			log.debug(hoanUng.getHoanungKhoa().getDmkhoaMa());
		}
		log.info("***Finished layTheoSoVaoVien **");
		
	}
	public void layTheoHoanUngMa() throws Exception {
		log.info("***Starting layTheoHoanUngMa ***");
		disabledPrinting = true;
		if (hoanUng == null) return;
		log.info("hoanUng.getHoanungMa()=" + hoanUng.getHoanungMa());
		String strMaTmp = hoanUng.getHoanungMa();
		HoanUngDelegate hoanUngDelegate = HoanUngDelegate.getInstance();
		try {
			hoanUng = hoanUngDelegate.find(hoanUng.getHoanungMa());
			if (hoanUng == null) {
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, strMaTmp);
			}
		} catch(Exception e) {
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, strMaTmp);
			log.error(e.toString());
		}	
		
		if(hoanUng != null && hoanUng.getHoanungKhoa() != null && hoanUng.getHoanungKhoa().getDmkhoaMa() != null
				&& hoanUng.getHsbaSovaovien() != null && hoanUng.getHsbaSovaovien().getHsbaSovaovien() != null) {
			
			qryBenhNhanBhtyThanhToan(hoanUng.getHoanungKhoa().getDmkhoaMa(), hoanUng.getHsbaSovaovien().getHsbaSovaovien());
			this.maPhu = hoanUng.getHoanungMa();
			disabledPrinting = false;
			if (disabledGhinhan) {
				FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);					
			}
		}else{
			benhNhan = null;
			hsbaBhyt = null;
			//hsThtoan = null;
		}
		log.info("***Finished layTheoHoanUngMa **");
		
	}
	private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien) {
		
		HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
		
		// 20120312 bao.ttc: khong dung hsbaKhoa ma tim truc tiep HSBA
		HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(soVaoVien, khoa);
		if (hsbaKhoa == null) {
			log.info("##### hsbaKhoa = NULL #####");
			//FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");
			benhNhan = null;
			hsbaBhyt = null;
			return;
		}
		
		if (!khoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa())){
			//FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");
			benhNhan = null;
			hsbaBhyt = null;
			return;
		}
		
		
		hoanUng.getHsbaSovaovien().setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien());
		soVaoVien = hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien();
		disabledGhinhan = checkDaTT(soVaoVien);	
		benhNhan = hsbaKhoa.getHsbaSovaovien().getBenhnhanMa();
		
		HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
		List<HsbaBhyt> list = hsbaBhytDelegate.findBySoVaoVien(soVaoVien);
		if (list != null && list.size() > 0) hsbaBhyt = list.get(0);
		if (benhNhan != null) {
			int dvt = benhNhan.getBenhnhanDonvituoi();
			donViTuoi = com.iesvn.yte.util.Utils.taoDonViTuoi(dvt);
			if (benhNhan.getDantocMa() != null) {
				log.info("benhNhan.getDantocMa().getDmdantocMa()=" + benhNhan.getDantocMa().getDmdantocMa());
				log.info("benhNhan.getDantocMa().getDmdantocTen()=" + benhNhan.getDantocMa().getDmdantocTen());
			}
			if (benhNhan.getDmgtMaso() != null) {
				log.info("benhNhan.getDmgtMaso()=" + benhNhan.getDmgtMaso().getDmgtMaso());
			}
		}			
		if (hsbaBhyt != null) {
			//this.soTheBH_SoTheNgheo = hsbaBhyt.getHsbabhytSothebh() + "/" + hsbaBhyt.getHsbabhytSothengheo();
			log.info("hsbaBhyt.getHsbabhytSothebh()=" + hsbaBhyt.getHsbabhytSothebh());
			this.soTheBH_SoTheNgheo = hsbaBhyt.getHsbabhytSothebh();
			if (StringUtils.isBlank(soTheBH_SoTheNgheo)) {
				log.info("hsbaBhyt.getHsbabhytSothengheo()=" + hsbaBhyt.getHsbabhytSothengheo());
				this.soTheBH_SoTheNgheo = hsbaBhyt.getHsbabhytSothengheo();
			}
			log.info("hsbaBhyt.getHsbabhytMakcb()=" + hsbaBhyt.getHsbabhytMakcb());
			if (hsbaBhyt.getHsbabhytMakcb() != null)
				log.info("hsbaBhyt.getHsbabhytMakcb().getDtdmkcbbhytMa()=" + hsbaBhyt.getHsbabhytMakcb().getDmbenhvienMa());
			log.info("hsbaBhyt.getHsbabhytSothebh()=" + hsbaBhyt.getHsbabhytSothebh());
			log.info("hsbaBhyt.getHsbabhytSothengheo()=" + hsbaBhyt.getHsbabhytSothengheo());
		}

		
		/* bao.ttc: old code, ko hien ra Ung, Tra, Sodu
		hosoThanhToanUtil = new HoSoThanhToanUtil(hsbaChuyenMon.getHsbaSovaovien());
		if (hosoThanhToanUtil != null) {
			log.info("hosoThanhToanUtil.getUng()=" + hosoThanhToanUtil.getUng());
			log.info("hosoThanhToanUtil.getTra()=" + hosoThanhToanUtil.getTra());
			soDu = com.iesvn.yte.util.Utils.formatLongDouble(hosoThanhToanUtil.getSoDu());
		}
		bao.ttc: end old code */
		
		// bao.ttc: new code
	 		HsThtoan hsThtoan = new HsThtoan();
			hsThtoan.setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());

			tinhToanChoHSTT(hsThtoan,hsbaKhoa.getHsbaSovaovien());
			Utils.setInforForHsThToan(hsThtoan);

			daUng = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanTamung());
			daTra = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanHoanung());
			soDu = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanThtoan());			
	}
	
	private void tinhToanChoHSTT(HsThtoan hstt, Hsba hsba) {
		HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
		hsthtoanUtil.tinhToanChoHSTT(hstt);

	}
	
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		disabledPrinting = true;
		log.info(hoanUng.getHoanungSotien());
		ghiNhanException = null;
		
		HoanUngDelegate hoanUngDelegate = HoanUngDelegate.getInstance();
		if (hoanUng != null) {
			try {	
				if(StringUtils.isBlank(hoanUng.getHoanungMa())) {	
					TamUng tu = TamUngDelegate.getInstance().findByMaHsba(hoanUng.getHsbaSovaovien().getHsbaSovaovien());
					hoanUng.setHoanungMaphieu(tu);
					hoanUng.setHoanungThungan(nhanVienThungan);
					// phuc.lc 04-11-2010 : Fix bug 1349
					hoanUng.setHoanungNgaygio(Calendar.getInstance().getTime());
					hoanUng = hoanUngDelegate.create(hoanUng);
				} else {
					hoanUngDelegate.edit(hoanUng);
				}
				FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.PHIEU_SO, hoanUng.getHoanungMa());
				disabledPrinting = false;
			}catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.FAIL);
				log.error(e.toString());
				ghiNhanException = e.getClass().getName();
				log.error("Ghi nhan khong thanh cong");
				
			}	
		}
			
		//XuatReport();
		log.info("***Finished ghinhan **");
		//return "B3360_Chonmenuxuattaptin";
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
		this.hoanUng = null;
		this.getHoanUng(true).setHoanungNgaygio(new java.util.Date());
		this.benhNhan = null;
		this.hsbaBhyt = null;
		//this.hsThtoan = null;
		// bao.ttc: this.hosoThanhToanUtil = null;
		this.soDu = null;
		this.daUng = null; //bao.ttc
		this.daTra = null; //bao.ttc
		this.soTheBH_SoTheNgheo = null;
		this.donViTuoi = null;
		this.maPhu = new java.util.Date().getTime() + "";  
		disabledPrinting = true;
		disabledGhinhan = false;		
		log.info("End ResetForm(): ");
	}
	
	// phuc.lc 21-03-2011 : Begin fix bug 2490
	private boolean checkDaTT(String sovaovien) {
		HsThtoan hsTmp = HsThtoanDelegate.getInstance().findBySovaovien(sovaovien);
		if (hsTmp != null) {
			return (hsTmp.getHsthtoanDatt() == null ? false : hsTmp.getHsthtoanDatt().booleanValue()); 
		}
		return false;
	}
	// phuc.lc 21-03-2011 : End fix bug 2490
	
	public HoanUng getHoanUng(boolean create) {
		if(create && hoanUng == null) hoanUng = new HoanUng();
		return hoanUng;
	}
	public HoanUng getHoanUng() {
		return hoanUng;
	}
	public void setHoanUng(HoanUng hoanUng) {
		this.hoanUng = hoanUng;
	}
	public BenhNhan getBenhNhan() {
		return benhNhan;
	}
	public BenhNhan getBenhNhan(boolean create) {
		if(create && benhNhan == null) benhNhan = new BenhNhan();
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}


	public HsbaBhyt getHsbaBhyt() {
		return hsbaBhyt;
	}
	public HsbaBhyt getHsbaBhyt(boolean create) {
		if(create && hsbaBhyt == null) {
			hsbaBhyt = new HsbaBhyt();
		}
		return hsbaBhyt;
	}

	public void setHsbaBhyt(HsbaBhyt hsbaBhyt) {
		this.hsbaBhyt = hsbaBhyt;
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

	public String getSoTheBH_SoTheNgheo() {
		return soTheBH_SoTheNgheo;
	}
	public void setSoTheBH_SoTheNgheo(String soTheBH_SoTheNgheo) {
		this.soTheBH_SoTheNgheo = soTheBH_SoTheNgheo;
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
	public String getDaUng() {
		return daUng;
	}
	public void setDaUng(String daUng) {
		this.daUng = daUng;
	}
	public String getDaTra() {
		return daTra;
	}
	public void setDaTra(String daTra) {
		this.daTra = daTra;
	}
// bao.ttc:	public HoSoThanhToanUtil getHosoThanhToanUtil() {
//		return hosoThanhToanUtil;
//	}
//	public void setHosoThanhToanUtil(HoSoThanhToanUtil hosoThanhToanUtil) {
//		this.hosoThanhToanUtil = hosoThanhToanUtil;
//	}
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		ChiTraTienTamUngAction.log = log;
	}
	public String getReportPathVP() {
		return reportPathVP;
	}
	public void setReportPathVP(String reportPathVP) {
		this.reportPathVP = reportPathVP;
	}
	public String getReportTypeVP() {
		return reportTypeVP;
	}
	public void setReportTypeVP(String reportTypeVP) {
		this.reportTypeVP = reportTypeVP;
	}
	public JasperPrint getJasperPrintVP() {
		return jasperPrintVP;
	}
	public void setJasperPrintVP(JasperPrint jasperPrintVP) {
		this.jasperPrintVP = jasperPrintVP;
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
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	public boolean getDisabledPrinting() {
		return disabledPrinting;
	}
	public void setDisabledPrinting(boolean disabledPrinting) {
		this.disabledPrinting = disabledPrinting;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isDisabledGhinhan() {
		return disabledGhinhan;
	}
	public void setDisabledGhinhan(boolean disabledGhinhan) {
		this.disabledGhinhan = disabledGhinhan;
	}	
	
	
}
