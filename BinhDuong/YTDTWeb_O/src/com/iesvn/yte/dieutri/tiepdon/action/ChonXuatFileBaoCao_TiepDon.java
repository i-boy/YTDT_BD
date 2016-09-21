/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;

@Name("ChonXuatFileBaoCao_TiepDon")
@Scope(SESSION)
public class ChonXuatFileBaoCao_TiepDon implements Serializable{
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathTD;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeTD;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintTD;
	
	private String chonFileXuat=null;
	
	private boolean reportFinish=false;
	private String duongdanFileXuat=null;
	
	@Create
	public void init(){
		setChonFileXuat("DOC");
	} 
	
	public void inbaocao(){
		log.info("bat dau in bao cao");
		try{
			JasperPrintManager.printReport(jasperPrintTD, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void xuatFileAction(){
		log.info("bat dau xuat file" + chonFileXuat);
		String ketquaPath=null;
		String tenfile=null;
		ketquaPath=IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"tiepdon/";
		if(reportTypeTD.equals("Baocaothongketainanthuongtich")){
			log.info("-------Bao cao thong ke tai nan thuong tich-----------");
			tenfile="Baocaothongketainanthuongtich";
		}else if(reportTypeTD.equals("Phantichsolieuthuchingoaitru")){
			log.info("-------Bao cao Phan tich so lieu thu chi ngoai tru-----------");
			tenfile="Phantichsolieuthuchingoaitru";
		}else if(reportTypeTD.equals("Phantichchiphidieutritaiccl")){
			log.info("-------Bao cao Phan tich chi phi dieu tri tai ccl-----------");
			tenfile="Phantichchiphidieutritaiccl";
		}else if(reportTypeTD.equals("CanLamSanPhauThuat")){
			log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------");
			tenfile="CanLamSanPhauThuat";
		}else if(reportTypeTD.equals("XuTriThuocTaiBanKham")){
			log.info("-------Vao Method XuatReport bao cao xu tri thuoc tai ban kham-----------");
			tenfile="XuTriThuocTaiBanKham";
		}else if(reportTypeTD.equals("KeToaVe")){
			log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------");
			tenfile="ketoave";
		}else if(reportTypeTD.equals("Tonghopthuocvtthdadung")){
			log.info("-------Vao Method XuatReport tong hop thuoc vtth da dung-----------");
			tenfile="Tonghopthuocvtthdadung";
		}else if(reportTypeTD.equals("Insoluutru")){
			log.info("-------Vao Method XuatReport In so luu tru-----------");
			tenfile="Insoluutru";
		}else if(reportTypeTD.equals("indsbnkhamthutien")){
			log.info("-------Vao Method XuatReport In benh nhan kham thu tien-----------");
			tenfile="indsbnkhamthutien";
		}else if(reportTypeTD.equals("indsbndkonline")){
			log.info("-------Vao Method XuatReport In BN dang ky Online ----------");
			tenfile="indsbndkonline";
		}else if(reportTypeTD.equals("ptsolieuclsngoaitru")){
			log.info("-------Vao Method XuatReport pt so lieu cls ngoai tru-----------");
			tenfile="ptsolieuclsngoaitru";
		}else if(reportTypeTD.equals("B123_Inphieulinhthuoc")){
			log.info("-------Vao Method XuatReport B123_Inphieulinhthuoc-----------");
			tenfile="B123_Inphieulinhthuoc";
		}else if(reportTypeTD.equals("ThamKhamVaXuTri")){
			log.info("-------Vao Method ThamKhamVaXuTri B123_Inphieulinhthuoc-----------");
			tenfile="ThamKhamVaXuTri";
		}else if(reportTypeTD.equals("B141_Phantichbenhnhankhambenh")){
			log.info("-------Vao Method  B141_Phantichbenhnhankhambenh-----------");
			tenfile="B141_Phantichbenhnhankhambenh";
		}else if(reportTypeTD.equals("B135_Denghitamungthanhtoan")){
				log.info("-------Vao Method  B135_Denghitamungthanhtoan-----------");
				tenfile="B135_Denghitamungthanhtoan";
		}else if(reportTypeTD.equals("indanhsachbenhnhancapcuu")){
			log.info("-------Vao Method  indanhsachbenhnhancapcuu-----------");
			tenfile="indanhsachbenhnhancapcuu";
		}else if(reportTypeTD.equals("Insoluutrubhytthuphi")){
			log.info("-------Vao Method  Insoluutrubhytthuphi-----------");
			tenfile="Insoluutrubhytthuphi";
		}else if(reportTypeTD.equals("benhanvaovien")){
			log.info("-------Vao Method  benhanvaovien-----------");
			tenfile="benhanvaovien";
		}else if(reportTypeTD.equals("benhantuvongtruockhivaovien")){
			log.info("-------Vao Method  benhantuvongtruockhivaovien-----------");
			tenfile="benhantuvongtruockhivaovien";
		}else if(reportTypeTD.equals("giaychungthuong")){
			log.info("-------Vao Method  giaychungthuong-----------");
			tenfile="giaychungthuong";
		}else if(reportTypeTD.equals("giaychuyenviennguoibenhcobhyt")){
			log.info("-------Vao Method  giaychuyenviennguoibenhcobhyt-----------");
			tenfile="giaychuyenviennguoibenhcobhyt";
		}else if(reportTypeTD.equals("thamkhamdieutringoaitru")){
			log.info("-------Vao Method  thamkhamdieutringoaitru-----------");
			tenfile="thamkhamdieutringoaitru";
		}else if(reportTypeTD.equals("giaychungnhan")){
			log.info("-------Vao Method benhanvaovien-----------");
			tenfile="giaychungnhan";
		}else if(reportTypeTD.equals("phieukhamchuyenkhoa")){
			log.info("-------Vao Method  phieukhamchuyenkhoa-----------");
			tenfile="phieukhamchuyenkhoa";
		}else if(reportTypeTD.equals("phieukhambenhvaovien")){
			log.info("-------Vao Method  phieukhambenhvaovien-----------");
			tenfile="phieukhambenhvaovien";
		}else if(reportTypeTD.equals("giaychungnhansuckhoe")){
			log.info("-------Vao Method  giaychungnhansuckhoe-----------");
			tenfile="giaychungnhansuckhoe";
		}
		else if(reportTypeTD.equals("ketquaphatmau")){
			log.info("-------Vao Method  ketquaphatmau-----------");
			tenfile="ketquaphatmau";
		}else if(reportTypeTD.equals("CapNhatThuocBNSD")){
			log.info("-------Vao Method  CapNhatThuocBNSD-----------");
			tenfile="VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung";
		}else if(reportTypeTD.equals("benhAnNgoaitruYhct")){
			log.info("-------Vao Method  benhAnNgoaitruYhct-----------");
			tenfile="benhAnNgoaitruYhct";
		}
		
		int index=0;
		String tempStr=null;
		tempStr=XuatReportUtil.ReportUtil(jasperPrintTD,index,ketquaPath,chonFileXuat.trim(),tenfile);
		setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE,""));
		log.info("duong dan----" + getDuongdanFileXuat());
		setReportFinish(true);
	}
	
	public String troveAction(){
		if(reportTypeTD.equals("Baocaothongketainanthuongtich")){
			log.info("-------Bao cao thong ke tai nan thuong tich-----------");
			return "";
		}else if(reportTypeTD.equals("Phantichsolieuthuchingoaitru")){
			log.info("-------Bao cao Phan tich so lieu thu chi ngoai tru-----------");
			return "TiepDon_PhanTichBaoCao_PhanTichSoLieuThuChiNgoaiTru";
		}else if(reportTypeTD.equals("Phantichchiphidieutritaiccl")){
			log.info("-------Bao cao Phan tich chi phi dieu tri tai ccl-----------");
			return "";
		}else if(reportTypeTD.equals("CanLamSanPhauThuat")){
			log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------");
			return "clsthuthat";
		}else if(reportTypeTD.equals("XuTriThuocTaiBanKham")){
			log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------");
			return "xutrithuoctaibankham";
		}else if(reportTypeTD.equals("KeToaVe")){
			log.info("-------Vao Method XuatReport bao cao can lam san phau thuat-----------");
			return "ketoave";
		}else if(reportTypeTD.equals("Tonghopthuocvtthdadung")){
			log.info("-------Vao Method XuatReport tong hop thuoc vtth da dung-----------");
			return "TiepDon_KhamBenh_TongHopThuocVTTHDaDung";
		}else if(reportTypeTD.equals("Insoluutru")){
			log.info("-------Vao Method XuatReport In so luu tru-----------");
			return "TiepDon_PhanTichBaoCao_InSoLuuTru";
		}else if(reportTypeTD.equals("indsbnkhamthutien")){
			log.info("-------Vao Method XuatReport In benh nhan kham thu tien-----------");
			return "TiepDon_TiepDonBenhNhan_InDanhSachBNKhamThuTien";
		}else if(reportTypeTD.equals("indsbndkonline")){
			log.info("-------Vao Method XuatReport In BN dang ky Online -----------");
			return "TiepDon_TiepDonBenhNhan_InDSBNDKOnline";
		}else if(reportTypeTD.equals("ptsolieuclsngoaitru")){
			log.info("-------Vao Method XuatReport pt so lieu cls ngoai tru-----------");
			return "TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan";
		}
		else if(reportTypeTD.equals("B123_Inphieulinhthuoc")){
			log.info("-------Vao Method XuatReport pt so lieu cls ngoai tru-----------");
			return "TiepDon_KhamBenh_InPhieuLinhThuoc";
		}else if(reportTypeTD.equals("ThamKhamVaXuTri")){
			log.info("-------Vao Method ThamKhamVaXuTri B123_Inphieulinhthuoc-----------");
			return "ghinhan";
		}else if(reportTypeTD.equals("B141_Phantichbenhnhankhambenh")){
			log.info("-------Vao Method B141_Phantichbenhnhankhambenh-----------");
			return "TiepDon_PhanTichBaoCao_PhanTichBenhNhanKhamBenh";
		}else if(reportTypeTD.equals("B135_Denghitamungthanhtoan")){
			log.info("-------Vao Method B135_Denghitamungthanhtoan-----------");
			return "TiepDon_TiepDonKhamBenhCapCuu_DeNghiTamUngThanhToan";
		}else if(reportTypeTD.equals("indanhsachbenhnhancapcuu")){
			log.info("-------Vao Method indanhsachbenhnhancapcuu-----------");
			return "TiepDon_TiepDonKhamBenhCapCuu_InDanhSachBenhNhanCapCuu";
		}else if(reportTypeTD.equals("Insoluutrubhytthuphi")){
			log.info("-------Vao Method Insoluutrubhytthuphi-----------");
			return "TiepDon_PhanTichBaoCao_InSoLuuTruBHYTThuPhi";
		}else if(reportTypeTD.equals("benhanvaovien")){
			log.info("-------Vao Method benhanvaovien-----------");
			return "lapbenhanngoaitru";
		}else if(reportTypeTD.equals("benhantuvongtruockhivaovien")){
			log.info("-------Vao Method benhantuvongtruockhivaovien-----------");
			return "lapbatuvongtruockhivaovien";
		}else if(reportTypeTD.equals("giaychungthuong")){
			log.info("-------Vao giaychungthuong-----------");
			return "giaychungthuong";
		}
		else if(reportTypeTD.equals("giaychuyenviennguoibenhcobhyt")){
			log.info("-------Vao Method giaychuyenviennguoibenhcobhyt-----------");
			return "ChuyenVienNguoiBenhCoTheBHYT";
		}else if(reportTypeTD.equals("thamkhamdieutringoaitru")){
			log.info("-------Vao Method thamkhamdieutringoaitru-----------");
			return "ThamKhamDieuTriNgoaiTru";
		}else if(reportTypeTD.equals("phieukhamchuyenkhoa")){
			log.info("-------Vao Method phieukhamchuyenkhoa-----------");
			return "PhieuKhamChuyenKhoa";
		}else if(reportTypeTD.equals("phieukhambenhvaovien")){
			log.info("-------Vao Method phieukhambenhvaovien-----------");
			return "PhieuKhamBenhVaoVien";
		}else if(reportTypeTD.equals("giaychungnhansuckhoe")){
			log.info("-------Vao Method giaychungnhansuckhoe-----------");
			return "GiayChungNhanSucKhoe";
		}else if(reportTypeTD.equals("giaychungnhan")){
			log.info("-------Vao Method benhanvaovien-----------");
			return "giaychungnhan";
		}
		else if(reportTypeTD.equals("ketquaphatmau")){
			log.info("-------Vao Method ketquaphatmau-----------");
			return "capNhatKetQuaCLS";
		}else if(reportTypeTD.equals("CapNhatThuocBNSD")){
			log.info("-------Vao Method  CapNhatThuocBNSD-----------");
			return "VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung";
		}else if(reportTypeTD.equals("benhAnNgoaitruYhct")){
			log.info("-------Vao Method  benhAnNgoaitruYhct-----------");
			return "BenhanngoaitruYHCT";
		}
		
		
		return "";
	}
	
	public void setChonFileXuat(String chonFileXuat) {
		this.chonFileXuat = chonFileXuat;
	}
	
	public String getChonFileXuat() {
		return chonFileXuat;
	}

	public void setDuongdanFileXuat(String duongdanFileXuat) {
		this.duongdanFileXuat = duongdanFileXuat;
	}

	public String getDuongdanFileXuat() {
		return duongdanFileXuat;
	}

	public void setReportFinish(boolean reportFinish) {
		this.reportFinish = reportFinish;
	}

	public boolean isReportFinish() {
		return reportFinish;
	}
}
