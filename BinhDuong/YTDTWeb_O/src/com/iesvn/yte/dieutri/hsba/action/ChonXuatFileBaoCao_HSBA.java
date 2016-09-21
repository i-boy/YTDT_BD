/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.hsba.action;

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

@Name("ChonXuatFileBaoCaoHSBA")
@Scope(SESSION)
public class ChonXuatFileBaoCao_HSBA implements Serializable {
	@Logger
	private Log log;
	
	@In(required=false)
	@Out(required=false)
	private String duongdanStrDT;
	
	@Out(required=false)
	@In(required=false)
	private String loaiBCDT;
	
	@In(required=false)
	@Out(required=false)
	JasperPrint jasperPrintDT;
	
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
			JasperPrintManager.printReport(jasperPrintDT, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void xuatFileAction(){
		log.info("bat dau xuat file" + chonFileXuat);
		String tenfile=null;
		String ketquaPath=IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"hsba/";
		if(loaiBCDT.equals("BCTHBenhTatTuVong")){
			log.info("-------Bao cao tinh hinh benh tat tu vong-----------");
			tenfile="BCTHBenhTatTuVong";
		}else if(loaiBCDT.equals("Soravaovien")){
			log.info("-------So ra vao vien----------");
			tenfile="Soravaovien";
		}else if(loaiBCDT.equals("Sochuyenvien")){
			log.info("-------So chuyen vien----------");
			tenfile="Sochuyenvien";
		}else if(loaiBCDT.equals("BaoCaoBADangCapnhat")){
			log.info("-------BaoCaoBADangCapnhat----------");
			tenfile="BaoCaoBADangCapnhat";
		}else if(loaiBCDT.equals("BaoCaoBAKetThucCapnhat")){
			log.info("-------BaoCaoBAKetThucCapnhat ----------");
			tenfile="BaoCaoBAKetThucCapnhat";
		}else if(loaiBCDT.equals("GiayChuyenVien")){
			log.info("-------GiayChuyenVien ----------");
			tenfile="GiayChuyenVien";
		}else if(loaiBCDT.equals("GiayRaVien")){
			log.info("-------GiayRaVien ----------");
			tenfile="GiayRaVien";
		}else if(loaiBCDT.equals("GiayChungNhan")){
			log.info("-------GiayChungNhan ----------");
			tenfile="GiayChungNhan";
		}else if(loaiBCDT.equals("GiayChuyenXac")){
			log.info("-------GiayChuyenXac ----------");
			tenfile="GiayChuyenXac";
		}else if(loaiBCDT.equals("PhieuGuiXac")){
			log.info("-------PhieuGuiXac ----------");
			tenfile="PhieuGuiXac";
		}else if(loaiBCDT.equals("BaoCaoThongKeTaiNan")){
			log.info("-------Bao cao thong ke tai nan ----------");
			tenfile="baocaothongketainan";
		}else if(loaiBCDT.equals("PhieuPhauThuatThuThuat")){
			log.info("-------PhieuPhauThuatThuThuat ----------");
			tenfile="PhieuPhauThuatThuThuat";
		}else if(loaiBCDT.equals("LapTrichBienBanHoiChan")){
			log.info("-------LapTrichBienBanHoiChan ----------");
			tenfile="LapTrichBienBanHoiChan";
		}else if(loaiBCDT.equals("Giaytomtatbenhan")){
			log.info("-------Giaytomtatbenhan ----------");
			tenfile="Giaytomtatbenhan";
		}else if(loaiBCDT.equals("Bangtomtatbenhan")){
			log.info("-------Bangtomtatbenhan ----------");
			tenfile="Bangtomtatbenhan";
		}else if(loaiBCDT.equals("Giaychungnhanphauthuat")){
			log.info("-------Giaychungnhanphauthuat ----------");
			tenfile="Giaychungnhanphauthuat";
		}else if(loaiBCDT.equals("Giaychungnhanthuongtich")){
			log.info("-------Giaychungnhanthuongtich ----------");
			tenfile="Giaychungnhanthuongtich";
		}else if(loaiBCDT.equals("giaykhambenhvaovien")){
			log.info("-------giaykhambenhvaovien ----------");
			tenfile="giaykhambenhvaovien";
		}else if(loaiBCDT.equals("Timkiembenhnhantheongayhionhapvien")){
			log.info("-------Timkiembenhnhantheongayhionhapvien ----------");
			tenfile="Timkiembenhnhantheongayhionhapvien";
		}else if(loaiBCDT.equals("soluutruhsba")){
			log.info("-------soluutruhsba ----------");
			tenfile="soluutruhsba";
		}else if(loaiBCDT.equals("bienbanhoichanphauthuat")){
			log.info("-------bienbanhoichanphauthuat ----------");
			tenfile="bienbanhoichanphauthuat";
		}else if(loaiBCDT.equals("CapNhatThongTinChiTietBANoi")){
			log.info("-------CapNhatThongTinChiTietBANoi ----------");
			tenfile="CapNhatThongTinChiTietBANoi";
		}
		else if(loaiBCDT.equals("phieuchamsoc")){
			log.info("-------PHIEUCHAMSOC ----------");
			tenfile="phieuchamsoc";
		}else if(loaiBCDT.equals("ToDieuTri")){
			log.info("-------ToDieuTri ----------");
			tenfile="ToDieuTri";
		}else if(loaiBCDT.equals("PhieuTheoDoiTruyenDich")){
			log.info("-------PhieuTheoDoiTruyenDich ----------");
			tenfile="PhieuTheoDoiTruyenDich";
		}else if(loaiBCDT.equals("DSNguoiBenhVaoRaVien")){
			log.info("-------DSNguoiBenhVaoRaVien ----------");
			tenfile="DSNguoiBenhVaoRaVien";
		}else if(loaiBCDT.equals("BCBenhTruyenNhiem")){
			log.info("-------BCBenhTruyenNhiem ----------");
			tenfile="BCBenhTruyenNhiem";
		}
		else if(loaiBCDT.equals("BCCoQuanYTeChuyenDen")){
			log.info("-------InDSCoQuanYTeChuyenDen ----------");
			tenfile="danhsachcoquanytechuyenden";
		}
		else if(loaiBCDT.equals("BCHoatDongKhamBenh")){
			log.info("-------BCHoatDongKhamBenh ----------");
			tenfile="hoatdongkhambenh";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong")){
			log.info("-------BieuMauBanTuDong ----------");
			tenfile="bieumaubantudong";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong1")){
			log.info("-------BieuMauBanTuDong1 ----------");
			tenfile="bieumaubantudong1";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong2")){
			log.info("-------BieuMauBanTuDong2 ----------");
			tenfile="bieumaubantudong2";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong3")){
			log.info("-------BieuMauBanTuDong3 ----------");
			tenfile="bieumaubantudong3";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong4")){
			log.info("-------BieuMauBanTuDong4 ----------");
			tenfile="bieumaubantudong4";
		}
		else if(loaiBCDT.equals("BCDuocBenhVien")){
			log.info("-------BCDuocBenhVien ----------");
			tenfile="duocbenhvien";
		}
		else if(loaiBCDT.equals("BCSotXuatHuyet")){
			log.info("-------BCSotXuatHuyet----------");
			tenfile="sotxuathuyet";
		}
		else if(loaiBCDT.equals("BCTinhHinhHoatDongTrongNgay")){
			log.info("-------BCTinhHinhHoatDongTrongNgay ----------");
			tenfile="tinhhinhhoatdongtrongngay";
		}
		else if(loaiBCDT.equals("BCTinhHinhBenhTatVaTuVongTaiBV")){
			log.info("-------BCTinhHinhBenhTatVaTuVongTaiBV ----------");
			tenfile="tinhhinhbenhtatvatuvongtaibv";
		}
		else if(loaiBCDT.equals("BCHoatDongCanLamSan")){
			log.info("-------BCHoatDongCanLamSan ----------");
			tenfile="hoatdongcanlamsan";
		}
		else if(loaiBCDT.equals("BCHoatDongCacKhoa")){
			log.info("-------BCHoatDongCacKhoa ----------");
			tenfile="hoatdongcackhoa";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBATmh")){
			log.info("-------CapNhatThongTinChiTietBATmh ----------");
			tenfile="capNhatThongTinChiTietBATmh";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANhikhoa")){
			log.info("-------CapNhatThongTinChiTietBANhikhoa ----------");
			tenfile="capNhatThongTinChiTietBANhikhoa";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBASosinh")){
			log.info("-------CapNhatThongTinChiTietBASosinh ----------");
			tenfile="capNhatThongTinChiTietBASosinh";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBAPhukhoa")){
			log.info("-------CapNhatThongTinChiTietBAPhukhoa ----------");
			tenfile="capNhatThongTinChiTietBAPhukhoa";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBASankhoa")){
			log.info("-------CapNhatThongTinChiTietBASankhoa ----------");
			tenfile="capNhatThongTinChiTietBASankhoa";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANgoaitruYhct")){
			log.info("-------CapNhatThongTinChiTietBANgoaitruYhct ----------");
			tenfile="capNhatThongTinChiTietBANgoaitruYhct";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANaophathai")){
			log.info("-------CapNhatThongTinChiTietBANaophathai ----------");
			tenfile="capNhatThongTinChiTietBANaophathai";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANoitruYhct")){
			log.info("-------CapNhatThongTinChiTietBANoitruYhct ----------");
			tenfile="capNhatThongTinChiTietBANoitruYhct";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANgoai")){
			log.info("-------CapNhatThongTinChiTietBANgoai ----------");
			tenfile="CapNhatThongTinChiTietBANgoai";
		}
		else if(loaiBCDT.equals("SoSanhTinhHinhMacBenhTheoNam")){
			log.info("-------SoSanhTinhHinhMacBenhTheoNam ----------");
			tenfile="SoSanhTinhHinhMacBenhTheoNam";
		}
		else if(loaiBCDT.equals("Baocaohsba")){
			log.info("-------Baocaohsba ----------");
			tenfile="Baocaohsba";
		}
		
		int index=0;
		String tempStr=null;
		tempStr=XuatReportUtil.ReportUtil(jasperPrintDT,index,ketquaPath,chonFileXuat.trim(),tenfile);
		setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE,""));
		log.info("duong dan----" + getDuongdanFileXuat());
		setReportFinish(true);
	}
	
	public String troveAction(){
		setDuongdanFileXuat("");
		
		if(loaiBCDT.equals("Soravaovien")){
			log.info("-------So ra vao vien----------");
			return "DieuTri_LapVanBanTheoMau_SoRaVaoVien";
		}
		else if(loaiBCDT.equals("Sochuyenvien")){
			log.info("-------So chuyen vien----------");
			return "DieuTri_LapVanBanTheoMau_SoChuyenVien";
		}
		else if(loaiBCDT.equals("BCTHBenhTatTuVong")){
			log.info("-------Bao cao tinh hinh benh tat tu vong-----------");
			return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhBenhTatTuVong";
		}
		else if(loaiBCDT.equals("BaoCaoBADangCapnhat")){
			log.info("-------So BaoCaoBADangCapnhat vien----------");
			return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat";
		}
		else if(loaiBCDT.equals("BaoCaoThongKeTaiNan")){
			log.info("-------So BaoCaoThongKeTaiNan ----------");
			return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoThongKeTaiNan";
		}
		
		else if(loaiBCDT.equals("BaoCaoBAKetThucCapnhat")){
			log.info("-------BaoCaoBAKetThucCapnhat ----------");
			return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnKetThucCapNhat";
		}
		else if(loaiBCDT.equals("GiayChuyenVien")){
			log.info("-------GiayChuyenVien ----------");
			return "DieuTri_LapVanBanTheoMau_GiayChuyenVien";
		}
		else if(loaiBCDT.equals("GiayRaVien")){
			log.info("-------GiayRaVien ----------");
			return "DieuTri_LapVanBanTheoMau_GiayRaVien";
		}
		else if(loaiBCDT.equals("GiayChungNhan")){
			log.info("-------GiayChungNhan----------");
			return "DieuTri_LapVanBanTheoMau_GiayChungNhan";
		}
		else if(loaiBCDT.equals("GiayChuyenXac")){
			log.info("-------GiayChuyenXac ----------");
			return "DieuTri_LapVanBanTheoMau_GiayChuyenXac";
		}
		else if(loaiBCDT.equals("PhieuGuiXac")){
			log.info("-------PhieuGuiXac ----------");
			return "DieuTri_LapVanBanTheoMau_PhieuGuiXac";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong")){
			log.info("-------BieuMauBanTuDong ----------");
			return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong1")){
			log.info("-------BieuMauBanTuDong1 ----------");
			return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong1";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong2")){
			log.info("-------BieuMauBanTuDong2 ----------");
			return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong2";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong3")){
			log.info("-------BieuMauBanTuDong3 ----------");
			return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong3";
		}
		else if(loaiBCDT.equals("BieuMauBanTuDong4")){
			log.info("-------BieuMauBanTuDong4 ----------");
			return "DieuTri_LapVanBanTheoMau_BieuMauBanTuDong4";
		}
		else if(loaiBCDT.equals("PhieuPhauThuatThuThuat")){
			log.info("-------PhieuPhauThuatThuThuat ----------");
			return "DieuTri_LapVanBanTheoMau_PhieuPhauThuatThuThuat";
		}
		else if(loaiBCDT.equals("LapTrichBienBanHoiChan")){
			log.info("-------LapTrichBienBanHoiChan ----------");
			return "DieuTri_LapVanBanTheoMau_LapTrichBienBanHoiChan";
		}
		else if(loaiBCDT.equals("Giaytomtatbenhan")){
			log.info("-------Giaytomtatbenhan ----------");
			return "DieuTri_LapVanBanTheoMau_TomTatBenhAn";
		}
		else if(loaiBCDT.equals("Bangtomtatbenhan")){
			log.info("-------Bangtomtatbenhan ----------");
			return "DieuTri_LapVanBanTheoMau_BangTomTatBenhAn";
		}
		else if(loaiBCDT.equals("Giaychungnhanphauthuat")){
			log.info("-------Giaychungnhanphauthuat ----------");
			return "DieuTri_LapVanBanTheoMau_GiayChungNhanPhauThuat";
		}
		else if(loaiBCDT.equals("Giaychungnhanthuongtich")){
			log.info("-------Giaychungnhanthuongtich ----------");
			return "DieuTri_LapVanBanTheoMau_GiayChungThuong";
		}
		else if(loaiBCDT.equals("giaykhambenhvaovien")){
			log.info("-------giaykhambenhvaovien ----------");
			return "DieuTri_CapNhat_CapNhatThongTinNhapVien";
		}
		else if(loaiBCDT.equals("Timkiembenhnhantheongayhionhapvien")){
			log.info("-------Timkiembenhnhantheongayhionhapvien ----------");
			return "DieuTri_BaoCaoHoatDongDieuTri_TimDanhSachBenhNhanTheoNgayGioNhapVien";
		}
		else if(loaiBCDT.equals("soluutruhsba")){
			log.info("-------soluutruhsba ----------");
			return "DieuTri_LapVanBanTheoMau_SoLuuTruBenhAn";
		}
		else if(loaiBCDT.equals("bienbanhoichanphauthuat")){
			log.info("-------bienbanhoichanphauthuat ----------");
			return "DieuTri_LapVanBanTheoMau_BienBanHoiChanPhauThuat";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANoi")){
			log.info("-------CapNhatThongTinChiTietBANoi ----------");
			return "DieuTri_CapNhat_CapNhatThongTinChiTietBANoi";
		}
		else if(loaiBCDT.equals("phieuchamsoc")){
			log.info("-------PHIEUCHAMSOC ----------");
			return "DieuTri_LapVanBanTheoMau_PhieuChamSoc";
		}
		else if(loaiBCDT.equals("ToDieuTri")){
			log.info("-------ToDieuTri ----------");
			return "DieuTri_LapVanBanTheoMau_ToDieuTri";
		}
		else if(loaiBCDT.equals("PhieuTheoDoiTruyenDich")){
			log.info("-------PhieuTheoDoiTruyenDich ----------");
			return "/B2_Dieutri/B270_phieutheodoitruyendich.xhtml";
		}
		else if(loaiBCDT.equals("DSNguoiBenhVaoRaVien")){
			log.info("-------DSNguoiBenhVaoRaVien ----------");
			return "/B2_Dieutri/B271_DanhSachNguoiBenhRaVaoVien.xhtml";
		}
		else if(loaiBCDT.equals("BCBenhTruyenNhiem")){
			log.info("-------BCBenhTruyenNhiem ----------");
			return "/B2_Dieutri/B272_BaoCaoBenhTruyenNhiem.xhtml";
		}
		else if(loaiBCDT.equals("BCHD_PhauThuatThuThuat")){
			log.info("-------BCHD_PhauThuatThuThuat ----------");
			return "/B2_Dieutri/B217_BaoCaoHoatDongPhauThuatThuThuat.xhtml";
		}
		else if(loaiBCDT.equals("BCCoQuanYTeChuyenDen")){
			log.info("-------InDSCoQuanYTeChuyenDen ----------");
			return "/B2_Dieutri/B274_InDSCoQuanYTeChuyenDen.xhtml";
		}
		else if(loaiBCDT.equals("BCHoatDongKhamBenh")){
			log.info("-------BCHoatDongKhamBenh ----------");
			return "/B2_Dieutri/B275_BaoCaoHoatDongKhamBenh.xhtml";
		}
		else if(loaiBCDT.equals("BCDuocBenhVien")){
			log.info("-------BCDuocBenhVien ----------");
			return "/B2_Dieutri/B295_Baocaoduocbenhvien.xhtml";
		}
		else if(loaiBCDT.equals("BCSotXuatHuyet")){
			log.info("-------BCSotXuatHuyet ----------");
			return "/B2_Dieutri/B296_Baocaosotxuathuyet.xhtml";
		}
		else if(loaiBCDT.equals("BCTinhHinhHoatDongTrongNgay")){
			log.info("-------BCTinhHinhHoatDongTrongNgay ----------");
			return "/B2_Dieutri/B276_BaoCaoTinhHinhHoatDongTrongNgay.xhtml";
		}
		else if(loaiBCDT.equals("BCTinhHinhBenhTatVaTuVongTaiBV")){
			log.info("-------BCTinhHinhBenhTatVaTuVongTaiBV ----------");
			return "/B2_Dieutri/B277_BaoCaoTinhHinhBenhTatVaTuVongTaiBV.xhtml";
		}
		else if(loaiBCDT.equals("BCHoatDongCanLamSan")){
			log.info("-------BCHoatDongCanLamSan ----------");
			return "/B2_Dieutri/B278_BaoCaoHoatDongCanLamSan.xhtml";
		}
		else if(loaiBCDT.equals("BCHoatDongCacKhoa")){
			log.info("-------BCHoatDongCacKhoa ----------");
			return "/B2_Dieutri/B279_BaoCaoHoatDongCacKhoa.xhtml";
		}
		else if(loaiBCDT.equals("DanhSachBenhAnDangCapNhatTaiKhoa")){
			log.info("------- DanhSachBenhAnDangCapNhatTaiKhoa ----------");
			return "/B2_Dieutri/B280_DanhSachBenhAnDangCapNhatTaiKhoa.xhtml";
		}
		else if(loaiBCDT.equals("BaoCaoThongKeTaiNan")){
			log.info("------- BaoCaoThongKeTaiNan ----------");
			return "/B2_Dieutri/B300_Baocaothongketainan.xhtml";
		}
		else if(loaiBCDT.equals("DanhSachBenhNhanChuyenVien")){
			log.info("------- DanhSachBenhNhanChuyenVien ----------");
			return "/B2_Dieutri/B281_DanhSachBenhNhanChuyenVien.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBATmh")){
			log.info("-------CapNhatThongTinChiTietBATmh ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbatmh.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBARhm")){
			log.info("-------CapNhatThongTinChiTietBARhm ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbarhm.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANhikhoa")){
			log.info("-------CapNhatThongTinChiTietBANhikhoa ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbanhikhoa.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBASosinh")){
			log.info("-------CapNhatThongTinChiTietBASosinh ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbasosinh.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBAPhukhoa")){
			log.info("-------CapNhatThongTinChiTietBAPhukhoa ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbaphukhoa.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBASankhoa")){
			log.info("-------CapNhatThongTinChiTietBASankhoa ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbasankhoa.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANgoaitruYhct")){
			log.info("-------CapNhatThongTinChiTietBANgoaitruYhct ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbangoaitruyhct.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANaophathai")){
			log.info("-------CapNhatThongTinChiTietBANaophathai ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbanaophathai.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANoitruYhct")){
			log.info("-------CapNhatThongTinChiTietBANoitruYhct ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbanoitruyhct.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBAMat")){
			log.info("-------CapNhatThongTinChiTietBAMat ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbamat.xhtml";
		}
		else if(loaiBCDT.equals("CapNhatThongTinChiTietBANgoai")){
			log.info("-------CapNhatThongTinChiTietBANgoai ----------");
			return "/B2_Dieutri/B211_3_Capnhathongtinchitietbangoai.xhtml";
		}
		else if(loaiBCDT.equals("SoSanhTinhHinhMacBenhTheoNam")){
			log.info("-------SoSanhTinhHinhMacBenhTheoNam ----------");
			return "/B2_Dieutri/B298_SoSanhTinhHinhMacBenhTheoNam.xhtml";
		}
		else if(loaiBCDT.equals("CanLamSanPhauThuat")){
			log.info("-------CanLamSanPhauThuat ----------");
			return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_CanLamSan";
		}else if(loaiBCDT.equals("Baocaohsba")){
			log.info("-------DieuTri_BaoCaoHsba_TheoTieuChi ----------");
			return "DieuTri_BaoCaoHsba_TheoTieuChi";
		}
		
		return null;
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
