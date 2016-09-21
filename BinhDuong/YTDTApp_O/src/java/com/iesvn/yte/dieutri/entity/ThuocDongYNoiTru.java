/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.iesvn.yte.entity.DmKhoa;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "THUOC_DONG_Y_NOI_TRU")
@NamedQueries({
    @NamedQuery(name = "ThuocDongYNoiTru.findAll", query = "SELECT t FROM ThuocDongYNoiTru t")})
public class ThuocDongYNoiTru implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THUOC_DONG_Y_NOI_TRU")
    @SequenceGenerator(name = "THUOC_DONG_Y_NOI_TRU", sequenceName = "THUOC_DONG_Y_NOI_TRU_THUOCDONG", allocationSize = 1)
    @Column(name = "THUOCDONGY_MASO")
    private Integer thuocdongyMaso;
    @Column(name = "THUOCDONGY_LOAI")
    private String thuocdongyLoai;
    @Column(name = "THUOCDONGY_SOLUONG")
    private double thuocdongySoluong;
    @Column(name = "THUOCDONGY_DONGIA")
    private double thuocdongyDongia;
    @Column(name = "THUOCDONGY_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocdongyNgaygiocn;
    @Column(name = "THUOCDONGY_TYLEBH")
    private Double thuocdongyTylebh;
    @Column(name = "THUOCDONGY_TIENBNTRA")
    private Double thuocdongyTienbntra;
    @Column(name = "THUOCDONGY_DONGIATT", nullable = false)
    private int thuocdongyDongiatt;
    @Column(name = "THUOCDONGY_THANHTIEN", nullable = false)
    private int thuocdongyThanhtien;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "THUOCDONGY_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa thuocdongyKhoa;
    @JoinColumn(name = "DMBAITHUOC_MASO", referencedColumnName = "DMBAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBaiThuoc dmbaithuocMaso;
    @JoinColumn(name = "THUOCDONGY_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocdongyNhanviencn;

    public ThuocDongYNoiTru() {
    }

    public ThuocDongYNoiTru(Integer thuocdongyMaso) {
        this.thuocdongyMaso = thuocdongyMaso;
    }

    public ThuocDongYNoiTru(Integer thuocdongyMaso, double thuocdongySoluong, double thuocdongyDongia) {
        this.thuocdongyMaso = thuocdongyMaso;
        this.thuocdongySoluong = thuocdongySoluong;
        this.thuocdongyDongia = thuocdongyDongia;
    }

    public Integer getThuocdongyMaso() {
        return thuocdongyMaso;
    }

    public void setThuocdongyMaso(Integer thuocdongyMaso) {
        this.thuocdongyMaso = thuocdongyMaso;
    }

    public String getThuocdongyLoai() {
        return thuocdongyLoai;
    }

    public void setThuocdongyLoai(String thuocdongyLoai) {
        this.thuocdongyLoai = thuocdongyLoai;
    }

    public double getThuocdongySoluong() {
        return thuocdongySoluong;
    }

    public void setThuocdongySoluong(double thuocdongySoluong) {
        this.thuocdongySoluong = thuocdongySoluong;
    }

    public double getThuocdongyDongia() {
        return thuocdongyDongia;
    }

    public void setThuocdongyDongia(double thuocdongyDongia) {
        this.thuocdongyDongia = thuocdongyDongia;
    }

    public Date getThuocdongyNgaygiocn() {
        return thuocdongyNgaygiocn;
    }

    public void setThuocdongyNgaygiocn(Date thuocdongyNgaygiocn) {
        this.thuocdongyNgaygiocn = thuocdongyNgaygiocn;
    }
    
    public Double getThuocdongyTylebh() {
        return thuocdongyTylebh;
    }

    public void setThuocdongyTylebh(Double thuocdongyTylebh) {
        this.thuocdongyTylebh = thuocdongyTylebh;
    }

    public Double getThuocdongyTienbntra() {
        return thuocdongyTienbntra;
    }

    public void setThuocdongyTienbntra(Double thuocdongyTienbntra) {
        this.thuocdongyTienbntra = thuocdongyTienbntra;
    }
    
    public int getThuocdongyDongiatt() {
        return thuocdongyDongiatt;
    }

    public void setThuocdongyDongiatt(int thuocdongyDongiatt) {
        this.thuocdongyDongiatt = thuocdongyDongiatt;
    }

    public int getThuocdongyThanhtien() {
        return thuocdongyThanhtien;
    }

    public void setThuocdongyThanhtien(int thuocdongyThanhtien) {
        this.thuocdongyThanhtien = thuocdongyThanhtien;
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

    public DmKhoa getThuocdongyKhoa(boolean create) {
        if (create && thuocdongyKhoa == null) {
            thuocdongyKhoa = new DmKhoa();
        }
        return thuocdongyKhoa;
    }

    public DmKhoa getThuocdongyKhoa() {
        return thuocdongyKhoa;
    }

    public void setThuocdongyKhoa(DmKhoa thuocdongyKhoa) {
        this.thuocdongyKhoa = thuocdongyKhoa;
    }

    public DmBaiThuoc getDmbaithuocMaso() {
        return dmbaithuocMaso;
    }

    public DmBaiThuoc getDmbaithuocMaso(boolean created) {
        if (created && dmbaithuocMaso == null) {
            dmbaithuocMaso = new DmBaiThuoc();
        }
        return dmbaithuocMaso;
    }

    public void setDmbaithuocMaso(DmBaiThuoc dmbaithuocMaso) {
        this.dmbaithuocMaso = dmbaithuocMaso;
    }

    public DtDmNhanVien getThuocdongyNhanviencn(boolean create) {
        if (create && thuocdongyNhanviencn == null) {
            thuocdongyNhanviencn = new DtDmNhanVien();
        }
        return thuocdongyNhanviencn;
    }

    public DtDmNhanVien getThuocdongyNhanviencn() {
        return thuocdongyNhanviencn;
    }

    public void setThuocdongyNhanviencn(DtDmNhanVien thuocdongyNhanviencn) {
        this.thuocdongyNhanviencn = thuocdongyNhanviencn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thuocdongyMaso != null ? thuocdongyMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThuocDongYNoiTru)) {
            return false;
        }
        ThuocDongYNoiTru other = (ThuocDongYNoiTru) object;
        if ((this.thuocdongyMaso == null && other.thuocdongyMaso != null) || (this.thuocdongyMaso != null && !this.thuocdongyMaso.equals(other.thuocdongyMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru[thuocdongyMaso=" + thuocdongyMaso + "]";
    }

}
