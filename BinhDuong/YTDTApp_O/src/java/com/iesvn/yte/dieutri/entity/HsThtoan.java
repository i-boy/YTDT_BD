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
@Table(name = "HS_THTOAN")
@NamedQueries({})
public class HsThtoan implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "HSTHTOAN_MA", nullable = false)
    private String hsthtoanMa;
    @Column(name = "HSTHTOAN_TAMUNG")
    private Double hsthtoanTamung;
    @Column(name = "HSTHTOAN_HOANUNG")
    private Double hsthtoanHoanung;
    @Column(name = "HSTHTOAN_HOANTHU")
    private Double hsthtoanHoanthu;
    @Column(name = "HSTHTOAN_TONGCHI")
    private Double hsthtoanTongchi;
    @Column(name = "HSTHTOAN_DM")
    private Double hsthtoanDm;
    @Column(name = "HSTHTOAN_NDM")
    private Double hsthtoanNdm;
    @Column(name = "HSTHTOAN_YEUCAU")
    private Double hsthtoanYeucau;
    @Column(name = "HSTHTOAN_MIENPHONG")
    private Double hsthtoanMienphong;
    @Column(name = "HSTHTOAN_MIENTHUOC")
    private Double hsthtoanMienthuoc;
    @Column(name = "HSTHTOAN_NGANSACH")
    private Double hsthtoanNgansach;
    @Column(name = "HSTHTOAN_KHONGTHU")
    private Double hsthtoanKhongthu;
    @Column(name = "HSTHTOAN_BHYT")
    private Double hsthtoanBhyt;
    @Column(name = "HSTHTOAN_BHXH")
    private Double hsthtoanBhxh;
    @Column(name = "HSTHTOAN_TYLEBH")
    private Short hsthtoanTylebh;
    @Column(name = "HSTHTOAN_BNTRA")
    private Double hsthtoanBntra;
    @Column(name = "HSTHTOAN_MIENTHUOCLAO")
    private Double hsthtoanMienthuoclao;
    @Column(name = "HSTHTOAN_MIENTE")
    private Double hsthtoanMiente;
    @Column(name = "HSTHTOAN_MIENDT")
    private Double hsthtoanMiendt;
    @Column(name = "HSTHTOAN_MIENMAU")
    private Double hsthtoanMienmau;
    @Column(name = "HSTHTOAN_XETGIAM")
    private Double hsthtoanXetgiam;
    @Column(name = "HSTHTOAN_THATTHU")
    private Double hsthtoanThatthu;
    @Column(name = "HSTHTOAN_LYDOTT")
    private String hsthtoanLydott;
    @Column(name = "HSTHTOAN_THTOAN")
    
    private Double hsthtoanThtoan;
    @Temporal(TemporalType.TIMESTAMP)    
    @Column(name = "HSTHTOAN_NGAYTT")
   
    private Date hsthtoanNgaytt;
    @Column(name = "HSTHTOAN_NGAYD")
    @Temporal(TemporalType.DATE)
    private Date hsthtoanNgayd;
    @Column(name = "HSTHTOAN_NGAYC")
    @Temporal(TemporalType.DATE)
    private Date hsthtoanNgayc;
    @Column(name = "HSTHTOAN_SOKHOA")
    private Short hsthtoanSokhoa;
   
    @Column(name = "HSTHTOAN_CLS")
    private Double hsthtoanCls;
    @Column(name = "HSTHTOAN_CLSNDM")
    private Double hsthtoanClsndm;
    @Column(name = "HSTHTOAN_THUOC")
    private Double hsthtoanThuoc;
    @Column(name = "HSTHTOAN_THUOCNDM")
    private Double hsthtoanThuocndm;
    @Column(name = "HSTHTOAN_VTTH")
    private Double hsthtoanVtth;
    @Column(name = "HSTHTOAN_VTTHNDM")
    private Double hsthtoanVtthndm;
    @Column(name = "HSTHTOAN_PHAUTHUAT")
    private Double hsthtoanPhauthuat;
    @Column(name = "HSTHTOAN_PHAUTHUATNDM")
    private Double hsthtoanPhauthuatndm;
    @Column(name = "HSTHTOAN_PHONG")
    private Double hsthtoanPhong;
    @Column(name = "HSTHTOAN_PHONGNDM")
    private Double hsthtoanPhongndm;
    @Column(name = "HSTHTOAN_DIENMIEN")
    private Boolean hsthtoanDienmien;
    @Column(name = "HSTHTOAN_CONGKHAM")
    private Double hsthtoanCongkham;
    @Column(name = "HSTHTOAN_DATT")
    private Boolean hsthtoanDatt;
    @Column(name = "HSTHTOAN_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsthtoanNgaygiocn;
    @Column(name = "HSTHTOAN_KYHIEU")
    private String hsthtoanKyhieu;
    @Column(name = "HSTHTOAN_QUYEN")
    private String hsthtoanQuyen;
    @Column(name = "HSTHTOAN_BIENLAI")
    private String hsthtoanBienlai;
    @Column(name = "HSTHTOAN_BOVIEN")
    private Boolean hsthtoanBovien;
    @Column(name = "HSTHTOAN_NOIDUNGPT")
    private String hsthtoanNoidungpt;
//    @OneToMany(mappedBy = "hoanthuMaphieu")
//    private Collection<HoanThu> hoanThuCollection;
//    @OneToMany(mappedBy = "hoanthuMaphieu1")
//    private Collection<HoanThu> hoanThuCollection1;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSTHTOAN_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hsthtoanKhoa;
    @JoinColumn(name = "HSTHTOAN_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsthtoanNhanviencn;
    @JoinColumn(name = "HSTHTOAN_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsthtoanThungan;
    
    @JoinColumn(name = "HSTHTOAN_CUM", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum hsthtoanCum;
     
    @Column(name = "HSTHTOAN_MAU")
    private Double hsthtoanMau;

    @Column(name = "HSTHTOAN_XNTDCN")
    private Double hsthtoanXntdcn;

    @Column(name = "HSTHTOAN_CDHA")
    private Double hsthtoanCdha;

    @Column(name = "HSTHTOAN_DVKT_TT")
    private Double hsthtoanDvkttt;

    @Column(name = "HSTHTOAN_DV_KTC")
    private Double hsthtoanDvktc;

    @Column(name = "HSTHTOAN_CP_VC")
    private Double hsthtoanCpvc;

    @Column(name = "HSTHTOAN_LYDOTHANHTOAN")
    private String hsthtoanLidothanhtoan;
    @Column(name = "HSTHTOAN_LANIN")
    private String hsthtoanLanin;
    
    public HsThtoan() {
    }

    public HsThtoan(String hsthtoanMa) {
        this.hsthtoanMa = hsthtoanMa;
    }

    public String getHsthtoanMa() {
        return hsthtoanMa;
    }

    public void setHsthtoanMa(String hsthtoanMa) {
        this.hsthtoanMa = hsthtoanMa;
    }

    public Double getHsthtoanTamung() {
        return hsthtoanTamung;
    }

    public void setHsthtoanTamung(Double hsthtoanTamung) {
        this.hsthtoanTamung = hsthtoanTamung;
    }

    public Double getHsthtoanHoanung() {
        return hsthtoanHoanung;
    }

    public void setHsthtoanHoanung(Double hsthtoanHoanung) {
        this.hsthtoanHoanung = hsthtoanHoanung;
    }

    public Double getHsthtoanHoanthu() {
        return hsthtoanHoanthu;
    }

    public void setHsthtoanHoanthu(Double hsthtoanHoanthu) {
        this.hsthtoanHoanthu = hsthtoanHoanthu;
    }

    public Double getHsthtoanTongchi() {
        return hsthtoanTongchi;
    }

    public void setHsthtoanTongchi(Double hsthtoanTongchi) {
        this.hsthtoanTongchi = hsthtoanTongchi;
    }

    public Double getHsthtoanDm() {
        return hsthtoanDm;
    }

    public void setHsthtoanDm(Double hsthtoanDm) {
        this.hsthtoanDm = hsthtoanDm;
    }

    public Double getHsthtoanNdm() {
        return hsthtoanNdm;
    }

    public void setHsthtoanNdm(Double hsthtoanNdm) {
        this.hsthtoanNdm = hsthtoanNdm;
    }

    public Double getHsthtoanYeucau() {
        return hsthtoanYeucau;
    }

    public void setHsthtoanYeucau(Double hsthtoanYeucau) {
        this.hsthtoanYeucau = hsthtoanYeucau;
    }

    public Double getHsthtoanMienphong() {
        return hsthtoanMienphong;
    }

    public void setHsthtoanMienphong(Double hsthtoanMienphong) {
        this.hsthtoanMienphong = hsthtoanMienphong;
    }

    public Double getHsthtoanMienthuoc() {
        return hsthtoanMienthuoc;
    }

    public void setHsthtoanMienthuoc(Double hsthtoanMienthuoc) {
        this.hsthtoanMienthuoc = hsthtoanMienthuoc;
    }

    public Double getHsthtoanNgansach() {
        return hsthtoanNgansach;
    }

    public void setHsthtoanNgansach(Double hsthtoanNgansach) {
        this.hsthtoanNgansach = hsthtoanNgansach;
    }

    public Double getHsthtoanKhongthu() {
        return hsthtoanKhongthu;
    }

    public void setHsthtoanKhongthu(Double hsthtoanKhongthu) {
        this.hsthtoanKhongthu = hsthtoanKhongthu;
    }

    public Double getHsthtoanBhyt() {
        return hsthtoanBhyt;
    }

    public void setHsthtoanBhyt(Double hsthtoanBhyt) {
        this.hsthtoanBhyt = hsthtoanBhyt;
    }

    public Double getHsthtoanBhxh() {
        return hsthtoanBhxh;
    }

    public void setHsthtoanBhxh(Double hsthtoanBhxh) {
        this.hsthtoanBhxh = hsthtoanBhxh;
    }

    public Short getHsthtoanTylebh() {
        return hsthtoanTylebh;
    }

    public void setHsthtoanTylebh(Short hsthtoanTylebh) {
        this.hsthtoanTylebh = hsthtoanTylebh;
    }

    public Double getHsthtoanBntra() {
        return hsthtoanBntra;
    }

    public void setHsthtoanBntra(Double hsthtoanBntra) {
        this.hsthtoanBntra = hsthtoanBntra;
    }

    public Double getHsthtoanMienthuoclao() {
        return hsthtoanMienthuoclao;
    }

    public void setHsthtoanMienthuoclao(Double hsthtoanMienthuoclao) {
        this.hsthtoanMienthuoclao = hsthtoanMienthuoclao;
    }

    public Double getHsthtoanMiente() {
        return hsthtoanMiente;
    }

    public void setHsthtoanMiente(Double hsthtoanMiente) {
        this.hsthtoanMiente = hsthtoanMiente;
    }

    public Double getHsthtoanMiendt() {
        return hsthtoanMiendt;
    }

    public void setHsthtoanMiendt(Double hsthtoanMiendt) {
        this.hsthtoanMiendt = hsthtoanMiendt;
    }

    public Double getHsthtoanMienmau() {
        return hsthtoanMienmau;
    }

    public void setHsthtoanMienmau(Double hsthtoanMienmau) {
        this.hsthtoanMienmau = hsthtoanMienmau;
    }

    public Double getHsthtoanXetgiam() {
        return hsthtoanXetgiam;
    }

    public void setHsthtoanXetgiam(Double hsthtoanXetgiam) {
        this.hsthtoanXetgiam = hsthtoanXetgiam;
    }

    public Double getHsthtoanThatthu() {
        return hsthtoanThatthu;
    }

    public void setHsthtoanThatthu(Double hsthtoanThatthu) {
        this.hsthtoanThatthu = hsthtoanThatthu;
    }

    public String getHsthtoanLydott() {
        return hsthtoanLydott;
    }

    public void setHsthtoanLydott(String hsthtoanLydott) {
        this.hsthtoanLydott = hsthtoanLydott;
    }

    public Double getHsthtoanThtoan() {
        return hsthtoanThtoan;
    }

    public void setHsthtoanThtoan(Double hsthtoanThtoan) {
        this.hsthtoanThtoan = hsthtoanThtoan;
    }

    public Date getHsthtoanNgaytt() {
        return hsthtoanNgaytt;
    }

    public void setHsthtoanNgaytt(Date hsthtoanNgaytt) {
        this.hsthtoanNgaytt = hsthtoanNgaytt;
    }

    public Date getHsthtoanNgayd() {
        return hsthtoanNgayd;
    }

    public void setHsthtoanNgayd(Date hsthtoanNgayd) {
        this.hsthtoanNgayd = hsthtoanNgayd;
    }

    public Date getHsthtoanNgayc() {
        return hsthtoanNgayc;
    }

    public void setHsthtoanNgayc(Date hsthtoanNgayc) {
        this.hsthtoanNgayc = hsthtoanNgayc;
    }

    public Short getHsthtoanSokhoa() {
        return hsthtoanSokhoa;
    }

    public void setHsthtoanSokhoa(Short hsthtoanSokhoa) {
        this.hsthtoanSokhoa = hsthtoanSokhoa;
    }

    public DtDmCum getHsthtoanCum() {
        return hsthtoanCum;
    }

    public void setHsthtoanCum(DtDmCum hsthtoanCum) {
        this.hsthtoanCum = hsthtoanCum;
    }

    public Double getHsthtoanCls() {
        return hsthtoanCls;
    }

    public void setHsthtoanCls(Double hsthtoanCls) {
        this.hsthtoanCls = hsthtoanCls;
    }

    public Double getHsthtoanClsndm() {
        return hsthtoanClsndm;
    }

    public void setHsthtoanClsndm(Double hsthtoanClsndm) {
        this.hsthtoanClsndm = hsthtoanClsndm;
    }

    public Double getHsthtoanThuoc() {
        return hsthtoanThuoc;
    }

    public void setHsthtoanThuoc(Double hsthtoanThuoc) {
        this.hsthtoanThuoc = hsthtoanThuoc;
    }

    public Double getHsthtoanThuocndm() {
        return hsthtoanThuocndm;
    }

    public void setHsthtoanThuocndm(Double hsthtoanThuocndm) {
        this.hsthtoanThuocndm = hsthtoanThuocndm;
    }

    public Double getHsthtoanVtth() {
        return hsthtoanVtth;
    }

    public void setHsthtoanVtth(Double hsthtoanVtth) {
        this.hsthtoanVtth = hsthtoanVtth;
    }

    public Double getHsthtoanVtthndm() {
        return hsthtoanVtthndm;
    }

    public void setHsthtoanVtthndm(Double hsthtoanVtthndm) {
        this.hsthtoanVtthndm = hsthtoanVtthndm;
    }

    public Double getHsthtoanPhauthuat() {
        return hsthtoanPhauthuat;
    }

    public void setHsthtoanPhauthuat(Double hsthtoanPhauthuat) {
        this.hsthtoanPhauthuat = hsthtoanPhauthuat;
    }

    public Double getHsthtoanPhauthuatndm() {
        return hsthtoanPhauthuatndm;
    }

    public void setHsthtoanPhauthuatndm(Double hsthtoanPhauthuatndm) {
        this.hsthtoanPhauthuatndm = hsthtoanPhauthuatndm;
    }

    public Double getHsthtoanPhong() {
        return hsthtoanPhong;
    }

    public void setHsthtoanPhong(Double hsthtoanPhong) {
        this.hsthtoanPhong = hsthtoanPhong;
    }

    public Double getHsthtoanPhongndm() {
        return hsthtoanPhongndm;
    }

    public void setHsthtoanPhongndm(Double hsthtoanPhongndm) {
        this.hsthtoanPhongndm = hsthtoanPhongndm;
    }

    public Boolean getHsthtoanDienmien() {
        return hsthtoanDienmien;
    }

    public void setHsthtoanDienmien(Boolean hsthtoanDienmien) {
        this.hsthtoanDienmien = hsthtoanDienmien;
    }

    public Double getHsthtoanCongkham() {
        return hsthtoanCongkham;
    }

    public void setHsthtoanCongkham(Double hsthtoanCongkham) {
        this.hsthtoanCongkham = hsthtoanCongkham;
    }

    public Boolean getHsthtoanDatt() {
        return hsthtoanDatt;
    }

    public void setHsthtoanDatt(Boolean hsthtoanDatt) {
        this.hsthtoanDatt = hsthtoanDatt;
    }

    public Date getHsthtoanNgaygiocn() {
        return hsthtoanNgaygiocn;
    }

    public void setHsthtoanNgaygiocn(Date hsthtoanNgaygiocn) {
        this.hsthtoanNgaygiocn = hsthtoanNgaygiocn;
    }

    public String getHsthtoanKyhieu() {
        return hsthtoanKyhieu;
    }

    public void setHsthtoanKyhieu(String hsthtoanKyhieu) {
        this.hsthtoanKyhieu = hsthtoanKyhieu;
    }

    public String getHsthtoanQuyen() {
        return hsthtoanQuyen;
    }

    public void setHsthtoanQuyen(String hsthtoanQuyen) {
        this.hsthtoanQuyen = hsthtoanQuyen;
    }

    public String getHsthtoanBienlai() {
        return hsthtoanBienlai;
    }

    public void setHsthtoanBienlai(String hsthtoanBienlai) {
        this.hsthtoanBienlai = hsthtoanBienlai;
    }

    public Boolean getHsthtoanBovien() {
        return hsthtoanBovien;
    }

    public void setHsthtoanBovien(Boolean hsthtoanBovien) {
        this.hsthtoanBovien = hsthtoanBovien;
    }

    public String getHsthtoanNoidungpt() {
        return hsthtoanNoidungpt;
    }

    public void setHsthtoanNoidungpt(String hsthtoanNoidungpt) {
        this.hsthtoanNoidungpt = hsthtoanNoidungpt;
    }

//    public Collection<HoanThu> getHoanThuCollection() {
//        return hoanThuCollection;
//    }
//
//    public void setHoanThuCollection(Collection<HoanThu> hoanThuCollection) {
//        this.hoanThuCollection = hoanThuCollection;
//    }

//    public Collection<HoanThu> getHoanThuCollection1() {
//        return hoanThuCollection1;
//    }
//
//    public void setHoanThuCollection1(Collection<HoanThu> hoanThuCollection1) {
//        this.hoanThuCollection1 = hoanThuCollection1;
//    }
    public Hsba getHsbaSovaovien(boolean create) {
        if(create && getHsbaSovaovien() == null) setHsbaSovaovien(new Hsba());
        return  getHsbaSovaovien();
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

    public DmKhoa getHsthtoanKhoa(boolean create) {
        if(create && getHsthtoanKhoa() == null) setHsthtoanKhoa(new DmKhoa());
        return  getHsthtoanKhoa();
    }

    public DmKhoa getHsthtoanKhoa() {
        return hsthtoanKhoa;
    }

    public void setHsthtoanKhoa(DmKhoa hsthtoanKhoa) {
        this.hsthtoanKhoa = hsthtoanKhoa;
    }

    public DtDmNhanVien getHsthtoanNhanviencn(boolean create) {
        if(create && getHsthtoanNhanviencn() == null) setHsthtoanNhanviencn(new DtDmNhanVien());
        return  getHsthtoanNhanviencn();
    }

    public DtDmNhanVien getHsthtoanNhanviencn() {
        return hsthtoanNhanviencn;
    }

    public void setHsthtoanNhanviencn(DtDmNhanVien hsthtoanNhanviencn) {
        this.hsthtoanNhanviencn = hsthtoanNhanviencn;
    }

    public DtDmNhanVien getHsthtoanThungan(boolean create) {
        if(create && getHsthtoanThungan() == null) setHsthtoanThungan(new DtDmNhanVien());
        return  getHsthtoanThungan();
    }

    public DtDmNhanVien getHsthtoanThungan() {
        return hsthtoanThungan;
    }

    public void setHsthtoanThungan(DtDmNhanVien hsthtoanThungan) {
        this.hsthtoanThungan = hsthtoanThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHsthtoanMa() != null ? getHsthtoanMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsThtoan)) {
            return false;
        }
        HsThtoan other = (HsThtoan) object;
        if ((this.getHsthtoanMa() == null && other.getHsthtoanMa() != null) || (this.getHsthtoanMa() != null && !this.hsthtoanMa.equals(other.hsthtoanMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsThtoan[hsthtoanMa=" + getHsthtoanMa() + "]";
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
     * @return the hsthtoanMau
     */
    public Double getHsthtoanMau() {
        return hsthtoanMau;
    }

    /**
     * @param hsthtoanMau the hsthtoanMau to set
     */
    public void setHsthtoanMau(Double hsthtoanMau) {
        this.hsthtoanMau = hsthtoanMau;
    }

    /**
     * @return the hsthtoanXntdcn
     */
    public Double getHsthtoanXntdcn() {
        return hsthtoanXntdcn;
    }

    /**
     * @param hsthtoanXntdcn the hsthtoanXntdcn to set
     */
    public void setHsthtoanXntdcn(Double hsthtoanXntdcn) {
        this.hsthtoanXntdcn = hsthtoanXntdcn;
    }

    /**
     * @return the hsthtoanCdha
     */
    public Double getHsthtoanCdha() {
        return hsthtoanCdha;
    }

    /**
     * @param hsthtoanCdha the hsthtoanCdha to set
     */
    public void setHsthtoanCdha(Double hsthtoanCdha) {
        this.hsthtoanCdha = hsthtoanCdha;
    }

    /**
     * @return the hsthtoanDvkttt
     */
    public Double getHsthtoanDvkttt() {
        return hsthtoanDvkttt;
    }

    /**
     * @param hsthtoanDvkttt the hsthtoanDvkttt to set
     */
    public void setHsthtoanDvkttt(Double hsthtoanDvkttt) {
        this.hsthtoanDvkttt = hsthtoanDvkttt;
    }

    /**
     * @return the hsthtoanDvktc
     */
    public Double getHsthtoanDvktc() {
        return hsthtoanDvktc;
    }

    /**
     * @param hsthtoanDvktc the hsthtoanDvktc to set
     */
    public void setHsthtoanDvktc(Double hsthtoanDvktc) {
        this.hsthtoanDvktc = hsthtoanDvktc;
    }

    /**
     * @return the hsthtoanCpvc
     */
    public Double getHsthtoanCpvc() {
        return hsthtoanCpvc;
    }

    /**
     * @param hsthtoanCpvc the hsthtoanCpvc to set
     */
    public void setHsthtoanCpvc(Double hsthtoanCpvc) {
        this.hsthtoanCpvc = hsthtoanCpvc;
    }

    /**
     * @return the hsthtoanLidothanhtoan
     */
    public String getHsthtoanLidothanhtoan() {
        return hsthtoanLidothanhtoan;
    }

    /**
     * @param hsthtoanLidothanhtoan the hsthtoanLidothanhtoan to set
     */
    public void setHsthtoanLidothanhtoan(String hsthtoanLidothanhtoan) {
        this.hsthtoanLidothanhtoan = hsthtoanLidothanhtoan;
    }

    public String getHsthtoanLanin() {
        return hsthtoanLanin;
    }

    public void setHsthtoanLanin(String hsthtoanLanin) {
        this.hsthtoanLanin = hsthtoanLanin;
    }
}


