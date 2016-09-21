/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
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
@Table(name = "TOA_THUOC_KHAM")
@NamedQueries({})
public class ToaThuocKham implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOA_THUOC_KHAM")
    @SequenceGenerator(name = "TOA_THUOC_KHAM", sequenceName = "TOA_THUOC_KHAM_TOATHUOCKHAM_MA", allocationSize = 1)
    @Column(name = "TOATHUOCKHAM_MA", nullable = false)
    private Integer toathuockhamMa;
    @Column(name = "TOATHUOCKHAM_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toathuockhamNgaygio;
    @Column(name = "TOATHUOCKHAM_SOTHANG")
    private Short toathuockhamSothang;
    @Column(name = "TOATHUOCKHAM_LOIDAN")
    private String toathuockhamLoidan;
    @Column(name = "TOATHUOCKHAM_TAIKHAM")
    @Temporal(TemporalType.DATE)
    private Date toathuockhamTaikham;
    @Column(name = "TOATHUOCKHAM_CUM")
    private Short toathuockhamCum;
    @Column(name = "TOATHUOCKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toathuockhamNgaygiocn;
    @Column(name = "TOATHUOCKHAM_MAPHIEUD")
    private String toathuockhamMaphieud;
    @Column(name = "TOATHUOCKHAM_STATUS")
    private String toathuockhamStatus;
    @Column(name = "TOATHUOCKHAM_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date toathuockhamNgaytt;
    @JoinColumn(name = "TOATHUOCKHAM_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien toathuockhamBacsi;
    @JoinColumn(name = "TOATHUOCKHAM_KHO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa toathuockhamKho;
    @JoinColumn(name = "TOATHUOCKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien toathuockhamNhanviencn;
    @JoinColumn(name = "TOATHUOCKHAM_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham toathuockhamThamkham;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toathuockhamMa")
//    private Collection<CtToaThuock> ctToaThuockCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toathuockhamMa1")
//    private Collection<CtToaThuock> ctToaThuockCollection1;

    public ToaThuocKham() {
    }

    public ToaThuocKham(Integer toathuockhamMa) {
        this.toathuockhamMa = toathuockhamMa;
    }

    public Integer getToathuockhamMa() {
        return toathuockhamMa;
    }

    public void setToathuockhamMa(Integer toathuockhamMa) {
        this.toathuockhamMa = toathuockhamMa;
    }

    public Date getToathuockhamNgaygio() {
        return toathuockhamNgaygio;
    }

    public void setToathuockhamNgaygio(Date toathuockhamNgaygio) {
        this.toathuockhamNgaygio = toathuockhamNgaygio;
    }

    public Short getToathuockhamSothang() {
        return toathuockhamSothang;
    }

    public void setToathuockhamSothang(Short toathuockhamSothang) {
        this.toathuockhamSothang = toathuockhamSothang;
    }

    public String getToathuockhamLoidan() {
        return toathuockhamLoidan;
    }

    public void setToathuockhamLoidan(String toathuockhamLoidan) {
        this.toathuockhamLoidan = toathuockhamLoidan;
    }

    public Date getToathuockhamTaikham() {
        return toathuockhamTaikham;
    }

    public void setToathuockhamTaikham(Date toathuockhamTaikham) {
        this.toathuockhamTaikham = toathuockhamTaikham;
    }

    public Short getToathuockhamCum() {
        return toathuockhamCum;
    }

    public void setToathuockhamCum(Short toathuockhamCum) {
        this.toathuockhamCum = toathuockhamCum;
    }

    public Date getToathuockhamNgaygiocn() {
        return toathuockhamNgaygiocn;
    }

    public void setToathuockhamNgaygiocn(Date toathuockhamNgaygiocn) {
        this.toathuockhamNgaygiocn = toathuockhamNgaygiocn;
    }

    public String getToathuockhamMaphieud() {
        return toathuockhamMaphieud;
    }

    public void setToathuockhamMaphieud(String toathuockhamMaphieud) {
        this.toathuockhamMaphieud = toathuockhamMaphieud;
    }

    public String getToathuockhamStatus() {
        return toathuockhamStatus;
    }

    public void setToathuockhamStatus(String toathuockhamStatus) {
        this.toathuockhamStatus = toathuockhamStatus;
    }

    public Date getToathuockhamNgaytt() {
        return toathuockhamNgaytt;
    }

    public void setToathuockhamNgaytt(Date toathuockhamNgaytt) {
        this.toathuockhamNgaytt = toathuockhamNgaytt;
    }

    public DtDmNhanVien getToathuockhamBacsi(boolean create) {
if(create && toathuockhamBacsi == null) toathuockhamBacsi = new DtDmNhanVien();
return toathuockhamBacsi;
}
    public DtDmNhanVien getToathuockhamBacsi() {
        return toathuockhamBacsi;
    }

    public void setToathuockhamBacsi(DtDmNhanVien toathuockhamBacsi) {
        this.toathuockhamBacsi = toathuockhamBacsi;
    }

    public DmKhoa getToathuockhamKho(boolean create) {
if(create && toathuockhamKho == null) toathuockhamKho = new DmKhoa();
return toathuockhamKho;
}
    public DmKhoa getToathuockhamKho() {
        return toathuockhamKho;
    }

    public void setToathuockhamKho(DmKhoa toathuockhamKho) {
        this.toathuockhamKho = toathuockhamKho;
    }

    public DtDmNhanVien getToathuockhamNhanviencn(boolean create) {
if(create && toathuockhamNhanviencn == null) toathuockhamNhanviencn = new DtDmNhanVien();
return toathuockhamNhanviencn;
}
    public DtDmNhanVien getToathuockhamNhanviencn() {
        return toathuockhamNhanviencn;
    }

    public void setToathuockhamNhanviencn(DtDmNhanVien toathuockhamNhanviencn) {
        this.toathuockhamNhanviencn = toathuockhamNhanviencn;
    }

    public ThamKham getToathuockhamThamkham(boolean create) {
if(create && toathuockhamThamkham == null) toathuockhamThamkham = new ThamKham();
return toathuockhamThamkham;
}
    public ThamKham getToathuockhamThamkham() {
        return toathuockhamThamkham;
    }

    public void setToathuockhamThamkham(ThamKham toathuockhamThamkham) {
        this.toathuockhamThamkham = toathuockhamThamkham;
    }

//    public Collection<CtToaThuock> getCtToaThuockCollection() {
//        return ctToaThuockCollection;
//    }
//
//    public void setCtToaThuockCollection(Collection<CtToaThuock> ctToaThuockCollection) {
//        this.ctToaThuockCollection = ctToaThuockCollection;
//    }

//    public Collection<CtToaThuock> getCtToaThuockCollection1() {
//        return ctToaThuockCollection1;
//    }
//
//    public void setCtToaThuockCollection1(Collection<CtToaThuock> ctToaThuockCollection1) {
//        this.ctToaThuockCollection1 = ctToaThuockCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (toathuockhamMa != null ? toathuockhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ToaThuocKham)) {
            return false;
        }
        ToaThuocKham other = (ToaThuocKham) object;
        if ((this.toathuockhamMa == null && other.toathuockhamMa != null) || (this.toathuockhamMa != null && !this.toathuockhamMa.equals(other.toathuockhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.ToaThuocKham[toathuockhamMa=" + toathuockhamMa + "]";
    }

}


