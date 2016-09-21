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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HOAN_THU")
@NamedQueries({})
public class HoanThu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "HOANTHU_MA", nullable = false)
    private String hoanthuMa;
    @Column(name = "HOANTHU_SOTIEN")
    private Double hoanthuSotien;
    @Column(name = "HOANTHU_THTOAN")
    private Double hoanthuThtoan;
    @Column(name = "HOANTHU_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date hoanthuNgaytt;
    @Column(name = "HOANTHU_CUM")
    private Short hoanthuCum;
    @Column(name = "HOANTHU_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoanthuNgaygiocn;
    @Column(name = "HOANTHU_KYHIEU")
    private String hoanthuKyhieu;
    @Column(name = "HOANTHU_QUYEN")
    private String hoanthuQuyen;
    @Column(name = "HOANTHU_BIENLAI")
    private String hoanthuBienlai;
    @Column(name = "HOANTHU_TINHTRANG")
    private String hoanthuTinhtrang;
    @Column(name = "HOANTHU_PHANBIET")
    private String hoanthuPhanbiet;
    @Column(name = "HOANTHU_NGAYGIOBO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoanthuNgaygiobo;
    @Column(name = "HOANTHU_NGUOIBO")
    private String hoanthuNguoibo;
    @Column(name = "HOANTHU_LYDO")
    private String hoanthuLydo;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HOANTHU_MAPHIEU", referencedColumnName = "HSTHTOAN_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsThtoan hoanthuMaphieu;
    @JoinColumn(name = "HOANTHU_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hoanthuKhoa;
    @JoinColumn(name = "HOANTHU_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanthuNhanviencn;
    @JoinColumn(name = "HOANTHU_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanthuThungan;

    public HoanThu() {
    }

    public HoanThu(String hoanthuMa) {
        this.hoanthuMa = hoanthuMa;
    }

    public String getHoanthuMa() {
        return hoanthuMa;
    }

    public void setHoanthuMa(String hoanthuMa) {
        this.hoanthuMa = hoanthuMa;
    }

    public Double getHoanthuSotien() {
        return hoanthuSotien;
    }

    public void setHoanthuSotien(Double hoanthuSotien) {
        this.hoanthuSotien = hoanthuSotien;
    }

    public Double getHoanthuThtoan() {
        return hoanthuThtoan;
    }

    public void setHoanthuThtoan(Double hoanthuThtoan) {
        this.hoanthuThtoan = hoanthuThtoan;
    }

    public Date getHoanthuNgaytt() {
        return hoanthuNgaytt;
    }

    public void setHoanthuNgaytt(Date hoanthuNgaytt) {
        this.hoanthuNgaytt = hoanthuNgaytt;
    }

    public Short getHoanthuCum() {
        return hoanthuCum;
    }

    public void setHoanthuCum(Short hoanthuCum) {
        this.hoanthuCum = hoanthuCum;
    }

    public Date getHoanthuNgaygiocn() {
        return hoanthuNgaygiocn;
    }

    public void setHoanthuNgaygiocn(Date hoanthuNgaygiocn) {
        this.hoanthuNgaygiocn = hoanthuNgaygiocn;
    }

    public String getHoanthuKyhieu() {
        return hoanthuKyhieu;
    }

    public void setHoanthuKyhieu(String hoanthuKyhieu) {
        this.hoanthuKyhieu = hoanthuKyhieu;
    }

    public String getHoanthuQuyen() {
        return hoanthuQuyen;
    }

    public void setHoanthuQuyen(String hoanthuQuyen) {
        this.hoanthuQuyen = hoanthuQuyen;
    }

    public String getHoanthuBienlai() {
        return hoanthuBienlai;
    }

    public void setHoanthuBienlai(String hoanthuBienlai) {
        this.hoanthuBienlai = hoanthuBienlai;
    }

    public String getHoanthuTinhtrang() {
        return hoanthuTinhtrang;
    }

    public void setHoanthuTinhtrang(String hoanthuTinhtrang) {
        this.hoanthuTinhtrang = hoanthuTinhtrang;
    }

    public String getHoanthuPhanbiet() {
        return hoanthuPhanbiet;
    }

    public void setHoanthuPhanbiet(String hoanthuPhanbiet) {
        this.hoanthuPhanbiet = hoanthuPhanbiet;
    }

    public Date getHoanthuNgaygiobo() {
        return hoanthuNgaygiobo;
    }

    public void setHoanthuNgaygiobo(Date hoanthuNgaygiobo) {
        this.hoanthuNgaygiobo = hoanthuNgaygiobo;
    }

    public String getHoanthuNguoibo() {
        return hoanthuNguoibo;
    }

    public void setHoanthuNguoibo(String hoanthuNguoibo) {
        this.hoanthuNguoibo = hoanthuNguoibo;
    }

    public String getHoanthuLydo() {
        return hoanthuLydo;
    }

    public void setHoanthuLydo(String hoanthuLydo) {
        this.hoanthuLydo = hoanthuLydo;
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

    public HsThtoan getHoanthuMaphieu(boolean create) {
if(create && hoanthuMaphieu == null) hoanthuMaphieu = new HsThtoan();
return hoanthuMaphieu;
}
    public HsThtoan getHoanthuMaphieu() {
        return hoanthuMaphieu;
    }

    public void setHoanthuMaphieu(HsThtoan hoanthuMaphieu) {
        this.hoanthuMaphieu = hoanthuMaphieu;
    }

    public DmKhoa getHoanthuKhoa(boolean create) {
if(create && hoanthuKhoa == null) hoanthuKhoa = new DmKhoa();
return hoanthuKhoa;
}
    public DmKhoa getHoanthuKhoa() {
        return hoanthuKhoa;
    }

    public void setHoanthuKhoa(DmKhoa hoanthuKhoa) {
        this.hoanthuKhoa = hoanthuKhoa;
    }

    public DtDmNhanVien getHoanthuNhanviencn(boolean create) {
if(create && hoanthuNhanviencn == null) hoanthuNhanviencn = new DtDmNhanVien();
return hoanthuNhanviencn;
}
    public DtDmNhanVien getHoanthuNhanviencn() {
        return hoanthuNhanviencn;
    }

    public void setHoanthuNhanviencn(DtDmNhanVien hoanthuNhanviencn) {
        this.hoanthuNhanviencn = hoanthuNhanviencn;
    }

    public DtDmNhanVien getHoanthuThungan(boolean create) {
if(create && hoanthuThungan == null) hoanthuThungan = new DtDmNhanVien();
return hoanthuThungan;
}
    public DtDmNhanVien getHoanthuThungan() {
        return hoanthuThungan;
    }

    public void setHoanthuThungan(DtDmNhanVien hoanthuThungan) {
        this.hoanthuThungan = hoanthuThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hoanthuMa != null ? hoanthuMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoanThu)) {
            return false;
        }
        HoanThu other = (HoanThu) object;
        if ((this.hoanthuMa == null && other.hoanthuMa != null) || (this.hoanthuMa != null && !this.hoanthuMa.equals(other.hoanthuMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HoanThu[hoanthuMa=" + hoanthuMa + "]";
    }
}


