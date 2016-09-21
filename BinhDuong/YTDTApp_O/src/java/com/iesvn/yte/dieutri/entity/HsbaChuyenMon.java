/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKetQuaDieuTri;
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
//import javax.persistence.OneToMany;
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
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHUYEN_MON")
@NamedQueries({})
public class HsbaChuyenMon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHUYEN_MON")
    @SequenceGenerator(name = "HSBA_CHUYEN_MON", sequenceName = "HSBA_CHUYEN_MON_HSBACM_MA_SEQ", allocationSize = 1)
    @Column(name = "HSBACM_MA")
    private Integer hsbacmMa;
    @Column(name = "HSBACM_SINH")
    private Short hsbacmSinh;
    @Column(name = "HSBACM_SOM")
    private Short hsbacmSom;
    @Column(name = "HSBACM_SAY")
    private Short hsbacmSay;
    @Column(name = "HSBACM_SONG")
    private Short hsbacmSong;
    @Column(name = "HSBACM_KSTC")
    private Boolean hsbacmKstc;
    @Column(name = "HSBACM_LATHUTHUAT")
    private Boolean hsbacmLathuthuat;
    @Column(name = "HSBACM_LAPHAUTHUAT")
    private Boolean hsbacmLaphauthuat;
    @Column(name = "HSBACM_LASINH")
    private Boolean hsbacmLasinh;
    @Column(name = "HSBACM_HAMAX")
    private Integer hsbacmHamax;
    @Column(name = "HSBACM_HAMIN")
    private Integer hsbacmHamin;
    @Column(name = "HSBACM_NGAYGIOVAOK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbacmNgaygiovaok;
    @Column(name = "HSBACM_NGAYGIORAK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbacmNgaygiorak;
    @Column(name = "HSBACM_SONGAYDT")
    private Integer hsbacmSongaydt;
    @Column(name = "HSBACM_BENHSU")
    private String hsbacmBenhsu;
    @Column(name = "HSBACM_DIENGIAIBC")
    private String hsbacmDiengiaibc;
    @Column(name = "HSBACM_BIENCHUNG")
    private String hsbacmBienchung;
    @Column(name = "HSBACM_DIEUTRI")
    private String hsbacmDieutri;
    @Column(name = "HSBACM_DIUNG")
    private String hsbacmDiung;
    @Column(name = "HSBACM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbacmNgaygiocn;
    @Column(name = "HSBACM_HUONGDIEUTRI")
    private String hsbacmHuongdieutri;

     @Column(name = "HSBACM_LAN")
    private Integer hsbacmlan;
    @Column(name = "HSBACM_SOGIUONG")
    private String hsbacmSogiuong;
    @Column(name = "HSBACM_SOBUONG")
    private String hsbacmSobuong;

    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "KHOA_MA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa khoaMa;
    @JoinColumn(name = "HSBACM_BENHCHINH", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbacmBenhchinh;
    @JoinColumn(name = "NOISINH_MA", referencedColumnName = "DTDMNOISINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNoiSinh noisinhMa;
    @JoinColumn(name = "HSBACM_BENHPHU", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbacmBenhphu;
    @JoinColumn(name = "HSBACM_BENHPHU2", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbacmBenhphu2;
    @JoinColumn(name = "HSBACM_BENHPHU3", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbacmBenhphu3;
    @JoinColumn(name = "HSBACM_BENHPHU4", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbacmBenhphu4;
    @JoinColumn(name = "HSBACM_BENHPHU5", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbacmBenhphu5;
    @JoinColumn(name = "KETQUA_MA", referencedColumnName = "DMKQDT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKetQuaDieuTri ketquaMa;
    @JoinColumn(name = "HSBACM_CHUYENKHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hsbacmChuyenkhoa;
    @JoinColumn(name = "HSBACM_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbacmBacsi;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBACM_TACNHAN", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbacmTacnhan;

    public HsbaChuyenMon() {
    }

    public HsbaChuyenMon(Integer hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public Integer getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(Integer hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public Short getHsbacmSinh() {
        return hsbacmSinh;
    }

    public void setHsbacmSinh(Short hsbacmSinh) {
        this.hsbacmSinh = hsbacmSinh;
    }

    public Short getHsbacmSom() {
        return hsbacmSom;
    }

    public void setHsbacmSom(Short hsbacmSom) {
        this.hsbacmSom = hsbacmSom;
    }

    public Short getHsbacmSay() {
        return hsbacmSay;
    }

    public void setHsbacmSay(Short hsbacmSay) {
        this.hsbacmSay = hsbacmSay;
    }

    public Short getHsbacmSong() {
        return hsbacmSong;
    }

    public void setHsbacmSong(Short hsbacmSong) {
        this.hsbacmSong = hsbacmSong;
    }

    public Boolean getHsbacmKstc() {
        return hsbacmKstc;
    }

    public void setHsbacmKstc(Boolean hsbacmKstc) {
        this.hsbacmKstc = hsbacmKstc;
    }

    public Boolean getHsbacmLathuthuat() {
        return hsbacmLathuthuat;
    }

    public void setHsbacmLathuthuat(Boolean hsbacmLathuthuat) {
        this.hsbacmLathuthuat = hsbacmLathuthuat;
    }

    public Boolean getHsbacmLaphauthuat() {
        return hsbacmLaphauthuat;
    }

    public void setHsbacmLaphauthuat(Boolean hsbacmLaphauthuat) {
        this.hsbacmLaphauthuat = hsbacmLaphauthuat;
    }

    public Boolean getHsbacmLasinh() {
        return hsbacmLasinh;
    }

    public void setHsbacmLasinh(Boolean hsbacmLasinh) {
        this.hsbacmLasinh = hsbacmLasinh;
    }

    public Integer getHsbacmHamax() {
        return hsbacmHamax;
    }

    public void setHsbacmHamax(Integer hsbacmHamax) {
        this.hsbacmHamax = hsbacmHamax;
    }

    public Integer getHsbacmHamin() {
        return hsbacmHamin;
    }

    public void setHsbacmHamin(Integer hsbacmHamin) {
        this.hsbacmHamin = hsbacmHamin;
    }

    public Date getHsbacmNgaygiovaok() {
        return hsbacmNgaygiovaok;
    }

    public void setHsbacmNgaygiovaok(Date hsbacmNgaygiovaok) {
        this.hsbacmNgaygiovaok = hsbacmNgaygiovaok;
    }

    public Date getHsbacmNgaygiorak() {
        return hsbacmNgaygiorak;
    }

    public void setHsbacmNgaygiorak(Date hsbacmNgaygiorak) {
        this.hsbacmNgaygiorak = hsbacmNgaygiorak;
    }

    public Integer getHsbacmSongaydt() {
        return hsbacmSongaydt;
    }

    public void setHsbacmSongaydt(Integer hsbacmSongaydt) {
        this.hsbacmSongaydt = hsbacmSongaydt;
    }

    public String getHsbacmBenhsu() {
        return hsbacmBenhsu;
    }

    public void setHsbacmBenhsu(String hsbacmBenhsu) {
        this.hsbacmBenhsu = hsbacmBenhsu;
    }

    public String getHsbacmDiengiaibc() {
        return hsbacmDiengiaibc;
    }

    public void setHsbacmDiengiaibc(String hsbacmDiengiaibc) {
        this.hsbacmDiengiaibc = hsbacmDiengiaibc;
    }

    public String getHsbacmBienchung() {
        return hsbacmBienchung;
    }

    public void setHsbacmBienchung(String hsbacmBienchung) {
        this.hsbacmBienchung = hsbacmBienchung;
    }

    public String getHsbacmDieutri() {
        return hsbacmDieutri;
    }

    public void setHsbacmDieutri(String hsbacmDieutri) {
        this.hsbacmDieutri = hsbacmDieutri;
    }

    public String getHsbacmDiung() {
        return hsbacmDiung;
    }

    public void setHsbacmDiung(String hsbacmDiung) {
        this.hsbacmDiung = hsbacmDiung;
    }

    public Date getHsbacmNgaygiocn() {
        return hsbacmNgaygiocn;
    }

    public void setHsbacmNgaygiocn(Date hsbacmNgaygiocn) {
        this.hsbacmNgaygiocn = hsbacmNgaygiocn;
    }

    public Hsba getHsbaSovaovien(boolean create) {
        if (create && hsbaSovaovien == null) {
            hsbaSovaovien = new Hsba();
        }
        return hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmKhoa getKhoaMa(boolean create) {
        if (create && khoaMa == null) {
            khoaMa = new DmKhoa();
        }
        return khoaMa;
    }

    public DmKhoa getKhoaMa() {
        return khoaMa;
    }

    public void setKhoaMa(DmKhoa khoaMa) {
        this.khoaMa = khoaMa;
    }

    public DmBenhIcd getHsbacmBenhchinh(boolean create) {
        if (create && hsbacmBenhchinh == null) {
            hsbacmBenhchinh = new DmBenhIcd();
        }
        return hsbacmBenhchinh;
    }

    public DmBenhIcd getHsbacmBenhchinh() {
        return hsbacmBenhchinh;
    }

    public void setHsbacmBenhchinh(DmBenhIcd hsbacmBenhchinh) {
        this.hsbacmBenhchinh = hsbacmBenhchinh;
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

    public DmBenhIcd getHsbacmBenhphu(boolean create) {
        if (create && hsbacmBenhphu == null) {
            hsbacmBenhphu = new DmBenhIcd();
        }
        return hsbacmBenhphu;
    }

    public DmBenhIcd getHsbacmBenhphu() {
        return hsbacmBenhphu;
    }

    public void setHsbacmBenhphu(DmBenhIcd hsbacmBenhphu) {
        this.hsbacmBenhphu = hsbacmBenhphu;
    }

    public DmBenhIcd getHsbacmBenhphu2(boolean create) {
        if (create && hsbacmBenhphu2 == null) {
            hsbacmBenhphu2 = new DmBenhIcd();
        }
        return hsbacmBenhphu2;
    }

    public DmBenhIcd getHsbacmBenhphu2() {
        return hsbacmBenhphu2;
    }

    public void setHsbacmBenhphu2(DmBenhIcd hsbacmBenhphu2) {
        this.hsbacmBenhphu2 = hsbacmBenhphu2;
    }

    public DmBenhIcd getHsbacmBenhphu3(boolean create) {
        if (create && hsbacmBenhphu3 == null) {
            hsbacmBenhphu3 = new DmBenhIcd();
        }
        return hsbacmBenhphu3;
    }

    public DmBenhIcd getHsbacmBenhphu3() {
        return hsbacmBenhphu3;
    }

    public void setHsbacmBenhphu3(DmBenhIcd hsbacmBenhphu3) {
        this.hsbacmBenhphu3 = hsbacmBenhphu3;
    }

    public DmBenhIcd getHsbacmBenhphu4(boolean create) {
        if (create && hsbacmBenhphu4 == null) {
            hsbacmBenhphu4 = new DmBenhIcd();
        }
        return hsbacmBenhphu4;
    }

    public DmBenhIcd getHsbacmBenhphu4() {
        return hsbacmBenhphu4;
    }

    public void setHsbacmBenhphu4(DmBenhIcd hsbacmBenhphu4) {
        this.hsbacmBenhphu4 = hsbacmBenhphu4;
    }

    public DmBenhIcd getHsbacmBenhphu5(boolean create) {
        if (create && hsbacmBenhphu5 == null) {
            hsbacmBenhphu5 = new DmBenhIcd();
        }
        return hsbacmBenhphu5;
    }

    public DmBenhIcd getHsbacmBenhphu5() {
        return hsbacmBenhphu5;
    }

    public void setHsbacmBenhphu5(DmBenhIcd hsbacmBenhphu5) {
        this.hsbacmBenhphu5 = hsbacmBenhphu5;
    }

    public DmKetQuaDieuTri getKetquaMa(boolean create) {
        if (create && ketquaMa == null) {
            ketquaMa = new DmKetQuaDieuTri();
        }
        return ketquaMa;
    }

    public DmKetQuaDieuTri getKetquaMa() {
        return ketquaMa;
    }

    public void setKetquaMa(DmKetQuaDieuTri ketquaMa) {
        this.ketquaMa = ketquaMa;
    }

    public DmKhoa getHsbacmChuyenkhoa(boolean create) {
        if (create && hsbacmChuyenkhoa == null) {
            hsbacmChuyenkhoa = new DmKhoa();
        }
        return hsbacmChuyenkhoa;
    }

    public DmKhoa getHsbacmChuyenkhoa() {
        return hsbacmChuyenkhoa;
    }

    public void setHsbacmChuyenkhoa(DmKhoa hsbacmChuyenkhoa) {
        this.hsbacmChuyenkhoa = hsbacmChuyenkhoa;
    }

    public DtDmNhanVien getHsbacmBacsi(boolean create) {
        if (create && hsbacmBacsi == null) {
            hsbacmBacsi = new DtDmNhanVien();
        }
        return hsbacmBacsi;
    }

    public DtDmNhanVien getHsbacmBacsi() {
        return hsbacmBacsi;
    }

    public void setHsbacmBacsi(DtDmNhanVien hsbacmBacsi) {
        this.hsbacmBacsi = hsbacmBacsi;
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

    public String getHsbacmHuongdieutri() {
        return hsbacmHuongdieutri;
    }

    public void setHsbacmHuongdieutri(String hsbacmHuongdieutri) {
        this.hsbacmHuongdieutri = hsbacmHuongdieutri;
    }

    public DmBenhIcd getHsbacmTacnhan(boolean create) {
        if (create && hsbacmTacnhan == null) {
            hsbacmTacnhan = new DmBenhIcd();
        }
        return hsbacmTacnhan;
    }

    public DmBenhIcd getHsbacmTacnhan() {
        return hsbacmTacnhan;
    }

    public void setHsbacmTacnhan(DmBenhIcd hsbacmTacnhan) {
        this.hsbacmTacnhan = hsbacmTacnhan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbacmMa != null ? hsbacmMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChuyenMon)) {
            return false;
        }
        HsbaChuyenMon other = (HsbaChuyenMon) object;
        if ((this.hsbacmMa == null && other.hsbacmMa != null) || (this.hsbacmMa != null && !this.hsbacmMa.equals(other.hsbacmMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaChuyenMon[hsbacmMa=" + hsbacmMa + "]";
    }

    public Integer getHsbacmlan() {
        return hsbacmlan;
    }

    public void setHsbacmlan(Integer hsbacmlan) {
        this.hsbacmlan = hsbacmlan;
    }

     public String getHsbacmSogiuong() {
        return hsbacmSogiuong;
    }

    public void setHsbacmSogiuong(String hsbacmSogiuong) {
        this.hsbacmSogiuong = hsbacmSogiuong;
    }

    public String getHsbacmSobuong() {
        return hsbacmSobuong;
    }

    public void setHsbacmSobuong(String hsbacmSobuong) {
        this.hsbacmSobuong = hsbacmSobuong;
    }
}


