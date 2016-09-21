/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmTinh;
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
@Table(name = "HSBA_BHYT")
@NamedQueries({})
public class HsbaBhyt implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_BHYT_HSBABHYT_MA")
    @SequenceGenerator(name = "HSBA_BHYT_HSBABHYT_MA", sequenceName = "HSBA_BHYT_HSBABHYT_MA_SEQ", allocationSize = 1)
    @Column(name = "HSBABHYT_MA", nullable = false)
    private Integer hsbabhytMa;
    @Column(name = "HSBABHYT_HUYENBH")
    private String hsbabhytHuyenbh;
    @Column(name = "HSBABHYT_COQUANBH")
    private String hsbabhytCoquanbh;
    @Column(name = "HSBABHYT_SOTHEBH")
    private String hsbabhytSothebh;
    @Column(name = "HSBABHYT_NAMBHYT")
    private String hsbabhytNambhyt;
    @Column(name = "HSBABHYT_NAMTG")
    private Boolean hsbabhytNamtg;
    @Column(name = "HSBABHYT_MOC1")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date hsbabhytMoc1;
    @Column(name = "HSBABHYT_MOC2")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date hsbabhytMoc2;
    @Column(name = "HSBABHYT_GIATRI0")
    @Temporal(TemporalType.DATE)
    private Date hsbabhytGiatri0;
    @Column(name = "HSBABHYT_GIATRI1")
    @Temporal(TemporalType.DATE)
    private Date hsbabhytGiatri1;
    @Column(name = "HSBABHYT_GIATRI2")
    @Temporal(TemporalType.DATE)
    private Date hsbabhytGiatri2;
    @Column(name = "HSBABHYT_GIATRI3")
    @Temporal(TemporalType.DATE)
    private Date hsbabhytGiatri3;
    @Column(name = "HSBABHYT_TUYEN")
    private Short hsbabhytTuyen;
    @Column(name = "HSBABHYT_NGAYGHITHE")
    @Temporal(TemporalType.DATE)
    private Date hsbabhytNgayghithe;
    @Column(name = "HSBABHYT_NGAYTRATHE")
    @Temporal(TemporalType.DATE)
    private Date hsbabhytNgaytrathe;
    @Column(name = "HSBABHYT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbabhytNgaygiocn;
    @Column(name = "HSBABHYT_NHANVIEN_MA")
    private String hsbabhytNhanvienMa;
    @Column(name = "HSBABHYT_SOTHETE")
    private String hsbabhytSothete;
    @Column(name = "HSBABHYT_SOTHENGHEO")
    private String hsbabhytSothengheo;
    @Column(name = "HSBABHYT_KHAISINH")
    private String hsbabhytKhaisinh;
    @Column(name = "HSBABHYT_CHUNGSINH")
    private String hsbabhytChungsinh;
    @Column(name = "HSBABHYT_CNXA")
    private String hsbabhytCnxa;
    @Column(name = "HSBABHYT_GIAMHO")
    private String hsbabhytGiamho;
    @Column(name = "HSBABHYT_XNMIENTN")
    private Boolean hsbabhytXnmientn;
    
    @Column(name = "HSBABHYT_TTVANCHUYEN")
    private Boolean hsbabhytTTVanChuyen;
    

    @Column(name = "HSBABHYT_CO_GIAY_CHUYEN_VIEN")
    private Boolean hsbabhytCoGiayChuyenVien;

            
    @Column(name = "HSBABHYT_MOC3")
    @Temporal(TemporalType.DATE)
    private Date hsbabhytMoc3;
    
    @Column(name = "HSBABHYT_TYLEBH")
    private Short hsbabhytTylebh;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSBABHYT_MAKCB", referencedColumnName = "DMBENHVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVien hsbabhytMakcb;
    @JoinColumn(name = "HSBABHYT_KHOIBH", referencedColumnName = "DTDMKHOIBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmKhoiBhyt hsbabhytKhoibh;

    
    @JoinColumn(name = "HSBABHYT_TINHBH", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh hsbabhytTinhbh;


    public HsbaBhyt() {
    }

    public HsbaBhyt(Integer hsbabhytMa) {
        this.hsbabhytMa = hsbabhytMa;
    }

    public Integer getHsbabhytMa() {
        return hsbabhytMa;
    }

    public void setHsbabhytMa(Integer hsbabhytMa) {
        this.hsbabhytMa = hsbabhytMa;
    }

    public String getHsbabhytHuyenbh() {
        return hsbabhytHuyenbh;
    }

    public void setHsbabhytHuyenbh(String hsbabhytHuyenbh) {
        this.hsbabhytHuyenbh = hsbabhytHuyenbh;
    }

    public String getHsbabhytCoquanbh() {
        return hsbabhytCoquanbh;
    }

    public void setHsbabhytCoquanbh(String hsbabhytCoquanbh) {
        this.hsbabhytCoquanbh = hsbabhytCoquanbh;
    }

    public String getHsbabhytSothebh() {
        return hsbabhytSothebh;
    }

    public void setHsbabhytSothebh(String hsbabhytSothebh) {
        this.hsbabhytSothebh = hsbabhytSothebh;
    }

    public String getHsbabhytNambhyt() {
        return hsbabhytNambhyt;
    }

    public void setHsbabhytNambhyt(String hsbabhytNambhyt) {
        this.hsbabhytNambhyt = hsbabhytNambhyt;
    }

    public Boolean getHsbabhytNamtg() {
        return hsbabhytNamtg;
    }

    public void setHsbabhytNamtg(Boolean hsbabhytNamtg) {
        this.hsbabhytNamtg = hsbabhytNamtg;
    }

    public Date getHsbabhytGiatri0() {
        return hsbabhytGiatri0;
    }

    public void setHsbabhytGiatri0(Date hsbabhytGiatri0) {
        this.hsbabhytGiatri0 = hsbabhytGiatri0;
    }

    public Date getHsbabhytGiatri1() {
        return hsbabhytGiatri1;
    }

    public void setHsbabhytGiatri1(Date hsbabhytGiatri1) {
        this.hsbabhytGiatri1 = hsbabhytGiatri1;
    }

    public Date getHsbabhytGiatri2() {
        return hsbabhytGiatri2;
    }

    public void setHsbabhytGiatri2(Date hsbabhytGiatri2) {
        this.hsbabhytGiatri2 = hsbabhytGiatri2;
    }

    public Date getHsbabhytGiatri3() {
        return hsbabhytGiatri3;
    }

    public void setHsbabhytGiatri3(Date hsbabhytGiatri3) {
        this.hsbabhytGiatri3 = hsbabhytGiatri3;
    }

    public Short getHsbabhytTuyen() {
        return hsbabhytTuyen;
    }

    public void setHsbabhytTuyen(Short hsbabhytTuyen) {
        this.hsbabhytTuyen = hsbabhytTuyen;
    }

    public Date getHsbabhytNgayghithe() {
        return hsbabhytNgayghithe;
    }

    public void setHsbabhytNgayghithe(Date hsbabhytNgayghithe) {
        this.hsbabhytNgayghithe = hsbabhytNgayghithe;
    }

    public Date getHsbabhytNgaytrathe() {
        return hsbabhytNgaytrathe;
    }

    public void setHsbabhytNgaytrathe(Date hsbabhytNgaytrathe) {
        this.hsbabhytNgaytrathe = hsbabhytNgaytrathe;
    }

    public Date getHsbabhytNgaygiocn() {
        return hsbabhytNgaygiocn;
    }

    public void setHsbabhytNgaygiocn(Date hsbabhytNgaygiocn) {
        this.hsbabhytNgaygiocn = hsbabhytNgaygiocn;
    }

    public String getHsbabhytNhanvienMa() {
        return hsbabhytNhanvienMa;
    }

    public void setHsbabhytNhanvienMa(String hsbabhytNhanvienMa) {
        this.hsbabhytNhanvienMa = hsbabhytNhanvienMa;
    }

    public String getHsbabhytSothete() {
        return hsbabhytSothete;
    }

    public void setHsbabhytSothete(String hsbabhytSothete) {
        this.hsbabhytSothete = hsbabhytSothete;
    }

    public String getHsbabhytSothengheo() {
        return hsbabhytSothengheo;
    }

    public void setHsbabhytSothengheo(String hsbabhytSothengheo) {
        this.hsbabhytSothengheo = hsbabhytSothengheo;
    }

    public String getHsbabhytKhaisinh() {
        return hsbabhytKhaisinh;
    }

    public void setHsbabhytKhaisinh(String hsbabhytKhaisinh) {
        this.hsbabhytKhaisinh = hsbabhytKhaisinh;
    }

    public String getHsbabhytChungsinh() {
        return hsbabhytChungsinh;
    }

    public void setHsbabhytChungsinh(String hsbabhytChungsinh) {
        this.hsbabhytChungsinh = hsbabhytChungsinh;
    }

    public String getHsbabhytCnxa() {
        return hsbabhytCnxa;
    }

    public void setHsbabhytCnxa(String hsbabhytCnxa) {
        this.hsbabhytCnxa = hsbabhytCnxa;
    }

    public String getHsbabhytGiamho() {
        return hsbabhytGiamho;
    }

    public void setHsbabhytGiamho(String hsbabhytGiamho) {
        this.hsbabhytGiamho = hsbabhytGiamho;
    }

    public Boolean getHsbabhytXnmientn() {
        return hsbabhytXnmientn;
    }

    public void setHsbabhytXnmientn(Boolean hsbabhytXnmientn) {
        this.hsbabhytXnmientn = hsbabhytXnmientn;
    }

    public Short getHsbabhytTylebh() {
        return hsbabhytTylebh;
    }

    public void setHsbabhytTylebh(Short hsbabhytTylebh) {
        this.hsbabhytTylebh = hsbabhytTylebh;
    }

    public Hsba getHsbaSovaovien(boolean create) {
if(create && getHsbaSovaovien() == null) setHsbaSovaovien(new Hsba());
return  getHsbaSovaovien();
}
    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmBenhVien getHsbabhytMakcb(boolean create) {
if(create && getHsbabhytMakcb() == null) setHsbabhytMakcb(new DmBenhVien());
return  getHsbabhytMakcb();
}
    public DmBenhVien getHsbabhytMakcb() {
        return hsbabhytMakcb;
    }

    public void setHsbabhytMakcb(DmBenhVien hsbabhytMakcb) {
        this.hsbabhytMakcb = hsbabhytMakcb;
    }

    public DtDmKhoiBhyt getHsbabhytKhoibh(boolean create) {
if(create && getHsbabhytKhoibh() == null) setHsbabhytKhoibh(new DtDmKhoiBhyt());
return  getHsbabhytKhoibh();
}
    public DtDmKhoiBhyt getHsbabhytKhoibh() {
        return hsbabhytKhoibh;
    }

    public void setHsbabhytKhoibh(DtDmKhoiBhyt hsbabhytKhoibh) {
        this.hsbabhytKhoibh = hsbabhytKhoibh;
    }

    public DmTinh getHsbabhytTinhbh(boolean create) {
if(create && getHsbabhytTinhbh() == null) setHsbabhytTinhbh(new DmTinh());
return  getHsbabhytTinhbh();
}
    public DmTinh getHsbabhytTinhbh() {
        return hsbabhytTinhbh;
    }

    public void setHsbabhytTinhbh(DmTinh hsbabhytTinhbh) {
        this.hsbabhytTinhbh = hsbabhytTinhbh;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHsbabhytMa() != null ? getHsbabhytMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaBhyt)) {
            return false;
        }
        HsbaBhyt other = (HsbaBhyt) object;
        if ((this.getHsbabhytMa() == null && other.getHsbabhytMa() != null) || (this.getHsbabhytMa() != null && !this.hsbabhytMa.equals(other.hsbabhytMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaBhyt[hsbabhytMa=" + getHsbabhytMa() + "]";
    }

    public Boolean getHsbabhytTTVanChuyen() {
        return hsbabhytTTVanChuyen;
    }

    public void setHsbabhytTTVanChuyen(Boolean hsbabhytTTVanChuyen) {
        this.hsbabhytTTVanChuyen = hsbabhytTTVanChuyen;
    }

    /**
     * @return the hsbabhytMoc1
     */
    public Date getHsbabhytMoc1() {
        return hsbabhytMoc1;
    }

    /**
     * @param hsbabhytMoc1 the hsbabhytMoc1 to set
     */
    public void setHsbabhytMoc1(Date hsbabhytMoc1) {
        this.hsbabhytMoc1 = hsbabhytMoc1;
    }

    /**
     * @return the hsbabhytMoc2
     */
    public Date getHsbabhytMoc2() {
        return hsbabhytMoc2;
    }

    /**
     * @param hsbabhytMoc2 the hsbabhytMoc2 to set
     */
    public void setHsbabhytMoc2(Date hsbabhytMoc2) {
        this.hsbabhytMoc2 = hsbabhytMoc2;
    }

    /**
     * @return the hsbabhytMoc3
     */
    public Date getHsbabhytMoc3() {
        return hsbabhytMoc3;
    }

    /**
     * @param hsbabhytMoc3 the hsbabhytMoc3 to set
     */
    public void setHsbabhytMoc3(Date hsbabhytMoc3) {
        this.hsbabhytMoc3 = hsbabhytMoc3;
    }

    /**
     * @return the tiepdonCoGiayChuyenVien
     */
    public Boolean getHsbabhytCoGiayChuyenVien() {
        return hsbabhytCoGiayChuyenVien;
    }

    /**
     * @param tiepdonCoGiayChuyenVien the tiepdonCoGiayChuyenVien to set
     */
    public void setHsbabhytCoGiayChuyenVien(Boolean tiepdonCoGiayChuyenVien) {
        this.hsbabhytCoGiayChuyenVien = tiepdonCoGiayChuyenVien;
    }

   


}


