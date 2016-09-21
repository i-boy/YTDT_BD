/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmDieuTri;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKetQuaDieuTri;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "THAM_KHAM")
@NamedQueries({})
public class ThamKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THAM_KHAM")
    @SequenceGenerator(name = "THAM_KHAM", sequenceName = "THAM_KHAM_THAMKHAM_MA_SEQ", allocationSize = 1)
    @Column(name = "THAMKHAM_MA", nullable = false)
    private Integer thamkhamMa;
    
    @Column(name = "THAMKHAM_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thamkhamNgaygio;
    
    @Column(name = "THAMKHAM_DGBENH")
    private String thamkhamDgbenh;
    @Column(name = "THAMKHAM_NGAYGIOKT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thamkhamNgaygiokt;
    @Column(name = "THAMKHAM_CLSANG")
    private Boolean thamkhamClsang;
    @Column(name = "THAMKHAM_THUTHUAT")
    private Boolean thamkhamThuthuat;
    @Column(name = "THAMKHAM_DONLINH")
    private Boolean thamkhamDonlinh;
    @Column(name = "THAMKHAM_DONVE")
    private Boolean thamkhamDonve;
    @Column(name = "THAMKHAM_MAPHIEUCV")
    private String thamkhamMaphieucv;
    @Column(name = "THAMKHAM_BENHSU")
    private String thamkhamBenhsu;
    @Column(name = "THAMKHAM_THAMKHAM")
    private String thamkhamThamkham;
    @Column(name = "THAMKHAM_XUTRI")
    private String thamkhamXutri;
    @Column(name = "THAMKHAM_LAMSANG")
    private String thamkhamLamsang;
    @Column(name = "THAMKHAM_CUM")
    private Short thamkhamCum;

    @Column(name = "THAMKHAM_CHUYENSOLIEUVAONT")
    private Boolean thamkhamChuyenSoLieuVaoNoiTru;


    @Column(name = "THAMKHAM_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thamkhamNgaygiocn;
    @Column(name = "THAMKHAM_STATUS")
    private String thamkhamStatus;
    @Column(name = "THAMKHAM_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date thamkhamNgaytt;
//    @OneToMany(mappedBy = "clskhamThamkham")
//    private Collection<ClsKham> clsKhamCollection;
    @JoinColumn(name = "THAMKHAM_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thamkhamBacsi;
    @JoinColumn(name = "THAMKHAM_CHBANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham thamkhamChbankham;
    @JoinColumn(name = "BENHICD10", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd benhicd10;



    @JoinColumn(name = "BENHICD10_PHU1", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd benhicd10phu1;

    @JoinColumn(name = "BENHICD10_PHU2", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd benhicd10phu2;

    @JoinColumn(name = "BENHICD10_PHU3", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd benhicd10phu3;

    @JoinColumn(name = "BENHICD10_PHU4", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd benhicd10phu4;

    @JoinColumn(name = "BENHICD10_PHU5", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd benhicd10phu5;
    @Column(name = "THAMKHAM_NGAYTAIKHAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thamkhamNgaytaikham;

    @JoinColumn(name = "THAMKHAM_KETQUA", referencedColumnName = "DMKQDT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKetQuaDieuTri thamkhamKetqua;
    
    
    @JoinColumn(name = "THAMKHAM_DIEUTRI", referencedColumnName = "DMDIEUTRI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDieuTri thamkhamDieutri;
    @JoinColumn(name = "THAMKHAM_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thamkhamNhanviencn;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
    @JoinColumn(name = "THAMKHAM_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham thamkhamBankham;
//    @OneToMany(mappedBy = "toalinhkhamThamkham")
//    private Collection<ToaLinhKham> toaLinhKhamCollection;
//    @OneToMany(mappedBy = "toathuockhamThamkham")
//    private Collection<ToaThuocKham> toaThuocKhamCollection;
//    @OneToMany(mappedBy = "thuocphongkhamThamkham")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection;

    @Column(name = "THAMKHAM_CAP_TOA")
    private Boolean thamkhamCapToa;
    @Column(name = "THAMKHAM_GHICHU")
    private String thamkhamGhichu;
    @Column(name = "THAMKHAM_BIEN_CHUNG")
    private String thamkhamBienChung;

    public ThamKham() {
    }

    public ThamKham(Integer thamkhamMa) {
        this.thamkhamMa = thamkhamMa;
    }

    public Integer getThamkhamMa() {
        return thamkhamMa;
    }

    public void setThamkhamMa(Integer thamkhamMa) {
        this.thamkhamMa = thamkhamMa;
    }

    public Date getThamkhamNgaygio() {
        return thamkhamNgaygio;
    }

    public void setThamkhamNgaygio(Date thamkhamNgaygio) {
        this.thamkhamNgaygio = thamkhamNgaygio;
    }

    public String getThamkhamDgbenh() {
        return thamkhamDgbenh;
    }

    public void setThamkhamDgbenh(String thamkhamDgbenh) {
        this.thamkhamDgbenh = thamkhamDgbenh;
    }

    public Date getThamkhamNgaygiokt() {
        return thamkhamNgaygiokt;
    }

    public void setThamkhamNgaygiokt(Date thamkhamNgaygiokt) {
        this.thamkhamNgaygiokt = thamkhamNgaygiokt;
    }

    public Boolean getThamkhamClsang() {
        return thamkhamClsang;
    }

    public void setThamkhamClsang(Boolean thamkhamClsang) {
        this.thamkhamClsang = thamkhamClsang;
    }

    public Boolean getThamkhamThuthuat() {
        return thamkhamThuthuat;
    }

    public void setThamkhamThuthuat(Boolean thamkhamThuthuat) {
        this.thamkhamThuthuat = thamkhamThuthuat;
    }

    public Boolean getThamkhamDonlinh() {
        return thamkhamDonlinh;
    }

    public void setThamkhamDonlinh(Boolean thamkhamDonlinh) {
        this.thamkhamDonlinh = thamkhamDonlinh;
    }

    public Boolean getThamkhamDonve() {
        return thamkhamDonve;
    }

    public void setThamkhamDonve(Boolean thamkhamDonve) {
        this.thamkhamDonve = thamkhamDonve;
    }

    public String getThamkhamMaphieucv() {
        return thamkhamMaphieucv;
    }

    public void setThamkhamMaphieucv(String thamkhamMaphieucv) {
        this.thamkhamMaphieucv = thamkhamMaphieucv;
    }

    public String getThamkhamBenhsu() {
        return thamkhamBenhsu;
    }

    public void setThamkhamBenhsu(String thamkhamBenhsu) {
        this.thamkhamBenhsu = thamkhamBenhsu;
    }

    public String getThamkhamThamkham() {
        return thamkhamThamkham;
    }

    public void setThamkhamThamkham(String thamkhamThamkham) {
        this.thamkhamThamkham = thamkhamThamkham;
    }

    public String getThamkhamXutri() {
        return thamkhamXutri;
    }

    public void setThamkhamXutri(String thamkhamXutri) {
        this.thamkhamXutri = thamkhamXutri;
    }

    public String getThamkhamLamsang() {
        return thamkhamLamsang;
    }

    public void setThamkhamLamsang(String thamkhamLamsang) {
        this.thamkhamLamsang = thamkhamLamsang;
    }

    public Short getThamkhamCum() {
        return thamkhamCum;
    }

    public void setThamkhamCum(Short thamkhamCum) {
        this.thamkhamCum = thamkhamCum;
    }

    public Date getThamkhamNgaygiocn() {
        return thamkhamNgaygiocn;
    }

    public void setThamkhamNgaygiocn(Date thamkhamNgaygiocn) {
        this.thamkhamNgaygiocn = thamkhamNgaygiocn;
    }

    public String getThamkhamStatus() {
        return thamkhamStatus;
    }

    public void setThamkhamStatus(String thamkhamStatus) {
        this.thamkhamStatus = thamkhamStatus;
    }

    public Date getThamkhamNgaytt() {
        return thamkhamNgaytt;
    }

    public void setThamkhamNgaytt(Date thamkhamNgaytt) {
        this.thamkhamNgaytt = thamkhamNgaytt;
    }

//    public Collection<ClsKham> getClsKhamCollection() {
//        return clsKhamCollection;
//    }
//
//    public void setClsKhamCollection(Collection<ClsKham> clsKhamCollection) {
//        this.clsKhamCollection = clsKhamCollection;
//    }
    public DtDmNhanVien getThamkhamBacsi(boolean create) {
        if (create && thamkhamBacsi == null) {
            thamkhamBacsi = new DtDmNhanVien();
        }
        return thamkhamBacsi;
    }

    public DtDmNhanVien getThamkhamBacsi() {
        return thamkhamBacsi;
    }

    public void setThamkhamBacsi(DtDmNhanVien thamkhamBacsi) {
        this.thamkhamBacsi = thamkhamBacsi;
    }

    public DtDmBanKham getThamkhamChbankham(boolean create) {
        if (create && thamkhamChbankham == null) {
            thamkhamChbankham = new DtDmBanKham();
        }
        return thamkhamChbankham;
    }

    public DtDmBanKham getThamkhamChbankham() {
        return thamkhamChbankham;
    }

    public void setThamkhamChbankham(DtDmBanKham thamkhamChbankham) {
        this.thamkhamChbankham = thamkhamChbankham;
    }

    public DmBenhIcd getBenhicd10(boolean create) {
        if (create && benhicd10 == null) {
            benhicd10 = new DmBenhIcd();
        }
        return benhicd10;
    }

    public DmBenhIcd getBenhicd10() {
        return benhicd10;
    }

    public void setBenhicd10(DmBenhIcd benhicd10) {
        this.benhicd10 = benhicd10;
    }

    public DmKetQuaDieuTri getThamkhamKetqua(boolean create) {
        if (create && thamkhamKetqua == null) {
            thamkhamKetqua = new DmKetQuaDieuTri();
        }
        return thamkhamKetqua;
    }

    public DmKetQuaDieuTri getThamkhamKetqua() {
        return thamkhamKetqua;
    }

    public void setThamkhamKetqua(DmKetQuaDieuTri thamkhamKetqua) {
        this.thamkhamKetqua = thamkhamKetqua;
    }

    public DmDieuTri getThamkhamDieutri(boolean create) {
        if (create && thamkhamDieutri == null) {
            thamkhamDieutri = new DmDieuTri();
        }
        return thamkhamDieutri;
    }

    public DmDieuTri getThamkhamDieutri() {
        return thamkhamDieutri;
    }

    public void setThamkhamDieutri(DmDieuTri thamkhamDieutri) {
        this.thamkhamDieutri = thamkhamDieutri;
    }

    public DtDmNhanVien getThamkhamNhanviencn(boolean create) {
        if (create && thamkhamNhanviencn == null) {
            thamkhamNhanviencn = new DtDmNhanVien();
        }
        return thamkhamNhanviencn;
    }

    public DtDmNhanVien getThamkhamNhanviencn() {
        return thamkhamNhanviencn;
    }

    public void setThamkhamNhanviencn(DtDmNhanVien thamkhamNhanviencn) {
        this.thamkhamNhanviencn = thamkhamNhanviencn;
    }

    public TiepDon getTiepdonMa(boolean create) {
        if (create && tiepdonMa == null) {
            tiepdonMa = new TiepDon();
        }
        return tiepdonMa;
    }

    public TiepDon getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(TiepDon tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public DtDmBanKham getThamkhamBankham(boolean create) {
        if (create && thamkhamBankham == null) {
            thamkhamBankham = new DtDmBanKham();
        }
        return thamkhamBankham;
    }

    public DtDmBanKham getThamkhamBankham() {
        return thamkhamBankham;
    }

    public void setThamkhamBankham(DtDmBanKham thamkhamBankham) {
        this.thamkhamBankham = thamkhamBankham;
    }
//    public Collection<ToaLinhKham> getToaLinhKhamCollection() {
//        return toaLinhKhamCollection;
//    }
//
//    public void setToaLinhKhamCollection(Collection<ToaLinhKham> toaLinhKhamCollection) {
//        this.toaLinhKhamCollection = toaLinhKhamCollection;
//    }

//    public Collection<ToaThuocKham> getToaThuocKhamCollection() {
//        return toaThuocKhamCollection;
//    }
//
//    public void setToaThuocKhamCollection(Collection<ToaThuocKham> toaThuocKhamCollection) {
//        this.toaThuocKhamCollection = toaThuocKhamCollection;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection() {
//        return thuocPhongKhamCollection;
//    }
//
//    public void setThuocPhongKhamCollection(Collection<ThuocPhongKham> thuocPhongKhamCollection) {
//        this.thuocPhongKhamCollection = thuocPhongKhamCollection;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thamkhamMa != null ? thamkhamMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThamKham)) {
            return false;
        }
        ThamKham other = (ThamKham) object;
        if ((this.thamkhamMa == null && other.thamkhamMa != null) || (this.thamkhamMa != null && !this.thamkhamMa.equals(other.thamkhamMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.ThamKham[thamkhamMa=" + thamkhamMa + "]";
    }

    /**
     * @return the benhicd10phu1
     */
    public DmBenhIcd getBenhicd10phu1() {
        return benhicd10phu1;
    }



    /**
     * @param benhicd10phu1 the benhicd10phu1 to set
     */
    public void setBenhicd10phu1(DmBenhIcd benhicd10phu1) {
        this.benhicd10phu1 = benhicd10phu1;
    }

    /**
     * @return the benhicd10phu2
     */
    public DmBenhIcd getBenhicd10phu2() {
        return benhicd10phu2;
    }

    /**
     * @param benhicd10phu2 the benhicd10phu2 to set
     */
    public void setBenhicd10phu2(DmBenhIcd benhicd10phu2) {
        this.benhicd10phu2 = benhicd10phu2;
    }

    /**
     * @return the benhicd10phu3
     */
    public DmBenhIcd getBenhicd10phu3() {
        return benhicd10phu3;
    }

    /**
     * @param benhicd10phu3 the benhicd10phu3 to set
     */
    public void setBenhicd10phu3(DmBenhIcd benhicd10phu3) {
        this.benhicd10phu3 = benhicd10phu3;
    }

    /**
     * @return the benhicd10phu4
     */
    public DmBenhIcd getBenhicd10phu4() {
        return benhicd10phu4;
    }

    /**
     * @param benhicd10phu4 the benhicd10phu4 to set
     */
    public void setBenhicd10phu4(DmBenhIcd benhicd10phu4) {
        this.benhicd10phu4 = benhicd10phu4;
    }

    /**
     * @return the benhicd10phu5
     */
    public DmBenhIcd getBenhicd10phu5() {
        return benhicd10phu5;
    }

    /**
     * @param benhicd10phu5 the benhicd10phu5 to set
     */
    public void setBenhicd10phu5(DmBenhIcd benhicd10phu5) {
        this.benhicd10phu5 = benhicd10phu5;
    }

     public DmBenhIcd getBenhicd10phu1(boolean create) {
       if (create && benhicd10phu1 == null) {
            benhicd10phu1 = new DmBenhIcd();
        }
        return benhicd10phu1;
    }


      public DmBenhIcd getBenhicd10phu5(boolean create) {
       if (create && benhicd10phu5 == null) {
            benhicd10phu5 = new DmBenhIcd();
        }
        return benhicd10phu5;
    }


       public DmBenhIcd getBenhicd10phu4(boolean create) {
       if (create && benhicd10phu4 == null) {
            benhicd10phu4 = new DmBenhIcd();
        }
        return benhicd10phu4;
    }

        public DmBenhIcd getBenhicd10phu3(boolean create) {
       if (create && benhicd10phu3 == null) {
            benhicd10phu3 = new DmBenhIcd();
        }
        return benhicd10phu3;
    }

         public DmBenhIcd getBenhicd10phu2(boolean create) {
       if (create && benhicd10phu2 == null) {
            benhicd10phu2 = new DmBenhIcd();
        }
        return benhicd10phu2;
    }

    /**
     * @return the thamkhamNgaytaikham
     */
    public Date getThamkhamNgaytaikham() {
        return thamkhamNgaytaikham;
    }

    /**
     * @param thamkhamNgaytaikham the thamkhamNgaytaikham to set
     */
    public void setThamkhamNgaytaikham(Date thamkhamNgaytaikham) {
        this.thamkhamNgaytaikham = thamkhamNgaytaikham;
    }

    /**
     * @return the thamkhamChuyenSoLieuVaoNoiTru
     */
    public Boolean getThamkhamChuyenSoLieuVaoNoiTru() {
        return thamkhamChuyenSoLieuVaoNoiTru;
    }

    /**
     * @param thamkhamChuyenSoLieuVaoNoiTru the thamkhamChuyenSoLieuVaoNoiTru to set
     */
    public void setThamkhamChuyenSoLieuVaoNoiTru(Boolean thamkhamChuyenSoLieuVaoNoiTru) {
        this.thamkhamChuyenSoLieuVaoNoiTru = thamkhamChuyenSoLieuVaoNoiTru;
    }

    /**
     * @return the thamkhamCapToa
     */
    public Boolean getThamkhamCapToa() {
        return thamkhamCapToa;
    }

    /**
     * @param thamkhamCapToa the thamkhamCapToa to set
     */
    public void setThamkhamCapToa(Boolean thamkhamCapToa) {
        this.thamkhamCapToa = thamkhamCapToa;
    }
    
    public String getThamkhamGhichu() {
        return thamkhamGhichu;
    }

    public void setThamkhamGhichu(String thamkhamGhichu) {
        this.thamkhamGhichu = thamkhamGhichu;
    }

    public String getThamkhamBienChung() {
        return thamkhamBienChung;
    }

    public void setThamkhamBienChung(String thamkhamBienChung) {
        this.thamkhamBienChung = thamkhamBienChung;
    }

}


