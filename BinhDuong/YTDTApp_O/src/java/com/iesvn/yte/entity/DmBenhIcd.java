/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_BENH_ICD")
@NamedQueries({})
public class DmBenhIcd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_BENH_ICD")
    @SequenceGenerator(name = "DM_BENH_ICD", sequenceName = "DM_BENH_ICD_DMBENHICD_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMBENHICD_MASO", nullable = false)
    private Integer dmbenhicdMaso;
    @Column(name = "DMBENHICD_MA", nullable = false)
    private String dmbenhicdMa;
    @Column(name = "DMBENHICD_MACHUNG")
    private String dmbenhicdMachung;
    @Column(name = "DMBENHICD_TACNHAN")
    private Boolean dmbenhicdTacnhan;
    @Column(name = "DMBENHICD_LIENHE")
    private String dmbenhicdLienhe;
    
    @Column(name = "DMBENHICD_TIENGANH")
    private String dmbenhicdTiengAnh;

    @Column(name = "DMBENHICD_NGAYGIOCN")
    private Double dmbenhicdNgaygiocn;
    @Column(name = "DMBENHICD_QL")
    private Boolean dmbenhicdQl;
    @Column(name = "DMBENHICD_DT")
    private Boolean dmbenhicdDt;
    @Column(name = "DMBENHICD_DP")
    private Boolean dmbenhicdDp;
    @Column(name = "DMBENHICD_TEN")
    private String dmbenhicdTen;
    @JoinColumn(name = "DMCHUONGBENH_MASO", referencedColumnName = "DMCHUONGBENH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmChuongBenh dmchuongbenhMaso;
    @JoinColumn(name = "DMBENHICD_BENHVN", referencedColumnName = "DMBENHVN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVn dmbenhicdBenhvn;
//    @OneToMany(mappedBy = "hsthtoankMabenh")
//    private Collection<HsThtoank> hsThtoankCollection;
//    @OneToMany(mappedBy = "hsthtoankMabenh1")
//    private Collection<HsThtoank> hsThtoankCollection1;
//    @OneToMany(mappedBy = "hsbasanMabenh")
//    private Collection<HsbaSan> hsbaSanCollection;
//    @OneToMany(mappedBy = "hsbasanMatuvong")
//    private Collection<HsbaSan> hsbaSanCollection1;
//    @OneToMany(mappedBy = "hsbasanMabenh1")
//    private Collection<HsbaSan> hsbaSanCollection2;
//    @OneToMany(mappedBy = "hsbasanMatuvong1")
//    private Collection<HsbaSan> hsbaSanCollection3;
//    @OneToMany(mappedBy = "benhicd10")
//    private Collection<ThamKham> thamKhamCollection;
//    @OneToMany(mappedBy = "benhicd101")
//    private Collection<ThamKham> thamKhamCollection1;
//    @OneToMany(mappedBy = "tiepdonMachdoanb0")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "tiepdonMachdoanbd")
//    private Collection<TiepDon> tiepDonCollection1;
//    @OneToMany(mappedBy = "tiepdonTuvong")
//    private Collection<TiepDon> tiepDonCollection2;
//    @OneToMany(mappedBy = "hsbacmBenhchinh")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection;
//    @OneToMany(mappedBy = "hsbacmBenhphu")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection1;
//    @OneToMany(mappedBy = "hsbacmTacnhan")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection2;
//    @OneToMany(mappedBy = "hsbacmBenhchinh1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection3;
//    @OneToMany(mappedBy = "hsbacmBenhphu1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection4;
//    @OneToMany(mappedBy = "hsbacmTacnhan1")
//    private Collection<HsbaChuyenMon> hsbaChuyenMonCollection5;
//    @OneToMany(mappedBy = "macd1")
//    private Collection<KetQuaMo> ketQuaMoCollection;
//    @OneToMany(mappedBy = "macd11")
//    private Collection<KetQuaMo> ketQuaMoCollection1;
//    @OneToMany(mappedBy = "hsbaTuvong")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "hsbaMachdravien")
//    private Collection<Hsba> hsbaCollection1;
//    @OneToMany(mappedBy = "hsbaMachdoantuyent")
//    private Collection<Hsba> hsbaCollection2;
//    @OneToMany(mappedBy = "hsbaMachdoanbd")
//    private Collection<Hsba> hsbaCollection3;
//    @OneToMany(mappedBy = "hsbaMachdoanbd1")
//    private Collection<Hsba> hsbaCollection4;
//    @OneToMany(mappedBy = "hsbaMachdoantuyent1")
//    private Collection<Hsba> hsbaCollection5;
//    @OneToMany(mappedBy = "hsbaMachdravien1")
//    private Collection<Hsba> hsbaCollection6;
//    @OneToMany(mappedBy = "hsbaTuvong1")
//    private Collection<Hsba> hsbaCollection7;

    public DmBenhIcd() {
    }

    public DmBenhIcd(Integer dmbenhicdMaso) {
        this.dmbenhicdMaso = dmbenhicdMaso;
    }

    public DmBenhIcd(Integer dmbenhicdMaso, String dmbenhicdMa) {
        this.dmbenhicdMaso = dmbenhicdMaso;
        this.dmbenhicdMa = dmbenhicdMa;
    }

    public Integer getDmbenhicdMaso() {
        return dmbenhicdMaso;
    }

    public void setDmbenhicdMaso(Integer dmbenhicdMaso) {
        this.dmbenhicdMaso = dmbenhicdMaso;
    }

    public String getDmbenhicdMa() {
        return dmbenhicdMa;
    }

    public void setDmbenhicdMa(String dmbenhicdMa) {
        this.dmbenhicdMa = dmbenhicdMa;
    }

    public String getDmbenhicdMachung() {
        return dmbenhicdMachung;
    }

    public void setDmbenhicdMachung(String dmbenhicdMachung) {
        this.dmbenhicdMachung = dmbenhicdMachung;
    }

    public Boolean getDmbenhicdTacnhan() {
        return dmbenhicdTacnhan;
    }

    public void setDmbenhicdTacnhan(Boolean dmbenhicdTacnhan) {
        this.dmbenhicdTacnhan = dmbenhicdTacnhan;
    }

    public String getDmbenhicdLienhe() {
        return dmbenhicdLienhe;
    }

    public void setDmbenhicdLienhe(String dmbenhicdLienhe) {
        this.dmbenhicdLienhe = dmbenhicdLienhe;
    }

   

    public Double getDmbenhicdNgaygiocn() {
        return dmbenhicdNgaygiocn;
    }

    public void setDmbenhicdNgaygiocn(Double dmbenhicdNgaygiocn) {
        this.dmbenhicdNgaygiocn = dmbenhicdNgaygiocn;
    }

    public Boolean getDmbenhicdQl() {
        return dmbenhicdQl;
    }

    public void setDmbenhicdQl(Boolean dmbenhicdQl) {
        this.dmbenhicdQl = dmbenhicdQl;
    }

    public Boolean getDmbenhicdDt() {
        return dmbenhicdDt;
    }

    public void setDmbenhicdDt(Boolean dmbenhicdDt) {
        this.dmbenhicdDt = dmbenhicdDt;
    }

    public Boolean getDmbenhicdDp() {
        return dmbenhicdDp;
    }

    public void setDmbenhicdDp(Boolean dmbenhicdDp) {
        this.dmbenhicdDp = dmbenhicdDp;
    }

    public String getDmbenhicdTen() {
        return dmbenhicdTen;
    }

    public void setDmbenhicdTen(String dmbenhicdTen) {
        this.dmbenhicdTen = dmbenhicdTen;
    }

    public DmChuongBenh getDmchuongbenhMaso(boolean create) {
if(create && dmchuongbenhMaso == null) dmchuongbenhMaso = new DmChuongBenh();
return dmchuongbenhMaso;
}
    public DmChuongBenh getDmchuongbenhMaso() {
        return dmchuongbenhMaso;
    }

    public void setDmchuongbenhMaso(DmChuongBenh dmchuongbenhMaso) {
        this.dmchuongbenhMaso = dmchuongbenhMaso;
    }

    public DmBenhVn getDmbenhicdBenhvn(boolean create) {
if(create && dmbenhicdBenhvn == null) dmbenhicdBenhvn = new DmBenhVn();
return dmbenhicdBenhvn;
}
    public DmBenhVn getDmbenhicdBenhvn() {
        return dmbenhicdBenhvn;
    }

    public void setDmbenhicdBenhvn(DmBenhVn dmbenhicdBenhvn) {
        this.dmbenhicdBenhvn = dmbenhicdBenhvn;
    }

   

//    public Collection<HsThtoank> getHsThtoankCollection() {
//        return hsThtoankCollection;
//    }
//
//    public void setHsThtoankCollection(Collection<HsThtoank> hsThtoankCollection) {
//        this.hsThtoankCollection = hsThtoankCollection;
//    }

//    public Collection<HsThtoank> getHsThtoankCollection1() {
//        return hsThtoankCollection1;
//    }
//
//    public void setHsThtoankCollection1(Collection<HsThtoank> hsThtoankCollection1) {
//        this.hsThtoankCollection1 = hsThtoankCollection1;
//    }

//    public Collection<HsbaSan> getHsbaSanCollection() {
//        return hsbaSanCollection;
//    }
//
//    public void setHsbaSanCollection(Collection<HsbaSan> hsbaSanCollection) {
//        this.hsbaSanCollection = hsbaSanCollection;
//    }

//    public Collection<HsbaSan> getHsbaSanCollection1() {
//        return hsbaSanCollection1;
//    }
//
//    public void setHsbaSanCollection1(Collection<HsbaSan> hsbaSanCollection1) {
//        this.hsbaSanCollection1 = hsbaSanCollection1;
//    }

//    public Collection<HsbaSan> getHsbaSanCollection2() {
//        return hsbaSanCollection2;
//    }
//
//    public void setHsbaSanCollection2(Collection<HsbaSan> hsbaSanCollection2) {
//        this.hsbaSanCollection2 = hsbaSanCollection2;
//    }

//    public Collection<HsbaSan> getHsbaSanCollection3() {
//        return hsbaSanCollection3;
//    }
//
//    public void setHsbaSanCollection3(Collection<HsbaSan> hsbaSanCollection3) {
//        this.hsbaSanCollection3 = hsbaSanCollection3;
//    }

//    public Collection<ThamKham> getThamKhamCollection() {
//        return thamKhamCollection;
//    }
//
//    public void setThamKhamCollection(Collection<ThamKham> thamKhamCollection) {
//        this.thamKhamCollection = thamKhamCollection;
//    }

//    public Collection<ThamKham> getThamKhamCollection1() {
//        return thamKhamCollection1;
//    }
//
//    public void setThamKhamCollection1(Collection<ThamKham> thamKhamCollection1) {
//        this.thamKhamCollection1 = thamKhamCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<TiepDon> getTiepDonCollection1() {
//        return tiepDonCollection1;
//    }
//
//    public void setTiepDonCollection1(Collection<TiepDon> tiepDonCollection1) {
//        this.tiepDonCollection1 = tiepDonCollection1;
//    }

//    public Collection<TiepDon> getTiepDonCollection2() {
//        return tiepDonCollection2;
//    }
//
//    public void setTiepDonCollection2(Collection<TiepDon> tiepDonCollection2) {
//        this.tiepDonCollection2 = tiepDonCollection2;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection() {
//        return hsbaChuyenMonCollection;
//    }
//
//    public void setHsbaChuyenMonCollection(Collection<HsbaChuyenMon> hsbaChuyenMonCollection) {
//        this.hsbaChuyenMonCollection = hsbaChuyenMonCollection;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection1() {
//        return hsbaChuyenMonCollection1;
//    }
//
//    public void setHsbaChuyenMonCollection1(Collection<HsbaChuyenMon> hsbaChuyenMonCollection1) {
//        this.hsbaChuyenMonCollection1 = hsbaChuyenMonCollection1;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection2() {
//        return hsbaChuyenMonCollection2;
//    }
//
//    public void setHsbaChuyenMonCollection2(Collection<HsbaChuyenMon> hsbaChuyenMonCollection2) {
//        this.hsbaChuyenMonCollection2 = hsbaChuyenMonCollection2;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection3() {
//        return hsbaChuyenMonCollection3;
//    }
//
//    public void setHsbaChuyenMonCollection3(Collection<HsbaChuyenMon> hsbaChuyenMonCollection3) {
//        this.hsbaChuyenMonCollection3 = hsbaChuyenMonCollection3;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection4() {
//        return hsbaChuyenMonCollection4;
//    }
//
//    public void setHsbaChuyenMonCollection4(Collection<HsbaChuyenMon> hsbaChuyenMonCollection4) {
//        this.hsbaChuyenMonCollection4 = hsbaChuyenMonCollection4;
//    }

//    public Collection<HsbaChuyenMon> getHsbaChuyenMonCollection5() {
//        return hsbaChuyenMonCollection5;
//    }
//
//    public void setHsbaChuyenMonCollection5(Collection<HsbaChuyenMon> hsbaChuyenMonCollection5) {
//        this.hsbaChuyenMonCollection5 = hsbaChuyenMonCollection5;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection() {
//        return ketQuaMoCollection;
//    }
//
//    public void setKetQuaMoCollection(Collection<KetQuaMo> ketQuaMoCollection) {
//        this.ketQuaMoCollection = ketQuaMoCollection;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection1() {
//        return ketQuaMoCollection1;
//    }
//
//    public void setKetQuaMoCollection1(Collection<KetQuaMo> ketQuaMoCollection1) {
//        this.ketQuaMoCollection1 = ketQuaMoCollection1;
//    }

//    public Collection<Hsba> getHsbaCollection() {
//        return hsbaCollection;
//    }
//
//    public void setHsbaCollection(Collection<Hsba> hsbaCollection) {
//        this.hsbaCollection = hsbaCollection;
//    }

//    public Collection<Hsba> getHsbaCollection1() {
//        return hsbaCollection1;
//    }
//
//    public void setHsbaCollection1(Collection<Hsba> hsbaCollection1) {
//        this.hsbaCollection1 = hsbaCollection1;
//    }

//    public Collection<Hsba> getHsbaCollection2() {
//        return hsbaCollection2;
//    }
//
//    public void setHsbaCollection2(Collection<Hsba> hsbaCollection2) {
//        this.hsbaCollection2 = hsbaCollection2;
//    }

//    public Collection<Hsba> getHsbaCollection3() {
//        return hsbaCollection3;
//    }
//
//    public void setHsbaCollection3(Collection<Hsba> hsbaCollection3) {
//        this.hsbaCollection3 = hsbaCollection3;
//    }

//    public Collection<Hsba> getHsbaCollection4() {
//        return hsbaCollection4;
//    }
//
//    public void setHsbaCollection4(Collection<Hsba> hsbaCollection4) {
//        this.hsbaCollection4 = hsbaCollection4;
//    }

//    public Collection<Hsba> getHsbaCollection5() {
//        return hsbaCollection5;
//    }
//
//    public void setHsbaCollection5(Collection<Hsba> hsbaCollection5) {
//        this.hsbaCollection5 = hsbaCollection5;
//    }

//    public Collection<Hsba> getHsbaCollection6() {
//        return hsbaCollection6;
//    }
//
//    public void setHsbaCollection6(Collection<Hsba> hsbaCollection6) {
//        this.hsbaCollection6 = hsbaCollection6;
//    }

//    public Collection<Hsba> getHsbaCollection7() {
//        return hsbaCollection7;
//    }
//
//    public void setHsbaCollection7(Collection<Hsba> hsbaCollection7) {
//        this.hsbaCollection7 = hsbaCollection7;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmbenhicdMaso != null ? dmbenhicdMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmBenhIcd)) {
            return false;
        }
        DmBenhIcd other = (DmBenhIcd) object;
        if ((this.dmbenhicdMaso == null && other.dmbenhicdMaso != null) || (this.dmbenhicdMaso != null && !this.dmbenhicdMaso.equals(other.dmbenhicdMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmBenhIcd[dmbenhicdMaso=" + dmbenhicdMaso + "]";
    }

    public String getDmbenhicdTiengAnh() {
        return dmbenhicdTiengAnh;
    }

    public void setDmbenhicdTiengAnh(String dmbenhicdTiengAnh) {
        this.dmbenhicdTiengAnh = dmbenhicdTiengAnh;
    }

}


