/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmCachDungThuoc;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmThuoc;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "THUOC_PHONG_KHAM")
public class ThuocPhongKham implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THUOC_PHONG_KHAM")
    @SequenceGenerator(name = "THUOC_PHONG_KHAM", sequenceName = "THUOC_PHONG_KHAM_THUOCPHONGKHA", allocationSize = 1)
    @Column(name = "THUOCPHONGKHAM_MA", nullable = false)
    private Integer thuocphongkhamMa;
    @Column(name = "THUOCPHONGKHAM_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocphongkhamNgaygio;
    @Column(name = "THUOCPHONGKHAM_MAPHIEUH")
    private String thuocphongkhamMaphieuh;
    @Column(name = "THUOCPHONGKHAM_LOAI")
    private String thuocphongkhamLoai;
    @Column(name = "THUOCPHONGKHAM_NAMNHAP")
    private String thuocphongkhamNamnhap;
    @Column(name = "THUOCPHONGKHAM_NGAYHD")
    private String thuocphongkhamNgayhd;
    @Column(name = "THUOCPHONGKHAM_THANGHD")
    private String thuocphongkhamThanghd;
    @Column(name = "THUOCPHONGKHAM_NAMHD")
    private String thuocphongkhamNamhd;
    @Column(name = "THUOCPHONGKHAM_YEUCAU")
    private Boolean thuocphongkhamYeucau;
    @Column(name = "THUOCPHONGKHAM_YC")
    private Boolean thuocphongkhamYc;
    @Column(name = "THUOCPHONGKHAM_MIEN")
    private Boolean thuocphongkhamMien;
    @Column(name = "THUOCPHONGKHAM_KHONGTHU")
    private Boolean thuocphongkhamKhongthu;
    @Column(name = "THUOCPHONGKHAM_DONGIA")
    private Double thuocphongkhamDongia;
    @Column(name = "THUOCPHONGKHAM_DONGIABH")
    private Double thuocphongkhamDongiabh;
    @Column(name = "THUOCPHONGKHAM_LO")
    private String thuocphongkhamLo;
    @Column(name = "THUOCPHONGKHAM_SODANGKY")
    private String thuocphongkhamSodangky;
    @Column(name = "THUOCPHONGKHAM_SOLUONG")
    private Double thuocphongkhamSoluong;
    @Column(name = "THUOCPHONGKHAM_YLENH")
    private Double thuocphongkhamYlenh;
    @Column(name = "THUOCPHONGKHAM_TRA")
    private Double thuocphongkhamTra;
    @Column(name = "THUOCPHONGKHAM_CACHDUNG")
    private String thuocphongkhamCachdung;
    @Column(name = "THUOCPHONGKHAM_NGAYGIODUNG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocphongkhamNgaygiodung;
    @Column(name = "THUOCPHONGKHAM_DIEUDUONG")
    private Integer thuocphongkhamDieuduong;
    @Column(name = "THUOCPHONGKHAM_LANIN")
    private Short thuocphongkhamLanin;
    @Column(name = "THUOCPHONGKHAM_NGAYINLINH")
    @Temporal(TemporalType.DATE)
    private Date thuocphongkhamNgayinlinh;
    @Column(name = "THUOCPHONGKHAM_LANTRA")
    private Short thuocphongkhamLantra;
    @Column(name = "THUOCPHONGKHAM_NGAYINTRA")
    @Temporal(TemporalType.DATE)
    private Date thuocphongkhamNgayintra;
    @Column(name = "THUOCPHONGKHAM_DONGIABAN")
    private Double thuocphongkhamDongiaban;
    @Column(name = "THUOCPHONGKHAM_NGAYUONG")
    private String thuocphongkhamNgayuong;
    @Column(name = "THUOCPHONGKHAM_SOLUONGUONG")
    private String thuocphongkhamSoluonguong;


     @JoinColumn(name = "THUOCPHONGKHAM_CUM", referencedColumnName = "DTDMCUM_MASO")
       @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum thuocphongkhamCum;

       @JoinColumn(name = "THUOCPHONGKHAM_CUMHOAN", referencedColumnName = "DTDMCUM_MASO")
       @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum thuocphongkhamCumHoan;


    @Column(name = "THUOCPHONGKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocphongkhamNgaygiocn;
    @Column(name = "THUOCPHONGKHAM_NGAYGIOTT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocphongkhamNgaygiott;

    @Column(name = "THUOCPHONGKHAM_NGAYGIOHOANTHU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocphongkhamNgaygioHoanThu;

    @Column(name = "THUOCPHONGKHAM_STATUS")
    private String thuocphongkhamStatus;
    @Column(name = "THUOCPHONGKHAM_XETGIAM")
    private Boolean thuocphongkhamXetgiam;
    @Column(name = "THUOCPHONGKHAM_TYLEBH")
    private Double thuocphongkhamTylebh;
    @Column(name = "THUOCPHONGKHAM_TYLEGIAM")
    private Short thuocphongkhamTylegiam;
    @Column(name = "THUOCPHONGKHAM_INTEM")
    private Boolean thuocphongkhamIntem;
    @Column(name = "THUOCPHONGKHAM_MAPHIEUD")
    private String thuocphongkhamMaphieud;
    @Column(name = "THUOCPHONGKHAM_DATT")
    private Boolean thuocphongkhamDatt;
    @Column(name = "THUOCPHONGKHAM_NDM")
    private Boolean thuocphongkhamNdm;
    @Column(name = "THUOCPHONGKHAM_SOTHANG")
    private Integer thuocphongkhamSothang;



     @Column(name = "THUOCPHONGKHAM_MALOIDAN")
    private String thuocphongkhamMaloidan;

     @Column(name = "THUOCPHONGKHAM_TENLOIDAN")
    private String thuocphongkhamTenloidan;
    @Column(name = "THUOCPHONGKHAM_TIENBNTRA")
    private Double thuocphongkhamTienbntra;

    @Column(name = "THUOCPHONGKHAM_TAIKHAM")
    @Temporal(TemporalType.DATE)
    private Date thuocphongkhamTaikham;
    @Column(name = "THUOCPHONGKHAM_KHO")
    private Integer thuocphongkhamKho;

    @Column(name = "THUOCPHONGKHAM_KYHIEU")
    private String thuocphongkhamKyhieu;
    @Column(name = "THUOCPHONGKHAM_QUYEN")
    private String thuocphongkhamQuyen;
    @Column(name = "THUOCPHONGKHAM_BIENLAI")
    private String thuocphongkhamBienlai;

    @Column(name = "THUOCPHONGKHAM_KYHIEUHOAN")
    private String thuocphongkhamKyhieuHoan;
    @Column(name = "THUOCPHONGKHAM_QUYENHOAN")
    private String thuocphongkhamQuyenHoan;
    @Column(name = "THUOCPHONGKHAM_BIENLAIHOAN")
    private String thuocphongkhamBienlaiHoan;

    @Column(name = "THUOCPHONGKHAM_MALK")
    private String thuocphongkhamMalk;
    @Column(name = "THUOCPHONGKHAM_ORD")
    private Short thuocphongkhamOrd;
    @JoinColumn(name = "THUOCPHONGKHAM_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocphongkhamBacsi;
    @JoinColumn(name = "THUOCPHONGKHAM_MADUNG", referencedColumnName = "DMCACHDUNGTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmCachDungThuoc thuocphongkhamMadung;
    @JoinColumn(name = "THUOCPHONGKHAM_CHEDOTT", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong thuocphongkhamChedott;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;
    @JoinColumn(name = "DMNGUONKINHPHI_MASO", referencedColumnName = "DMNGUONKINHPHI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonKinhPhi dmnguonkinhphiMaso;
    @JoinColumn(name = "THUOCPHONGKHAM_HANGSX", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat thuocphongkhamHangsx;
    @JoinColumn(name = "THUOCPHONGKHAM_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa thuocphongkhamKhoa;
    @JoinColumn(name = "THUOCPHONGKHAM_MATHUOC", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc thuocphongkhamMathuoc;
    @JoinColumn(name = "THUOCPHONGKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocphongkhamNhanviencn;
    @JoinColumn(name = "THUOCPHONGKHAM_PHANLOAI", referencedColumnName = "DMPHANLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanLoaiThuoc thuocphongkhamPhanloai;
    @JoinColumn(name = "THUOCPHONGKHAM_QUOCGIA", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia thuocphongkhamQuocgia;
    @JoinColumn(name = "THUOCPHONGKHAM_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocphongkhamThungan;

     @JoinColumn(name = "THUOCPHONGKHAM_THUNGANHOAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocphongkhamThunganHoan;


     @Column(name = "THUOCPHONGKHAM_CHIDAN")
    private String thuocphongkhamChidan;

      @Column(name = "THUOCPHONGKHAM_MACHIDAN")
    private String thuocphongkhamMaChidan;
      @Column(name = "THUOCPHONGKHAM_MAPHIEUDT")
    private String thuocphongkhamMaPhieuDT;

    @JoinColumn(name = "THUOCPHONGKHAM_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham thuocphongkhamThamkham;
    @JoinColumn(name = "THUOCDONGY_MASO", referencedColumnName = "THUOCDONGY_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThuocDongYNgoaiTru thuocdongyNgoaiTru;
    @Column(name = "DMBAITHUOC_MASO")
    private Integer dmbaithuocMaso;
    @Column(name = "THUOCPHONGKHAM_DONGIATT", nullable = false)
    private int thuocphongkhamDongiatt;
    @Column(name = "THUOCPHONGKHAM_THANHTIEN", nullable = false)
    private int thuocphongkhamThanhtien;
    public ThuocPhongKham() {
    }

    public String getThuocphongkhamChidan() {
        return thuocphongkhamChidan;
    }

    public void setThuocphongkhamChidan(String thuocphongkhamChidan) {
        this.thuocphongkhamChidan = thuocphongkhamChidan;
    }

    public ThuocPhongKham(Integer thuocphongkhamMa) {
        this.thuocphongkhamMa = thuocphongkhamMa;
    }

    public Integer getThuocphongkhamMa() {
        return thuocphongkhamMa;
    }

    public void setThuocphongkhamMa(Integer thuocphongkhamMa) {
        this.thuocphongkhamMa = thuocphongkhamMa;
    }

    public Date getThuocphongkhamNgaygio() {
        return thuocphongkhamNgaygio;
    }

    public void setThuocphongkhamNgaygio(Date thuocphongkhamNgaygio) {
        this.thuocphongkhamNgaygio = thuocphongkhamNgaygio;
    }

    public String getThuocphongkhamLoai() {
        return thuocphongkhamLoai;
    }

    public void setThuocphongkhamLoai(String thuocphongkhamLoai) {
        this.thuocphongkhamLoai = thuocphongkhamLoai;
    }

    public String getThuocphongkhamNamnhap() {
        return thuocphongkhamNamnhap;
    }

    public void setThuocphongkhamNamnhap(String thuocphongkhamNamnhap) {
        this.thuocphongkhamNamnhap = thuocphongkhamNamnhap;
    }

    public String getThuocphongkhamNgayhd() {
        return thuocphongkhamNgayhd;
    }

    public void setThuocphongkhamNgayhd(String thuocphongkhamNgayhd) {
        this.thuocphongkhamNgayhd = thuocphongkhamNgayhd;
    }

    public String getThuocphongkhamThanghd() {
        return thuocphongkhamThanghd;
    }

    public void setThuocphongkhamThanghd(String thuocphongkhamThanghd) {
        this.thuocphongkhamThanghd = thuocphongkhamThanghd;
    }

    public String getThuocphongkhamNamhd() {
        return thuocphongkhamNamhd;
    }

    public void setThuocphongkhamNamhd(String thuocphongkhamNamhd) {
        this.thuocphongkhamNamhd = thuocphongkhamNamhd;
    }

    public Boolean getThuocphongkhamYeucau() {
        return thuocphongkhamYeucau;
    }

    public void setThuocphongkhamYeucau(Boolean thuocphongkhamYeucau) {
        this.thuocphongkhamYeucau = thuocphongkhamYeucau;
    }

    public Boolean getThuocphongkhamYc() {
        return thuocphongkhamYc;
    }

    public void setThuocphongkhamYc(Boolean thuocphongkhamYc) {
        this.thuocphongkhamYc = thuocphongkhamYc;
    }

    public Boolean getThuocphongkhamMien() {
        return thuocphongkhamMien;
    }

    public void setThuocphongkhamMien(Boolean thuocphongkhamMien) {
        this.thuocphongkhamMien = thuocphongkhamMien;
    }

    public Boolean getThuocphongkhamKhongthu() {
        return thuocphongkhamKhongthu;
    }

    public void setThuocphongkhamKhongthu(Boolean thuocphongkhamKhongthu) {
        this.thuocphongkhamKhongthu = thuocphongkhamKhongthu;
    }

    public Double getThuocphongkhamDongia() {
        return thuocphongkhamDongia;
    }

    public void setThuocphongkhamDongia(Double thuocphongkhamDongia) {
        this.thuocphongkhamDongia = thuocphongkhamDongia;
    }

    public Double getThuocphongkhamDongiabh() {
        return thuocphongkhamDongiabh;
    }

    public void setThuocphongkhamDongiabh(Double thuocphongkhamDongiabh) {
        this.thuocphongkhamDongiabh = thuocphongkhamDongiabh;
    }

    public String getThuocphongkhamLo() {
        return thuocphongkhamLo;
    }

    public void setThuocphongkhamLo(String thuocphongkhamLo) {
        this.thuocphongkhamLo = thuocphongkhamLo;
    }

    public String getThuocphongkhamSodangky() {
        return thuocphongkhamSodangky;
    }

    public void setThuocphongkhamSodangky(String thuocphongkhamSodangky) {
        this.thuocphongkhamSodangky = thuocphongkhamSodangky;
    }

    public Double getThuocphongkhamSoluong() {
        return thuocphongkhamSoluong;
    }

    public void setThuocphongkhamSoluong(Double thuocphongkhamSoluong) {
        this.thuocphongkhamSoluong = thuocphongkhamSoluong;
    }

    public Double getThuocphongkhamYlenh() {
        return thuocphongkhamYlenh;
    }

    public void setThuocphongkhamYlenh(Double thuocphongkhamYlenh) {
        this.thuocphongkhamYlenh = thuocphongkhamYlenh;
    }

    public Double getThuocphongkhamTra() {
        return thuocphongkhamTra;
    }

    public void setThuocphongkhamTra(Double thuocphongkhamTra) {
        this.thuocphongkhamTra = thuocphongkhamTra;
    }

    public String getThuocphongkhamCachdung() {
        return thuocphongkhamCachdung;
    }

    public void setThuocphongkhamCachdung(String thuocphongkhamCachdung) {
        this.thuocphongkhamCachdung = thuocphongkhamCachdung;
    }

    public Date getThuocphongkhamNgaygiodung() {
        return thuocphongkhamNgaygiodung;
    }

    public void setThuocphongkhamNgaygiodung(Date thuocphongkhamNgaygiodung) {
        this.thuocphongkhamNgaygiodung = thuocphongkhamNgaygiodung;
    }

    public Integer getThuocphongkhamDieuduong() {
        return thuocphongkhamDieuduong;
    }

    public void setThuocphongkhamDieuduong(Integer thuocphongkhamDieuduong) {
        this.thuocphongkhamDieuduong = thuocphongkhamDieuduong;
    }

    public Short getThuocphongkhamLanin() {
        return thuocphongkhamLanin;
    }

    public void setThuocphongkhamLanin(Short thuocphongkhamLanin) {
        this.thuocphongkhamLanin = thuocphongkhamLanin;
    }

    public Date getThuocphongkhamNgayinlinh() {
        return thuocphongkhamNgayinlinh;
    }

    public void setThuocphongkhamNgayinlinh(Date thuocphongkhamNgayinlinh) {
        this.thuocphongkhamNgayinlinh = thuocphongkhamNgayinlinh;
    }

    public Short getThuocphongkhamLantra() {
        return thuocphongkhamLantra;
    }

    public void setThuocphongkhamLantra(Short thuocphongkhamLantra) {
        this.thuocphongkhamLantra = thuocphongkhamLantra;
    }

    public Date getThuocphongkhamNgayintra() {
        return thuocphongkhamNgayintra;
    }

    public void setThuocphongkhamNgayintra(Date thuocphongkhamNgayintra) {
        this.thuocphongkhamNgayintra = thuocphongkhamNgayintra;
    }

    public Double getThuocphongkhamDongiaban() {
        return thuocphongkhamDongiaban;
    }

    public void setThuocphongkhamDongiaban(Double thuocphongkhamDongiaban) {
        this.thuocphongkhamDongiaban = thuocphongkhamDongiaban;
    }

    public DtDmCum getThuocphongkhamCum() {
        return thuocphongkhamCum;
    }

    public void setThuocphongkhamCum(DtDmCum thuocphongkhamCum) {
        this.thuocphongkhamCum = thuocphongkhamCum;
    }

    public Date getThuocphongkhamNgaygiocn() {
        return thuocphongkhamNgaygiocn;
    }

    public void setThuocphongkhamNgaygiocn(Date thuocphongkhamNgaygiocn) {
        this.thuocphongkhamNgaygiocn = thuocphongkhamNgaygiocn;
    }

    public Date getThuocphongkhamNgaygiott() {
        return thuocphongkhamNgaygiott;
    }

    public void setThuocphongkhamNgaygiott(Date thuocphongkhamNgaygiott) {
        this.thuocphongkhamNgaygiott = thuocphongkhamNgaygiott;
    }

    public String getThuocphongkhamStatus() {
        return thuocphongkhamStatus;
    }

    public void setThuocphongkhamStatus(String thuocphongkhamStatus) {
        this.thuocphongkhamStatus = thuocphongkhamStatus;
    }

    public Boolean getThuocphongkhamXetgiam() {
        return thuocphongkhamXetgiam;
    }

    public void setThuocphongkhamXetgiam(Boolean thuocphongkhamXetgiam) {
        this.thuocphongkhamXetgiam = thuocphongkhamXetgiam;
    }

    public Double getThuocphongkhamTylebh() {
        return thuocphongkhamTylebh;
    }

    public void setThuocphongkhamTylebh(Double thuocphongkhamTylebh) {
        this.thuocphongkhamTylebh = thuocphongkhamTylebh;
    }

    public Short getThuocphongkhamTylegiam() {
        return thuocphongkhamTylegiam;
    }

    public void setThuocphongkhamTylegiam(Short thuocphongkhamTylegiam) {
        this.thuocphongkhamTylegiam = thuocphongkhamTylegiam;
    }

    public Boolean getThuocphongkhamIntem() {
        return thuocphongkhamIntem;
    }

    public void setThuocphongkhamIntem(Boolean thuocphongkhamIntem) {
        this.thuocphongkhamIntem = thuocphongkhamIntem;
    }

    public String getThuocphongkhamMaphieud() {
        return thuocphongkhamMaphieud;
    }

    public void setThuocphongkhamMaphieud(String thuocphongkhamMaphieud) {
        this.thuocphongkhamMaphieud = thuocphongkhamMaphieud;
    }

    public Boolean getThuocphongkhamDatt() {
        return thuocphongkhamDatt;
    }

    public void setThuocphongkhamDatt(Boolean thuocphongkhamDatt) {
        this.thuocphongkhamDatt = thuocphongkhamDatt;
    }

    public ThamKham getThuocphongkhamThamkham(boolean create) {
if(create && getThuocphongkhamThamkham() == null) setThuocphongkhamThamkham(new ThamKham());
return  getThuocphongkhamThamkham();
}
    public ThamKham getThuocphongkhamThamkham() {
        return thuocphongkhamThamkham;
    }

    public void setThuocphongkhamThamkham(ThamKham thuocphongkhamThamkham) {
        this.thuocphongkhamThamkham = thuocphongkhamThamkham;
    }

    public Boolean getThuocphongkhamNdm() {
        return thuocphongkhamNdm;
    }

    public void setThuocphongkhamNdm(Boolean thuocphongkhamNdm) {
        this.thuocphongkhamNdm = thuocphongkhamNdm;
    }

    public Integer getThuocphongkhamSothang() {
        return thuocphongkhamSothang;
    }

    public void setThuocphongkhamSothang(Integer thuocphongkhamSothang) {
        this.thuocphongkhamSothang = thuocphongkhamSothang;
    }



    public Date getThuocphongkhamTaikham() {
        return thuocphongkhamTaikham;
    }

    public void setThuocphongkhamTaikham(Date thuocphongkhamTaikham) {
        this.thuocphongkhamTaikham = thuocphongkhamTaikham;
    }

    public Integer getThuocphongkhamKho() {
        return thuocphongkhamKho;
    }

    public void setThuocphongkhamKho(Integer thuocphongkhamKho) {
        this.thuocphongkhamKho = thuocphongkhamKho;
    }

    public String getThuocphongkhamKyhieu() {
        return thuocphongkhamKyhieu;
    }

    public void setThuocphongkhamKyhieu(String thuocphongkhamKyhieu) {
        this.thuocphongkhamKyhieu = thuocphongkhamKyhieu;
    }

    public String getThuocphongkhamQuyen() {
        return thuocphongkhamQuyen;
    }

    public void setThuocphongkhamQuyen(String thuocphongkhamQuyen) {
        this.thuocphongkhamQuyen = thuocphongkhamQuyen;
    }

    public String getThuocphongkhamBienlai() {
        return thuocphongkhamBienlai;
    }

    public void setThuocphongkhamBienlai(String thuocphongkhamBienlai) {
        this.thuocphongkhamBienlai = thuocphongkhamBienlai;
    }

    public String getThuocphongkhamMalk() {
        return thuocphongkhamMalk;
    }

    public void setThuocphongkhamMalk(String thuocphongkhamMalk) {
        this.thuocphongkhamMalk = thuocphongkhamMalk;
    }

    public Short getThuocphongkhamOrd() {
        return thuocphongkhamOrd;
    }

    public void setThuocphongkhamOrd(Short thuocphongkhamOrd) {
        this.thuocphongkhamOrd = thuocphongkhamOrd;
    }

    public DtDmNhanVien getThuocphongkhamBacsi(boolean create) {
if(create && getThuocphongkhamBacsi() == null) setThuocphongkhamBacsi(new DtDmNhanVien());
return  getThuocphongkhamBacsi();
}
    public DtDmNhanVien getThuocphongkhamBacsi() {
        return thuocphongkhamBacsi;
    }

    public void setThuocphongkhamBacsi(DtDmNhanVien thuocphongkhamBacsi) {
        this.thuocphongkhamBacsi = thuocphongkhamBacsi;
    }

    public DmCachDungThuoc getThuocphongkhamMadung(boolean create) {
if(create && getThuocphongkhamMadung() == null) setThuocphongkhamMadung(new DmCachDungThuoc());
return  getThuocphongkhamMadung();
}
    public DmCachDungThuoc getThuocphongkhamMadung() {
        return thuocphongkhamMadung;
    }

    public void setThuocphongkhamMadung(DmCachDungThuoc thuocphongkhamMadung) {
        this.thuocphongkhamMadung = thuocphongkhamMadung;
    }

    public DmDoiTuong getThuocphongkhamChedott(boolean create) {
if(create && getThuocphongkhamChedott() == null) setThuocphongkhamChedott(new DmDoiTuong());
return  getThuocphongkhamChedott();
}
    public DmDoiTuong getThuocphongkhamChedott() {
        return thuocphongkhamChedott;
    }

    public void setThuocphongkhamChedott(DmDoiTuong thuocphongkhamChedott) {
        this.thuocphongkhamChedott = thuocphongkhamChedott;
    }

    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
if(create && getDmnctMaso() == null) setDmnctMaso(new DmNguonChuongTrinh());
return  getDmnctMaso();
}
    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso(boolean create) {
if(create && getDmnguonkinhphiMaso() == null) setDmnguonkinhphiMaso(new DmNguonKinhPhi());
return  getDmnguonkinhphiMaso();
}
    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    public DmNhaSanXuat getThuocphongkhamHangsx(boolean create) {
if(create && getThuocphongkhamHangsx() == null) setThuocphongkhamHangsx(new DmNhaSanXuat());
return  getThuocphongkhamHangsx();
}
    public DmNhaSanXuat getThuocphongkhamHangsx() {
        return thuocphongkhamHangsx;
    }

    public void setThuocphongkhamHangsx(DmNhaSanXuat thuocphongkhamHangsx) {
        this.thuocphongkhamHangsx = thuocphongkhamHangsx;
    }

    public DmKhoa getThuocphongkhamKhoa(boolean create) {
if(create && getThuocphongkhamKhoa() == null) setThuocphongkhamKhoa(new DmKhoa());
return  getThuocphongkhamKhoa();
}
    public DmKhoa getThuocphongkhamKhoa() {
        return thuocphongkhamKhoa;
    }

    public void setThuocphongkhamKhoa(DmKhoa thuocphongkhamKhoa) {
        this.thuocphongkhamKhoa = thuocphongkhamKhoa;
    }

    public DmThuoc getThuocphongkhamMathuoc(boolean create) {
if(create && getThuocphongkhamMathuoc() == null) setThuocphongkhamMathuoc(new DmThuoc());
return  getThuocphongkhamMathuoc();
}
    public DmThuoc getThuocphongkhamMathuoc() {
        return thuocphongkhamMathuoc;
    }

    public void setThuocphongkhamMathuoc(DmThuoc thuocphongkhamMathuoc) {
        this.thuocphongkhamMathuoc = thuocphongkhamMathuoc;
    }

    public DtDmNhanVien getThuocphongkhamNhanviencn(boolean create) {
if(create && getThuocphongkhamNhanviencn() == null) setThuocphongkhamNhanviencn(new DtDmNhanVien());
return  getThuocphongkhamNhanviencn();
}
    public DtDmNhanVien getThuocphongkhamNhanviencn() {
        return thuocphongkhamNhanviencn;
    }

    public void setThuocphongkhamNhanviencn(DtDmNhanVien thuocphongkhamNhanviencn) {
        this.thuocphongkhamNhanviencn = thuocphongkhamNhanviencn;
    }

    public DmPhanLoaiThuoc getThuocphongkhamPhanloai(boolean create) {
if(create && getThuocphongkhamPhanloai() == null) setThuocphongkhamPhanloai(new DmPhanLoaiThuoc());
return  getThuocphongkhamPhanloai();
}
    public DmPhanLoaiThuoc getThuocphongkhamPhanloai() {
        return thuocphongkhamPhanloai;
    }

    public void setThuocphongkhamPhanloai(DmPhanLoaiThuoc thuocphongkhamPhanloai) {
        this.thuocphongkhamPhanloai = thuocphongkhamPhanloai;
    }

    public DmQuocGia getThuocphongkhamQuocgia(boolean create) {
if(create && getThuocphongkhamQuocgia() == null) setThuocphongkhamQuocgia(new DmQuocGia());
return  getThuocphongkhamQuocgia();
}
    public DmQuocGia getThuocphongkhamQuocgia() {
        return thuocphongkhamQuocgia;
    }

    public void setThuocphongkhamQuocgia(DmQuocGia thuocphongkhamQuocgia) {
        this.thuocphongkhamQuocgia = thuocphongkhamQuocgia;
    }

    public DtDmNhanVien getThuocphongkhamThungan(boolean create) {
if(create && getThuocphongkhamThungan() == null) setThuocphongkhamThungan(new DtDmNhanVien());
return  getThuocphongkhamThungan();
}
    public DtDmNhanVien getThuocphongkhamThungan() {
        return thuocphongkhamThungan;
    }

    public void setThuocphongkhamThungan(DtDmNhanVien thuocphongkhamThungan) {
        this.thuocphongkhamThungan = thuocphongkhamThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getThuocphongkhamMa() != null ? getThuocphongkhamMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThuocPhongKham)) {
            return false;
        }
        ThuocPhongKham other = (ThuocPhongKham) object;
        if ((this.getThuocphongkhamMa() == null && other.getThuocphongkhamMa() != null) || (this.getThuocphongkhamMa() != null && !this.thuocphongkhamMa.equals(other.thuocphongkhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.ThuocPhongKham[thuocphongkhamMa=" + getThuocphongkhamMa() + "]";
    }

    public String getThuocphongkhamMaphieuh() {
        return thuocphongkhamMaphieuh;
    }

    public void setThuocphongkhamMaphieuh(String thuocphongkhamMaphieuh) {
        this.thuocphongkhamMaphieuh = thuocphongkhamMaphieuh;
    }

    public Date getThuocphongkhamNgaygioHoanThu() {
        return thuocphongkhamNgaygioHoanThu;
    }

    public void setThuocphongkhamNgaygioHoanThu(Date thuocphongkhamNgaygioHoanThu) {
        this.thuocphongkhamNgaygioHoanThu = thuocphongkhamNgaygioHoanThu;
    }

    public DtDmNhanVien getThuocphongkhamThunganHoan() {
        return thuocphongkhamThunganHoan;
    }

    public void setThuocphongkhamThunganHoan(DtDmNhanVien thuocphongkhamThunganHoan) {
        this.thuocphongkhamThunganHoan = thuocphongkhamThunganHoan;
    }

    public String getThuocphongkhamKyhieuHoan() {
        return thuocphongkhamKyhieuHoan;
    }

    public void setThuocphongkhamKyhieuHoan(String thuocphongkhamKyhieuHoan) {
        this.thuocphongkhamKyhieuHoan = thuocphongkhamKyhieuHoan;
    }

    public String getThuocphongkhamQuyenHoan() {
        return thuocphongkhamQuyenHoan;
    }

    public void setThuocphongkhamQuyenHoan(String thuocphongkhamQuyenHoan) {
        this.thuocphongkhamQuyenHoan = thuocphongkhamQuyenHoan;
    }

    public String getThuocphongkhamBienlaiHoan() {
        return thuocphongkhamBienlaiHoan;
    }

    public void setThuocphongkhamBienlaiHoan(String thuocphongkhamBienlaiHoan) {
        this.thuocphongkhamBienlaiHoan = thuocphongkhamBienlaiHoan;
    }

    public DtDmCum getThuocphongkhamCumHoan() {
        return thuocphongkhamCumHoan;
    }

    public void setThuocphongkhamCumHoan(DtDmCum thuocphongkhamCumHoan) {
        this.thuocphongkhamCumHoan = thuocphongkhamCumHoan;
    }

    public String getThuocphongkhamNgayuong() {
        return thuocphongkhamNgayuong;
    }

    /**
     * @param thuocphongkhamNgayuong the thuocphongkhamNgayuong to set
     */
    public void setThuocphongkhamNgayuong(String thuocphongkhamNgayuong) {
        this.thuocphongkhamNgayuong = thuocphongkhamNgayuong;
    }

    /**
     * @return the thuocphongkhamSoluonguong
     */
    public String getThuocphongkhamSoluonguong() {
        return thuocphongkhamSoluonguong;
    }

    /**
     * @param thuocphongkhamSoluonguong the thuocphongkhamSoluonguong to set
     */
    public void setThuocphongkhamSoluonguong(String thuocphongkhamSoluonguong) {
        this.thuocphongkhamSoluonguong = thuocphongkhamSoluonguong;
    }

    /**
     * @return the thuocphongkhamMaChidan
     */
    public String getThuocphongkhamMaChidan() {
        return thuocphongkhamMaChidan;
    }

    /**
     * @param thuocphongkhamMaChidan the thuocphongkhamMaChidan to set
     */
    public void setThuocphongkhamMaChidan(String thuocphongkhamMaChidan) {
        this.thuocphongkhamMaChidan = thuocphongkhamMaChidan;
    }



    /**
     * @return the thuocphongkhamTenloidan
     */
    public String getThuocphongkhamTenloidan() {
        return thuocphongkhamTenloidan;
    }

    /**
     * @param thuocphongkhamTenloidan the thuocphongkhamTenloidan to set
     */
    public void setThuocphongkhamTenloidan(String thuocphongkhamTenloidan) {
        this.thuocphongkhamTenloidan = thuocphongkhamTenloidan;
    }

     public Double getThuocphongkhamTienbntra() {
        return thuocphongkhamTienbntra;
    }

    public void setThuocphongkhamTienbntra(Double thuocphongkhamTienbntra) {
        this.thuocphongkhamTienbntra = thuocphongkhamTienbntra;
    }
    /**
     * @return the thuocphongkhamMaloidan
     */
    public String getThuocphongkhamMaloidan() {
        return thuocphongkhamMaloidan;
    }

    /**
     * @param thuocphongkhamMaloidan the thuocphongkhamMaloidan to set
     */
    public void setThuocphongkhamMaloidan(String thuocphongkhamMaloidan) {
        this.thuocphongkhamMaloidan = thuocphongkhamMaloidan;
    }

    public String getThuocphongkhamMaPhieuDT() {
        return thuocphongkhamMaPhieuDT;
    }

    public void setThuocphongkhamMaPhieuDT(String thuocphongkhamMaPhieuDT) {
        this.thuocphongkhamMaPhieuDT = thuocphongkhamMaPhieuDT;
    }

    public ThuocDongYNgoaiTru getThuocdongyNgoaiTru(boolean create) {
        if (create && thuocdongyNgoaiTru == null) {
            thuocdongyNgoaiTru = new ThuocDongYNgoaiTru();
        }
        return thuocdongyNgoaiTru;
    }

    public ThuocDongYNgoaiTru getThuocdongyNgoaiTru() {
        return thuocdongyNgoaiTru;
    }

    public void setThuocdongyNgoaiTru(ThuocDongYNgoaiTru thuocdongyNgoaiTru) {
        this.thuocdongyNgoaiTru = thuocdongyNgoaiTru;
    }

    public Integer getDmbaithuocMaso() {
        return dmbaithuocMaso;
    }

    public void setDmbaithuocMaso(Integer dmbaithuocMaso) {
        this.dmbaithuocMaso = dmbaithuocMaso;
    }
    public int getThuocphongkhamDongiatt() {
        return thuocphongkhamDongiatt;
    }

    public void setThuocphongkhamDongiatt(int thuocphongkhamDongiatt) {
        this.thuocphongkhamDongiatt = thuocphongkhamDongiatt;
    }

    public int getThuocphongkhamThanhtien() {
        return thuocphongkhamThanhtien;
    }

    public void setThuocphongkhamThanhtien(int thuocphongkhamThanhtien) {
        this.thuocphongkhamThanhtien = thuocphongkhamThanhtien;
    }
}
