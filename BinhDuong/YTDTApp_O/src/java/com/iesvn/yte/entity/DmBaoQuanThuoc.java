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
@Table(name = "DM_BAO_QUAN_THUOC")
@NamedQueries({})
public class DmBaoQuanThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BAO_QUAN_THUOC")
    @SequenceGenerator(name = "DM_BAO_QUAN_THUOC", sequenceName = "DM_BAO_QUAN_THUOC_DMBAOQUANTHU", allocationSize = 1)
    @Column(name = "DMBAOQUANTHUOC_MASO", nullable = false)
    private Integer dmbaoquanthuocMaso;
    @Column(name = "DMBAOQUANTHUOC_MA", nullable = false)
    private String dmbaoquanthuocMa;
    @Column(name = "DMBAOQUANTHUOC_MAPHU")
    private String dmbaoquanthuocMaphu;
    @Column(name = "DMBAOQUANTHUOC_TEN", nullable = false)
    private String dmbaoquanthuocTen;
    @Column(name = "DMBAOQUANTHUOC_GHICHU")
    private String dmbaoquanthuocGhichu;
    @Column(name = "NHANVIEN_CN", nullable = false)
    private String nhanvienCn;
    @Column(name = "DMBAOQUANTHUOC_NGAYGIOCN")
    private Double dmbaoquanthuocNgaygiocn;
    @Column(name = "DMBAOQUANTHUOC_DT")
    private Boolean dmbaoquanthuocDt;
    @Column(name = "DMBAOQUANTHUOC_QL")
    private Boolean dmbaoquanthuocQl;
    @Column(name = "DMBAOQUANTHUOC_DP")
    private Boolean dmbaoquanthuocDp;
//    @OneToMany(mappedBy = "dmbaoquanMaso")
//    private Collection<DmThuoc> dmThuocCollection;
//    @OneToMany(mappedBy = "dmbaoquanMaso1")
//    private Collection<DmThuoc> dmThuocCollection1;

    public DmBaoQuanThuoc() {
    }

    public DmBaoQuanThuoc(Integer dmbaoquanthuocMaso) {
        this.dmbaoquanthuocMaso = dmbaoquanthuocMaso;
    }

    public DmBaoQuanThuoc(Integer dmbaoquanthuocMaso, String dmbaoquanthuocMa, String dmbaoquanthuocTen, String nhanvienCn) {
        this.dmbaoquanthuocMaso = dmbaoquanthuocMaso;
        this.dmbaoquanthuocMa = dmbaoquanthuocMa;
        this.dmbaoquanthuocTen = dmbaoquanthuocTen;
        this.nhanvienCn = nhanvienCn;
    }

    public Integer getDmbaoquanthuocMaso() {
        return dmbaoquanthuocMaso;
    }

    public void setDmbaoquanthuocMaso(Integer dmbaoquanthuocMaso) {
        this.dmbaoquanthuocMaso = dmbaoquanthuocMaso;
    }

    public String getDmbaoquanthuocMa() {
        return dmbaoquanthuocMa;
    }

    public void setDmbaoquanthuocMa(String dmbaoquanthuocMa) {
        this.dmbaoquanthuocMa = dmbaoquanthuocMa;
    }

    public String getDmbaoquanthuocMaphu() {
        return dmbaoquanthuocMaphu;
    }

    public void setDmbaoquanthuocMaphu(String dmbaoquanthuocMaphu) {
        this.dmbaoquanthuocMaphu = dmbaoquanthuocMaphu;
    }

    public String getDmbaoquanthuocTen() {
        return dmbaoquanthuocTen;
    }

    public void setDmbaoquanthuocTen(String dmbaoquanthuocTen) {
        this.dmbaoquanthuocTen = dmbaoquanthuocTen;
    }

    public String getDmbaoquanthuocGhichu() {
        return dmbaoquanthuocGhichu;
    }

    public void setDmbaoquanthuocGhichu(String dmbaoquanthuocGhichu) {
        this.dmbaoquanthuocGhichu = dmbaoquanthuocGhichu;
    }

    public String getNhanvienCn() {
        return nhanvienCn;
    }

    public void setNhanvienCn(String nhanvienCn) {
        this.nhanvienCn = nhanvienCn;
    }

    public Double getDmbaoquanthuocNgaygiocn() {
        return dmbaoquanthuocNgaygiocn;
    }

    public void setDmbaoquanthuocNgaygiocn(Double dmbaoquanthuocNgaygiocn) {
        this.dmbaoquanthuocNgaygiocn = dmbaoquanthuocNgaygiocn;
    }

    public Boolean getDmbaoquanthuocDt() {
        return dmbaoquanthuocDt;
    }

    public void setDmbaoquanthuocDt(Boolean dmbaoquanthuocDt) {
        this.dmbaoquanthuocDt = dmbaoquanthuocDt;
    }

    public Boolean getDmbaoquanthuocQl() {
        return dmbaoquanthuocQl;
    }

    public void setDmbaoquanthuocQl(Boolean dmbaoquanthuocQl) {
        this.dmbaoquanthuocQl = dmbaoquanthuocQl;
    }

    public Boolean getDmbaoquanthuocDp() {
        return dmbaoquanthuocDp;
    }

    public void setDmbaoquanthuocDp(Boolean dmbaoquanthuocDp) {
        this.dmbaoquanthuocDp = dmbaoquanthuocDp;
    }

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
        hash += (dmbaoquanthuocMaso != null ? dmbaoquanthuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmBaoQuanThuoc)) {
            return false;
        }
        DmBaoQuanThuoc other = (DmBaoQuanThuoc) object;
        if ((this.dmbaoquanthuocMaso == null && other.dmbaoquanthuocMaso != null) || (this.dmbaoquanthuocMaso != null && !this.dmbaoquanthuocMaso.equals(other.dmbaoquanthuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmBaoQuanThuoc[dmbaoquanthuocMaso=" + dmbaoquanthuocMaso + "]";
    }

}


