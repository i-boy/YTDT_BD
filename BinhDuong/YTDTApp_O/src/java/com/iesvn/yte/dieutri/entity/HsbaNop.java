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
@Table(name = "HSBA_NOP")
@NamedQueries({})
public class HsbaNop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_NOP_HSBANOP_MA")
    @SequenceGenerator(name = "HSBA_NOP_HSBANOP_MA", sequenceName = "HSBA_NOP_HSBANOP_MA_SEQ", allocationSize = 1)
    @Column(name = "HSBANOP_MA", nullable = false)
    private Integer hsbanopMa;
    @Column(name = "HSBANOP_NGAYGIONOP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbanopNgaygionop;
    @Column(name = "HSBANOP_NGAYGIOLUUTRU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbanopNgaygioluutru;
    @Column(name = "HSBANOP_SOLUUTRU")
    private String hsbanopSoluutru;
    @Column(name = "HSBANOP_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbanopNgaygiocn;
    @Column(name = "HSBANOP_LOAIBA")
    private Integer hsbanopLoaiba;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSBANOP_NGUOINOP", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbanopNguoinop;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;

//    @OneToMany(mappedBy = "hsbaMasogiao")
//    private Collection<Hsba> hsbaCollection;

    public HsbaNop() {
    }

    public HsbaNop(Integer hsbanopMa) {
        this.hsbanopMa = hsbanopMa;
    }

    public Integer getHsbanopMa() {
        return hsbanopMa;
    }

    public void setHsbanopMa(Integer hsbanopMa) {
        this.hsbanopMa = hsbanopMa;
    }

    public Date getHsbanopNgaygionop() {
        return hsbanopNgaygionop;
    }

    public void setHsbanopNgaygionop(Date hsbanopNgaygionop) {
        this.hsbanopNgaygionop = hsbanopNgaygionop;
    }

    public Date getHsbanopNgaygioluutru() {
        return hsbanopNgaygioluutru;
    }

    public void setHsbanopNgaygioluutru(Date hsbanopNgaygioluutru) {
        this.hsbanopNgaygioluutru = hsbanopNgaygioluutru;
    }

    public String getHsbanopSoluutru() {
        return hsbanopSoluutru;
    }

    public void setHsbanopSoluutru(String hsbanopSoluutru) {
        this.hsbanopSoluutru = hsbanopSoluutru;
    }

    public Date getHsbanopNgaygiocn() {
        return hsbanopNgaygiocn;
    }

    public void setHsbanopNgaygiocn(Date hsbanopNgaygiocn) {
        this.hsbanopNgaygiocn = hsbanopNgaygiocn;
    }

    public Integer getHsbanopLoaiba() {
        return hsbanopLoaiba;
    }

    public void setHsbanopLoaiba(Integer hsbanopLoaiba) {
        this.hsbanopLoaiba = hsbanopLoaiba;
    }

    public Hsba getHsbaSovaovien(boolean create) {
if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
return hsbaSovaovien;
}
    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DtDmNhanVien getHsbanopNguoinop(boolean create) {
if(create && hsbanopNguoinop == null) hsbanopNguoinop = new DtDmNhanVien();
return hsbanopNguoinop;
}
    public DtDmNhanVien getHsbanopNguoinop() {
        return hsbanopNguoinop;
    }

    public void setHsbanopNguoinop(DtDmNhanVien hsbanopNguoinop) {
        this.hsbanopNguoinop = hsbanopNguoinop;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
return nhanvienMa;
}
    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

  

//    public Collection<Hsba> getHsbaCollection() {
//        return hsbaCollection;
//    }
//
//    public void setHsbaCollection(Collection<Hsba> hsbaCollection) {
//        this.hsbaCollection = hsbaCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbanopMa != null ? hsbanopMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaNop)) {
            return false;
        }
        HsbaNop other = (HsbaNop) object;
        if ((this.hsbanopMa == null && other.hsbanopMa != null) || (this.hsbanopMa != null && !this.hsbanopMa.equals(other.hsbanopMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaNop[hsbanopMa=" + hsbanopMa + "]";
    }

}


