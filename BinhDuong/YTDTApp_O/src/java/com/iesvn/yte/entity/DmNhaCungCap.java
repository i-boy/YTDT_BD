/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

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
@Table(name = "DM_NHA_CUNG_CAP")
@NamedQueries({})
public class DmNhaCungCap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NHA_CUNG_CAP")
    @SequenceGenerator(name = "DM_NHA_CUNG_CAP", sequenceName = "DM_NHA_CUNG_CAP_DMNHACUNGCAP_M", allocationSize = 1)
    @Column(name = "DMNHACUNGCAP_MASO", nullable = false)
    private Integer dmnhacungcapMaso;
    @Column(name = "DMNHACUNGCAP_MA", nullable = false)
    private String dmnhacungcapMa;
    @Column(name = "DMNHACUNGCAP_TEN")
    private String dmnhacungcapTen;
    @Column(name = "DMNHACUNGCAP_DIACHI")
    private String dmnhacungcapDiachi;
    @Column(name = "DMNHACUNGCAP_DIENTHOAI")
    private String dmnhacungcapDienthoai;
    @Column(name = "DMNHACUNGCAP_MASOTHUE")
    private String dmnhacungcapMasothue;
    @Column(name = "DMNHACUNGCAP_FAX")
    private String dmnhacungcapFax;
    @Column(name = "DMNHACUNGCAP_PHANLOAI")
    private String dmnhacungcapPhanloai;
    @Column(name = "DMNHACUNGCAP_PHANBIET")
    private String dmnhacungcapPhanbiet;
    @Column(name = "DMNHACUNGCAP_NGAYLV")
    @Temporal(TemporalType.DATE)
    private Date dmnhacungcapNgaylv;
    @Column(name = "DMNHACUNGCAP_NGAYGIOCN")
    private Double dmnhacungcapNgaygiocn;
    @Column(name = "DMNHACUNGCAP_QL")
    private Boolean dmnhacungcapQl;
    @Column(name = "DMNHACUNGCAP_DT")
    private Boolean dmnhacungcapDt;
    @Column(name = "DMNHACUNGCAP_DP")
    private Boolean dmnhacungcapDp;
    @JoinColumn(name = "DMTINH_MASO", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh dmtinhMaso;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnoibanMa")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmnoibanMa1")
//    private Collection<PhieuNhapKho> phieuNhapKhoCollection1;

    public DmNhaCungCap() {
    }

    public DmNhaCungCap(Integer dmnhacungcapMaso) {
        this.dmnhacungcapMaso = dmnhacungcapMaso;
    }

    public DmNhaCungCap(Integer dmnhacungcapMaso, String dmnhacungcapMa) {
        this.dmnhacungcapMaso = dmnhacungcapMaso;
        this.dmnhacungcapMa = dmnhacungcapMa;
    }

    public Integer getDmnhacungcapMaso() {
        return dmnhacungcapMaso;
    }

    public void setDmnhacungcapMaso(Integer dmnhacungcapMaso) {
        this.dmnhacungcapMaso = dmnhacungcapMaso;
    }

    public String getDmnhacungcapMa() {
        return dmnhacungcapMa;
    }

    public void setDmnhacungcapMa(String dmnhacungcapMa) {
        this.dmnhacungcapMa = dmnhacungcapMa;
    }

    public String getDmnhacungcapTen() {
        return dmnhacungcapTen;
    }

    public void setDmnhacungcapTen(String dmnhacungcapTen) {
        this.dmnhacungcapTen = dmnhacungcapTen;
    }

    public String getDmnhacungcapDiachi() {
        return dmnhacungcapDiachi;
    }

    public void setDmnhacungcapDiachi(String dmnhacungcapDiachi) {
        this.dmnhacungcapDiachi = dmnhacungcapDiachi;
    }

    public String getDmnhacungcapDienthoai() {
        return dmnhacungcapDienthoai;
    }

    public void setDmnhacungcapDienthoai(String dmnhacungcapDienthoai) {
        this.dmnhacungcapDienthoai = dmnhacungcapDienthoai;
    }

    public String getDmnhacungcapMasothue() {
        return dmnhacungcapMasothue;
    }

    public void setDmnhacungcapMasothue(String dmnhacungcapMasothue) {
        this.dmnhacungcapMasothue = dmnhacungcapMasothue;
    }

    public String getDmnhacungcapFax() {
        return dmnhacungcapFax;
    }

    public void setDmnhacungcapFax(String dmnhacungcapFax) {
        this.dmnhacungcapFax = dmnhacungcapFax;
    }

    public String getDmnhacungcapPhanloai() {
        return dmnhacungcapPhanloai;
    }

    public void setDmnhacungcapPhanloai(String dmnhacungcapPhanloai) {
        this.dmnhacungcapPhanloai = dmnhacungcapPhanloai;
    }

    public String getDmnhacungcapPhanbiet() {
        return dmnhacungcapPhanbiet;
    }

    public void setDmnhacungcapPhanbiet(String dmnhacungcapPhanbiet) {
        this.dmnhacungcapPhanbiet = dmnhacungcapPhanbiet;
    }

    public Date getDmnhacungcapNgaylv() {
        return dmnhacungcapNgaylv;
    }

    public void setDmnhacungcapNgaylv(Date dmnhacungcapNgaylv) {
        this.dmnhacungcapNgaylv = dmnhacungcapNgaylv;
    }

    public Double getDmnhacungcapNgaygiocn() {
        return dmnhacungcapNgaygiocn;
    }

    public void setDmnhacungcapNgaygiocn(Double dmnhacungcapNgaygiocn) {
        this.dmnhacungcapNgaygiocn = dmnhacungcapNgaygiocn;
    }

    public Boolean getDmnhacungcapQl() {
        return dmnhacungcapQl;
    }

    public void setDmnhacungcapQl(Boolean dmnhacungcapQl) {
        this.dmnhacungcapQl = dmnhacungcapQl;
    }

    public Boolean getDmnhacungcapDt() {
        return dmnhacungcapDt;
    }

    public void setDmnhacungcapDt(Boolean dmnhacungcapDt) {
        this.dmnhacungcapDt = dmnhacungcapDt;
    }

    public Boolean getDmnhacungcapDp() {
        return dmnhacungcapDp;
    }

    public void setDmnhacungcapDp(Boolean dmnhacungcapDp) {
        this.dmnhacungcapDp = dmnhacungcapDp;
    }

    public DmTinh getDmtinhMaso(boolean create) {
if(create && dmtinhMaso == null) dmtinhMaso = new DmTinh();
return dmtinhMaso;
}
    public DmTinh getDmtinhMaso() {
        return dmtinhMaso;
    }

    public void setDmtinhMaso(DmTinh dmtinhMaso) {
        this.dmtinhMaso = dmtinhMaso;
    }

    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
if(create && dmnctMaso == null) dmnctMaso = new DmNguonChuongTrinh();
return dmnctMaso;
}
    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

   

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnhacungcapMaso != null ? dmnhacungcapMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNhaCungCap)) {
            return false;
        }
        DmNhaCungCap other = (DmNhaCungCap) object;
        if ((this.dmnhacungcapMaso == null && other.dmnhacungcapMaso != null) || (this.dmnhacungcapMaso != null && !this.dmnhacungcapMaso.equals(other.dmnhacungcapMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNhaCungCap[dmnhacungcapMaso=" + dmnhacungcapMaso + "]";
    }

}


