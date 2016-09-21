/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_BAN_KHAM")
@NamedQueries({})
public class DtDmBanKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_BAN_KHAM_DTDMBANKHAM")
    @SequenceGenerator(name = "DT_DM_BAN_KHAM_DTDMBANKHAM", sequenceName = "DT_DM_BAN_KHAM_DTDMBANKHAM_MAS", allocationSize = 1)
    @Column(name = "DTDMBANKHAM_MASO", nullable = false)
    private Integer dtdmbankhamMaso;
    @Column(name = "DTDMBANKHAM_MA", nullable = false)
    private String dtdmbankhamMa;
    @Column(name = "DTDMBANKHAM_MA0")
    private String dtdmbankhamMa0;
    @Column(name = "DTDMBANKHAM_MA2")
    private String dtdmbankhamMa2;
    @Column(name = "DTDMBANKHAM_TEN")
    private String dtdmbankhamTen;
    @Column(name = "DTDMBANKHAM_KYHIEU")
    private String dtdmbankhamKyhieu;
    @Column(name = "DTDMBANKHAM_THOIGIAN")
    private Integer dtdmbankhamThoigian;
    @Column(name = "DTDMBANKHAM_TINHCHAT")
    private String dtdmbankhamTinhchat;
    @Column(name = "DTDMNHANVIEN_TENBC")
    private String dtdmnhanvienTenbc;
    
    @Column(name = "DTDMNHANVIEN_NHOM")
    private String dtdmbankhamNhom;
    
    
    @Column(name = "DTDMBANKHAM_NGAYGIOCN")
    private Double dtdmbankhamNgaygiocn;
    @Column(name = "DTDMBANKHAM_CHON")
    private Boolean dtdmbankhamChon;
