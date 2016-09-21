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
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DON_MIEN")
@NamedQueries({})
public class DonMien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DONMIEN_MA", nullable = false)
    private String donmienMa;
    @Column(name = "DONMIEN_CHIPHI")
    private Double donmienChiphi;
    @Column(name = "DONMIEN_MIENGIAM")
    private Double donmienMiengiam;
    @Column(name = "DONMIEN_MIENPHI")
    private Double donmienMienphi;
    @Column(name = "DONMIEN_BHYT")
    private Double donmienBhyt;
    @Column(name = "DONMIEN_BNTRA")
    private Double donmienBntra;
    @Column(name = "DONMIEN_TAMUNG")
    private Double donmienTamung;
    @Column(name = "DONMIEN_NGAY")
    @Temporal(TemporalType.DATE)
    private Date donmienNgay;
    @Column(name = "DONMIEN_CUM")
    private Short donmienCum;
    @Column(name = "DONMIEN_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date donmienNgaygiocn;
    @Column(name = "DONMIEN_LYDO")
    private String donmienLydo;
    @Column(name = "DONMIEN_MIENPHONG")
    private Boolean donmienMienphong;
    @Column(name = "DONMIEN_MIENCPDT")
    private Boolean donmienMiencpdt;
    @Column(name = "DONMIEN_MIENMAU")
    private Short donmienMienmau;
    @Column(name = "DONMIEN_MIENKTC")
    private Boolean donmienMienktc;
    @Column(name = "DONMIEN_MIENCONLAI")
    private Boolean donmienMienconlai;
    @Column(name = "DONMIEN_MIENNGAY")
    private Boolean donmienMienngay;
    @Column(name = "DONMIEN_NGAY1")
    @Temporal(TemporalType.DATE)
    private Date donmienNgay1;
    @Column(name = "DONMIEN_NGAY2")
    @Temporal(TemporalType.DATE)
    private Date donmienNgay2;
    @Column(name = "DONMIEN_MIENKHAC")
    private String donmienMienkhac;
    @Column(name = "DONMIEN_STATUS")
    private String donmienStatus;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "DONMIEN_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa donmienKhoa;
    @JoinColumn(name = "DONMIEN_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien donmienNhanviencn;

    public DonMien() {
    }

    public DonMien(String donmienMa) {
        this.donmienMa = donmienMa;
    }

    public String getDonmienMa() {
        return donmienMa;
    }

    public void setDonmienMa(String donmienMa) {
        this.donmienMa = donmienMa;
    }

    public Double getDonmienChiphi() {
        return donmienChiphi;
    }

    public void setDonmienChiphi(Double donmienChiphi) {
        this.donmienChiphi = donmienChiphi;
    }

    public Double getDonmienMiengiam() {
        return donmienMiengiam;
    }

    public void setDonmienMiengiam(Double donmienMiengiam) {
        this.donmienMiengiam = donmienMiengiam;
    }

    public Double getDonmienMienphi() {
        return donmienMienphi;
    }

    public void setDonmienMienphi(Double donmienMienphi) {
        this.donmienMienphi = donmienMienphi;
    }

    public Double getDonmienBhyt() {
        return donmienBhyt;
    }

    public void setDonmienBhyt(Double donmienBhyt) {
        this.donmienBhyt = donmienBhyt;
    }

    public Double getDonmienBntra() {
        return donmienBntra;
    }

    public void setDonmienBntra(Double donmienBntra) {
        this.donmienBntra = donmienBntra;
    }

    public Double getDonmienTamung() {
        return donmienTamung;
    }

    public void setDonmienTamung(Double donmienTamung) {
        this.donmienTamung = donmienTamung;
    }

    public Date getDonmienNgay() {
        return donmienNgay;
    }

    public void setDonmienNgay(Date donmienNgay) {
        this.donmienNgay = donmienNgay;
    }

    public Short getDonmienCum() {
        return donmienCum;
    }

    public void setDonmienCum(Short donmienCum) {
        this.donmienCum = donmienCum;
    }

    public Date getDonmienNgaygiocn() {
        return donmienNgaygiocn;
    }

    public void setDonmienNgaygiocn(Date donmienNgaygiocn) {
        this.donmienNgaygiocn = donmienNgaygiocn;
    }

    public String getDonmienLydo() {
        return donmienLydo;
    }

    public void setDonmienLydo(String donmienLydo) {
        this.donmienLydo = donmienLydo;
    }

    public Boolean getDonmienMienphong() {
        return donmienMienphong;
    }

    public void setDonmienMienphong(Boolean donmienMienphong) {
        this.donmienMienphong = donmienMienphong;
    }

    public Boolean getDonmienMiencpdt() {
        return donmienMiencpdt;
    }

    public void setDonmienMiencpdt(Boolean donmienMiencpdt) {
        this.donmienMiencpdt = donmienMiencpdt;
    }

    public Short getDonmienMienmau() {
        return donmienMienmau;
    }

    public void setDonmienMienmau(Short donmienMienmau) {
        this.donmienMienmau = donmienMienmau;
    }

    public Boolean getDonmienMienktc() {
        return donmienMienktc;
    }

    public void setDonmienMienktc(Boolean donmienMienktc) {
        this.donmienMienktc = donmienMienktc;
    }

    public Boolean getDonmienMienconlai() {
        return donmienMienconlai;
    }

    public void setDonmienMienconlai(Boolean donmienMienconlai) {
        this.donmienMienconlai = donmienMienconlai;
    }

    public Boolean getDonmienMienngay() {
        return donmienMienngay;
    }

    public void setDonmienMienngay(Boolean donmienMienngay) {
        this.donmienMienngay = donmienMienngay;
    }

    public Date getDonmienNgay1() {
        return donmienNgay1;
    }

    public void setDonmienNgay1(Date donmienNgay1) {
        this.donmienNgay1 = donmienNgay1;
    }

    public Date getDonmienNgay2() {
        return donmienNgay2;
    }

    public void setDonmienNgay2(Date donmienNgay2) {
        this.donmienNgay2 = donmienNgay2;
    }

    public String getDonmienMienkhac() {
        return donmienMienkhac;
    }

    public void setDonmienMienkhac(String donmienMienkhac) {
        this.donmienMienkhac = donmienMienkhac;
    }

    public String getDonmienStatus() {
        return donmienStatus;
    }

    public void setDonmienStatus(String donmienStatus) {
        this.donmienStatus = donmienStatus;
    }

    public Hsba getHsbaSovaovien(boolean create) {
if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
return hsbaSovaovien;
}
    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmKhoa getDonmienKhoa(boolean create) {
if(create && donmienKhoa == null) donmienKhoa = new DmKhoa();
return donmienKhoa;
}
    public DmKhoa getDonmienKhoa() {
        return donmienKhoa;
    }

    public void setDonmienKhoa(DmKhoa donmienKhoa) {
        this.donmienKhoa = donmienKhoa;
    }

    public DtDmNhanVien getDonmienNhanviencn(boolean create) {
if(create && donmienNhanviencn == null) donmienNhanviencn = new DtDmNhanVien();
return donmienNhanviencn;
}
    public DtDmNhanVien getDonmienNhanviencn() {
        return donmienNhanviencn;
    }

    public void setDonmienNhanviencn(DtDmNhanVien donmienNhanviencn) {
        this.donmienNhanviencn = donmienNhanviencn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (donmienMa != null ? donmienMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DonMien)) {
            return false;
        }
        DonMien other = (DonMien) object;
        if ((this.donmienMa == null && other.donmienMa != null) || (this.donmienMa != null && !this.donmienMa.equals(other.donmienMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DonMien[donmienMa=" + donmienMa + "]";
    }

}


