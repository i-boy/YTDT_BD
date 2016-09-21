/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

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
@Table(name = "DT_DM_CLS_BANG_GIA")
@NamedQueries({})
public class DtDmClsBangGia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CLS_BANG_GIA")
    @SequenceGenerator(name = "DT_DM_CLS_BANG_GIA", sequenceName = "DT_DM_CLS_BANG_GIA_DTDMCLSBG_M", allocationSize = 1)
    @Column(name = "DTDMCLSBG_MASO", nullable = false)
    private Integer dtdmclsbgMaso;
    @Column(name = "DTDMCLSBG_MA", nullable = false)
    private String dtdmclsbgMa;
    @Column(name = "DTDMCLSBG_MA2")
    private String dtdmclsbgMa2;
    @Column(name = "DTDMCLSBG_PHANBIET")
    private String dtdmclsbgPhanBiet;
    @Column(name = "DMCLSBG_LOAI")
    private String dtdmclsbgLoai;
    @Column(name = "DTDMCLSBG_DIENGIAI", nullable = false)
    private String dtdmclsbgDiengiai;
    @Column(name = "DTDMCLSBG_PHANDV")
    private Double dtdmclsbgPhandv;
    @Column(name = "DTDMCLSBG_DONGIA")
    private Double dtdmclsbgDongia;
    @Column(name = "DTDMCLSBG_DONGIAMP")
    private Double dtdmclsbgDongiamp;
    @Column(name = "DTDMCLSBG_DONGIABH")
    private Double dtdmclsbgDongiabh;
    @Column(name = "DTDMCLSBG_DONGIA2")
    private Double dtdmclsbgDongia2;
    @Column(name = "DTDMCLSBG_DONGIA3")
    private Double dtdmclsbgDongia3;
    @Column(name = "DTDMCLSBG_DONGIAYC")
    private Double dtdmclsbgDongiayc;
    @Column(name = "DTDMCLSBG_DONGIANN")
    private Double dtdmclsbgDongiann;
    @Column(name = "DTDMCLSBG_KHACTUYEN")
    private Short dtdmclsbgKhactuyen;
    @Column(name = "DTDMCLSBG_VATTU")
    private Short dtdmclsbgVattu;
    @Column(name = "DTDMCLSBG_BAOTRI")
    private Short dtdmclsbgBaotri;
    @Column(name = "DTDMCLSBG_DIENNUOC")
    private Short dtdmclsbgDiennuoc;
    @Column(name = "DTDMCLSBG_CONG")
    private Short dtdmclsbgCong;
    @Column(name = "DTDMCLSBG_OXY")
    private Short dtdmclsbgOxy;
    @Column(name = "DTDMCLSBG_NGAYGIOCN")
    private Double dtdmclsbgNgaygiocn;
    @Column(name = "DTDMCLSBG_CHON")
    private Boolean dtdmclsbgChon;
    @Column(name = "DMCLSBG_NDM")
    private Boolean dtdmclsbgNDM;
    @Column(name = "DMCLSBG_MIEN")
    private Boolean dtdmclsbgMien;
    @Column(name = "DMCLSBG_XN")
    private Boolean dtdmclsbgXn;
