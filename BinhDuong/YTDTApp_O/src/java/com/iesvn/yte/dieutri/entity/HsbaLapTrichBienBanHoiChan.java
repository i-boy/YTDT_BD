/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhIcd;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_LAP_TRICH_BIEN_BAN_HCHAN")
@NamedQueries({
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findAll", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcMaso", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcMaso = :hsbaltbbhcMaso"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcNgaycap", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcNgaycap = :hsbaltbbhcNgaycap"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByNhanvienMa", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.nhanvienMa = :nhanvienMa"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcNgaygiocn", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcNgaygiocn = :hsbaltbbhcNgaygiocn"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcMa", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcMa = :hsbaltbbhcMa"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcThoigianhoichan", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcThoigianhoichan = :hsbaltbbhcThoigianhoichan"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcPhuongphapVocam", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcPhuongphapVocam = :hsbaltbbhcPhuongphapVocam"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcTomtat", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcTomtat = :hsbaltbbhcTomtat"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcKetluan", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcKetluan = :hsbaltbbhcKetluan"),
    @NamedQuery(name = "HsbaLapTrichBienBanHoiChan.findByHsbaltbbhcHuongdandieutri", query = "SELECT h FROM HsbaLapTrichBienBanHoiChan h WHERE h.hsbaltbbhcHuongdandieutri = :hsbaltbbhcHuongdandieutri")})
