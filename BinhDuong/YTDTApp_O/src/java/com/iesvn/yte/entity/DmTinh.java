/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_TINH")
@NamedQueries({})
public class DmTinh implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_THUOC_HOAT_CHAT")
    @SequenceGenerator(name = "DM_THUOC_HOAT_CHAT", sequenceName = "DM_TINH_DMTINH_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMTINH_MASO", nullable = false)
    private Integer dmtinhMaso;
    @Column(name = "DMTINH_MA", nullable = false)
    private String dmtinhMa;
    @Column(name = "DMHUYEN_MA")
    private Boolean dmhuyenMa;
    @Column(name = "DMXA_MA")
    private Boolean dmxaMa;
    @Column(name = "DMTINH_TEN", nullable = false)
    private String dmtinhTen;
    
    
    @Column(name = "DMTINH_MABHYT", nullable = false)
    private String dmtinhBHYT;
        
    
    @Column(name = "DMTINH_DEFA", nullable = false)
    private Boolean dmtinhDefa;
    @Column(name = "DMTINH_NGAYGIOCN")
    private Double dmtinhNgaygiocn;
    @Column(name = "DMTINH_CHON")
    private Boolean dmtinhChon;
//    @OneToMany(mappedBy = "dmtinhMaso")
//    private Collection<DmNhaCungCap> dmNhaCungCapCollection;
//    @OneToMany(mappedBy = "dmtinhMaso1")
//    private Collection<DmNhaCungCap> dmNhaCungCapCollection1;
//    @OneToMany(mappedBy = "dmtinhMaso")
//    private Collection<DmBenhVien> dmBenhVienCollection;
//    @OneToMany(mappedBy = "dmtinhMaso1")
//    private Collection<DmBenhVien> dmBenhVienCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmtinhMaso")
//    private Collection<DmHuyen> dmHuyenCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmtinhMaso1")
//    private Collection<DmHuyen> dmHuyenCollection1;
//    @OneToMany(mappedBy = "dmtinhMaso")
//    private Collection<DmDonVi> dmDonViCollection;
//    @OneToMany(mappedBy = "dmtinhMaso1")
//    private Collection<DmDonVi> dmDonViCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmtinhMaso")
//    private Collection<DtDmKhach> dtDmKhachCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmtinhMaso1")
//    private Collection<DtDmKhach> dtDmKhachCollection1;
//    @OneToMany(mappedBy = "tinhMa")
//    private Collection<BenhNhan> benhNhanCollection;
//    @OneToMany(mappedBy = "tinhMa1")
//    private Collection<BenhNhan> benhNhanCollection1;
    public DmTinh() {
    }

    public DmTinh(Integer dmtinhMaso) {
        this.dmtinhMaso = dmtinhMaso;
    }

    public DmTinh(Integer dmtinhMaso, String dmtinhMa, String dmtinhTen) {
        this.dmtinhMaso = dmtinhMaso;
        this.dmtinhMa = dmtinhMa;
        this.dmtinhTen = dmtinhTen;
    }

    public Integer getDmtinhMaso() {
        return dmtinhMaso;
    }

    public void setDmtinhMaso(Integer dmtinhMaso) {
        this.dmtinhMaso = dmtinhMaso;
    }

    public String getDmtinhMa() {
        return dmtinhMa;
    }

    public void setDmtinhMa(String dmtinhMa) {
        this.dmtinhMa = dmtinhMa;
    }

    public Boolean getDmhuyenMa() {
        return dmhuyenMa;
    }

    public void setDmhuyenMa(Boolean dmhuyenMa) {
        this.dmhuyenMa = dmhuyenMa;
    }

    public Boolean getDmxaMa() {
        return dmxaMa;
    }

    public void setDmxaMa(Boolean dmxaMa) {
        this.dmxaMa = dmxaMa;
    }

    public String getDmtinhTen() {
        return dmtinhTen;
    }

    public void setDmtinhTen(String dmtinhTen) {
        this.dmtinhTen = dmtinhTen;
    }

    public Double getDmtinhNgaygiocn() {
        return dmtinhNgaygiocn;
    }

    public void setDmtinhNgaygiocn(Double dmtinhNgaygiocn) {
        this.dmtinhNgaygiocn = dmtinhNgaygiocn;
    }

    public Boolean getDmtinhChon() {
        return dmtinhChon;
    }

    public void setDmtinhChon(Boolean dmtinhChon) {
        this.dmtinhChon = dmtinhChon;
    }

