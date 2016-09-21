/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_TUYEN_DON_VI")
@NamedQueries({})
public class DmTuyenDonVi implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DmTuyenDonViPK dmTuyenDonViPK;
    @Column(name = "DMTUYEN_MA", nullable = false)
    private String dmtuyenMa;
    @Column(name = "DMTUYENDONVI_NGAYGIOCN")
    private Double dmtuyendonviNgaygiocn;
    @Column(name = "DMTUYENDONVI_DT")
    private Boolean dmtuyendonviDt;
    @Column(name = "DMTUYENDONVI_QL")
    private Boolean dmtuyendonviQl;
    @Column(name = "DMTUYENDONVI_DP")
    private Boolean dmtuyendonviDp;
    @JoinColumn(name = "DMTUYEN_MASO", referencedColumnName = "DMTUYEN_MASO", insertable = false, updatable = false)
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTuyen dmTuyen;
    @JoinColumn(name = "DMDONVI_MASO", referencedColumnName = "DMDONVI_MASO", insertable = false, updatable = false)
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDonVi dmDonVi;


    public DmTuyenDonVi() {
    }

    public DmTuyenDonVi(DmTuyenDonViPK dmTuyenDonViPK) {
        this.dmTuyenDonViPK = dmTuyenDonViPK;
    }

    public DmTuyenDonVi(DmTuyenDonViPK dmTuyenDonViPK, String dmtuyenMa) {
        this.dmTuyenDonViPK = dmTuyenDonViPK;
        this.dmtuyenMa = dmtuyenMa;
    }

    public DmTuyenDonVi(int dmtuyenMaso, int dmdonviMaso) {
        this.dmTuyenDonViPK = new DmTuyenDonViPK(dmtuyenMaso, dmdonviMaso);
    }

    public DmTuyenDonViPK getDmTuyenDonViPK() {
        return dmTuyenDonViPK;
    }

    public void setDmTuyenDonViPK(DmTuyenDonViPK dmTuyenDonViPK) {
        this.dmTuyenDonViPK = dmTuyenDonViPK;
    }

    public String getDmtuyenMa() {
        return dmtuyenMa;
    }

    public void setDmtuyenMa(String dmtuyenMa) {
        this.dmtuyenMa = dmtuyenMa;
    }

    public Double getDmtuyendonviNgaygiocn() {
        return dmtuyendonviNgaygiocn;
    }

    public void setDmtuyendonviNgaygiocn(Double dmtuyendonviNgaygiocn) {
        this.dmtuyendonviNgaygiocn = dmtuyendonviNgaygiocn;
    }

    public Boolean getDmtuyendonviDt() {
        return dmtuyendonviDt;
    }

    public void setDmtuyendonviDt(Boolean dmtuyendonviDt) {
        this.dmtuyendonviDt = dmtuyendonviDt;
    }

    public Boolean getDmtuyendonviQl() {
        return dmtuyendonviQl;
    }

    public void setDmtuyendonviQl(Boolean dmtuyendonviQl) {
        this.dmtuyendonviQl = dmtuyendonviQl;
    }

    public Boolean getDmtuyendonviDp() {
        return dmtuyendonviDp;
    }

    public void setDmtuyendonviDp(Boolean dmtuyendonviDp) {
        this.dmtuyendonviDp = dmtuyendonviDp;
    }

    public DmTuyen getDmTuyen(boolean create) {
if(create && dmTuyen == null) dmTuyen = new DmTuyen();
return dmTuyen;
}
    public DmTuyen getDmTuyen() {
        return dmTuyen;
    }

    public void setDmTuyen(DmTuyen dmTuyen) {
        this.dmTuyen = dmTuyen;
    }

    public DmDonVi getDmDonVi(boolean create) {
if(create && dmDonVi == null) dmDonVi = new DmDonVi();
return dmDonVi;
}
    public DmDonVi getDmDonVi() {
        return dmDonVi;
    }

    public void setDmDonVi(DmDonVi dmDonVi) {
        this.dmDonVi = dmDonVi;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmTuyenDonViPK != null ? dmTuyenDonViPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTuyenDonVi)) {
            return false;
        }
        DmTuyenDonVi other = (DmTuyenDonVi) object;
        if ((this.dmTuyenDonViPK == null && other.dmTuyenDonViPK != null) || (this.dmTuyenDonViPK != null && !this.dmTuyenDonViPK.equals(other.dmTuyenDonViPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmTuyenDonVi[dmTuyenDonViPK=" + dmTuyenDonViPK + "]";
    }

}


