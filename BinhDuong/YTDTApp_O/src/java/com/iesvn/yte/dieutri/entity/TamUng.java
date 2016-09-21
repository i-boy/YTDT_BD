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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TAM_UNG")
@NamedQueries({})
public class TamUng implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "TAMUNG_MA", nullable = false)
    private String tamungMa;
    @Column(name = "TAMUNG_SOTIEN")
    private Double tamungSotien;
    @Column(name = "TAMUNG_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tamungNgaygio;
    @Column(name = "TAMUNG_KYHIEU")
    private String tamungKyhieu;
    @Column(name = "TAMUNG_QUYEN")
    private String tamungQuyen;
    @Column(name = "TAMUNG_BIENLAI")
    private String tamungBienlai;
    @Column(name = "TAMUNG_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tamungNgaygiocn;
    @Column(name = "TAMUNG_MAKIEMTRA")
    private String tamungMakiemtra;
    @Column(name = "TAMUNG_LYDO")
    private String tamungLydo;
    @Column(name = "TAMUNG_IN")
    private String tamungIn;
    @Column(name = "TAMUNG_LYDOINLAI")
    private String tamungLydoinlai;
    @Column(name = "TAMUNG_STATUS")
    private String tamungStatus;
//    @OneToMany(mappedBy = "hoanungMaphieu")
//    private Collection<HoanUng> hoanUngCollection;
//    @OneToMany(mappedBy = "hoanungMaphieu1")
//    private Collection<HoanUng> hoanUngCollection1;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "TAMUNG_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa tamungKhoa;
    @JoinColumn(name = "TAMUNG_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tamungNhanviencn;
    @JoinColumn(name = "TAMUNG_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tamungThungan;
    @JoinColumn(name = "TAMUNG_CUM", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum tamungCum;

    public TamUng() {
    }

    public TamUng(String tamungMa) {
        this.tamungMa = tamungMa;
    }

    public String getTamungMa() {
        return tamungMa;
    }

    public void setTamungMa(String tamungMa) {
        this.tamungMa = tamungMa;
    }

    public Double getTamungSotien() {
        return tamungSotien;
    }

    public void setTamungSotien(Double tamungSotien) {
        this.tamungSotien = tamungSotien;
    }

    public Date getTamungNgaygio() {
        return tamungNgaygio;
    }

    public void setTamungNgaygio(Date tamungNgaygio) {
        this.tamungNgaygio = tamungNgaygio;
    }

    public String getTamungKyhieu() {
        return tamungKyhieu;
    }

    public void setTamungKyhieu(String tamungKyhieu) {
        this.tamungKyhieu = tamungKyhieu;
    }

    public String getTamungQuyen() {
        return tamungQuyen;
    }

    public void setTamungQuyen(String tamungQuyen) {
        this.tamungQuyen = tamungQuyen;
    }

    public String getTamungBienlai() {
        return tamungBienlai;
    }

    public void setTamungBienlai(String tamungBienlai) {
        this.tamungBienlai = tamungBienlai;
    }

    public Date getTamungNgaygiocn() {
        return tamungNgaygiocn;
    }

    public void setTamungNgaygiocn(Date tamungNgaygiocn) {
        this.tamungNgaygiocn = tamungNgaygiocn;
    }

    public String getTamungMakiemtra() {
        return tamungMakiemtra;
    }

    public void setTamungMakiemtra(String tamungMakiemtra) {
        this.tamungMakiemtra = tamungMakiemtra;
    }

    public String getTamungLydo() {
        return tamungLydo;
    }

    public void setTamungLydo(String tamungLydo) {
        this.tamungLydo = tamungLydo;
    }

    public String getTamungIn() {
        return tamungIn;
    }

    public void setTamungIn(String tamungIn) {
        this.tamungIn = tamungIn;
    }

    public String getTamungLydoinlai() {
        return tamungLydoinlai;
    }

    public void setTamungLydoinlai(String tamungLydoinlai) {
        this.tamungLydoinlai = tamungLydoinlai;
    }

    public String getTamungStatus() {
        return tamungStatus;
    }

    public void setTamungStatus(String tamungStatus) {
        this.tamungStatus = tamungStatus;
    }

//    public Collection<HoanUng> getHoanUngCollection() {
//        return hoanUngCollection;
//    }
//
//    public void setHoanUngCollection(Collection<HoanUng> hoanUngCollection) {
//        this.hoanUngCollection = hoanUngCollection;
//    }

//    public Collection<HoanUng> getHoanUngCollection1() {
//        return hoanUngCollection1;
//    }
//
//    public void setHoanUngCollection1(Collection<HoanUng> hoanUngCollection1) {
//        this.hoanUngCollection1 = hoanUngCollection1;
//    }
    public Hsba getHsbaSovaovien(boolean create) {
        if (create && hsbaSovaovien == null) {
            hsbaSovaovien = new Hsba();
        }
        return hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmKhoa getTamungKhoa(boolean create) {
        if (create && tamungKhoa == null) {
            tamungKhoa = new DmKhoa();
        }
        return tamungKhoa;
    }

    public DmKhoa getTamungKhoa() {
        return tamungKhoa;
    }

    public void setTamungKhoa(DmKhoa tamungKhoa) {
        this.tamungKhoa = tamungKhoa;
    }

    public DtDmNhanVien getTamungNhanviencn(boolean create) {
        if (create && tamungNhanviencn == null) {
            tamungNhanviencn = new DtDmNhanVien();
        }
        return tamungNhanviencn;
    }

    public DtDmNhanVien getTamungNhanviencn() {
        return tamungNhanviencn;
    }

    public void setTamungNhanviencn(DtDmNhanVien tamungNhanviencn) {
        this.tamungNhanviencn = tamungNhanviencn;
    }

    public DtDmNhanVien getTamungThungan(boolean create) {
        if (create && tamungThungan == null) {
            tamungThungan = new DtDmNhanVien();
        }
        return tamungThungan;
    }

    public DtDmNhanVien getTamungThungan() {
        return tamungThungan;
    }

    public void setTamungThungan(DtDmNhanVien tamungThungan) {
        this.tamungThungan = tamungThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tamungMa != null ? tamungMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TamUng)) {
            return false;
        }
        TamUng other = (TamUng) object;
        if ((this.tamungMa == null && other.tamungMa != null) || (this.tamungMa != null && !this.tamungMa.equals(other.tamungMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.TamUng[tamungMa=" + tamungMa + "]";
    }

    public DtDmCum getTamungCum() {
        return tamungCum;
    }

    public DtDmCum getTamungCum(boolean create) {
        if (create && tamungCum == null) {
            tamungCum = new DtDmCum();
        }
        return tamungCum;
    }

    public void setTamungCum(DtDmCum tamungCum) {
        this.tamungCum = tamungCum;
    }
}


