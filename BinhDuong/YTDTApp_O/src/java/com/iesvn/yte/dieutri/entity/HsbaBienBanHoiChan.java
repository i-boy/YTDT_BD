/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

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
@Table(name = "HSBA_BIEN_BAN_HOI_CHAN")
@NamedQueries({})
public class HsbaBienBanHoiChan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_BIEN_BAN_HOI_CHAN")
    @SequenceGenerator(name = "HSBA_BIEN_BAN_HOI_CHAN", sequenceName = "HSBA_BIEN_BAN_HOI_CHAN_HSBABBH", allocationSize = 1)
    @Column(name = "HSBABBHC_MASO", nullable = false)
    private Integer hsbabbhcMaso;
    @Column(name = "HSBABBHC_NGAYGIOHOICHAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbabbhcNgaygiohoichan;
    @Column(name = "HSBABBHC_TOMTAT")
    private String hsbabbhcTomtat;
    @Column(name = "HSBABBHC_KETLUAN")
    private String hsbabbhcKetluan;
    @Column(name = "HSBABBHC_HUONGDIEUTRI")
    private String hsbabbhcHuongdieutri;
    @Column(name = "HSBABBHC_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbabbhcNgaygiocn;
    @Column(name = "HSBABBHC_MA")
    private String hsbabbhcMa;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBABBHC_CHUTOA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbabbhcChutoa;
    @JoinColumn(name = "HSBABBHC_THUKY", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbabbhcThuky;
    @JoinColumn(name = "HSBABBHC_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbabbhcNhanviencn;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hsbabbhcMaso")
//    private Collection<HsbaBienBanHoiChanThanhVien> hsbaBienBanHoiChanThanhVienCollection;

    public HsbaBienBanHoiChan() {
    }

    public HsbaBienBanHoiChan(Integer hsbabbhcMaso) {
        this.hsbabbhcMaso = hsbabbhcMaso;
    }

    public Integer getHsbabbhcMaso() {
        return hsbabbhcMaso;
    }

    public void setHsbabbhcMaso(Integer hsbabbhcMaso) {
        this.hsbabbhcMaso = hsbabbhcMaso;
    }

    public Date getHsbabbhcNgaygiohoichan() {
        return hsbabbhcNgaygiohoichan;
    }

    public void setHsbabbhcNgaygiohoichan(Date hsbabbhcNgaygiohoichan) {
        this.hsbabbhcNgaygiohoichan = hsbabbhcNgaygiohoichan;
    }

    public String getHsbabbhcTomtat() {
        return hsbabbhcTomtat;
    }

    public void setHsbabbhcTomtat(String hsbabbhcTomtat) {
        this.hsbabbhcTomtat = hsbabbhcTomtat;
    }

    public String getHsbabbhcKetluan() {
        return hsbabbhcKetluan;
    }

    public void setHsbabbhcKetluan(String hsbabbhcKetluan) {
        this.hsbabbhcKetluan = hsbabbhcKetluan;
    }

    public String getHsbabbhcHuongdieutri() {
        return hsbabbhcHuongdieutri;
    }

    public void setHsbabbhcHuongdieutri(String hsbabbhcHuongdieutri) {
        this.hsbabbhcHuongdieutri = hsbabbhcHuongdieutri;
    }

    public Date getHsbabbhcNgaygiocn() {
        return hsbabbhcNgaygiocn;
    }

    public void setHsbabbhcNgaygiocn(Date hsbabbhcNgaygiocn) {
        this.hsbabbhcNgaygiocn = hsbabbhcNgaygiocn;
    }

    public String getHsbabbhcMa() {
        return hsbabbhcMa;
    }

    public void setHsbabbhcMa(String hsbabbhcMa) {
        this.hsbabbhcMa = hsbabbhcMa;
    }

    public HsbaChuyenMon getHsbacmMa(boolean create) {
if(create && hsbacmMa == null) hsbacmMa = new HsbaChuyenMon();
return hsbacmMa;
}
    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DtDmNhanVien getHsbabbhcChutoa(boolean create) {
if(create && hsbabbhcChutoa == null) hsbabbhcChutoa = new DtDmNhanVien();
return hsbabbhcChutoa;
}
    public DtDmNhanVien getHsbabbhcChutoa() {
        return hsbabbhcChutoa;
    }

    public void setHsbabbhcChutoa(DtDmNhanVien hsbabbhcChutoa) {
        this.hsbabbhcChutoa = hsbabbhcChutoa;
    }

    public DtDmNhanVien getHsbabbhcThuky(boolean create) {
if(create && hsbabbhcThuky == null) hsbabbhcThuky = new DtDmNhanVien();
return hsbabbhcThuky;
}
    public DtDmNhanVien getHsbabbhcThuky() {
        return hsbabbhcThuky;
    }

    public void setHsbabbhcThuky(DtDmNhanVien hsbabbhcThuky) {
        this.hsbabbhcThuky = hsbabbhcThuky;
    }

    public DtDmNhanVien getHsbabbhcNhanviencn(boolean create) {
if(create && hsbabbhcNhanviencn == null) hsbabbhcNhanviencn = new DtDmNhanVien();
return hsbabbhcNhanviencn;
}
    public DtDmNhanVien getHsbabbhcNhanviencn() {
        return hsbabbhcNhanviencn;
    }

    public void setHsbabbhcNhanviencn(DtDmNhanVien hsbabbhcNhanviencn) {
        this.hsbabbhcNhanviencn = hsbabbhcNhanviencn;
    }


//    public Collection<HsbaBienBanHoiChanThanhVien> getHsbaBienBanHoiChanThanhVienCollection() {
//        return hsbaBienBanHoiChanThanhVienCollection;
//    }
//
//    public void setHsbaBienBanHoiChanThanhVienCollection(Collection<HsbaBienBanHoiChanThanhVien> hsbaBienBanHoiChanThanhVienCollection) {
//        this.hsbaBienBanHoiChanThanhVienCollection = hsbaBienBanHoiChanThanhVienCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbabbhcMaso != null ? hsbabbhcMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaBienBanHoiChan)) {
            return false;
        }
        HsbaBienBanHoiChan other = (HsbaBienBanHoiChan) object;
        if ((this.hsbabbhcMaso == null && other.hsbabbhcMaso != null) || (this.hsbabbhcMaso != null && !this.hsbabbhcMaso.equals(other.hsbabbhcMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaBienBanHoiChan[hsbabbhcMaso=" + hsbabbhcMaso + "]";
    }

}


