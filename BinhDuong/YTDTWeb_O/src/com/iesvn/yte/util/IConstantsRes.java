package com.iesvn.yte.util;

import org.jboss.seam.faces.FacesMessages;


public interface IConstantsRes {
    public static final String RESOURCES = "com.iesvn.yte.ApplicationResources";
    public static final String UIRESOURCES = "com.iesvn.yte.UIResources";
    //public static final String SERVER_IP = Utils.getResource("SERVER_IP", RESOURCES);
    
    //CONFIG LDAP
    public static final String LDAP_URL = Utils.getResource("LDAP_URL", RESOURCES);
    public static final String LDAP_USERNAME = Utils.getResource("LDAP_USERNAME", RESOURCES);
    public static final String LDAP_PASSWORD = Utils.getResource("LDAP_PASSWORD", RESOURCES);
    public static final String LDAP_VERSION = Utils.getResource("LDAP_VERSION", RESOURCES);
    public static final String LDAP_FACTORY_INITIAL = Utils.getResource("LDAP_FACTORY_INITIAL", RESOURCES);
    public static final String LDAP_REFERRAL = Utils.getResource("LDAP_REFERRAL", RESOURCES);
    public static final String LDAP_SECURITY_AUTHENTICATION = Utils.getResource("LDAP_SECURITY_AUTHENTICATION", RESOURCES);
    public static final String DN_BASE = Utils.getResource("DN_BASE", RESOURCES);
    
    //DINH NGHIA - Y TE DIEU TRI - ROLE
    public static final String TiepDon_TiepDonBenhNhan_DangKyKhamBenh = Utils.getResource("TiepDon_TiepDonBenhNhan_DangKyKhamBenh", RESOURCES);
    public static final String TiepDon_KhamBenh_ThamKhamXuTri = Utils.getResource("TiepDon_KhamBenh_ThamKhamXuTri", RESOURCES);
    public static final String TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan = Utils.getResource("TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan", RESOURCES);
    
    public static final String DieuTri_CapNhat_CapNhatThongTinChung = Utils.getResource("DieuTri_CapNhat_CapNhatThongTinChung", RESOURCES);
    public static final String DieuTri_CapNhat_CapNhatThongTinMo = Utils.getResource("DieuTri_CapNhat_CapNhatThongTinMo", RESOURCES);
    public static final String DieuTri_CapNhat_CapNhatThongTinNhapVien = Utils.getResource("DieuTri_CapNhat_CapNhatThongTinNhapVien", RESOURCES);
    public static final String DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat = Utils.getResource("DieuTri_BaoCaoHoatDongDieuTri_BaoCaoBenhAnDangCapNhat", RESOURCES);
    public static final String DieuTri_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung = Utils.getResource("DieuTri_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung", RESOURCES);
    
    public static final String VienPhi_SoLieuCanLamSanPhauThuat_LapPhieuDuTru = Utils.getResource("VienPhi_SoLieuCanLamSanPhauThuat_LapPhieuDuTru", RESOURCES);
    public static final String VienPhi_SoLieuKhamBenh_CanLamSanPhongKham = Utils.getResource("VienPhi_SoLieuKhamBenh_CanLamSanPhongKham", RESOURCES);
    public static final String VienPhi_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung = Utils.getResource("VienPhi_SoLieuBenhNhanSuDung_ThuocYDungCuSuDung", RESOURCES);
    public static final String VienPhi_ThanhToanRaVien_ThanhToanRaVien = Utils.getResource("VienPhi_ThanhToanRaVien_ThanhToanRaVien", RESOURCES);
    public static final String VienPhi_HoSoBaoCao_SoLieuCanLamSanNoiTruNgoaiTru = Utils.getResource("VienPhi_HoSoBaoCao_SoLieuCanLamSanNoiTruNgoaiTru", RESOURCES);
    
    public static final String DuocPham_NhapKho_NhapHangTuNhaCungCap = Utils.getResource("DuocPham_NhapKho_NhapHangTuNhaCungCap", RESOURCES);
    public static final String DuocPham_XuatKho_XuatHangTheoPhieuDuTru = Utils.getResource("DuocPham_XuatKho_XuatHangTheoPhieuDuTru", RESOURCES);
    public static final String DuocPham_XuatKho_XuatHangDenKhoKhacKhoBHYT = Utils.getResource("DuocPham_XuatKho_XuatHangDenKhoKhacKhoBHYT", RESOURCES);
    public static final String DuocPham_XuatKho_XuatHangchoBenhNhanBHYT = Utils.getResource("DuocPham_XuatKho_XuatHangchoBenhNhanBHYT", RESOURCES);
    public static final String DuocPham_BaoCaoKho_TinhTonKhoVaGiaTri = Utils.getResource("DuocPham_BaoCaoKho_TinhTonKhoVaGiaTri", RESOURCES);
    public static final String DuocPham_BaoCaoKho_BaoCaoNhapXuat = Utils.getResource("DuocPham_BaoCaoKho_BaoCaoNhapXuat", RESOURCES);
    public static final String DuocPham_TruyXuatTTKho_InTheKho = Utils.getResource("IN_THE_KHO_NOTFOUND_MLK", UIRESOURCES);
    
    
    public static final String YEAR_NUM_LENGTH = Utils.getResource("YEAR_NUM_LENGTH", RESOURCES);
    public static final String ORDER_NUM_LENGTH = Utils.getResource("ORDER_NUM_LENGTH", RESOURCES);
    
    public static final String MYSQL_DRIVER =Utils.getResource("MYSQL_DRIVER", RESOURCES);
    
    //DINH NGHIA - Y TE DU PHONG - ROLE
    public static final String VeSinhATTP_CongBoTCSP = Utils.getResource("VeSinhATTP_CongBoTCSP", RESOURCES);
    public static final String VeSinhATTP_DuDieuKienVSATTP = Utils.getResource("VeSinhATTP_DuDieuKienVSATTP", RESOURCES);
    public static final String VeSinhATTP_QuanLySoLieu_TinhHinhKinhPhi = Utils.getResource("VeSinhATTP_QuanLySoLieu_TinhHinhKinhPhi", RESOURCES);
    public static final String VeSinhATTP_QuanLySoLieu_TinhHinhXayDungMoHinhDiem = Utils.getResource("VeSinhATTP_QuanLySoLieu_TinhHinhXayDungMoHinhDiem", RESOURCES);
    public static final String VeSinhATTP_QuanLySoLieu_CongTacXetNghiem = Utils.getResource("VeSinhATTP_QuanLySoLieu_CongTacXetNghiem", RESOURCES);
    public static final String VeSinhATTP_QuanLySoLieu_ThongTinQuangCaoSanPham = Utils.getResource("VeSinhATTP_QuanLySoLieu_ThongTinQuangCaoSanPham", RESOURCES);
    public static final String VeSinhATTP_QuanLySoLieu_CongTacChiDaoTuyenTruyenThanhKiemTra = Utils.getResource("VeSinhATTP_QuanLySoLieu_CongTacChiDaoTuyenTruyenThanhKiemTra", RESOURCES);
    public static final String VeSinhATTP_TongHopBaoCao = Utils.getResource("VeSinhATTP_TongHopBaoCao", RESOURCES);
    public static final String BenhTruyenNhiem_HoSoDichBenh_NhapMoi = Utils.getResource("BenhTruyenNhiem_HoSoDichBenh_NhapMoi", RESOURCES);
    public static final String BenhTruyenNhiem_HoSoDichBenh_TimKiem = Utils.getResource("BenhTruyenNhiem_HoSoDichBenh_TimKiem", RESOURCES);
    public static final String BenhTruyenNhiem_CaBenhTruyenNhiem_NhapMoi = Utils.getResource("BenhTruyenNhiem_CaBenhTruyenNhiem_NhapMoi", RESOURCES);
    public static final String BenhTruyenNhiem_CaBenhTruyenNhiem_TimKiem = Utils.getResource("BenhTruyenNhiem_CaBenhTruyenNhiem_TimKiem", RESOURCES);
    public static final String BenhTruyenNhiem_TongHopBaoCao = Utils.getResource("BenhTruyenNhiem_TongHopBaoCao", RESOURCES);
    public static final String CSSKSS_SucKhoeBaMe_PhaThai = Utils.getResource("CSSKSS_SucKhoeBaMe_PhaThai", RESOURCES);
    public static final String CSSKSS_KeHoachHoaGiaDinh_DungBCS = Utils.getResource("CSSKSS_KeHoachHoaGiaDinh_DungBCS", RESOURCES);
    public static final String CSSKSS_SoLieuCSSKSS_SoLieuChuongTrinhCSSKSS = Utils.getResource("CSSKSS_SoLieuCSSKSS_SoLieuChuongTrinhCSSKSS", RESOURCES);
    public static final String CSSKSS_TongHopBaoCao = Utils.getResource("CSSKSS_TongHopBaoCao", RESOURCES);
    public static final String MTYTQuocGia_TiemChungMoRong_KetQuaTiemChungTreEm = Utils.getResource("MTYTQuocGia_TiemChungMoRong_KetQuaTiemChungTreEm", RESOURCES);
    
    public static final String PATH_BASE = Utils.getResource("PATH_BASE", RESOURCES);
    public static final String PATH_GEAR_SERVER = Utils.getResource("PATH_GEAR_SERVER", RESOURCES);
    public static final String PATH_GEAR_SHARE = Utils.getResource("PATH_GEAR_SHARE", RESOURCES);
    public static final String SHARE_FOLDER_IP_USERNAME = Utils.getResource("SHARE_FOLDER_IP_USERNAME", RESOURCES);
    public static final String SHARE_FOLDER_PASS = Utils.getResource("SHARE_FOLDER_PASS", RESOURCES);
    public static final String PATH_REPORT_TEMPLATE_DIEU_TRI = Utils.getResource("PATH_REPORT_TEMPLATE_DIEU_TRI", RESOURCES);
    public static final String PATH_REPORT_RESULT_DIEU_TRI = Utils.getResource("PATH_REPORT_RESULT_DIEU_TRI", RESOURCES);
    
    public static final String REPORT_DIEUTRI_TINH = Utils.getResource("REPORT_DIEUTRI_TINH", RESOURCES);
    public static final String REPORT_DIEUTRI_SO_Y_TE = Utils.getResource("REPORT_DIEUTRI_SO_Y_TE", RESOURCES);
	public static final String REPORT_DIEUTRI_SO_Y_TE_SHORT = Utils.getResource("REPORT_DIEUTRI_SO_Y_TE_SHORT", RESOURCES);
    public static final String REPORT_DIEUTRI_TEN_DON_VI = Utils.getResource("REPORT_DIEUTRI_TEN_DON_VI", RESOURCES);
    public static final String REPORT_DIEUTRI_TEN_DON_VI_FULL = Utils.getResource("REPORT_DIEUTRI_TEN_DON_VI_FULL", RESOURCES);
    public static final String REPORT_DIEUTRI_DIEN_THOAI_DON_VI = Utils.getResource("REPORT_DIEUTRI_DIEN_THOAI_DON_VI", RESOURCES);
    public static final String REPORT_DIEUTRI_MA_TINH = Utils.getResource("REPORT_DIEUTRI_MA_TINH", RESOURCES);
    public static final String REPORT_DIEUTRI_MA_DON_VI = Utils.getResource("REPORT_DIEUTRI_MA_DON_VI", RESOURCES);
    public static final String REPORT_DIEUTRI_DIA_DIEM = Utils.getResource("REPORT_DIEUTRI_DIA_DIEM", RESOURCES);
    public static final String REPORT_DIEUTRI_DIA_DIEM_QUAN_HUYEN = Utils.getResource("REPORT_DIEUTRI_DIA_DIEM_QUAN_HUYEN", RESOURCES);
    public static final String REPORT_DIEUTRI_DIA_CHI = Utils.getResource("REPORT_DIEUTRI_DIA_CHI", RESOURCES);
    public static final String REPORT_DIEUTRI_MA_SO_THUE = Utils.getResource("REPORT_DIEUTRI_MA_SO_THUE", RESOURCES);
    public static final String REPORT_DIEUTRI_GIAMDOC_BV = Utils.getResource("REPORT_DIEUTRI_GIAMDOC_BV", RESOURCES);
    public static final String REPORT_BIENLAITHUTIEN = Utils.getResource("REPORT_BIENLAITHUTIEN", RESOURCES);
    public static final String REPORT_HOADON = Utils.getResource("REPORT_HOADON", RESOURCES);
    
