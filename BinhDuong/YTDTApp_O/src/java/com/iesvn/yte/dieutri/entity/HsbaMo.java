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
@Table(name = "HSBA_MO")
@NamedQueries({})
public class HsbaMo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_MO_HSBAMO_MA")
    @SequenceGenerator(name = "HSBA_MO_HSBAMO_MA", sequenceName = "HSBA_MO_HSBAMO_MA_SEQ", allocationSize = 1)
    @Column(name = "HSBAMO_MA", nullable = false)
    private Integer hsbamoMa;
    @Column(name = "HSBAMO_NOIDUNG")
    private String hsbamoNoidung;
    @Column(name = "HSBAMO_PHUONGPHAP")
    private String hsbamoPhuongphap;
    @Column(name = "HSBAMO_TAIBIEN")
    private Boolean hsbamoTaibien;
    @Column(name = "HSBAMO_CHETBANMO")
    private Boolean hsbamoChetbanmo;
    @Column(name = "HSBAMO_NGAYGIOMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbamoNgaygiomo;
    @Column(name = "HSBAMO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbamoNgaygiocn;
    @Column(name = "HSBAMO_LANTHU")
    private Integer hsbamoLanthu;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "VOCAM_MASO", referencedColumnName = "DTDMVOCAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmVoCam vocamMaso;
    @JoinColumn(name = "HSBAMO_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbamoBacsi;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBAMO_CCPHIEN", referencedColumnName = "DTDMCCP_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCapCuuPhien hsbamoCcphien;
    @JoinColumn(name = "HSBAMO_MAMO", referencedColumnName = "DTDMPHAUTHUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmPhauThuat hsbamoMamo;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hsbamoMa")
//    private Collection<KetQuaMo> ketQuaMoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hsbamoMa1")
//    private Collection<KetQuaMo> ketQuaMoCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hsbamoMa")
//    private Collection<LichMo> lichMoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hsbamoMa1")
//    private Collection<LichMo> lichMoCollection1;

    public HsbaMo() {
    }

    public HsbaMo(Integer hsbamoMa) {
        this.hsbamoMa = hsbamoMa;
    }

    public Integer getHsbamoMa() {
        return hsbamoMa;
    }

    public void setHsbamoMa(Integer hsbamoMa) {
        this.hsbamoMa = hsbamoMa;
    }

    public String getHsbamoNoidung() {
        return hsbamoNoidung;
    }

    public void setHsbamoNoidung(String hsbamoNoidung) {
        this.hsbamoNoidung = hsbamoNoidung;
    }

    public String getHsbamoPhuongphap() {
        return hsbamoPhuongphap;
    }

    public void setHsbamoPhuongphap(String hsbamoPhuongphap) {
        this.hsbamoPhuongphap = hsbamoPhuongphap;
    }

    public Boolean getHsbamoTaibien() {
        return hsbamoTaibien;
    }

    public void setHsbamoTaibien(Boolean hsbamoTaibien) {
        this.hsbamoTaibien = hsbamoTaibien;
    }

    public Boolean getHsbamoChetbanmo() {
        return hsbamoChetbanmo;
    }

    public void setHsbamoChetbanmo(Boolean hsbamoChetbanmo) {
        this.hsbamoChetbanmo = hsbamoChetbanmo;
    }

    public Date getHsbamoNgaygiomo() {
        return hsbamoNgaygiomo;
    }

    public void setHsbamoNgaygiomo(Date hsbamoNgaygiomo) {
        this.hsbamoNgaygiomo = hsbamoNgaygiomo;
    }

    public Date getHsbamoNgaygiocn() {
        return hsbamoNgaygiocn;
    }

    public void setHsbamoNgaygiocn(Date hsbamoNgaygiocn) {
        this.hsbamoNgaygiocn = hsbamoNgaygiocn;
    }

    public Integer getHsbamoLanthu() {
        return hsbamoLanthu;
    }

    public void setHsbamoLanthu(Integer hsbamoLanthu) {
        this.hsbamoLanthu = hsbamoLanthu;
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

    public DtDmVoCam getVocamMaso(boolean create) {
if(create && vocamMaso == null) vocamMaso = new DtDmVoCam();
return vocamMaso;
}
    public DtDmVoCam getVocamMaso() {
        return vocamMaso;
    }

    public void setVocamMaso(DtDmVoCam vocamMaso) {
        this.vocamMaso = vocamMaso;
    }

    public DtDmNhanVien getHsbamoBacsi(boolean create) {
if(create && hsbamoBacsi == null) hsbamoBacsi = new DtDmNhanVien();
return hsbamoBacsi;
}
    public DtDmNhanVien getHsbamoBacsi() {
        return hsbamoBacsi;
    }

    public void setHsbamoBacsi(DtDmNhanVien hsbamoBacsi) {
        this.hsbamoBacsi = hsbamoBacsi;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
return nhanvienMa;
}
    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public DtDmCapCuuPhien getHsbamoCcphien(boolean create) {
if(create && hsbamoCcphien == null) hsbamoCcphien = new DtDmCapCuuPhien();
return hsbamoCcphien;
}
    public DtDmCapCuuPhien getHsbamoCcphien() {
        return hsbamoCcphien;
    }

    public void setHsbamoCcphien(DtDmCapCuuPhien hsbamoCcphien) {
        this.hsbamoCcphien = hsbamoCcphien;
    }

    public DtDmPhauThuat getHsbamoMamo(boolean create) {
if(create && hsbamoMamo == null) hsbamoMamo = new DtDmPhauThuat();
return hsbamoMamo;
}
    public DtDmPhauThuat getHsbamoMamo() {
        return hsbamoMamo;
    }

    public void setHsbamoMamo(DtDmPhauThuat hsbamoMamo) {
        this.hsbamoMamo = hsbamoMamo;
    }

  
//    public Collection<KetQuaMo> getKetQuaMoCollection() {
//        return ketQuaMoCollection;
//    }
//
//    public void setKetQuaMoCollection(Collection<KetQuaMo> ketQuaMoCollection) {
//        this.ketQuaMoCollection = ketQuaMoCollection;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection1() {
//        return ketQuaMoCollection1;
//    }
//
//    public void setKetQuaMoCollection1(Collection<KetQuaMo> ketQuaMoCollection1) {
//        this.ketQuaMoCollection1 = ketQuaMoCollection1;
//    }

//    public Collection<LichMo> getLichMoCollection() {
//        return lichMoCollection;
//    }
//
//    public void setLichMoCollection(Collection<LichMo> lichMoCollection) {
//        this.lichMoCollection = lichMoCollection;
//    }

//    public Collection<LichMo> getLichMoCollection1() {
//        return lichMoCollection1;
//    }
//
//    public void setLichMoCollection1(Collection<LichMo> lichMoCollection1) {
//        this.lichMoCollection1 = lichMoCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbamoMa != null ? hsbamoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaMo)) {
            return false;
        }
        HsbaMo other = (HsbaMo) object;
        if ((this.hsbamoMa == null && other.hsbamoMa != null) || (this.hsbamoMa != null && !this.hsbamoMa.equals(other.hsbamoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaMo[hsbamoMa=" + hsbamoMa + "]";
    }

}


