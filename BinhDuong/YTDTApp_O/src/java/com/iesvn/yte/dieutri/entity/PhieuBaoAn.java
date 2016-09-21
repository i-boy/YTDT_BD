/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author james
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_BAO_AN")
@NamedQueries({})
public class PhieuBaoAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHIEU_BAO_AN_PHIEUBAOAN")
    @SequenceGenerator(name = "PHIEU_BAO_AN_PHIEUBAOAN", sequenceName = "PHIEU_BAO_AN_PHIEUBAOAN_MASO_S", allocationSize = 1)
    @Column(name = "PHIEUBAOAN_MASO", nullable = false)
    private Integer phieubaoanMaso;
    @Column(name = "PHIEUBAOAN_MA")
    private String phieubaoanMa;
    @Column(name = "PHIEUBAOAN_NGAYAN")
    @Temporal(TemporalType.DATE)
    private Date phieubaoanNgayan;
    @Column(name = "PHIEUBAOAN_TRANGTHAIDUYET")
    private Boolean phieubaoanTrangthaiduyet;
    @JoinColumn(name = "KHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa khoaMaso;
    @JoinColumn(name = "NHANVIEN_DUYET_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienDuyetMaso;
    @JoinColumn(name = "NHANVIEN_NHAP_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienNhapMaso;

    public PhieuBaoAn() {
    }

    public PhieuBaoAn(Integer phieubaoanMaso) {
        this.phieubaoanMaso = phieubaoanMaso;
    }

    public Integer getPhieubaoanMaso() {
        return phieubaoanMaso;
    }

    public void setPhieubaoanMaso(Integer phieubaoanMaso) {
        this.phieubaoanMaso = phieubaoanMaso;
    }

    public String getPhieubaoanMa() {
        return phieubaoanMa;
    }

    public void setPhieubaoanMa(String phieubaoanMa) {
        this.phieubaoanMa = phieubaoanMa;
    }

    public Date getPhieubaoanNgayan() {
        return phieubaoanNgayan;
    }

    public void setPhieubaoanNgayan(Date phieubaoanNgayan) {
        this.phieubaoanNgayan = phieubaoanNgayan;
    }

    public Boolean getPhieubaoanTrangthaiduyet() {
        return phieubaoanTrangthaiduyet;
    }

    public void setPhieubaoanTrangthaiduyet(Boolean phieubaoanTrangthaiduyet) {
        this.phieubaoanTrangthaiduyet = phieubaoanTrangthaiduyet;
    }

    public DmKhoa getKhoaMaso() {
        return khoaMaso;
    }

    public void setKhoaMaso(DmKhoa khoaMaso) {
        this.khoaMaso = khoaMaso;
    }

    public DtDmNhanVien getNhanvienDuyetMaso() {
        return nhanvienDuyetMaso;
    }

    public void setNhanvienDuyetMaso(DtDmNhanVien nhanvienDuyetMaso) {
        this.nhanvienDuyetMaso = nhanvienDuyetMaso;
    }

    public DtDmNhanVien getNhanvienNhapMaso() {
        return nhanvienNhapMaso;
    }

    public void setNhanvienNhapMaso(DtDmNhanVien nhanvienNhapMaso) {
        this.nhanvienNhapMaso = nhanvienNhapMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieubaoanMaso != null ? phieubaoanMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuBaoAn)) {
            return false;
        }
        PhieuBaoAn other = (PhieuBaoAn) object;
        if ((this.phieubaoanMaso == null && other.phieubaoanMaso != null) || (this.phieubaoanMaso != null && !this.phieubaoanMaso.equals(other.phieubaoanMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.PhieuBaoAn[phieubaoanMaso=" + phieubaoanMaso + "]";
    }

}