    public static final String DATABASE_URL = Utils.getResource("DATABASE_URL", RESOURCES);
    public static final String ORACLE_DRIVER = Utils.getResource("ORACLE_DRIVER", RESOURCES);    
    public static final String DK_ONLINE_DB_URL = Utils.getResource("DK_ONLINE_DB_URL", RESOURCES);
    public static final String SQL_FORMAT_DATE = Utils.getResource("SQL_FORMAT_DATE", RESOURCES);
    public static final String SQL_FORMAT_DATETIME = Utils.getResource("SQL_FORMAT_DATETIME", RESOURCES);
    public static final String DATABASE_USER = Utils.getResource("DATABASE_USER", RESOURCES);
    public static final String DATABASE_PASS = Utils.getResource("DATABASE_PASS", RESOURCES);
    public static final String DK_ONLINE_DB_USER = Utils.getResource("DK_ONLINE_DB_USER", RESOURCES);
    public static final String DK_ONLINE_DB_PASS = Utils.getResource("DK_ONLINE_DB_PASS", RESOURCES);
//    public static final String WEB_NAME = Utils.getResource("WEB_NAME", RESOURCES);
//    public static final String WEB_PATH = Utils.getResource("WEB_PATH", RESOURCES);
    public static final String WEB_NAME = Utils.getResource("WEB_NAME", RESOURCES);
    public static final String WEB_PATH = Utils.getResource("WEB_PATH", RESOURCES);
    public static final String MAU_TOA_THUOC = Utils.getResource("MAU_TOA_THUOC", RESOURCES);
    
    public static final String DANHSACHTK_NULL = Utils.getResource("DANHSACHTK_NULL", UIRESOURCES);
    
    // giay chung thuong
    
    public static final String GCT_LT_THANHCONG = Utils.getResource("GCT_LT_THANHCONG", UIRESOURCES);
    public static final String GCT_LT_THATBAI = Utils.getResource("GCT_LT_THATBAI", UIRESOURCES);
    public static final String GCT_CN_THANHCONG = Utils.getResource("GCT_CN_THANHCONG", UIRESOURCES);
    public static final String GCT_CN_THATBAI = Utils.getResource("GCT_CN_THATBAI", UIRESOURCES);
    
    // giay chung nhan phau thuat
    
    public static final String GCNPT_LT_THANHCONG = Utils.getResource("GCNPT_LT_THANHCONG", UIRESOURCES);
    public static final String GCNPT_LT_THATBAI = Utils.getResource("GCNPT_LT_THATBAI", UIRESOURCES);
    public static final String GCNPT_CN_THANHCONG = Utils.getResource("GCNPT_CN_THANHCONG", UIRESOURCES);
    public static final String GCNPT_CN_THATBAI = Utils.getResource("GCNPT_CN_THATBAI", UIRESOURCES);
    
    // giay ra vien
    
    public static final String GRV_LT_THANHCONG = Utils.getResource("GRV_LT_THANHCONG", UIRESOURCES);
    public static final String GRV_LT_THATBAI = Utils.getResource("GRV_LT_THATBAI", UIRESOURCES);
    public static final String GRV_CN_THANHCONG = Utils.getResource("GRV_CN_THANHCONG", UIRESOURCES);
    public static final String GRV_CN_THATBAI = Utils.getResource("GRV_CN_THATBAI", UIRESOURCES);
    
    public static final String GCT_NULL = Utils.getResource("GCT_NULL", UIRESOURCES);
    public static final String GCNPT_NULL = Utils.getResource("GCNPT_NULL", UIRESOURCES);
    public static final String GRV_NULL = Utils.getResource("GRV_NULL", UIRESOURCES);
    public static final String HSBA_BY_MTD_NULL = Utils.getResource("HSBA_BY_MTD_NULL", UIRESOURCES);
    
	// giay chuyển xác
    
    public static final String GCX_LT_THANHCONG = Utils.getResource("GCX_LT_THANHCONG", UIRESOURCES);
    public static final String GCX_LT_THATBAI = Utils.getResource("GCX_LT_THATBAI", UIRESOURCES);
    public static final String GCX_CN_THANHCONG = Utils.getResource("GCX_CN_THANHCONG", UIRESOURCES);
    public static final String GCX_CN_THATBAI = Utils.getResource("GCX_CN_THATBAI", UIRESOURCES);
    public static final String GCX_NULL = Utils.getResource("GCX_NULL", UIRESOURCES);

    //giay chuyen vien
    public static final String GCV_LT_THANHCONG = Utils.getResource("GCV_LT_THANHCONG", UIRESOURCES);
    public static final String GCV_LT_THATBAI = Utils.getResource("GCV_LT_THATBAI", UIRESOURCES);
    public static final String GCV_CN_THANHCONG = Utils.getResource("GCV_CN_THANHCONG", UIRESOURCES);
    public static final String GCV_CN_THATBAI = Utils.getResource("GCV_CN_THATBAI", UIRESOURCES);
    public static final String GCV_NULL = Utils.getResource("GCV_NULL", UIRESOURCES);
    
    //tiep don cap cuu
    public static final String TDCC_NULL_MATD = Utils.getResource("TDCC_NULL_MATD", UIRESOURCES);
    public static final String TDCC_NULL_MABN = Utils.getResource("TDCC_NULL_MABN", UIRESOURCES);
    
    //phan cong cum thu phi
    public static final String PCCTP_NULL = Utils.getResource("PCCTP_NULL", UIRESOURCES);
    public static final String PCCTP_SEARCH_NULL = Utils.getResource("PCCTP_SEARCH_NULL", UIRESOURCES);
    public static final String PCCTP_DEL_SUC = Utils.getResource("PCCTP_DEL_SUC", UIRESOURCES);
    public static final String PCCTP_DEL_FAIL = Utils.getResource("PCCTP_DEL_FAIL", UIRESOURCES);
    public static final String PCCTP_INS_FAIL = Utils.getResource("PCCTP_INS_FAIL", UIRESOURCES);
    
    //kiem ke kho
    public static final String KKK_NULL = Utils.getResource("KKK_NULL", UIRESOURCES);
    public static final String KKK_CN_THANHCONG = Utils.getResource("KKK_CN_THANHCONG", UIRESOURCES);
    public static final String KKK_CN_THATBAI = Utils.getResource("KKK_CN_THATBAI", UIRESOURCES);
    
    public static final String XUATHANG_BENHNHAN_BHYT_THATBAI = Utils.getResource("XUATHANG_BENHNHAN_BHYT_THATBAI", UIRESOURCES);

    public static final String CAP_TRIEN_KHAI_PHAN_MEM = Utils.getResource("CAP_TRIEN_KHAI_PHAN_MEM", RESOURCES);
    public static final String MASO_HUYEN_TRIEN_KHAI = Utils.getResource("MASO_HUYEN_TRIEN_KHAI", RESOURCES);
    public static final String MASO_TUYEN_BV_TRIEN_KHAI = Utils.getResource("MASO_TUYEN_BV_TRIEN_KHAI", RESOURCES);
    public static final String TINH_DEFAULT = Utils.getResource("TINH_DEFAULT", RESOURCES);
    public static final String HUYEN_DEFAULT = Utils.getResource("HUYEN_DEFAULT", RESOURCES);
    public static final String XA_DEFAULT = Utils.getResource("XA_DEFAULT", RESOURCES);
    public static final String GIOI_DEFAULT = Utils.getResource("GIOI_DEFAULT", RESOURCES);
    public static final String DAN_TOC_DEFAULT = Utils.getResource("DAN_TOC_DEFAULT", RESOURCES);
    public static final String NGHE_NGHIEP_DEFAULT = Utils.getResource("NGHE_NGHIEP_DEFAULT", RESOURCES);
    public static final String QUOC_GIA_DEFAULT = Utils.getResource("QUOC_GIA_DEFAULT", RESOURCES);
    public static final String TINH_BHYT_DEFAULT = Utils.getResource("TINH_BHYT_DEFAULT", RESOURCES);
    public static final String PHONGKHAMCC_DEFAULT = Utils.getResource("PHONG_KHAM_CAP_CUU_DEFAULT", RESOURCES);
    public static final String MA_BAN_KHAM_CAP_CUU_1 = Utils.getResource("MA_BAN_KHAM_CAP_CUU_1", RESOURCES);
    public static final String MA_BAN_KHAM_CAP_CUU_2 = Utils.getResource("MA_BAN_KHAM_CAP_CUU_2", RESOURCES);
    public static final String DON_VI_TUOI_DEFAULT = Utils.getResource("DON_VI_TUOI_DEFAULT", RESOURCES);
    
   // Dieu tri  
    public static final String SUCCESS=Utils.getResource("common_success", UIRESOURCES);
    public static final String FAIL=Utils.getResource("common_fail", UIRESOURCES);
    public static final String NOT_FOUND =Utils.getResource("dieutri_notfound", UIRESOURCES);
    public static final String SAVE_LABEL_BUTTON = Utils.getResource("SAVE_LABEL_BUTTON", RESOURCES);
    public static final String UPDATE_LABEL_BUTTON = Utils.getResource("UPDATE_LABEL_BUTTON", RESOURCES);
    public static final String KHOA_HSBA_NULL = Utils.getResource("KHOA_HSBA_NULL", UIRESOURCES);
    public static final String KHOA_OR_HSBA_NULL = Utils.getResource("KHOA_OR_HSBA_NULL", UIRESOURCES);
    public static final String HSBA_NULL = Utils.getResource("HSBA_NULL", UIRESOURCES);
    public static final String HSBA_NOT_EDIT = Utils.getResource("HSBA_NOT_EDIT", UIRESOURCES);
    
    public static final String KC_MASO =   Utils.getResource("KC_MASO", RESOURCES);
    
    // Dieu tri tiepdon
    public static final String MATIEPDON_NOTFOUND =Utils.getResource("MATIEPDON_NULL", UIRESOURCES);
    public static final String TIEP_DON_UNAVAILABLE =Utils.getResource("TIEP_DON_UNAVAILABLE", UIRESOURCES);
    
