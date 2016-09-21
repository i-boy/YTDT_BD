/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.duocpham.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.iesvn.yte.XuatReportUtil;
import com.iesvn.yte.util.IConstantsRes;

@Name("ChonXuatFileBaoCao_KhoChinh")
@Scope(CONVERSATION)
public class ChonXuatFileBaoCao_KhoChinh implements Serializable {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathKC;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeKC;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintKC;
	
	private String chonFileXuat=null;
	
	private boolean reportFinish=false;
	private String duongdanFileXuat=null;
	
	@Begin(join=true)
	public String init(){
		setChonFileXuat("DOC");
		return "B4160_Chonmenuxuattaptin";
	}
	@End
	public void EndFunc(){
		
	}
	
	@Create
	public void initForm(){
		setChonFileXuat("DOC");
	} 
	
	public void inbaocao(){
		log.info("bat dau in bao cao");
		try{
			JasperPrintManager.printReport(jasperPrintKC, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void xuatFileAction(){
		log.info("bat dau xuat file" + chonFileXuat);
		String ketquaPath=null;
		String tenfile=null;
		ketquaPath=IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/";
		if(reportTypeKC.equals("BCnhapxuat1mathang")){
			log.info("-------Bao cao tinh hinh nhap xuat mot mat hang-----------");
			tenfile="BCnhapxuat1mathang";
		}else if(reportTypeKC.equals("Mathangkhongsd")){
			log.info("-------Bao cao mat hang khogn su dung-----------");
			tenfile="Mathangkhongsd";
		}else if(reportTypeKC.equals("Canhbaohangganhethan")){
			log.info("-------Bao cao mat hang dan het han-----------");
			tenfile="Canhbaohangganhethan";
		}else if(reportTypeKC.equals("Dstonkhokhonghandung")){
			log.info("-------Bao cao mat hang khong han dung-----------");
			tenfile="Tonkhokhonghandung";
		}else if(reportTypeKC.equals("Indanhsachtondau")){
			log.info("-------In danh sach ton dau-----------");
			tenfile="Solieutondau";
		}else if(reportTypeKC.equals("tinhhinhkinhphinhap")){
			log.info("-------Tinh hinh kinh phi nhap-----------");
			tenfile="Tinhhinhkinhphinhap";
		}else if(reportTypeKC.equals("Inbctiensudungtaikhoa")){
			log.info("-------Bao cao tien su dung-----------");
			tenfile="Baocaotiensudung";
		}else if(reportTypeKC.equals("Phantichsolieunhapxuat")){
			log.info("-------Phan tich so lieu nhap xuat----------");
			tenfile="Phantichsolieunhapxuat";
		}else if(reportTypeKC.equals("Inthephieu")){
			log.info("-------In the kho----------");
			tenfile="Inthekho";
		}else if(reportTypeKC.equals("Inbenhnhanlanhthuoc")){
			log.info("-------In benh nhan lanh thuoc----------");
			tenfile="Inbenhnhanlanhthuoc";
		}else if(reportTypeKC.equals("Inthongtinchitiet1mathang")){
			log.info("-------In thong tin chi tiet mot mat hang----------");
			tenfile="Inthongtinchitiet1mathang";
		}else if(reportTypeKC.equals("TKsophieuthuchien")){
			log.info("-------Thong ke so phieu thuc hien----------");
			tenfile="TKsophieuthuchien";
		}else if(reportTypeKC.equals("BCkettoanthuocchongtamthan")){
			log.info("-------bao cao ket toan chong tam than----------");
			tenfile="BCkettoanthuocchongtamthan";
		}else if(reportTypeKC.equals("Inbanglietkechungtu")){
			log.info("-------In bang liet ke chung tu----------");
			tenfile="Inbanglietkechungtu";
		}else if(reportTypeKC.equals("Inbaocaonhapxuattrongngay")){
			log.info("-------In bao cao nhap xuat trong ngay----------");
			tenfile="Inbaocaonhapxuattrongngay";
		}else if(reportTypeKC.equals("BCxuattrongngay")){
			log.info("-------In bao cao xuat trong ngay----------");
			tenfile="BCxuattrongngay";
		}else if(reportTypeKC.equals("Inchitietnhaphang")){
			log.info("-------In chi tiet nhap hang----------");
			tenfile="Inchitietnhaphang";
		}else if(reportTypeKC.equals("Capnhatbangkiemke")){
			log.info("-------Cap nhat bang kiem ke----------");
			tenfile="Capnhatbangkiemke";
		}else if(reportTypeKC.equals("inbangkiemke")){
			log.info("-------in bang kiem ke----------");
			tenfile="inbangkiemke";
		}else if(reportTypeKC.equals("Tinhtonkhovagiatri")){
			log.info("-------Tinh ton kho va gia tri----------");
			tenfile="Tinhtonkhovagiatri";
		}else if(reportTypeKC.equals("InphieuXHBNBHYT")){
			log.info("-------InphieuXHBNBHYT----------");
			tenfile="InphieuXHBNBHYT";
		}else if(reportTypeKC.equals("D01_Phieunhapkhothuocthuong")){
			log.info("-------D01_Phieunhapkhothuocthuong----------");
			tenfile="D01_Phieunhapkhothuocthuong";
		}else if(reportTypeKC.equals("D03_phieuxuatkho")){
			log.info("-------D03_phieuxuatkho----------");
			tenfile="D03_phieuxuatkho";
		}else if(reportTypeKC.equals("D03_phieuxuatkho_khoaphong")){
			log.info("-------D03_phieuxuatkho_khoaphong----------");
			tenfile="D03_phieuxuatkho_khoaphong";
		}else if(reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru")){
			log.info("-------B4122_XuatHangTheoPhieuDuTru----------");
			tenfile="B4122_XuatHangTheoPhieuDuTru";
		}else if(reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru_Tra")){
			log.info("-------B4122_XuatHangTheoPhieuDuTru_Tra----------");
			tenfile="B4122_XuatHangTheoPhieuDuTru_Tra";
		}else if(reportTypeKC.equals("Inbaocaonhapxuathang")){
			log.info("-------Inbaocaonhapxuathang----------");
			tenfile="Inbaocaonhapxuathang";
		}else if(reportTypeKC.equals("xuathangdenkhokhac")){
			log.info("-------xuathangdenkhokhac----------");
			tenfile="xuathangdenkhokhac";
		}else if(reportTypeKC.equals("phieutrahangtheodutrutra")){
			log.info("-------phieutrahangtheodutrutra----------");
			tenfile="phieutrahangtheodutrutra";
		}else if(reportTypeKC.equals("Canhbaoluongtonkho")){
			log.info("-------Canhbaoluongtonkho----------");
			tenfile="Canhbaoluongtonkho";
		}else if(reportTypeKC.equals("PhieuXuatTraNhaCungCap")){
			log.info("-------PhieuXuatTraNhaCungCap----------");
			tenfile="PhieuXuatTraNhaCungCap";
		}else if(reportTypeKC.equals("D27_phieutraBHYTChoKhoChinh")){
			log.info("-------PhieuXuatTraNhaCungCap----------");
			tenfile="D27_phieutraBHYTChoKhoChinh";
		}else if(reportTypeKC.equals("PhieuDuTruTraThuoc")){
			log.info("-------B4444_Phieutrahangbhytchokhochinh : PhieuXuatTraNhaCungCap----------");
			tenfile="PhieuDuTruTraThuoc";
		}else if(reportTypeKC.equals("PhieuDuTruLanhThuocCuaKhoLe")){
			log.info("-------PhieuDuTruLanhThuocCuaKhoLe----------");
			tenfile="PhieuDuTruLanhThuocCuaKhoLe";
		}else if(reportTypeKC.equals("TraHangKhoChinh")){
			log.info("-------TraHangKhoChinh----------");
			tenfile="TraHangKhoChinh";
		}else if(reportTypeKC.equals("PhieuThanhToanKCB")){
			log.info("-------PhieuThanhToanKCB----------");
			tenfile="PhieuThanhToanKCB";
		}else if(reportTypeKC.equals("PhieuThanhToanKCB_Thuphi")){
			log.info("-------PhieuThanhToanKCB_Thuphi----------");
			tenfile="PhieuThanhToanKCB_Thuphi";
		}
		
		
		
		int index=0;
		String tempStr=null;
		tempStr=XuatReportUtil.ReportUtil(jasperPrintKC,index,ketquaPath,chonFileXuat.trim(),tenfile);
		setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE,""));
		log.info("duong dan----" + getDuongdanFileXuat());
		setReportFinish(true);
	}
	
	public String troveAction(){
		log.info("bat dau xuat file" + chonFileXuat);
		String ketquaPath=null;
		String tenfile=null;
		ketquaPath=IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"duocpham/";
		if(reportTypeKC.equals("BCnhapxuat1mathang")){
			log.info("-------Bao cao tinh hinh nhap xuat mot mat hang-----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InChiTietNhapXuatMotMatHang";
		}else if(reportTypeKC.equals("Mathangkhongsd")){
			log.info("-------Bao cao mat hang khong su dung-----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangKhongSuDung";
		}else if(reportTypeKC.equals("Canhbaohangganhethan")){
			log.info("-------Bao cao mat hang dan het han-----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangGanHetHan";
		}else if(reportTypeKC.equals("Dstonkhokhonghandung")){
			log.info("-------Bao cao mat hang khong han dung-----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangTonKhoKhongHanDung";
		}else if(reportTypeKC.equals("Indanhsachtondau")){
			log.info("-------In danh sach ton dau-----------");
			return "QuanLyKhoChinh_KiemKeKhoChinh_InDanhSachTonDau";
		}else if(reportTypeKC.equals("tinhhinhkinhphinhap")){
			log.info("-------Tinh hinh kinh phi nhap-----------");
			return "BaoCaoDuoc_InBaoCaoKhoChinh_PhanTichKinhPhiNhap";
		}else if(reportTypeKC.equals("Inbctiensudungtaikhoa")){
			log.info("-------Bao cao tien su dung-----------");
			return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoTienSuDungTaiKhoa";
		}else if(reportTypeKC.equals("Phantichsolieunhapxuat")){
			log.info("-------Phan tich so lieu nhap xuat----------");
			return "";
		}else if(reportTypeKC.equals("Inthephieu")){
			log.info("-------In the kho----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InTheKho";
		}else if(reportTypeKC.equals("Inbenhnhanlanhthuoc")){
			log.info("-------In benh nhan lanh thuoc----------");
			return "BaoCaoDuoc_InBaoCaoKhoBHYT_InDanhSachBenhNhanLanhThuoc";
		}else if(reportTypeKC.equals("Inthongtinchitiet1mathang")){
			log.info("-------In thong tin chi tiet mot mat hang----------");
			return "";
		}else if(reportTypeKC.equals("InphieuXUATTHUOCBNBHYT")){
			log.info("-------Thong ke so phieu thuc hien----------");
			return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
		}else if(reportTypeKC.equals("TKsophieuthuchien")){
			log.info("-------Thong ke so phieu thuc hien----------");
			return "";
		}else if(reportTypeKC.equals("BCkettoanthuocchongtamthan")){
			log.info("-------bao cao ket toan chong tam than----------");
			return "";
		}else if(reportTypeKC.equals("Inbanglietkechungtu")){
			log.info("-------In bang liet ke chung tu----------");
			return "BaoCaoDuoc_InBaoCaoKhoChinh_InBangLietKeChungTu";
		}else if(reportTypeKC.equals("Inbaocaonhapxuattrongngay")){
			log.info("-------In bao cao nhap xuat trong ngay----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoNhapXuatTrongNgay";
		}else if(reportTypeKC.equals("BCxuattrongngay")){
			log.info("-------In bao cao xuat trong ngay----------");
			return "";
		}else if(reportTypeKC.equals("Inchitietnhaphang")){
			log.info("-------In chi tiet nhap hang----------");
			return "BaoCaoDuoc_InBaoCaoKhoChinh_InChiTietNhapXuatHang";
		}else if(reportTypeKC.equals("Inbangkiemke")){
			log.info("-------In bang kiem ke----------");
			return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
		}else if(reportTypeKC.equals("Tinhtonkhovagiatri")){
			log.info("-------Tinh ton kho va gia tri----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_TinhTonKhoVaGiaTri";
		}else if(reportTypeKC.equals("InphieuXHBNBHYT")){
			log.info("-------InphieuXHBNBHYT----------");
			//return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
			return "ThuVienPhi_SoLieuKhamBenh_ThanhToanBenhNhanBHYT";
		}else if(reportTypeKC.equals("D01_Phieunhapkhothuocthuong")){
			log.info("-------D01_Phieunhapkhothuocthuong----------");
			return "QuanLyKhoChinh_NhapKhoChinh_NhapHangTuNhaCungCap";
		}else if(reportTypeKC.equals("D03_phieuxuatkho")){
			log.info("-------D03_phieuxuatkho----------");
			return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT";
		}else if(reportTypeKC.equals("D03_phieuxuatkho_khoaphong")){
			log.info("-------D03_phieuxuatkho_khoaphong----------");
			return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTrucThanhLy";
		}else if(reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru")){
			log.info("-------B4122_XuatHangTheoPhieuDuTru----------");
			return "QuanLyKhoChinh_XuatKhoChinh_XuatHangTheoPhieuDuTru";
		}else if(reportTypeKC.equals("B4164_Phieuxuathangtutruc")){
			log.info("-------B4164_Phieuxuathangtutruc----------");
			return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenTuTruc";
		}else if(reportTypeKC.equals("B4122_XuatHangTheoPhieuDuTru_Tra")){
			log.info("-------B4122_XuatHangTheoPhieuDuTru_Tra----------");
			return "B4122_XuatHangTheoPhieuDuTru_Tra";
		}else if(reportTypeKC.equals("Inbaocaonhapxuathang")){
			log.info("-------Inbaocaonhapxuathang----------");
			return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatTrongThang";
		}else if(reportTypeKC.equals("xuathangdenkhokhac")){
			log.info("-------xuathangdenkhokhac----------");
			return "QuanLyKhoChinh_XuatKhoChinh_XuatHangDenKhoLeKhoBHYT";
		}else if(reportTypeKC.equals("TraHangKhoChinh")){
			log.info("-------TraHangKhoChinh----------");
			return "QuanLyKhoChinh_NhapKhoChinh_KhoLeTraHang";
		}else if(reportTypeKC.equals("phieutrahangtheodutrutra")){
			log.info("-------phieutrahangtheodutrutra----------");
			return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTheoPhieuDuTru";
		}else if(reportTypeKC.equals("phieutrahangcuatutruc")){
			log.info("-------phieutrahangcuatutruc----------");
			return "QuanLyKhoChinh_XuatKhoChinh_TraHangCuaTuTruc";
		}else if(reportTypeKC.equals("Canhbaoluongtonkho")){
			log.info("-------Canhbaoluongtonkho----------");
			return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_Canhbaoluongtonkho";
		}else if(reportTypeKC.equals("PhieuXuatTraNhaCungCap")){
			log.info("-------PhieuXuatTraNhaCungCap----------");
			return "Tranhacungcap";
		}else if(reportTypeKC.equals("D27_phieutraBHYTChoKhoChinh")){
			log.info("-------D27_phieutraBHYTChoKhoChinh----------");
			return "QuanLyKhoBHYT_XuatKhoBHYT_BHYTTraKhoChinh";
		}else if(reportTypeKC.equals("PhieuDuTruTraThuoc")){
			log.info("-------B4444_Phieutrahangbhytchokhochinh: PhieuDuTruTraThuoc----------");
			return "QuanLyKhoBHYT_XuatKhoBHYT_BHYTTraKhoChinh";
		}else if(reportTypeKC.equals("PhieuDuTruLanhThuocCuaKhoLe")){
			log.info("-------PhieuDuTruLanhThuocCuaKhoLe----------");
			return "QuanLyKhoChinh_NhapKhoChinh_DuTruLanhThuoc";
		}else if(reportTypeKC.equals("PhieuThanhToanKCB")){
			log.info("-------PhieuThanhToanKCB----------");
			return "ThuVienPhi_SoLieuKhamBenh_ThanhToanBenhNhanBHYT";
		}else if(reportTypeKC.equals("PhieuThanhToanKCB_Thuphi")){
			log.info("-------PhieuThanhToanKCB_Thuphi----------");
			return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
		}else if(reportTypeKC.equals("D03_phieutrakhoTD")){
			log.info("-------D03_phieutrakhoTD----------");
			return "QuanLyKhoChinh_NhapKhoChinh_TraHangTuyenDuoi";
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
