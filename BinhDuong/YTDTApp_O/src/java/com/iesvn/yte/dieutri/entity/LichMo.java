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
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "LICH_MO")
@NamedQueries({})
public class LichMo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LICH_MO_LICHMO")
    @SequenceGenerator(name = "LICH_MO_LICHMO", sequenceName = "LICH_MO_LICHMO_MA_SEQ", allocationSize = 1)
    @Column(name = "LICHMO_MA", nullable = false)
    private Integer lichmoMa;
    @Column(name = "LICHMO_KIPMO")
    private String lichmoKipmo;
    @Column(name = "LICHMO_KIPGAYME")
    private String lichmoKipgayme;
    @Column(name = "LICHMO_NGAYGIOMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lichmoNgaygiomo;
    @Column(name = "LICHMO_NGAYGIOMOK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lichmoNgaygiomok;
    @Column(name = "LICHMO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lichmoNgaygiocn;
    @JoinColumn(name = "TUANMO_MA", referencedColumnName = "TUANMO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TuanMo tuanmoMa;
    @JoinColumn(name = "HSBAMO_MA", referencedColumnName = "HSBAMO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaMo hsbamoMa;
    @JoinColumn(name = "PHONGMO_MA", referencedColumnName = "DTDMPHONGMO_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmPhongMo phongmoMa;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;


    public LichMo() {
    }

    public LichMo(Integer lichmoMa) {
        this.lichmoMa = lichmoMa;
    }

    public Integer getLichmoMa() {
        return lichmoMa;
    }

    public void setLichmoMa(Integer lichmoMa) {
        this.lichmoMa = lichmoMa;
    }

    public String getLichmoKipmo() {
        return lichmoKipmo;
    }

    public void setLichmoKipmo(String lichmoKipmo) {
        this.lichmoKipmo = lichmoKipmo;
    }

    public String getLichmoKipgayme() {
        return lichmoKipgayme;
    }

    public void setLichmoKipgayme(String lichmoKipgayme) {
        this.lichmoKipgayme = lichmoKipgayme;
    }

    public Date getLichmoNgaygiomo() {
        return lichmoNgaygiomo;
    }

    public void setLichmoNgaygiomo(Date lichmoNgaygiomo) {
        this.lichmoNgaygiomo = lichmoNgaygiomo;
    }

    public Date getLichmoNgaygiomok() {
        return lichmoNgaygiomok;
    }

    public void setLichmoNgaygiomok(Date lichmoNgaygiomok) {
        this.lichmoNgaygiomok = lichmoNgaygiomok;
    }

    public Date getLichmoNgaygiocn() {
        return lichmoNgaygiocn;
    }

    public void setLichmoNgaygiocn(Date lichmoNgaygiocn) {
        this.lichmoNgaygiocn = lichmoNgaygiocn;
    }

    public TuanMo getTuanmoMa(boolean create) {
if(create && tuanmoMa == null) tuanmoMa = new TuanMo();
return tuanmoMa;
}
    public TuanMo getTuanmoMa() {
        return tuanmoMa;
    }

    public void setTuanmoMa(TuanMo tuanmoMa) {
        this.tuanmoMa = tuanmoMa;
    }

    public HsbaMo getHsbamoMa(boolean create) {
if(create && hsbamoMa == null) hsbamoMa = new HsbaMo();
return hsbamoMa;
}
    public HsbaMo getHsbamoMa() {
        return hsbamoMa;
    }

    public void setHsbamoMa(HsbaMo hsbamoMa) {
        this.hsbamoMa = hsbamoMa;
    }

    public DtDmPhongMo getPhongmoMa(boolean create) {
if(create && phongmoMa == null) phongmoMa = new DtDmPhongMo();
return phongmoMa;
}
    public DtDmPhongMo getPhongmoMa() {
        return phongmoMa;
    }

    public void setPhongmoMa(DtDmPhongMo phongmoMa) {
        this.phongmoMa = phongmoMa;
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

  


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lichmoMa != null ? lichmoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LichMo)) {
            return false;
        }
        LichMo other = (LichMo) object;
        if ((this.lichmoMa == null && other.lichmoMa != null) || (this.lichmoMa != null && !this.lichmoMa.equals(other.lichmoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.LichMo[lichmoMa=" + lichmoMa + "]";
    }

}


