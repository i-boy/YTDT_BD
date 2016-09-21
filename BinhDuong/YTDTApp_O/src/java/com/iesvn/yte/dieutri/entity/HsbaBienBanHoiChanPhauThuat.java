/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKhoa;
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
@Table(name = "HSBA_BIEN_BAN_HOI_CHAN_PT")
@NamedQueries({
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findAll", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptMaso", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptMaso = :hsbabbhcptMaso"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptChandoan", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptChandoan = :hsbabbhcptChandoan"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptNgaycap", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptNgaycap = :hsbabbhcptNgaycap"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByNhanvienMa", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.nhanvienMa = :nhanvienMa"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptNgaygiocn", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptNgaygiocn = :hsbabbhcptNgaygiocn"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptMa", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptMa = :hsbabbhcptMa"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptNgaytghc", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptNgaytghc = :hsbabbhcptNgaytghc"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptNgayvaovien", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptNgayvaovien = :hsbabbhcptNgayvaovien"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptNgaypttt", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptNgaypttt = :hsbabbhcptNgaypttt"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptLdvv", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptLdvv = :hsbabbhcptLdvv"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptTomtat", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptTomtat = :hsbabbhcptTomtat"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptYkien", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptYkien = :hsbabbhcptYkien"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptTienluong", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptTienluong = :hsbabbhcptTienluong"),
    @NamedQuery(name = "HsbaBienBanHoiChanPhauThuat.findByHsbabbhcptHinhthuc", query = "SELECT h FROM HsbaBienBanHoiChanPhauThuat h WHERE h.hsbabbhcptHinhthuc = :hsbabbhcptHinhthuc")})
