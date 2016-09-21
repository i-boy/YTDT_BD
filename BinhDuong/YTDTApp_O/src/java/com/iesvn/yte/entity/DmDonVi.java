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
@Table(name = "DM_DON_VI")
@NamedQueries({})
public class DmDonVi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_DON_VI")
    @SequenceGenerator(name = "DM_DON_VI", sequenceName = "DM_DON_VI_DMDONVI_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMDONVI_MASO", nullable = false)
    private Integer dmdonviMaso;
    @Column(name = "DMDONVI_MA")
    private String dmdonviMa;
    @Column(name = "DMDONVI_TEN")
    private String dmdonviTen;
    @Column(name = "DMDONVI_TENBAOCAO")
    private String dmdonviTenbaocao;
    @Column(name = "DMDONVI_SONHATUYENDUONG")
    private String dmdonviSonhatuyenduong;
    @Column(name = "DMDONVI_DIENTHOAI")
    private String dmdonviDienthoai;
    @Column(name = "DMDONVI_DVQUANLYTRUCTIEP")
    private String dmdonviDvquanlytructiep;
    @Column(name = "DMDONVI_NGAYGIOCN")
    private Double dmdonviNgaygiocn;
    @Column(name = "DMDONVI_CHON")
    private Boolean dmdonviChon;
    @Column(name = "DMDONVI_URL")
    private String dmdonviUrl;
    @JoinColumn(name = "DMTINH_MASO", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh dmtinhMaso;
    @JoinColumn(name = "DMHUYEN_MASO", referencedColumnName = "DMHUYEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmHuyen dmhuyenMaso;
    @JoinColumn(name = "DMXA_MASO", referencedColumnName = "DMXA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmXa dmxaMaso;
    @JoinColumn(name = "DMTUYEN_MASO", referencedColumnName = "DMTUYEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTuyen dmtuyenMaso;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmDonVi")
//    private Collection<DmTuyenDonVi> dmTuyenDonViCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmDonVi1")
//    private Collection<DmTuyenDonVi> dmTuyenDonViCollection1;

    public DmDonVi() {
    }

    public DmDonVi(Integer dmdonviMaso) {
        this.dmdonviMaso = dmdonviMaso;
    }

    public Integer getDmdonviMaso() {
        return dmdonviMaso;
    }

    public void setDmdonviMaso(Integer dmdonviMaso) {
        this.dmdonviMaso = dmdonviMaso;
    }

    public String getDmdonviMa() {
        return dmdonviMa;
    }

    public void setDmdonviMa(String dmdonviMa) {
        this.dmdonviMa = dmdonviMa;
    }

    public String getDmdonviTen() {
        return dmdonviTen;
    }

    public void setDmdonviTen(String dmdonviTen) {
        this.dmdonviTen = dmdonviTen;
    }

    public String getDmdonviTenbaocao() {
        return dmdonviTenbaocao;
    }

    public void setDmdonviTenbaocao(String dmdonviTenbaocao) {
        this.dmdonviTenbaocao = dmdonviTenbaocao;
    }

    public String getDmdonviSonhatuyenduong() {
        return dmdonviSonhatuyenduong;
    }

    public void setDmdonviSonhatuyenduong(String dmdonviSonhatuyenduong) {
        this.dmdonviSonhatuyenduong = dmdonviSonhatuyenduong;
    }

    public String getDmdonviDienthoai() {
        return dmdonviDienthoai;
    }

    public void setDmdonviDienthoai(String dmdonviDienthoai) {
        this.dmdonviDienthoai = dmdonviDienthoai;
    }

    public String getDmdonviDvquanlytructiep() {
        return dmdonviDvquanlytructiep;
    }

    public void setDmdonviDvquanlytructiep(String dmdonviDvquanlytructiep) {
        this.dmdonviDvquanlytructiep = dmdonviDvquanlytructiep;
    }

    public Double getDmdonviNgaygiocn() {
        return dmdonviNgaygiocn;
    }

    public void setDmdonviNgaygiocn(Double dmdonviNgaygiocn) {
        this.dmdonviNgaygiocn = dmdonviNgaygiocn;
    }

    public Boolean getDmdonviChon() {
        return dmdonviChon;
    }

    public void setDmdonviChon(Boolean dmdonviChon) {
        this.dmdonviChon = dmdonviChon;
    }

    public String getDmdonviUrl() {
        return dmdonviUrl;
    }

    public void setDmdonviUrl(String dmdonviUrl) {
        this.dmdonviUrl = dmdonviUrl;
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

    public DmHuyen getDmhuyenMaso(boolean create) {
if(create && dmhuyenMaso == null) dmhuyenMaso = new DmHuyen();
return dmhuyenMaso;
}
    public DmHuyen getDmhuyenMaso() {
        return dmhuyenMaso;
    }

    public void setDmhuyenMaso(DmHuyen dmhuyenMaso) {
        this.dmhuyenMaso = dmhuyenMaso;
    }

    public DmXa getDmxaMaso(boolean create) {
if(create && dmxaMaso == null) dmxaMaso = new DmXa();
return dmxaMaso;
}
    public DmXa getDmxaMaso() {
        return dmxaMaso;
    }

    public void setDmxaMaso(DmXa dmxaMaso) {
        this.dmxaMaso = dmxaMaso;
    }

    public DmTuyen getDmtuyenMaso(boolean create) {
if(create && dmtuyenMaso == null) dmtuyenMaso = new DmTuyen();
return dmtuyenMaso;
}
    public DmTuyen getDmtuyenMaso() {
        return dmtuyenMaso;
    }

    public void setDmtuyenMaso(DmTuyen dmtuyenMaso) {
        this.dmtuyenMaso = dmtuyenMaso;
    }

//    public Collection<DmTuyenDonVi> getDmTuyenDonViCollection() {
//        return dmTuyenDonViCollection;
//    }
//
//    public void setDmTuyenDonViCollection(Collection<DmTuyenDonVi> dmTuyenDonViCollection) {
//        this.dmTuyenDonViCollection = dmTuyenDonViCollection;
//    }

//    public Collection<DmTuyenDonVi> getDmTuyenDonViCollection1() {
//        return dmTuyenDonViCollection1;
//    }
//
//    public void setDmTuyenDonViCollection1(Collection<DmTuyenDonVi> dmTuyenDonViCollection1) {
//        this.dmTuyenDonViCollection1 = dmTuyenDonViCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmdonviMaso != null ? dmdonviMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmDonVi)) {
            return false;
        }
        DmDonVi other = (DmDonVi) object;
        if ((this.dmdonviMaso == null && other.dmdonviMaso != null) || (this.dmdonviMaso != null && !this.dmdonviMaso.equals(other.dmdonviMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmDonVi[dmdonviMaso=" + dmdonviMaso + "]";
    }

}


