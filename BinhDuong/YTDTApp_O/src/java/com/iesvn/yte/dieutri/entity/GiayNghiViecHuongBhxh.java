/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author halylam
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "GIAY_NGHI_VIEC_HUONG_BHXH")
@NamedQueries({@NamedQuery(name = "GiayNghiViecHuongBhxh.findAll", query = "SELECT g FROM GiayNghiViecHuongBhxh g"), @NamedQuery(name = "GiayNghiViecHuongBhxh.findByGnvhbhxhMaso", query = "SELECT g FROM GiayNghiViecHuongBhxh g WHERE g.gnvhbhxhMaso = :gnvhbhxhMaso"), @NamedQuery(name = "GiayNghiViecHuongBhxh.findByGnvhbhxhDonvicongtac", query = "SELECT g FROM GiayNghiViecHuongBhxh g WHERE g.gnvhbhxhDonvicongtac = :gnvhbhxhDonvicongtac"), @NamedQuery(name = "GiayNghiViecHuongBhxh.findByGnvhbhxhLydonghiviec", query = "SELECT g FROM GiayNghiViecHuongBhxh g WHERE g.gnvhbhxhLydonghiviec = :gnvhbhxhLydonghiviec"), @NamedQuery(name = "GiayNghiViecHuongBhxh.findByGnvhbhxhSongaynghi", query = "SELECT g FROM GiayNghiViecHuongBhxh g WHERE g.gnvhbhxhSongaynghi = :gnvhbhxhSongaynghi"), @NamedQuery(name = "GiayNghiViecHuongBhxh.findByGnvhbhxhTungay", query = "SELECT g FROM GiayNghiViecHuongBhxh g WHERE g.gnvhbhxhTungay = :gnvhbhxhTungay"), @NamedQuery(name = "GiayNghiViecHuongBhxh.findByGnvhbhxhDenngay", query = "SELECT g FROM GiayNghiViecHuongBhxh g WHERE g.gnvhbhxhDenngay = :gnvhbhxhDenngay")})
public class GiayNghiViecHuongBhxh implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GIAY_NGHI_VIEC_HUONG_BHXH")
    @SequenceGenerator(name = "GIAY_NGHI_VIEC_HUONG_BHXH", sequenceName = "GIAY_NGHI_VIEC_HUONG_BHXH_GNVH", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "GNVHBHXH_MASO")
    private Integer gnvhbhxhMaso;
    @Column(name = "GNVHBHXH_DONVICONGTAC")
    private String gnvhbhxhDonvicongtac;
    @Column(name = "GNVHBHXH_LYDONGHIVIEC")
    private String gnvhbhxhLydonghiviec;
    @Column(name = "GNVHBHXH_SONGAYNGHI")
    private String gnvhbhxhSongaynghi;
    @Column(name = "GNVHBHXH_TUNGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gnvhbhxhTungay;
    @Column(name = "GNVHBHXH_DENNGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gnvhbhxhDenngay;
    @JoinColumn(name = "THAMKHAM_MA", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham thamkhamMa;

    public GiayNghiViecHuongBhxh() {
    }

    public GiayNghiViecHuongBhxh(Integer gnvhbhxhMaso) {
        this.gnvhbhxhMaso = gnvhbhxhMaso;
    }

    public Integer getGnvhbhxhMaso() {
        return gnvhbhxhMaso;
    }

    public void setGnvhbhxhMaso(Integer gnvhbhxhMaso) {
        this.gnvhbhxhMaso = gnvhbhxhMaso;
    }

    public String getGnvhbhxhDonvicongtac() {
        return gnvhbhxhDonvicongtac;
    }

    public void setGnvhbhxhDonvicongtac(String gnvhbhxhDonvicongtac) {
        this.gnvhbhxhDonvicongtac = gnvhbhxhDonvicongtac;
    }

    public String getGnvhbhxhLydonghiviec() {
        return gnvhbhxhLydonghiviec;
    }

    public void setGnvhbhxhLydonghiviec(String gnvhbhxhLydonghiviec) {
        this.gnvhbhxhLydonghiviec = gnvhbhxhLydonghiviec;
    }

    public String getGnvhbhxhSongaynghi() {
        return gnvhbhxhSongaynghi;
    }

    public void setGnvhbhxhSongaynghi(String gnvhbhxhSongaynghi) {
        this.gnvhbhxhSongaynghi = gnvhbhxhSongaynghi;
    }

    public Date getGnvhbhxhTungay() {
        return gnvhbhxhTungay;
    }

    public void setGnvhbhxhTungay(Date gnvhbhxhTungay) {
        this.gnvhbhxhTungay = gnvhbhxhTungay;
    }

    public Date getGnvhbhxhDenngay() {
        return gnvhbhxhDenngay;
    }

    public void setGnvhbhxhDenngay(Date gnvhbhxhDenngay) {
        this.gnvhbhxhDenngay = gnvhbhxhDenngay;
    }

    public ThamKham getThamkhamMa() {
        return thamkhamMa;
    }

    public void setThamkhamMa(ThamKham thamkhamMa) {
        this.thamkhamMa = thamkhamMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gnvhbhxhMaso != null ? gnvhbhxhMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiayNghiViecHuongBhxh)) {
            return false;
        }
        GiayNghiViecHuongBhxh other = (GiayNghiViecHuongBhxh) object;
        if ((this.gnvhbhxhMaso == null && other.gnvhbhxhMaso != null) || (this.gnvhbhxhMaso != null && !this.gnvhbhxhMaso.equals(other.gnvhbhxhMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.GiayNghiViecHuongBhxh[gnvhbhxhMaso=" + gnvhbhxhMaso + "]";
    }

}
