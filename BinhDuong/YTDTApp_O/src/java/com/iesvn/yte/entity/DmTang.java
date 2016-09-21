/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author user01
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_TANG")
@NamedQueries({
    @NamedQuery(name = "DmTang.findAll", query = "SELECT d FROM DmTang d")})
public class DmTang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TANG_DMTANG")
    @SequenceGenerator(name = "DM_TANG_DMTANG", sequenceName = "DM_TANG_DMTANG_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMTANG_MASO")
    private Integer dmtangMaso;
    @Column(name = "DMTANG_MA")
    private String dmtangMa;
    @Column(name = "DMTANG_TEN")
    private String dmtangTen;
    @Column(name = "DMTANG_DEFAULT")
    private Boolean dmtangDefault;
    @Column(name = "DMTANG_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dmtangNgaygiocn;
    @Column(name = "DMTANG_CHON")
    private Boolean dmtangChon;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
    @JoinColumn(name = "DMTANG_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dmtangNhanviencn;

    public DmTang() {
    }

    public DmTang(Integer dmtangMaso) {
        this.dmtangMaso = dmtangMaso;
    }

    public DmTang(Integer dmtangMaso, String dmtangMa, String dmtangTen, Date dmtangNgaygiocn) {
        this.dmtangMaso = dmtangMaso;
        this.dmtangMa = dmtangMa;
        this.dmtangTen = dmtangTen;
        this.dmtangNgaygiocn = dmtangNgaygiocn;
    }

    public Integer getDmtangMaso() {
        return dmtangMaso;
    }

    public void setDmtangMaso(Integer dmtangMaso) {
        this.dmtangMaso = dmtangMaso;
    }

    public String getDmtangMa() {
        return dmtangMa;
    }

    public void setDmtangMa(String dmtangMa) {
        this.dmtangMa = dmtangMa;
    }

    public String getDmtangTen() {
        return dmtangTen;
    }

    public void setDmtangTen(String dmtangTen) {
        this.dmtangTen = dmtangTen;
    }

    public Boolean getDmtangDefault() {
        return dmtangDefault;
    }

    public void setDmtangDefault(Boolean dmtangDefault) {
        this.dmtangDefault = dmtangDefault;
    }

    public Date getDmtangNgaygiocn() {
        return dmtangNgaygiocn;
    }

    public void setDmtangNgaygiocn(Date dmtangNgaygiocn) {
        this.dmtangNgaygiocn = dmtangNgaygiocn;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
        if (create && dmkhoaMaso == null) {
            dmkhoaMaso = new DmKhoa();
        }
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DtDmNhanVien getDmtangNhanviencn() {
        return dmtangNhanviencn;
    }

    public DtDmNhanVien getDmtangNhanviencn(boolean create) {
        if (create && dmtangNhanviencn == null) {
            dmtangNhanviencn = new DtDmNhanVien();
        }
        return dmtangNhanviencn;
    }

    public void setDmtangNhanviencn(DtDmNhanVien dmtangNhanviencn) {
        this.dmtangNhanviencn = dmtangNhanviencn;
    }

    public Boolean getDmtangChon() {
        return dmtangChon;
    }

    public void setDmtangChon(Boolean dmtangChon) {
        this.dmtangChon = dmtangChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmtangMaso != null ? dmtangMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTang)) {
            return false;
        }
        DmTang other = (DmTang) object;
        if ((this.dmtangMaso == null && other.dmtangMaso != null) || (this.dmtangMaso != null && !this.dmtangMaso.equals(other.dmtangMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.entity.DmTang[dmtangMaso=" + dmtangMaso + "]";
    }

}
