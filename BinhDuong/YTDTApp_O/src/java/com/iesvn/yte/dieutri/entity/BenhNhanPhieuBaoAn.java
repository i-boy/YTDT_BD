/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
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
 * @author HP
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "BENH_NHAN_PHIEU_BAO_AN")
@NamedQueries({})
public class BenhNhanPhieuBaoAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BENH_NHAN_PHIEU_BAO_AN")
    @SequenceGenerator(name = "BENH_NHAN_PHIEU_BAO_AN", sequenceName = "BENH_NHAN_PHIEU_BAO_AN_BNPBA_M", allocationSize = 1)
    @Column(name = "BNPBA_MASO", nullable = false)
    private Integer bnpbaMaso;
    @Column(name = "BNPBA_PHUTROI")
    private Short bnpbaPhutroi;
    @Column(name = "DTDMLSDN_MASO")
    private Integer dtdmlsdnMaso;
    @Column(name = "BNPBA_SOLUONG")
    private Integer bnpbaSoluong;
    @Column(name = "BNPBA_DANGSUA")
    private Short bnpbaDangsua;
    @JoinColumn(name = "PHIEUBAOAN_MASO", referencedColumnName = "PHIEUBAOAN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuBaoAn phieubaoanMaso;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "DTDMLA_MASO", referencedColumnName = "DTDMLA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn dtdmlaMaso;
    @JoinColumn(name = "DTDMLA2_MASO", referencedColumnName = "DTDMLA2_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiAn2 dtdmla2Maso;
    @JoinColumn(name = "DTDMDTA_MASO", referencedColumnName = "DTDMDTA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmDoiTuongAn dtdmdtaMaso;
    @JoinColumn(name = "DTDMMA_MASO", referencedColumnName = "DTDMMA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmMucAn dtdmmaMaso;
    @JoinColumn(name = "DTDMDT_MASO", referencedColumnName = "DTDMDT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmDongThem dtdmdtMaso;

    public BenhNhanPhieuBaoAn() {
    }

    public BenhNhanPhieuBaoAn(Integer bnpbaMaso) {
        this.bnpbaMaso = bnpbaMaso;
    }

    public Integer getBnpbaMaso() {
        return bnpbaMaso;
    }

    public void setBnpbaMaso(Integer bnpbaMaso) {
        this.bnpbaMaso = bnpbaMaso;
    }

    public Short getBnpbaPhutroi() {
        return bnpbaPhutroi;
    }

    public void setBnpbaPhutroi(Short bnpbaPhutroi) {
        this.bnpbaPhutroi = bnpbaPhutroi;
    }

    public Integer getDtdmlsdnMaso() {
        return dtdmlsdnMaso;
    }

    public void setDtdmlsdnMaso(Integer dtdmlsdnMaso) {
        this.dtdmlsdnMaso = dtdmlsdnMaso;
    }

    public Integer getBnpbaSoluong() {
        return bnpbaSoluong;
    }

    public void setBnpbaSoluong(Integer bnpbaSoluong) {
        this.bnpbaSoluong = bnpbaSoluong;
    }

    public Short getBnpbaDangsua() {
        return bnpbaDangsua;
    }

    public void setBnpbaDangsua(Short bnpbaDangsua) {
        this.bnpbaDangsua = bnpbaDangsua;
    }

    public PhieuBaoAn getPhieubaoanMaso() {
        return phieubaoanMaso;
    }

    public void setPhieubaoanMaso(PhieuBaoAn phieubaoanMaso) {
        this.phieubaoanMaso = phieubaoanMaso;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
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

    public DtDmDoiTuongAn getDtdmdtaMaso() {
        return dtdmdtaMaso;
    }

    public void setDtdmdtaMaso(DtDmDoiTuongAn dtdmdtaMaso) {
        this.dtdmdtaMaso = dtdmdtaMaso;
    }

    public DtDmMucAn getDtdmmaMaso() {
        return dtdmmaMaso;
    }

    public void setDtdmmaMaso(DtDmMucAn dtdmmaMaso) {
        this.dtdmmaMaso = dtdmmaMaso;
    }

    public DtDmDongThem getDtdmdtMaso() {
        return dtdmdtMaso;
    }

    public void setDtdmdtMaso(DtDmDongThem dtdmdtMaso) {
        this.dtdmdtMaso = dtdmdtMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bnpbaMaso != null ? bnpbaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BenhNhanPhieuBaoAn)) {
            return false;
        }
        BenhNhanPhieuBaoAn other = (BenhNhanPhieuBaoAn) object;
        if ((this.bnpbaMaso == null && other.bnpbaMaso != null) || (this.bnpbaMaso != null && !this.bnpbaMaso.equals(other.bnpbaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.BenhNhanPhieuBaoAn[bnpbaMaso=" + bnpbaMaso + "]";
    }

}
