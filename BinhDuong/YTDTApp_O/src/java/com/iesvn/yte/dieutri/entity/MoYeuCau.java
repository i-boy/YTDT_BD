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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "MO_YEU_CAU")
@NamedQueries({})
public class MoYeuCau implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MOYEUCAU_MA", nullable = false)
    private String moyeucauMa;
    @Column(name = "MOYEUCAU_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date moyeucauNgaytt;
    @Column(name = "MOYEUCAU_YEUCAU")
    private Boolean moyeucauYeucau;
    @Column(name = "MOYEUCAU_NHI")
    private Boolean moyeucauNhi;
    @Column(name = "MOYEUCAU_LAO")
    private Boolean moyeucauLao;
    @Column(name = "MOYEUCAU_DONGIA")
    private Double moyeucauDongia;
    @Column(name = "MOYEUCAU_DONGIABH")
    private Double moyeucauDongiabh;
    @Column(name = "MOYEUCAU_CUM")
    private Short moyeucauCum;
    @Column(name = "MOYEUCAU_NGOAIGIO")
    private Boolean moyeucauNgoaigio;
    @Column(name = "MOYEUCAU_NGAY")
    @Temporal(TemporalType.DATE)
    private Date moyeucauNgay;
    @Column(name = "MOYEUCAU_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date moyeucauNgaygiocn;
    @Column(name = "MOYEUCAU_NOIDUNGPT")
    private String moyeucauNoidungpt;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "MOYEUCAU_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa moyeucauKhoa;
    @JoinColumn(name = "MOYEUCAU_MAMYC", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia moyeucauMamyc;
    @JoinColumn(name = "MOYEUCAU_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien moyeucauBacsi;
    @JoinColumn(name = "MOYEUCAU_BACSI2", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien moyeucauBacsi2;
    @JoinColumn(name = "MOYEUCAU_BACSI3", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien moyeucauBacsi3;
    @JoinColumn(name = "MOYEUCAU_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien moyeucauNhanviencn;
    @JoinColumn(name = "MOYEUCAU_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien moyeucauThungan;

  

    public MoYeuCau() {
    }

    public MoYeuCau(String moyeucauMa) {
        this.moyeucauMa = moyeucauMa;
    }

    public String getMoyeucauMa() {
        return moyeucauMa;
    }

    public void setMoyeucauMa(String moyeucauMa) {
        this.moyeucauMa = moyeucauMa;
    }

    public Date getMoyeucauNgaytt() {
        return moyeucauNgaytt;
    }

    public void setMoyeucauNgaytt(Date moyeucauNgaytt) {
        this.moyeucauNgaytt = moyeucauNgaytt;
    }

    public Boolean getMoyeucauYeucau() {
        return moyeucauYeucau;
    }

    public void setMoyeucauYeucau(Boolean moyeucauYeucau) {
        this.moyeucauYeucau = moyeucauYeucau;
    }

    public Boolean getMoyeucauNhi() {
        return moyeucauNhi;
    }

    public void setMoyeucauNhi(Boolean moyeucauNhi) {
        this.moyeucauNhi = moyeucauNhi;
    }

    public Boolean getMoyeucauLao() {
        return moyeucauLao;
    }

    public void setMoyeucauLao(Boolean moyeucauLao) {
        this.moyeucauLao = moyeucauLao;
    }

    public Double getMoyeucauDongia() {
        return moyeucauDongia;
    }

    public void setMoyeucauDongia(Double moyeucauDongia) {
        this.moyeucauDongia = moyeucauDongia;
    }

    public Double getMoyeucauDongiabh() {
        return moyeucauDongiabh;
    }

    public void setMoyeucauDongiabh(Double moyeucauDongiabh) {
        this.moyeucauDongiabh = moyeucauDongiabh;
    }

    public Short getMoyeucauCum() {
        return moyeucauCum;
    }

    public void setMoyeucauCum(Short moyeucauCum) {
        this.moyeucauCum = moyeucauCum;
    }

    public Boolean getMoyeucauNgoaigio() {
        return moyeucauNgoaigio;
    }

    public void setMoyeucauNgoaigio(Boolean moyeucauNgoaigio) {
        this.moyeucauNgoaigio = moyeucauNgoaigio;
    }

    public Date getMoyeucauNgay() {
        return moyeucauNgay;
    }

    public void setMoyeucauNgay(Date moyeucauNgay) {
        this.moyeucauNgay = moyeucauNgay;
    }

    public Date getMoyeucauNgaygiocn() {
        return moyeucauNgaygiocn;
    }

    public void setMoyeucauNgaygiocn(Date moyeucauNgaygiocn) {
        this.moyeucauNgaygiocn = moyeucauNgaygiocn;
    }

    public String getMoyeucauNoidungpt() {
        return moyeucauNoidungpt;
    }

    public void setMoyeucauNoidungpt(String moyeucauNoidungpt) {
        this.moyeucauNoidungpt = moyeucauNoidungpt;
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

    public DmKhoa getMoyeucauKhoa(boolean create) {
if(create && moyeucauKhoa == null) moyeucauKhoa = new DmKhoa();
return moyeucauKhoa;
}
    public DmKhoa getMoyeucauKhoa() {
        return moyeucauKhoa;
    }

    public void setMoyeucauKhoa(DmKhoa moyeucauKhoa) {
        this.moyeucauKhoa = moyeucauKhoa;
    }

    public DtDmClsBangGia getMoyeucauMamyc(boolean create) {
if(create && moyeucauMamyc == null) moyeucauMamyc = new DtDmClsBangGia();
return moyeucauMamyc;
}
    public DtDmClsBangGia getMoyeucauMamyc() {
        return moyeucauMamyc;
    }

    public void setMoyeucauMamyc(DtDmClsBangGia moyeucauMamyc) {
        this.moyeucauMamyc = moyeucauMamyc;
    }

    public DtDmNhanVien getMoyeucauBacsi(boolean create) {
if(create && moyeucauBacsi == null) moyeucauBacsi = new DtDmNhanVien();
return moyeucauBacsi;
}
    public DtDmNhanVien getMoyeucauBacsi() {
        return moyeucauBacsi;
    }

    public void setMoyeucauBacsi(DtDmNhanVien moyeucauBacsi) {
        this.moyeucauBacsi = moyeucauBacsi;
    }

    public DtDmNhanVien getMoyeucauBacsi2(boolean create) {
if(create && moyeucauBacsi2 == null) moyeucauBacsi2 = new DtDmNhanVien();
return moyeucauBacsi2;
}
    public DtDmNhanVien getMoyeucauBacsi2() {
        return moyeucauBacsi2;
    }

    public void setMoyeucauBacsi2(DtDmNhanVien moyeucauBacsi2) {
        this.moyeucauBacsi2 = moyeucauBacsi2;
    }

    public DtDmNhanVien getMoyeucauBacsi3(boolean create) {
if(create && moyeucauBacsi3 == null) moyeucauBacsi3 = new DtDmNhanVien();
return moyeucauBacsi3;
}
    public DtDmNhanVien getMoyeucauBacsi3() {
        return moyeucauBacsi3;
    }

    public void setMoyeucauBacsi3(DtDmNhanVien moyeucauBacsi3) {
        this.moyeucauBacsi3 = moyeucauBacsi3;
    }

    public DtDmNhanVien getMoyeucauNhanviencn(boolean create) {
if(create && moyeucauNhanviencn == null) moyeucauNhanviencn = new DtDmNhanVien();
return moyeucauNhanviencn;
}
    public DtDmNhanVien getMoyeucauNhanviencn() {
        return moyeucauNhanviencn;
    }

    public void setMoyeucauNhanviencn(DtDmNhanVien moyeucauNhanviencn) {
        this.moyeucauNhanviencn = moyeucauNhanviencn;
    }

    public DtDmNhanVien getMoyeucauThungan(boolean create) {
if(create && moyeucauThungan == null) moyeucauThungan = new DtDmNhanVien();
return moyeucauThungan;
}
    public DtDmNhanVien getMoyeucauThungan() {
        return moyeucauThungan;
    }

    public void setMoyeucauThungan(DtDmNhanVien moyeucauThungan) {
        this.moyeucauThungan = moyeucauThungan;
    }

  


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moyeucauMa != null ? moyeucauMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoYeuCau)) {
            return false;
        }
        MoYeuCau other = (MoYeuCau) object;
        if ((this.moyeucauMa == null && other.moyeucauMa != null) || (this.moyeucauMa != null && !this.moyeucauMa.equals(other.moyeucauMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.MoYeuCau[moyeucauMa=" + moyeucauMa + "]";
    }

}


