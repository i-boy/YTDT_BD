/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "CLS_MO")
@NamedQueries({})
public class ClsMo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_MO_CLSMO_MA")
    @SequenceGenerator(name = "CLS_MO_CLSMO_MA", sequenceName = "CLS_MO_CLSMO_MA_SEQ", allocationSize = 1)
    @Column(name = "CLSMO_MA", nullable = false)
    private Integer clsmoMa;
    @Column(name = "CLSMO_MAPHIEU")
    private String clsmoMaphieu;
    @Column(name = "CLSMO_NGAY")
    @Temporal(TemporalType.DATE)
    private Date clsmoNgay;
    @Column(name = "CLSMO_LOAI")
    private String clsmoLoai;
    @Column(name = "CLSMO_YEUCAU")
    private Boolean clsmoYeucau;
    @Column(name = "CLSMO_KHONGTHU")
    private Boolean clsmoKhongthu;
    @Column(name = "CLSMO_MIEN")
    private Boolean clsmoMien;
    @Column(name = "CLSMO_NHI")
    private Boolean clsmoNhi;
    @Column(name = "CLSMO_LAO")
    private Boolean clsmoLao;
    @Column(name = "CLSMO_DICHVU")
    private Boolean clsmoDichvu;
    @Column(name = "CLSMO_KTCAO")
    private Boolean clsmoKtcao;
    @Column(name = "CLSMO_LAN")
    private Short clsmoLan;
    @Column(name = "CLSMO_TRA")
    private Short clsmoTra;
    @Column(name = "CLSMO_PHANDV")
    private Double clsmoPhandv;
    @Column(name = "CLSMO_DONGIA")
    private Double clsmoDongia;
    @Column(name = "CLSMO_DONGIABH")
    private Double clsmoDongiabh;
    @Column(name = "CLSMO_DONGIADV")
    private Double clsmoDongiadv;
    @Column(name = "CLSMO_DONGIATP")
    private Double clsmoDongiatp;
    @Column(name = "CLSMO_TYLEBH")
    private Short clsmoTylebh;
    @Column(name = "CLSMO_TYLEBHKTC")
    private Short clsmoTylebhktc;
    @Column(name = "CLSMO_NGAYGIOCN")
    private Double clsmoNgaygiocn;
    @Column(name = "CLSMO_MADUYET")
    private String clsmoMaduyet;
    @Column(name = "CLSMO_CHANDOAN")
    private String clsmoChandoan;
    @Column(name = "CLSMO_KETQUA")
    private String clsmoKetqua;
    @Column(name = "CLSMO_DUONGTINH")
    private String clsmoDuongtinh;
    @Column(name = "CLSMO_TIEUBAN")
    private Short clsmoTieuban;
    @Column(name = "CLSMO_LAM")
    private Short clsmoLam;
    @Column(name = "CLSMO_ORD")
    private Short clsmoOrd;
    @Column(name = "CLSMO_DATT")
    private Boolean clsmoDatt;
    @Column(name = "CLSMO_NDM")
    private Boolean clsmoNDM;
    @Column(name = "CLSMO_GHICHU")
    private String clsmoGhichu;
    @Column(name = "CLSMO_DONGIABNTRA")
    private Double clsmoDongiabntra;
    @Column(name = "CLSMO_LOAI2")
    private String clsmoLoai2;
    @Column(name = "CLSMO_SOML")
    private Integer clsmoSoml;
    @Column(name = "CLSMO_DONGIATT", nullable = false)
    private int clsmoDongiatt;
    @Column(name = "CLSMO_THANHTIEN", nullable = false)
    private int clsmoThanhtien;
    @Column(name = "CLSMO_TH")
    private Boolean clsmoTh;
    @JoinColumn(name = "HSBAKHOA_MASO", referencedColumnName = "HSBAKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private HsbaKhoa hsbaKhoa;
    @JoinColumn(name = "CLSMO_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKhoa clsmoKhoa;
    @JoinColumn(name = "CLSMO_MAHANG", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmClsBangGia clsmoMahang;
    @JoinColumn(name = "CLSMO_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNhanVien clsmoBacsi;
    @JoinColumn(name = "CLSMO_KHOATHUCHIEN", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKhoa clsmoKhoathuchien;
    @JoinColumn(name = "CLSMO_THUCHIEN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNhanVien clsmoThuchien;
    @JoinColumn(name = "CLSMO_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNhanVien clsmoNhanviencn;
    @JoinColumn(name = "CLSMO_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNhanVien clsmoThungan;
    @JoinColumn(name = "CLSMO_LOAICLS", referencedColumnName = "DTDMCLS_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmCls clsmoLoaicls;

    public ClsMo() {
    }

    public ClsMo(Integer clsmoMa) {
        this.clsmoMa = clsmoMa;
    }

    public Integer getClsmoMa() {
        return clsmoMa;
    }

    public void setClsmoMa(Integer clsmoMa) {
        this.clsmoMa = clsmoMa;
    }

    public String getClsmoMaphieu() {
        return clsmoMaphieu;
    }

    public void setClsmoMaphieu(String clsmoMaphieu) {
        this.clsmoMaphieu = clsmoMaphieu;
    }

    public Date getClsmoNgay() {
        return clsmoNgay;
    }

    public void setClsmoNgay(Date clsmoNgay) {
        this.clsmoNgay = clsmoNgay;
    }

    public String getClsmoLoai() {
        return clsmoLoai;
    }

    public void setClsmoLoai(String clsmoLoai) {
        this.clsmoLoai = clsmoLoai;
    }

    public Boolean getClsmoYeucau() {
        return clsmoYeucau;
    }

    public void setClsmoYeucau(Boolean clsmoYeucau) {
        this.clsmoYeucau = clsmoYeucau;
    }

    public Boolean getClsmoKhongthu() {
        return clsmoKhongthu;
    }

    public void setClsmoKhongthu(Boolean clsmoKhongthu) {
        this.clsmoKhongthu = clsmoKhongthu;
    }

    public Boolean getClsmoMien() {
        return clsmoMien;
    }

    public void setClsmoMien(Boolean clsmoMien) {
        this.clsmoMien = clsmoMien;
    }

    public Boolean getClsmoNhi() {
        return clsmoNhi;
    }

    public void setClsmoNhi(Boolean clsmoNhi) {
        this.clsmoNhi = clsmoNhi;
    }

    // Dung de xac dinh co dieu chinh gia CLS hay khong
    public Boolean getClsmoLao() {
        return clsmoLao;
    }

    public void setClsmoLao(Boolean clsmoLao) {
        this.clsmoLao = clsmoLao;
    }

    public Boolean getClsmoDichvu() {
        return clsmoDichvu;
    }

    public void setClsmoDichvu(Boolean clsmoDichvu) {
        this.clsmoDichvu = clsmoDichvu;
    }

    public Boolean getClsmoKtcao() {
        return clsmoKtcao;
    }

    public void setClsmoKtcao(Boolean clsmoKtcao) {
        this.clsmoKtcao = clsmoKtcao;
    }

    public Short getClsmoLan() {
        return clsmoLan;
    }

    public void setClsmoLan(Short clsmoLan) {
        this.clsmoLan = clsmoLan;
    }

    public Short getClsmoTra() {
        return clsmoTra;
    }

    public void setClsmoTra(Short clsmoTra) {
        this.clsmoTra = clsmoTra;
    }

    public Double getClsmoPhandv() {
        return clsmoPhandv;
    }

    public void setClsmoPhandv(Double clsmoPhandv) {
        this.clsmoPhandv = clsmoPhandv;
    }

    public Double getClsmoDongia() {
        return clsmoDongia;
    }

    public void setClsmoDongia(Double clsmoDongia) {
        this.clsmoDongia = clsmoDongia;
    }

    public Double getClsmoDongiabh() {
        return clsmoDongiabh;
    }

    public void setClsmoDongiabh(Double clsmoDongiabh) {
        this.clsmoDongiabh = clsmoDongiabh;
    }

    public Double getClsmoDongiadv() {
        return clsmoDongiadv;
    }

    public void setClsmoDongiadv(Double clsmoDongiadv) {
        this.clsmoDongiadv = clsmoDongiadv;
    }

    public Double getClsmoDongiatp() {
        return clsmoDongiatp;
    }

    public void setClsmoDongiatp(Double clsmoDongiatp) {
        this.clsmoDongiatp = clsmoDongiatp;
    }

    public Short getClsmoTylebh() {
        return clsmoTylebh;
    }

    public void setClsmoTylebh(Short clsmoTylebh) {
        this.clsmoTylebh = clsmoTylebh;
    }

    public Short getClsmoTylebhktc() {
        return clsmoTylebhktc;
    }

    public void setClsmoTylebhktc(Short clsmoTylebhktc) {
        this.clsmoTylebhktc = clsmoTylebhktc;
    }

    public Double getClsmoNgaygiocn() {
        return clsmoNgaygiocn;
    }

    public void setClsmoNgaygiocn(Double clsmoNgaygiocn) {
        this.clsmoNgaygiocn = clsmoNgaygiocn;
    }

    public String getClsmoMaduyet() {
        return clsmoMaduyet;
    }

    public void setClsmoMaduyet(String clsmoMaduyet) {
        this.clsmoMaduyet = clsmoMaduyet;
    }

    public String getClsmoChandoan() {
        return clsmoChandoan;
    }

    public void setClsmoChandoan(String clsmoChandoan) {
        this.clsmoChandoan = clsmoChandoan;
    }

    public String getClsmoKetqua() {
        return clsmoKetqua;
    }

    public void setClsmoKetqua(String clsmoKetqua) {
        this.clsmoKetqua = clsmoKetqua;
    }

    public String getClsmoDuongtinh() {
        return clsmoDuongtinh;
    }

    public void setClsmoDuongtinh(String clsmoDuongtinh) {
        this.clsmoDuongtinh = clsmoDuongtinh;
    }

    public Short getClsmoTieuban() {
        return clsmoTieuban;
    }

    public void setClsmoTieuban(Short clsmoTieuban) {
        this.clsmoTieuban = clsmoTieuban;
    }

    public Short getClsmoLam() {
        return clsmoLam;
    }

    public void setClsmoLam(Short clsmoLam) {
        this.clsmoLam = clsmoLam;
    }

    public Short getClsmoOrd() {
        return clsmoOrd;
    }

    public void setClsmoOrd(Short clsmoOrd) {
        this.clsmoOrd = clsmoOrd;
    }

    public DmKhoa getClsmoKhoa(boolean create) {
        if (create && clsmoKhoa == null) {
            clsmoKhoa = new DmKhoa();
        }
        return clsmoKhoa;
    }

    public DmKhoa getClsmoKhoa() {
        return clsmoKhoa;
    }

    public void setClsmoKhoa(DmKhoa clsmoKhoa) {
        this.clsmoKhoa = clsmoKhoa;
    }

    public DtDmClsBangGia getClsmoMahang(boolean create) {
        if (create && clsmoMahang == null) {
            clsmoMahang = new DtDmClsBangGia();
        }
        return clsmoMahang;
    }

    public DtDmClsBangGia getClsmoMahang() {
        return clsmoMahang;
    }

    public void setClsmoMahang(DtDmClsBangGia clsmoMahang) {
        this.clsmoMahang = clsmoMahang;
    }

    public DtDmNhanVien getClsmoBacsi(boolean create) {
        if (create && clsmoBacsi == null) {
            clsmoBacsi = new DtDmNhanVien();
        }
        return clsmoBacsi;
    }

    public DtDmNhanVien getClsmoBacsi() {
        return clsmoBacsi;
    }

    public void setClsmoBacsi(DtDmNhanVien clsmoBacsi) {
        this.clsmoBacsi = clsmoBacsi;
    }

    public DmKhoa getClsmoKhoathuchien(boolean create) {
        if (create && clsmoKhoathuchien == null) {
            clsmoKhoathuchien = new DmKhoa();
        }
        return clsmoKhoathuchien;
    }

    public DmKhoa getClsmoKhoathuchien() {
        return clsmoKhoathuchien;
    }

    public void setClsmoKhoathuchien(DmKhoa clsmoKhoathuchien) {
        this.clsmoKhoathuchien = clsmoKhoathuchien;
    }

    public DtDmNhanVien getClsmoThuchien(boolean create) {
        if (create && clsmoThuchien == null) {
            clsmoThuchien = new DtDmNhanVien();
        }
        return clsmoThuchien;
    }

    public DtDmNhanVien getClsmoThuchien() {
        return clsmoThuchien;
    }

    public void setClsmoThuchien(DtDmNhanVien clsmoThuchien) {
        this.clsmoThuchien = clsmoThuchien;
    }

    public DtDmNhanVien getClsmoNhanviencn(boolean create) {
        if (create && clsmoNhanviencn == null) {
            clsmoNhanviencn = new DtDmNhanVien();
        }
        return clsmoNhanviencn;
    }

    public DtDmNhanVien getClsmoNhanviencn() {
        return clsmoNhanviencn;
    }

    public void setClsmoNhanviencn(DtDmNhanVien clsmoNhanviencn) {
        this.clsmoNhanviencn = clsmoNhanviencn;
    }

    public DtDmNhanVien getClsmoThungan(boolean create) {
        if (create && clsmoThungan == null) {
            clsmoThungan = new DtDmNhanVien();
        }
        return clsmoThungan;
    }

    public DtDmNhanVien getClsmoThungan() {
        return clsmoThungan;
    }

    public void setClsmoThungan(DtDmNhanVien clsmoThungan) {
        this.clsmoThungan = clsmoThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clsmoMa != null ? clsmoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClsMo)) {
            return false;
        }
        ClsMo other = (ClsMo) object;
        if ((this.clsmoMa == null && other.clsmoMa != null) || (this.clsmoMa != null && !this.clsmoMa.equals(other.clsmoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.ClsMo[clsmoMa=" + clsmoMa + "]";
    }

    public DtDmCls getClsmoLoaicls(boolean create) {
        if (create && clsmoLoaicls == null) {
            clsmoLoaicls = new DtDmCls();
        }
        return clsmoLoaicls;
    }

    public DtDmCls getClsmoLoaicls() {
        return clsmoLoaicls;
    }

    public void setClsmoLoaicls(DtDmCls clsmoLoaicls) {
        this.clsmoLoaicls = clsmoLoaicls;
    }

    public Boolean getClsmoDatt() {
        return clsmoDatt;
    }

    public void setClsmoDatt(Boolean clsmoDatt) {
        this.clsmoDatt = clsmoDatt;
    }

    public Boolean getClsmoNDM() {
        return clsmoNDM;
    }

    public void setClsmoNDM(Boolean clsmoNDM) {
        this.clsmoNDM = clsmoNDM;
    }

    public String getClsmoGhichu() {
        return clsmoGhichu;
    }

    public void setClsmoGhichu(String clsmoGhichu) {
        this.clsmoGhichu = clsmoGhichu;
    }

    public Double getClsmoDongiabntra() {
        return clsmoDongiabntra;
    }

    public void setClsmoDongiabntra(Double clsmoDongiabntra) {
        this.clsmoDongiabntra = clsmoDongiabntra;
    }

    public String getClsmoLoai2() {
        return clsmoLoai2;
    }

    public void setClsmoLoai2(String clsmoLoai2) {
        this.clsmoLoai2 = clsmoLoai2;
    }

    public Integer getClsmoSoml() {
        return clsmoSoml;
    }

    public void setClsmoSoml(Integer clsmoSoml) {
        this.clsmoSoml = clsmoSoml;
    }

    public HsbaKhoa getHsbaKhoa() {
        return hsbaKhoa;
    }

    public HsbaKhoa getHsbaKhoa(boolean create) {
        if (create && hsbaKhoa == null) {
            hsbaKhoa = new HsbaKhoa();
        }
        return hsbaKhoa;
    }

    public void setHsbaKhoa(HsbaKhoa hsbaKhoa) {
        this.hsbaKhoa = hsbaKhoa;
    }

    public int getClsmoDongiatt() {
        return clsmoDongiatt;
    }

    public void setClsmoDongiatt(int clsmoDongiatt) {
        this.clsmoDongiatt = clsmoDongiatt;
    }

    public int getClsmoThanhtien() {
        return clsmoThanhtien;
    }

    public void setClsmoThanhtien(int clsmoThanhtien) {
        this.clsmoThanhtien = clsmoThanhtien;
    }

    public Boolean getClsmoTh() {
        return clsmoTh;
    }

    public void setClsmoTh(Boolean clsmoTh) {
        this.clsmoTh = clsmoTh;
    }
}


