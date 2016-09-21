/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
@Table(name = "DT_DM_VO_CAM")
@NamedQueries({})
public class DtDmVoCam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_VO_CAM")
    @SequenceGenerator(name = "DT_DM_VO_CAM", sequenceName = "DT_DM_VO_CAM_DTDMVOCAM_MASO_SE", allocationSize = 1)
    @Column(name = "DTDMVOCAM_MASO", nullable = false)
    private Integer dtdmvocamMaso;
    @Column(name = "DTDMVOCAM_MA", nullable = false)
    private String dtdmvocamMa;
    @Column(name = "DTDMVOCAM_MAPHU")
    private String dtdmvocamMaphu;
    @Column(name = "DTDMVOCAM_DIENGIAI", nullable = false)
    private String dtdmvocamDiengiai;
    @Column(name = "DTDMVOCAM_PHANLOAI")
    private String dtdmvocamPhanloai;
    @Column(name = "DTDMVOCAM_GHICHU")
    private String dtdmvocamGhichu;
    @Column(name = "DTDMVOCAM_NGAYGIOCN")
    private Double dtdmvocamNgaygiocn;
    @Column(name = "DTDMVOCAM_CHON")
    private Boolean dtdmvocamChon;
//    @OneToMany(mappedBy = "vocamMaso")
//    private Collection<HsbaMo> hsbaMoCollection;
//    @OneToMany(mappedBy = "vocamMaso1")
//    private Collection<HsbaMo> hsbaMoCollection1;
    public DtDmVoCam() {
    }

    public DtDmVoCam(Integer dtdmvocamMaso) {
        this.dtdmvocamMaso = dtdmvocamMaso;
    }

    public DtDmVoCam(Integer dtdmvocamMaso, String dtdmvocamMa, String dtdmvocamDiengiai) {
        this.dtdmvocamMaso = dtdmvocamMaso;
        this.dtdmvocamMa = dtdmvocamMa;
        this.dtdmvocamDiengiai = dtdmvocamDiengiai;
    }

    public Integer getDtdmvocamMaso() {
        return dtdmvocamMaso;
    }

    public void setDtdmvocamMaso(Integer dtdmvocamMaso) {
        this.dtdmvocamMaso = dtdmvocamMaso;
    }

    public String getDtdmvocamMa() {
        return dtdmvocamMa;
    }

    public void setDtdmvocamMa(String dtdmvocamMa) {
        this.dtdmvocamMa = dtdmvocamMa;
    }

    public String getDtdmvocamMaphu() {
        return dtdmvocamMaphu;
    }

    public void setDtdmvocamMaphu(String dtdmvocamMaphu) {
        this.dtdmvocamMaphu = dtdmvocamMaphu;
    }

    public String getDtdmvocamDiengiai() {
        return dtdmvocamDiengiai;
    }

    public void setDtdmvocamDiengiai(String dtdmvocamDiengiai) {
        this.dtdmvocamDiengiai = dtdmvocamDiengiai;
    }

    public String getDtdmvocamPhanloai() {
        return dtdmvocamPhanloai;
    }

    public void setDtdmvocamPhanloai(String dtdmvocamPhanloai) {
        this.dtdmvocamPhanloai = dtdmvocamPhanloai;
    }

    public String getDtdmvocamGhichu() {
        return dtdmvocamGhichu;
    }

    public void setDtdmvocamGhichu(String dtdmvocamGhichu) {
        this.dtdmvocamGhichu = dtdmvocamGhichu;
    }

    public Double getDtdmvocamNgaygiocn() {
        return dtdmvocamNgaygiocn;
    }

    public void setDtdmvocamNgaygiocn(Double dtdmvocamNgaygiocn) {
        this.dtdmvocamNgaygiocn = dtdmvocamNgaygiocn;
    }

    public Boolean getDtdmvocamChon() {
        return dtdmvocamChon;
    }

    public void setDtdmvocamChon(Boolean dtdmvocamChon) {
        this.dtdmvocamChon = dtdmvocamChon;
    }

//    public Collection<HsbaMo> getHsbaMoCollection() {
//        return hsbaMoCollection;
//    }
//
//    public void setHsbaMoCollection(Collection<HsbaMo> hsbaMoCollection) {
//        this.hsbaMoCollection = hsbaMoCollection;
//    }

//    public Collection<HsbaMo> getHsbaMoCollection1() {
//        return hsbaMoCollection1;
//    }
//
//    public void setHsbaMoCollection1(Collection<HsbaMo> hsbaMoCollection1) {
//        this.hsbaMoCollection1 = hsbaMoCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmvocamMaso != null ? dtdmvocamMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmVoCam)) {
            return false;
        }
        DtDmVoCam other = (DtDmVoCam) object;
        if ((this.dtdmvocamMaso == null && other.dtdmvocamMaso != null) || (this.dtdmvocamMaso != null && !this.dtdmvocamMaso.equals(other.dtdmvocamMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmVoCam[dtdmvocamMaso=" + dtdmvocamMaso + "]";
    }
}