public class HsbaLapTrichBienBanHoiChan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_LAP_TRICH_BBHC")
    @SequenceGenerator(name = "HSBA_LAP_TRICH_BBHC", sequenceName = "HSBA_LAP_TRICH_BIEN_BAN_HCHAN_", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBALTBBHC_MASO")
    private Integer hsbaltbbhcMaso;
    @Column(name = "HSBALTBBHC_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbaltbbhcNgaycap;
    @Column(name = "NHANVIEN_MA")
    private Integer nhanvienMa;
    @Column(name = "HSBALTBBHC_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbaltbbhcNgaygiocn;
    @Column(name = "HSBALTBBHC_MA")
    private String hsbaltbbhcMa;
    @Column(name = "HSBALTBBHC_THOIGIANHOICHAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbaltbbhcThoigianhoichan;
    @Column(name = "HSBALTBBHC_PHUONGPHAP_VOCAM")
    private Integer hsbaltbbhcPhuongphapVocam;
    @Column(name = "HSBALTBBHC_TOMTAT")
    private String hsbaltbbhcTomtat;
    @Column(name = "HSBALTBBHC_KETLUAN")
    private String hsbaltbbhcKetluan;
    @Column(name = "HSBALTBBHC_HUONGDANDIEUTRI")
    private String hsbaltbbhcHuongdandieutri;
    @JoinColumn(name = "HSBALTBBHC_THUKY", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbaltbbhcThuky;
    @JoinColumn(name = "HSBALTBBHC_CHUTOA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbaltbbhcChutoa;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSBALTBBHC_CHUANDOANMA", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbaltbbhcChuandoanma;
//    @OneToMany(mappedBy = "hsbaLapTrichBienBanHoiChan")
//    private Collection<HsbaLapTrichBienBanHoiChanBacsi> hsbaLapTrichBienBanHoiChanBacsiCollection;


    public DmBenhIcd getHsbaltbbhcChuandoanma() {
        return hsbaltbbhcChuandoanma;
    }

    public DmBenhIcd getHsbaltbbhcChuandoanma(boolean create) {
        if(create && hsbaltbbhcChuandoanma == null) hsbaltbbhcChuandoanma = new DmBenhIcd();
        return hsbaltbbhcChuandoanma;
    }

    

    public void setHsbaltbbhcChuandoanma(DmBenhIcd hsbaltbbhcChuandoanma) {
        this.hsbaltbbhcChuandoanma = hsbaltbbhcChuandoanma;
    }

    public DtDmNhanVien getHsbaltbbhcChutoa() {
        return hsbaltbbhcChutoa;
    }

    public DtDmNhanVien getHsbaltbbhcChutoa(boolean create) {
        if(create && hsbaltbbhcChutoa == null) hsbaltbbhcChutoa = new DtDmNhanVien();
        return hsbaltbbhcChutoa;
    }

    public void setHsbaltbbhcChutoa(DtDmNhanVien hsbaltbbhcChutoa) {
        this.hsbaltbbhcChutoa = hsbaltbbhcChutoa;
    }

    public DtDmNhanVien getHsbaltbbhcThuky() {
        return hsbaltbbhcThuky;
    }

    public DtDmNhanVien getHsbaltbbhcThuky(boolean create) {
        if(create && hsbaltbbhcThuky == null) hsbaltbbhcThuky = new DtDmNhanVien();
        return hsbaltbbhcThuky;
    }

    public void setHsbaltbbhcThuky(DtDmNhanVien hsbaltbbhcThuky) {
        this.hsbaltbbhcThuky = hsbaltbbhcThuky;
    }

    
    public HsbaLapTrichBienBanHoiChan() {
    }

    public HsbaLapTrichBienBanHoiChan(Integer hsbaltbbhcMaso) {
        this.hsbaltbbhcMaso = hsbaltbbhcMaso;
    }

    public Integer getHsbaltbbhcMaso() {
        return hsbaltbbhcMaso;
    }

    public void setHsbaltbbhcMaso(Integer hsbaltbbhcMaso) {
        this.hsbaltbbhcMaso = hsbaltbbhcMaso;
    }

    public Date getHsbaltbbhcNgaycap() {
        return hsbaltbbhcNgaycap;
    }

    public void setHsbaltbbhcNgaycap(Date hsbaltbbhcNgaycap) {
        this.hsbaltbbhcNgaycap = hsbaltbbhcNgaycap;
    }

    public Integer getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(Integer nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public Date getHsbaltbbhcNgaygiocn() {
        return hsbaltbbhcNgaygiocn;
    }

    public void setHsbaltbbhcNgaygiocn(Date hsbaltbbhcNgaygiocn) {
        this.hsbaltbbhcNgaygiocn = hsbaltbbhcNgaygiocn;
    }

    public String getHsbaltbbhcMa() {
        return hsbaltbbhcMa;
    }

    public void setHsbaltbbhcMa(String hsbaltbbhcMa) {
        this.hsbaltbbhcMa = hsbaltbbhcMa;
    }

    public Date getHsbaltbbhcThoigianhoichan() {
        return hsbaltbbhcThoigianhoichan;
    }

    public void setHsbaltbbhcThoigianhoichan(Date hsbaltbbhcThoigianhoichan) {
        this.hsbaltbbhcThoigianhoichan = hsbaltbbhcThoigianhoichan;
    }

    public Integer getHsbaltbbhcPhuongphapVocam() {
        return hsbaltbbhcPhuongphapVocam;
    }

    public void setHsbaltbbhcPhuongphapVocam(Integer hsbaltbbhcPhuongphapVocam) {
        this.hsbaltbbhcPhuongphapVocam = hsbaltbbhcPhuongphapVocam;
    }

    public String getHsbaltbbhcTomtat() {
        return hsbaltbbhcTomtat;
    }

    public void setHsbaltbbhcTomtat(String hsbaltbbhcTomtat) {
        this.hsbaltbbhcTomtat = hsbaltbbhcTomtat;
    }

    public String getHsbaltbbhcKetluan() {
        return hsbaltbbhcKetluan;
    }

    public void setHsbaltbbhcKetluan(String hsbaltbbhcKetluan) {
        this.hsbaltbbhcKetluan = hsbaltbbhcKetluan;
    }

    public String getHsbaltbbhcHuongdandieutri() {
        return hsbaltbbhcHuongdandieutri;
    }

    public void setHsbaltbbhcHuongdandieutri(String hsbaltbbhcHuongdandieutri) {
        this.hsbaltbbhcHuongdandieutri = hsbaltbbhcHuongdandieutri;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien(boolean create) {
        if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }
   
//    public Collection<HsbaLapTrichBienBanHoiChanBacsi> getHsbaLapTrichBienBanHoiChanBacsiCollection() {
//        return hsbaLapTrichBienBanHoiChanBacsiCollection;
//    }
//
//    public void setHsbaLapTrichBienBanHoiChanBacsiCollection(Collection<HsbaLapTrichBienBanHoiChanBacsi> hsbaLapTrichBienBanHoiChanBacsiCollection) {
//        this.hsbaLapTrichBienBanHoiChanBacsiCollection = hsbaLapTrichBienBanHoiChanBacsiCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbaltbbhcMaso != null ? hsbaltbbhcMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaLapTrichBienBanHoiChan)) {
            return false;
        }
        HsbaLapTrichBienBanHoiChan other = (HsbaLapTrichBienBanHoiChan) object;
        if ((this.hsbaltbbhcMaso == null && other.hsbaltbbhcMaso != null) || (this.hsbaltbbhcMaso != null && !this.hsbaltbbhcMaso.equals(other.hsbaltbbhcMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan[hsbaltbbhcMaso=" + hsbaltbbhcMaso + "]";
    }

}
