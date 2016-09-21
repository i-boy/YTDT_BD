/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmDonViTinh;
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
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_LOAI_THUC_PHAM")
@NamedQueries({})
public class DtDmLoaiThucPham implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOAI_THUC_PHAM")
    @SequenceGenerator(name = "DT_DM_LOAI_THUC_PHAM", sequenceName = "DT_DM_LOAI_THUC_PHAM_DTDMLTP_M", allocationSize = 1)
    @Column(name = "DTDMLTP_MASO", nullable = false)
    private Integer dtdmltpMaso;
    @Column(name = "DTDMLTP_MA", nullable = false)
    private String dtdmltpMa;
    @Column(name = "DTDMLTP_TEN")
    private String dtdmltpTen;
    @Column(name = "DTDMLTP_NGAYGIOCN")
    private Double dtdmltpNgaygiocn;
    @Column(name = "DTDMLTP_CHON")
    private Boolean dtdmltpChon;
    @JoinColumn(name = "DMDONVITINH_MASO", referencedColumnName = "DMDONVITINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDonViTinh dmdonvitinhMaso;

    public DtDmLoaiThucPham() {
    }

    public DtDmLoaiThucPham(Integer dtdmltpMaso) {
        this.dtdmltpMaso = dtdmltpMaso;
    }

    public DtDmLoaiThucPham(Integer dtdmltpMaso, String dtdmltpMa) {
        this.dtdmltpMaso = dtdmltpMaso;
        this.dtdmltpMa = dtdmltpMa;
    }

    public Integer getDtdmltpMaso() {
        return dtdmltpMaso;
    }

    public void setDtdmltpMaso(Integer dtdmltpMaso) {
        this.dtdmltpMaso = dtdmltpMaso;
    }

    public String getDtdmltpMa() {
        return dtdmltpMa;
    }

    public void setDtdmltpMa(String dtdmltpMa) {
        this.dtdmltpMa = dtdmltpMa;
    }

    public String getDtdmltpTen() {
        return dtdmltpTen;
    }

    public void setDtdmltpTen(String dtdmltpTen) {
        this.dtdmltpTen = dtdmltpTen;
    }

    public Double getDtdmltpNgaygiocn() {
        return dtdmltpNgaygiocn;
    }

    public void setDtdmltpNgaygiocn(Double dtdmltpNgaygiocn) {
        this.dtdmltpNgaygiocn = dtdmltpNgaygiocn;
    }

    public Boolean getDtdmltpChon() {
        return dtdmltpChon;
    }

    public void setDtdmltpChon(Boolean dtdmltpChon) {
        this.dtdmltpChon = dtdmltpChon;
    }

    public DmDonViTinh getDmdonvitinhMaso() {
        return dmdonvitinhMaso;
    }

    public void setDmdonvitinhMaso(DmDonViTinh dmdonvitinhMaso) {
        this.dmdonvitinhMaso = dmdonvitinhMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmltpMaso != null ? dtdmltpMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLoaiThucPham)) {
            return false;
        }
        DtDmLoaiThucPham other = (DtDmLoaiThucPham) object;
        if ((this.dtdmltpMaso == null && other.dtdmltpMaso != null) || (this.dtdmltpMaso != null && !this.dtdmltpMaso.equals(other.dtdmltpMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham[dtdmltpMaso=" + dtdmltpMaso + "]";
    }

}
