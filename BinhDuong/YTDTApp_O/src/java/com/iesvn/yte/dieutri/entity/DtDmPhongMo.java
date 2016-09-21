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
@Table(name = "DT_DM_PHONG_MO")
@NamedQueries({})
public class DtDmPhongMo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_PHONG_MO")
    @SequenceGenerator(name = "DT_DM_PHONG_MO", sequenceName = "DT_DM_PHONG_MO_DTDMPHONGMO_MAS", allocationSize = 1)
    @Column(name = "DTDMPHONGMO_MASO", nullable = false)
    private Integer dtdmphongmoMaso;
    @Column(name = "DTDMPHONGMO_MA", nullable = false)
    private String dtdmphongmoMa;
    @Column(name = "DTDMPHONGMO_TENPHONG", nullable = false)
    private String dtdmphongmoTenphong;
    @Column(name = "DTDMPHONGMO_GHICHU")
    private String dtdmphongmoGhichu;
    @Column(name = "DTDMPHONGMO_NGAYGIOCN")
    private Double dtdmphongmoNgaygiocn;
    @Column(name = "DTDMPHONGMO_CHON")
    private Boolean dtdmphongmoChon;
    @JoinColumn(name = "KHOA_LIENHE", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa khoaLienhe;
//    @OneToMany(mappedBy = "phongmoMa")
//    private Collection<LichMo> lichMoCollection;
//    @OneToMany(mappedBy = "phongmoMa1")
//    private Collection<LichMo> lichMoCollection1;
    public DtDmPhongMo() {
    }

    public DtDmPhongMo(Integer dtdmphongmoMaso) {
        this.dtdmphongmoMaso = dtdmphongmoMaso;
    }

    public DtDmPhongMo(Integer dtdmphongmoMaso, String dtdmphongmoMa, String dtdmphongmoTenphong) {
        this.dtdmphongmoMaso = dtdmphongmoMaso;
        this.dtdmphongmoMa = dtdmphongmoMa;
        this.dtdmphongmoTenphong = dtdmphongmoTenphong;
    }

    public Integer getDtdmphongmoMaso() {
        return dtdmphongmoMaso;
    }

    public void setDtdmphongmoMaso(Integer dtdmphongmoMaso) {
        this.dtdmphongmoMaso = dtdmphongmoMaso;
    }

    public String getDtdmphongmoMa() {
        return dtdmphongmoMa;
    }

    public void setDtdmphongmoMa(String dtdmphongmoMa) {
        this.dtdmphongmoMa = dtdmphongmoMa;
    }

    public String getDtdmphongmoTenphong() {
        return dtdmphongmoTenphong;
    }

    public void setDtdmphongmoTenphong(String dtdmphongmoTenphong) {
        this.dtdmphongmoTenphong = dtdmphongmoTenphong;
    }

    public String getDtdmphongmoGhichu() {
        return dtdmphongmoGhichu;
    }

    public void setDtdmphongmoGhichu(String dtdmphongmoGhichu) {
        this.dtdmphongmoGhichu = dtdmphongmoGhichu;
    }

    public Double getDtdmphongmoNgaygiocn() {
        return dtdmphongmoNgaygiocn;
    }

    public void setDtdmphongmoNgaygiocn(Double dtdmphongmoNgaygiocn) {
        this.dtdmphongmoNgaygiocn = dtdmphongmoNgaygiocn;
    }

    public Boolean getDtdmphongmoChon() {
        return dtdmphongmoChon;
    }

    public void setDtdmphongmoChon(Boolean dtdmphongmoChon) {
        this.dtdmphongmoChon = dtdmphongmoChon;
    }

    public DmKhoa getKhoaLienhe(boolean create) {
if(create && khoaLienhe == null) khoaLienhe = new DmKhoa();
return khoaLienhe;
}
    public DmKhoa getKhoaLienhe() {
        return khoaLienhe;
    }

    public void setKhoaLienhe(DmKhoa khoaLienhe) {
        this.khoaLienhe = khoaLienhe;
    }

//    public Collection<LichMo> getLichMoCollection() {
//        return lichMoCollection;
//    }
//
//    public void setLichMoCollection(Collection<LichMo> lichMoCollection) {
//        this.lichMoCollection = lichMoCollection;
//    }

//    public Collection<LichMo> getLichMoCollection1() {
//        return lichMoCollection1;
//    }
//
//    public void setLichMoCollection1(Collection<LichMo> lichMoCollection1) {
//        this.lichMoCollection1 = lichMoCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmphongmoMaso != null ? dtdmphongmoMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmPhongMo)) {
            return false;
        }
        DtDmPhongMo other = (DtDmPhongMo) object;
        if ((this.dtdmphongmoMaso == null && other.dtdmphongmoMaso != null) || (this.dtdmphongmoMaso != null && !this.dtdmphongmoMaso.equals(other.dtdmphongmoMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmPhongMo[dtdmphongmoMaso=" + dtdmphongmoMaso + "]";
    }
}


