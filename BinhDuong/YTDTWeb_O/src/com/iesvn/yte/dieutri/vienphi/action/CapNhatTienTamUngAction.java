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
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.delegate.TamUngDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.dieutri.entity.TamUng;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3212_CapNhatTienTamUng")
@Synchronized(timeout = 6000000)
public class CapNhatTienTamUngAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatTienTamUngAction.class);
	@In(required = false)
	private Identity identity;
	@Out(required=false)
	@In(required=false)
	private String reportPathVP=null;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP=null;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP=null;
	
	private static int index=0;
	private BenhNhan benhNhan;
	private HsbaBhyt hsbaBhyt;	
	private TamUng tamUng;
	//private HsThtoan hsThtoan;
	private String ghiNhanException;
	private String maPhu;
	private String tamUng_HoanUng;
	private String soDu;
	private String soTheBH_SoTheNgheo;
	private String tienBangChu;
	private String donViTuoi;
	private String sLiDoNop="";
	private String lanTamung="";
	//@Out(required=false)
	//private String tienbangchu = null;
	

	
	private DtDmCum cum = null;
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
	private boolean disabledPrinting = true;
	private boolean disabledGhinhan = false;
	
	@Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
	@Create
	@Begin(join=true)
	public String init() throws Exception {
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
		
		//this.sLiDoNop = getTamUng(true).getTamungKhoa(true).getDmkhoaTen()+ IConstantsRes.LI_DO_NOP_TAM_UNG_THEO_KHOA;
		this.getTamUng(true).setTamungNgaygio(new java.util.Date());
		this.maPhu = new java.util.Date().getTime() + "";	
		log.info("***Finished init **");
		
		//Manager.instance().endConversation(true);
		return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatTienTamUng";
		
	}
	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		log.info("***Finished back **");
		return "MyMainForm";
	}
	public String xuatReport() {
		MyXuatReport();
	return "B3360_Chonmenuxuattaptin";
}