//    @OneToMany(mappedBy = "thuocnoitruMaphong")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruMaphong1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
//    @OneToMany(mappedBy = "clsmoMahang")
//    private Collection<ClsMo> clsMoCollection;
//    @OneToMany(mappedBy = "clsmoMahang1")
//    private Collection<ClsMo> clsMoCollection1;
//    @OneToMany(mappedBy = "clskhamMahang")
//    private Collection<ClsKham> clsKhamCollection;
//    @OneToMany(mappedBy = "clskhamMahang1")
//    private Collection<ClsKham> clsKhamCollection1;
    @JoinColumn(name = "DTDMCLSBG_MALOAI", referencedColumnName = "DTDMCLS_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCls dtdmclsbgMaloai;
    @JoinColumn(name = "DTDMCLSBG_PHANLOAI", referencedColumnName = "DTDMCLS_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCls dtdmclsbgPhanloai;
    @Column(name = "DTDMCLSBG_CDHA")
    private String dtdmclsbgCdha;
    @Column(name = "DMCLSBG_SOML")
    private Integer dmclsbgSoml;
    @JoinColumn(name = "DMCLSBG_LOAIPTTT", referencedColumnName = "DTDMLOAIPT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiPhauThuat dtdmclsbgLoaipttt;

    public DtDmLoaiPhauThuat getDtdmclsbgLoaipttt() {
        return dtdmclsbgLoaipttt;
    }

    public void setDtdmclsbgLoaipttt(DtDmLoaiPhauThuat dtdmclsbgLoaipttt) {
        this.dtdmclsbgLoaipttt = dtdmclsbgLoaipttt;
    }

    public DtDmLoaiPhauThuat getDtdmclsbgLoaipttt(boolean create) {
         if(create && dtdmclsbgLoaipttt == null) dtdmclsbgLoaipttt = new DtDmLoaiPhauThuat();
        return dtdmclsbgLoaipttt;
    }

    public DtDmClsBangGia() {
        init();
    }

    public DtDmClsBangGia(Integer dtdmclsbgMaso) {
        this.dtdmclsbgMaso = dtdmclsbgMaso;
        init();
    }

    public DtDmClsBangGia(Integer dtdmclsbgMaso, String dtdmclsbgMa, String dtdmclsbgDiengiai) {
        this.dtdmclsbgMaso = dtdmclsbgMaso;
        this.dtdmclsbgMa = dtdmclsbgMa;
        this.dtdmclsbgDiengiai = dtdmclsbgDiengiai;
        init();
    }

    public Integer getDtdmclsbgMaso() {
        return dtdmclsbgMaso;
    }

    public void setDtdmclsbgMaso(Integer dtdmclsbgMaso) {
        this.dtdmclsbgMaso = dtdmclsbgMaso;
    }

    public String getDtdmclsbgMa() {
        return dtdmclsbgMa;
    }

    public void setDtdmclsbgMa(String dtdmclsbgMa) {
        this.dtdmclsbgMa = dtdmclsbgMa;
    }

    public String getDtdmclsbgMa2() {
        return dtdmclsbgMa2;
    }

    public void setDtdmclsbgMa2(String dtdmclsbgMa2) {
        this.dtdmclsbgMa2 = dtdmclsbgMa2;
    }

    public String getDtdmclsbgDiengiai() {
        return dtdmclsbgDiengiai;
    }

    public void setDtdmclsbgDiengiai(String dtdmclsbgDiengiai) {
        this.dtdmclsbgDiengiai = dtdmclsbgDiengiai;
    }

    public Double getDtdmclsbgPhandv() {
        return dtdmclsbgPhandv;
    }

    public void setDtdmclsbgPhandv(Double dtdmclsbgPhandv) {
        this.dtdmclsbgPhandv = dtdmclsbgPhandv;
    }

    public Double getDtdmclsbgDongia() {
        return dtdmclsbgDongia;
    }

    public void setDtdmclsbgDongia(Double dtdmclsbgDongia) {
        this.dtdmclsbgDongia = dtdmclsbgDongia;
    }

    public Double getDtdmclsbgDongiamp() {
        return dtdmclsbgDongiamp;
    }

    public void setDtdmclsbgDongiamp(Double dtdmclsbgDongiamp) {
        this.dtdmclsbgDongiamp = dtdmclsbgDongiamp;
    }

    public Double getDtdmclsbgDongiabh() {
        return dtdmclsbgDongiabh;
    }

    public void setDtdmclsbgDongiabh(Double dtdmclsbgDongiabh) {
        this.dtdmclsbgDongiabh = dtdmclsbgDongiabh;
    }

    public Double getDtdmclsbgDongia2() {
        return dtdmclsbgDongia2;
    }

    public void setDtdmclsbgDongia2(Double dtdmclsbgDongia2) {
        this.dtdmclsbgDongia2 = dtdmclsbgDongia2;
    }

    public Double getDtdmclsbgDongia3() {
        return dtdmclsbgDongia3;
    }

    public void setDtdmclsbgDongia3(Double dtdmclsbgDongia3) {
        this.dtdmclsbgDongia3 = dtdmclsbgDongia3;
    }

    public Double getDtdmclsbgDongiayc() {
        return dtdmclsbgDongiayc;
    }

    public void setDtdmclsbgDongiayc(Double dtdmclsbgDongiayc) {
        this.dtdmclsbgDongiayc = dtdmclsbgDongiayc;
    }

    public Double getDtdmclsbgDongiann() {
        return dtdmclsbgDongiann;
    }

    public void setDtdmclsbgDongiann(Double dtdmclsbgDongiann) {
        this.dtdmclsbgDongiann = dtdmclsbgDongiann;
    }

    public Short getDtdmclsbgKhactuyen() {
        return dtdmclsbgKhactuyen;
    }

    public void setDtdmclsbgKhactuyen(Short dtdmclsbgKhactuyen) {
        this.dtdmclsbgKhactuyen = dtdmclsbgKhactuyen;
    }

    public Short getDtdmclsbgVattu() {
        return dtdmclsbgVattu;
    }

    public void setDtdmclsbgVattu(Short dtdmclsbgVattu) {
        this.dtdmclsbgVattu = dtdmclsbgVattu;
    }

    public Short getDtdmclsbgBaotri() {
        return dtdmclsbgBaotri;
    }

    public void setDtdmclsbgBaotri(Short dtdmclsbgBaotri) {
        this.dtdmclsbgBaotri = dtdmclsbgBaotri;
    }

    public Short getDtdmclsbgDiennuoc() {
        return dtdmclsbgDiennuoc;
    }

    public void setDtdmclsbgDiennuoc(Short dtdmclsbgDiennuoc) {
        this.dtdmclsbgDiennuoc = dtdmclsbgDiennuoc;
    }

    public Short getDtdmclsbgCong() {
        return dtdmclsbgCong;
    }

    public void setDtdmclsbgCong(Short dtdmclsbgCong) {
        this.dtdmclsbgCong = dtdmclsbgCong;
    }

    public Short getDtdmclsbgOxy() {
        return dtdmclsbgOxy;
    }

    public void setDtdmclsbgOxy(Short dtdmclsbgOxy) {
        this.dtdmclsbgOxy = dtdmclsbgOxy;
    }

    public Double getDtdmclsbgNgaygiocn() {
        return dtdmclsbgNgaygiocn;
    }

    public void setDtdmclsbgNgaygiocn(Double dtdmclsbgNgaygiocn) {
        this.dtdmclsbgNgaygiocn = dtdmclsbgNgaygiocn;
    }

    public Boolean getDtdmclsbgChon() {
        return dtdmclsbgChon;
    }

    public void setDtdmclsbgChon(Boolean dtdmclsbgChon) {
        this.dtdmclsbgChon = dtdmclsbgChon;
    }

    public Boolean getDtdmclsbgNDM() {
        return dtdmclsbgNDM;
    }

    public void setDtdmclsbgNDM(Boolean dtdmclsbgNDM) {
        this.dtdmclsbgNDM = dtdmclsbgNDM;
    }

    public Boolean getDtdmclsbgMien() {
        return dtdmclsbgMien;
    }

    public void setDtdmclsbgMien(Boolean dtdmclsbgMien) {
        this.dtdmclsbgMien = dtdmclsbgMien;
    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection() {
//        return thuocNoiTruCollection;
//    }
//
//    public void setThuocNoiTruCollection(Collection<ThuocNoiTru> thuocNoiTruCollection) {
//        this.thuocNoiTruCollection = thuocNoiTruCollection;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection1() {
//        return thuocNoiTruCollection1;
//    }
//
//    public void setThuocNoiTruCollection1(Collection<ThuocNoiTru> thuocNoiTruCollection1) {
//        this.thuocNoiTruCollection1 = thuocNoiTruCollection1;
//    }

//    public Collection<ClsMo> getClsMoCollection() {
//        return clsMoCollection;
//    }
//
//    public void setClsMoCollection(Collection<ClsMo> clsMoCollection) {
//        this.clsMoCollection = clsMoCollection;
//    }

//    public Collection<ClsMo> getClsMoCollection1() {
//        return clsMoCollection1;
//    }
//
//    public void setClsMoCollection1(Collection<ClsMo> clsMoCollection1) {
//        this.clsMoCollection1 = clsMoCollection1;
//    }

//    public Collection<ClsKham> getClsKhamCollection() {
//        return clsKhamCollection;
//    }
//
//    public void setClsKhamCollection(Collection<ClsKham> clsKhamCollection) {
//        this.clsKhamCollection = clsKhamCollection;
//    }

//    public Collection<ClsKham> getClsKhamCollection1() {
//        return clsKhamCollection1;
//    }
//
//    public void setClsKhamCollection1(Collection<ClsKham> clsKhamCollection1) {
//        this.clsKhamCollection1 = clsKhamCollection1;
//    }
    public DtDmCls getDtdmclsbgMaloai(boolean create) {
        if (create && dtdmclsbgMaloai == null) {
            dtdmclsbgMaloai = new DtDmCls();
        }
        return dtdmclsbgMaloai;
    }

    public DtDmCls getDtdmclsbgMaloai() {
        return dtdmclsbgMaloai;
    }

    public void setDtdmclsbgMaloai(DtDmCls dtdmclsbgMaloai) {
        this.dtdmclsbgMaloai = dtdmclsbgMaloai;
    }

    public DtDmCls getDtdmclsbgPhanloai(boolean create) {
        if (create && dtdmclsbgPhanloai == null) {
            dtdmclsbgPhanloai = new DtDmCls();
        }
        return dtdmclsbgPhanloai;
    }

    public DtDmCls getDtdmclsbgPhanloai() {
        return dtdmclsbgPhanloai;
    }

    public void setDtdmclsbgPhanloai(DtDmCls dtdmclsbgPhanloai) {
        this.dtdmclsbgPhanloai = dtdmclsbgPhanloai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmclsbgMaso != null ? dtdmclsbgMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmClsBangGia)) {
            return false;
        }
        DtDmClsBangGia other = (DtDmClsBangGia) object;
        if ((this.dtdmclsbgMaso == null && other.dtdmclsbgMaso != null) || (this.dtdmclsbgMaso != null && !this.dtdmclsbgMaso.equals(other.dtdmclsbgMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmClsBangGia[dtdmclsbgMaso=" + dtdmclsbgMaso + "]";
    }

    private void init(){
        this.dtdmclsbgDongia = 0.0;
        this.dtdmclsbgPhandv = 0.0;
        this.dtdmclsbgDongiabh = 0.0;
        this.dtdmclsbgDongiamp = 0.0;
        this.dtdmclsbgDongiayc = 0.0;
    }

    public String getDtdmclsbgPhanBiet() {
        return dtdmclsbgPhanBiet;
    }

    public void setDtdmclsbgPhanBiet(String dtdmclsbgPhanBiet) {
        this.dtdmclsbgPhanBiet = dtdmclsbgPhanBiet;
    }

    public String getDtdmclsbgLoai() {
        return dtdmclsbgLoai;
    }

    public void setDtdmclsbgLoai(String dtdmclsbgLoai) {
        this.dtdmclsbgLoai = dtdmclsbgLoai;
    }
    
    public Boolean getDtdmclsbgXn() {
        return dtdmclsbgXn;
    }

    public void setDtdmclsbgXn(Boolean dtdmclsbgXn) {
        this.dtdmclsbgXn = dtdmclsbgXn;
    }

    public String getDtdmclsbgCdha() {
        return dtdmclsbgCdha;
    }

    public void setDtdmclsbgCdha(String dtdmclsbgCdha) {
        this.dtdmclsbgCdha = dtdmclsbgCdha;
    }
    public Integer getDmclsbgSoml() {
        return dmclsbgSoml;
    }

    public void setDmclsbgSoml(Integer dmclsbgSoml) {
        this.dmclsbgSoml = dmclsbgSoml;
    }
    
}


