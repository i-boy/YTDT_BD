/**
 * author : HOAI NAM
 */

package com.iesvn.yte.dieutri.vienphi.action;

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

@Name("ChonXuatFileBaoCao_VienPhi")
@Scope(SESSION)
public class ChonXuatFileBaoCao_VienPhi implements Serializable  {
	@Logger
	private Log log;
	
	@Out(required=false)
	@In(required=false)
	private String reportPathVP;
	
	@Out(required=false)
	@In(required=false)
	private String reportTypeVP;
	
	@Out(required=false)
	@In(required=false)
	JasperPrint jasperPrintVP;
	
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
			JasperPrintManager.printReport(jasperPrintVP, true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void xuatFileAction(){
		log.info("bat dau xuat file" + chonFileXuat);
		String ketquaPath=null;
		String tenfile=null;
		ketquaPath=IConstantsRes.PATH_BASE+IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI+"vienphi/";
		if(reportTypeVP.equals("Bangkethuchitamungtheongay")){
			log.info("-------Bang ke thu chi tam ung theo ngay-----------");
			tenfile="Bangkethuchitamungtheongay";
		}else if(reportTypeVP.equals("Bangkethuchithanhtoantheongay")){
			log.info("-------Bang ke thu chi thanh toan theo ngay-----------");
			tenfile="Bangkethuchithanhtoantheongay";
		}else if(reportTypeVP.equals("Tonghopthuchithanhtoanravien")){
			log.info("-------Tong hop thu chi thanh toan ra vien-----------");
			tenfile="Tonghopthuchithanhtoanravien";
		}else if(reportTypeVP.equals("Solieubenhnhanduocxetmiengiam")){
			log.info("-------So lieu benh nhan duoc xet mien giam-----------");
			tenfile="Solieubenhnhanduocxetmiengiam";
		}else if(reportTypeVP.equals("Solieucanlamsannoitrungoaitru")){
			log.info("-------So lieu can lam san noi tru ngoai tru-----------");
			tenfile="Solieucanlamsannoitrungoaitru";
		}else if(reportTypeVP.equals("Solieuthuchitoanvien")){
			log.info("-------So lieu thu chi toan vien-----------");
			tenfile="Solieuthuchitoanvien";
		}else if(reportTypeVP.equals("Solieuthuchicackhoa")){
			log.info("-------So lieu thu chi cac khoa-----------");
			tenfile="Solieuthuchicackhoa";
		}else if(reportTypeVP.equals("giaytamung")){
			log.info("-------Giay tam ung-----------");
			tenfile="giaytamung";
		}else if(reportTypeVP.equals("chitrabottientamung")){
			log.info("-------Giay chi tra bot tien tam ung-----------");
			tenfile="chitrabottientamung";
		}else if(reportTypeVP.equals("giaymiengiam")){
			log.info("-------Giay mien giam-----------");
			tenfile="giaymiengiam";

		}else if(reportTypeVP.equals("capnhattientamung_pcc")){
			log.info("-------Cap nhat tien tam ung----------");
			tenfile="capnhattientamung_pcc";
		}else if(reportTypeVP.equals("Phieucongkhai1bn")){
			log.info("-------Phieu cong khai mot benh nhan----------");
			tenfile="Phieucongkhai1bn";
		}else if(reportTypeVP.equals("Xeminlientucphieucongkhai")){
			log.info("-------Xem in lien tuc phieu cong khai----------");
			tenfile="Xeminlientucphieucongkhai";
		}else if(reportTypeVP.equals("B3125_Phieulinhthuoc")){
			log.info("-------B3125_Phieulinhthuoc-----------");
			tenfile="B3125_Phieulinhthuoc";
		}else if(reportTypeVP.equals("Tonghopthuocydungcutheophong")){
			log.info("-------Tong hop thuoc y dung cu theo phong-----------");
			tenfile="Tonghopthuocydungcutheophong";
		}else if(reportTypeVP.equals("Tonghopthuocydungcutheongaysudung")){
			log.info("-------Tong hop thuoc y dung cu theo ngay su dung-----------");
			tenfile="Tonghopthuocydungcutheongaysudung";
		}else if(reportTypeVP.equals("B3125_Lapphieudutru")){
			log.info("-------B3125_Lapphieudutru-----------");
			tenfile="B3125_Lapphieudutru";
		}else if(reportTypeVP.equals("B3125_2_KhoaPhongTraTheoPDT")){
			log.info("-------B3125_2_KhoaPhongTraTheoPDT-----------");
			tenfile="B3125_2_KhoaPhongTraTheoPDT";
		}else if(reportTypeVP.equals("ThanhToanRaVien")){
			log.info("-------ThanhToanRaVien-----------");
			tenfile="ThanhToanRaVien";
		}else if(reportTypeVP.equals("BaocaoBHYTnoitru")){
			log.info("-------BaocaoBHYTnoitru-----------");
			tenfile="BaocaoBHYTnoitru";
		}else if(reportTypeVP.equals("BaocaoBHYTCLSngoaitru")){
			log.info("-------BaocaoBHYTCLSngoaitru-----------");
			tenfile="BaocaoBHYTCLSngoaitru";
		}else if(reportTypeVP.equals("BaocaoBHYTtaiphongphatthuoc")){
			log.info("-------BaocaoBHYTtaiphongphatthuoc-----------");
			tenfile="BaocaoBHYTtaiphongphatthuoc";
		}else if(reportTypeVP.equals("BaocaoBHYTNgoaitru")){
			log.info("-------BaocaoBHYTNgoaitru-----------");
			tenfile="BaocaoBHYTNgoaitru";
		}else if(reportTypeVP.equals("TomtatsolieuBHYTsechitra")){
			log.info("-------TomtatsolieuBHYTsechitra-----------");
			tenfile="TomtatsolieuBHYTsechitra";
		}else if(reportTypeVP.equals("BHYTChiPhiKBCtheoNhom")){
			log.info("-------BHYTChiPhiKBCtheoNhom-----------");
			tenfile="BHYTChiPhiKBCtheoNhom";
		}else if(reportTypeVP.equals("Thuocydungcuphongkham")){
			log.info("-------Thuocydungcuphongkham-----------");
			tenfile="Thuocydungcuphongkham";
		}else if(reportTypeVP.equals("B3232_Phieuthanhtoanclsphongkham")){
			log.info("-------B3232_Phieuthanhtoanclsphongkham-----------");
			tenfile="B3232_Phieuthanhtoanclsphongkham";
		}else if(reportTypeVP.equals("thanhtoanphongcapcuu")){
			log.info("-------B3232_Phieuthanhtoanclsphongkham-----------");
			tenfile="thanhtoanphongcapcuu";
		}else if(reportTypeVP.equals("Hoanthutiencanlamsang")){
			log.info("-------B3232_Phieuthanhtoanclsphongkham-----------");
			tenfile="Hoanthutiencanlamsang";
		}else if(reportTypeVP.equals("V06_Chiphicanlamsangbaravien")){
			log.info("-------V06_Chiphicanlamsangbaravien-----------");
			tenfile="V06_Chiphicanlamsangbaravien";
		}else if(reportTypeVP.equals("V10_PTthucanlamsangbnkhambenh")){
			log.info("-------V10_PTthucanlamsangbnkhambenh-----------");
			tenfile="V10_PTthucanlamsangbnkhambenh";
		}else if(reportTypeVP.equals("V11_PTTienphongvamoyeucau")){
			log.info("-------V11_PTTienphongvamoyeucau-----------");
			tenfile="V11_PTTienphongvamoyeucau";
		}else if(reportTypeVP.equals("V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD")){
			log.info("-------V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD-----------");
			tenfile="V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD";
		}else if(reportTypeVP.equals("BaoCaoTongHopChiPhiKCBHYTDaTuyen")){
			log.info("-------BaoCaoTongHopChiPhiKCBHYTDaTuyen-----------");
			tenfile="BaoCaoTongHopChiPhiKCBHYTDaTuyen";
		}else if(reportTypeVP.equals("ThongKeSoLuotBN")){
			log.info("-------ThongKeSoLuotBN-----------");
			tenfile="ThongKeSoLuotBN";
		}else if(reportTypeVP.equals("Xacnhanthongtindieutri")){
			log.info("-------Xacnhanthongtindieutri-----------");
			tenfile="Xacnhanthongtindieutri";
		}else if(reportTypeVP.equals("V14_Thanhtoanchiphibenhnhandieutridoituongthuphi")){
			log.info("-------V14_Thanhtoanchiphibenhnhandieutridoituongthuphi-----------");
			tenfile="V14_Thanhtoanchiphibenhnhandieutridoituongthuphi";
		}else if(reportTypeVP.equals("lapphieubaoanhangngay")){
			log.info("-------V14_Thanhtoanchiphibenhnhandieutridoituongthuphi-----------");
			tenfile="lapphieubaoanhangngay";
		}else if(reportTypeVP.equals("BaocaoBHYTnoitruNhi")){
			log.info("-------BaocaoBHYTnoitruNhi-----------");
			tenfile="BaocaoBHYTnoitruNhi";
		}else if(reportTypeVP.equals("BaocaoBHYTNgoaitruNhi")){
			log.info("-------BaocaoBHYTNgoaitruNhi-----------");
			tenfile="BaocaoBHYTNgoaitruNhi";
		}else if(reportTypeVP.equals("bangkethanhtoanvienphicactuyen")){
			log.info("-------bangkethanhtoanvienphicactuyen-----------");
			tenfile="bangkethanhtoanvienphicactuyen";
		}else if(reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhong")){
			log.info("-------PhieuLanhThuocTuTrucKhoaPhong-----------");
			tenfile="PhieuLanhThuocTuTrucKhoaPhong";
		}else if(reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhongNGT")){
			log.info("-------PhieuLanhThuocTuTrucKhoaPhongNGT-----------");
			tenfile="PhieuLanhThuocTuTrucKhoaPhongNGT";
		}else if(reportTypeVP.equals("PhieuTraThuocTuTrucKhoaPhong")){
			log.info("-------PhieuTraThuocTuTrucKhoaPhong-----------");
			tenfile="PhieuTraThuocTuTrucKhoaPhong";
		} else if(reportTypeVP.equals("lapphieubaoanhangngay_form")){
			log.info("-------lapphieubaoanhangngay_form-----------");
			tenfile =  "LapPhieuBaoAnHangNgay";
		} else if(reportTypeVP.equals("xuatphieubaoan")){
			log.info("-------xuatphieubaoan-----------");
			tenfile =  "XuatPhieuBaoAn";
		} else if(reportTypeVP.equals("xuatphieubangiaokhauphanan")){
			log.info("-------xuatphieubangiaokhauphanan-----------");
			tenfile =  "Phieubangiaokhauphanan";
		} else if(reportTypeVP.equals("PhieugiaobankhoaDD")){
			log.info("-------PhieugiaobankhoaDD-----------");
			tenfile =  "PhieugiaobankhoaDD";
		}else if(reportTypeVP.equals("baocaosuaduongnhi")){
			log.info("-------baocaosuaduongnhi-----------");
			tenfile =  "BaoCaoSuaDuongNhi";
		}else if(reportTypeVP.equals("xuatphieukynhanspdd")){
			log.info("-------xuatphieukynhanspdd-----------");
			tenfile =  "PhieuKyNhanSPDD";
		}else if(reportTypeVP.equals("baocaotienanbn")){
			log.info("-------baocaotienanbn-----------");
			tenfile =  "BaoCaoTienAnBN";
		}else if(reportTypeVP.equals("phieugiaokhauphanan")){
			log.info("-------phieugiaokhauphanan-----------");
			tenfile =  "PhieuGiaoKhauPhanAn";
		}else if(reportTypeVP.equals("xuatdutrutp")){
			log.info("-------xuatdutrutp-----------");
			tenfile =  "BangDuTruThucPham";
		}else if(reportTypeVP.equals("baocaosanphamDD")){
			log.info("-------baocaosanphamDD-----------");
			tenfile =  "BaoCaoSanPhamDD";
		}else if(reportTypeVP.equals("baocaotienanCBTC")){
			log.info("-------baocaotienanCBTC-----------");
			tenfile =  "BaoCaoTienAnCBTC";
		}else if(reportTypeVP.equals("baocaovienphi")){
			log.info("-------baocaovienphi-----------");
			tenfile =  "BaoCaoVienPhi";
		}else if(reportTypeVP.equals("baocaonuoc")){
			log.info("-------baocaonuoc-----------");
			tenfile =  "BaoCaoNuoc";
		}else if(reportTypeVP.equals("B3160_Denghitamungthanhtoan")){
			log.info("-------B3160_Denghitamungthanhtoan-----------");
			tenfile =  "B3160_Denghitamungthanhtoan";
		}else if(reportTypeVP.equals("B3341_DSBenhnhanchothanhtoan")){
			log.info("-------B3341_DSBenhnhanchothanhtoan-----------");
			tenfile =  "B3341_DSBenhnhanchothanhtoan";
		}else if(reportTypeVP.equals("PhieuThanhToanKCBNgoaiTru_2")){
			log.info("-------PhieuThanhToanKCBNgoaiTru_2-----------");
			tenfile =  "PhieuThanhToanKCBNgoaiTru";
		}
									
		
		int index=0;
		String tempStr=null;
		tempStr=XuatReportUtil.ReportUtil(jasperPrintVP,index,ketquaPath,chonFileXuat.trim(),tenfile);
		setDuongdanFileXuat(tempStr.replaceFirst(IConstantsRes.PATH_BASE,""));
		//log.info("B3125_Phieulinhthuoc---reportTypeVP - :" + reportTypeVP);
		log.info("duong dan----" + getDuongdanFileXuat());
		setReportFinish(true);
	}
	
	public String troveAction(){
		setDuongdanFileXuat("");
		if(reportTypeVP.equals("Bangkethuchitamungtheongay")){
			log.info("-------Bang ke thu chi tam ung theo ngay-----------");
			return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiTamUngTheoNgay";
		}else if(reportTypeVP.equals("Bangkethuchithanhtoantheongay")){
			log.info("-------Bang ke thu chi thanh toan theo ngay-----------");
			return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiThanhToanTheoNgay";
		}else if(reportTypeVP.equals("Tonghopthuchithanhtoanravien")){
			log.info("-------Tong hop thu chi thanh toan ra vien-----------");
			return "BaoCaoVienPhi_SoLieuThanhToan_TongHopThuChiThanhToanRaVien";
		}else if(reportTypeVP.equals("Solieubenhnhanduocxetmiengiam")){
			log.info("-------So lieu benh nhan duoc xet mien giam-----------");
			return "BaoCaoVienPhi_HoSoBaoCao_SoLieuBNDuocXetMienGiam";
		}else if(reportTypeVP.equals("Solieucanlamsannoitrungoaitru")){
			log.info("-------So lieu can lam san noi tru ngoai tru-----------");
			return "";
		}else if(reportTypeVP.equals("Solieuthuchitoanvien")){
			log.info("-------So lieu thu chi toan vien-----------");
			return "BaoCaoVienPhi_HoSoBaoCao_SoLieuThuChiToanVien";
		}else if(reportTypeVP.equals("Solieuthuchicackhoa")){
			log.info("-------So lieu thu chi cac khoa-----------");
			return "";
		}else if(reportTypeVP.equals("giaytamung")){
			log.info("-------Giay tam ung-----------");
			return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatTienTamUng";
		}else if(reportTypeVP.equals("chitrabottientamung")){
			log.info("-------Giay chi tra bot tien tam ung-----------");
			return "ThuVienPhi_SoLieuDieuTriTamUng_ChiTraBotTienTamUng";
		}else if(reportTypeVP.equals("giaymiengiam")){
			log.info("-------Giay mien giam-----------");
			return "ThuVienPhi_SoLieuDieuTriTamUng_CapNhatMienGiam";
		}else if(reportTypeVP.equals("capnhattientamung_pcc")){
			log.info("-------Cap nhat tien tam ung----------");
			return "ThuVienPhi_SoLieuKhamBenh_TamUngPhongCapCuu";
		}else if(reportTypeVP.equals("Phieucongkhai1bn")){
			log.info("-------Phieu cong khai mot benh nhan-----------");
			return "VienPhiTaiKhoa_XemInPhieu_PhieuCongKhaiCuaBN";
		}else if(reportTypeVP.equals("Xeminlientucphieucongkhai")){
			log.info("-------Xem in lien tuc phieu cong khai-----------");
			return "VienPhiTaiKhoa_XemInPhieu_XemInLienTucPhieuCongKhai";
		}else if(reportTypeVP.equals("B3125_Phieulinhthuoc")){
			log.info("-------Giay mien giam-----------");
			return "VienPhiTaiKhoa_XemInPhieu_InSoThuoc";
		}else if(reportTypeVP.equals("Tonghopthuocydungcutheophong")){
			log.info("-------Tong hop thuoc y dung cu theo phong-----------");
			return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoPhong";
		}else if(reportTypeVP.equals("Tonghopthuocydungcutheongaysudung")){
			log.info("-------Tong hop thuoc y dung cu theo ngay su dung-----------");
			return "VienPhiTaiKhoa_XemInPhieu_TongHopThuocYDungCuTheoNgaySuDung";
		}else if(reportTypeVP.equals("B3125_Lapphieudutru")){
			log.info("-------B3125_Lapphieudutru-----------");
			return "B3125_Lapphieudutru";
		}else if(reportTypeVP.equals("B3125_2_KhoaPhongTraTheoPDT")){
			log.info("-------B3125_2_KhoaPhongTraTheoPDT-----------");
			return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTruTra";
		}else if(reportTypeVP.equals("ThanhToanRaVien")){
			log.info("-------ThanhToanRaVien-----------");
			return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
		}else if(reportTypeVP.equals("BaocaoBHYTnoitru")){
			log.info("-------BaocaoBHYTnoitru-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNoiTru";
		}else if(reportTypeVP.equals("BaocaoBHYTCLSngoaitru")){
			log.info("-------BaocaoBHYTCLSngoaitru-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTCLSNgoaiTru";
		}else if(reportTypeVP.equals("BaocaoBHYTtaiphongphatthuoc")){
			log.info("-------BaocaoBHYTtaiphongphatthuoc-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTTaiPhongPhatThuoc";
		}else if(reportTypeVP.equals("BaocaoBHYTNgoaitru")){
			log.info("-------BaocaoBHYTNgoaitru-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_BaoCaoBHYTNgoaiTru";
		}else if(reportTypeVP.equals("TomtatsolieuBHYTsechitra")){
			log.info("-------TomtatsolieuBHYTsechitra-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_TomTatSoLieuBHYTSeChiTra";
		}else if(reportTypeVP.equals("BHYTChiPhiKBCtheoNhom")){
			log.info("-------BHYTChiPhiKBCtheoNhom-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_BHYTChiPhiKBCtheoNhom";
		}else if(reportTypeVP.equals("Thuocydungcuphongkham")){
			log.info("-------Thuocydungcuphongkham-----------");
			return "ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham";
		}else if(reportTypeVP.equals("B3232_Phieuthanhtoanclsphongkham")){
			log.info("-------B3232_Phieuthanhtoanclsphongkham-----------");
			return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
		}else if(reportTypeVP.equals("thanhtoanphongcapcuu")){
			log.info("-------thanhtoanphongcapcuu-----------");
			return "TiepDon_KhamBenh_XemChiPhiDieuTri";
		}else if(reportTypeVP.equals("Hoanthutiencanlamsang")){
			log.info("-------Hoanthutiencanlamsang-----------");
			return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam";
		}else if(reportTypeVP.equals("V06_Chiphicanlamsangbaravien")){
			log.info("-------V06_Chiphicanlamsangbaravien-----------");
			return "BaoCaoVienPhi_HoSoBaoCao_SoLieuCLSNgoaiTru";
		}else if(reportTypeVP.equals("V10_PTthucanlamsangbnkhambenh")){
			log.info("-------V10_PTthucanlamsangbnkhambenh-----------");
			return "BaoCaoVienPhi_HoSoBaoCao_SoLieuThuTaiCacPhongKham";
		}else if(reportTypeVP.equals("V11_PTTienphongvamoyeucau")){
			log.info("-------V11_PTTienphongvamoyeucau-----------");
			return "BaoCaoVienPhi_HoSoBaoCao_PhanTichTienPhongMoYeuCau";
		}else if(reportTypeVP.equals("V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD")){
			log.info("-------V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_BHYT_DanhSachNguoiBenhKCBNgoaiTruDeNghiThanhToanMauC98aHD";
		}else if(reportTypeVP.equals("BaoCaoTongHopChiPhiKCBHYTDaTuyen")){
			log.info("-------BaoCaoTongHopChiPhiKCBHYTDaTuyen-----------");
			return "BaoCaoVienPhi_BaoCaoBHYT_BHYT_BangTongHopChiPhiKCBDaTuyen";
		}else if(reportTypeVP.equals("ThongKeSoLuotBN")){
			log.info("-------ThongKeSoLuotBN-----------");
			return "BaoCaoVienPhi_BHYT_ThongKeSoLuotTungBN";
		}else if(reportTypeVP.equals("Xacnhanthongtindieutri")){
			log.info("-------Xacnhanthongtindieutri-----------");
			return "VienPhiTaiKhoa_SoLieuBNSuDung_XacNhanThongTinDieuTri";
		}else if(reportTypeVP.equals("V14_Thanhtoanchiphibenhnhandieutridoituongthuphi")){
			log.info("-------V14_Thanhtoanchiphibenhnhandieutridoituongthuphi-----------");
			return "BaoCaoVienPhi_HoSoBaoCao_PhanTichChiPhiDieuTriNoiTru";
		}else if(reportTypeVP.equals("lapphieubaoanhangngay")){
			log.info("-------lapphieubaoanhangngay-----------");
			return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
		}else if(reportTypeVP.equals("BaocaoBHYTnoitruNhi")){
			log.info("-------BaocaoBHYTnoitruNhi-----------");
			return "baocaobhyt_noitru_nhi";
		}else if(reportTypeVP.equals("BaocaoBHYTNgoaitruNhi")){
			log.info("-------BaocaoBHYTNgoaitruNhi-----------");
			return "baocaobhyt_ngoaitru_nhi";
		}else if(reportTypeVP.equals("bangkethanhtoanvienphicactuyen")){
			log.info("-------bangkethanhtoanvienphicactuyen-----------");
			return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThanhToanVienPhi";
		}else if(reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhongNGT")){
			log.info("-------PhieuLanhThuocTuTrucKhoaPhongNGT-----------");
			return "VienPhiTaiKhoa_PhieuDTLanhThuocTuTruNGT";
		}else if(reportTypeVP.equals("PhieuLanhThuocTuTrucKhoaPhong")){
			log.info("-------PhieuLanhThuocTuTrucKhoaPhong-----------");
			return "VienPhiTaiKhoa_PhieuDTLanhThuocTuTru";
		}else if(reportTypeVP.equals("PhieuTraThuocTuTrucKhoaPhong")){
			log.info("-------PhieuTraThuocTuTrucKhoaPhong-----------");
			return "VienPhiTaiKhoa_PhieuDTTraThuocTuTru";
		} else if(reportTypeVP.equals("lapphieubaoanhangngay_form")){
			log.info("-------lapphieubaoanhangngay_form-----------");
			return "VienPhiTaiKhoa_LapPhieuBaoAnHangNgay";
		}else if(reportTypeVP.equals("xuatphieubaoan")){
			log.info("-------xuatphieubaoan-----------");
			return "/B3_Vienphi/TaiKhoa/B3142_Xuatphieubaoan.xhtml";
		} else if(reportTypeVP.equals("xuatphieubangiaokhauphanan")){
			log.info("-------xuatphieubangiaokhauphanan-----------");
			return "/B3_Vienphi/TaiKhoa/B3144_Xuatphieubangiaokhauphanan.xhtml";
		} else if(reportTypeVP.equals("PhieugiaobankhoaDD")){
			log.info("-------PhieugiaobankhoaDD-----------");
			return "/B3_Vienphi/TaiKhoa/B3141_Phieugiaoban.xhtml";
		}else if(reportTypeVP.equals("baocaosuaduongnhi")){
			log.info("-------baocaosuaduongnhi-----------");
			return "/B3_Vienphi/TaiKhoa/B3148_BaoCaoSuaDuongNhi.xhtml";
		}else if(reportTypeVP.equals("xuatphieukynhanspdd")){
			log.info("-------xuatphieukynhanspdd-----------");
			return "/B3_Vienphi/TaiKhoa/B3149_PhieuKyNhanSPDD.xhtml";
		}else if(reportTypeVP.equals("baocaotienanbn")){
			log.info("-------baocaotienanbn-----------");
			return "/B3_Vienphi/TaiKhoa/B3150_BaoCaoTienAnBN.xhtml";
		} else if(reportTypeVP.equals("phieugiaokhauphanan")){
			log.info("-------phieugiaokhauphanan-----------");
			return "/B3_Vienphi/TaiKhoa/B3151_PhieuGiaoKhauPhanAn.xhtml";
		} else if(reportTypeVP.equals("xuatdutrutp")){
			log.info("-------xuatdutrutp-----------");
			return "/B3_Vienphi/TaiKhoa/B3154_XuatDuTruThucPham.xhtml";
		}else if(reportTypeVP.equals("baocaosanphamDD")){
			log.info("-------baocaosanphamDD-----------");
			return "/B3_Vienphi/TaiKhoa/B3155_BaoCaoSanPhamDD.xhtml";
		}else if(reportTypeVP.equals("baocaotienanCBTC")){
			log.info("-------baocaotienanCBTC-----------");
			return "/B3_Vienphi/TaiKhoa/B3156_BaoCaoTienAnCBTC.xhtml";
		}else if(reportTypeVP.equals("baocaovienphi")){
			log.info("-------baocaovienphi-----------");
			return "/B3_Vienphi/TaiKhoa/B3157_BaoCaoVienPhi.xhtml";
		}else if(reportTypeVP.equals("baocaonuoc")){
			log.info("-------baocaonuoc-----------");
			return "/B3_Vienphi/TaiKhoa/B3159_BaoCaoNuoc.xhtml";
		}else if(reportTypeVP.equals("B3160_Denghitamungthanhtoan")){
			log.info("-------B3160_Denghitamungthanhtoan-----------");
			return "/B3_Vienphi/TaiKhoa/B3160_Denghitamungthanhtoan.xhtml";
		}
		else if(reportTypeVP.equals("B3341_DSBenhnhanchothanhtoan")){
			log.info("-------B3341_DSBenhnhanchothanhtoan-----------");
			return "/B3_Vienphi/BaoCao/B3341_DSBenhNhanChoThanhToan.xhtml";
		}else if(reportTypeVP.equals("PhieuThanhToanKCBNgoaiTru_2")){
			log.info("-------PhieuThanhToanKCBNgoaiTru_2-----------");
			return "ThuVienPhi_ThanhToanRaVien_ThanhToanRaVien";
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
