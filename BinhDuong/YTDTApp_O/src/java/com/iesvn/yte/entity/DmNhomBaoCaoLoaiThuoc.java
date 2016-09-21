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
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_NHOM_BAO_CAO_LOAI_THUOC")
@NamedQueries({})
public class DmNhomBaoCaoLoaiThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_NHOM_BAO_CAO_LOAI_THUOC")
    @SequenceGenerator(name = "DM_NHOM_BAO_CAO_LOAI_THUOC", sequenceName = "DM_NHOM_BAO_CAO_LOAI_THUOC_DMN", allocationSize = 1)
    @Column(name = "DMNHOMBCTHUOCLOAITHUOC_MASO", nullable = false)
    private Integer dmnhombcthuocloaithuocMaso;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;
    @JoinColumn(name = "DMNHOMBCTHUOC_MASO", referencedColumnName = "DMNHOMBCTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhomBaoCaoThuoc dmnhombcthuocMaso;
    
    
            
    @Column(name = "DMNHOMBCTHUOCLOAITHUOC_NGGIOCN")
    private Double dmnhombcthuocNgaygiocn;
    @Column(name = "DMNHOMBCTHUOCLOAITHUOC_DT")
    private Boolean dmnhombcthuocDt;
    @Column(name = "DMNHOMBCTHUOCLOAITHUOC_QL")
    private Boolean dmnhombcthuocQl;
    @Column(name = "DMNHOMBCTHUOCLOAITHUOC_DP")
    private Boolean dmnhombcthuocDp;
            

    public DmNhomBaoCaoLoaiThuoc() {
    }

    public DmNhomBaoCaoLoaiThuoc(Integer dmnhombcthuocloaithuocMaso) {
        this.dmnhombcthuocloaithuocMaso = dmnhombcthuocloaithuocMaso;
    }

    public Integer getDmnhombcthuocloaithuocMaso() {
        return dmnhombcthuocloaithuocMaso;
    }

    public void setDmnhombcthuocloaithuocMaso(Integer dmnhombcthuocloaithuocMaso) {
        this.dmnhombcthuocloaithuocMaso = dmnhombcthuocloaithuocMaso;
    }

    public DmLoaiThuoc getDmloaithuocMaso(boolean create) {
if(create && dmloaithuocMaso == null) dmloaithuocMaso = new DmLoaiThuoc();
return dmloaithuocMaso;
}
    public DmLoaiThuoc getDmloaithuocMaso() {
        return dmloaithuocMaso;
    }

    public void setDmloaithuocMaso(DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public DmNhomBaoCaoThuoc getDmnhombcthuocMaso(boolean create) {
if(create && dmnhombcthuocMaso == null) dmnhombcthuocMaso = new DmNhomBaoCaoThuoc();
return dmnhombcthuocMaso;
}
    public DmNhomBaoCaoThuoc getDmnhombcthuocMaso() {
        return dmnhombcthuocMaso;
    }

    public void setDmnhombcthuocMaso(DmNhomBaoCaoThuoc dmnhombcthuocMaso) {
        this.dmnhombcthuocMaso = dmnhombcthuocMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmnhombcthuocloaithuocMaso != null ? dmnhombcthuocloaithuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmNhomBaoCaoLoaiThuoc)) {
            return false;
        }
        DmNhomBaoCaoLoaiThuoc other = (DmNhomBaoCaoLoaiThuoc) object;
        if ((this.dmnhombcthuocloaithuocMaso == null && other.dmnhombcthuocloaithuocMaso != null) || (this.dmnhombcthuocloaithuocMaso != null && !this.dmnhombcthuocloaithuocMaso.equals(other.dmnhombcthuocloaithuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmNhomBaoCaoLoaiThuoc[dmnhombcthuocloaithuocMaso=" + dmnhombcthuocloaithuocMaso + "]";
    }

    public Double getDmnhombcthuocNgaygiocn() {
        return dmnhombcthuocNgaygiocn;
    }

    public void setDmnhombcthuocNgaygiocn(Double dmnhombcthuocNgaygiocn) {
        this.dmnhombcthuocNgaygiocn = dmnhombcthuocNgaygiocn;
    }

    public Boolean getDmnhombcthuocDt() {
        return dmnhombcthuocDt;
    }

    public void setDmnhombcthuocDt(Boolean dmnhombcthuocDt) {
        this.dmnhombcthuocDt = dmnhombcthuocDt;
    }

    public Boolean getDmnhombcthuocQl() {
        return dmnhombcthuocQl;
    }

    public void setDmnhombcthuocQl(Boolean dmnhombcthuocQl) {
        this.dmnhombcthuocQl = dmnhombcthuocQl;
    }

    public Boolean getDmnhombcthuocDp() {
        return dmnhombcthuocDp;
    }

    public void setDmnhombcthuocDp(Boolean dmnhombcthuocDp) {
        this.dmnhombcthuocDp = dmnhombcthuocDp;
    }

}


