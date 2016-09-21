/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 *
 * @author i-boy
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "BA_NGOAI_TRU_YHCT")
@NamedQueries({})
public class BaNgoaiTruYhct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BANT_YHCT_MA")
    private String bantYhctMa;
    @Column(name = "BANT_YHCT_LYDOVAOV")
    private String bantYhctLydovaov;
    @Column(name = "BANT_YHCT_QTBENHLY")
    private String bantYhctQtbenhly;
    @Column(name = "BANT_YHCT_TSUBENHBT")
    private String bantYhctTsubenhbt;
    @Column(name = "BANT_YHCT_TSUBENHGD")
    private String bantYhctTsubenhgd;
    @Column(name = "BANT_YHCT_BPBIBENH")
    private String bantYhctBpbibenh;
    @Column(name = "BANT_YHCT_XNCANTHIET")
    private String bantYhctXncanthiet;
    @Column(name = "BANT_YHCT_VONGCHAN")
    private String bantYhctVongchan;
    @Column(name = "BANT_YHCT_VANCHAN")
    private String bantYhctVanchan;
    @Column(name = "BANT_YHCT_VANCHAN2")
    private String bantYhctVanchan2;
    @Column(name = "BANT_YHCT_THIETCHAN")
    private String bantYhctThietchan;
    @Column(name = "BANT_YHCT_CDBENHDANH")
    private String bantYhctCdbenhdanh;
    @Column(name = "BANT_YHCT_CDTANGPHU")
    private String bantYhctCdtangphu;
    @Column(name = "BANT_YHCT_CDBATCUONG")
    private String bantYhctCdbatcuong;
    @Column(name = "BANT_YHCT_CDNGNHAN")
    private String bantYhctCdngnhan;
    @Column(name = "BANT_YHCT_DTPHEPCHUA")
    private String bantYhctDtphepchua;
    @Column(name = "BANT_YHCT_DTPHTHUOC")
    private String bantYhctDtphthuoc;
    @Column(name = "BANT_YHCT_DTPHHUYET")
    private String bantYhctDtphhuyet;
    @Column(name = "BANT_YHCT_DTXOABOP")
    private String bantYhctDtxoabop;
    @Column(name = "BANT_YHCT_DTCDATNHA")
    private String bantYhctDtcdatnha;
    @Column(name = "BANT_YHCT_DTCDHLTNHA")
    private String bantYhctDtcdhltnha;
    @Column(name = "BANT_YHCT_TIENLUONG")
    private String bantYhctTienluong;
    @Column(name = "BANT_YHCT_KQCLSCHINH")
    private String bantYhctKqclschinh;
    @Column(name = "BANT_YHCT_KQGPBENH")
    private String bantYhctKqgpbenh;
    @Column(name = "BANT_YHCT_PT_YHHD")
    private String bantYhctPtYhhd;
    @Column(name = "BANT_YHCT_PT_YHCT")
    private String bantYhctPtYhct;
    @Column(name = "BANT_YHCT_CDRV_YHHD")
    private String bantYhctCdrvYhhd;
    @Column(name = "BANT_YHCT_CDRV_YHCT")
    private String bantYhctCdrvYhct;
    @Column(name = "BANT_YHCT_HDT_CDTT")
    private String bantYhctHdtCdtt;
    @Column(name = "BANT_YHCT_SOTHEBH")
    private String bantYhctSothebh;
    @Column(name = "BANT_YHCT_SOVAOVIEN")
    private String bantYhctSovaovien;
    @JoinColumn(name = "BANT_YHCT_BENHNHAN", referencedColumnName = "BENHNHAN_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhNhan bantYhctBenhnhan;
    @JoinColumn(name = "BANT_YHCT_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham bantYhctBankham;
    @JoinColumn(name = "BANT_YHCT_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien bantYhctBsdieutri;
    @JoinColumn(name = "BANT_YHCT_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham bantYhctThamkham;

    public BaNgoaiTruYhct() {
    }

    public BaNgoaiTruYhct(String bantYhctMa) {
        this.bantYhctMa = bantYhctMa;
    }

    public String getBantYhctMa() {
        return bantYhctMa;
    }

    public void setBantYhctMa(String bantYhctMa) {
        this.bantYhctMa = bantYhctMa;
    }

    public String getBantYhctLydovaov() {
        return bantYhctLydovaov;
    }

    public void setBantYhctLydovaov(String bantYhctLydovaov) {
        this.bantYhctLydovaov = bantYhctLydovaov;
    }

    public String getBantYhctQtbenhly() {
        return bantYhctQtbenhly;
    }

    public void setBantYhctQtbenhly(String bantYhctQtbenhly) {
        this.bantYhctQtbenhly = bantYhctQtbenhly;
    }

    public String getBantYhctTsubenhbt() {
        return bantYhctTsubenhbt;
    }

    public void setBantYhctTsubenhbt(String bantYhctTsubenhbt) {
        this.bantYhctTsubenhbt = bantYhctTsubenhbt;
    }

    public String getBantYhctTsubenhgd() {
        return bantYhctTsubenhgd;
    }

    public void setBantYhctTsubenhgd(String bantYhctTsubenhgd) {
        this.bantYhctTsubenhgd = bantYhctTsubenhgd;
    }

    public String getBantYhctBpbibenh() {
        return bantYhctBpbibenh;
    }

    public void setBantYhctBpbibenh(String bantYhctBpbibenh) {
        this.bantYhctBpbibenh = bantYhctBpbibenh;
    }

    public String getBantYhctXncanthiet() {
        return bantYhctXncanthiet;
    }

    public void setBantYhctXncanthiet(String bantYhctXncanthiet) {
        this.bantYhctXncanthiet = bantYhctXncanthiet;
    }

    public String getBantYhctVongchan() {
        return bantYhctVongchan;
    }

    public void setBantYhctVongchan(String bantYhctVongchan) {
        this.bantYhctVongchan = bantYhctVongchan;
    }

    public String getBantYhctVanchan() {
        return bantYhctVanchan;
    }

    public void setBantYhctVanchan(String bantYhctVanchan) {
        this.bantYhctVanchan = bantYhctVanchan;
    }

    public String getBantYhctVanchan2() {
        return bantYhctVanchan2;
    }

    public void setBantYhctVanchan2(String bantYhctVanchan2) {
        this.bantYhctVanchan2 = bantYhctVanchan2;
    }

    public String getBantYhctThietchan() {
        return bantYhctThietchan;
    }

    public void setBantYhctThietchan(String bantYhctThietchan) {
        this.bantYhctThietchan = bantYhctThietchan;
    }

    public String getBantYhctCdbenhdanh() {
        return bantYhctCdbenhdanh;
    }

    public void setBantYhctCdbenhdanh(String bantYhctCdbenhdanh) {
        this.bantYhctCdbenhdanh = bantYhctCdbenhdanh;
    }

    public String getBantYhctCdtangphu() {
        return bantYhctCdtangphu;
    }

    public void setBantYhctCdtangphu(String bantYhctCdtangphu) {
        this.bantYhctCdtangphu = bantYhctCdtangphu;
    }

    public String getBantYhctCdbatcuong() {
        return bantYhctCdbatcuong;
    }

    public void setBantYhctCdbatcuong(String bantYhctCdbatcuong) {
        this.bantYhctCdbatcuong = bantYhctCdbatcuong;
    }

    public String getBantYhctCdngnhan() {
        return bantYhctCdngnhan;
    }

    public void setBantYhctCdngnhan(String bantYhctCdngnhan) {
        this.bantYhctCdngnhan = bantYhctCdngnhan;
    }

    public String getBantYhctDtphepchua() {
        return bantYhctDtphepchua;
    }

    public void setBantYhctDtphepchua(String bantYhctDtphepchua) {
        this.bantYhctDtphepchua = bantYhctDtphepchua;
    }

    public String getBantYhctDtphthuoc() {
        return bantYhctDtphthuoc;
    }

    public void setBantYhctDtphthuoc(String bantYhctDtphthuoc) {
        this.bantYhctDtphthuoc = bantYhctDtphthuoc;
    }

    public String getBantYhctDtphhuyet() {
        return bantYhctDtphhuyet;
    }

    public void setBantYhctDtphhuyet(String bantYhctDtphhuyet) {
        this.bantYhctDtphhuyet = bantYhctDtphhuyet;
    }

    public String getBantYhctDtxoabop() {
        return bantYhctDtxoabop;
    }

    public void setBantYhctDtxoabop(String bantYhctDtxoabop) {
        this.bantYhctDtxoabop = bantYhctDtxoabop;
    }

    public String getBantYhctDtcdatnha() {
        return bantYhctDtcdatnha;
    }

    public void setBantYhctDtcdatnha(String bantYhctDtcdatnha) {
        this.bantYhctDtcdatnha = bantYhctDtcdatnha;
    }

    public String getBantYhctDtcdhltnha() {
        return bantYhctDtcdhltnha;
    }

    public void setBantYhctDtcdhltnha(String bantYhctDtcdhltnha) {
        this.bantYhctDtcdhltnha = bantYhctDtcdhltnha;
    }

    public String getBantYhctTienluong() {
        return bantYhctTienluong;
    }

    public void setBantYhctTienluong(String bantYhctTienluong) {
        this.bantYhctTienluong = bantYhctTienluong;
    }

    public String getBantYhctKqclschinh() {
        return bantYhctKqclschinh;
    }

    public void setBantYhctKqclschinh(String bantYhctKqclschinh) {
        this.bantYhctKqclschinh = bantYhctKqclschinh;
    }

    public String getBantYhctKqgpbenh() {
        return bantYhctKqgpbenh;
    }

    public void setBantYhctKqgpbenh(String bantYhctKqgpbenh) {
        this.bantYhctKqgpbenh = bantYhctKqgpbenh;
    }

    public String getBantYhctPtYhhd() {
        return bantYhctPtYhhd;
    }

    public void setBantYhctPtYhhd(String bantYhctPtYhhd) {
        this.bantYhctPtYhhd = bantYhctPtYhhd;
    }

    public String getBantYhctPtYhct() {
        return bantYhctPtYhct;
    }

    public void setBantYhctPtYhct(String bantYhctPtYhct) {
        this.bantYhctPtYhct = bantYhctPtYhct;
    }

    public String getBantYhctCdrvYhhd() {
        return bantYhctCdrvYhhd;
    }

    public void setBantYhctCdrvYhhd(String bantYhctCdrvYhhd) {
        this.bantYhctCdrvYhhd = bantYhctCdrvYhhd;
    }

    public String getBantYhctCdrvYhct() {
        return bantYhctCdrvYhct;
    }

    public void setBantYhctCdrvYhct(String bantYhctCdrvYhct) {
        this.bantYhctCdrvYhct = bantYhctCdrvYhct;
    }

    public String getBantYhctHdtCdtt() {
        return bantYhctHdtCdtt;
    }

    public void setBantYhctHdtCdtt(String bantYhctHdtCdtt) {
        this.bantYhctHdtCdtt = bantYhctHdtCdtt;
    }

    public String getBantYhctSothebh() {
        return bantYhctSothebh;
    }

    public void setBantYhctSothebh(String bantYhctSothebh) {
        this.bantYhctSothebh = bantYhctSothebh;
    }

    public String getBantYhctSovaovien() {
        return bantYhctSovaovien;
    }

    public void setBantYhctSovaovien(String bantYhctSovaovien) {
        this.bantYhctSovaovien = bantYhctSovaovien;
    }

    public BenhNhan getBantYhctBenhnhan() {
        return bantYhctBenhnhan;
    }

	public BenhNhan getBantYhctBenhnhan(Boolean create) {
        if(create && bantYhctBenhnhan == null) bantYhctBenhnhan = new BenhNhan();
        return bantYhctBenhnhan;
    }

    public void setBantYhctBenhnhan(BenhNhan bantYhctBenhnhan) {
        this.bantYhctBenhnhan = bantYhctBenhnhan;
    }

    public DtDmBanKham getBantYhctBankham() {
        return bantYhctBankham;
    }

	public DtDmBanKham getBantYhctBankham(Boolean create) {
        if(create && bantYhctBankham == null) bantYhctBankham = new DtDmBanKham();
        return bantYhctBankham;
    }

    public void setBantYhctBankham(DtDmBanKham bantYhctBankham) {
        this.bantYhctBankham = bantYhctBankham;
    }

    public DtDmNhanVien getBantYhctBsdieutri() {
        return bantYhctBsdieutri;
    }

	public DtDmNhanVien getBantYhctBsdieutri(boolean create) {
        if (create && bantYhctBsdieutri == null) {
            bantYhctBsdieutri = new DtDmNhanVien();
        }
        return bantYhctBsdieutri;
    }

    public void setBantYhctBsdieutri(DtDmNhanVien bantYhctBsdieutri) {
        this.bantYhctBsdieutri = bantYhctBsdieutri;
    }

    public ThamKham getBantYhctThamkham() {
        return bantYhctThamkham;
    }

	public ThamKham getBantYhctThamkham(Boolean create) {
        if(create && bantYhctThamkham == null) bantYhctThamkham = new ThamKham();
        return bantYhctThamkham;
    }

    public void setBantYhctThamkham(ThamKham bantYhctThamkham) {
        this.bantYhctThamkham = bantYhctThamkham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bantYhctMa != null ? bantYhctMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaNgoaiTruYhct)) {
            return false;
        }
        BaNgoaiTruYhct other = (BaNgoaiTruYhct) object;
        if ((this.bantYhctMa == null && other.bantYhctMa != null) || (this.bantYhctMa != null && !this.bantYhctMa.equals(other.bantYhctMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.BaNgoaiTruYhct[bantYhctMa=" + bantYhctMa + "]";
    }

}
