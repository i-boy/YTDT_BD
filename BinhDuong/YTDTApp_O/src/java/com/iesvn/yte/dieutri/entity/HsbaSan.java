/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmLoaiSinh;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmGioiTinh;
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
@Table(name = "HSBA_SAN")
@NamedQueries({})
public class HsbaSan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_SAN_HSBASAN_MA")
    @SequenceGenerator(name = "HSBA_SAN_HSBASAN_MA", sequenceName = "HSBA_SAN_HSBASAN_MA_SEQ", allocationSize = 1)
    @Column(name = "HSBASAN_MA", nullable = false)
    private Integer hsbasanMa;
    @Column(name = "HSBASAN_THAICHET")
    private String hsbasanThaichet;
    @Column(name = "HSBASAN_TUOITHAI")
    private Integer hsbasanTuoithai;
    @Column(name = "HSBASAN_CANNANG")
    private Integer hsbasanCannang;
    @Column(name = "HSBASAN_LANTHU")
    private Integer hsbasanLanthu;
    @Column(name = "HSBASAN_NGAYGIOSINH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbasanNgaygiosinh;
    @Column(name = "HSBASAN_BCG")
    private Boolean hsbasanBcg;
    @Column(name = "HSBASAN_NGAYTIEM")
    @Temporal(TemporalType.DATE)
    private Date hsbasanNgaytiem;
    @Column(name = "HSBASAN_NGAYGIOCHET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbasanNgaygiochet;
    @Column(name = "HSBASAN_DIENGIAITV")
    private String hsbasanDiengiaitv;
    @Column(name = "HSBASAN_KSTC")
    private Boolean hsbasanKstc;
    @Column(name = "HSBASAN_APGA")
    private Double hsbasanApga;
    @Column(name = "HSBASAN_APGA2")
    private Double hsbasanApga2;
    @Column(name = "HSBASAN_MINUTES")
    private Double hsbasanMinutes;
    @Column(name = "NHANVIEN_MA")
    private String nhanvienMa;
    @Column(name = "HSBASAN_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbasanNgaygiocn;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "DMLOAISINH_MASO", referencedColumnName = "DMLOAISINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiSinh dmloaisinhMaso;
    @JoinColumn(name = "HSBASAN_MABENH", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbasanMabenh;
    @JoinColumn(name = "HSBASAN_MATUVONG", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbasanMatuvong;
    @JoinColumn(name = "HSBASAN_GIOI", referencedColumnName = "DMGT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmGioiTinh hsbasanGioi;
    @JoinColumn(name = "HSBASAN_CHUYENKHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hsbasanChuyenkhoa;

    public HsbaSan() {
    }

    public HsbaSan(Integer hsbasanMa) {
        this.hsbasanMa = hsbasanMa;
    }

    public Integer getHsbasanMa() {
        return hsbasanMa;
    }

    public void setHsbasanMa(Integer hsbasanMa) {
        this.hsbasanMa = hsbasanMa;
    }

    public String getHsbasanThaichet() {
        return hsbasanThaichet;
    }

    public void setHsbasanThaichet(String hsbasanThaichet) {
        this.hsbasanThaichet = hsbasanThaichet;
    }

    public Integer getHsbasanTuoithai() {
        return hsbasanTuoithai;
    }

    public void setHsbasanTuoithai(Integer hsbasanTuoithai) {
        this.hsbasanTuoithai = hsbasanTuoithai;
    }

    public Integer getHsbasanCannang() {
        return hsbasanCannang;
    }

    public void setHsbasanCannang(Integer hsbasanCannang) {
        this.hsbasanCannang = hsbasanCannang;
    }

    public Integer getHsbasanLanthu() {
        return hsbasanLanthu;
    }

    public void setHsbasanLanthu(Integer hsbasanLanthu) {
        this.hsbasanLanthu = hsbasanLanthu;
    }

    public Date getHsbasanNgaygiosinh() {
        return hsbasanNgaygiosinh;
    }

    public void setHsbasanNgaygiosinh(Date hsbasanNgaygiosinh) {
        this.hsbasanNgaygiosinh = hsbasanNgaygiosinh;
    }

    public Boolean getHsbasanBcg() {
        return hsbasanBcg;
    }

    public void setHsbasanBcg(Boolean hsbasanBcg) {
        this.hsbasanBcg = hsbasanBcg;
    }

    public Date getHsbasanNgaytiem() {
        return hsbasanNgaytiem;
    }

    public void setHsbasanNgaytiem(Date hsbasanNgaytiem) {
        this.hsbasanNgaytiem = hsbasanNgaytiem;
    }

    public Date getHsbasanNgaygiochet() {
        return hsbasanNgaygiochet;
    }

    public void setHsbasanNgaygiochet(Date hsbasanNgaygiochet) {
        this.hsbasanNgaygiochet = hsbasanNgaygiochet;
    }

    public String getHsbasanDiengiaitv() {
        return hsbasanDiengiaitv;
    }

    public void setHsbasanDiengiaitv(String hsbasanDiengiaitv) {
        this.hsbasanDiengiaitv = hsbasanDiengiaitv;
    }

    public Boolean getHsbasanKstc() {
        return hsbasanKstc;
    }

    public void setHsbasanKstc(Boolean hsbasanKstc) {
        this.hsbasanKstc = hsbasanKstc;
    }

    public Double getHsbasanApga() {
        return hsbasanApga;
    }

    public void setHsbasanApga(Double hsbasanApga) {
        this.hsbasanApga = hsbasanApga;
    }

    public Double getHsbasanApga2() {
        return hsbasanApga2;
    }

    public void setHsbasanApga2(Double hsbasanApga2) {
        this.hsbasanApga2 = hsbasanApga2;
    }

    public Double getHsbasanMinutes() {
        return hsbasanMinutes;
    }

    public void setHsbasanMinutes(Double hsbasanMinutes) {
        this.hsbasanMinutes = hsbasanMinutes;
    }

    public String getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(String nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public Date getHsbasanNgaygiocn() {
        return hsbasanNgaygiocn;
    }

    public void setHsbasanNgaygiocn(Date hsbasanNgaygiocn) {
        this.hsbasanNgaygiocn = hsbasanNgaygiocn;
    }

    public HsbaChuyenMon getHsbacmMa(boolean create) {
        if (create && hsbacmMa == null) {
            hsbacmMa = new HsbaChuyenMon();
        }
        return hsbacmMa;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DmLoaiSinh getDmloaisinhMaso(boolean create) {
        if (create && dmloaisinhMaso == null) {
            dmloaisinhMaso = new DmLoaiSinh();
        }
        return dmloaisinhMaso;
    }

    public DmLoaiSinh getDmloaisinhMaso() {
        return dmloaisinhMaso;
    }

    public void setDmloaisinhMaso(DmLoaiSinh dmloaisinhMaso) {
        this.dmloaisinhMaso = dmloaisinhMaso;
    }

    public DmBenhIcd getHsbasanMabenh(boolean create) {
        if (create && hsbasanMabenh == null) {
            hsbasanMabenh = new DmBenhIcd();
        }
        return hsbasanMabenh;
    }

    public DmBenhIcd getHsbasanMabenh() {
        return hsbasanMabenh;
    }

    public void setHsbasanMabenh(DmBenhIcd hsbasanMabenh) {
        this.hsbasanMabenh = hsbasanMabenh;
    }

    public DmBenhIcd getHsbasanMatuvong(boolean create) {
        if (create && hsbasanMatuvong == null) {
            hsbasanMatuvong = new DmBenhIcd();
        }
        return hsbasanMatuvong;
    }

    public DmBenhIcd getHsbasanMatuvong() {
        return hsbasanMatuvong;
    }

    public void setHsbasanMatuvong(DmBenhIcd hsbasanMatuvong) {
        this.hsbasanMatuvong = hsbasanMatuvong;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbasanMa != null ? hsbasanMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaSan)) {
            return false;
        }
        HsbaSan other = (HsbaSan) object;
        if ((this.hsbasanMa == null && other.hsbasanMa != null) || (this.hsbasanMa != null && !this.hsbasanMa.equals(other.hsbasanMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsbaSan[hsbasanMa=" + hsbasanMa + "]";
    }

    public DmGioiTinh getHsbasanGioi() {
        return hsbasanGioi;
    }
    
    public DmGioiTinh getHsbasanGioi(boolean create) {
        if (create && hsbasanGioi == null) {
            hsbasanGioi = new DmGioiTinh();
        }
        return hsbasanGioi;
    }

    public void setHsbasanGioi(DmGioiTinh hsbasanGioi) {
        this.hsbasanGioi = hsbasanGioi;
    }

    public DmKhoa getHsbasanChuyenkhoa() {
        return hsbasanChuyenkhoa;
    }
    
    public DmKhoa getHsbasanChuyenkhoa(boolean create) {
        if (create && hsbasanChuyenkhoa == null) {
            hsbasanChuyenkhoa = new DmKhoa();
        }
        return hsbasanChuyenkhoa;
    }

    public void setHsbasanChuyenkhoa(DmKhoa hsbasanChuyenkhoa) {
        this.hsbasanChuyenkhoa = hsbasanChuyenkhoa;
    }
}


