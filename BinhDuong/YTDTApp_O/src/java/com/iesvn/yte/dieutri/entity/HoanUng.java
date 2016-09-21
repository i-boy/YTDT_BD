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
@Table(name = "HOAN_UNG")
@NamedQueries({})
public class HoanUng implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "HOANUNG_MA", nullable = false)
    private String hoanungMa;
    @Column(name = "HOANUNG_SOTIEN")
    private Double hoanungSotien;
    @Column(name = "HOANUNG_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoanungNgaygio;
    @Column(name = "HOANUNG_CUM")
    private Short hoanungCum;
    @Column(name = "HOANUNG_KYHIEU")
    private String hoanungKyhieu;
    @Column(name = "HOANUNG_QUYEN")
    private String hoanungQuyen;
    @Column(name = "HOANUNG_BIENLAI")
    private String hoanungBienlai;
    @Column(name = "HOANUNG_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoanungNgaygiocn;
    @Column(name = "HOANUNG_MAKIEMTRA")
    private String hoanungMakiemtra;
    @Column(name = "HOANUNG_LYDO")
    private String hoanungLydo;
    @Column(name = "HOANUNG_IN")
    private String hoanungIn;
    @Column(name = "HOANUNG_LYDOINLAI")
    private String hoanungLydoinlai;
    @Column(name = "HOANUNG_STATUS")
    private String hoanungStatus;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HOANUNG_MAPHIEU", referencedColumnName = "TAMUNG_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TamUng hoanungMaphieu;
    @JoinColumn(name = "HOANUNG_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hoanungKhoa;
    @JoinColumn(name = "HOANUNG_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanungNhanviencn;
    @JoinColumn(name = "HOANUNG_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hoanungThungan;

    public HoanUng() {
    }

    public HoanUng(String hoanungMa) {
        this.hoanungMa = hoanungMa;
    }

    public String getHoanungMa() {
        return hoanungMa;
    }

    public void setHoanungMa(String hoanungMa) {
        this.hoanungMa = hoanungMa;
    }

    public Double getHoanungSotien() {
        return hoanungSotien;
    }

    public void setHoanungSotien(Double hoanungSotien) {
        this.hoanungSotien = hoanungSotien;
    }

    public Date getHoanungNgaygio() {
        return hoanungNgaygio;
    }

    public void setHoanungNgaygio(Date hoanungNgaygio) {
        this.hoanungNgaygio = hoanungNgaygio;
    }

    public Short getHoanungCum() {
        return hoanungCum;
    }

    public void setHoanungCum(Short hoanungCum) {
        this.hoanungCum = hoanungCum;
    }

    public String getHoanungKyhieu() {
        return hoanungKyhieu;
    }

    public void setHoanungKyhieu(String hoanungKyhieu) {
        this.hoanungKyhieu = hoanungKyhieu;
    }

    public String getHoanungQuyen() {
        return hoanungQuyen;
    }

    public void setHoanungQuyen(String hoanungQuyen) {
        this.hoanungQuyen = hoanungQuyen;
    }

    public String getHoanungBienlai() {
        return hoanungBienlai;
    }

    public void setHoanungBienlai(String hoanungBienlai) {
        this.hoanungBienlai = hoanungBienlai;
    }

    public Date getHoanungNgaygiocn() {
        return hoanungNgaygiocn;
    }

    public void setHoanungNgaygiocn(Date hoanungNgaygiocn) {
        this.hoanungNgaygiocn = hoanungNgaygiocn;
    }

    public String getHoanungMakiemtra() {
        return hoanungMakiemtra;
    }

    public void setHoanungMakiemtra(String hoanungMakiemtra) {
        this.hoanungMakiemtra = hoanungMakiemtra;
    }

    public String getHoanungLydo() {
        return hoanungLydo;
    }

    public void setHoanungLydo(String hoanungLydo) {
        this.hoanungLydo = hoanungLydo;
    }

    public String getHoanungIn() {
        return hoanungIn;
    }

    public void setHoanungIn(String hoanungIn) {
        this.hoanungIn = hoanungIn;
    }

    public String getHoanungLydoinlai() {
        return hoanungLydoinlai;
    }

    public void setHoanungLydoinlai(String hoanungLydoinlai) {
        this.hoanungLydoinlai = hoanungLydoinlai;
    }

    public String getHoanungStatus() {
        return hoanungStatus;
    }

    public void setHoanungStatus(String hoanungStatus) {
        this.hoanungStatus = hoanungStatus;
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

    public TamUng getHoanungMaphieu(boolean create) {
if(create && hoanungMaphieu == null) hoanungMaphieu = new TamUng();
return hoanungMaphieu;
}
    public TamUng getHoanungMaphieu() {
        return hoanungMaphieu;
    }

    public void setHoanungMaphieu(TamUng hoanungMaphieu) {
        this.hoanungMaphieu = hoanungMaphieu;
    }

    public DmKhoa getHoanungKhoa(boolean create) {
if(create && hoanungKhoa == null) hoanungKhoa = new DmKhoa();
return hoanungKhoa;
}
    public DmKhoa getHoanungKhoa() {
        return hoanungKhoa;
    }

    public void setHoanungKhoa(DmKhoa hoanungKhoa) {
        this.hoanungKhoa = hoanungKhoa;
    }

    public DtDmNhanVien getHoanungNhanviencn(boolean create) {
if(create && hoanungNhanviencn == null) hoanungNhanviencn = new DtDmNhanVien();
return hoanungNhanviencn;
}
    public DtDmNhanVien getHoanungNhanviencn() {
        return hoanungNhanviencn;
    }

    public void setHoanungNhanviencn(DtDmNhanVien hoanungNhanviencn) {
        this.hoanungNhanviencn = hoanungNhanviencn;
    }

    public DtDmNhanVien getHoanungThungan(boolean create) {
if(create && hoanungThungan == null) hoanungThungan = new DtDmNhanVien();
return hoanungThungan;
}
    public DtDmNhanVien getHoanungThungan() {
        return hoanungThungan;
    }

    public void setHoanungThungan(DtDmNhanVien hoanungThungan) {
        this.hoanungThungan = hoanungThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hoanungMa != null ? hoanungMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoanUng)) {
            return false;
        }
        HoanUng other = (HoanUng) object;
        if ((this.hoanungMa == null && other.hoanungMa != null) || (this.hoanungMa != null && !this.hoanungMa.equals(other.hoanungMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HoanUng[hoanungMa=" + hoanungMa + "]";
    }
}


