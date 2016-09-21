/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
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
@Table(name = "DT_DM_KHACH")
@NamedQueries({})
public class DtDmKhach implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DTDMKHACH_MA", nullable = false)
    private String dtdmkhachMa;
    @Column(name = "DTDMKHACH_TEN")
    private String dtdmkhachTen;
    @Column(name = "DTDMKHACH_DIACHI")
    private String dtdmkhachDiachi;
    @Column(name = "DTDMKHACH_MABOPHAN", nullable = false)
    private String dtdmkhachMabophan;
    @Column(name = "DTDMKHACH_DIENTHOAI", nullable = false)
    private String dtdmkhachDienthoai;
    @Column(name = "DTDMKHACH_MASOTHUE", nullable = false)
    private String dtdmkhachMasothue;
    @Column(name = "DTDMKHACH_FAX", nullable = false)
    private String dtdmkhachFax;
    @Column(name = "DTDMKHACH_PHANBIET", nullable = false)
    private String dtdmkhachPhanbiet;
    @Column(name = "DTDMKHACH_NGAYLV")
    @Temporal(TemporalType.DATE)
    private Date dtdmkhachNgaylv;
    @Column(name = "DTDMKHACH_NGAYGIOCN")
    private Double dtdmkhachNgaygiocn;
    @Column(name = "DTDMKHACH_CHON")
    private Boolean dtdmkhachChon;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;
    @JoinColumn(name = "DMNGUONKINHPHI_MASO", referencedColumnName = "DMNGUONKINHPHI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonKinhPhi dmnguonkinhphiMaso;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
    @JoinColumn(name = "DMTINH_MASO", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh dmtinhMaso;

    public DtDmKhach() {
    }

    public DtDmKhach(String dtdmkhachMa) {
        this.dtdmkhachMa = dtdmkhachMa;
    }

    public DtDmKhach(String dtdmkhachMa, String dtdmkhachMabophan, String dtdmkhachDienthoai, String dtdmkhachMasothue, String dtdmkhachFax, String dtdmkhachPhanbiet) {
        this.dtdmkhachMa = dtdmkhachMa;
        this.dtdmkhachMabophan = dtdmkhachMabophan;
        this.dtdmkhachDienthoai = dtdmkhachDienthoai;
        this.dtdmkhachMasothue = dtdmkhachMasothue;
        this.dtdmkhachFax = dtdmkhachFax;
        this.dtdmkhachPhanbiet = dtdmkhachPhanbiet;
    }

    public String getDtdmkhachMa() {
        return dtdmkhachMa;
    }

    public void setDtdmkhachMa(String dtdmkhachMa) {
        this.dtdmkhachMa = dtdmkhachMa;
    }

    public String getDtdmkhachTen() {
        return dtdmkhachTen;
    }

    public void setDtdmkhachTen(String dtdmkhachTen) {
        this.dtdmkhachTen = dtdmkhachTen;
    }

    public String getDtdmkhachDiachi() {
        return dtdmkhachDiachi;
    }

    public void setDtdmkhachDiachi(String dtdmkhachDiachi) {
        this.dtdmkhachDiachi = dtdmkhachDiachi;
    }

    public String getDtdmkhachMabophan() {
        return dtdmkhachMabophan;
    }

    public void setDtdmkhachMabophan(String dtdmkhachMabophan) {
        this.dtdmkhachMabophan = dtdmkhachMabophan;
    }

    public String getDtdmkhachDienthoai() {
        return dtdmkhachDienthoai;
    }

    public void setDtdmkhachDienthoai(String dtdmkhachDienthoai) {
        this.dtdmkhachDienthoai = dtdmkhachDienthoai;
    }

    public String getDtdmkhachMasothue() {
        return dtdmkhachMasothue;
    }

    public void setDtdmkhachMasothue(String dtdmkhachMasothue) {
        this.dtdmkhachMasothue = dtdmkhachMasothue;
    }

    public String getDtdmkhachFax() {
        return dtdmkhachFax;
    }

    public void setDtdmkhachFax(String dtdmkhachFax) {
        this.dtdmkhachFax = dtdmkhachFax;
    }

    public String getDtdmkhachPhanbiet() {
        return dtdmkhachPhanbiet;
    }

    public void setDtdmkhachPhanbiet(String dtdmkhachPhanbiet) {
        this.dtdmkhachPhanbiet = dtdmkhachPhanbiet;
    }

    public Date getDtdmkhachNgaylv() {
        return dtdmkhachNgaylv;
    }

    public void setDtdmkhachNgaylv(Date dtdmkhachNgaylv) {
        this.dtdmkhachNgaylv = dtdmkhachNgaylv;
    }

    public Double getDtdmkhachNgaygiocn() {
        return dtdmkhachNgaygiocn;
    }

    public void setDtdmkhachNgaygiocn(Double dtdmkhachNgaygiocn) {
        this.dtdmkhachNgaygiocn = dtdmkhachNgaygiocn;
    }

    public Boolean getDtdmkhachChon() {
        return dtdmkhachChon;
    }

    public void setDtdmkhachChon(Boolean dtdmkhachChon) {
        this.dtdmkhachChon = dtdmkhachChon;
    }

    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
if(create && dmnctMaso == null) dmnctMaso = new DmNguonChuongTrinh();
return dmnctMaso;
}
    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso(boolean create) {
if(create && dmnguonkinhphiMaso == null) dmnguonkinhphiMaso = new DmNguonKinhPhi();
return dmnguonkinhphiMaso;
}
    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
if(create && dmkhoaMaso == null) dmkhoaMaso = new DmKhoa();
return dmkhoaMaso;
}
    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DmTinh getDmtinhMaso(boolean create) {
if(create && dmtinhMaso == null) dmtinhMaso = new DmTinh();
return dmtinhMaso;
}
    public DmTinh getDmtinhMaso() {
        return dmtinhMaso;
    }

    public void setDmtinhMaso(DmTinh dmtinhMaso) {
        this.dmtinhMaso = dmtinhMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmkhachMa != null ? dtdmkhachMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmKhach)) {
            return false;
        }
        DtDmKhach other = (DtDmKhach) object;
        if ((this.dtdmkhachMa == null && other.dtdmkhachMa != null) || (this.dtdmkhachMa != null && !this.dtdmkhachMa.equals(other.dtdmkhachMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmKhach[dtdmkhachMa=" + dtdmkhachMa + "]";
    }
}