//    @OneToMany(mappedBy = "xetgiamkhamBankham")
//    private Collection<XetGiamKham> xetGiamKhamCollection;
//    @OneToMany(mappedBy = "xetgiamkhamBankham1")
//    private Collection<XetGiamKham> xetGiamKhamCollection1;
//    @OneToMany(mappedBy = "hoanungkhamBankham")
//    private Collection<HoanUngKham> hoanUngKhamCollection;
//    @OneToMany(mappedBy = "hoanungkhamBankham1")
//    private Collection<HoanUngKham> hoanUngKhamCollection1;
//    @OneToMany(mappedBy = "thamkhamChbankham")
//    private Collection<ThamKham> thamKhamCollection;
//    @OneToMany(mappedBy = "thamkhamBankham")
//    private Collection<ThamKham> thamKhamCollection1;
//    @OneToMany(mappedBy = "thamkhamBankham1")
//    private Collection<ThamKham> thamKhamCollection2;
//    @OneToMany(mappedBy = "thamkhamChbankham1")
//    private Collection<ThamKham> thamKhamCollection3;
//    @OneToMany(mappedBy = "tamungkhamBankham")
//    private Collection<TamUngKham> tamUngKhamCollection;
//    @OneToMany(mappedBy = "tamungkhamBankham1")
//    private Collection<TamUngKham> tamUngKhamCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmbankhamMaso")
//    private Collection<DtDmBacSiBanKham> dtDmBacSiBanKhamCollection;
//    @OneToMany(mappedBy = "tiepdonBankham")
//    private Collection<TiepDon> tiepDonCollection;
    @JoinColumn(name = "DTDMNHANVIEN_BACSI1", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsi1;
    @JoinColumn(name = "DTDMNHANVIEN_BACSI2", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsi2;
    @JoinColumn(name = "DTDMNHANVIEN_BACSI3", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsi3;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;

    public DtDmBanKham() {
    }

    public DtDmBanKham(Integer dtdmbankhamMaso) {
        this.dtdmbankhamMaso = dtdmbankhamMaso;
    }

    public DtDmBanKham(Integer dtdmbankhamMaso, String dtdmbankhamMa) {
        this.dtdmbankhamMaso = dtdmbankhamMaso;
        this.dtdmbankhamMa = dtdmbankhamMa;
    }

    public Integer getDtdmbankhamMaso() {
        return dtdmbankhamMaso;
    }

    public void setDtdmbankhamMaso(Integer dtdmbankhamMaso) {
        this.dtdmbankhamMaso = dtdmbankhamMaso;
    }

    public String getDtdmbankhamMa() {
        return dtdmbankhamMa;
    }

    public void setDtdmbankhamMa(String dtdmbankhamMa) {
        this.dtdmbankhamMa = dtdmbankhamMa;
    }

    public String getDtdmbankhamMa0() {
        return dtdmbankhamMa0;
    }

    public void setDtdmbankhamMa0(String dtdmbankhamMa0) {
        this.dtdmbankhamMa0 = dtdmbankhamMa0;
    }

    public String getDtdmbankhamMa2() {
        return dtdmbankhamMa2;
    }

    public void setDtdmbankhamMa2(String dtdmbankhamMa2) {
        this.dtdmbankhamMa2 = dtdmbankhamMa2;
    }

    public String getDtdmbankhamTen() {
        return dtdmbankhamTen;
    }

    public void setDtdmbankhamTen(String dtdmbankhamTen) {
        this.dtdmbankhamTen = dtdmbankhamTen;
    }

    public String getDtdmbankhamKyhieu() {
        return dtdmbankhamKyhieu;
    }

    public void setDtdmbankhamKyhieu(String dtdmbankhamKyhieu) {
        this.dtdmbankhamKyhieu = dtdmbankhamKyhieu;
    }

    public Integer getDtdmbankhamThoigian() {
        return dtdmbankhamThoigian;
    }

    public void setDtdmbankhamThoigian(Integer dtdmbankhamThoigian) {
        this.dtdmbankhamThoigian = dtdmbankhamThoigian;
    }

    public String getDtdmbankhamTinhchat() {
        return dtdmbankhamTinhchat;
    }

    public void setDtdmbankhamTinhchat(String dtdmbankhamTinhchat) {
        this.dtdmbankhamTinhchat = dtdmbankhamTinhchat;
    }

    public String getDtdmnhanvienTenbc() {
        return dtdmnhanvienTenbc;
    }

    public void setDtdmnhanvienTenbc(String dtdmnhanvienTenbc) {
        this.dtdmnhanvienTenbc = dtdmnhanvienTenbc;
    }

    public Double getDtdmbankhamNgaygiocn() {
        return dtdmbankhamNgaygiocn;
    }

    public void setDtdmbankhamNgaygiocn(Double dtdmbankhamNgaygiocn) {
        this.dtdmbankhamNgaygiocn = dtdmbankhamNgaygiocn;
    }

    public Boolean getDtdmbankhamChon() {
        return dtdmbankhamChon;
    }

    public void setDtdmbankhamChon(Boolean dtdmbankhamChon) {
        this.dtdmbankhamChon = dtdmbankhamChon;
    }

//    public Collection<XetGiamKham> getXetGiamKhamCollection() {
//        return xetGiamKhamCollection;
//    }
//
//    public void setXetGiamKhamCollection(Collection<XetGiamKham> xetGiamKhamCollection) {
//        this.xetGiamKhamCollection = xetGiamKhamCollection;
//    }

//    public Collection<XetGiamKham> getXetGiamKhamCollection1() {
//        return xetGiamKhamCollection1;
//    }
//
//    public void setXetGiamKhamCollection1(Collection<XetGiamKham> xetGiamKhamCollection1) {
//        this.xetGiamKhamCollection1 = xetGiamKhamCollection1;
//    }

//    public Collection<HoanUngKham> getHoanUngKhamCollection() {
//        return hoanUngKhamCollection;
//    }
//
//    public void setHoanUngKhamCollection(Collection<HoanUngKham> hoanUngKhamCollection) {
//        this.hoanUngKhamCollection = hoanUngKhamCollection;
//    }

//    public Collection<HoanUngKham> getHoanUngKhamCollection1() {
//        return hoanUngKhamCollection1;
//    }
//
//    public void setHoanUngKhamCollection1(Collection<HoanUngKham> hoanUngKhamCollection1) {
//        this.hoanUngKhamCollection1 = hoanUngKhamCollection1;
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

//    public Collection<ThamKham> getThamKhamCollection2() {
//        return thamKhamCollection2;
//    }
//
//    public void setThamKhamCollection2(Collection<ThamKham> thamKhamCollection2) {
//        this.thamKhamCollection2 = thamKhamCollection2;
//    }

//    public Collection<ThamKham> getThamKhamCollection3() {
//        return thamKhamCollection3;
//    }
//
//    public void setThamKhamCollection3(Collection<ThamKham> thamKhamCollection3) {
//        this.thamKhamCollection3 = thamKhamCollection3;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection() {
//        return tamUngKhamCollection;
//    }
//
//    public void setTamUngKhamCollection(Collection<TamUngKham> tamUngKhamCollection) {
//        this.tamUngKhamCollection = tamUngKhamCollection;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection1() {
//        return tamUngKhamCollection1;
//    }
//
//    public void setTamUngKhamCollection1(Collection<TamUngKham> tamUngKhamCollection1) {
//        this.tamUngKhamCollection1 = tamUngKhamCollection1;
//    }

//    public Collection<DtDmBacSiBanKham> getDtDmBacSiBanKhamCollection() {
//        return dtDmBacSiBanKhamCollection;
//    }
//
//    public void setDtDmBacSiBanKhamCollection(Collection<DtDmBacSiBanKham> dtDmBacSiBanKhamCollection) {
//        this.dtDmBacSiBanKhamCollection = dtDmBacSiBanKhamCollection;
//    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }
    public DtDmNhanVien getDtdmnhanvienBacsi1(boolean create) {
if(create && dtdmnhanvienBacsi1 == null) dtdmnhanvienBacsi1 = new DtDmNhanVien();
return dtdmnhanvienBacsi1;
}
    public DtDmNhanVien getDtdmnhanvienBacsi1() {
        return dtdmnhanvienBacsi1;
    }

    public void setDtdmnhanvienBacsi1(DtDmNhanVien dtdmnhanvienBacsi1) {
        this.dtdmnhanvienBacsi1 = dtdmnhanvienBacsi1;
    }

    public DtDmNhanVien getDtdmnhanvienBacsi2(boolean create) {
if(create && dtdmnhanvienBacsi2 == null) dtdmnhanvienBacsi2 = new DtDmNhanVien();
return dtdmnhanvienBacsi2;
}
    public DtDmNhanVien getDtdmnhanvienBacsi2() {
        return dtdmnhanvienBacsi2;
    }

    public void setDtdmnhanvienBacsi2(DtDmNhanVien dtdmnhanvienBacsi2) {
        this.dtdmnhanvienBacsi2 = dtdmnhanvienBacsi2;
    }

    public DtDmNhanVien getDtdmnhanvienBacsi3(boolean create) {
if(create && dtdmnhanvienBacsi3 == null) dtdmnhanvienBacsi3 = new DtDmNhanVien();
return dtdmnhanvienBacsi3;
}
    public DtDmNhanVien getDtdmnhanvienBacsi3() {
        return dtdmnhanvienBacsi3;
    }

    public void setDtdmnhanvienBacsi3(DtDmNhanVien dtdmnhanvienBacsi3) {
        this.dtdmnhanvienBacsi3 = dtdmnhanvienBacsi3;
    }


    
  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmbankhamMaso != null ? dtdmbankhamMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmBanKham)) {
            return false;
        }
        DtDmBanKham other = (DtDmBanKham) object;
        if ((this.dtdmbankhamMaso == null && other.dtdmbankhamMaso != null) || (this.dtdmbankhamMaso != null && !this.dtdmbankhamMaso.equals(other.dtdmbankhamMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmBanKham[dtdmbankhamMaso=" + dtdmbankhamMaso + "]";
    }

    public String getDtdmbankhamNhom() {
        return dtdmbankhamNhom;
    }

    public void setDtdmbankhamNhom(String dtdmbankhamNhom) {
        this.dtdmbankhamNhom = dtdmbankhamNhom;
    }

    /**
     * @return the dmkhoaMaso
     */
    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    /**
     * @param dmkhoaMaso the dmkhoaMaso to set
     */
    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

        public DmKhoa getDmkhoaMaso(boolean create) {
if(create && dmkhoaMaso == null) dmkhoaMaso = new DmKhoa();
return dmkhoaMaso;
}

}


