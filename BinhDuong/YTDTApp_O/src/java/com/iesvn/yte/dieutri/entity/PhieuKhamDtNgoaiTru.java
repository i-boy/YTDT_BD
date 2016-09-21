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
 * @author james
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_KHAM_DT_NGOAI_TRU")
@NamedQueries({})
public class PhieuKhamDtNgoaiTru implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKDTNT_MA")
    private String pkdtntMa;
    @Column(name = "PKDTNT_TSCHAYMAULAU")
    private Boolean pkdtntTschaymaulau;
    @Column(name = "PKDTNT_TSPHANUNGTHUOC")
    private Boolean pkdtntTsphanungthuoc;
    @Column(name = "PKDTNT_TSBDIUNG")
    private Boolean pkdtntTsbdiung;
    @Column(name = "PKDTNT_TSBCAOHA")
    private Boolean pkdtntTsbcaoha;
    @Column(name = "PKDTNT_TSBTM")
    private Boolean pkdtntTsbtm;
    @Column(name = "PKDTNT_TSBTIEUDUONG")
    private Boolean pkdtntTsbtieuduong;
    @Column(name = "PKDTNT_TSBDADAYTH")
    private Boolean pkdtntTsbdadayth;
    @Column(name = "PKDTNT_TSBPHOI")
    private Boolean pkdtntTsbphoi;
    @Column(name = "PKDTNT_TSBTRUYENNHIEM")
    private Boolean pkdtntTsbtruyennhiem;
    @Column(name = "PKDTNT_PTRONGMIENG")
    private Boolean pkdtntPtrongmieng;
    @Column(name = "PKDTNT_PNGOAIMAT")
    private Boolean pkdtntPngoaimat;
    @Column(name = "PKDTNT_XNCONGTHUCMAU")
    private Boolean pkdtntXncongthucmau;
    @Column(name = "PKDTNT_XNTEBAOHOC")
    private Boolean pkdtntXntebaohoc;
    @Column(name = "PKDTNT_XNCAYVK")
    private Boolean pkdtntXncayvk;
    @Column(name = "PKDTNT_KHNHACHU")
    private Boolean pkdtntKhnhachu;
    @Column(name = "PKDTNT_KHCHUARANG")
    private Boolean pkdtntKhchuarang;
    @Column(name = "PKDTNT_KHNHORANG")
    private Boolean pkdtntKhnhorang;
    @Column(name = "PKDTNT_KHCANKHOP")
    private Boolean pkdtntKhcankhop;
    @Column(name = "PKDTNT_KHPHUCHINHCD")
    private Boolean pkdtntKhphuchinhcd;
    @Column(name = "PKDTNT_KHPHUHINHTL")
    private Boolean pkdtntKhphuhinhtl;
    @Column(name = "PKDTNT_KHCHINHHINHRM")
    private Boolean pkdtntKhchinhhinhrm;
    @Column(name = "PKDTNT_KHRANGTREEM")
    private Boolean pkdtntKhrangtreem;
    @Column(name = "PKDTNT_KHPHONGNGUASR")
    private Boolean pkdtntKhphongnguasr;
    @Column(name = "PKDTNT_KHPHAUTHUATHM")
    private Boolean pkdtntKhphauthuathm;
    @Column(name = "PKDTNT_PHTL")
    private Boolean pkdtntPhtl;
    @Column(name = "PKDTNT_PHCD")
    private Boolean pkdtntPhcd;
    @Column(name = "PKDTNT_NHANXET")
    private String pkdtntNhanxet;
    @Column(name = "PKDTNT_CHANDOAN")
    private String pkdtntChandoan;
     @Column(name = "PKDTNT_SOTHEBH")
    private String pkdtntSothebh;
    @Column(name = "PKDTNT_SOVAOVIEN")
    private String pkdtntSovaovien;
    @JoinColumn(name = "PKDTNT_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham pkdtntThamkham;
    @JoinColumn(name = "PKDTNT_BENHNHAN", referencedColumnName = "BENHNHAN_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhNhan pkdtntBenhnhan;
    @JoinColumn(name = "PKDTNT_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham pkdtntBankham;

    public PhieuKhamDtNgoaiTru() {
    }

    public PhieuKhamDtNgoaiTru(String pkdtntMa) {
        this.pkdtntMa = pkdtntMa;
    }

    public String getPkdtntMa() {
        return pkdtntMa;
    }

    public void setPkdtntMa(String pkdtntMa) {
        this.pkdtntMa = pkdtntMa;
    }

    public Boolean getPkdtntTschaymaulau() {
        return pkdtntTschaymaulau;
    }

    public void setPkdtntTschaymaulau(Boolean pkdtntTschaymaulau) {
        this.pkdtntTschaymaulau = pkdtntTschaymaulau;
    }

    public Boolean getPkdtntTsphanungthuoc() {
        return pkdtntTsphanungthuoc;
    }

    public void setPkdtntTsphanungthuoc(Boolean pkdtntTsphanungthuoc) {
        this.pkdtntTsphanungthuoc = pkdtntTsphanungthuoc;
    }

    public Boolean getPkdtntTsbdiung() {
        return pkdtntTsbdiung;
    }

    public void setPkdtntTsbdiung(Boolean pkdtntTsbdiung) {
        this.pkdtntTsbdiung = pkdtntTsbdiung;
    }

    public Boolean getPkdtntTsbcaoha() {
        return pkdtntTsbcaoha;
    }

    public void setPkdtntTsbcaoha(Boolean pkdtntTsbcaoha) {
        this.pkdtntTsbcaoha = pkdtntTsbcaoha;
    }

    public Boolean getPkdtntTsbtm() {
        return pkdtntTsbtm;
    }

    public void setPkdtntTsbtm(Boolean pkdtntTsbtm) {
        this.pkdtntTsbtm = pkdtntTsbtm;
    }

    public Boolean getPkdtntTsbtieuduong() {
        return pkdtntTsbtieuduong;
    }

    public void setPkdtntTsbtieuduong(Boolean pkdtntTsbtieuduong) {
        this.pkdtntTsbtieuduong = pkdtntTsbtieuduong;
    }

    public Boolean getPkdtntTsbdadayth() {
        return pkdtntTsbdadayth;
    }

    public void setPkdtntTsbdadayth(Boolean pkdtntTsbdadayth) {
        this.pkdtntTsbdadayth = pkdtntTsbdadayth;
    }

    public Boolean getPkdtntTsbphoi() {
        return pkdtntTsbphoi;
    }

    public void setPkdtntTsbphoi(Boolean pkdtntTsbphoi) {
        this.pkdtntTsbphoi = pkdtntTsbphoi;
    }

    public Boolean getPkdtntTsbtruyennhiem() {
        return pkdtntTsbtruyennhiem;
    }

    public void setPkdtntTsbtruyennhiem(Boolean pkdtntTsbtruyennhiem) {
        this.pkdtntTsbtruyennhiem = pkdtntTsbtruyennhiem;
    }

    public Boolean getPkdtntPtrongmieng() {
        return pkdtntPtrongmieng;
    }

    public void setPkdtntPtrongmieng(Boolean pkdtntPtrongmieng) {
        this.pkdtntPtrongmieng = pkdtntPtrongmieng;
    }

    public Boolean getPkdtntPngoaimat() {
        return pkdtntPngoaimat;
    }

    public void setPkdtntPngoaimat(Boolean pkdtntPngoaimat) {
        this.pkdtntPngoaimat = pkdtntPngoaimat;
    }

    public Boolean getPkdtntXncongthucmau() {
        return pkdtntXncongthucmau;
    }

    public void setPkdtntXncongthucmau(Boolean pkdtntXncongthucmau) {
        this.pkdtntXncongthucmau = pkdtntXncongthucmau;
    }

    public Boolean getPkdtntXntebaohoc() {
        return pkdtntXntebaohoc;
    }

    public void setPkdtntXntebaohoc(Boolean pkdtntXntebaohoc) {
        this.pkdtntXntebaohoc = pkdtntXntebaohoc;
    }

    public Boolean getPkdtntXncayvk() {
        return pkdtntXncayvk;
    }

    public void setPkdtntXncayvk(Boolean pkdtntXncayvk) {
        this.pkdtntXncayvk = pkdtntXncayvk;
    }

    public Boolean getPkdtntKhnhachu() {
        return pkdtntKhnhachu;
    }

    public void setPkdtntKhnhachu(Boolean pkdtntKhnhachu) {
        this.pkdtntKhnhachu = pkdtntKhnhachu;
    }

    public Boolean getPkdtntKhchuarang() {
        return pkdtntKhchuarang;
    }

    public void setPkdtntKhchuarang(Boolean pkdtntKhchuarang) {
        this.pkdtntKhchuarang = pkdtntKhchuarang;
    }

    public Boolean getPkdtntKhnhorang() {
        return pkdtntKhnhorang;
    }

    public void setPkdtntKhnhorang(Boolean pkdtntKhnhorang) {
        this.pkdtntKhnhorang = pkdtntKhnhorang;
    }

    public Boolean getPkdtntKhcankhop() {
        return pkdtntKhcankhop;
    }

    public void setPkdtntKhcankhop(Boolean pkdtntKhcankhop) {
        this.pkdtntKhcankhop = pkdtntKhcankhop;
    }

    public Boolean getPkdtntKhphuchinhcd() {
        return pkdtntKhphuchinhcd;
    }

    public void setPkdtntKhphuchinhcd(Boolean pkdtntKhphuchinhcd) {
        this.pkdtntKhphuchinhcd = pkdtntKhphuchinhcd;
    }

    public Boolean getPkdtntKhphuhinhtl() {
        return pkdtntKhphuhinhtl;
    }

    public void setPkdtntKhphuhinhtl(Boolean pkdtntKhphuhinhtl) {
        this.pkdtntKhphuhinhtl = pkdtntKhphuhinhtl;
    }

    public Boolean getPkdtntKhchinhhinhrm() {
        return pkdtntKhchinhhinhrm;
    }

    public void setPkdtntKhchinhhinhrm(Boolean pkdtntKhchinhhinhrm) {
        this.pkdtntKhchinhhinhrm = pkdtntKhchinhhinhrm;
    }

    public Boolean getPkdtntKhrangtreem() {
        return pkdtntKhrangtreem;
    }

    public void setPkdtntKhrangtreem(Boolean pkdtntKhrangtreem) {
        this.pkdtntKhrangtreem = pkdtntKhrangtreem;
    }

    public Boolean getPkdtntKhphongnguasr() {
        return pkdtntKhphongnguasr;
    }

    public void setPkdtntKhphongnguasr(Boolean pkdtntKhphongnguasr) {
        this.pkdtntKhphongnguasr = pkdtntKhphongnguasr;
    }

    public Boolean getPkdtntKhphauthuathm() {
        return pkdtntKhphauthuathm;
    }

    public void setPkdtntKhphauthuathm(Boolean pkdtntKhphauthuathm) {
        this.pkdtntKhphauthuathm = pkdtntKhphauthuathm;
    }

    

    public String getPkdtntNhanxet() {
        return pkdtntNhanxet;
    }

    public void setPkdtntNhanxet(String pkdtntNhanxet) {
        this.pkdtntNhanxet = pkdtntNhanxet;
    }

    public String getPkdtntChandoan() {
        return pkdtntChandoan;
    }

    public void setPkdtntChandoan(String pkdtntChandoan) {
        this.pkdtntChandoan = pkdtntChandoan;
    }

    public String getPkdtntSothebh() {
        return pkdtntSothebh;
    }

    public void setPkdtntSothebh(String pkdtntSothebh) {
        this.pkdtntSothebh = pkdtntSothebh;
    }

    public String getPkdtntSovaovien() {
        return pkdtntSovaovien;
    }

    public void setPkdtntSovaovien(String pkdtntSovaovien) {
        this.pkdtntSovaovien = pkdtntSovaovien;
    }

    public ThamKham getPkdtntThamkham() {
        return pkdtntThamkham;
    }

     public ThamKham getPkdtntThamkham(Boolean create) {
        if(create&&pkdtntThamkham==null) pkdtntThamkham=new ThamKham();
         return pkdtntThamkham;
    }

    public void setPkdtntThamkham(ThamKham pkdtntThamkham) {
        this.pkdtntThamkham = pkdtntThamkham;
    }

    public DtDmBanKham getPkdtntBankham() {
        return pkdtntBankham;
    }
     public DtDmBanKham getPkdtntBankham(Boolean create) {
        if(create&pkdtntBankham==null) pkdtntBankham=new DtDmBanKham();
        return pkdtntBankham;
    }
    public void setPkdtntBankham(DtDmBanKham pkdtntBankham) {
        this.pkdtntBankham = pkdtntBankham;
    }

    public BenhNhan getPkdtntBenhnhan() {
        return pkdtntBenhnhan;
    }
    public BenhNhan getPkdtntBenhnhan(Boolean create) {
        if(create&pkdtntBenhnhan==null) pkdtntBenhnhan=new BenhNhan();
        return pkdtntBenhnhan;
    }
    public void setPkdtntBenhnhan(BenhNhan pkdtntBenhnhan) {
        this.pkdtntBenhnhan = pkdtntBenhnhan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkdtntMa != null ? pkdtntMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuKhamDtNgoaiTru)) {
            return false;
        }
        PhieuKhamDtNgoaiTru other = (PhieuKhamDtNgoaiTru) object;
        if ((this.pkdtntMa == null && other.pkdtntMa != null) || (this.pkdtntMa != null && !this.pkdtntMa.equals(other.pkdtntMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.PhieuKhamDtNgoaiTru[pkdtntMa=" + pkdtntMa + "]";
    }

    /**
     * @return the pkdtntPhcl
     */
    public Boolean getPkdtntPhtl() {
        return pkdtntPhtl;
    }

    /**
     * @param pkdtntPhcl the pkdtntPhtl to set
     */
    public void setPkdtntPhtl(Boolean pkdtntPhtl) {
        this.pkdtntPhtl = pkdtntPhtl;
    }

    /**
     * @return the pkdtntPhcd
     */
    public Boolean getPkdtntPhcd() {
        return pkdtntPhcd;
    }

    /**
     * @param pkdtntPhcd the pkdtntPhcd to set
     */
    public void setPkdtntPhcd(Boolean pkdtntPhcd) {
        this.pkdtntPhcd = pkdtntPhcd;
    }

}
