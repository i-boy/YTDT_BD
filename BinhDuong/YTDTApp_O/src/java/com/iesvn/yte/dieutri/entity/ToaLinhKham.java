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
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TOA_LINH_KHAM")
@NamedQueries({})
public class ToaLinhKham implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOA_LINH_KHAM")
    @SequenceGenerator(name = "TOA_LINH_KHAM", sequenceName = "TOA_LINH_KHAM_TOALINHKHAM_MA_S", allocationSize = 1)
    @Column(name = "TOALINHKHAM_MA", nullable = false)
    private Integer toalinhkhamMa;
    @Column(name = "TOALINHKHAM_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toalinhkhamNgaygio;
    @Column(name = "TOALINHKHAM_SOTHANG")
    private Short toalinhkhamSothang;
    @Column(name = "TOALINHKHAM_TAIKHAM")
    @Temporal(TemporalType.DATE)
    private Date toalinhkhamTaikham;
    @Column(name = "TOALINHKHAM_CUM")
    private Short toalinhkhamCum;
    @Column(name = "TOALINHKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toalinhkhamNgaygiocn;
    @Column(name = "TOALINHKHAM_MAPHIEUD")
    private String toalinhkhamMaphieud;
    @Column(name = "TOALINHKHAM_STATUS")
    private String toalinhkhamStatus;
    @Column(name = "TOALINHKHAM_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date toalinhkhamNgaytt;
    @JoinColumn(name = "TOALINHKHAM_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien toalinhkhamBacsi;
    @JoinColumn(name = "TOALINHKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien toalinhkhamNhanviencn;
    @JoinColumn(name = "TOALINHKHAM_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham toalinhkhamThamkham;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toalinhkhamMa")
//    private Collection<CtToaLinhk> ctToaLinhkCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toalinhkhamMa1")
//    private Collection<CtToaLinhk> ctToaLinhkCollection1;

    public ToaLinhKham() {
    }

    public ToaLinhKham(Integer toalinhkhamMa) {
        this.toalinhkhamMa = toalinhkhamMa;
    }

    public Integer getToalinhkhamMa() {
        return toalinhkhamMa;
    }

    public void setToalinhkhamMa(Integer toalinhkhamMa) {
        this.toalinhkhamMa = toalinhkhamMa;
    }

    public Date getToalinhkhamNgaygio() {
        return toalinhkhamNgaygio;
    }

    public void setToalinhkhamNgaygio(Date toalinhkhamNgaygio) {
        this.toalinhkhamNgaygio = toalinhkhamNgaygio;
    }

    public Short getToalinhkhamSothang() {
        return toalinhkhamSothang;
    }

    public void setToalinhkhamSothang(Short toalinhkhamSothang) {
        this.toalinhkhamSothang = toalinhkhamSothang;
    }

    public Date getToalinhkhamTaikham() {
        return toalinhkhamTaikham;
    }

    public void setToalinhkhamTaikham(Date toalinhkhamTaikham) {
        this.toalinhkhamTaikham = toalinhkhamTaikham;
    }

    public Short getToalinhkhamCum() {
        return toalinhkhamCum;
    }

    public void setToalinhkhamCum(Short toalinhkhamCum) {
        this.toalinhkhamCum = toalinhkhamCum;
    }

    public Date getToalinhkhamNgaygiocn() {
        return toalinhkhamNgaygiocn;
    }

    public void setToalinhkhamNgaygiocn(Date toalinhkhamNgaygiocn) {
        this.toalinhkhamNgaygiocn = toalinhkhamNgaygiocn;
    }

    public String getToalinhkhamMaphieud() {
        return toalinhkhamMaphieud;
    }

    public void setToalinhkhamMaphieud(String toalinhkhamMaphieud) {
        this.toalinhkhamMaphieud = toalinhkhamMaphieud;
    }

    public String getToalinhkhamStatus() {
        return toalinhkhamStatus;
    }

    public void setToalinhkhamStatus(String toalinhkhamStatus) {
        this.toalinhkhamStatus = toalinhkhamStatus;
    }

    public Date getToalinhkhamNgaytt() {
        return toalinhkhamNgaytt;
    }

    public void setToalinhkhamNgaytt(Date toalinhkhamNgaytt) {
        this.toalinhkhamNgaytt = toalinhkhamNgaytt;
    }

    public DtDmNhanVien getToalinhkhamBacsi(boolean create) {
if(create && toalinhkhamBacsi == null) toalinhkhamBacsi = new DtDmNhanVien();
return toalinhkhamBacsi;
}
    public DtDmNhanVien getToalinhkhamBacsi() {
        return toalinhkhamBacsi;
    }

    public void setToalinhkhamBacsi(DtDmNhanVien toalinhkhamBacsi) {
        this.toalinhkhamBacsi = toalinhkhamBacsi;
    }

    public DtDmNhanVien getToalinhkhamNhanviencn(boolean create) {
if(create && toalinhkhamNhanviencn == null) toalinhkhamNhanviencn = new DtDmNhanVien();
return toalinhkhamNhanviencn;
}
    public DtDmNhanVien getToalinhkhamNhanviencn() {
        return toalinhkhamNhanviencn;
    }

    public void setToalinhkhamNhanviencn(DtDmNhanVien toalinhkhamNhanviencn) {
        this.toalinhkhamNhanviencn = toalinhkhamNhanviencn;
    }

    public ThamKham getToalinhkhamThamkham(boolean create) {
if(create && toalinhkhamThamkham == null) toalinhkhamThamkham = new ThamKham();
return toalinhkhamThamkham;
}
    public ThamKham getToalinhkhamThamkham() {
        return toalinhkhamThamkham;
    }

    public void setToalinhkhamThamkham(ThamKham toalinhkhamThamkham) {
        this.toalinhkhamThamkham = toalinhkhamThamkham;
    }

  

//    public Collection<CtToaLinhk> getCtToaLinhkCollection() {
//        return ctToaLinhkCollection;
//    }
//
//    public void setCtToaLinhkCollection(Collection<CtToaLinhk> ctToaLinhkCollection) {
//        this.ctToaLinhkCollection = ctToaLinhkCollection;
//    }

//    public Collection<CtToaLinhk> getCtToaLinhkCollection1() {
//        return ctToaLinhkCollection1;
//    }
//
//    public void setCtToaLinhkCollection1(Collection<CtToaLinhk> ctToaLinhkCollection1) {
//        this.ctToaLinhkCollection1 = ctToaLinhkCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (toalinhkhamMa != null ? toalinhkhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ToaLinhKham)) {
            return false;
        }
        ToaLinhKham other = (ToaLinhKham) object;
        if ((this.toalinhkhamMa == null && other.toalinhkhamMa != null) || (this.toalinhkhamMa != null && !this.toalinhkhamMa.equals(other.toalinhkhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.ToaLinhKham[toalinhkhamMa=" + toalinhkhamMa + "]";
    }

}


