/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
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

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_KHO")
@NamedQueries({})
public class DtDmKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_KHO")
    @SequenceGenerator(name = "DT_DM_KHO", sequenceName = "DT_DM_KHO_DTDMKHO_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMKHO_MASO", nullable = false)
    private Integer dtdmkhoMaso;
    @Column(name = "DTDMKHO_MA", nullable = false)
    private String dtdmkhoMa;
    @Column(name = "DTDMKHO_TEN")
    private String dtdmkhoTen;
    @Column(name = "DTDMKHO_THUTUBC")
    private Integer dtdmkhoThutubc;
    @Column(name = "DTDMKHO_CHON")
    private Integer dtdmkhoChon;
    @Column(name = "DTDMKHOA_NGAYGIOCN")
    private Double dtdmkhoaNgaygiocn;
//    @OneToMany(mappedBy = "dtdmkhoMaso")
//    private Collection<PhieuXuatBh> phieuXuatBhCollection;
//    @OneToMany(mappedBy = "dtdmkhoMaso")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection;
//    @OneToMany(mappedBy = "dtdmkhoMaso1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection1;
//    @OneToMany(mappedBy = "dtdmkhoMaso")
//    private Collection<TonKho> tonKhoCollection;
    @JoinColumn(name = "DTDMKHO_KHOCHA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dtdmkhoKhocha;
    @JoinColumn(name = "DTDMKHO_THUKHO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmkhoThukho;

    public DtDmKho() {
    }

    public DtDmKho(Integer dtdmkhoMaso) {
        this.dtdmkhoMaso = dtdmkhoMaso;
    }

    public DtDmKho(Integer dtdmkhoMaso, String dtdmkhoMa) {
        this.dtdmkhoMaso = dtdmkhoMaso;
        this.dtdmkhoMa = dtdmkhoMa;
    }

    public Integer getDtdmkhoMaso() {
        return dtdmkhoMaso;
    }

    public void setDtdmkhoMaso(Integer dtdmkhoMaso) {
        this.dtdmkhoMaso = dtdmkhoMaso;
    }

    public String getDtdmkhoMa() {
        return dtdmkhoMa;
    }

    public void setDtdmkhoMa(String dtdmkhoMa) {
        this.dtdmkhoMa = dtdmkhoMa;
    }

    public String getDtdmkhoTen() {
        return dtdmkhoTen;
    }

    public void setDtdmkhoTen(String dtdmkhoTen) {
        this.dtdmkhoTen = dtdmkhoTen;
    }

    public Integer getDtdmkhoThutubc() {
        return dtdmkhoThutubc;
    }

    public void setDtdmkhoThutubc(Integer dtdmkhoThutubc) {
        this.dtdmkhoThutubc = dtdmkhoThutubc;
    }

    public Integer getDtdmkhoChon() {
        return dtdmkhoChon;
    }

    public void setDtdmkhoChon(Integer dtdmkhoChon) {
        this.dtdmkhoChon = dtdmkhoChon;
    }

    public Double getDtdmkhoaNgaygiocn() {
        return dtdmkhoaNgaygiocn;
    }

    public void setDtdmkhoaNgaygiocn(Double dtdmkhoaNgaygiocn) {
        this.dtdmkhoaNgaygiocn = dtdmkhoaNgaygiocn;
    }

//    public Collection<PhieuXuatBh> getPhieuXuatBhCollection() {
//        return phieuXuatBhCollection;
//    }
//
//    public void setPhieuXuatBhCollection(Collection<PhieuXuatBh> phieuXuatBhCollection) {
//        this.phieuXuatBhCollection = phieuXuatBhCollection;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection() {
//        return phieuNhapKhoCollection;
//    }
//
//    public void setPhieuNhapKhoCollection(Collection<PhieuNhapKho> phieuNhapKhoCollection) {
//        this.phieuNhapKhoCollection = phieuNhapKhoCollection;
//    }

//    public Collection<PhieuNhapKho> getPhieuNhapKhoCollection1() {
//        return phieuNhapKhoCollection1;
//    }
//
//    public void setPhieuNhapKhoCollection1(Collection<PhieuNhapKho> phieuNhapKhoCollection1) {
//        this.phieuNhapKhoCollection1 = phieuNhapKhoCollection1;
//    }

//    public Collection<TonKho> getTonKhoCollection() {
//        return tonKhoCollection;
//    }
//
//    public void setTonKhoCollection(Collection<TonKho> tonKhoCollection) {
//        this.tonKhoCollection = tonKhoCollection;
//    }
    public DmKhoa getDtdmkhoKhocha(boolean create) {
if(create && dtdmkhoKhocha == null) dtdmkhoKhocha = new DmKhoa();
return dtdmkhoKhocha;
}
    public DmKhoa getDtdmkhoKhocha() {
        return dtdmkhoKhocha;
    }

    public void setDtdmkhoKhocha(DmKhoa dtdmkhoKhocha) {
        this.dtdmkhoKhocha = dtdmkhoKhocha;
    }

    public DtDmNhanVien getDtdmkhoThukho(boolean create) {
if(create && dtdmkhoThukho == null) dtdmkhoThukho = new DtDmNhanVien();
return dtdmkhoThukho;
}
    public DtDmNhanVien getDtdmkhoThukho() {
        return dtdmkhoThukho;
    }

    public void setDtdmkhoThukho(DtDmNhanVien dtdmkhoThukho) {
        this.dtdmkhoThukho = dtdmkhoThukho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmkhoMaso != null ? dtdmkhoMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmKho)) {
            return false;
        }
        DtDmKho other = (DtDmKho) object;
        if ((this.dtdmkhoMaso == null && other.dtdmkhoMaso != null) || (this.dtdmkhoMaso != null && !this.dtdmkhoMaso.equals(other.dtdmkhoMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmKho[dtdmkhoMaso=" + dtdmkhoMaso + "]";
    }
}


