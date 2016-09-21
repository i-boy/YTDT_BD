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
@Table(name = "DM_DON_VI_TINH")
@NamedQueries({})
public class DmDonViTinh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DON_VI_TINH")
    @SequenceGenerator(name = "DM_DON_VI_TINH", sequenceName = "DM_DON_VI_TINH_DMDONVITINH_MAS", allocationSize = 1)
    @Column(name = "DMDONVITINH_MASO", nullable = false)
    private Integer dmdonvitinhMaso;
    @Column(name = "DMDONVITINH_MA")
    private String dmdonvitinhMa;
    @Column(name = "DMDONVITINH_TEN")
    private String dmdonvitinhTen;
    @Column(name = "DMDONVITINH_DTICH")
    private String dmdonvitinhDtich;
    @Column(name = "DMDONVITINH_DACDIEM")
    private String dmdonvitinhDacdiem;
    @Column(name = "DMDONVITINH_NGAYGIOCN")
    private Double dmdonvitinhNgaygiocn;
    @Column(name = "DMDONVITINH_DT")
    private Boolean dmdonvitinhDt;
    @Column(name = "DMDONVITINH_DP")
    private Boolean dmdonvitinhDp;
    @Column(name = "DMDONVITINH_QL")
    private Boolean dmdonvitinhQl;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmdonvitinhMaso")
//    private Collection<DtDmChiDanDvt> dtDmChiDanDvtCollection;
//    @OneToMany(mappedBy = "dmdonvitinhMaso")
//    private Collection<DmThuoc> dmThuocCollection;
//    @OneToMany(mappedBy = "dmdonvitinhMaso1")
//    private Collection<DmThuoc> dmThuocCollection1;

    public DmDonViTinh() {
    }

    public DmDonViTinh(Integer dmdonvitinhMaso) {
        this.dmdonvitinhMaso = dmdonvitinhMaso;
    }

    public Integer getDmdonvitinhMaso() {
        return dmdonvitinhMaso;
    }

    public void setDmdonvitinhMaso(Integer dmdonvitinhMaso) {
        this.dmdonvitinhMaso = dmdonvitinhMaso;
    }

    public String getDmdonvitinhMa() {
        return dmdonvitinhMa;
    }

    public void setDmdonvitinhMa(String dmdonvitinhMa) {
        this.dmdonvitinhMa = dmdonvitinhMa;
    }

    public String getDmdonvitinhTen() {
        return dmdonvitinhTen;
    }

    public void setDmdonvitinhTen(String dmdonvitinhTen) {
        this.dmdonvitinhTen = dmdonvitinhTen;
    }

    public String getDmdonvitinhDtich() {
        return dmdonvitinhDtich;
    }

    public void setDmdonvitinhDtich(String dmdonvitinhDtich) {
        this.dmdonvitinhDtich = dmdonvitinhDtich;
    }

    public String getDmdonvitinhDacdiem() {
        return dmdonvitinhDacdiem;
    }

    public void setDmdonvitinhDacdiem(String dmdonvitinhDacdiem) {
        this.dmdonvitinhDacdiem = dmdonvitinhDacdiem;
    }

    public Double getDmdonvitinhNgaygiocn() {
        return dmdonvitinhNgaygiocn;
    }

    public void setDmdonvitinhNgaygiocn(Double dmdonvitinhNgaygiocn) {
        this.dmdonvitinhNgaygiocn = dmdonvitinhNgaygiocn;
    }

    public Boolean getDmdonvitinhDt() {
        return dmdonvitinhDt;
    }

    public void setDmdonvitinhDt(Boolean dmdonvitinhDt) {
        this.dmdonvitinhDt = dmdonvitinhDt;
    }

    public Boolean getDmdonvitinhDp() {
        return dmdonvitinhDp;
    }

    public void setDmdonvitinhDp(Boolean dmdonvitinhDp) {
        this.dmdonvitinhDp = dmdonvitinhDp;
    }

    public Boolean getDmdonvitinhQl() {
        return dmdonvitinhQl;
    }

    public void setDmdonvitinhQl(Boolean dmdonvitinhQl) {
        this.dmdonvitinhQl = dmdonvitinhQl;
    }

//    public Collection<DtDmChiDanDvt> getDtDmChiDanDvtCollection() {
//        return dtDmChiDanDvtCollection;
//    }
//
//    public void setDtDmChiDanDvtCollection(Collection<DtDmChiDanDvt> dtDmChiDanDvtCollection) {
//        this.dtDmChiDanDvtCollection = dtDmChiDanDvtCollection;
//    }

//    public Collection<DmThuoc> getDmThuocCollection() {
//        return dmThuocCollection;
//    }
//
//    public void setDmThuocCollection(Collection<DmThuoc> dmThuocCollection) {
//        this.dmThuocCollection = dmThuocCollection;
//    }

//    public Collection<DmThuoc> getDmThuocCollection1() {
//        return dmThuocCollection1;
//    }
//
//    public void setDmThuocCollection1(Collection<DmThuoc> dmThuocCollection1) {
//        this.dmThuocCollection1 = dmThuocCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmdonvitinhMaso != null ? dmdonvitinhMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmDonViTinh)) {
            return false;
        }
        DmDonViTinh other = (DmDonViTinh) object;
        if ((this.dmdonvitinhMaso == null && other.dmdonvitinhMaso != null) || (this.dmdonvitinhMaso != null && !this.dmdonvitinhMaso.equals(other.dmdonvitinhMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmDonViTinh[dmdonvitinhMaso=" + dmdonvitinhMaso + "]";
    }

}


