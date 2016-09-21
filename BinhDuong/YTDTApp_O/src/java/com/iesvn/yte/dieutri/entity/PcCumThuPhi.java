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
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PC_CUM_THU_PHI")
@NamedQueries({})
public class PcCumThuPhi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC_CUM_THU_PHI_PCCTP_MA")
    @SequenceGenerator(name = "PC_CUM_THU_PHI_PCCTP_MA", sequenceName = "PC_CUM_THU_PHI_PCCTP_MA_SEQ", allocationSize = 1)
    @Column(name = "PCCTP_MA", nullable = false)
    private Integer pcctpMa;
    @Column(name = "PCCTP_TUNGAY", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pcctpTungay;
    @Column(name = "PCCTP_DENNGAY", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pcctpDenngay;
    @Column(name = "PCCTP_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pcctpNgaygiocn;
    @Column(name = "QUYEN", nullable = false)
    private short quyen;
    @JoinColumn(name = "DTDMNHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMa;
    @JoinColumn(name = "DTDMCUM_MA", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum dtdmcumMa;

    public PcCumThuPhi() {
    }

    public PcCumThuPhi(Integer pcctpMa) {
        this.pcctpMa = pcctpMa;
    }

    public PcCumThuPhi(Integer pcctpMa, Date pcctpTungay, Date pcctpDenngay, short quyen) {
        this.pcctpMa = pcctpMa;
        this.pcctpTungay = pcctpTungay;
        this.pcctpDenngay = pcctpDenngay;
        this.quyen = quyen;
    }

    public Integer getPcctpMa() {
        return pcctpMa;
    }

    public void setPcctpMa(Integer pcctpMa) {
        this.pcctpMa = pcctpMa;
    }

    public Date getPcctpTungay() {
        return pcctpTungay;
    }

    public void setPcctpTungay(Date pcctpTungay) {
        this.pcctpTungay = pcctpTungay;
    }

    public Date getPcctpDenngay() {
        return pcctpDenngay;
    }

    public void setPcctpDenngay(Date pcctpDenngay) {
        this.pcctpDenngay = pcctpDenngay;
    }

    public Date getPcctpNgaygiocn() {
        return pcctpNgaygiocn;
    }

    public void setPcctpNgaygiocn(Date pcctpNgaygiocn) {
        this.pcctpNgaygiocn = pcctpNgaygiocn;
    }

    public short getQuyen() {
        return quyen;
    }

    public void setQuyen(short quyen) {
        this.quyen = quyen;
    }

    public DtDmNhanVien getDtdmnhanvienMa() {
        return dtdmnhanvienMa;
    }
    
    public DtDmNhanVien getDtdmnhanvienMa(Boolean create) {
        if (create && dtdmnhanvienMa == null) {
            dtdmnhanvienMa = new DtDmNhanVien();
        }
        return dtdmnhanvienMa;
    }

    public void setDtdmnhanvienMa(DtDmNhanVien dtdmnhanvienMa) {
        this.dtdmnhanvienMa = dtdmnhanvienMa;
    }
    
    public DtDmCum getDtdmcumMa(Boolean create) {
        if (create && dtdmcumMa == null) {
            dtdmcumMa = new DtDmCum();
        }
        return dtdmcumMa;
    }

    public DtDmCum getDtdmcumMa() {
        return dtdmcumMa;
    }

    public void setDtdmcumMa(DtDmCum dtdmcumMa) {
        this.dtdmcumMa = dtdmcumMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pcctpMa != null ? pcctpMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PcCumThuPhi)) {
            return false;
        }
        PcCumThuPhi other = (PcCumThuPhi) object;
        if ((this.pcctpMa == null && other.pcctpMa != null) || (this.pcctpMa != null && !this.pcctpMa.equals(other.pcctpMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.PcCumThuPhi[pcctpMa=" + pcctpMa + "]";
    }

}
