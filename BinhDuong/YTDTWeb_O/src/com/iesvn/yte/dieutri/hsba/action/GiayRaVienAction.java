package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
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
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaGiayRaVienDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaPhieuPhauThuatThuThuatDelegate;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaGiayRaVien;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

@Scope(CONVERSATION)
@Name("B234_Giayravien")
@Synchronized(timeout = 6000000)
public class GiayRaVienAction implements Serializable{

	private static final long serialVersionUID = -5106695460338085910L;
	private static Logger log = Logger.getLogger(GiayRaVienAction.class);
	
	@In(required = false)
	@Out(required = false)
	Identity identity;
	private HsbaGiayRaVien grv;
	private String ngayCap;
	private String ngayTk;
	private String nosuccess;
	private String nofound;
	private String nofoundHSBA;
	private String crrDate;
	private boolean isUpdate=false; 
	
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
	
	private String resultReportFileName;
	private String resultReportName;	
	private String loaiFileXuat;
	@Out(required = false)
	private String isReport = "false";
	
	@Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
	@Begin (join = true)
	public String init() {	
		resetValue();	
		return "DieuTri_LapVanBanTheoMau_GiayRaVien";
	}

	
	@End
	public void endFunc(){
		
	}
	public void resetValue() {
		log.info("---resetValue");
		grv = new HsbaGiayRaVien();	
//		grv.getHsbaSovaovien(true).getHsbaMachdoanbd(true).dmbenhicdTen();
		crrDate= ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		ngayTk="";
		nosuccess=nofound=nofoundHSBA="false";
		isUpdate=false;
		nofound = "true";
	}
	
	

	public void displayHSBA(){
		log.info("---displayHSBA");
		String maHsba = grv.getHsbaSovaovien().getHsbaSovaovien().trim();
		Hsba hsba_tmp=null;
		HsbaGiayRaVien grv_tmp = null;
		grv = new HsbaGiayRaVien();
		if (!maHsba.equals("")){
			hsba_tmp = HsbaDelegate.getInstance().find(maHsba);
			if (hsba_tmp==null){
				nofoundHSBA = "true";				
				hsba_tmp = new Hsba();
				FacesMessages.instance().add(IConstantsRes.HSBA_NULL, maHsba);
			}
			grv_tmp = HsbaGiayRaVienDelegate.getInstance().findBySoVaoVien(hsba_tmp.getHsbaSovaovien());
			if (grv_tmp !=null){
				grv = grv_tmp;
				isUpdate = true;
				if (grv.getHsbagrvNgaytaikham() == null) {
					ngayTk = "";
				} else {
					ngayTk = new SimpleDateFormat("dd/MM/yyyy").format(grv.getHsbagrvNgaytaikham());
				}
			}else{
				grv.setHsbaSovaovien(hsba_tmp);
				isUpdate = false;
			}
			
		}else{
			nofoundHSBA = "true";			
			hsba_tmp = new Hsba();
			grv.setHsbaSovaovien(hsba_tmp);
		}		
	}
	
	public void displayGiayRaVien(){
		log.info("---displayGiayRaVien");
		String maGrv = grv.getHsbagrvMa().trim();
		HsbaGiayRaVien grv_tmp = null;
		grv = new HsbaGiayRaVien();
		if (!maGrv.equals("")){
			grv_tmp = HsbaGiayRaVienDelegate.getInstance().findByHsbagrvMa(maGrv);
			if (grv_tmp==null){
				FacesMessages.instance().add(IConstantsRes.GRV_NULL, maGrv);
				resetValue();
			} else {
				isUpdate = true;
				grv = grv_tmp;
				// 20111222 bao.ttc: ma giay ra vien da co san roi: grv.setHsbagrvMa(maGrv);
				if (grv.getHsbagrvNgaytaikham() == null) {
					ngayTk = "";
				} else {
					ngayTk = new SimpleDateFormat("dd/MM/yyyy").format(grv.getHsbagrvNgaytaikham());
				}
			}
		} else {
			nofound = "true";
			resetValue();
		}
	}
	
	public String getdmBenhIcdMaRV(){
		return grv.getHsbaSovaovien(true).getHsbaMachdravien(true).getDmbenhicdMa();
	}
	
