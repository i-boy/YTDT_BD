/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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

/**
 *
 * @author james
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_GIAO_BAN_THANH_PHAN_THAM_DU")
@NamedQueries({})
public class PhieuGiaoBanThanhPhanThamDu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NnkdLoaigiayphep")
//    @SequenceGenerator(name = "NnkdLoaigiayphep", sequenceName = "NnkdLoaigiayphep_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "PGBTPTD_MASO")
    private Integer pgbtptdMaso;
    @Column(name = "PGBTPTD_VAITRO")
    private Boolean pgbtptdVaitro;
    @Column(name = "PGBTPTD_NGAYGIOCN")
    private Double pgbtptdNgaygiocn;
    @Column(name = "PGBTPTD_CHON")
    private Boolean pgbtptdChon;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;
    @JoinColumn(name = "PGB_MA", referencedColumnName = "PGB_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuGiaoBan pgbMa;

    public PhieuGiaoBanThanhPhanThamDu() {
    }

    public PhieuGiaoBanThanhPhanThamDu(Integer pgbtptdMaso) {
        this.pgbtptdMaso = pgbtptdMaso;
    }

    public Integer getPgbtptdMaso() {
        return pgbtptdMaso;
    }

    public void setPgbtptdMaso(Integer pgbtptdMaso) {
        this.pgbtptdMaso = pgbtptdMaso;
    }

    public Boolean getPgbtptdVaitro() {
        return pgbtptdVaitro;
    }

    public void setPgbtptdVaitro(Boolean pgbtptdVaitro) {
        this.pgbtptdVaitro = pgbtptdVaitro;
    }

    public Double getPgbtptdNgaygiocn() {
        return pgbtptdNgaygiocn;
    }

    public void setPgbtptdNgaygiocn(Double pgbtptdNgaygiocn) {
        this.pgbtptdNgaygiocn = pgbtptdNgaygiocn;
    }

    public Boolean getPgbtptdChon() {
        return pgbtptdChon;
    }

    public void setPgbtptdChon(Boolean pgbtptdChon) {
        this.pgbtptdChon = pgbtptdChon;
    }

    public DtDmNhanVien getDtdmnhanvienMaso() {
        return dtdmnhanvienMaso;
    }

    public void setDtdmnhanvienMaso(DtDmNhanVien dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

    public PhieuGiaoBan getPgbMa() {
        return pgbMa;
    }

    public void setPgbMa(PhieuGiaoBan pgbMa) {
        this.pgbMa = pgbMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pgbtptdMaso != null ? pgbtptdMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuGiaoBanThanhPhanThamDu)) {
            return false;
        }
        PhieuGiaoBanThanhPhanThamDu other = (PhieuGiaoBanThanhPhanThamDu) object;
        if ((this.pgbtptdMaso == null && other.pgbtptdMaso != null) || (this.pgbtptdMaso != null && !this.pgbtptdMaso.equals(other.pgbtptdMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu[pgbtptdMaso=" + pgbtptdMaso + "]";
    }

}
