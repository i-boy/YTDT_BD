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
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_HOC_HAM")
@NamedQueries({})
public class DmHocHam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_HOC_HAM")
    @SequenceGenerator(name = "DM_HOC_HAM", sequenceName = "DM_HOC_HAM_DMHOCHAM_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMHOCHAM_MASO", nullable = false)
    private Integer dmhochamMaso;
    @Column(name = "DMHOCHAM_MA")
    private String dmhochamMa;
    @Column(name = "DMHOCHAM_TEN", nullable = false)
    private String dmhochamTen;
    @Column(name = "DMHOCHAM_NGAYGIOCN")
    private Double dmhochamNgaygiocn;
    @Column(name = "DMHOCHAM_QL")
    private Boolean dmhochamQl;
    @Column(name = "DMHOCHAM_DT")
    private Boolean dmhochamDt;
    @Column(name = "DMHOCHAM_DP")
    private Boolean dmhochamDp;

    public DmHocHam() {
    }

    public DmHocHam(Integer dmhochamMaso) {
        this.dmhochamMaso = dmhochamMaso;
    }

    public DmHocHam(Integer dmhochamMaso, String dmhochamTen) {
        this.dmhochamMaso = dmhochamMaso;
        this.dmhochamTen = dmhochamTen;
    }

    public Integer getDmhochamMaso() {
        return dmhochamMaso;
    }

    public void setDmhochamMaso(Integer dmhochamMaso) {
        this.dmhochamMaso = dmhochamMaso;
    }

    public String getDmhochamMa() {
        return dmhochamMa;
    }

    public void setDmhochamMa(String dmhochamMa) {
        this.dmhochamMa = dmhochamMa;
    }

    public String getDmhochamTen() {
        return dmhochamTen;
    }

    public void setDmhochamTen(String dmhochamTen) {
        this.dmhochamTen = dmhochamTen;
    }

    public Double getDmhochamNgaygiocn() {
        return dmhochamNgaygiocn;
    }

    public void setDmhochamNgaygiocn(Double dmhochamNgaygiocn) {
        this.dmhochamNgaygiocn = dmhochamNgaygiocn;
    }

    public Boolean getDmhochamQl() {
        return dmhochamQl;
    }

    public void setDmhochamQl(Boolean dmhochamQl) {
        this.dmhochamQl = dmhochamQl;
    }

    public Boolean getDmhochamDt() {
        return dmhochamDt;
    }

    public void setDmhochamDt(Boolean dmhochamDt) {
        this.dmhochamDt = dmhochamDt;
    }

    public Boolean getDmhochamDp() {
        return dmhochamDp;
    }

    public void setDmhochamDp(Boolean dmhochamDp) {
        this.dmhochamDp = dmhochamDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmhochamMaso != null ? dmhochamMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmHocHam)) {
            return false;
        }
        DmHocHam other = (DmHocHam) object;
        if ((this.dmhochamMaso == null && other.dmhochamMaso != null) || (this.dmhochamMaso != null && !this.dmhochamMaso.equals(other.dmhochamMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmHocHam[dmhochamMaso=" + dmhochamMaso + "]";
    }

}


