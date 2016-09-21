/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

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
@Table(name = "DM_NGUON_CHUONG_TRINH")
@NamedQueries({})
public class DmNguonChuongTrinh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NGUON_CHUONG_TRINH")
    @SequenceGenerator(name = "DM_NGUON_CHUONG_TRINH", sequenceName = "DM_NGUON_CHUONG_TRINH_DMNCT_MA", allocationSize = 1)
    @Column(name = "DMNCT_MASO", nullable = false)
    private Integer dmnctMaso;
    @Column(name = "DMNCT_MA", nullable = false)
    private String dmnctMa;
    @Column(name = "DMNCT_TEN")
    private String dmnctTen;
    @Column(name = "DMNCT_THUTUCBC")
    private Short dmnctThutucbc;
    @Column(name = "DMNCT_DEFA")
    private Boolean dmnctDefa;
    @Column(name = "DMNCT_NGAYGIOCN")
    private Double dmnctNgaygiocn;
    @Column(name = "DMNCT_DT")
    private Boolean dmnctDt;
    @Column(name = "DMNCT_QL")
    private Boolean dmnctQl;
    @Column(name = "DMNCT_DP")
    private Boolean dmnctDp;
//    @OneToMany(mappedBy = "thuocnoitruNguon")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruNguon1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
//    @OneToMany(mappedBy = "dmnctMaso")
//    private Collection<CtTraKho> ctTraKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnctMaso")
//    private Collection<CtXuatKho> ctXuatKhoCollection;
//    @OneToMany(mappedBy = "dmnctMaso")
//    private Collection<DmNhaCungCap> dmNhaCungCapCollection;
//    @OneToMany(mappedBy = "dmnctMaso1")
//    private Collection<DmNhaCungCap> dmNhaCungCapCollection1;
//    @OneToMany(mappedBy = "dmnctMaso")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
//    @OneToMany(mappedBy = "dmnctMaso")
//    private Collection<CtXuatBh> ctXuatBhCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnctMaso")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmnctMaso1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection1;
//    @OneToMany(mappedBy = "dmnctMaso")
//    private Collection<TonKho> tonKhoCollection;
    @JoinColumn(name = "DMLNCT_MASO", referencedColumnName = "DMLNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiNguonChuongTrinh dmlnctMaso;

//    @OneToMany(mappedBy = "dmnctMaso")
//    private Collection<DtDmKhach> dtDmKhachCollection;
//    @OneToMany(mappedBy = "dmnctMaso1")
//    private Collection<DtDmKhach> dtDmKhachCollection1;

    public DmNguonChuongTrinh() {
    }

    public DmNguonChuongTrinh(Integer dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonChuongTrinh(Integer dmnctMaso, String dmnctMa) {
        this.dmnctMaso = dmnctMaso;
        this.dmnctMa = dmnctMa;
    }

    public Integer getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(Integer dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public String getDmnctMa() {
        return dmnctMa;
    }

    public void setDmnctMa(String dmnctMa) {
        this.dmnctMa = dmnctMa;
    }

    public String getDmnctTen() {
        return dmnctTen;
    }

    public void setDmnctTen(String dmnctTen) {
        this.dmnctTen = dmnctTen;
    }

    public Short getDmnctThutucbc() {
        return dmnctThutucbc;
    }

    public void setDmnctThutucbc(Short dmnctThutucbc) {
        this.dmnctThutucbc = dmnctThutucbc;
    }

    public Boolean getDmnctDefa() {
        return dmnctDefa;
    }

    public void setDmnctDefa(Boolean dmnctDefa) {
        this.dmnctDefa = dmnctDefa;
    }

    public Double getDmnctNgaygiocn() {
        return dmnctNgaygiocn;
    }

    public void setDmnctNgaygiocn(Double dmnctNgaygiocn) {
        this.dmnctNgaygiocn = dmnctNgaygiocn;
    }

    public Boolean getDmnctDt() {
        return dmnctDt;
    }

    public void setDmnctDt(Boolean dmnctDt) {
        this.dmnctDt = dmnctDt;
    }

    public Boolean getDmnctQl() {
        return dmnctQl;
    }

    public void setDmnctQl(Boolean dmnctQl) {
        this.dmnctQl = dmnctQl;
    }

    public Boolean getDmnctDp() {
        return dmnctDp;
    }

    public void setDmnctDp(Boolean dmnctDp) {
        this.dmnctDp = dmnctDp;
    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection() {
//        return thuocNoiTruCollection;
//    }
//
//    public void setThuocNoiTruCollection(Collection<ThuocNoiTru> thuocNoiTruCollection) {
//        this.thuocNoiTruCollection = thuocNoiTruCollection;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection1() {
//        return thuocNoiTruCollection1;
//    }
//
//    public void setThuocNoiTruCollection1(Collection<ThuocNoiTru> thuocNoiTruCollection1) {
//        this.thuocNoiTruCollection1 = thuocNoiTruCollection1;
//    }

//    public Collection<CtTraKho> getCtTraKhoCollection() {
//        return ctTraKhoCollection;
//    }
//
//    public void setCtTraKhoCollection(Collection<CtTraKho> ctTraKhoCollection) {
//        this.ctTraKhoCollection = ctTraKhoCollection;
//    }

//    public Collection<CtXuatKho> getCtXuatKhoCollection() {
//        return ctXuatKhoCollection;
//    }
//
//    public void setCtXuatKhoCollection(Collection<CtXuatKho> ctXuatKhoCollection) {
//        this.ctXuatKhoCollection = ctXuatKhoCollection;
//    }

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

//    public Collection<CtPhieuDt> getCtPhieuDtCollection() {
//        return ctPhieuDtCollection;
//    }
//
//    public void setCtPhieuDtCollection(Collection<CtPhieuDt> ctPhieuDtCollection) {
//        this.ctPhieuDtCollection = ctPhieuDtCollection;
//    }

//    public Collection<CtXuatBh> getCtXuatBhCollection() {
//        return ctXuatBhCollection;
//    }
//
//    public void setCtXuatBhCollection(Collection<CtXuatBh> ctXuatBhCollection) {
//        this.ctXuatBhCollection = ctXuatBhCollection;
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

    public DmLoaiNguonChuongTrinh getDmlnctMaso(boolean create) {
if(create && dmlnctMaso == null) dmlnctMaso = new DmLoaiNguonChuongTrinh();
return dmlnctMaso;
}
    public DmLoaiNguonChuongTrinh getDmlnctMaso() {
        return dmlnctMaso;
    }

    public void setDmlnctMaso(DmLoaiNguonChuongTrinh dmlnctMaso) {
        this.dmlnctMaso = dmlnctMaso;
    }

   

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnctMaso != null ? dmnctMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNguonChuongTrinh)) {
            return false;
        }
        DmNguonChuongTrinh other = (DmNguonChuongTrinh) object;
        if ((this.dmnctMaso == null && other.dmnctMaso != null) || (this.dmnctMaso != null && !this.dmnctMaso.equals(other.dmnctMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNguonChuongTrinh[dmnctMaso=" + dmnctMaso + "]";
    }

}