    public static final String CANT_CANCEL =Utils.getResource("CANT_CANCEL", UIRESOURCES);
    public static final String NOT_FOUND_TIEPDON =Utils.getResource("NOT_FOUND", UIRESOURCES);
    public static final String NOT_HAVE_ROLE =Utils.getResource("NOT_HAVE_ROLE", UIRESOURCES);
    //Du phong
    public static final String INSERT_SUCCESS=Utils.getResource("common_success_insert", UIRESOURCES);
    public static final String INSERT_FAIL=Utils.getResource("common_fail_insert", UIRESOURCES);
    public static final String UPDATE_SUCCESS=Utils.getResource("common_success_update", UIRESOURCES);
    public static final String UPDATE_FAIL=Utils.getResource("common_fail_update", UIRESOURCES);
    
    public static final String SAVE_BUTTON = Utils.getResource("save_button", UIRESOURCES);
    public static final String UPDATE_BUTTON = Utils.getResource("update_button", UIRESOURCES);
    
    //bao cao du phong
    //public static final String BCCSSKSS_HUYEN_TEMPLATE_PATH = Utils.getResource("BCCSSKSS_HUYEN_TEMPLATE_PATH", RESOURCES);
  
    // Use in form Tham Kham Xu Tri -> ke toa quay
    // In order to get Id of DMKHOA of BanKham
    public static final String KHO_BHYT_MA = Utils.getResource("KHO_BHYT_MA", RESOURCES);
    public static final String KHO_TRE_EM_MA = Utils.getResource("KHO_TRE_EM_MA", RESOURCES);
    public static final String KHO_CHINH_MA = Utils.getResource("KHO_CHINH_MA", RESOURCES);
    public static final String KHO_NOITRU_MA = Utils.getResource("KHO_NOITRU_MA", RESOURCES);
    public static final String XLBK_SOLUONGKHONGDU = Utils.getResource("XLBK_SOLUONGKHONGDU", UIRESOURCES);  
    public static final String XLBK_NOTEDIT = Utils.getResource("XLBK_NOTEDIT", UIRESOURCES);
    public static final String XLBK_HETTONTHUOC = Utils.getResource("XLBK_HETTONTHUOC", UIRESOURCES);
    //------------------------------
    
    
    public static final String TIEPDON_NOTFOUND = Utils.getResource("khongtimthay_matiepdon", UIRESOURCES);
    public static final String THUOC_EXIST = Utils.getResource("danhmucthuoc_tontai", UIRESOURCES);
    public static final String TK_TON_NOTENOUGH = Utils.getResource("soluongtonkho_khongdu", UIRESOURCES);
    public static final String SOBENHAN_NOTFOUND = Utils.getResource("sobenhan_notfound", UIRESOURCES);    
    
    public static final String COMMON_HAS_NO_ROLE = Utils.getResource("common_has_no_role", UIRESOURCES);
    
    public static final String THEM_MOI_THANH_CONG_VOI_SO_VAO_VIEN =  Utils.getResource("common_success_insert_with_sovaovien", UIRESOURCES);
    public static final String CAP_NHAT_THANH_CONG_VOI_SO_VAO_VIEN = Utils.getResource("common_success_update_with_sovaovien", UIRESOURCES);
    public static final String GHI_NHAN_THANH_CONG_VOI_SO_VAO_VIEN = Utils.getResource("common_success_with_sovaovien", UIRESOURCES);
    
    public static final String HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA = Utils.getResource("hosodathanhtoan", UIRESOURCES);
    public static final String DA_CHUYEN_VAO_NOI_TRU = Utils.getResource("DA_CHUYEN_VAO_NOI_TRU", UIRESOURCES);
    
    public static final String BANKHAM_NOTFOUND =   Utils.getResource("bankham_notfound", UIRESOURCES);
    public static final String INVALID_LOGIN =   Utils.getResource("invalid_login", UIRESOURCES);
    public static final String NOT_FOUND_ID =   Utils.getResource("NOT_FOUND_ID", UIRESOURCES);
    
    public static final String KE_TOA_TAI_BAN_KHAM =   Utils.getResource("xutrithuoctaibankham", UIRESOURCES);
    public static final String KE_TOA_QUAY_BENH_VIEN =   Utils.getResource("ketoaquaybenhvien", UIRESOURCES);
    public static final String KE_TOA_VE =   Utils.getResource("ketoave", UIRESOURCES);
    public static final String HOSODATHTOAN = Utils.getResource("HOSODATHTOAN", UIRESOURCES);
    
    
    public static final String CHON_IT_NHAT_MOT_CLS_DE_HOAN_TRA = Utils.getResource("chon_it_nhat_mot_cls_de_hoan_tra", UIRESOURCES);
    public static final String KHONG_TIM_THAY_PHIEU = Utils.getResource("phieu_khong_tim_thay", UIRESOURCES);
    
    // Tom tat benh an
    public static final String GTTHSBA_NULL = Utils.getResource("GTTHSBA_NULL", UIRESOURCES);
    
    
    public static final String MA_BIEN_BAN = Utils.getResource("Ma_bien_ban", UIRESOURCES);
    public static final String NOT_FOUND_INFO = Utils.getResource("NOT_FOUND_INFO", UIRESOURCES);
    public static final String SO_BENH_AN = Utils.getResource("So_benh_an", UIRESOURCES);
    public static final String PHIEU_SO = Utils.getResource("Phieu_so", UIRESOURCES);
    public static final String KHOA_PHONG = Utils.getResource("Khoa_phong", UIRESOURCES);
    public static final String MA_TIEP_DON = Utils.getResource("Ma_tiep_don", UIRESOURCES);
    
    public static final String NAM = Utils.getResource("namw", UIRESOURCES);
    public static final String THANG = Utils.getResource("thangs", UIRESOURCES);
    public static final String NGAY = Utils.getResource("ngayf", UIRESOURCES);
    
	public static final String REGISTER_ERROR_PASSCONFIRM = Utils.getResource("REGISTER_ERROR_PASSCONFIRM", UIRESOURCES);
	public static final String REGISTER_DUPLICATE_USERNAME = Utils.getResource("REGISTER_DUPLICATE_USERNAME", UIRESOURCES);
	public static final String REGISTER_SUCCESS = Utils.getResource("REGISTER_SUCCESS", UIRESOURCES);
	public static final String CHANGE_PASSWORD_SUCCESS = Utils.getResource("CHANGE_PASSWORD_SUCCESS", UIRESOURCES);
	public static final String CHANGE_PASSWORD_FAIL = Utils.getResource("CHANGE_PASSWORD_FAIL", UIRESOURCES);
	
	public static final String NHAN_VIEN_KHONG_TIM_THAY = Utils.getResource("nhanvien_khongtontai", UIRESOURCES);

	public static final String CAP_QUYEN_THANH_CONG = Utils.getResource("cap_quyen_thanh_cong", UIRESOURCES);
	public static final String CAP_QUYEN_THAT_BAI = Utils.getResource("cap_quyen_that_bai", UIRESOURCES);
	
	public static final String ND_TONTAI = Utils.getResource("ND_TONTAI", UIRESOURCES);
	
	public static final String BENH_NHAN = Utils.getResource("benh_nhan", UIRESOURCES);
	public static final String BAN_KHAM = Utils.getResource("ban_kham", UIRESOURCES);

	public static final String PHIEU_DA_HOAN_THU = Utils.getResource("phieu_da_hoan_thu", UIRESOURCES);
	
	public static final String HUY_PHIEU_THANH_CONG = Utils.getResource("HuyPhieuThanhCong", UIRESOURCES);
	public static final String HUY_PHIEU_THAT_BAI = Utils.getResource("HuyPhieuThatBai", UIRESOURCES);

	public static final String THUOCYDUNGCU_THANHCONG = Utils.getResource("THUOCYDUNGCU_THANHCONG", UIRESOURCES);
	public static final String THUOCYDUNGCU_THATBAI = Utils.getResource("THUOCYDUNGCU_THATBAI", UIRESOURCES);
	
	public static final String BENHNHANTRATHUOC_PDT_NULL = Utils.getResource("BENHNHANTRATHUOC_PDT_NULL", UIRESOURCES);
	public static final String BENHNHANTRATHUOC_PDT_XUAT_NULL = Utils.getResource("BENHNHANTRATHUOC_PDT_XUAT_NULL", UIRESOURCES);
	public static final String CHINH_SUA_NGAY_HE_THONG = Utils.getResource("Chinh_Ngay_Gio_He_Thong", UIRESOURCES);
	public static final String THUOC_KHOA_HSBA_NULL = Utils.getResource("THUOC_KHOA_HSBA_NULL", UIRESOURCES);
	
	public static final String REPORT_NAM = Utils.getResource("REPORT_NAM", UIRESOURCES);
	public static final String REPORT_THANG = Utils.getResource("REPORT_THANG", UIRESOURCES);
	public static final String REPORT_NGAY = Utils.getResource("REPORT_NGAY", UIRESOURCES);
	
	public static final String In_Tach_Rieng = Utils.getResource("In_Tach_Rieng", UIRESOURCES);
	public static final String In_Gop_Ma_NCT_NSX = Utils.getResource("In_Gop_Ma_NCT_NSX", UIRESOURCES);
	
	public static final String THU_NGAN_MA = Utils.getResource("THU_NGAN_MA", RESOURCES);
	public static final String CUM_TIEP_DON = Utils.getResource("CUM_TIEP_DON", RESOURCES);
	
	public static final String GIOI_TINH_NAM = Utils.getResource("GIOI_TINH_NAM", UIRESOURCES);
	public static final String GIOI_TINH_NU = Utils.getResource("GIOI_TINH_NU", UIRESOURCES);
	
	
	public static final String khong = Utils.getResource("khong", UIRESOURCES);
	public static final String mot = Utils.getResource("mot", UIRESOURCES);
	public static final String hai = Utils.getResource("hai", UIRESOURCES);
	public static final String ba = Utils.getResource("ba", UIRESOURCES);
	public static final String bon = Utils.getResource("bon", UIRESOURCES);
	public static final String nam = Utils.getResource("nam", UIRESOURCES);
	public static final String sau = Utils.getResource("sau", UIRESOURCES);
	public static final String bay = Utils.getResource("bay", UIRESOURCES);
	public static final String tam = Utils.getResource("tam", UIRESOURCES);
	public static final String chin = Utils.getResource("chin", UIRESOURCES);
	public static final String muoi = Utils.getResource("muoi", UIRESOURCES);
	public static final String muoi_10 = Utils.getResource("muoi_10", UIRESOURCES);
	public static final String tram = Utils.getResource("tram", UIRESOURCES);
	public static final String ngan = Utils.getResource("ngan", UIRESOURCES);
	public static final String trieu = Utils.getResource("trieu", UIRESOURCES);
	public static final String ty = Utils.getResource("ty", UIRESOURCES);
	public static final String nganty = Utils.getResource("nganty", UIRESOURCES);
	
	public static final String le = Utils.getResource("le", UIRESOURCES);
	public static final String khong_tam = Utils.getResource("khong_tam", UIRESOURCES);
	public static final String lam = Utils.getResource("lam", UIRESOURCES);
	public static final String dongVN = Utils.getResource("dongVN", UIRESOURCES);
	
	public static final String HANG_DA_CHON = Utils.getResource("HANG_DA_CHON", UIRESOURCES);
	
	public static final String CHECK_DATE_TIME = Utils.getResource("CHECK_DATE_TIME", RESOURCES);	
	
	public static final String MENU_CAP_CUU = Utils.getResource("MENU_CAP_CUU", RESOURCES);
	
