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
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TON_KHO_DINH_DUONG")
@NamedQueries({})
public class TonKhoDinhDuong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TON_KHO_DINH_DUONG")
    @SequenceGenerator(name = "TON_KHO_DINH_DUONG", sequenceName = "TON_KHO_DINH_DUONG_TKDD_MASO_S", allocationSize = 1)
    @Column(name = "TKDD_MASO", nullable = false)
    private Integer tkddMaso;
    @Column(name = "TKDD_NGAYTON")
    @Temporal(TemporalType.DATE)
    private Date tkddNgayton;
    @Column(name = "TKDD_SOLUONG")
    private Float tkddSoluong;
    @Column(name = "TKDD_DONVITINH")
    private Short tkddDonvitinh;
    @JoinColumn(name = "DTDMLA_MASO", referencedColumnName = "DTDMLA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn dtdmlaMaso;
    @JoinColumn(name = "DTDMLA2_MASO", referencedColumnName = "DTDMLA2_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn2 dtdmla2Maso;
    @JoinColumn(name = "DTDMNSX_MASO", referencedColumnName = "DTDMNSX_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhaSxSpdd dtdmnsxMaso;

    public TonKhoDinhDuong() {
    }

    public TonKhoDinhDuong(Integer tkddMaso) {
        this.tkddMaso = tkddMaso;
    }

    public Integer getTkddMaso() {
        return tkddMaso;
    }

    public void setTkddMaso(Integer tkddMaso) {
        this.tkddMaso = tkddMaso;
    }

    public Date getTkddNgayton() {
        return tkddNgayton;
    }

    public void setTkddNgayton(Date tkddNgayton) {
        this.tkddNgayton = tkddNgayton;
    }

    public Float getTkddSoluong() {
        return tkddSoluong;
    }

    public void setTkddSoluong(Float tkddSoluong) {
        this.tkddSoluong = tkddSoluong;
    }

    public Short getTkddDonvitinh() {
        return tkddDonvitinh;
    }

    public void setTkddDonvitinh(Short tkddDonvitinh) {
        this.tkddDonvitinh = tkddDonvitinh;
    }

    public DtDmLoaiAn getDtdmlaMaso() {
        return dtdmlaMaso;
    }

    public void setDtdmlaMaso(DtDmLoaiAn dtdmlaMaso) {
        this.dtdmlaMaso = dtdmlaMaso;
    }

    public DtDmLoaiAn2 getDtdmla2Maso() {
        return dtdmla2Maso;
    }

    public void setDtdmla2Maso(DtDmLoaiAn2 dtdmla2Maso) {
        this.dtdmla2Maso = dtdmla2Maso;
    }

    public DtDmNhaSxSpdd getDtdmnsxMaso() {
        return dtdmnsxMaso;
    }

    public void setDtdmnsxMaso(DtDmNhaSxSpdd dtdmnsxMaso) {
        this.dtdmnsxMaso = dtdmnsxMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tkddMaso != null ? tkddMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TonKhoDinhDuong)) {
            return false;
        }
        TonKhoDinhDuong other = (TonKhoDinhDuong) object;
        if ((this.tkddMaso == null && other.tkddMaso != null) || (this.tkddMaso != null && !this.tkddMaso.equals(other.tkddMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.TonKhoDinhDuong[tkddMaso=" + tkddMaso + "]";
    }

}