public void MyXuatReport() {
	reportTypeVP="giaytamung";
	log.info("Vao Method XuatReport thuoc y dung cu phong kham, lanTamung = " + lanTamung);
	String baocao1=null;
	String pathTemplate=null;
	try {
		pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/PhieuThu.jrxml";
		
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
		
		params.put("tienThuoc", tamUng.getTamungSotien());
		params.put("TIENBANGCHU", Utils.NumberToString(tamUng.getTamungSotien()));
		
		params.put("LIDONOP",tamUng.getTamungLydo() != null ? tamUng.getTamungLydo() : "");
		params.put("LANTAMUNG", lanTamung);
		// phuc.lc 21-04-2011 : Fix bug 2655
		int lanIn = tamUng.getTamungIn() == null ? 1 : (Integer.valueOf(tamUng.getTamungIn()).intValue() + 1);
		tamUng.setTamungIn("" + lanIn);
		params.put("LANIN", "" + lanIn);
		//phuc.lc 21-05-2011 : Fix bug 3044		
		params.put("SOPHIEU",tamUng.getTamungMa());
		params.put("QUYEN", tamUng.getTamungQuyen());
		//phuc.lc 12-09-2011 : Fix bug 3983
		log.info("nv maso = "+ nhanVienThungan);
		DtDmNhanVien nv = DtDmNhanVienDelegate.getInstance().find(nhanVienThungan.getDtdmnhanvienMaso());
		params.put("nguoiLapPhieu", (nv == null) ? "" : nv.getDtdmnhanvienTen());
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
		    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","giaytamung");
		    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
		    log.info("duong dan file xuat report :" + baocao1);
		    log.info("duong dan -------------------- :"+reportPathVP);
		    index+= 1;
		    log.info("Index :" + index);
		    if(conn != null) conn.close();
		 // phuc.lc 21-04-2011 : Fix bug 2655
		    TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
		    tamUngDelegate.edit(tamUng);
	}catch (Exception e) {
	    log.info("ERROR Method XuatReport!!!");
		e.printStackTrace();
	}
    log.info("Thoat Method XuatReport");
}

	public void layTheoSoVaoVien() throws Exception {
		log.info("***Starting layTheoSoVaoVien ***");
		disabledPrinting = true;
		if(tamUng.getHsbaSovaovien() != null && tamUng.getHsbaSovaovien().getHsbaSovaovien() != null) {
			try {
				HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
				Hsba hsbaCur = hsbaDelegate.find(tamUng.getHsbaSovaovien().getHsbaSovaovien());
			    if  (hsbaCur == null){
			    	FacesMessages.instance().add(IConstantsRes.HSBA_NULL, tamUng.getHsbaSovaovien().getHsbaSovaovien());
					return ;
			    }
			    tamUng.setHsbaSovaovien(hsbaCur);
			    tamUng.setTamungKhoa(hsbaCur.getHsbaKhoadangdt(true));
			    
				qryBenhNhanBhtyThanhToan(tamUng.getTamungKhoa().getDmkhoaMa(), tamUng.getHsbaSovaovien().getHsbaSovaovien());
				FacesMessages.instance().clear();
				if (benhNhan == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, tamUng.getTamungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, tamUng.getHsbaSovaovien().getHsbaSovaovien());
				} else if (disabledGhinhan) {
					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				}
			} catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, tamUng.getTamungKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, tamUng.getHsbaSovaovien().getHsbaSovaovien());
				log.error(e.toString());
			}	
			
			this.maPhu = new java.util.Date().getTime() + "";
			log.debug(tamUng.getHsbaSovaovien());
			log.debug(tamUng.getTamungKhoa().getDmkhoaMa());
		}
		log.info("***Finished layTheoSoVaoVien **");
		
	}
	public void layTheoTamUngMa() throws Exception {
		log.info("***Starting layTheoTamUngMa ***");
		disabledPrinting = true;
		if (tamUng == null) return;
		String tamUngMa = tamUng.getTamungMa();
		TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
		try {
			tamUng = tamUngDelegate.find(tamUng.getTamungMa());
			
			if (tamUng == null) {
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, tamUngMa);
			}
		} catch(Exception e) {
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, tamUngMa);
			log.error(e.toString());
		}	
		
		
		if(tamUng != null && tamUng.getTamungKhoa() != null && tamUng.getTamungKhoa().getDmkhoaMa() != null
				&& tamUng.getHsbaSovaovien() != null && tamUng.getHsbaSovaovien().getHsbaSovaovien() != null) {
			sLiDoNop = tamUng.getTamungLydo();
			if(tamUng.getTamungThungan(true)!=null)
				nhanVienThungan = tamUng.getTamungThungan(true);
			log.info("*****NHAN VIEN THU NGAN: "+nhanVienThungan);
			
			qryBenhNhanBhtyThanhToan(tamUng.getTamungKhoa().getDmkhoaMa(), tamUng.getHsbaSovaovien().getHsbaSovaovien());
			this.maPhu = tamUng.getTamungMa();
			disabledPrinting = false;
			if(disabledGhinhan) {
				FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
			}
		}else{
			benhNhan = null;
			hsbaBhyt = null;
			//hsThtoan = null;
		}
		
		log.info("***Finished layTheoTamUngMa **");
		
	}
	private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien) {
		
		
		HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
		
		// phuc.lc 21-07-2011 : Fix bug 3736
		// 20120312 bao.ttc: khong dung hsbaKhoa ma tim truc tiep HSBA
		HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(soVaoVien, khoa);
		if (hsbaKhoa == null) {
			log.info("hsbaKhoa11:"+hsbaKhoa);
			
			//FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");
			benhNhan = null;
			hsbaBhyt = null;
			
			return;
		}	
		// kiem tra khoaMa co' phai la khoa cuoi cung ko
		log.info("maKhoa:"+khoa);
		if (!khoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa())){
			log.info("hsbaKhoa22:"+hsbaKhoa);
			
			//FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");
			benhNhan = null;
			hsbaBhyt = null;
			
			return;
		}
		
		tamUng.getHsbaSovaovien().setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien());
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
		
		HsThtoan hsThtoan = new HsThtoan();
		hsThtoan.setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());

		tinhToanChoHSTT(hsThtoan,hsbaKhoa.getHsbaSovaovien());
		Utils.setInforForHsThToan(hsThtoan);

		log.info("hsThtoan.getHsthtoanTamung():"
				+ hsThtoan.getHsthtoanTamung());
		log.info("hsThtoan.getHsthtoanHoanung():"
				+ hsThtoan.getHsthtoanHoanung());

		tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble( hsThtoan.getHsthtoanTamung()- hsThtoan.getHsthtoanHoanung());
		log.info("ungTra:" + tamUng_HoanUng);
		
		//bao.ttc: co the hien so am hay ko??
		//soDu = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanThtoan()).replace("-", "");
		soDu = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanThtoan());
		System.out.println("CapNhatTienTamUngAction.qryBenhNhanBhtyThanhToan()*****soDu=" + soDu);
		// Dem so lan da tam ung
		TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
		lanTamung = "" + tamUngDelegate.countSolanTamUngByHsba(soVaoVien);
		
		
	}
	
	
	private void tinhToanChoHSTT(HsThtoan hstt, Hsba hsba) {
		HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
		hsthtoanUtil.tinhToanChoHSTT(hstt);

	}
		
	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		disabledPrinting = true;
		log.info(tamUng.getTamungSotien());
		ghiNhanException = null;
		
		TamUngDelegate tamUngDelegate = TamUngDelegate.getInstance();
		if (tamUng != null) {
			try {
				DtDmNhanVien userNv = DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
				if (userNv != null) {
					PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
					PcCumThuPhi pcCumThuPhi = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(tamUng.getTamungNgaygio(), userNv.getDtdmnhanvienMa());
					if (pcCumThuPhi != null){
						tamUng.setTamungCum(pcCumThuPhi.getDtdmcumMa());
						tamUng.setTamungLydo(sLiDoNop);
					}
				}
				
				if(StringUtils.isBlank(tamUng.getTamungMa())) {
					tamUng.setTamungThungan(nhanVienThungan);
					tamUng.setTamungLydo(sLiDoNop);
					tamUng.setTamungNgaygio(Calendar.getInstance().getTime());
					tamUng = tamUngDelegate.create(tamUng);
					lanTamung = "" + (Integer.valueOf(lanTamung).intValue() + 1);
				} else {
					tamUng.setTamungNgaygio(Calendar.getInstance().getTime());
					tamUng.setTamungLydo(sLiDoNop);
					tamUngDelegate.edit(tamUng);
				}
				FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.PHIEU_SO, tamUng.getTamungMa());
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
		this.sLiDoNop = "";
		this.tamUng = null;
		this.soTheBH_SoTheNgheo=null;
		this.getTamUng(true).setTamungNgaygio(new java.util.Date());
		this.benhNhan = null;
		this.hsbaBhyt = null;
		//this.hsThtoan = null;
		this.soDu = null;
		this.tamUng_HoanUng = null;
		this.soTheBH_SoTheNgheo = null;
		this.tienBangChu = null;
		this.donViTuoi = null;
		this.maPhu = new java.util.Date().getTime() + "";
		disabledPrinting = true;
		disabledGhinhan = false;
		lanTamung = "";
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


	public TamUng getTamUng() {
		return tamUng;
	}
	public TamUng getTamUng(boolean create) {
		if(create && tamUng == null) {
			tamUng = new TamUng();
		}
		return tamUng;
	}

	public void setTamUng(TamUng tamUng) {
		this.tamUng = tamUng;
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
	public String getDonViTuoi() {
		return donViTuoi;
	}
	public void setDonViTuoi(String donViTuoi) {
		this.donViTuoi = donViTuoi;
	}
	public static void setIndex(int index) {
		CapNhatTienTamUngAction.index = index;
	}
	public static int getIndex() {
		return index;
	}
	/*
	public void setTienbangchu(String tienbangchu) {
		this.tienbangchu = tienbangchu;
	}
	public String getTienbangchu() {
		return tienbangchu;
	}
	*/
	public String getSoDu() {
		return soDu;
	}
	public void setSoDu(String soDu) {
		this.soDu = soDu;
	}
	
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
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
	public boolean getDisabledPrinting() {
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
	public boolean isDisabledGhinhan() {
		return disabledGhinhan;
	}
	public void setDisabledGhinhan(boolean disabledGhinhan) {
		this.disabledGhinhan = disabledGhinhan;
	}
	public String getLanTamung() {
		return lanTamung;
	}
	public void setLanTamung(String lanTamung) {
		this.lanTamung = lanTamung;
	}
	
	
}