	public static final String NOI_TINH = Utils.getResource("NOI_TINH", RESOURCES);	
	
	//----DanhMuc--Dung---//
	public static final String no_found = Utils.getResource("no_found", UIRESOURCES);

	public static final String nhacungcap_de_su = Utils.getResource("nhacungcap_de_su", UIRESOURCES);
	public static final String nhacungcap_de_fa = Utils.getResource("nhacungcap_de_fa", UIRESOURCES);
	public static final String nhacungcap_de_co = Utils.getResource("nhacungcap_de_co", UIRESOURCES);
	
	public static final String nhacungcap_cr_su = Utils.getResource("nhacungcap_cr_su", UIRESOURCES);
	public static final String nhacungcap_cr_fa = Utils.getResource("nhacungcap_cr_fa", UIRESOURCES);
	
	public static final String nhacungcap_up_su = Utils.getResource("nhacungcap_up_su", UIRESOURCES);
	public static final String nhacungcap_up_fa = Utils.getResource("nhacungcap_up_fa", UIRESOURCES);
	
	public static final String nhasanxuat_de_su = Utils.getResource("nhasanxuat_de_su", UIRESOURCES);
	public static final String nhasanxuat_de_fa = Utils.getResource("nhasanxuat_de_fa", UIRESOURCES);
	public static final String nhasanxuat_de_co = Utils.getResource("nhasanxuat_de_co", UIRESOURCES);
	
	public static final String nhasanxuat_cr_su = Utils.getResource("nhasanxuat_cr_su", UIRESOURCES);
	public static final String nhasanxuat_cr_fa = Utils.getResource("nhasanxuat_cr_fa", UIRESOURCES);
	
	public static final String nhasanxuat_up_su = Utils.getResource("nhasanxuat_up_su", UIRESOURCES);
	public static final String nhasanxuat_up_fa = Utils.getResource("nhasanxuat_up_fa", UIRESOURCES);
	
	public static final String baithuoc_de_su = Utils.getResource("baithuoc_de_su", UIRESOURCES);
	public static final String baithuoc_de_fa = Utils.getResource("baithuoc_de_fa", UIRESOURCES);
	public static final String baithuoc_de_co = Utils.getResource("baithuoc_de_co", UIRESOURCES);
	
	public static final String baithuoc_cr_su = Utils.getResource("baithuoc_cr_su", UIRESOURCES);
	public static final String baithuoc_cr_fa = Utils.getResource("baithuoc_cr_fa", UIRESOURCES);
	
	public static final String baithuoc_up_su = Utils.getResource("baithuoc_up_su", UIRESOURCES);
	public static final String baithuoc_up_fa = Utils.getResource("baithuoc_up_fa", UIRESOURCES);
	
	//==============================================
	
	public static final String dtdmclskq_de_su = Utils.getResource("dtdmclskq_de_su", UIRESOURCES);
	public static final String dtdmclskq_de_fa = Utils.getResource("dtdmclskq_de_fa", UIRESOURCES);
	public static final String dtdmclskq_de_co = Utils.getResource("dtdmclskq_de_co", UIRESOURCES);
	
	public static final String dtdmclskq_cr_su = Utils.getResource("dtdmclskq_cr_su", UIRESOURCES);
	public static final String dtdmclskq_cr_fa = Utils.getResource("dtdmclskq_cr_fa", UIRESOURCES);
	
	public static final String dtdmclskq_up_su = Utils.getResource("dtdmclskq_up_su", UIRESOURCES);
	public static final String dtdmclskq_up_fa = Utils.getResource("dtdmclskq_up_fa", UIRESOURCES);

	public static final String clsbanggia_de_su = Utils.getResource("clsbanggia_de_su", UIRESOURCES);
	public static final String clsbanggia_de_fa = Utils.getResource("clsbanggia_de_fa", UIRESOURCES);
	public static final String clsbanggia_de_co = Utils.getResource("clsbanggia_de_co", UIRESOURCES);
	
	public static final String clsbanggia_cr_su = Utils.getResource("clsbanggia_cr_su", UIRESOURCES);
	public static final String clsbanggia_cr_fa = Utils.getResource("clsbanggia_cr_fa", UIRESOURCES);
	
	public static final String clsbanggia_up_su = Utils.getResource("clsbanggia_up_su", UIRESOURCES);
	public static final String clsbanggia_up_fa = Utils.getResource("clsbanggia_up_fa", UIRESOURCES);
	
	//----DanhMuc--Lam---//
	public static final String thuoc_de_su = Utils.getResource("thuoc_de_su", UIRESOURCES);
	public static final String thuoc_de_fa = Utils.getResource("thuoc_de_fa", UIRESOURCES);
	public static final String thuoc_de_co = Utils.getResource("thuoc_de_co", UIRESOURCES);
	public static final String thuoc_de_war = Utils.getResource("thuoc_de_war", UIRESOURCES);
	
	public static final String thuoc_cr_su = Utils.getResource("thuoc_cr_su", UIRESOURCES);
	public static final String thuoc_cr_fa = Utils.getResource("thuoc_cr_fa", UIRESOURCES);
	
	public static final String thuoc_up_su = Utils.getResource("thuoc_up_su", UIRESOURCES);
	public static final String thuoc_up_fa = Utils.getResource("thuoc_up_fa", UIRESOURCES);
	
	public static final String plthuoc_cr_su = Utils.getResource("plthuoc_cr_su", UIRESOURCES);
	public static final String plthuoc_cr_fa = Utils.getResource("plthuoc_cr_fa", UIRESOURCES);
	public static final String plthuoc_cr_ma_trung = Utils.getResource("plthuoc_cr_ma_trung", UIRESOURCES);
	
	public static final String plthuoc_up_su = Utils.getResource("plthuoc_up_su", UIRESOURCES);
	public static final String plthuoc_up_fa = Utils.getResource("plthuoc_up_fa", UIRESOURCES);
	
	public static final String plthuoc_de_co = Utils.getResource("plthuoc_de_co", UIRESOURCES);
	public static final String plthuoc_de_fa = Utils.getResource("plthuoc_de_fa", UIRESOURCES);
	public static final String plthuoc_de_su = Utils.getResource("plthuoc_de_su", UIRESOURCES);
	
	public static final String nghenghiep_de_su = Utils.getResource("nghenghiep_de_su", UIRESOURCES);
	public static final String nghenghiep_de_fa = Utils.getResource("nghenghiep_de_fa", UIRESOURCES);
	public static final String nghenghiep_de_co = Utils.getResource("nghenghiep_de_co", UIRESOURCES);
	
	public static final String nghenghiep_cr_su = Utils.getResource("nghenghiep_cr_su", UIRESOURCES);
	public static final String nghenghiep_cr_fa = Utils.getResource("nghenghiep_cr_fa", UIRESOURCES);
	
	public static final String nghenghiep_up_su = Utils.getResource("nghenghiep_up_su", UIRESOURCES);
	public static final String nghenghiep_up_fa = Utils.getResource("nghenghiep_up_fa", UIRESOURCES);
	
	//==============================================
    
    public static final String nhanvien_de_su = Utils.getResource("nhanvien_de_su", UIRESOURCES);
    public static final String nhanvien_de_fa = Utils.getResource("nhanvien_de_fa", UIRESOURCES);
    public static final String nhanvien_de_co = Utils.getResource("nhanvien_de_co", UIRESOURCES);
    
    public static final String nhanvien_cr_su = Utils.getResource("nhanvien_cr_su", UIRESOURCES);
    public static final String nhanvien_cr_fa = Utils.getResource("nhanvien_cr_fa", UIRESOURCES);
    
    public static final String nhanvien_up_su = Utils.getResource("nhanvien_up_su", UIRESOURCES);
    public static final String nhanvien_up_fa = Utils.getResource("nhanvien_up_fa", UIRESOURCES);
    
    //==============================================
    
    public static final String canlamsan_de_su = Utils.getResource("canlamsan_de_su", UIRESOURCES);
    public static final String canlamsan_de_fa = Utils.getResource("canlamsan_de_fa", UIRESOURCES);
    public static final String canlamsan_de_co = Utils.getResource("canlamsan_de_co", UIRESOURCES);
    
    public static final String canlamsan_cr_su = Utils.getResource("canlamsan_cr_su", UIRESOURCES);
    public static final String canlamsan_cr_fa = Utils.getResource("canlamsan_cr_fa", UIRESOURCES);
    
    public static final String canlamsan_up_su = Utils.getResource("canlamsan_up_su", UIRESOURCES);
    public static final String canlamsan_up_fa = Utils.getResource("canlamsan_up_fa", UIRESOURCES);
    
    //==============================================
    
    public static final String donvitinh_de_su = Utils.getResource("donvitinh_de_su", UIRESOURCES);
    public static final String donvitinh_de_fa = Utils.getResource("donvitinh_de_fa", UIRESOURCES);
    public static final String donvitinh_de_co = Utils.getResource("donvitinh_de_co", UIRESOURCES);
    
    public static final String donvitinh_cr_su = Utils.getResource("donvitinh_cr_su", UIRESOURCES);
    public static final String donvitinh_cr_fa = Utils.getResource("donvitinh_cr_fa", UIRESOURCES);
    
    public static final String donvitinh_up_su = Utils.getResource("donvitinh_up_su", UIRESOURCES);
    public static final String donvitinh_up_fa = Utils.getResource("donvitinh_up_fa", UIRESOURCES);
    
    //==============================================
    
    public static final String vocam_de_su = Utils.getResource("vocam_de_su", UIRESOURCES);
    public static final String vocam_de_fa = Utils.getResource("donvitinh_de_fa", UIRESOURCES);
    public static final String vocam_de_co = Utils.getResource("vocam_de_co", UIRESOURCES);
    
    public static final String vocam_cr_su = Utils.getResource("vocam_cr_su", UIRESOURCES);
    public static final String vocam_cr_fa = Utils.getResource("vocam_cr_fa", UIRESOURCES);
    
    public static final String vocam_up_su = Utils.getResource("vocam_up_su", UIRESOURCES);
    public static final String vocam_up_fa = Utils.getResource("vocam_up_fa", UIRESOURCES);
    
    //==============================================
    
    public static final String khoaphong_de_su = Utils.getResource("khoaphong_de_su", UIRESOURCES);
    public static final String khoaphong_de_fa = Utils.getResource("khoaphong_de_fa", UIRESOURCES);
    public static final String khoaphong_de_co = Utils.getResource("khoaphong_de_co", UIRESOURCES);
    
    public static final String khoaphong_cr_su = Utils.getResource("khoaphong_cr_su", UIRESOURCES);
    public static final String khoaphong_cr_fa = Utils.getResource("khoaphong_cr_fa", UIRESOURCES);
    
    public static final String khoaphong_up_su = Utils.getResource("khoaphong_up_su", UIRESOURCES);
    public static final String khoaphong_up_fa = Utils.getResource("khoaphong_up_fa", UIRESOURCES);
    
    //==============================================
    
    public static final String tainan_de_su = Utils.getResource("tainan_de_su", UIRESOURCES);
    public static final String tainan_de_fa = Utils.getResource("tainan_de_fa", UIRESOURCES);
    public static final String tainan_de_co = Utils.getResource("tainan_de_co", UIRESOURCES);
    
    public static final String tainan_cr_su = Utils.getResource("tainan_cr_su", UIRESOURCES);
    public static final String tainan_cr_fa = Utils.getResource("tainan_cr_fa", UIRESOURCES);
    
