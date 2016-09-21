/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "HSBA")
@NamedQueries({})
public class Hsba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "HSBA_SOVAOVIEN", nullable = false)
    private String hsbaSovaovien;
    @Column(name = "HSBA_IS_NOITRU")
    private Boolean hsbaIsNoitru;
    @Column(name = "HSBA_TYPE")
    private String hsbaType;
    @Column(name = "HSBA_NGAYGIOVAOV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbaNgaygiovaov;
    @Column(name = "HSBA_NGAYGIORAV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbaNgaygiorav;
    @Column(name = "HSBA_NGAYGIOTV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbaNgaygiotv;
    @Column(name = "HSBA_CHANDOANBD")
    private String hsbaChandoanbd;
    @Column(name = "HSBA_DIENGIAITUYENT")
    private String hsbaDiengiaituyent;
    @Column(name = "HSBA_DIENGIAIBD")
    private String hsbaDiengiaibd;
    @Column(name = "HSBA_THANHTOANTN")
    private Boolean hsbaThanhtoantn;
    @Column(name = "HSBA_NGUYENNHAN")
    private String hsbaNguyennhan;
    @Column(name = "HSBA_CAPCUU")
    private Boolean hsbaCapcuu;
    @Column(name = "HSBA_BENHSU")
    private String hsbaBenhsu;
    @Column(name = "HSBA_RUOUBIA")
    private Boolean hsbaRuoubia;
    @Column(name = "HSBA_TUVVONG24G")
    private Boolean hsbaTuvvong24g;
    @Column(name = "HSBA_MUON")
    private Boolean hsbaMuon;
    @Column(name = "HSBA_NANG")
    private Boolean hsbaNang;
    @Column(name = "HSBA_NGAYNOP")
    @Temporal(TemporalType.DATE)
    private Date hsbaNgaynop;
    @Column(name = "HSBA_NGAYGIAO")
    @Temporal(TemporalType.DATE)
    private Date hsbaNgaygiao;
    @Column(name = "HSBA_NHOMMAU")
    private String hsbaNhommau;
    @Column(name = "HSBA_RH")
    private String hsbaRh;
    @Column(name = "HSBA_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbaNgaygiocn;
    @Column(name = "HSBA_BAOTIN")
    private String hsbaBaotin;
    @Column(name = "HSBA_LYDOVAO")
    private String hsbaLydovao;
    @Column(name = "HSBA_DIENGIAITV")
    private String hsbaDiengiaitv;
    @Column(name = "HSBA_TITRCHVI")
    private String hsbaTitrchvi;
    @Column(name = "HSBA_HANHCHANH")
    private String hsbaHanhchanh;
    @Column(name = "HSBA_CHMON")
    private String hsbaChmon;
    @Column(name = "HSBA_CHANDOANRAV")
    private String hsbaChandoanrav;
    @Column(name = "HSBA_DONGY")
    private Boolean hsbaDongy;
    @Column(name = "HSBA_YEUCAU")
    private String hsbaYeuCau;
    @Column(name = "HSBA_NOI")
    private Boolean hsbaNoi;
    @Column(name = "HSBA_NGOAI")
    private Boolean hsbaNgoai;
    @Column(name = "TIEPDON_MA")
    private String tiepdonMa;
    
    @JoinColumn(name = "HSBA_DIEUTRI", referencedColumnName = "DMDIEUTRI_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmDieuTri hsbaDieutri;
    @JoinColumn(name = "HSBA_KETQUA", referencedColumnName = "DMKQDT_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKetQuaDieuTri hsbaKetqua;
    @JoinColumn(name = "HSBA_TUVONG", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmBenhIcd hsbaTuvong;
    @JoinColumn(name = "NOISINH_MA", referencedColumnName = "DTDMNOISINH_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNoiSinh noisinhMa;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBA_KHOADANGDT", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKhoa hsbaKhoadangdt;
    @JoinColumn(name = "HSBA_KHOADANGDT_CM", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKhoa hsbaKhoadangdtCm;
    @JoinColumn(name = "HSBA_KHOAVAOV", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKhoa hsbaKhoavaov;
    @JoinColumn(name = "HSBA_KHOARAV", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKhoa hsbaKhoarav;
    @JoinColumn(name = "HSBA_MACHDRAVIEN", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmBenhIcd hsbaMachdravien;
    @JoinColumn(name = "BENHNHAN_MA", referencedColumnName = "BENHNHAN_MA")
    @ManyToOne(fetch = FetchType.LAZY)
    private BenhNhan benhnhanMa;
    @JoinColumn(name = "HSBA_DONVIGOI", referencedColumnName = "DMBENHVIEN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmBenhVien hsbaDonvigoi;
    @JoinColumn(name = "HSBA_MACHDOANTUYENT", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmBenhIcd hsbaMachdoantuyent;
    @JoinColumn(name = "HSBA_MACHDOANBD", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmBenhIcd hsbaMachdoanbd;
    @JoinColumn(name = "TAINAN_MA", referencedColumnName = "DMTAINAN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmTaiNan tainanMa;
    @JoinColumn(name = "DMPTGTN_MASO", referencedColumnName = "DMPTGTN_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmPhuongThucGayTaiNan dmptgtnMaso;
    @JoinColumn(name = "DIADIEM_MA", referencedColumnName = "DMDIADIEM_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmDiaDiem diadiemMa;
    @JoinColumn(name = "DOITUONG_MA", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmDoiTuong doituongMa;
    @JoinColumn(name = "HSBA_TIEPNHAN", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmKhoa hsbaTiepnhan;
    @JoinColumn(name = "HSBA_MASOGIAO", referencedColumnName = "HSBANOP_MA")
    @ManyToOne(fetch = FetchType.LAZY)
    private HsbaNop hsbaMasogiao;
    @JoinColumn(name = "TANG_DANGDT", referencedColumnName = "DMTANG_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmTang tangDangdt;

    public Hsba() {
    }

    public Hsba(String hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public String getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(String hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public Boolean getHsbaIsNoitru() {
        return hsbaIsNoitru;
    }

    public void setHsbaIsNoitru(Boolean hsbaIsNoitru) {
        this.hsbaIsNoitru = hsbaIsNoitru;
    }

    public String getHsbaType() {
        return hsbaType;
    }

    public void setHsbaType(String hsbaType) {
        this.hsbaType = hsbaType;
    }

    public Date getHsbaNgaygiovaov() {
        return hsbaNgaygiovaov;
    }

    public void setHsbaNgaygiovaov(Date hsbaNgaygiovaov) {
        this.hsbaNgaygiovaov = hsbaNgaygiovaov;
    }

    public Date getHsbaNgaygiorav() {
        return hsbaNgaygiorav;
    }

    public void setHsbaNgaygiorav(Date hsbaNgaygiorav) {
        this.hsbaNgaygiorav = hsbaNgaygiorav;
    }

    public Date getHsbaNgaygiotv() {
        return hsbaNgaygiotv;
    }

    public void setHsbaNgaygiotv(Date hsbaNgaygiotv) {
        this.hsbaNgaygiotv = hsbaNgaygiotv;
    }

    public String getHsbaChandoanbd() {
        return hsbaChandoanbd;
    }

    public void setHsbaChandoanbd(String hsbaChandoanbd) {
        this.hsbaChandoanbd = hsbaChandoanbd;
    }

    public String getHsbaDiengiaituyent() {
        return hsbaDiengiaituyent;
    }

    public void setHsbaDiengiaituyent(String hsbaDiengiaituyent) {
        this.hsbaDiengiaituyent = hsbaDiengiaituyent;
    }

    public String getHsbaDiengiaibd() {
        return hsbaDiengiaibd;
    }

    public void setHsbaDiengiaibd(String hsbaDiengiaibd) {
        this.hsbaDiengiaibd = hsbaDiengiaibd;
    }

    public Boolean getHsbaThanhtoantn() {
        return hsbaThanhtoantn;
    }

    public void setHsbaThanhtoantn(Boolean hsbaThanhtoantn) {
        this.hsbaThanhtoantn = hsbaThanhtoantn;
    }

    public String getHsbaNguyennhan() {
        return hsbaNguyennhan;
    }

    public void setHsbaNguyennhan(String hsbaNguyennhan) {
        this.hsbaNguyennhan = hsbaNguyennhan;
    }

    public Boolean getHsbaCapcuu() {
        return hsbaCapcuu;
    }

    public void setHsbaCapcuu(Boolean hsbaCapcuu) {
        this.hsbaCapcuu = hsbaCapcuu;
    }

    public String getHsbaBenhsu() {
        return hsbaBenhsu;
    }

    public void setHsbaBenhsu(String hsbaBenhsu) {
        this.hsbaBenhsu = hsbaBenhsu;
    }

    public Boolean getHsbaRuoubia() {
        return hsbaRuoubia;
    }

    public void setHsbaRuoubia(Boolean hsbaRuoubia) {
        this.hsbaRuoubia = hsbaRuoubia;
    }

    public Boolean getHsbaTuvvong24g() {
        return hsbaTuvvong24g;
    }

    public void setHsbaTuvvong24g(Boolean hsbaTuvvong24g) {
        this.hsbaTuvvong24g = hsbaTuvvong24g;
    }

    public Boolean getHsbaMuon() {
        return hsbaMuon;
    }

    public void setHsbaMuon(Boolean hsbaMuon) {
        this.hsbaMuon = hsbaMuon;
    }

    public Boolean getHsbaNang() {
        return hsbaNang;
    }

    public void setHsbaNang(Boolean hsbaNang) {
        this.hsbaNang = hsbaNang;
    }

    public Date getHsbaNgaynop() {
        return hsbaNgaynop;
    }

    public void setHsbaNgaynop(Date hsbaNgaynop) {
        this.hsbaNgaynop = hsbaNgaynop;
    }

    public Date getHsbaNgaygiao() {
        return hsbaNgaygiao;
    }

    public void setHsbaNgaygiao(Date hsbaNgaygiao) {
        this.hsbaNgaygiao = hsbaNgaygiao;
    }

    public String getHsbaNhommau() {
        return hsbaNhommau;
    }

    public void setHsbaNhommau(String hsbaNhommau) {
        this.hsbaNhommau = hsbaNhommau;
    }

    public String getHsbaRh() {
        return hsbaRh;
    }

    public void setHsbaRh(String hsbaRh) {
        this.hsbaRh = hsbaRh;
    }

    public Date getHsbaNgaygiocn() {
        return hsbaNgaygiocn;
    }

    public void setHsbaNgaygiocn(Date hsbaNgaygiocn) {
        this.hsbaNgaygiocn = hsbaNgaygiocn;
    }

    public String getHsbaBaotin() {
        return hsbaBaotin;
    }

    public void setHsbaBaotin(String hsbaBaotin) {
        this.hsbaBaotin = hsbaBaotin;
    }

    public String getHsbaLydovao() {
        return hsbaLydovao;
    }

    public void setHsbaLydovao(String hsbaLydovao) {
        this.hsbaLydovao = hsbaLydovao;
    }

    public String getHsbaDiengiaitv() {
        return hsbaDiengiaitv;
    }

    public void setHsbaDiengiaitv(String hsbaDiengiaitv) {
        this.hsbaDiengiaitv = hsbaDiengiaitv;
    }

    public String getHsbaTitrchvi() {
        return hsbaTitrchvi;
    }

    public void setHsbaTitrchvi(String hsbaTitrchvi) {
        this.hsbaTitrchvi = hsbaTitrchvi;
    }

    public String getHsbaHanhchanh() {
        return hsbaHanhchanh;
    }

    public void setHsbaHanhchanh(String hsbaHanhchanh) {
        this.hsbaHanhchanh = hsbaHanhchanh;
    }

    public String getHsbaChmon() {
        return hsbaChmon;
    }

    public void setHsbaChmon(String hsbaChmon) {
        this.hsbaChmon = hsbaChmon;
    }

    public String getHsbaChandoanrav() {
        return hsbaChandoanrav;
    }

    public void setHsbaChandoanrav(String hsbaChandoanrav) {
        this.hsbaChandoanrav = hsbaChandoanrav;
    }

    public Boolean getHsbaDongy() {
        return hsbaDongy;
    }

    public void setHsbaDongy(Boolean hsbaDongy) {
        this.hsbaDongy = hsbaDongy;
    }

    public String getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(String tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public DmDieuTri getHsbaDieutri(boolean create) {
        if (create && hsbaDieutri == null) {
            hsbaDieutri = new DmDieuTri();
        }
        return hsbaDieutri;
    }

    public DmDieuTri getHsbaDieutri() {
        return hsbaDieutri;
    }

    public void setHsbaDieutri(DmDieuTri hsbaDieutri) {
        this.hsbaDieutri = hsbaDieutri;
    }

    public DmKetQuaDieuTri getHsbaKetqua(boolean create) {
        if (create && hsbaKetqua == null) {
            hsbaKetqua = new DmKetQuaDieuTri();
        }
        return hsbaKetqua;
    }

    public DmKetQuaDieuTri getHsbaKetqua() {
        return hsbaKetqua;
    }

    public void setHsbaKetqua(DmKetQuaDieuTri hsbaKetqua) {
        this.hsbaKetqua = hsbaKetqua;
    }

    public DmBenhIcd getHsbaTuvong(boolean create) {
        if (create && hsbaTuvong == null) {
            hsbaTuvong = new DmBenhIcd();
        }
        return hsbaTuvong;
    }

    public DmBenhIcd getHsbaTuvong() {
        return hsbaTuvong;
    }

    public void setHsbaTuvong(DmBenhIcd hsbaTuvong) {
        this.hsbaTuvong = hsbaTuvong;
    }

    public DtDmNoiSinh getNoisinhMa(boolean create) {
        if (create && noisinhMa == null) {
            noisinhMa = new DtDmNoiSinh();
        }
        return noisinhMa;
    }

    public DtDmNoiSinh getNoisinhMa() {
        return noisinhMa;
    }

    public void setNoisinhMa(DtDmNoiSinh noisinhMa) {
        this.noisinhMa = noisinhMa;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
        if (create && nhanvienMa == null) {
            nhanvienMa = new DtDmNhanVien();
        }
        return nhanvienMa;
    }

    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public DmKhoa getHsbaKhoadangdtCm(boolean create) {
        if (create && hsbaKhoadangdtCm == null) {
            hsbaKhoadangdtCm = new DmKhoa();
        }
        return hsbaKhoadangdtCm;
    }

    public DmKhoa getHsbaKhoadangdtCm() {
        return hsbaKhoadangdtCm;
    }

    public void setHsbaKhoadangdtCm(DmKhoa hsbaKhoadangdtCm) {
        this.hsbaKhoadangdtCm = hsbaKhoadangdtCm;
    }

    public DmKhoa getHsbaKhoadangdt(boolean create) {
        if (create && hsbaKhoadangdt == null) {
            hsbaKhoadangdt = new DmKhoa();
        }
        return hsbaKhoadangdt;
    }

    public DmKhoa getHsbaKhoadangdt() {
        return hsbaKhoadangdt;
    }

    public void setHsbaKhoadangdt(DmKhoa hsbaKhoadangdt) {
        this.hsbaKhoadangdt = hsbaKhoadangdt;
    }

    public DmKhoa getHsbaKhoavaov(boolean create) {
        if (create && hsbaKhoavaov == null) {
            hsbaKhoavaov = new DmKhoa();
        }
        return hsbaKhoavaov;
    }

    public DmKhoa getHsbaKhoavaov() {
        return hsbaKhoavaov;
    }

    public void setHsbaKhoavaov(DmKhoa hsbaKhoavaov) {
        this.hsbaKhoavaov = hsbaKhoavaov;
    }

    public DmKhoa getHsbaKhoarav(boolean create) {
        if (create && hsbaKhoarav == null) {
            hsbaKhoarav = new DmKhoa();
        }
        return hsbaKhoarav;
    }

    public DmKhoa getHsbaKhoarav() {
        return hsbaKhoarav;
    }

    public void setHsbaKhoarav(DmKhoa hsbaKhoarav) {
        this.hsbaKhoarav = hsbaKhoarav;
    }

    public DmBenhIcd getHsbaMachdravien(boolean create) {
        if (create && hsbaMachdravien == null) {
            hsbaMachdravien = new DmBenhIcd();
        }
        return hsbaMachdravien;
    }

    public DmBenhIcd getHsbaMachdravien() {
        return hsbaMachdravien;
    }

    public void setHsbaMachdravien(DmBenhIcd hsbaMachdravien) {
        this.hsbaMachdravien = hsbaMachdravien;
    }

    public BenhNhan getBenhnhanMa(boolean create) {
        if (create && benhnhanMa == null) {
            benhnhanMa = new BenhNhan();
        }
        return benhnhanMa;
    }

    public BenhNhan getBenhnhanMa() {
        return benhnhanMa;
    }

    public void setBenhnhanMa(BenhNhan benhnhanMa) {
        this.benhnhanMa = benhnhanMa;
    }

    public DmBenhVien getHsbaDonvigoi(boolean create) {
        if (create && hsbaDonvigoi == null) {
            hsbaDonvigoi = new DmBenhVien();
        }
        return hsbaDonvigoi;
    }

    public DmBenhVien getHsbaDonvigoi() {
        return hsbaDonvigoi;
    }

    public void setHsbaDonvigoi(DmBenhVien hsbaDonvigoi) {
        this.hsbaDonvigoi = hsbaDonvigoi;
    }

    public DmBenhIcd getHsbaMachdoantuyent(boolean create) {
        if (create && hsbaMachdoantuyent == null) {
            hsbaMachdoantuyent = new DmBenhIcd();
        }
        return hsbaMachdoantuyent;
    }

    public DmBenhIcd getHsbaMachdoantuyent() {
        return hsbaMachdoantuyent;
    }

    public void setHsbaMachdoantuyent(DmBenhIcd hsbaMachdoantuyent) {
        this.hsbaMachdoantuyent = hsbaMachdoantuyent;
    }

    public DmBenhIcd getHsbaMachdoanbd(boolean create) {
        if (create && hsbaMachdoanbd == null) {
            hsbaMachdoanbd = new DmBenhIcd();
        }
        return hsbaMachdoanbd;
    }

    public DmBenhIcd getHsbaMachdoanbd() {
        return hsbaMachdoanbd;
    }

    public void setHsbaMachdoanbd(DmBenhIcd hsbaMachdoanbd) {
        this.hsbaMachdoanbd = hsbaMachdoanbd;
    }

    public DmTaiNan getTainanMa(boolean create) {
        if (create && tainanMa == null) {
            tainanMa = new DmTaiNan();
        }
        return tainanMa;
    }

    public DmTaiNan getTainanMa() {
        return tainanMa;
    }

    public void setTainanMa(DmTaiNan tainanMa) {
        this.tainanMa = tainanMa;
    }

    public DmPhuongThucGayTaiNan getDmptgtnMaso(boolean create) {
        if (create && dmptgtnMaso == null) {
            dmptgtnMaso = new DmPhuongThucGayTaiNan();
        }
        return dmptgtnMaso;
    }

    public DmPhuongThucGayTaiNan getDmptgtnMaso() {
        return dmptgtnMaso;
    }

    public void setDmptgtnMaso(DmPhuongThucGayTaiNan dmptgtnMaso) {
        this.dmptgtnMaso = dmptgtnMaso;
    }

    public DmDiaDiem getDiadiemMa(boolean create) {
        if (create && diadiemMa == null) {
            diadiemMa = new DmDiaDiem();
        }
        return diadiemMa;
    }

    public DmDiaDiem getDiadiemMa() {
        return diadiemMa;
    }

    public void setDiadiemMa(DmDiaDiem diadiemMa) {
        this.diadiemMa = diadiemMa;
    }

    public DmDoiTuong getDoituongMa(boolean create) {
        if (create && doituongMa == null) {
            doituongMa = new DmDoiTuong();
        }
        return doituongMa;
    }

    public DmDoiTuong getDoituongMa() {
        return doituongMa;
    }

    public void setDoituongMa(DmDoiTuong doituongMa) {
        this.doituongMa = doituongMa;
    }

    public DmKhoa getHsbaTiepnhan(boolean create) {
        if (create && hsbaTiepnhan == null) {
            hsbaTiepnhan = new DmKhoa();
        }
        return hsbaTiepnhan;
    }

    public DmKhoa getHsbaTiepnhan() {
        return hsbaTiepnhan;
    }

    public void setHsbaTiepnhan(DmKhoa hsbaTiepnhan) {
        this.hsbaTiepnhan = hsbaTiepnhan;
    }

    public HsbaNop getHsbaMasogiao(boolean create) {
        if (create && hsbaMasogiao == null) {
            hsbaMasogiao = new HsbaNop();
        }
        return hsbaMasogiao;
    }

    public HsbaNop getHsbaMasogiao() {
        return hsbaMasogiao;
    }

    public void setHsbaMasogiao(HsbaNop hsbaMasogiao) {
        this.hsbaMasogiao = hsbaMasogiao;
    }

    public DmTang getTangDangdt() {
        return tangDangdt;
    }

    public DmTang getTangDangdt(boolean create) {
        if (create && tangDangdt == null) {
            tangDangdt = new DmTang();
        }
        return tangDangdt;
    }

    public void setTangDangdt(DmTang tangDangdt) {
        this.tangDangdt = tangDangdt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbaSovaovien != null ? hsbaSovaovien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hsba)) {
            return false;
        }
        Hsba other = (Hsba) object;
        if ((this.hsbaSovaovien == null && other.hsbaSovaovien != null) || (this.hsbaSovaovien != null && !this.hsbaSovaovien.equals(other.hsbaSovaovien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.Hsba[hsbaSovaovien=" + hsbaSovaovien + "]";
    }

    public Boolean getHsbaNoi() {
        return hsbaNoi;
    }

    public void setHsbaNoi(Boolean hsbaNoi) {
        this.hsbaNoi = hsbaNoi;
    }

    public Boolean getHsbaNgoai() {
        return hsbaNgoai;
    }

    public void setHsbaNgoai(Boolean hsbaNgoai) {
        this.hsbaNgoai = hsbaNgoai;
    }

    public String getHsbaYeuCau() {
        return hsbaYeuCau;
    }

    public void setHsbaYeuCau(String hsbaYeuCau) {
        this.hsbaYeuCau = hsbaYeuCau;
    }
}
