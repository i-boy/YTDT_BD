package com.iesvn.yte.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

public class XuatReportDuocPham {

	public JasperPrint jasperPrintKC;
	public String reportPathKC;
	public String reportTypeKC;
	
	public void XuatReportPhieuDuTruTuTruc(Logger log,PhieuDuTru phieuDuTru,int index, String loaiPhieu) {
		log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
		String baocao1=null;
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate ="";
			if(loaiPhieu.equals("GN")){//Thuoc gay nghien
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_02GayNghien.jrxml";
			}else if(loaiPhieu.equals("HT")){//Thuoc huong tam than
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_02HTT.jrxml";
			}else{//cac loai con lai
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_02.jrxml";
			}					
			
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(phieuDuTru.getPhieudtPhanBiet()==0 || phieuDuTru.getPhieudtPhanBiet()==1){//Lanh thuoc tong hop VA TU TRUC KHOA PHONG
				if(loaiPhieu.equals("GN")){//Thuoc gay nghien
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_GN);
				}else if(loaiPhieu.equals("HT")){//Thuoc huong tam than
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HT);
				}else if(loaiPhieu.equals("YC")){//cac loai con lai					
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_TABLE);
				}else if(loaiPhieu.equals("HC")){
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_TABLE );
				}else{
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_TABLE );
				}
				params.put("khoa", phieuDuTru.getDmkhoaMaso().getDmkhoaTen());
			}else{//Tra thuoc tu truc doi voi loai thuoc tan duoc (thuoc thuong/hoa chat/ycu) su dung template rieng vi title tra thuoc qua dai, khong dung chung duoc
				if(loaiPhieu.equals("GN")){//Thuoc gay nghien
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC_GN);
				}else if(loaiPhieu.equals("HT")){//Thuoc huong tam than
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC_HT);
				}else{//cac loai con lai
					pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_06.jrxml";
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
				}	
				// 20120312 bao.ttc: tra thuoc cung in ten khoa can tra
				params.put("khoa", phieuDuTru.getDmkhoaMaso().getDmkhoaTen());
				//params.put("khoa", phieuDuTru.getPhieudtMakho().getDmkhoaTen());
			}	
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file templete 5" + pathTemplate);
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);			
			params.put("ngayXuat", Utils.reFactorString(phieuDuTru.getPhieudtNgay()));
			params.put("maphieu", phieuDuTru.getPhieudtMa());
			log.info("Ma phieu :" + phieuDuTru.getPhieudtMa());
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			   log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf",reportTypeKC);
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		
		log.info("Thoat Method XuatReport");
	}
	
	public void XuatReportPhieuDuTruKhoaPhong(Logger log,PhieuDuTru phieuDuTru,int index, String loaiPhieu) {
		log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
		String baocao1=null;
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate ="";
			if(loaiPhieu.equals("GN")){//Thuoc gay nghien
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_04GayNghien.jrxml";
			}else if(loaiPhieu.equals("HT")){//Thuoc huong tam than
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_04HTT.jrxml";
			}else{//cac loai con lai
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_04.jrxml";
			}
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			if(phieuDuTru.getPhieudtPhanBiet()==0||phieuDuTru.getPhieudtPhanBiet()==1)
			{
//				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
//				log.info(IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
				if(loaiPhieu.equals("GN")){//Thuoc gay nghien
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_GN);
				}else if(loaiPhieu.equals("HT")){//Thuoc huong tam than
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HT);
				}else if(loaiPhieu.equals("YC")){//cac loai con lai					
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_TABLE);
				}else if(loaiPhieu.equals("HC")){
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_TABLE );
				}else{
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_TABLE );
				}
			}
			else
			{
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
				log.info(IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
			}
			
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(phieuDuTru.getDmkhoaMaso().getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
			params.put("khoa",  khoa.getDmkhoaTen());
			//params.put("khoa", phieuDuTru.getDmkhoaMaso(true).getDmkhoaTen());
			params.put("ngayXuat", Utils.reFactorString(phieuDuTru.getPhieudtNgay()));
			params.put("maphieu", phieuDuTru.getPhieudtMa());
			log.info("Ma phieu :" + phieuDuTru.getPhieudtMa());
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			   log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf",reportTypeKC);
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		
		log.info("Thoat Method XuatReport");
	}
	
	public void XuatReportPhieuDuTruKhoLeTraThuoc(Logger log,PhieuTraKho phieuTra,int index, String loaiPhieu) {
		log.info("Vao Method XuatReport XuatReportPhieuDuTruKhoLeTraThuoc");
		String baocao1=null;
			
		try {
			log.info("bat dau method XuatReport ");
			String pathTemplate = "";
			if(loaiPhieu.equals("GN")){//Thuoc gay nghien
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_05GayNghien.jrxml";
			}else if(loaiPhieu.equals("HT")){//Thuoc huong tam than
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_05HTT.jrxml";
			}else{//cac loai con lai
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_05.jrxml";
			}			
			log.info("da thay file templete 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			Map<String, Object> params = new HashMap<String, Object>();
			
			/*if(phieuDuTru.getPhieudtPhanBiet()==0||phieuDuTru.getPhieudtPhanBiet()==1)
			{
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
				log.info(IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
			}
			else
			{
				params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
				log.info(IConstantsRes.PHIEU_DU_TRU_TRA_THUOC);
			}*/
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(phieuTra.getDmkhoaTra(true).getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
			params.put("khoa",  khoa.getDmkhoaTen());			
			params.put("ngayXuat", Utils.reFactorString(phieuTra.getPhieutrakhoNgay()));
			params.put("maphieu", phieuTra.getPhieutrakhoMa());
			log.info("Ma phieu :" + phieuTra.getPhieutrakhoMa());
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			   log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf",reportTypeKC);
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		
		log.info("Thoat Method XuatReport");
	}
	
	public void XuatReportPhieuDuTruKhoLeLanhThuoc(Logger log,PhieuXuatKho phieuDuTru,int index, String loaiPhieu) {
		log.info("Vao Method XuatReport XuatReportPhieuDuTruKhoLeLanhThuoc");
		String baocao1=null;
		String pathTemplate = null;
			
		try {
			log.info("bat dau method XuatReport ");
			Map<String, Object> params = new HashMap<String, Object>();
			if(loaiPhieu.equals("GN")){//Thuoc gay nghien
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_GayNghien.jrxml";
			}else if(loaiPhieu.equals("HT")){//Thuoc huong tam than
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_HTT.jrxml";
			}else{//cac loai con lai
				pathTemplate = IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI+"duocpham/phieudutru_03.jrxml";
				 if(loaiPhieu.equals("YC")){//cac loai con lai					
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC_TABLE);
				}else if(loaiPhieu.equals("HC")){
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC_TABLE );
				}else{
					params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC );
					params.put("TITLE_RIGHT", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_MS );
					params.put("TABLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_TABLE );
				}
			}
			
			log.info("da thay file template 5" + pathTemplate);
			
			JasperReport jasperReport =JasperCompileManager.compileReport(pathTemplate);
			log.info("da thay file template ");
			
			
			params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
			params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
			DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
			DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(phieuDuTru.getDmkhoaNhan().getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
			params.put("khoa",  khoa.getDmkhoaTen());			
			params.put("ngayXuat", Utils.reFactorString(phieuDuTru.getPhieuxuatkhoNgaylap()));
			params.put("maphieu", phieuDuTru.getPhieuxuatkhoMa());
			log.info("Ma phieu :" + phieuDuTru.getPhieuxuatkhoMa());
			
			log.info("================= ");
			Class.forName("oracle.jdbc.OracleDriver");
			   log.info("da thay driver mysql");
			    Connection conn = null;
			    try{
			    	conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL,IConstantsRes.DATABASE_USER,IConstantsRes.DATABASE_PASS);
			    }catch(Exception e){
			    	log.info(e.getMessage());
			    }
			    jasperPrintKC =  JasperFillManager.fillReport(jasperReport,params, conn);
			    baocao1=XuatReportUtil.ReportUtil(jasperPrintKC,index,IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/", "pdf",reportTypeKC);
			    reportPathKC=baocao1.replaceFirst(IConstantsRes.PATH_BASE,"");
			    log.info("duong dan file xuat report :" + baocao1);
			    log.info("duong dan -------------------- :"+reportPathKC);
			    log.info("Index :" + index);
			    if(conn != null) conn.close();
		}catch (Exception e) {
			log.info("ERROR Method XuatReport!!!");
			e.printStackTrace();
		}
		
		log.info("Thoat Method XuatReport");
	}
	
}