    public static final String tainan_up_su = Utils.getResource("tainan_up_su", UIRESOURCES);
    public static final String tainan_up_fa = Utils.getResource("tainan_up_fa", UIRESOURCES);
    
    //==============================================
    
    public static final String phanloaitainan_de_su = Utils.getResource("phanloaitainan_de_su", UIRESOURCES);
    public static final String phanloaitainan_de_fa = Utils.getResource("phanloaitainan_de_fa", UIRESOURCES);
    public static final String phanloaitainan_de_co = Utils.getResource("phanloaitainan_de_co", UIRESOURCES);
    
    public static final String phanloaitainan_cr_su = Utils.getResource("phanloaitainan_cr_su", UIRESOURCES);
    public static final String phanloaitainan_cr_fa = Utils.getResource("phanloaitainan_cr_fa", UIRESOURCES);
    
    public static final String phanloaitainan_up_su = Utils.getResource("phanloaitainan_up_su", UIRESOURCES);
    public static final String phanloaitainan_up_fa = Utils.getResource("phanloaitainan_up_fa", UIRESOURCES);
    
    //==============================================
    
    public static final String phuongthucgaytainan_de_su = Utils.getResource("phuongthucgaytainan_de_su", UIRESOURCES);
    public static final String phuongthucgaytainan_de_fa = Utils.getResource("phuongthucgaytainan_de_fa", UIRESOURCES);
    public static final String phuongthucgaytainan_de_co = Utils.getResource("phuongthucgaytainan_de_co", UIRESOURCES);
    
    public static final String phuongthucgaytainan_cr_su = Utils.getResource("phuongthucgaytainan_cr_su", UIRESOURCES);
    public static final String phuongthucgaytainan_cr_fa = Utils.getResource("phuongthucgaytainan_cr_fa", UIRESOURCES);
    
    public static final String phuongthucgaytainan_up_su = Utils.getResource("phuongthucgaytainan_up_su", UIRESOURCES);
    public static final String phuongthucgaytainan_up_fa = Utils.getResource("phuongthucgaytainan_up_fa", UIRESOURCES);
    	
	
	public static final String benhvien_de_su = Utils.getResource("benhvien_de_su", UIRESOURCES);
	public static final String benhvien_de_fa = Utils.getResource("benhvien_de_fa", UIRESOURCES);
	public static final String benhvien_de_co = Utils.getResource("benhvien_de_co", UIRESOURCES);
	
	public static final String benhvien_cr_su = Utils.getResource("benhvien_cr_su", UIRESOURCES);
	public static final String benhvien_cr_fa = Utils.getResource("benhvien_cr_fa", UIRESOURCES);
	
	public static final String benhvien_up_su = Utils.getResource("benhvien_up_su", UIRESOURCES);
	public static final String benhvien_up_fa = Utils.getResource("benhvien_up_fa", UIRESOURCES);
	
	public static final String mabenh_de_su = Utils.getResource("mabenh_de_su", UIRESOURCES);
	public static final String mabenh_de_fa = Utils.getResource("mabenh_de_fa", UIRESOURCES);
	public static final String mabenh_de_co = Utils.getResource("mabenh_de_co", UIRESOURCES);
	
	public static final String mabenh_cr_su = Utils.getResource("mabenh_cr_su", UIRESOURCES);
	public static final String mabenh_cr_fa = Utils.getResource("mabenh_cr_fa", UIRESOURCES);
	
	public static final String mabenh_up_su = Utils.getResource("mabenh_up_su", UIRESOURCES);
	public static final String mabenh_up_fa = Utils.getResource("mabenh_up_fa", UIRESOURCES);
	
//==============================================
    
    public static final String buong_de_su = Utils.getResource("buong_de_su", UIRESOURCES);
    public static final String buong_de_fa = Utils.getResource("buong_de_fa", UIRESOURCES);
    public static final String buong_de_co = Utils.getResource("buong_de_co", UIRESOURCES);
    
    public static final String buong_cr_su = Utils.getResource("buong_cr_su", UIRESOURCES);
    public static final String buong_cr_fa = Utils.getResource("buong_cr_fa", UIRESOURCES);
    
    public static final String buong_up_su = Utils.getResource("buong_up_su", UIRESOURCES);
    public static final String buong_up_fa = Utils.getResource("buong_up_fa", UIRESOURCES);
    
    //==============================================

	public static final String GIAYXACNHANRAVIEN_REQUIRED = Utils.getResource("GIAYXACNHANRAVIEN_REQUIRED", UIRESOURCES);
	
	public static final String benh_nhan_phai_thu_vien_phi = Utils.getResource("benh_nhan_phai_thu_vien_phi", UIRESOURCES);
	public static final String benh_nhan_phai_thuoc_doi_tuong_tre_em_hoac_bhyt = Utils.getResource("benh_nhan_phai_thuoc_doi_tuong_tre_em_hoac_bhyt", UIRESOURCES);

	public static final String so_luu_tru_hsba = Utils.getResource("so_luu_tru_hsba", UIRESOURCES);
	public static final String so_luu_tru_hsba_tu_vong = Utils.getResource("so_luu_tru_hsba_tu_vong", UIRESOURCES);
	
	 public static final String HSBA_NOP_NULL = Utils.getResource("HSBA_NOP_NULL", UIRESOURCES);
	 public static final String HSBA_NOP_SO_VAO_VIEN_NULL = Utils.getResource("HSBA_NOP_SO_VAO_VIEN_NULL", UIRESOURCES);
	 public static final String TRAM_Y_TE_CHON_THANG_NAM_TRAM_Y_TE = Utils.getResource("TRAM_Y_TE_CHON_THANG_NAM_TRAM_Y_TE", UIRESOURCES);
	 
	 public static final String MA_KO_TRUNG_NHAU = Utils.getResource("MA_KO_TRUNG_NHAU", UIRESOURCES);
	 
	 
	 // Phieu bao an 
	 public static final String GIO_AN_TON_TAI = Utils.getResource("PBA_TON_TAI_TRONG_LUOI", UIRESOURCES);
	 public static final String PBA_EMPTY = Utils.getResource("PBA_EMPTY", UIRESOURCES);
	 public static final String PBA_INCORRECT = Utils.getResource("PBA_INCORRECT", UIRESOURCES);
	 public static final String PBA_INSERT_SUCCESS = Utils.getResource("PBA_INSERT_SUCCESS", UIRESOURCES);
	 public static final String PBA_EXISTS = Utils.getResource("PBA_EXISTS", UIRESOURCES);
	 public static final String DU_TRU_TP_EXISTS = Utils.getResource("DU_TRU_TP_EXISTS", UIRESOURCES);
	 
	 // CAU HINH THIET BI QUANG BAO, SO THU TU
	  public static final String THIET_BI_QUANG_BAO = Utils.getResource("THIET_BI_QUANG_BAO", UIRESOURCES);
	  public static final String SO_THU_TU = Utils.getResource("SO_THU_TU", UIRESOURCES);
	  
	  
	// Phieu giao ban
	 public static final String PGB_TPTD_EMPTY = Utils.getResource("PGB_TPTD_EMPTY", UIRESOURCES);
	 public static final String PGB_EMPTY_PBA = Utils.getResource("PGB_EMPTY_PBA", UIRESOURCES);
	 public static final String PGB_INCORRECT = Utils.getResource("PGB_INCORRECT", UIRESOURCES);
	 public static final String PGB_NVTD_EXIST = Utils.getResource("PGB_NVTD_EXIST", UIRESOURCES);
	 public static final String PGB_NVVM_EXIST = Utils.getResource("PGB_NVVM_EXIST", UIRESOURCES);
	 public static final String PGB_EXIST_IN_VM = Utils.getResource("PGB_EXIST_IN_VM", UIRESOURCES);
	 public static final String PGB_EXIST_IN_TD = Utils.getResource("PGB_EXIST_IN_TD", UIRESOURCES);
	 public static final String PGB_DELETE_SUCCESS = Utils.getResource("PGB_DELETE_SUCCESS", UIRESOURCES);
	 public static final String PGB_INSERT_SUCCESS = Utils.getResource("PGB_INSERT_SUCCESS", UIRESOURCES);
	 public static final String MA_DA_TON_TAI = Utils.getResource("MA_DA_TON_TAI", UIRESOURCES);
	 public static final String SAN_PHAM_KHONG_CO = Utils.getResource("SAN_PHAM_KHONG_CO", UIRESOURCES);
	 public static final String SL_XUAT_KHONG_HOP_LE = Utils.getResource("SL_XUAT_KHONG_HOP_LE", UIRESOURCES);
	
	 public static final String VI_TRI_THU_MUC = Utils.getResource("VI_TRI_THU_MUC", UIRESOURCES);
	 

	 public static final String ghi_nhan_truoc_ghi_nhap_benh_an_chi_tiet = Utils.getResource("ghi_nhan_truoc_ghi_nhap_benh_an_chi_tiet", UIRESOURCES);

	 //Giay chuyen vien nguoi benh co the BHYT
	 public static final String RPGCV_NOT_EXIST = Utils.getResource("RPGCV_NOT_EXIST", UIRESOURCES);
	 public static final String RPGCV_INSERT_SUCCESS = Utils.getResource("RPGCV_INSERT_SUCCESS", UIRESOURCES);
	 public static final String RPGCV_DELETE_SUCCESS = Utils.getResource("RPGCV_DELETE_SUCCESS", UIRESOURCES);

	 
	 //Report Phieu Kham Va Dieu Tri Ngoai Tru
	 public static final String RPPKDTNT_NOT_EXIST = Utils.getResource("RPPKDTNT_NOT_EXIST", UIRESOURCES);
	 public static final String RPPKDTNT_INSERT_SUCCESS = Utils.getResource("RPPKDTNT_INSERT_SUCCESS", UIRESOURCES);
	 public static final String RPPKDTNT_DELETE_SUCCESS = Utils.getResource("RPPKDTNT_DELETE_SUCCESS", UIRESOURCES);
	 
	//Report Phieu Kham Chuyen Khoa
	 public static final String RPPKCK_NOT_EXIST = Utils.getResource("RPPKCK_NOT_EXIST", UIRESOURCES);
	 public static final String RPPKCK_DELETE_SUCCESS = Utils.getResource("RPPKCK_DELETE_SUCCESS", UIRESOURCES);
	 public static final String RPPKCK_INSERT_SUCCESS = Utils.getResource("RPPKCK_INSERT_SUCCESS", UIRESOURCES);
	 
	//Report Phieu Kham Chuyen Khoa
	 public static final String RPPKBVV_NOT_EXIST = Utils.getResource("RPPKBVV_NOT_EXIST", UIRESOURCES);
	 public static final String RPPKBVV_DELETE_SUCCESS = Utils.getResource("RPPKBVV_DELETE_SUCCESS", UIRESOURCES);
	 public static final String RPPKBVV_INSERT_SUCCESS = Utils.getResource("RPPKBVV_INSERT_SUCCESS", UIRESOURCES);
	 
	 public static final String REPORT_DIEUTRI_UBNDTINH = Utils.getResource("REPORT_DIEUTRI_UBNDTINH", RESOURCES);
	 public static final String CHANGEPASS_SUCCESS = Utils.getResource("CHANGEPASS_SUCCESS", UIRESOURCES);