public class HsbaBienBanHoiChanPhauThuat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_BBHC_PHAU_THUAT")
    @SequenceGenerator(name = "HSBA_BBHC_PHAU_THUAT", sequenceName = "HSBA_BIEN_BAN_HOI_CHAN_PT_HSBA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBABBHCPT_MASO")
    private Integer hsbabbhcptMaso;
    @Column(name = "HSBABBHCPT_CHANDOAN")
    private String hsbabbhcptChandoan;
    @Column(name = "HSBABBHCPT_DTSPT")
    private String hsbabbhcptDtspt;
    @Column(name = "HSBABBHCPT_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbabbhcptNgaycap;
    @Column(name = "HSBABBHCPT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbabbhcptNgaygiocn;
    @Column(name = "HSBABBHCPT_MA")
    private String hsbabbhcptMa;
    @Column(name = "HSBABBHCPT_NGAYTGHC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbabbhcptNgaytghc;
    @Column(name = "HSBABBHCPT_NGAYVAOVIEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbabbhcptNgayvaovien;
    @Column(name = "HSBABBHCPT_NGAYPTTT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbabbhcptNgaypttt;
    @Column(name = "HSBABBHCPT_LDVV")
    private String hsbabbhcptLdvv;
    @Column(name = "HSBABBHCPT_TOMTAT")
    private String hsbabbhcptTomtat;
    @Column(name = "HSBABBHCPT_YKIEN")
    private String hsbabbhcptYkien;
    @Column(name = "HSBABBHCPT_TIENLUONG")
    private String hsbabbhcptTienluong;
    @Column(name = "HSBABBHCPT_HINHTHUC")
    private Integer hsbabbhcptHinhthuc;
    @JoinColumn(name = "HSBABBHCPT_THUKY", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbabbhcptThuky;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBABBHCPT_PHUONGPHAP_VOCAM", referencedColumnName = "DTDMVOCAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmVoCam hsbabbhcptPhuongphapVocam;
//    @JoinColumn(name = "HSBABBHCPT_PHUONGPHAP", referencedColumnName = "DTDMPHAUTHUAT_MASO")
//    @ManyToOne (fetch=FetchType.LAZY)
//    private DtDmPhauThuat hsbabbhcptPhuongphap;
    @JoinColumn(name = "HSBABBHCPT_PHUONGPHAP", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia hsbabbhcptPhuongphap;
    
    @JoinColumn(name = "HSBABBHCPT_CHUANDOANMA", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbabbhcptChuandoanma;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSBABBHCPT_KHOAHOICHAN", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hsbabbhcptKhoahoichan;

    public String getHsbabbhcptDtspt() {
        return hsbabbhcptDtspt;
    }

    public void setHsbabbhcptDtspt(String hsbabbhcptDtspt) {
        this.hsbabbhcptDtspt = hsbabbhcptDtspt;
    }
//    @OneToMany(mappedBy = "hsbaBienBanHoiChanPhauThuat")
//    private Collection<HsbaBienBanHoiChanPhauThuatGm> hsbaBienBanHoiChanPhauThuatGmCollection;
//    @OneToMany(mappedBy = "hsbaBienBanHoiChanPhauThuat")
//    private Collection<HsbaBienBanHoiChanPhauThuatTpk> hsbaBienBanHoiChanPhauThuatTpkCollection;
//    @OneToMany(mappedBy = "hsbaBienBanHoiChanPhauThuat")
//    private Collection<HsbaBienBanHoiChanPhauThuatPtv> hsbaBienBanHoiChanPhauThuatPtvCollection;

    
    public HsbaBienBanHoiChanPhauThuat() {
    }

    public HsbaBienBanHoiChanPhauThuat(Integer hsbabbhcptMaso) {
        this.hsbabbhcptMaso = hsbabbhcptMaso;
    }

    public Integer getHsbabbhcptMaso() {
        return hsbabbhcptMaso;
    }

    public void setHsbabbhcptMaso(Integer hsbabbhcptMaso) {
        this.hsbabbhcptMaso = hsbabbhcptMaso;
    }

    public String getHsbabbhcptChandoan() {
        return hsbabbhcptChandoan;
    }

    public void setHsbabbhcptChandoan(String hsbabbhcptChandoan) {
        this.hsbabbhcptChandoan = hsbabbhcptChandoan;
    }

    public Date getHsbabbhcptNgaycap() {
        return hsbabbhcptNgaycap;
    }

    public void setHsbabbhcptNgaycap(Date hsbabbhcptNgaycap) {
        this.hsbabbhcptNgaycap = hsbabbhcptNgaycap;
    }

   

    public Date getHsbabbhcptNgaygiocn() {
        return hsbabbhcptNgaygiocn;
    }

    public void setHsbabbhcptNgaygiocn(Date hsbabbhcptNgaygiocn) {
        this.hsbabbhcptNgaygiocn = hsbabbhcptNgaygiocn;
    }

    public String getHsbabbhcptMa() {
        return hsbabbhcptMa;
    }

    public void setHsbabbhcptMa(String hsbabbhcptMa) {
        this.hsbabbhcptMa = hsbabbhcptMa;
    }

    public Date getHsbabbhcptNgaytghc() {
        return hsbabbhcptNgaytghc;
    }

    public void setHsbabbhcptNgaytghc(Date hsbabbhcptNgaytghc) {
        this.hsbabbhcptNgaytghc = hsbabbhcptNgaytghc;
    }

    public Date getHsbabbhcptNgayvaovien() {
        return hsbabbhcptNgayvaovien;
    }

    public void setHsbabbhcptNgayvaovien(Date hsbabbhcptNgayvaovien) {
        this.hsbabbhcptNgayvaovien = hsbabbhcptNgayvaovien;
    }

    public Date getHsbabbhcptNgaypttt() {
        return hsbabbhcptNgaypttt;
    }

    public void setHsbabbhcptNgaypttt(Date hsbabbhcptNgaypttt) {
        this.hsbabbhcptNgaypttt = hsbabbhcptNgaypttt;
    }

    public String getHsbabbhcptLdvv() {
        return hsbabbhcptLdvv;
    }

    public void setHsbabbhcptLdvv(String hsbabbhcptLdvv) {
        this.hsbabbhcptLdvv = hsbabbhcptLdvv;
    }

    public String getHsbabbhcptTomtat() {
        return hsbabbhcptTomtat;
    }

    public void setHsbabbhcptTomtat(String hsbabbhcptTomtat) {
        this.hsbabbhcptTomtat = hsbabbhcptTomtat;
    }

    public String getHsbabbhcptYkien() {
        return hsbabbhcptYkien;
    }

    public void setHsbabbhcptYkien(String hsbabbhcptYkien) {
        this.hsbabbhcptYkien = hsbabbhcptYkien;
    }

    public String getHsbabbhcptTienluong() {
        return hsbabbhcptTienluong;
    }

    public void setHsbabbhcptTienluong(String hsbabbhcptTienluong) {
        this.hsbabbhcptTienluong = hsbabbhcptTienluong;
    }

    public Integer getHsbabbhcptHinhthuc() {
        return hsbabbhcptHinhthuc;
    }

    public void setHsbabbhcptHinhthuc(Integer hsbabbhcptHinhthuc) {
        this.hsbabbhcptHinhthuc = hsbabbhcptHinhthuc;
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

    public DmBenhIcd getHsbabbhcptChuandoanma() {
        return hsbabbhcptChuandoanma;
    }

    public DmBenhIcd getHsbabbhcptChuandoanma(boolean create) {
        if(create && hsbabbhcptChuandoanma == null) hsbabbhcptChuandoanma = new DmBenhIcd();
        return hsbabbhcptChuandoanma;
    }



    public void setHsbabbhcptChuandoanma(DmBenhIcd hsbabbhcptChuandoanma) {
        this.hsbabbhcptChuandoanma = hsbabbhcptChuandoanma;
    }

    public DmKhoa getHsbabbhcptKhoahoichan() {
        return hsbabbhcptKhoahoichan;
    }

    public DmKhoa getHsbabbhcptKhoahoichan(boolean create) {
        if(create && hsbabbhcptKhoahoichan == null) hsbabbhcptKhoahoichan = new DmKhoa();
        return hsbabbhcptKhoahoichan;
    }

    public void setHsbabbhcptKhoahoichan(DmKhoa hsbabbhcptKhoahoichan) {
        this.hsbabbhcptKhoahoichan = hsbabbhcptKhoahoichan;
    }

    public DtDmClsBangGia getHsbabbhcptPhuongphap() {
        return hsbabbhcptPhuongphap;
    }
    public   DtDmClsBangGia getHsbabbhcptPhuongphap(boolean create) {
        if(create &&hsbabbhcptPhuongphap  == null) hsbabbhcptPhuongphap = new DtDmClsBangGia();
        return hsbabbhcptPhuongphap;
    }

    public void setHsbabbhcptPhuongphap(DtDmClsBangGia hsbabbhcptPhuongphap) {
        this.hsbabbhcptPhuongphap = hsbabbhcptPhuongphap;
    }

    public DtDmVoCam getHsbabbhcptPhuongphapVocam() {
        return hsbabbhcptPhuongphapVocam;
    }
    public   DtDmVoCam getHsbabbhcptPhuongphapVocam(boolean create) {
        if(create &&  hsbabbhcptPhuongphapVocam== null) hsbabbhcptPhuongphapVocam = new DtDmVoCam();
        return hsbabbhcptPhuongphapVocam;
    }

    public void setHsbabbhcptPhuongphapVocam(DtDmVoCam hsbabbhcptPhuongphapVocam) {
        this.hsbabbhcptPhuongphapVocam = hsbabbhcptPhuongphapVocam;
    }

    public DtDmNhanVien getHsbabbhcptThuky() {
        return hsbabbhcptThuky;
    }
    public  DtDmNhanVien getHsbabbhcptThuky(boolean create) {
        if(create &&  hsbabbhcptThuky== null)hsbabbhcptThuky  = new DtDmNhanVien();
        return hsbabbhcptThuky;
    }

    public void setHsbabbhcptThuky(DtDmNhanVien hsbabbhcptThuky) {
        this.hsbabbhcptThuky = hsbabbhcptThuky;
    }

    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public  DtDmNhanVien getNhanvienMa(boolean create) {
        if(create &&  nhanvienMa== null)nhanvienMa  = new DtDmNhanVien();
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    
    

//    public Collection<HsbaBienBanHoiChanPhauThuatGm> getHsbaBienBanHoiChanPhauThuatGmCollection() {
//        return hsbaBienBanHoiChanPhauThuatGmCollection;
//    }
//
//    public void setHsbaBienBanHoiChanPhauThuatGmCollection(Collection<HsbaBienBanHoiChanPhauThuatGm> hsbaBienBanHoiChanPhauThuatGmCollection) {
//        this.hsbaBienBanHoiChanPhauThuatGmCollection = hsbaBienBanHoiChanPhauThuatGmCollection;
//    }
//
//    public Collection<HsbaBienBanHoiChanPhauThuatTpk> getHsbaBienBanHoiChanPhauThuatTpkCollection() {
//        return hsbaBienBanHoiChanPhauThuatTpkCollection;
//    }
//
//    public void setHsbaBienBanHoiChanPhauThuatTpkCollection(Collection<HsbaBienBanHoiChanPhauThuatTpk> hsbaBienBanHoiChanPhauThuatTpkCollection) {
//        this.hsbaBienBanHoiChanPhauThuatTpkCollection = hsbaBienBanHoiChanPhauThuatTpkCollection;
//    }
//
//    public Collection<HsbaBienBanHoiChanPhauThuatPtv> getHsbaBienBanHoiChanPhauThuatPtvCollection() {
//        return hsbaBienBanHoiChanPhauThuatPtvCollection;
//    }
//
//    public void setHsbaBienBanHoiChanPhauThuatPtvCollection(Collection<HsbaBienBanHoiChanPhauThuatPtv> hsbaBienBanHoiChanPhauThuatPtvCollection) {
//        this.hsbaBienBanHoiChanPhauThuatPtvCollection = hsbaBienBanHoiChanPhauThuatPtvCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbabbhcptMaso != null ? hsbabbhcptMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaBienBanHoiChanPhauThuat)) {
            return false;
        }
        HsbaBienBanHoiChanPhauThuat other = (HsbaBienBanHoiChanPhauThuat) object;
        if ((this.hsbabbhcptMaso == null && other.hsbabbhcptMaso != null) || (this.hsbabbhcptMaso != null && !this.hsbabbhcptMaso.equals(other.hsbabbhcptMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat[hsbabbhcptMaso=" + hsbabbhcptMaso + "]";
    }

}
