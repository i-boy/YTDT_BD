/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmNhaSanXuat;
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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_TRA_NHA_CUNG_CAP")
@NamedQueries({})
public class CtTraNhaCungCap implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_TRA_NHA_CUNG_CAP")
    @SequenceGenerator(name = "CT_TRA_NHA_CUNG_CAP", sequenceName = "CT_TRA_NHA_CUNG_CAP_CTTRANHACU", allocationSize = 1)
    @Column(name = "CTTRANHACUNGCAP_MA", nullable = false)
    private Integer cttranhacungcapMa;
    @Column(name = "CTTRANHACUNGCAP_NAMNHAP")
    private String cttranhacungcapNamnhap;
    @Column(name = "CTTRANHACUNGCAP_NGAYHANDUNG")
    private String cttranhacungcapNgayhandung;
    @Column(name = "CTTRANHACUNGCAP_THANGHANDUNG")
    private String cttranhacungcapThanghandung;
    @Column(name = "CTTRANHACUNGCAP_NAMHANDUNG")
    private String cttranhacungcapNamhandung;
    @Column(name = "CTTRANHACUNGCAP_DONGIA")
    private Double cttranhacungcapDongia;
    @Column(name = "CTTRANHACUNGCAP_DONGIABAN")
    private Double cttranhacungcapDongiaban;
    @Column(name = "CTTRANHACUNGCAP_LO")
    private String cttranhacungcapLo;
    @Column(name = "CTTRANHACUNGCAP_MALK")
    private String cttranhacungcapMalk;
    @Column(name = "CTTRANHACUNGCAP_SODANGKY")
    private String cttranhacungcapSodangky;
    @Column(name = "CTTRANHACUNGCAP_THUTU")
    private Short cttranhacungcapThutu;
    @Column(name = "CTTRANHACUNGCAP_SOLUONG")
    private Double cttranhacungcapSoluong;
    @Column(name = "CTTRANHACUNGCAP_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cttranhacungcapNgaygiocn;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;
    @JoinColumn(name = "DMNGUONKINHPHI_MASO", referencedColumnName = "DMNGUONKINHPHI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonKinhPhi dmnguonkinhphiMaso;
    @JoinColumn(name = "DMNHASANXUAT_MASO", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat dmnhasanxuatMaso;
    @JoinColumn(name = "DMQUOCGIA_MASO", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia dmquocgiaMaso;
    @JoinColumn(name = "DMTHUOC_MASO", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc dmthuocMaso;
    
    @JoinColumn(name = "PHIEUTRANHACUNGCAP_MA", referencedColumnName = "PHIEUTRANHACUNGCAP_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuTraNhaCungCap phieutranhacungcapMa;

    @Column(name = "TONKHO_MA")
    private Integer tonKhoMa;
    
    public CtTraNhaCungCap() {
    }


    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
if(create && getDmnctMaso() == null) setDmnctMaso(new DmNguonChuongTrinh());
return  getDmnctMaso();
}
    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso(boolean create) {
if(create && getDmnguonkinhphiMaso() == null) setDmnguonkinhphiMaso(new DmNguonKinhPhi());
return  getDmnguonkinhphiMaso();
}
    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso(boolean create) {
if(create && getDmnhasanxuatMaso() == null) setDmnhasanxuatMaso(new DmNhaSanXuat());
return  getDmnhasanxuatMaso();
}
    public DmNhaSanXuat getDmnhasanxuatMaso() {
        return dmnhasanxuatMaso;
    }

    public void setDmnhasanxuatMaso(DmNhaSanXuat dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public DmQuocGia getDmquocgiaMaso(boolean create) {
if(create && getDmquocgiaMaso() == null) setDmquocgiaMaso(new DmQuocGia());
return  getDmquocgiaMaso();
}
    public DmQuocGia getDmquocgiaMaso() {
        return dmquocgiaMaso;
    }

    public void setDmquocgiaMaso(DmQuocGia dmquocgiaMaso) {
        this.dmquocgiaMaso = dmquocgiaMaso;
    }

    public DmThuoc getDmthuocMaso(boolean create) {
if(create && getDmthuocMaso() == null) setDmthuocMaso(new DmThuoc());
return  getDmthuocMaso();
}
    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCttranhacungcapMa() != null ? getCttranhacungcapMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtTraNhaCungCap)) {
            return false;
        }
        CtTraNhaCungCap other = (CtTraNhaCungCap) object;
        if ((this.getCttranhacungcapMa() == null && other.getCttranhacungcapMa() != null) || (this.getCttranhacungcapMa() != null && !this.cttranhacungcapMa.equals(other.cttranhacungcapMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtXuatKho[cttranhacungcapMa=" + getCttranhacungcapMa() + "]";
    }

    public Integer getTonKhoMa() {
        return tonKhoMa;
    }

    public void setTonKhoMa(Integer tonKhoMa) {
        this.tonKhoMa = tonKhoMa;
    }

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

    /**
     * @return the cttranhacungcapMa
     */
    public Integer getCttranhacungcapMa() {
        return cttranhacungcapMa;
    }

    /**
     * @param cttranhacungcapMa the cttranhacungcapMa to set
     */
    public void setCttranhacungcapMa(Integer cttranhacungcapMa) {
        this.cttranhacungcapMa = cttranhacungcapMa;
    }

    /**
     * @return the cttranhacungcapNamnhap
     */
    public String getCttranhacungcapNamnhap() {
        return cttranhacungcapNamnhap;
    }

    /**
     * @param cttranhacungcapNamnhap the cttranhacungcapNamnhap to set
     */
    public void setCttranhacungcapNamnhap(String cttranhacungcapNamnhap) {
        this.cttranhacungcapNamnhap = cttranhacungcapNamnhap;
    }

    /**
     * @return the cttranhacungcapNgayhandung
     */
    public String getCttranhacungcapNgayhandung() {
        return cttranhacungcapNgayhandung;
    }

    /**
     * @param cttranhacungcapNgayhandung the cttranhacungcapNgayhandung to set
     */
    public void setCttranhacungcapNgayhandung(String cttranhacungcapNgayhandung) {
        this.cttranhacungcapNgayhandung = cttranhacungcapNgayhandung;
    }

    /**
     * @return the cttranhacungcapThanghandung
     */
    public String getCttranhacungcapThanghandung() {
        return cttranhacungcapThanghandung;
    }

    /**
     * @param cttranhacungcapThanghandung the cttranhacungcapThanghandung to set
     */
    public void setCttranhacungcapThanghandung(String cttranhacungcapThanghandung) {
        this.cttranhacungcapThanghandung = cttranhacungcapThanghandung;
    }

    /**
     * @return the cttranhacungcapNamhandung
     */
    public String getCttranhacungcapNamhandung() {
        return cttranhacungcapNamhandung;
    }

    /**
     * @param cttranhacungcapNamhandung the cttranhacungcapNamhandung to set
     */
    public void setCttranhacungcapNamhandung(String cttranhacungcapNamhandung) {
        this.cttranhacungcapNamhandung = cttranhacungcapNamhandung;
    }

    /**
     * @return the cttranhacungcapDongia
     */
    public Double getCttranhacungcapDongia() {
        return cttranhacungcapDongia;
    }

    /**
     * @param cttranhacungcapDongia the cttranhacungcapDongia to set
     */
    public void setCttranhacungcapDongia(Double cttranhacungcapDongia) {
        this.cttranhacungcapDongia = cttranhacungcapDongia;
    }

    /**
     * @return the cttranhacungcapDongiaban
     */
    public Double getCttranhacungcapDongiaban() {
        return cttranhacungcapDongiaban;
    }

    /**
     * @param cttranhacungcapDongiaban the cttranhacungcapDongiaban to set
     */
    public void setCttranhacungcapDongiaban(Double cttranhacungcapDongiaban) {
        this.cttranhacungcapDongiaban = cttranhacungcapDongiaban;
    }

    /**
     * @return the cttranhacungcapLo
     */
    public String getCttranhacungcapLo() {
        return cttranhacungcapLo;
    }

    /**
     * @param cttranhacungcapLo the cttranhacungcapLo to set
     */
    public void setCttranhacungcapLo(String cttranhacungcapLo) {
        this.cttranhacungcapLo = cttranhacungcapLo;
    }

    /**
     * @return the cttranhacungcapMalk
     */
    public String getCttranhacungcapMalk() {
        return cttranhacungcapMalk;
    }

    /**
     * @param cttranhacungcapMalk the cttranhacungcapMalk to set
     */
    public void setCttranhacungcapMalk(String cttranhacungcapMalk) {
        this.cttranhacungcapMalk = cttranhacungcapMalk;
    }

    /**
     * @return the cttranhacungcapSodangky
     */
    public String getCttranhacungcapSodangky() {
        return cttranhacungcapSodangky;
    }

    /**
     * @param cttranhacungcapSodangky the cttranhacungcapSodangky to set
     */
    public void setCttranhacungcapSodangky(String cttranhacungcapSodangky) {
        this.cttranhacungcapSodangky = cttranhacungcapSodangky;
    }

    /**
     * @return the cttranhacungcapThutu
     */
    public Short getCttranhacungcapThutu() {
        return cttranhacungcapThutu;
    }

    /**
     * @param cttranhacungcapThutu the cttranhacungcapThutu to set
     */
    public void setCttranhacungcapThutu(Short cttranhacungcapThutu) {
        this.cttranhacungcapThutu = cttranhacungcapThutu;
    }

    /**
     * @return the cttranhacungcapSoluong
     */
    public Double getCttranhacungcapSoluong() {
        return cttranhacungcapSoluong;
    }

    /**
     * @param cttranhacungcapSoluong the cttranhacungcapSoluong to set
     */
    public void setCttranhacungcapSoluong(Double cttranhacungcapSoluong) {
        this.cttranhacungcapSoluong = cttranhacungcapSoluong;
    }

    /**
     * @return the cttranhacungcapNgaygiocn
     */
    public Date getCttranhacungcapNgaygiocn() {
        return cttranhacungcapNgaygiocn;
    }

    /**
     * @param cttranhacungcapNgaygiocn the cttranhacungcapNgaygiocn to set
     */
    public void setCttranhacungcapNgaygiocn(Date cttranhacungcapNgaygiocn) {
        this.cttranhacungcapNgaygiocn = cttranhacungcapNgaygiocn;
    }

    /**
     * @return the phieutranhacungcapMa
     */
    public PhieuTraNhaCungCap getPhieutranhacungcapMa() {
        return phieutranhacungcapMa;
    }

     public PhieuTraNhaCungCap getPhieutranhacungcapMa(boolean create) {
        if(create && getDmthuocMaso() == null) setPhieutranhacungcapMa(new PhieuTraNhaCungCap());
        return phieutranhacungcapMa;
    }

    /**
     * @param phieutranhacungcapMa the phieutranhacungcapMa to set
     */
    public void setPhieutranhacungcapMa(PhieuTraNhaCungCap phieutranhacungcapMa) {
        this.phieutranhacungcapMa = phieutranhacungcapMa;
    }
}