	 // Noi dung thu BHYT va Can Lam Sang
	 public static final String NOI_DUNG_THU_CLS = Utils.getResource("NOI_DUNG_THU_CLS", UIRESOURCES);
	 public static final String NOI_DUNG_THU_BHYT = Utils.getResource("NOI_DUNG_THU_BHYT", UIRESOURCES);
	 
	 // THu tam ung phong cap cuu
	 public static final String LI_DO_NOP_THU_TAM_UNG_PCC = Utils.getResource("LI_DO_NOP_THU_TAM_UNG_PCC", UIRESOURCES);
	 
	 public static final String TEN_KO_TRUNG_NHAU=Utils.getResource("TEN_KO_TRUNG_NHAU", UIRESOURCES);
	 
	 public static final String TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB=Utils.getResource("TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB", UIRESOURCES);
	 public static final String TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_HSBA=Utils.getResource("TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_HSBA", UIRESOURCES);
	 public static final String HUY_KHAM_THANH_CONG=Utils.getResource("huykham_thanhcong", UIRESOURCES);
	 
	 // Cac thong bao khac
	 public static final String DANH_SACH_KHOI_BHYT_KHAC_TUYEN_VUOT_TUYEN_KO_CAN_GIAY_CHUYEN_VIEN=Utils.getResource("DANH_SACH_KHOI_BHYT_KHAC_TUYEN_VUOT_TUYEN_KO_CAN_GIAY_CHUYEN_VIEN", RESOURCES);

	 public static final String VIEN_PHI=Utils.getResource("VIEN_PHI", UIRESOURCES);

	 public static final String TUOI_KHONG_XET_TUYEN=Utils.getResource("TUOI_KHONG_XET_TUYEN", RESOURCES);

	 public static final String CHIEU_DAI_PHAN_SO_MA_THUOC=Utils.getResource("CHIEU_DAI_PHAN_SO_MA_THUOC", RESOURCES);

	 public static final String THOI_GIAN_LAP_HE_THONG_DONG_HO=Utils.getResource("THOI_GIAN_LAP_HE_THONG_DONG_HO", RESOURCES);

	 public static final String KHONGPHAI_HXL_DTNT=Utils.getResource("khongphai_hxl_dtnt", UIRESOURCES);
	 
	 public static final String CHUACHON_HXL=Utils.getResource("chuachon_hxl", UIRESOURCES);
	 
	 public static final String DUPLICATE_HOADON = Utils.getResource("DUPLICATE_HOADON", UIRESOURCES);	 
	 
	 public static final String KHONG_PHAI_THUPHI =Utils.getResource("KHONG_PHAI_THUPHI", UIRESOURCES);
	 
	 public static final String DOITUONG_THUPHI =Utils.getResource("DOITUONG_THUPHI", UIRESOURCES);
	 
	 public static final String CHUA_CHON_CLS =Utils.getResource("CHUA_CHON_CLS", UIRESOURCES);

	 public static final String DOITUONG_THUPHI_CNBHYT =Utils.getResource("DOITUONG_THUPHI_CNBHYT", UIRESOURCES);

	 public static final String THONGTIN_CHANDOAN =Utils.getResource("THONGTIN_CHANDOAN", UIRESOURCES);
	 
	 public static final String SOTHEBHYT_DATONTAI =Utils.getResource("sothebhyt_datontai", UIRESOURCES);
	 
	 public static final String KHONG_IN_BIEN_LAI = Utils.getResource("KHONG_IN_BIEN_LAI", UIRESOURCES);
	 
	 public static final String HSBA_KHONG_TON_TAI = Utils.getResource("HSBA_KHONG_TON_TAI", UIRESOURCES);
	 
	 public static final String BN_DA_CHUYEN_VAO_NOI_TRU = Utils.getResource("BN_DA_CHUYEN_VAO_NOI_TRU", UIRESOURCES);
	 
	 public static final String THAM_KHAM_TRUNG_BAN_KHAM = Utils.getResource("THAM_KHAM_TRUNG_BAN_KHAM", UIRESOURCES);
	 
	 public static final String TYLE_BH_VUOT_TUYEN=Utils.getResource("TYLE_BH_VUOT_TUYEN", RESOURCES);
	 
	 public static final String TYLE_BH2_VUOT_TUYEN=Utils.getResource("TYLE_BH2_VUOT_TUYEN", RESOURCES);
	 
	 public static final String REPORT_KHOA_DINH_DUONG=Utils.getResource("REPORT_KHOA_DINH_DUONG", RESOURCES);
	 
	 public static final String MUC_TIEN_AN_CAN_BO_TRUNG_CAO=Utils.getResource("MUC_TIEN_AN_CAN_BO_TRUNG_CAO", RESOURCES);
	 
	 public static final String KHONG_PHAI_DOI_TUONG_BHYT =Utils.getResource("KHONG_PHAI_DOI_TUONG_BHYT", UIRESOURCES);
	 
	 public static final String KHONG_PHAI_DOI_TUONG_CAP_CUU =Utils.getResource("KHONG_PHAI_DOI_TUONG_CAP_CUU", UIRESOURCES);
	 
	 public static final String KHONG_THANH_TOAN_O_DAY =Utils.getResource("KHONG_THANH_TOAN_O_DAY", UIRESOURCES);
	 
	 public static final String DOI_TUONG_CAP_CUU =Utils.getResource("DOI_TUONG_CAP_CUU", UIRESOURCES);
	 
	 public static final String MASO_CO_SO_KCB=Utils.getResource("MASO_CO_SO_KCB", RESOURCES);
	 
	 public static final String MASO_CO_SO_KCB_CUNG_TINH=Utils.getResource("MASO_CO_SO_KCB_CUNG_TINH", RESOURCES);
	 
	 public static final String DANGKY_ONLINE_DENSOM =Utils.getResource("DANGKY_ONLINE_DENSOM", UIRESOURCES);
	 
	 public static final String DANGKY_ONLINE_DENTRE =Utils.getResource("DANGKY_ONLINE_DENTRE", UIRESOURCES);
	 
	 public static final String KHOA_VISINH_MA =Utils.getResource("KHOA_VISINH_MA", RESOURCES);
	 
	 public static final String VISINH_UPLOAD =Utils.getResource("VISINH_UPLOAD", RESOURCES);
	 
	 public static final String LABCONN_SERVER =Utils.getResource("LABCONN_SERVER", RESOURCES);
	 
	 public static final String LABCONN_PORT =Utils.getResource("LABCONN_PORT", RESOURCES);
	 
	 public static final String LABCONN_DATABASE =Utils.getResource("LABCONN_DATABASE", RESOURCES);
	 
	 public static final String LABCONN_USERNAME =Utils.getResource("LABCONN_USERNAME", RESOURCES);
	 
	 public static final String LABCONN_PASSWORD =Utils.getResource("LABCONN_PASSWORD", RESOURCES);
	 
	public static final String PACS_SERVER =Utils.getResource("PACS_SERVER", RESOURCES);
	 
	 public static final String PACS_PORT =Utils.getResource("PACS_PORT", RESOURCES);
	 
	 public static final String PACS_DATABASE =Utils.getResource("PACS_DATABASE", RESOURCES);
	 
	 public static final String PACS_USERNAME =Utils.getResource("PACS_USERNAME", RESOURCES);
	 
	 public static final String PACS_PASSWORD =Utils.getResource("PACS_PASSWORD", RESOURCES);

	 public static final String NHAPNUOC_EMPTY =Utils.getResource("NHAPNUOC_EMPTY", UIRESOURCES);
	 
	 public static final String NHAPNUOC_EXISTS =Utils.getResource("NHAPNUOC_EXISTS", UIRESOURCES);
	 
	 public static final String NOI_DUNG_THU_MP =Utils.getResource("NOI_DUNG_THU_MP", UIRESOURCES);	 
	 
	 public static final String KET_NOI_MAY_XET_NGHIEM =Utils.getResource("KET_NOI_MAY_XET_NGHIEM", RESOURCES);
	 
	public static final String KET_NOI_SERVER_PACS =Utils.getResource("KET_NOI_SERVER_PACS", RESOURCES);

	 //Quan ly Duoc
	 public static final String INPUT_TONDAU = Utils.getResource("INPUT_TONDAU", RESOURCES);
	 public static final String IS_USED_KHOTE = Utils.getResource("IS_USED_KHOTE", RESOURCES);
	 public static final String DINH_HUONG_THUOC_BAN_KHAM = Utils.getResource("DINH_HUONG_THUOC_BAN_KHAM", RESOURCES);
	 public static final String PRIORITY_TON_LO_THUOC = Utils.getResource("PRIORITY_TON_LO_THUOC", RESOURCES);
	 public static final String DINH_HUONG_THUOC_NT = Utils.getResource("DINH_HUONG_THUOC_NT", RESOURCES);
	 
	 public static final String PHIEU_DU_TRU_LANH_THUOC = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_TABLE = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_TABLE", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_MS = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_MS", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_HC = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_HC", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_HC_MS = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_HC_MS", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_HC_TABLE = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_HC_TABLE", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_YC = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_YC", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_YC_MS = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_YC_MS", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_YC_TABLE = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_YC_TABLE", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_GN = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_GN", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_LANH_THUOC_HT = Utils.getResource("PHIEU_DU_TRU_LANH_THUOC_HT", UIRESOURCES);
	 
