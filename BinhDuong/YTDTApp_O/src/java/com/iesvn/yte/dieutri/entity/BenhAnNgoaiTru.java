/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
 * @author i-boy
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "BENH_AN_NGOAI_TRU")
@NamedQueries({})
public class BenhAnNgoaiTru implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BANT_MA")
    private String bantMa;
    @Column(name = "BANT_QTBENHLY")
    private String bantQtbenhly;
    @Column(name = "BANT_TIENSUBENHBT")
    private String bantTiensubenhbt;
    @Column(name = "BANT_TIENSUBENHGD")
    private String bantTiensubenhgd;
    @Column(name = "BANT_TOANTHAN")
    private String bantToanthan;
    @Column(name = "BANT_CACBOPHAN")
    private String bantCacbophan;
    @Column(name = "BANT_XETNGHIEM")
    private String bantXetnghiem;
    @Column(name = "BANT_DAXULY")
    private String bantDaxuly;
    @Column(name = "BANT_QTBENHLIDBLS")
    private String bantQtbenhlidbls;
    @Column(name = "BANT_KQCANLAMSANG")
    private String bantKqcanlamsang;
    @Column(name = "BANT_GIAIPHAUBENH")
    private String bantGiaiphaubenh;
    @Column(name = "BANT_PPDIEUTRI")
    private String bantPpdieutri;
    @Column(name = "BANT_TINHTRANGNGUOIBENH")
    private String bantTinhtrangnguoibenh;
    @Column(name = "BANT_HUONGDT")
    private String bantHuongdt;
    @Column(name = "BANT_SLXQUANG")
    private Integer bantSlxquang;
    @Column(name = "BANT_SLSCANNER")
    private Integer bantSlscanner;
    @Column(name = "BANT_SLSIEUAM")
    private Integer bantSlsieuam;
    @Column(name = "BANT_SLXETNGHIEM")
    private Integer bantSlxetnghiem;
    @Column(name = "BANT_SLKHAC")
    private Integer bantSlkhac;
    @Column(name = "BANT_DTTUNGAY")
    @Temporal(TemporalType.DATE)
    private Date bantDttungay;
    @Column(name = "BANT_DTDENNGAY")
    @Temporal(TemporalType.DATE)
    private Date bantDtdenngay;
    @Column(name = "BANT_SOTHEBH")
    private String bantSothebh;
    @Column(name = "BANT_SOVAOVIEN")
    private String bantSovaovien;
    @Column(name = "BANT_LYDOVVIEN")
    private String bantLydovaovien;
    @JoinColumn(name = "BANT_BENHNHAN", referencedColumnName = "BENHNHAN_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhNhan bantBenhnhan;
    @JoinColumn(name = "BANT_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham bantBankham;
    @JoinColumn(name = "BANT_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham bantThamkham;

    public BenhAnNgoaiTru() {
    }

    public BenhAnNgoaiTru(String bantMa) {
        this.bantMa = bantMa;
    }

    public String getBantMa() {
        return bantMa;
    }

    public void setBantMa(String bantMa) {
        this.bantMa = bantMa;
    }

    public String getBantQtbenhly() {
        return bantQtbenhly;
    }

    public void setBantQtbenhly(String bantQtbenhly) {
        this.bantQtbenhly = bantQtbenhly;
    }

    public String getBantTiensubenhbt() {
        return bantTiensubenhbt;
    }

    public void setBantTiensubenhbt(String bantTiensubenhbt) {
        this.bantTiensubenhbt = bantTiensubenhbt;
    }

    public String getBantTiensubenhgd() {
        return bantTiensubenhgd;
    }

    public void setBantTiensubenhgd(String bantTiensubenhgd) {
        this.bantTiensubenhgd = bantTiensubenhgd;
    }

    public String getBantToanthan() {
        return bantToanthan;
    }

    public void setBantToanthan(String bantToanthan) {
        this.bantToanthan = bantToanthan;
    }

    public String getBantCacbophan() {
        return bantCacbophan;
    }

    public void setBantCacbophan(String bantCacbophan) {
        this.bantCacbophan = bantCacbophan;
    }

    public String getBantXetnghiem() {
        return bantXetnghiem;
    }

    public void setBantXetnghiem(String bantXetnghiem) {
        this.bantXetnghiem = bantXetnghiem;
    }

    public String getBantDaxuly() {
        return bantDaxuly;
    }

    public void setBantDaxuly(String bantDaxuly) {
        this.bantDaxuly = bantDaxuly;
    }

    public String getBantQtbenhlidbls() {
        return bantQtbenhlidbls;
    }

    public void setBantQtbenhlidbls(String bantQtbenhli) {
        this.bantQtbenhlidbls = bantQtbenhli;
    }

    public String getBantKqcanlamsang() {
        return bantKqcanlamsang;
    }

    public void setBantKqcanlamsang(String bantKqcanlamsang) {
        this.bantKqcanlamsang = bantKqcanlamsang;
    }

    public String getBantGiaiphaubenh() {
        return bantGiaiphaubenh;
    }

    public void setBantGiaiphaubenh(String bantGiaiphaubenh) {
        this.bantGiaiphaubenh = bantGiaiphaubenh;
    }

    public String getBantPpdieutri() {
        return bantPpdieutri;
    }

    public void setBantPpdieutri(String bantPpdieutri) {
        this.bantPpdieutri = bantPpdieutri;
    }

    public String getBantTinhtrangnguoibenh() {
        return bantTinhtrangnguoibenh;
    }

    public void setBantTinhtrangnguoibenh(String bantTinhtrangnguoibenh) {
        this.bantTinhtrangnguoibenh = bantTinhtrangnguoibenh;
    }

    public String getBantHuongdt() {
        return bantHuongdt;
    }

    public void setBantHuongdt(String bantHuongdt) {
        this.bantHuongdt = bantHuongdt;
    }

    public Integer getBantSlxquang() {
        return bantSlxquang;
    }

    public void setBantSlxquang(Integer bantSlxquang) {
        this.bantSlxquang = bantSlxquang;
    }

    public Integer getBantSlscanner() {
        return bantSlscanner;
    }

    public void setBantSlscanner(Integer bantSlscanner) {
        this.bantSlscanner = bantSlscanner;
    }

    public Integer getBantSlsieuam() {
        return bantSlsieuam;
    }

    public void setBantSlsieuam(Integer bantSlsieuam) {
        this.bantSlsieuam = bantSlsieuam;
    }

    public Integer getBantSlxetnghiem() {
        return bantSlxetnghiem;
    }

    public void setBantSlxetnghiem(Integer bantSlxetnghiem) {
        this.bantSlxetnghiem = bantSlxetnghiem;
    }

    public Integer getBantSlkhac() {
        return bantSlkhac;
    }

    public void setBantSlkhac(Integer bantSlkhac) {
        this.bantSlkhac = bantSlkhac;
    }

    public Date getBantDttungay() {
        return bantDttungay;
    }

    public void setBantDttungay(Date bantDttungay) {
        this.bantDttungay = bantDttungay;
    }

    public Date getBantDtdenngay() {
        return bantDtdenngay;
    }

    public void setBantDtdenngay(Date bantDtdenngay) {
        this.bantDtdenngay = bantDtdenngay;
    }

    public String getBantSothebh() {
        return bantSothebh;
    }

    public String getBantSovaovien() {
        return bantSovaovien;
    }

    public void setBantSovaovien(String bantSovaovien) {
        this.bantSovaovien = bantSovaovien;
    }

    public void setBantSothebh(String bantSothebh) {
        this.bantSothebh = bantSothebh;
    }

    public BenhNhan getBantBenhnhan() {
        return bantBenhnhan;
    }

    public BenhNhan getBantBenhnhan(Boolean create) {
        if(create && bantBenhnhan == null) bantBenhnhan = new BenhNhan();
        return bantBenhnhan;
    }

    public void setBantBenhnhan(BenhNhan bantBenhnhan) {
        this.bantBenhnhan = bantBenhnhan;
    }

    public DtDmBanKham getBantBankham() {
        return bantBankham;
    }

    public DtDmBanKham getBantBankham(Boolean create) {
        if(create && bantBankham == null) bantBankham = new DtDmBanKham();
        return bantBankham;
    }

    public void setBantBankham(DtDmBanKham bantBankham) {
        this.bantBankham = bantBankham;
    }

    public ThamKham getBantThamkham() {
        return bantThamkham;
    }

    public String getBantLydovaovien() {
        return bantLydovaovien;
    }

    public void setBantLydovaovien(String bantLydovaovien) {
        this.bantLydovaovien = bantLydovaovien;
    }


    public ThamKham getBantThamkham(Boolean create) {
        if( create && bantThamkham == null) bantThamkham = new ThamKham();
        return bantThamkham;
    }

    public void setBantThamkham(ThamKham bantThamkham) {
        this.bantThamkham = bantThamkham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bantMa != null ? bantMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BenhAnNgoaiTru)) {
            return false;
        }
        BenhAnNgoaiTru other = (BenhAnNgoaiTru) object;
        if ((this.bantMa == null && other.bantMa != null) || (this.bantMa != null && !this.bantMa.equals(other.bantMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sourcepackage.BenhAnNgoaiTru[bantMa=" + bantMa + "]";
    }

}
