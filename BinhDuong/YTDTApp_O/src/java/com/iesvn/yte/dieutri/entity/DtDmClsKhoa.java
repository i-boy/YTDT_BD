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
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_CLS_KHOA")
@NamedQueries({})
public class DtDmClsKhoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CLS_KHOA")
    @SequenceGenerator(name = "DT_DM_CLS_KHOA", sequenceName = "DT_DM_CLS_KHOA_DTDMCLSKHOA_MAS", allocationSize = 1)
    @Column(name = "DTDMCLSKHOA_MASO", nullable = false)
    private Integer dtdmclskhoaMaso;
    @JoinColumn(name = "DTDMCLS_MASO", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia dtdmclsMaso;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
    @Column(name = "DTDMCLSKHOA_NGAYGIOCN")
    private Double dtdmclsKhoaNgaygiocn;
    @Column(name = "DTDMCLSKHOA_CHON")
    private Boolean dtdmclsKhoaChon;
//    @Column(name = "DTDMCLSKHOA_NGAYGIOCN")
//    private Double dtdmclskhoaNgaygiocn;
//    @Column(name = "DTDMCLSKHOA_CHON")
//    private Boolean dtdmclskhoaChon;
    public DtDmClsKhoa() {
    }

    public DtDmClsKhoa(Integer dtdmclskhoaMaso) {
        this.dtdmclskhoaMaso = dtdmclskhoaMaso;
    }

    public Integer getDtdmclskhoaMaso() {
        return dtdmclskhoaMaso;
    }

    public void setDtdmclskhoaMaso(Integer dtdmclskhoaMaso) {
        this.dtdmclskhoaMaso = dtdmclskhoaMaso;
    }

    public DtDmClsBangGia getDtdmclsMaso(boolean create) {
        if (create && dtdmclsMaso == null) {
            dtdmclsMaso = new DtDmClsBangGia();
        }
        return dtdmclsMaso;
    }

    public DtDmClsBangGia getDtdmclsMaso() {
        return dtdmclsMaso;
    }

    public void setDtdmclsMaso(DtDmClsBangGia dtdmclsMaso) {
        this.dtdmclsMaso = dtdmclsMaso;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
        if (create && dmkhoaMaso == null) {
            dmkhoaMaso = new DmKhoa();
        }
        return dmkhoaMaso;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmclskhoaMaso != null ? dtdmclskhoaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmClsKhoa)) {
            return false;
        }
        DtDmClsKhoa other = (DtDmClsKhoa) object;
        if ((this.dtdmclskhoaMaso == null && other.dtdmclskhoaMaso != null) || (this.dtdmclskhoaMaso != null && !this.dtdmclskhoaMaso.equals(other.dtdmclskhoaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmClsKhoa[dtdmclskhoaMaso=" + dtdmclskhoaMaso + "]";
    }

    public Double getDtdmclsKhoaNgaygiocn() {
        return dtdmclsKhoaNgaygiocn;
    }

    public void setDtdmclsKhoaNgaygiocn(Double dtdmclsKhoaNgaygiocn) {
        this.dtdmclsKhoaNgaygiocn = dtdmclsKhoaNgaygiocn;
    }

    public Boolean getDtdmclsKhoaChon() {
        return dtdmclsKhoaChon;
    }

    public void setDtdmclsKhoaChon(Boolean dtdmclsKhoaChon) {
        this.dtdmclsKhoaChon = dtdmclsKhoaChon;
    }
}


