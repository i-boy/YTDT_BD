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
@Table(name = "HOAN_THU_KHAM")
@NamedQueries({})
public class HoanThuKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "HOANTHUKHAM_MA", nullable = false)
    private String hoanthukhamMa;
    @Column(name = "HOANTHUKHAM_MAPHIEU")
    private String hoanthukhamMaphieu;
    @Column(name = "HOANTHUKHAM_SOTIEN")
    private Double hoanthukhamSotien;
    @Column(name = "HOANTHUKHAM_THTOAN")
    private Double hoanthukhamThtoan;
    @Column(name = "HOANTHUKHAM_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date hoanthukhamNgaytt;
    @Column(name = "HOANTHUKHAM_CUM")
    private Short hoanthukhamCum;
    @Column(name = "HOANTHUKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoanthukhamNgaygiocn;
    @Column(name = "HOANTHUKHAM_KYHIEU")
    private String hoanthukhamKyhieu;
    @Column(name = "HOANTHUKHAM_QUYEN")
    private String hoanthukhamQuyen;
    @Column(name = "HOANTHUKHAM_TINHTRANG")
    private String hoanthukhamTinhtrang;
    @Column(name = "HOANTHUKHAM_PHANBIET")
    private String hoanthukhamPhanbiet;
    @Column(name = "HOANTHUKHAM_NGAYGIOBO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoanthukhamNgaygiobo;
    @Column(name = "HOANTHUKHAM_NGUOIBO")
    private String hoanthukhamNguoibo;
    @Column(name = "HOANTHUKHAM_LYDO")
    private String hoanthukhamLydo;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
    @JoinColumn(name = "HOANTHUKHAM_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hoanthukhamKhoa;
    @JoinColumn(name = "HOANTHUKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanthukhamNhanviencn;
    @JoinColumn(name = "HOANTHUKHAM_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanthukhamThungan;

    public HoanThuKham() {
    }

    public HoanThuKham(String hoanthukhamMa) {
        this.hoanthukhamMa = hoanthukhamMa;
    }

    public String getHoanthukhamMa() {
        return hoanthukhamMa;
    }

    public void setHoanthukhamMa(String hoanthukhamMa) {
        this.hoanthukhamMa = hoanthukhamMa;
    }

    public String getHoanthukhamMaphieu() {
        return hoanthukhamMaphieu;
    }

    public void setHoanthukhamMaphieu(String hoanthukhamMaphieu) {
        this.hoanthukhamMaphieu = hoanthukhamMaphieu;
    }

    public Double getHoanthukhamSotien() {
        return hoanthukhamSotien;
    }

    public void setHoanthukhamSotien(Double hoanthukhamSotien) {
        this.hoanthukhamSotien = hoanthukhamSotien;
    }

    public Double getHoanthukhamThtoan() {
        return hoanthukhamThtoan;
    }

    public void setHoanthukhamThtoan(Double hoanthukhamThtoan) {
        this.hoanthukhamThtoan = hoanthukhamThtoan;
    }

    public Date getHoanthukhamNgaytt() {
        return hoanthukhamNgaytt;
    }

    public void setHoanthukhamNgaytt(Date hoanthukhamNgaytt) {
        this.hoanthukhamNgaytt = hoanthukhamNgaytt;
    }

    public Short getHoanthukhamCum() {
        return hoanthukhamCum;
    }

    public void setHoanthukhamCum(Short hoanthukhamCum) {
        this.hoanthukhamCum = hoanthukhamCum;
    }

    public Date getHoanthukhamNgaygiocn() {
        return hoanthukhamNgaygiocn;
    }

    public void setHoanthukhamNgaygiocn(Date hoanthukhamNgaygiocn) {
        this.hoanthukhamNgaygiocn = hoanthukhamNgaygiocn;
    }

    public String getHoanthukhamKyhieu() {
        return hoanthukhamKyhieu;
    }

    public void setHoanthukhamKyhieu(String hoanthukhamKyhieu) {
        this.hoanthukhamKyhieu = hoanthukhamKyhieu;
    }

    public String getHoanthukhamQuyen() {
        return hoanthukhamQuyen;
    }

    public void setHoanthukhamQuyen(String hoanthukhamQuyen) {
        this.hoanthukhamQuyen = hoanthukhamQuyen;
    }

    public String getHoanthukhamTinhtrang() {
        return hoanthukhamTinhtrang;
    }

    public void setHoanthukhamTinhtrang(String hoanthukhamTinhtrang) {
        this.hoanthukhamTinhtrang = hoanthukhamTinhtrang;
    }

    public String getHoanthukhamPhanbiet() {
        return hoanthukhamPhanbiet;
    }

    public void setHoanthukhamPhanbiet(String hoanthukhamPhanbiet) {
        this.hoanthukhamPhanbiet = hoanthukhamPhanbiet;
    }

    public Date getHoanthukhamNgaygiobo() {
        return hoanthukhamNgaygiobo;
    }

    public void setHoanthukhamNgaygiobo(Date hoanthukhamNgaygiobo) {
        this.hoanthukhamNgaygiobo = hoanthukhamNgaygiobo;
    }

    public String getHoanthukhamNguoibo() {
        return hoanthukhamNguoibo;
    }

    public void setHoanthukhamNguoibo(String hoanthukhamNguoibo) {
        this.hoanthukhamNguoibo = hoanthukhamNguoibo;
    }

    public String getHoanthukhamLydo() {
        return hoanthukhamLydo;
    }

    public void setHoanthukhamLydo(String hoanthukhamLydo) {
        this.hoanthukhamLydo = hoanthukhamLydo;
    }

    public TiepDon getTiepdonMa(boolean create) {
if(create && tiepdonMa == null) tiepdonMa = new TiepDon();
return tiepdonMa;
}
    public TiepDon getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(TiepDon tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public DmKhoa getHoanthukhamKhoa(boolean create) {
if(create && hoanthukhamKhoa == null) hoanthukhamKhoa = new DmKhoa();
return hoanthukhamKhoa;
}
    public DmKhoa getHoanthukhamKhoa() {
        return hoanthukhamKhoa;
    }

    public void setHoanthukhamKhoa(DmKhoa hoanthukhamKhoa) {
        this.hoanthukhamKhoa = hoanthukhamKhoa;
    }

    public DtDmNhanVien getHoanthukhamNhanviencn(boolean create) {
if(create && hoanthukhamNhanviencn == null) hoanthukhamNhanviencn = new DtDmNhanVien();
return hoanthukhamNhanviencn;
}
    public DtDmNhanVien getHoanthukhamNhanviencn() {
        return hoanthukhamNhanviencn;
    }

    public void setHoanthukhamNhanviencn(DtDmNhanVien hoanthukhamNhanviencn) {
        this.hoanthukhamNhanviencn = hoanthukhamNhanviencn;
    }

    public DtDmNhanVien getHoanthukhamThungan(boolean create) {
if(create && hoanthukhamThungan == null) hoanthukhamThungan = new DtDmNhanVien();
return hoanthukhamThungan;
}
    public DtDmNhanVien getHoanthukhamThungan() {
        return hoanthukhamThungan;
    }

    public void setHoanthukhamThungan(DtDmNhanVien hoanthukhamThungan) {
        this.hoanthukhamThungan = hoanthukhamThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hoanthukhamMa != null ? hoanthukhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoanThuKham)) {
            return false;
        }
        HoanThuKham other = (HoanThuKham) object;
        if ((this.hoanthukhamMa == null && other.hoanthukhamMa != null) || (this.hoanthukhamMa != null && !this.hoanthukhamMa.equals(other.hoanthukhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HoanThuKham[hoanthukhamMa=" + hoanthukhamMa + "]";
    }
}


