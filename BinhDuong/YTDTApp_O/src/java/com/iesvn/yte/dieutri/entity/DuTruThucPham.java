/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
 * @author HP
 */
@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "DU_TRU_THUC_PHAM")
@NamedQueries({})
public class DuTruThucPham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DU_TRU_THUC_PHAM_DTTP")
    @SequenceGenerator(name = "DU_TRU_THUC_PHAM_DTTP", sequenceName = "DU_TRU_THUC_PHAM_DTTP_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTTP_MASO", nullable = false)
    private Integer dttpMaso;
    @Column(name = "DTTP_SLTHUCDAT")
    private Float dttpSlthucdat;
    @Column(name = "DTTP_SLTHUCGIAO")
    private Float dttpSlthucgiao;
    @Column(name = "DTTP_GHICHU")
    private String dttpGhichu;
    @Column(name = "DTTP_NGAYDUTRU")
    @Temporal(TemporalType.DATE)
    private Date dttpNgaydutru;
    @JoinColumn(name = "DTDMLTP_MASO", referencedColumnName = "DTDMLTP_MASO")
    @ManyToOne(fetch = FetchType.LAZY)
    private DtDmLoaiThucPham dtdmltpMaso;

    public DuTruThucPham() {
    }

    public DuTruThucPham(Integer dttpMaso) {
        this.dttpMaso = dttpMaso;
    }

    public Integer getDttpMaso() {
        return dttpMaso;
    }

    public void setDttpMaso(Integer dttpMaso) {
        this.dttpMaso = dttpMaso;
    }

    public Float getDttpSlthucdat() {
        return dttpSlthucdat;
    }

    public void setDttpSlthucdat(Float dttpSlthucdat) {
        this.dttpSlthucdat = dttpSlthucdat;
    }

    public Float getDttpSlthucgiao() {
        return dttpSlthucgiao;
    }

    public void setDttpSlthucgiao(Float dttpSlthucgiao) {
        this.dttpSlthucgiao = dttpSlthucgiao;
    }

    public String getDttpGhichu() {
        return dttpGhichu;
    }

    public void setDttpGhichu(String dttpGhichu) {
        this.dttpGhichu = dttpGhichu;
    }

    public Date getDttpNgaydutru() {
        return dttpNgaydutru;
    }

    public void setDttpNgaydutru(Date dttpNgaydutru) {
        this.dttpNgaydutru = dttpNgaydutru;
    }

    public DtDmLoaiThucPham getDtdmltpMaso() {
        return dtdmltpMaso;
    }

    public DtDmLoaiThucPham getDtdmltpMaso(boolean create) {
        if (create && dtdmltpMaso == null) {
            dtdmltpMaso = new DtDmLoaiThucPham();
        }
        return dtdmltpMaso;
    }

    public void setDtdmltpMaso(DtDmLoaiThucPham dtdmltpMaso) {
        this.dtdmltpMaso = dtdmltpMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dttpMaso != null ? dttpMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DuTruThucPham)) {
            return false;
        }
        DuTruThucPham other = (DuTruThucPham) object;
        if ((this.dttpMaso == null && other.dttpMaso != null) || (this.dttpMaso != null && !this.dttpMaso.equals(other.dttpMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DuTruThucPham[dttpMaso=" + dttpMaso + "]";
    }
}