	 public static final String PHIEU_DU_TRU_TRA_THUOC = Utils.getResource("PHIEU_DU_TRU_TRA_THUOC", UIRESOURCES);	 
	 public static final String PHIEU_DU_TRU_TRA_THUOC_GN = Utils.getResource("PHIEU_DU_TRU_TRA_THUOC_GN", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_TRA_THUOC_HT = Utils.getResource("PHIEU_DU_TRU_TRA_THUOC_HT", UIRESOURCES);
	 
	 public static final String PHIEU_DU_TRU_NOT_FOUND = Utils.getResource("PhieuDuTru_KhongTimThay", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_DA_XUAT_HANG = Utils.getResource("PhieuDuTru_DaXuatHang", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_DA_TRA_HANG = Utils.getResource("PhieuDuTru_DaTraHang", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_KHONGDUTHUOCTRA = Utils.getResource("PHIEU_DU_TRU_KHONGDUTHUOCTRA", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_KHO_NOT_FOUND = Utils.getResource("PhieuDuTru_Kho_KhongTimThay", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_NULL =  Utils.getResource("phieudutru_notfound", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_KHONGCOTHUOC =  Utils.getResource("PHIEU_DU_TRU_KHONGCOTHUOC", UIRESOURCES);
	 public static final String PHIEU_DU_TRU_KHONGCOTHUOCXUAT =  Utils.getResource("PHIEU_DU_TRU_KHONGCOTHUOCXUAT", UIRESOURCES);	 
	 public static final String PHIEU_DU_TRU_NOTEDIT =  Utils.getResource("PHIEU_DU_TRU_NOTEDIT", UIRESOURCES);	
	 
	 public static final String PHIEUNHAPKHO_DMT_NULL = Utils.getResource("PHIEUNHAPKHO_DMT_NULL", UIRESOURCES);
	 public static final String PHIEUNHAPKHO_THANHCONG = Utils.getResource("PHIEUNHAPKHO_THANHCONG", UIRESOURCES);
	 public static final String PHIEUNHAPKHO_THATBAI = Utils.getResource("PHIEUNHAPKHO_THATBAI", UIRESOURCES);
	 public static final String PHIEUNHAPKHO_NULL = Utils.getResource("PHIEUNHAPKHO_NULL", UIRESOURCES);
	 public static final String PHIEUNHAPKHO_FORMATDATE = Utils.getResource("PHIEUNHAPKHO_FORMATDATE", UIRESOURCES);
	 
	// phieu xuat kho
	 public static final String PHIEUXUATKHO_NULL = Utils.getResource("PHIEUXUATKHO_NULL", UIRESOURCES);
	 public static final String PHIEUXUATKHO_TK_NULL = Utils.getResource("PHIEUXUATKHO_TK_NULL", UIRESOURCES);
	 public static final String PHIEUXUATKHO_THANHCONG = Utils.getResource("PHIEUXUATKHO_THANHCONG", UIRESOURCES);
	 public static final String PHIEUXUATKHO_THATBAI = Utils.getResource("PHIEUXUATKHO_THATBAI", UIRESOURCES);
	 public static final String PHIEUXUATKHO_DMT_NULL = Utils.getResource("PHIEUXUATKHO_DMT_NULL", UIRESOURCES);
	 public static final String PHIEUXUATKHO_FORMATDATE = Utils.getResource("PHIEUXUATKHO_FORMATDATE", UIRESOURCES);
	 public static final String PHIEUXUATKHO_SLKHONGDUXUAT = Utils.getResource("PHIEUXUATKHO_SLKHONGDUXUAT", UIRESOURCES);
	 public static final String PHIEUXUATKHO_SLKHONGDUXUATTL = Utils.getResource("PHIEUXUATKHO_SLKHONGDUXUATTL", UIRESOURCES);
	 public static final String PHIEUXUATKHO_DANGKIEMKE = Utils.getResource("PHIEUXUATKHO_DANGKIEMKE", UIRESOURCES);
	    
	 //phieu xuat BH
	 public static final String PHIEUXUATBH_SLKHONGDUXUAT = Utils.getResource("PHIEUXUATBH_SLKHONGDUXUAT", UIRESOURCES);
	 public static final String PHIEUXUATBH_DAXUAT = Utils.getResource("PHIEUXUATBH_DAXUAT", UIRESOURCES);
	    
	 // phieu tra kho    
	 public static final String PHIEUTRAKHO_THANHCONG = Utils.getResource("PHIEUTRAKHO_THANHCONG", UIRESOURCES);
	 public static final String PHIEUTRAKHO_THATBAI = Utils.getResource("PHIEUTRAKHO_THATBAI", UIRESOURCES);
	 public static final String PHIEUTRAKHO_DMT_NULL = Utils.getResource("PHIEUTRAKHO_DMT_NULL", UIRESOURCES);
	 public static final String PHIEUTRAKHO_SLKHONGDUTRA = Utils.getResource("PHIEUTRAKHO_SLKHONGDUTRA", UIRESOURCES);
	 
	 //Kiem ke
	 public static final String KIEMKE_KHONG_CO_THUOC = Utils.getResource("KIEMKE_KHONG_CO_THUOC", UIRESOURCES);
	 public static final String KIEMKE_DANG_KIEM_KE = Utils.getResource("KIEMKE_DANG_KIEM_KE", UIRESOURCES);
	 
	 //KiemKe tu dong
	 public static final String THOI_HAN_DONG_KIEM_KE = Utils.getResource("THOI_HAN_DONG_KIEM_KE", RESOURCES);
	 public static final String KIEMKE_HOUR = Utils.getResource("KIEMKE_HOUR", RESOURCES);
	 public static final String KIEMKE_MINUTE = Utils.getResource("KIEMKE_MINUTE", RESOURCES);
	 public static final String KIEMKE_SEC = Utils.getResource("KIEMKE_SEC", RESOURCES);
	 
	 public static final String KHOA_BHYT_MA = Utils.getResource("KHOA_BHYT_MA", RESOURCES);
	 public static final String KHOA_TE_MA = Utils.getResource("KHOA_TE_MA", RESOURCES);
	 public static final String KHOA_NT_MA = Utils.getResource("KHOA_NT_MA", RESOURCES);
	 public static final String KHOA_KC_MA = Utils.getResource("KHOA_KC_MA", RESOURCES);
	 
	 public static final String MA_BENH_VIEN = Utils.getResource("MA_BENH_VIEN", UIRESOURCES);
	 public static final String MA_TINH_BHYT = Utils.getResource("MA_TINH_BHYT", UIRESOURCES);
	 
	 // Thong tin Y lenh
	 public static final String THUOC_BAN_KHAM = Utils.getResource("THUOC_BAN_KHAM", UIRESOURCES);
	 public static final String THUOC_QUAY_BV = Utils.getResource("THUOC_QUAY_BV", UIRESOURCES);
	 public static final String THUOC_TOA_VE = Utils.getResource("THUOC_TOA_VE", UIRESOURCES);
	 public static final String KHONG_DUNG_THUOC = Utils.getResource("KHONG_DUNG_THUOC", UIRESOURCES);
	 
	 public static final String KHONG_TIM_THAY_SO_THE_BHYT = Utils.getResource("KHONG_TIM_THAY_SO_THE_BHYT", UIRESOURCES);
	 public static final String KHONG_TIM_THAY_THAMKHAM = Utils.getResource("KHONG_TIM_THAY_THAMKHAM", UIRESOURCES);
	 
	//phieu chuyen xac
	 public static final String PGX_LT_THANHCONG = Utils.getResource("PGX_LT_THANHCONG", UIRESOURCES);
	 public static final String PGX_LT_THATBAI = Utils.getResource("PGX_LT_THATBAI", UIRESOURCES);
	 public static final String PGX_CN_THANHCONG = Utils.getResource("PGX_CN_THANHCONG", UIRESOURCES);
	 public static final String PGX_CN_THATBAI = Utils.getResource("PGX_CN_THATBAI", UIRESOURCES);
	 public static final String PGX_NULL = Utils.getResource("PGX_NULL", UIRESOURCES);
	 
	 
	 //Phieu phau thuat thu thuat
	 public static final String PPTTT_LT_THANHCONG = Utils.getResource("PPTTT_LT_THANHCONG", UIRESOURCES);
	 public static final String PPTTT_LT_THATBAI = Utils.getResource("PPTTT_LT_THATBAI", UIRESOURCES);
	 public static final String PPTTT_CN_THANHCONG = Utils.getResource("PPTTT_CN_THANHCONG", UIRESOURCES);
	 public static final String PPTTT_CN_THATBAI = Utils.getResource("PPTTT_CN_THATBAI", UIRESOURCES);
	 public static final String PPTTT_NULL = Utils.getResource("PPTTT_NULL", UIRESOURCES);
	 
	 // Benh an luu coh Benh nhan cap cuu
	 public static final String BA_LUU_THANH_CONG = Utils.getResource("BA_LUU_THANH_CONG", UIRESOURCES);
	 public static final String BA_LUU_THAT_BAI = Utils.getResource("BA_LUU_THAT_BAI", UIRESOURCES);
	 
	//Lap trich bien ban hoi chan
	 public static final String LTBBHC_LT_THANHCONG = Utils.getResource("LTBBHC_LT_THANHCONG", UIRESOURCES);
	 public static final String LTBBHC_LT_THATBAI = Utils.getResource("LTBBHC_LT_THATBAI", UIRESOURCES);
	 public static final String LTBBHC_CN_THANHCONG = Utils.getResource("LTBBHC_CN_THANHCONG", UIRESOURCES);
	 public static final String LTBBHC_CN_THATBAI = Utils.getResource("LTBBHC_CN_THATBAI", UIRESOURCES);
	 public static final String LTBBHC_NULL = Utils.getResource("LTBBHC_NULL", UIRESOURCES);
	 
	//Bien ban hoi chan phau thuat
	 public static final String BBHCPT_LT_THANHCONG = Utils.getResource("BBHCPT_LT_THANHCONG", UIRESOURCES);
	 public static final String BBHCPT_LT_THATBAI = Utils.getResource("BBHCPT_LT_THATBAI", UIRESOURCES);
	 public static final String BBHCPT_CN_THANHCONG = Utils.getResource("BBHCPT_CN_THANHCONG", UIRESOURCES);
	 public static final String BBHCPT_CN_THATBAI = Utils.getResource("BBHCPT_CN_THATBAI", UIRESOURCES);
	 public static final String BBHCPT_NULL = Utils.getResource("BBHCPT_NULL", UIRESOURCES);
	 
	//Toa xuat vien
	 public static final String SO_VAO_VIEN_UNAVAILABLE = Utils.getResource("SO_VAO_VIEN_UNAVAILABLE", UIRESOURCES);
	 public static final String SO_VAO_VIEN_NOTFOUND =Utils.getResource("SO_VAO_VIEN_NOTFOUND", UIRESOURCES);
	 public static final String DA_XUAT_THUOC_NT =Utils.getResource("DA_XUAT_THUOC_NT", UIRESOURCES);
	 public static final String CHUA_THANH_TOAN_VP =Utils.getResource("CHUA_THANH_TOAN_VP", UIRESOURCES);	 
	 public static final String HSBA_NOTFOUND =Utils.getResource("HSBA_NOTFOUND", UIRESOURCES);
	 
	 //Thuoc dong y
	 public static final String DONG_Y_APPLY_ALL = Utils.getResource("DONG_Y_APPLY_ALL", RESOURCES);
	 public static final String EACH_THANGTHUOC_PRICE = Utils.getResource("EACH_THANGTHUOC_PRICE", RESOURCES);
	 public static final String BAI_THUOC_DY_FOUNDED = Utils.getResource("BAI_THUOC_DY_FOUNDED", UIRESOURCES);
	 public static final String BAI_THUOC_DY_NOTFOUND_VITHUOC = Utils.getResource("BAI_THUOC_DY_NOTFOUND_VITHUOC", UIRESOURCES);
	 
	 //Mapping giua user va NVien BV
	 public static final String USER_MAP_NV = Utils.getResource("USER_MAP_NV", UIRESOURCES);
	 
	 //Xuat Du lieu BHYT
	 public static final String XUAT_DULIEU_BHYT_SUCCESS = Utils.getResource("XUAT_DULIEU_BHYT_SUCCESS", UIRESOURCES);
	 public static final String XUAT_DULIEU_BHYT_ERROR = Utils.getResource("XUAT_DULIEU_BHYT_ERROR", UIRESOURCES);
	 //Them tieu de
	 public static final String BAO_CAO_THONG_KE = Utils.getResource("BAO_CAO_THONG_KE", UIRESOURCES);
	 public static final String THANG_NAM = Utils.getResource("THANG_NAM", UIRESOURCES);
	 public static final String TU = Utils.getResource("TU", UIRESOURCES);
	 public static final String DEN = Utils.getResource("DEN", UIRESOURCES);
	 //Cho phep trung so the bao hiem
	 public static final String CHO_PHEP_TRUNG_SO_THE_BHYT = Utils.getResource("CHO_PHEP_TRUNG_SO_THE_BHYT", RESOURCES);
    //giay chuyen vien
    public static final String GCN_LT_THANHCONG = Utils.getResource("GCN_LT_THANHCONG", UIRESOURCES);
    public static final String GCN_LT_THATBAI = Utils.getResource("GCN_LT_THATBAI", UIRESOURCES);
    public static final String GCN_CN_THANHCONG = Utils.getResource("GCN_CN_THANHCONG", UIRESOURCES);
    public static final String GCN_CN_THATBAI = Utils.getResource("GCN_CN_THATBAI", UIRESOURCES);
    public static final String GCN_NULL = Utils.getResource("GCN_NULL", UIRESOURCES);	 
    //Tom tat benh an
    public static final String TTBA_LT_THANHCONG = Utils.getResource("TTBA_LT_THANHCONG", UIRESOURCES);
    public static final String TTBA_LT_THATBAI = Utils.getResource("TTBA_LT_THATBAI", UIRESOURCES);
    public static final String TTBA_CN_THANHCONG = Utils.getResource("TTBA_CN_THANHCONG", UIRESOURCES);
    public static final String TTBA_CN_THATBAI = Utils.getResource("TTBA_CN_THATBAI", UIRESOURCES);
    public static final String TTBA_NULL = Utils.getResource("TTBA_NULL", UIRESOURCES);
    public static final String BENH_NHAN_CO_HSBA_NOI_TRU = Utils.getResource("BENH_NHAN_CO_HSBA_NOI_TRU", UIRESOURCES);
    public static final String BENH_NHAN_CO_THONG_TIN_NGOAI_TRU = Utils.getResource("BENH_NHAN_CO_THONG_TIN_NGOAI_TRU", UIRESOURCES);
    
    //Danh sach loai can lam sang bang gia
    public static final String DS_LOAI_CLS_BANG_GIA_HUYET_HOC = Utils.getResource("DS_LOAI_CLS_BANG_GIA_HUYET_HOC", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_SINH_HOA = Utils.getResource("DS_LOAI_CLS_BANG_GIA_SINH_HOA", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_VI_TRUNG = Utils.getResource("DS_LOAI_CLS_BANG_GIA_VI_TRUNG", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_KY_SINH_TRUNG = Utils.getResource("DS_LOAI_CLS_BANG_GIA_KY_SINH_TRUNG", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_HIV = Utils.getResource("DS_LOAI_CLS_BANG_GIA_HIV", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_XNKHAC = Utils.getResource("DS_LOAI_CLS_BANG_GIA_XNKHAC", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_CHIEU_XQ = Utils.getResource("DS_LOAI_CLS_BANG_GIA_CHIEU_XQ", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_CHUP_XQT = Utils.getResource("DS_LOAI_CLS_BANG_GIA_CHUP_XQT", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_CHUP_XQKT = Utils.getResource("DS_LOAI_CLS_BANG_GIA_CHUP_XQKT", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_SIEU_AM = Utils.getResource("DS_LOAI_CLS_BANG_GIA_SIEU_AM", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_CT_SCANNER = Utils.getResource("DS_LOAI_CLS_BANG_GIA_CT_SCANNER", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_CDHA_CHT = Utils.getResource("DS_LOAI_CLS_BANG_GIA_CDHA_CHT", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_CDHA_KHAC = Utils.getResource("DS_LOAI_CLS_BANG_GIA_CDHA_KHAC", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_DIEN_TIM = Utils.getResource("DS_LOAI_CLS_BANG_GIA_DIEN_TIM", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_DIEN_NAO = Utils.getResource("DS_LOAI_CLS_BANG_GIA_DIEN_NAO", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_NOI_SOI = Utils.getResource("DS_LOAI_CLS_BANG_GIA_NOI_SOI", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_TDCN_KHAC = Utils.getResource("DS_LOAI_CLS_BANG_GIA_TDCN_KHAC", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_TRUYEN_MAU = Utils.getResource("DS_LOAI_CLS_BANG_GIA_TRUYEN_MAU", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_MO_DAI_THE = Utils.getResource("DS_LOAI_CLS_BANG_GIA_MO_DAI_THE", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_XN_VI_THE = Utils.getResource("DS_LOAI_CLS_BANG_GIA_XN_VI_THE", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_GPB_KHAC = Utils.getResource("DS_LOAI_CLS_BANG_GIA_GPB_KHAC", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_KSTST_DT = Utils.getResource("DS_LOAI_CLS_BANG_GIA_KSTST_DT", UIRESOURCES);    
    public static final String DS_LOAI_CLS_BANG_GIA_KSTST_FALCI = Utils.getResource("DS_LOAI_CLS_BANG_GIA_KSTST_FALCI", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_KSTST_VIVAX = Utils.getResource("DS_LOAI_CLS_BANG_GIA_KSTST_VIVAX", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_KSTST_FV = Utils.getResource("DS_LOAI_CLS_BANG_GIA_KSTST_FV", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_VLTL_CHIEU_HN = Utils.getResource("DS_LOAI_CLS_BANG_GIA_VLTL_CHIEU_HN", UIRESOURCES);    
    public static final String DS_LOAI_CLS_BANG_GIA_VLTL_KEO_TA = Utils.getResource("DS_LOAI_CLS_BANG_GIA_VLTL_KEO_TA", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_VLTL_TAP_VAN_DONG = Utils.getResource("DS_LOAI_CLS_BANG_GIA_VLTL_TAP_VAN_DONG", UIRESOURCES);
    public static final String DS_LOAI_CLS_BANG_GIA_KHAC = Utils.getResource("DS_LOAI_CLS_BANG_GIA_KHAC", UIRESOURCES);
    
    //Reset ma phieu tu dong
	 public static final String RESET_MAPHIEU_HOUR = Utils.getResource("RESET_MAPHIEU_HOUR", RESOURCES);
	 public static final String RESET_MAPHIEU_MINUTE = Utils.getResource("RESET_MAPHIEU_MINUTE", RESOURCES);
	 public static final String RESET_MAPHIEU_SEC = Utils.getResource("RESET_MAPHIEU_SEC", RESOURCES);
	 
	 //Xoa ho so benh an
	 public static final String DELFAIL_HSBA = Utils.getResource("DELFAIL_HSBA", UIRESOURCES);
	 public static final String DELSUCESS_HSBA = Utils.getResource("DELSUCESS_HSBA", UIRESOURCES);
	 public static final String DELFAILADMIN_HSBA = Utils.getResource("DELFAILADMIN_HSBA", UIRESOURCES);
	 public static final String DELFAIL_HSBACHUYENKHOA = Utils.getResource("DELFAIL_HSBACHUYENKHOA", UIRESOURCES);
	 public static final String DELSUCESS_HSBACHUYENKHOA = Utils.getResource("DELSUCESS_HSBACHUYENKHOA", UIRESOURCES);
	 public static final String DELFAIL_HSBARAV = Utils.getResource("DELFAIL_HSBARAV", UIRESOURCES);
	 public static final String DELSUCESS_HSBARAV = Utils.getResource("DELSUCESS_HSBARAV", UIRESOURCES);
	 
	 public static final String IN_TOA_THUOC_ERROR = Utils.getResource("IN_TOA_THUOC_ERROR", UIRESOURCES);
	 public static final String PHAI_NHAP_CHAN_DOAN = Utils.getResource("PHAI_NHAP_CHAN_DOAN", UIRESOURCES);
	 
	 //Danh muc Tang
	 public static final String tang_de_su = Utils.getResource("tang_de_su", UIRESOURCES);
	 public static final String tang_de_fa = Utils.getResource("tang_de_fa", UIRESOURCES);
	 public static final String tang_de_co = Utils.getResource("tang_de_co", UIRESOURCES);
	 public static final String tang_cr_su = Utils.getResource("tang_cr_su", UIRESOURCES);
	 public static final String tang_cr_fa = Utils.getResource("tang_cr_fa", UIRESOURCES);
	 public static final String tang_up_su = Utils.getResource("tang_up_su", UIRESOURCES);
	 public static final String tang_up_fa = Utils.getResource("tang_up_fa", UIRESOURCES);
	 public static final String tang_up_duplicate = Utils.getResource("tang_up_duplicate", UIRESOURCES);
	 
	 public static final String dangkykb_thanhcong = Utils.getResource("dangkykb_thanhcong", UIRESOURCES);
	 public static final String TIEP_DON_DUOC_TAO_TU_NOI_TRU = Utils.getResource("TIEP_DON_DUOC_TAO_TU_NOI_TRU", UIRESOURCES);
	 public static final String DANGKY_BHYT_TRONGNGAY = Utils.getResource("DANGKY_BHYT_TRONGNGAY", UIRESOURCES);
	 public static final String DANGKY_CHUATOINGAY_TAIKHAM = Utils.getResource("DANGKY_CHUATOINGAY_TAIKHAM", UIRESOURCES);
	 public static final String NGAY_GIO = Utils.getResource("NGAY_GIO", UIRESOURCES);
	 public static final String NOI_DK_KCB = Utils.getResource("Noi_ang_ky_KCB_", UIRESOURCES);
	 public static final String TINH_CAP_BHYT = Utils.getResource("TINH_CAP_BHYT", UIRESOURCES);
	 public static final String BAT_BUOC_NHAP = Utils.getResource("common_requireField", UIRESOURCES);
	 
	 public static final String CONG_VAO_SO_NGAY_DIEU_TRI = Utils.getResource("CONG_VAO_SO_NGAY_DIEU_TRI", RESOURCES);
	 public static final String DA_THANH_TOAN = Utils.getResource("DA_THANH_TOAN", UIRESOURCES);
	 public static final String CHUA_THANH_TOAN = Utils.getResource("CHUA_THANH_TOAN", UIRESOURCES);
	 public static final String CAP_CUU = Utils.getResource("CAP_CUU", UIRESOURCES);
	 
	 public static final String TOA_THUOC_TRUOC_KO_CO =  Utils.getResource("toa_thuoc_truoc_ko_co", UIRESOURCES);
	 public static final String BENH_NHAN_DA_RA_VIEN =  Utils.getResource("BENH_NHAN_DA_RA_VIEN", UIRESOURCES);
	 
	//update ton trong dmthuoc tu dong
	 public static final String UPDATING_THUOC_HOUR = Utils.getResource("UPDATING_THUOC_HOUR", RESOURCES);
	 public static final String UPDATING_THUOC_MINUTE = Utils.getResource("UPDATING_THUOC_MINUTE", RESOURCES);
	 public static final String UPDATING_THUOC_SEC = Utils.getResource("UPDATING_THUOC_SEC", RESOURCES);
	 
	 public static final String CHUYEN_VAO_NOI_TRU_OPTION = Utils.getResource("CHUYEN_VAO_NOI_TRU_OPTION", RESOURCES);
	 
	 public static final String THUOC = Utils.getResource("THUOC", UIRESOURCES);
	 public static final String CAN_LAM_SANG = Utils.getResource("CAN_LAM_SANG", UIRESOURCES);
	 public static final String THUOC_VA_CAN_LAM_SANG = Utils.getResource("THUOC_VA_CAN_LAM_SANG", UIRESOURCES);
	 public static final String BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN = Utils.getResource("BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN", UIRESOURCES);
	 public static final String BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN = Utils.getResource("BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN", UIRESOURCES);
	 
	 public static final String TIEPDON_YTBH_DANGDIEUTRI_NOITRU = Utils.getResource("TIEPDON_YTBH_DANGDIEUTRI_NOITRU", UIRESOURCES);
	 public static final String NHAP_CHINH_XAC_KHOA_BUONG = Utils.getResource("NHAP_CHINH_XAC_KHOA_BUONG", UIRESOURCES);
	 
}		