//    public Collection<DmNhaCungCap> getDmNhaCungCapCollection() {
//        return dmNhaCungCapCollection;
//    }
//
//    public void setDmNhaCungCapCollection(Collection<DmNhaCungCap> dmNhaCungCapCollection) {
//        this.dmNhaCungCapCollection = dmNhaCungCapCollection;
//    }

//    public Collection<DmNhaCungCap> getDmNhaCungCapCollection1() {
//        return dmNhaCungCapCollection1;
//    }
//
//    public void setDmNhaCungCapCollection1(Collection<DmNhaCungCap> dmNhaCungCapCollection1) {
//        this.dmNhaCungCapCollection1 = dmNhaCungCapCollection1;
//    }

//    public Collection<DmBenhVien> getDmBenhVienCollection() {
//        return dmBenhVienCollection;
//    }
//
//    public void setDmBenhVienCollection(Collection<DmBenhVien> dmBenhVienCollection) {
//        this.dmBenhVienCollection = dmBenhVienCollection;
//    }

//    public Collection<DmBenhVien> getDmBenhVienCollection1() {
//        return dmBenhVienCollection1;
//    }
//
//    public void setDmBenhVienCollection1(Collection<DmBenhVien> dmBenhVienCollection1) {
//        this.dmBenhVienCollection1 = dmBenhVienCollection1;
//    }

//    public Collection<DmHuyen> getDmHuyenCollection() {
//        return dmHuyenCollection;
//    }
//
//    public void setDmHuyenCollection(Collection<DmHuyen> dmHuyenCollection) {
//        this.dmHuyenCollection = dmHuyenCollection;
//    }

//    public Collection<DmHuyen> getDmHuyenCollection1() {
//        return dmHuyenCollection1;
//    }
//
//    public void setDmHuyenCollection1(Collection<DmHuyen> dmHuyenCollection1) {
//        this.dmHuyenCollection1 = dmHuyenCollection1;
//    }

//    public Collection<DmDonVi> getDmDonViCollection() {
//        return dmDonViCollection;
//    }
//
//    public void setDmDonViCollection(Collection<DmDonVi> dmDonViCollection) {
//        this.dmDonViCollection = dmDonViCollection;
//    }

//    public Collection<DmDonVi> getDmDonViCollection1() {
//        return dmDonViCollection1;
//    }
//
//    public void setDmDonViCollection1(Collection<DmDonVi> dmDonViCollection1) {
//        this.dmDonViCollection1 = dmDonViCollection1;
//    }

//    public Collection<DtDmKhach> getDtDmKhachCollection() {
//        return dtDmKhachCollection;
//    }
//
//    public void setDtDmKhachCollection(Collection<DtDmKhach> dtDmKhachCollection) {
//        this.dtDmKhachCollection = dtDmKhachCollection;
//    }

//    public Collection<DtDmKhach> getDtDmKhachCollection1() {
//        return dtDmKhachCollection1;
//    }
//
//    public void setDtDmKhachCollection1(Collection<DtDmKhach> dtDmKhachCollection1) {
//        this.dtDmKhachCollection1 = dtDmKhachCollection1;
//    }

//    public Collection<BenhNhan> getBenhNhanCollection() {
//        return benhNhanCollection;
//    }
//
//    public void setBenhNhanCollection(Collection<BenhNhan> benhNhanCollection) {
//        this.benhNhanCollection = benhNhanCollection;
//    }

//    public Collection<BenhNhan> getBenhNhanCollection1() {
//        return benhNhanCollection1;
//    }
//
//    public void setBenhNhanCollection1(Collection<BenhNhan> benhNhanCollection1) {
//        this.benhNhanCollection1 = benhNhanCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getDmtinhMaso() != null ? getDmtinhMaso().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTinh)) {
            return false;
        }
        DmTinh other = (DmTinh) object;
        if ((this.getDmtinhMaso() == null && other.getDmtinhMaso() != null) || (this.getDmtinhMaso() != null && !this.dmtinhMaso.equals(other.dmtinhMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmTinh[dmtinhMaso=" + getDmtinhMaso() + "]";
    }

    public Boolean getDmtinhDefa() {
        return dmtinhDefa;
    }

    public void setDmtinhDefa(Boolean dmtinhDefa) {
        this.dmtinhDefa = dmtinhDefa;
    }

    /**
     * @return the dmtinhBHYT
     */
    public String getDmtinhBHYT() {
        return dmtinhBHYT;
    }

    /**
     * @param dmtinhBHYT the dmtinhBHYT to set
     */
    public void setDmtinhBHYT(String dmtinhBHYT) {
        this.dmtinhBHYT = dmtinhBHYT;
    }
}


