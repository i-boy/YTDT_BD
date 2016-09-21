/*
 * author : Bao
 */
package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmDienMienDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmLoaiMienDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.delegate.MienGiamDelegate;
import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.DtDmDienMien;
import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.MienGiam;
import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B3213_CapNhatMienGiam")
@Synchronized(timeout = 6000000)
public class CapNhatMienGiamAction implements Serializable {
	private static final long serialVersionUID = 10L;
	private transient static Logger log = Logger.getLogger(CapNhatMienGiamAction.class);
	@In(required = false)
	private Identity identity;
	private BenhNhan benhNhan;
	private HsbaBhyt hsbaBhyt;	
	private MienGiam mienGiam;
	//private HsThtoan hsThtoan;
	private HoSoThanhToanUtil hosoThanhToanUtil;
	private String ghiNhanException;
	private String maPhu;
	private String tamUng_HoanUng;
	private String mienDT;
	private String soDu;
	private String soTheBH_SoTheNgheo;
	private String donViTuoi;
	private Date ngayhientai;
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
	
	private DtDmCum cum = null;
	private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
	
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
		
		
		
		this.maPhu = new java.util.Date().getTime() + "";
		mienGiam = new MienGiam();
		mienGiam.setMiengiamNgay(new java.util.Date());
		mienGiam.setMiengiamNgayky(new java.util.Date());
		ngayhientai = new Date();
		log.info("***Finished init **");
		
	}
	
	@End
	public String back() throws Exception {
		log.info("***Starting back ***");
		
		log.info("***Finished back **");
		return "MyMainForm";
		
	}
	public void layTheoSoVaoVien() throws Exception {
		log.info("***Starting layTheoSoVaoVien ***");
		disabledPrinting = true;
		if(mienGiam.getMiengiamKhoa() != null && mienGiam.getMiengiamKhoa().getDmkhoaMa() != null
				&& mienGiam.getHsbaSovaovien() != null && mienGiam.getHsbaSovaovien().getHsbaSovaovien() != null) {
			try {
				qryBenhNhanBhtyThanhToan(mienGiam.getMiengiamKhoa().getDmkhoaMa(), mienGiam.getHsbaSovaovien().getHsbaSovaovien());
				if (benhNhan == null) {
					FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, mienGiam.getMiengiamKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, mienGiam.getHsbaSovaovien().getHsbaSovaovien());
				} else if(disabledGhinhan) {
					FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
				}
			} catch(Exception e) {
				e.printStackTrace();
				FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", IConstantsRes.KHOA_PHONG, mienGiam.getMiengiamKhoa().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, mienGiam.getHsbaSovaovien().getHsbaSovaovien());
				log.error(e.toString());
			}	
			
			this.maPhu = new java.util.Date().getTime() + "";
			log.debug(mienGiam.getHsbaSovaovien());
			log.debug(mienGiam.getMiengiamKhoa().getDmkhoaMa());
		}
		log.info("***Finished layTheoSoVaoVien **");
		
	}
	
	/**
	 * xuat report 
	 */
	public String xuatReport() {		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String gt="";
		String diachi="";
		String baocao1=null;
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"vienphi/giaymiengiam.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			log.info("Ten benh nhan " + benhNhan.getBenhnhanHoten());
			params.put("TEN", benhNhan.getBenhnhanHoten());
			params.put("TUOI", benhNhan.getBenhnhanTuoi());
			if(benhNhan.getDmgtMaso() != null) {
				gt = benhNhan.getDmgtMaso().getDmgtTen();
			} 
			
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			log.info(String.format("-----tenSo: %s", params.get("tenSo")));
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			log.info(String.format("-----tenDonVi: %s", params.get("tenDonVi")));
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			
			params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
						
			params.put("GT", gt);
			if(benhNhan.getDantocMa() != null) {
				log.info("Dan toc " + benhNhan.getDantocMa().getDmdantocTen());
				params.put("DANTOC", benhNhan.getDantocMa().getDmdantocTen() );
			} else {
				params.put("DANTOC", "");
			}
			if (benhNhan.getBenhnhanDiachi() != null) {
				diachi = diachi + benhNhan.getBenhnhanDiachi();
			}
			if (benhNhan.getXaMa() != null) {
				if (benhNhan.getXaMa().getDmxaMaso() != null) {
					diachi += (diachi.trim().length() > 0 ? ", " + benhNhan.getXaMa().getDmxaTen() : benhNhan.getXaMa().getDmxaTen());
				}
			}
			if (benhNhan.getHuyenMa() != null) {
				if (benhNhan.getHuyenMa().getDmhuyenMaso() != null) {
					diachi += (diachi.trim().length() > 0 ? ", " + benhNhan.getHuyenMa().getDmhuyenTen() : benhNhan.getHuyenMa().getDmhuyenTen());
				}
			}
			if (benhNhan.getTinhMa() != null) {
				if (benhNhan.getTinhMa().getDmtinhMaso() != null) {
				diachi += (diachi.trim().length() > 0 ? ", " + benhNhan.getTinhMa().getDmtinhTen() : benhNhan.getTinhMa().getDmtinhTen());
				}
			}
			//if(benhNhan.getXaMa() != null && benhNhan.getHuyenMa() != null && benhNhan.getTinhMa() != null) {
			//	diachi=benhNhan.getBenhnhanDiachi()+","+benhNhan.getXaMa().getDmxaTen()+","+benhNhan.getHuyenMa().getDmhuyenTen()+","+benhNhan.getTinhMa().getDmtinhTen();
			//}
			log.info("Dia chi " + diachi);
			params.put("DIACHI", diachi );
			//log.info("khoa " + mienGiam.getMiengiamKhoa() + " " + mienGiam.getMiengiamKhoa().getDmkhoaTen() );
			if(mienGiam.getMiengiamKhoa()!=null){			
				log.info("khoa " + mienGiam.getMiengiamKhoa().getDmkhoaTen());
				params.put("KHOA", mienGiam.getMiengiamKhoa().getDmkhoaTen() );
			} else {
				params.put("KHOA", "");
			}
			log.info("So the BHYT " + hsbaBhyt.getHsbabhytSothebh());
			params.put("SOTHEBH", (hsbaBhyt.getHsbabhytSothebh() == null ? "" : hsbaBhyt.getHsbabhytSothebh()));
			if (hsbaBhyt.getHsbabhytMakcb() != null) {
				log.info("KCB " + hsbaBhyt.getHsbabhytMakcb().getDmbenhvienTen());
				params.put("KCB", hsbaBhyt.getHsbabhytMakcb().getDmbenhvienTen());
			} else {
				params.put("KCB", "");
			}
			log.info("Tuyen " + String.valueOf(hsbaBhyt.getHsbabhytTuyen()));
			if(hsbaBhyt.getHsbabhytTuyen()==null)
				params.put("TUYEN", "");
			else
				params.put("TUYEN", String.valueOf(hsbaBhyt.getHsbabhytTuyen()));
			log.info("mien giam " + String.valueOf(mienGiam.getMiengiamSotien()));
			params.put("SOTIEN", mienGiam.getMiengiamSotien() );
			params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM );
			if(mienGiam.getMiengiamNgay()!= null)
				params.put("NGAYLAP", sdf.format(mienGiam.getMiengiamNgay()));
			if(mienGiam.getMiengiamNgayd()!= null)
				params.put("TUNGAY", sdf.format(mienGiam.getMiengiamNgayd()));
			if(mienGiam.getMiengiamNgayc()!= null)
				params.put("DENNGAY", sdf.format(mienGiam.getMiengiamNgayc()));
			
			params.put("TYLEGIAM", (mienGiam.getMiengiamTyle() ==null)?"": String.valueOf(mienGiam.getMiengiamTyle()));
			
			log.info("MIEN GIAM MA SO " + mienGiam.getMiengiamMa());
			
			log.info("LOAIGIAM " + mienGiam.getMiengiamLoaimien() + " " + mienGiam.getMiengiamLoaimien().getDtdmloaimienTen() );
			if(mienGiam.getMiengiamLoaimien()!= null){
				DtDmLoaiMien dmLoaimien = DtDmLoaiMienDelegate.getInstance().find(mienGiam.getMiengiamLoaimien().getDtdmloaimienMaso());
				log.info("LOAIGIAM 2, " + dmLoaimien.getDtdmloaimienTen() );
				params.put("LOAIGIAM", dmLoaimien.getDtdmloaimienTen());
			} else {
				params.put("LOAIGIAM", "");
			}
			params.put("LYDOGIAM", mienGiam.getMiengiamLydo() == null ? "" : mienGiam.getMiengiamLydo());
			
			if(mienGiam.getMiengiamDoituong() !=null && mienGiam.getMiengiamDoituong().getDtdmdienmienMaso() != null) {
				DtDmDienMien dmDienMien = DtDmDienMienDelegate.getInstance().find(mienGiam.getMiengiamDoituong().getDtdmdienmienMaso());
				params.put("DOITUONGGIAM", dmDienMien.getDtdmdienmienTen());
			} else {
				params.put("DOITUONGGIAM", "");
			}
			log.info("NGUOIDUYET "+ mienGiam.getMiengiamNguoiduyet());
			if(mienGiam.getMiengiamNguoiduyet() !=null && mienGiam.getMiengiamNguoiduyet().getDtdmnhanvienMaso() !=null) {
				DtDmNhanVien dmNv = DtDmNhanVienDelegate.getInstance().find(mienGiam.getMiengiamNguoiduyet().getDtdmnhanvienMaso());
				log.info("NGUOIDUYET 2 "+ dmNv.getDtdmnhanvienMaso() + " - " + dmNv.getDtdmnhanvienTen());
				//params.put("NGUOIDUYET", mienGiam.getMiengiamNguoiduyet().getDtdmnhanvienTen());
				params.put("NGUOIDUYET", dmNv.getDtdmnhanvienTen());
			} else {
				params.put("NGUOIDUYET", "");
			}
			params.put("SOPHIEU", mienGiam.getMiengiamMaphieu() == null ? "" : mienGiam.getMiengiamMaphieu());
			
			// phuc.lc 21-04-2011 : Fix bug 2655
			int lanIn = mienGiam.getMiengiamIn() == null ? 1 : (Integer.valueOf(mienGiam.getMiengiamIn()).intValue() + 1);
			mienGiam.setMiengiamIn("" + lanIn);
			params.put("LANIN", "" + lanIn);
			
			reportTypeVP="giaymiengiam";
			log.info("Vao Method XuatReport Giay mien giam");
			log.info("================= ");
		    jasperPrintVP =  JasperFillManager.fillReport(jasperReport,params);
		    baocao1=XuatReportUtil.ReportUtil(jasperPrintVP,CapNhatMienGiamAction.getIndex(),IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/", "pdf","giaymiengiam");
		    reportPathVP=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
		    log.info("duong dan file xuat report :" + baocao1);
		    log.info("duong dan -------------------- :"+reportPathVP);
		    CapNhatMienGiamAction.setIndex(CapNhatMienGiamAction.getIndex() + 1);
		    log.info("Index :" + getIndex() );
		    
		    DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		    MienGiam mienGiamTmp = (MienGiam)dieuTriUtilDelegate.findByMaWithFormatMaPhieu(mienGiam.getMiengiamMaphieu(), "MienGiam", "miengiamMaphieu");
		    if(mienGiamTmp != null) {
		    	MienGiamDelegate mienGiamDelegate = MienGiamDelegate.getInstance();
		    	mienGiamTmp.setMiengiamIn("" + lanIn);
		    	mienGiamDelegate.edit(mienGiamTmp);
		    }
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	    return "B3360_Chonmenuxuattaptin";
	}
	
	
	public void layTheoMienGiamMa() throws Exception {
		log.info("***Starting layTheoMienGiamMa ***");
		disabledPrinting = true;
		if (mienGiam == null) return;
		String maPhieu = mienGiam.getMiengiamMaphieu();
		log.info("mienGiam.getMiengiamMaphieu()=" + maPhieu);
		DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
		try {
			mienGiam = (MienGiam)dieuTriUtilDelegate.findByMaWithFormatMaPhieu(mienGiam.getMiengiamMaphieu(), "MienGiam", "miengiamMaphieu");
			//mienGiam = mienGiamDelegate.find(mienGiam.getMiengiamMa());
			log.info("findByMaWithFormatMaPhieu ... end");
			log.info("mienGiam=" + mienGiam);
			if(mienGiam != null && mienGiam.getHsbaSovaovien() != null) {
				nhanVienThungan = mienGiam.getMiengiamThungan();
				log.info("mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov()=" + mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov());
			}
		} catch(Exception e) {
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, maPhieu);
			log.error(e.toString());
		}	
		if(mienGiam != null && mienGiam.getMiengiamKhoa() != null && mienGiam.getMiengiamKhoa().getDmkhoaMa() != null
				&& mienGiam.getHsbaSovaovien() != null && mienGiam.getHsbaSovaovien().getHsbaSovaovien() != null) {
			
			qryBenhNhanBhtyThanhToan(mienGiam.getMiengiamKhoa().getDmkhoaMa(), mienGiam.getHsbaSovaovien().getHsbaSovaovien());
			if(disabledGhinhan) {
				FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA);
			}
			this.maPhu = mienGiam.getMiengiamMaphieu() + "";
			disabledPrinting = false;
		}else{
			FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}.", IConstantsRes.PHIEU_SO, maPhieu);
			benhNhan = null;
			hsbaBhyt = null;
			//hsThtoan = null;
			hosoThanhToanUtil = null;
		}
		log.info("***Finished layTheoMienGiamMa **");
		
	}
	private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien) {
		
		
		HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
		
		log.info("hoSoBenhAn.getHsbaSovaovien():"+soVaoVien);
		
		// 20120312 bao.ttc: khong can tim hsbaKhoa ma tin truc tiep HSBA -- Giao dien: disable Khoa
		HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(soVaoVien, khoa);
		if (hsbaKhoa == null) {
			log.info("hsbaKhoa1:"+hsbaKhoa);
			
			//FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");
			benhNhan = null;
			hsbaBhyt = null;
			
			return;
		}	
		// kiem tra khoaMa co' phai la khoa cuoi cung ko
		log.info("maKhoa:"+khoa);
		if (!khoa.equals(hsbaKhoa.getKhoaMa().getDmkhoaMa())){
			log.info("hsbaKhoa1:"+hsbaKhoa);
			
			//FacesMessages.instance().add(IConstantsRes.KHOA_HSBA_NULL,"","");
			benhNhan = null;
			hsbaBhyt = null;
			
			return;
		}
		
		
//		HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
//		HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(soVaoVien, khoa);
//		if (hsbaChuyenMon == null || hsbaChuyenMon.getHsbaSovaovien() == null) {
//			benhNhan = null;
//			hsbaBhyt = null;
//			//hsThtoan = null;
//			hosoThanhToanUtil = null;
//			
//			return;
//		}
		//mienGiam.getHsbaSovaovien().setHsbaSovaovien(hsbaChuyenMon.getHsbaSovaovien().getHsbaSovaovien());
		mienGiam.setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());
		soVaoVien = hsbaKhoa.getHsbaSovaovien().getHsbaSovaovien();
		disabledGhinhan = checkDaTT(soVaoVien);
		if(mienGiam != null && mienGiam.getHsbaSovaovien() != null) {
			log.info("mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov()=" + mienGiam.getHsbaSovaovien().getHsbaNgaygiovaov());
		}
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
		/*
		HsThtoanDelegate hsThtoanDelegate = HsThtoanDelegate.getInstance();
		hsThtoan = hsThtoanDelegate.findBySovaovien(soVaoVien);
		*/
		/* bao.ttc: ko dung cach nay vi ko tinh dung KQ
		hosoThanhToanUtil = new HoSoThanhToanUtil(hsbaKhoa.getHsbaSovaovien());
		if (hosoThanhToanUtil != null) {
			log.info("hosoThanhToanUtil.getUng()=" + hosoThanhToanUtil.getUng());
			log.info("hosoThanhToanUtil.getTra()=" + hosoThanhToanUtil.getTra());
			//tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hosoThanhToanUtil.getUng()) + "-" + com.iesvn.yte.util.Utils.formatLongDouble(hosoThanhToanUtil.getTra());
			tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hosoThanhToanUtil.getUng() - hosoThanhToanUtil.getTra());
			if ( hosoThanhToanUtil.getUng() == null && hosoThanhToanUtil.getTra() == null) {
				tamUng_HoanUng = "0";
			} else if ( hosoThanhToanUtil.getUng() != null && hosoThanhToanUtil.getTra() != null) {
				tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hosoThanhToanUtil.getUng() - hosoThanhToanUtil.getTra());
			} else if ( hosoThanhToanUtil.getUng() != null) {
				tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(hosoThanhToanUtil.getUng());
			} else {
				tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble(- hosoThanhToanUtil.getTra());
			}
		}  end old code */
		
		HsThtoan hsThtoan = new HsThtoan();
		hsThtoan.setHsbaSovaovien(hsbaKhoa.getHsbaSovaovien());
		log.info("before tinhToanChoHSTT()");
		tinhToanChoHSTT(hsThtoan,hsbaKhoa.getHsbaSovaovien());
		log.info("After tinhToanChoHSTT()");
		Utils.setInforForHsThToan(hsThtoan);

		log.info("hsThtoan.getHsthtoanTamung(): " + hsThtoan.getHsthtoanTamung());
		log.info("hsThtoan.getHsthtoanHoanung():" + hsThtoan.getHsthtoanHoanung());

		tamUng_HoanUng = com.iesvn.yte.util.Utils.formatLongDouble( hsThtoan.getHsthtoanTamung()- hsThtoan.getHsthtoanHoanung());
		log.info("ungTra:" + tamUng_HoanUng);
		
		mienDT = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanMiendt());
		log.info("hsThtoan.getHsthtoanMiendt():" + mienDT);
		//bao.ttc: co the hien so am hay ko??
		//soDu = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanThtoan()).replace("-", "");
		soDu = com.iesvn.yte.util.Utils.formatLongDouble(hsThtoan.getHsthtoanThtoan());
		System.out.println("CapNhatTienTamUngAction.qryBenhNhanBhtyThanhToan()*****soDu=" + soDu);
		
	}
	
	
	private void tinhToanChoHSTT(HsThtoan hstt, Hsba hsba) {
		HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
		hsthtoanUtil.tinhToanChoHSTT(hstt);

	}

	// Ham ghi nhan thuc thi sau khi nhan nut Ghi Nhan
	public void ghinhan() {
		log.info("***Starting ghinhan **");
		ghiNhanException = null;
		disabledPrinting = true;
		MienGiamDelegate mienGiamDelegate = MienGiamDelegate.getInstance();
		if (mienGiam != null) {	
			try {
				if (mienGiam.getMiengiamDoituong(true).getDtdmdienmienMaso() == null) {
					mienGiam.setMiengiamDoituong(null);
				}
				if (mienGiam.getMiengiamLoaimien(true).getDtdmloaimienMaso() == null) {
					mienGiam.setMiengiamLoaimien(null);
				}
				if(mienGiam.getMiengiamNguoiduyet(true).getDtdmnhanvienMaso() == null) {
					mienGiam.setMiengiamNguoiduyet(null);
				}
				DtDmNhanVien userNv = DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername());
				if (userNv != null) {
					PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
					PcCumThuPhi pcCumThuPhi = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(mienGiam.getMiengiamNgay(), userNv.getDtdmnhanvienMa());
					if (pcCumThuPhi != null){
						mienGiam.setMiengiamCum(pcCumThuPhi.getDtdmcumMa());
					}
				}	
				log.info("ma : "+mienGiam.getMiengiamDoituong(true).getDtdmdienmienMa());
				log.info("maso : "+mienGiam.getMiengiamDoituong(true).getDtdmdienmienMaso());
				
				if(mienGiam.getMiengiamMa() == null) {	
					mienGiam.setMiengiamThungan(nhanVienThungan);
					mienGiam = mienGiamDelegate.create(mienGiam);
				} else {
					mienGiamDelegate.edit(mienGiam);
				}
			
				log.info("save miengiam thanh cong");
				HsThtoanDelegate hsThtoanDelegate = HsThtoanDelegate.getInstance();
				HsThtoan hsThtoan = hsThtoanDelegate.findBySovaovien(mienGiam.getHsbaSovaovien().getHsbaSovaovien());
				if (hsThtoan != null) {
					hsThtoanDelegate.remove(hsThtoan);
				}

				hsThtoan = new HsThtoan();
				hsThtoan.setHsbaSovaovien(mienGiam.getHsbaSovaovien());
				
				//20101209 bao.ttc: set khoa cho HSTT
				if(mienGiam.getMiengiamKhoa() != null){
					hsThtoan.setHsthtoanKhoa(mienGiam.getMiengiamKhoa());
				}
				
				HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(mienGiam.getHsbaSovaovien());
				hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
				Utils.setInforForHsThToan(hsThtoan);
				hsThtoanDelegate.create(hsThtoan);
			
				FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", IConstantsRes.PHIEU_SO, mienGiam.getMiengiamMaphieu());
				disabledPrinting = false;
			}catch(Exception e) {
				FacesMessages.instance().add(IConstantsRes.FAIL);
				log.error(e.toString());
				e.printStackTrace();
				ghiNhanException = e.getClass().getName();
				log.error("Ghi nhan khong thanh cong");
			}
		}
		log.info("disabledPrinting = " + disabledPrinting);
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
		mienGiam = new MienGiam();
		mienGiam.setMiengiamNgay(new java.util.Date());
		mienGiam.setMiengiamNgayky(new java.util.Date());
		this.benhNhan = null;
		this.hsbaBhyt = null;
		//this.hsThtoan = null;
		this.hosoThanhToanUtil = null;
		this.tamUng_HoanUng = null;
		this.mienDT = null;
		this.soDu = null;
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

	public String getTamUng_HoanUng() {
		return tamUng_HoanUng;
	}

	public void setTamUng_HoanUng(String tamUng_HoanUng) {
		this.tamUng_HoanUng = tamUng_HoanUng;
	}

	public String getMienDT() {
		return mienDT;
	}
	public void setMienDT(String mienDT) {
		this.mienDT = mienDT;
	}
	
	public String getSoDu() {
		return soDu;
	}
	public void setSoDu(String soDu) {
		this.soDu = soDu;
	}
	
	public String getSoTheBH_SoTheNgheo() {
		return soTheBH_SoTheNgheo;
	}
	public void setSoTheBH_SoTheNgheo(String soTheBH_SoTheNgheo) {
		this.soTheBH_SoTheNgheo = soTheBH_SoTheNgheo;
	}
	
	public MienGiam getMienGiam() {
		return mienGiam;
	}
	public MienGiam getMienGiam(boolean create) {
		if(create && mienGiam == null) {
			mienGiam = new MienGiam();
		}
		return mienGiam;
	}
	public void setMienGiam(MienGiam mienGiam) {
		this.mienGiam = mienGiam;
	}
	public String getDonViTuoi() {
		return donViTuoi;
	}
	public void setDonViTuoi(String donViTuoi) {
		this.donViTuoi = donViTuoi;
	}
	public HoSoThanhToanUtil getHosoThanhToanUtil() {
		return hosoThanhToanUtil;
	}
	public void setHosoThanhToanUtil(HoSoThanhToanUtil hosoThanhToanUtil) {
		this.hosoThanhToanUtil = hosoThanhToanUtil;
	}
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		CapNhatMienGiamAction.log = log;
	}
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
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
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setIndex(int index) {
		CapNhatMienGiamAction.index = index;
	}
	public static int getIndex() {
		return index;
	}
	public boolean getDisabledPrinting() {
		return disabledPrinting;
	}
	public void setDisabledPrinting(boolean disabledPrinting) {
		this.disabledPrinting = disabledPrinting;
	}
	public Date getNgayhientai() {
		return ngayhientai;
	}
	public void setNgayhientai(Date ngayhientai) {
		this.ngayhientai = ngayhientai;
	}

	public boolean isDisabledGhinhan() {
		return disabledGhinhan;
	}

	public void setDisabledGhinhan(boolean disabledGhinhan) {
		this.disabledGhinhan = disabledGhinhan;
	}
	
}
