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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thao
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_GIAY_CHUNG_NHAN_PHTHUAT")
@NamedQueries({}) 
public class HsbaGiayChungNhanPhauThuat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_GIAY_CHUNG_NHAN_PHAU")
    @SequenceGenerator(name = "HSBA_GIAY_CHUNG_NHAN_PHAU", sequenceName = "HSBA_GIAY_CHUNG_NHAN_PHTHUAT_H", allocationSize = 1)
    @Column(name = "HSBAGCNPT_MASO", nullable = false)
    private Integer hsbagcnptMaso;
    @Column(name = "HSBAGCNPT_NOIYEUCAU")
    private String hsbagcnptNoiyeucau;
    @Column(name = "HSBAGCNPT_NGAYYC")
    @Temporal(TemporalType.DATE)
    private Date hsbagcnptNgayyc;
    @Column(name = "HSBAGCNPT_NOIDUNG")
    private String hsbagcnptNoidung;
    @Column(name = "HSBAGCNPT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbagcnptNgaygiocn;
    @Column(name = "HSBAGCNPT_MA")
    private String hsbagcnptMa;
    @Column(name = "HSBAGCNPT_SONGAYTK")
    private Integer hsbagcnptSongaytk;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;

    public HsbaGiayChungNhanPhauThuat() {
    }

    public HsbaGiayChungNhanPhauThuat(Integer hsbagcnptMaso) {
        this.hsbagcnptMaso = hsbagcnptMaso;
    }

    public Integer getHsbagcnptMaso() {
        return hsbagcnptMaso;
    }

    public void setHsbagcnptMaso(Integer hsbagcnptMaso) {
        this.hsbagcnptMaso = hsbagcnptMaso;
    }

    public String getHsbagcnptNoiyeucau() {
        return hsbagcnptNoiyeucau;
    }

    public void setHsbagcnptNoiyeucau(String hsbagcnptNoiyeucau) {
        this.hsbagcnptNoiyeucau = hsbagcnptNoiyeucau;
    }

    public Date getHsbagcnptNgayyc() {
        return hsbagcnptNgayyc;
    }

    public void setHsbagcnptNgayyc(Date hsbagcnptNgayyc) {
        this.hsbagcnptNgayyc = hsbagcnptNgayyc;
    }

    public String getHsbagcnptNoidung() {
        return hsbagcnptNoidung;
    }

    public void setHsbagcnptNoidung(String hsbagcnptNoidung) {
        this.hsbagcnptNoidung = hsbagcnptNoidung;
    }

    public Date getHsbagcnptNgaygiocn() {
        return hsbagcnptNgaygiocn;
    }

    public void setHsbagcnptNgaygiocn(Date hsbagcnptNgaygiocn) {
        this.hsbagcnptNgaygiocn = hsbagcnptNgaygiocn;
    }

    public String getHsbagcnptMa() {
        return hsbagcnptMa;
    }

    public void setHsbagcnptMa(String hsbagcnptMa) {
        this.hsbagcnptMa = hsbagcnptMa;
    }

    public Integer getHsbagcnptSongaytk() {
        return hsbagcnptSongaytk;
    }

    public void setHsbagcnptSongaytk(Integer hsbagcnptSongaytk) {
        this.hsbagcnptSongaytk = hsbagcnptSongaytk;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }
    
    public Hsba getHsbaSovaovien(boolean create) {
        if (create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }
    
    public DtDmNhanVien getNhanvienMa(boolean create) {
        if (create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbagcnptMaso != null ? hsbagcnptMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaGiayChungNhanPhauThuat)) {
            return false;
        }
        HsbaGiayChungNhanPhauThuat other = (HsbaGiayChungNhanPhauThuat) object;
        if ((this.hsbagcnptMaso == null && other.hsbagcnptMaso != null) || (this.hsbagcnptMaso != null && !this.hsbagcnptMaso.equals(other.hsbagcnptMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tmp.HsbaGiayChungNhanPhauThuat[hsbagcnptMaso=" + hsbagcnptMaso + "]";
    }

}