	public String getdmBenhIcdTenRV(){
		return grv.getHsbaSovaovien(true).getHsbaMachdravien(true).getDmbenhicdTen();
	}
	
	public void ghiNhan(){
		log.info("---ghiNhan");
		String result="";
		if (!ngayCap.trim().equals("")){
			grv.setHsbagrvNgaycap(Utils.getDBDate("00:00", ngayCap).getTime());
		} else {
			grv.setHsbagrvNgaycap(new Date());
		}
		
		if (!ngayTk.trim().equals("")){
			grv.setHsbagrvNgaytaikham(Utils.getDBDate("00:00", ngayTk).getTime());
		} else {
			grv.setHsbagrvNgaytaikham(null);
		}
		
		grv.setHsbagrvNgaygiocn(new Date());
		grv.setNhanvienMa(DtDmNhanVienDelegate.getInstance().findByNd(identity.getUsername()));
		
		if (isUpdate){
			result = HsbaGiayRaVienDelegate.getInstance().update(grv);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GRV_CN_THATBAI);
			}else{
				grv.setHsbagrvMa(result);			
				FacesMessages.instance().add(IConstantsRes.GRV_CN_THANHCONG, result);
			}
		}else{
			result = HsbaGiayRaVienDelegate.getInstance().insert(grv);
			if (result == null || result.equals("")){
				nosuccess = "true";
				FacesMessages.instance().add(IConstantsRes.GRV_LT_THATBAI);
			}else{
				grv.setHsbagrvMa(result);			
				FacesMessages.instance().add(IConstantsRes.GRV_LT_THANHCONG, result);
			}
		}
		
	}
	public String thuchienAction(){
		XuatReport();
		return "B260_Chonmenuxuattaptin";
	}
	
	
	/**
	 * xuat report 
	 */
	public void XuatReport() {
		loaiBCDT="GiayRaVien";
		log.info("Vao Method XuatReport giay ra vien");
		String baocao1=null;
		String pathTemplate=null;
		String subpathTemplate1=null;
		try {
			pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Giayravien_main.jrxml";
			subpathTemplate1 = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"hsba/Giayravien_sub1.jrxml";
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			JasperReport rpt1 =JasperCompileManager.compileReport(subpathTemplate1);
			
			log.info("da thay file template ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
			params.put("khoaRav", grv.getHsbaSovaovien(true).getHsbaKhoarav(true).getDmkhoaTen());
			params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
			params.put("LOIDAN", grv.getHsbagrvLoidan());
			params.put("sub1", rpt1);	
			params.put("magiay", grv.getHsbagrvMa());
			
			HsbaPhieuPhauThuatThuThuatDelegate hsbapptttDelegate =HsbaPhieuPhauThuatThuThuatDelegate.getInstance();
			List<HsbaPhieuPhauThuatThuThuat> listHsbappttt = hsbapptttDelegate.findAllByHsba(grv.getHsbaSovaovien(true).getHsbaSovaovien());
			String thongTinPttt = "";
			if ( listHsbappttt != null && listHsbappttt.size() > 0 ) {
				
				for ( HsbaPhieuPhauThuatThuThuat hsbappttt : listHsbappttt) {
					
					thongTinPttt += "- Ph\u1EABu thu\u1EADt ng\u00E0y" + ": " // - Phau thuat ngay: 
					+ (hsbappttt.getHsbapptttNgaypttt() == null ? "" : sdf.format(hsbappttt.getHsbapptttNgaypttt())) + "\n";
					
					thongTinPttt += "- Ph\u01B0\u01A1ng ph\u00E1p v\u00F4 c\u1EA3m" + ": " // - Phuong phap vo cam: 
					+ (hsbappttt.getHsbapptttPhuongphapVocam() == null ? "" : hsbappttt.getHsbapptttPhuongphapVocam(true).getDtdmvocamDiengiai()) + "\n";
					
					String ptv = hsbapptttDelegate.getPtvByHsbappttt(hsbappttt.getHsbapptttMa());
					thongTinPttt += "- Ph\u1EABu thu\u1EADt vi\u00EAn" + ": " // - Phau thuat vien:
					+ (ptv == null ? "" : ptv) + "\n";
					
					thongTinPttt += "- C\u00E1ch th\u1EE9c ph\u1EABu thu\u1EADt" + ": " // - Cach thuc phau thuat: 
					+ (hsbappttt.getHsbapptttPhuongphap() == null ? "" : hsbappttt.getHsbapptttPhuongphap(true).getDtdmclsbgDiengiai()) + "\n \n";
					
					//params.put("NGAY_PHAUTHUAT",hsbappttt.getHsbapptttNgaypttt() );
					//params.put("PP_VOCAM",hsbappttt.getHsbapptttPhuongphapVocam(true).getDtdmvocamDiengiai() );
					//params.put("CACHTHUC_PHAUTHUAT", hsbappttt.getHsbapptttPhuongphap(true).getDtdmclsbgDiengiai());
				}
			} else {
				thongTinPttt += "- Ph\u1EABu thu\u1EADt ng\u00E0y" + ": " + "\n \n";
				
				thongTinPttt += "- Ph\u01B0\u01A1ng ph\u00E1p v\u00F4 c\u1EA3m" + ": " + "\n \n";
				
				thongTinPttt += "- Ph\u1EABu thu\u1EADt vi\u00EAn" + ": " + "\n \n";
				
				thongTinPttt += "- C\u00E1ch th\u1EE9c ph\u1EABu thu\u1EADt" + ": " + "\n \n";
			}
			params.put("THONGTINPTTT", thongTinPttt);
			params.put("TINHTRANG_RAVIEN", grv.getHsbagrvTtrv() == null ? "" : grv.getHsbagrvTtrv() );
			params.put("NGAY_TAIKHAM", grv.getHsbagrvNgaytaikham() == null ? "" : sdf.format(grv.getHsbagrvNgaytaikham()) );
			
			log.info(grv.getHsbagrvMa());
			
			// 20101111 bao.ttc: them cac thong tin khac
			if(grv.getHsbaSovaovien() != null){
				
				Hsba hsba = grv.getHsbaSovaovien();
				String diachistr="";
				if(hsba.getBenhnhanMa().getBenhnhanDiachi() != null)
					diachistr += hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
				if(hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null)
					diachistr += hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
				if(hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null)
					diachistr += hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
				if(hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null)
					diachistr += hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
				params.put("DIACHI", diachistr );
				
				// 20120424 bao.ttc: load thong tin Chan Doan: Benh chinh, dien giai BC, benh phu
				HsbaChuyenMon hsbaChuyenMon = null; 
				HsbaChuyenMonDelegate chuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
				hsbaChuyenMon = chuyenMonDelegate.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt(true).getDmkhoaMa());
				
				String chandoan = "";
				String chandoanPhu = "";
				chandoan += hsba.getHsbaMachdravien() == null ? "" : hsba.getHsbaMachdravien(true).getDmbenhicdMa() + " - " + hsba.getHsbaMachdravien(true).getDmbenhicdTen();
				if (hsbaChuyenMon != null){
					if (chandoan.equals("")){
						chandoan += hsbaChuyenMon.getHsbacmBenhchinh() == null ? "" : hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
					}
					chandoanPhu += hsbaChuyenMon.getHsbacmDiengiaibc() == null ? "" : ", " + hsbaChuyenMon.getHsbacmDiengiaibc();
					chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
					chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu2() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu2(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu2(true).getDmbenhicdTen();
					chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu3() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu3(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu3(true).getDmbenhicdTen();
					chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu4() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu4(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu4(true).getDmbenhicdTen();
					chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu5() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu5(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu5(true).getDmbenhicdTen();
				} 
				if (chandoanPhu.equals("")){
					hsbaChuyenMon = chuyenMonDelegate.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdtCm(true).getDmkhoaMa());
					if (hsbaChuyenMon != null){
						
						if (chandoan.equals("")){
							chandoan += hsbaChuyenMon.getHsbacmBenhchinh() == null ? "" : hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
						}
						chandoanPhu += hsbaChuyenMon.getHsbacmDiengiaibc() == null ? "" : ", " + hsbaChuyenMon.getHsbacmDiengiaibc();
						chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
						chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu2() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu2(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu2(true).getDmbenhicdTen();
						chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu3() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu3(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu3(true).getDmbenhicdTen();
						chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu4() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu4(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu4(true).getDmbenhicdTen();
						chandoanPhu += hsbaChuyenMon.getHsbacmBenhphu5() == null ? "" : ", " + hsbaChuyenMon.getHsbacmBenhphu5(true).getDmbenhicdMa() + " - " + hsbaChuyenMon.getHsbacmBenhphu5(true).getDmbenhicdTen();
					}
				}
				
				params.put("CHANDOAN", chandoan + " " + chandoanPhu);
				
				
				HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
				HsbaBhyt hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());
				if(hsbaBHYT != null){
					if(hsbaBHYT.getHsbabhytGiatri0() != null)
						params.put("GIATRITU", sdf.format(hsbaBHYT.getHsbabhytGiatri0()));
					if(hsbaBHYT.getHsbabhytGiatri1() != null)
						params.put("GIATRIDEN", sdf.format(hsbaBHYT.getHsbabhytGiatri1()));
					//if(hsbaBHYT.getHsbabhytSothebh() != null)
					//	params.put("SOTHEBH", hsbaBHYT.getHsbabhytSothebh());
					
					String so_bhyt = "";
					if (hsbaBHYT.getHsbabhytSothebh() != null && !hsbaBHYT.getHsbabhytSothebh().equals("")){
						so_bhyt = hsbaBHYT.getHsbabhytSothebh();
						params.put("khoiBhytMa", so_bhyt.substring(0, 2));
						params.put("khoiBhytMa1", so_bhyt.substring(2, 3));
						params.put("tinhBhytMa", so_bhyt.substring(3, 5));
						params.put("namBhyt", so_bhyt.substring(5, 7));
						params.put("maCoQuan", so_bhyt.substring(7, 10));
						if (hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() != null)
							params.put("THEBHYT", so_bhyt.substring(10) + " - " + hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa());
						else
							params.put("THEBHYT", so_bhyt.substring(10));
					}
					
				}
				
			}
			// 20101111 bao.ttc: them cac thong tin khac -- END
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			    log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    log.info("sau conn");
			    jasperPrintDT =  JasperFillManager.fillReport(jasperReport,params, conn);
			    log.info("sau fillreport");
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintDT,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/", "pdf","GiayRaVien");
			    log.info("sau xuat bao cao");
			    duongdanStrDT=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+duongdanStrDT);
			    index+= 1;
			    log.info("Index :" + getIndex());
			    if(conn != null) conn.close();
		}catch (Exception e) {
		    log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
	    log.info("Thoat Method XuatReport");
	}
	
	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
		
	public String formatDateTime(Date date){
		return date==null?"":Utils.getGioPhut(date);
	}
	
	public String formatGtBenhNhan(String gioitinh){
		if (gioitinh==null || gioitinh.trim().equals("")){
			return "";
		}
		return gioitinh.equals("1")?"true":"false";
	}

	
	

	public HsbaGiayRaVien getGrv() {
		return grv;
	}

	public void setGrv(HsbaGiayRaVien grv) {
		this.grv = grv;
	}

	public String getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(String ngayCap) {
		this.ngayCap = ngayCap;
	}

	public String getNgayTk() {
		return ngayTk;
	}

	public void setNgayTk(String ngayTk) {
		this.ngayTk = ngayTk;
	}

	public String getNosuccess() {
		return nosuccess;
	}

	public void setNosuccess(String nosuccess) {
		this.nosuccess = nosuccess;
	}

	public String getNofound() {
		return nofound;
	}

	public void setNofound(String nofound) {
		this.nofound = nofound;
	}

	public String getNofoundHSBA() {
		return nofoundHSBA;
	}

	public void setNofoundHSBA(String nofoundHSBA) {
		this.nofoundHSBA = nofoundHSBA;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	public String getResultReportFileName() {
		return resultReportFileName;
	}

	public void setResultReportFileName(String resultReportFileName) {
		this.resultReportFileName = resultReportFileName;
	}

	public String getResultReportName() {
		return resultReportName;
	}

	public void setResultReportName(String resultReportName) {
		this.resultReportName = resultReportName;
	}

	public String getLoaiFileXuat() {
		return loaiFileXuat;
	}

	public void setLoaiFileXuat(String loaiFileXuat) {
		this.loaiFileXuat = loaiFileXuat;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getCrrDate() {
		return crrDate;
	}

	public void setCrrDate(String crrDate) {
		this.crrDate = crrDate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
