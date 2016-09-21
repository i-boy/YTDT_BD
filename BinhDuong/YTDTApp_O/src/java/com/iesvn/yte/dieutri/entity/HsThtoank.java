/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhIcd;
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
@Table(name = "HS_THTOANK")
@NamedQueries({})
public class HsThtoank implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "HSTHTOANK_MA", nullable = false)
    private String hsthtoankMa;
    @Column(name = "HSTHTOANK_TAMUNG")
    private Double hsthtoankTamung;
    @Column(name = "HSTHTOANK_HOANUNG")
    private Double hsthtoankHoanung;
    @Column(name = "HSTHTOANK_HOANTHU")
    private Double hsthtoankHoanthu;
    @Column(name = "HSTHTOANK_TONGCHI")
    private Double hsthtoankTongchi;
    @Column(name = "HSTHTOANK_DM")
    private Double hsthtoankDm;
    @Column(name = "HSTHTOANK_NDM")
    private Double hsthtoankNdm;
    @Column(name = "HSTHTOANK_MIENPHONG")
    private Double hsthtoankMienphong;
    @Column(name = "HSTHTOANK_MIENTHUOC")
    private Double hsthtoankMienthuoc;
    @Column(name = "HSTHTOANK_NGANSACH")
    private Double hsthtoankNgansach;
    @Column(name = "HSTHTOANK_KHONGTHU")
    private Double hsthtoankKhongthu;
    @Column(name = "HSTHTOANK_MIENTHUOCLAO")
    private Double hsthtoankMienthuoclao;
    @Column(name = "HSTHTOANK_MIENTE")
    private Double hsthtoankMiente;
    @Column(name = "HSTHTOANK_MIENDT")
    private Double hsthtoankMiendt;
    @Column(name = "HSTHTOANK_MIENMAU")
    private Double hsthtoankMienmau;
    @Column(name = "HSTHTOANK_XETGIAM")
    private Double hsthtoankXetgiam;
    @Column(name = "HSTHTOANK_THATTHU")
    private Double hsthtoankThatthu;
    @Column(name = "HSTHTOANK_LYDOTT")
    private String hsthtoankLydott;
    @Column(name = "HSTHTOANK_BHYT")
    private Double hsthtoankBhyt;
    @Column(name = "HSTHTOANK_BHXH")
    private Double hsthtoankBhxh;
    @Column(name = "HSTHTOANK_TYLEBH")
    private Short hsthtoankTylebh;
    @Column(name = "HSTHTOANK_BNTRA")
    private Double hsthtoankBntra;
    @Column(name = "HSTHTOANK_THTOAN")
    private Double hsthtoankThtoan;
    @Column(name = "HSTHTOANK_DATT")
    private Boolean hsthtoankDatt;
    @Column(name = "HSTHTOANK_THUOC")
    private Double hsthtoankThuoc;
    @Column(name = "HSTHTOANK_THUOCNDM")
    private Double hsthtoankThuocndm;
    @Column(name = "HSTHTOANK_VTTH")
    private Double hsthtoankVtth;
    @Column(name = "HSTHTOANK_VTTHNDM")
    private Double hsthtoankVtthndm;
    @Column(name = "HSTHTOANK_CLS")
    private Double hsthtoankCls;
    @Column(name = "HSTHTOANK_CLSNDM")
    private Double hsthtoankClsndm;
    @Column(name = "HSTHTOANK_PHAUTHUAT")
    private Double hsthtoankPhauthuat;
    @Column(name = "HSTHTOANK_PHAUTHUATNDM")
    private Double hsthtoankPhauthuatndm;
    @Column(name = "HSTHTOANK_PHONG")
    private Double hsthtoankPhong;
    @Column(name = "HSTHTOANK_PHONGNDM")
    private Double hsthtoankPhongndm;
    @Column(name = "HSTHTOANK_DIENMIEN")
    private Boolean hsthtoankDienmien;
    @Column(name = "HSTHTOANK_CONGKHAM")
    private Double hsthtoankCongkham;
    @Column(name = "HSTHTOANK_NGAYGIOTT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsthtoankNgaygiott;
    @Column(name = "HSTHTOANK_NGAYD")
    @Temporal(TemporalType.DATE)
    private Date hsthtoankNgayd;
    @Column(name = "HSTHTOANK_NGAYC")
    @Temporal(TemporalType.DATE)
    private Date hsthtoankNgayc;
    @Column(name = "HSTHTOANK_SOKHOA")
    private Short hsthtoankSokhoa;
   
    @Column(name = "HSTHTOANK_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsthtoankNgaygiocn;
    @Column(name = "HSTHTOANK_KYHIEU")
    private String hsthtoankKyhieu;
    @Column(name = "HSTHTOANK_QUYEN")
    private String hsthtoankQuyen;
    @Column(name = "HSTHTOANK_BIENLAI")
    private String hsthtoankBienlai;
    @Column(name = "HSTHTOANK_BOVIEN")
    private Boolean hsthtoankBovien;
    @JoinColumn(name = "TIEPDON_MA", referencedColumnName = "TIEPDON_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private TiepDon tiepdonMa;
    @JoinColumn(name = "HSTHTOANK_MABENH", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsthtoankMabenh;
    @JoinColumn(name = "HSTHTOANK_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsthtoankNhanviencn;
    @JoinColumn(name = "HSTHTOANK_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsthtoankThungan;
    
    
      @JoinColumn(name = "HSTHTOANK_CUM", referencedColumnName = "DTDMCUM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCum hsthtoankCum;
            
  
    @Column(name = "HSTHTOANK_MAU")
    private Double hsthtoankMau;

    @Column(name = "HSTHTOANK_XNTDCN")
    private Double hsthtoankXntdcn;

    @Column(name = "HSTHTOANK_CDHA")
    private Double hsthtoankCdha;

    @Column(name = "HSTHTOANK_DVKT_TT")
    private Double hsthtoankDvkttt;

    @Column(name = "HSTHTOANK_DV_KTC")
    private Double hsthtoankDvktc;

    @Column(name = "HSTHTOANK_CP_VC")
    private Double hsthtoankCpvc;
    
    @Column(name = "HSTHTOANK_LANIN")
    private String hsthtoankLanin;

    public HsThtoank() {
    }

    public HsThtoank(String hsthtoankMa) {
        this.hsthtoankMa = hsthtoankMa;
    }

    public String getHsthtoankMa() {
        return hsthtoankMa;
    }

    public void setHsthtoankMa(String hsthtoankMa) {
        this.hsthtoankMa = hsthtoankMa;
    }

    public Double getHsthtoankTamung() {
        return hsthtoankTamung;
    }

    public void setHsthtoankTamung(Double hsthtoankTamung) {
        this.hsthtoankTamung = hsthtoankTamung;
    }

    public Double getHsthtoankHoanung() {
        return hsthtoankHoanung;
    }

    public void setHsthtoankHoanung(Double hsthtoankHoanung) {
        this.hsthtoankHoanung = hsthtoankHoanung;
    }

    public Double getHsthtoankHoanthu() {
        return hsthtoankHoanthu;
    }

    public void setHsthtoankHoanthu(Double hsthtoankHoanthu) {
        this.hsthtoankHoanthu = hsthtoankHoanthu;
    }

    public Double getHsthtoankTongchi() {
        return hsthtoankTongchi;
    }

    public void setHsthtoankTongchi(Double hsthtoankTongchi) {
        this.hsthtoankTongchi = hsthtoankTongchi;
    }

    public Double getHsthtoankDm() {
        return hsthtoankDm;
    }

    public void setHsthtoankDm(Double hsthtoankDm) {
        this.hsthtoankDm = hsthtoankDm;
    }

    public Double getHsthtoankNdm() {
        return hsthtoankNdm;
    }

    public void setHsthtoankNdm(Double hsthtoankNdm) {
        this.hsthtoankNdm = hsthtoankNdm;
    }

    public Double getHsthtoankMienphong() {
        return hsthtoankMienphong;
    }

    public void setHsthtoankMienphong(Double hsthtoankMienphong) {
        this.hsthtoankMienphong = hsthtoankMienphong;
    }

    public Double getHsthtoankMienthuoc() {
        return hsthtoankMienthuoc;
    }

    public void setHsthtoankMienthuoc(Double hsthtoankMienthuoc) {
        this.hsthtoankMienthuoc = hsthtoankMienthuoc;
    }

    public Double getHsthtoankNgansach() {
        return hsthtoankNgansach;
    }

    public void setHsthtoankNgansach(Double hsthtoankNgansach) {
        this.hsthtoankNgansach = hsthtoankNgansach;
    }

    public Double getHsthtoankKhongthu() {
        return hsthtoankKhongthu;
    }

    public void setHsthtoankKhongthu(Double hsthtoankKhongthu) {
        this.hsthtoankKhongthu = hsthtoankKhongthu;
    }

    public Double getHsthtoankMienthuoclao() {
        return hsthtoankMienthuoclao;
    }

    public void setHsthtoankMienthuoclao(Double hsthtoankMienthuoclao) {
        this.hsthtoankMienthuoclao = hsthtoankMienthuoclao;
    }

    public Double getHsthtoankMiente() {
        return hsthtoankMiente;
    }

    public void setHsthtoankMiente(Double hsthtoankMiente) {
        this.hsthtoankMiente = hsthtoankMiente;
    }

    public Double getHsthtoankMiendt() {
        return hsthtoankMiendt;
    }

    public void setHsthtoankMiendt(Double hsthtoankMiendt) {
        this.hsthtoankMiendt = hsthtoankMiendt;
    }

    public Double getHsthtoankMienmau() {
        return hsthtoankMienmau;
    }

    public void setHsthtoankMienmau(Double hsthtoankMienmau) {
        this.hsthtoankMienmau = hsthtoankMienmau;
    }

    public Double getHsthtoankXetgiam() {
        return hsthtoankXetgiam;
    }

    public void setHsthtoankXetgiam(Double hsthtoankXetgiam) {
        this.hsthtoankXetgiam = hsthtoankXetgiam;
    }

    public Double getHsthtoankThatthu() {
        return hsthtoankThatthu;
    }

    public void setHsthtoankThatthu(Double hsthtoankThatthu) {
        this.hsthtoankThatthu = hsthtoankThatthu;
    }

    public String getHsthtoankLydott() {
        return hsthtoankLydott;
    }

    public void setHsthtoankLydott(String hsthtoankLydott) {
        this.hsthtoankLydott = hsthtoankLydott;
    }

    public Double getHsthtoankBhyt() {
        return hsthtoankBhyt;
    }

    public void setHsthtoankBhyt(Double hsthtoankBhyt) {
        this.hsthtoankBhyt = hsthtoankBhyt;
    }

    public Double getHsthtoankBhxh() {
        return hsthtoankBhxh;
    }

    public void setHsthtoankBhxh(Double hsthtoankBhxh) {
        this.hsthtoankBhxh = hsthtoankBhxh;
    }

    public Short getHsthtoankTylebh() {
        return hsthtoankTylebh;
    }

    public void setHsthtoankTylebh(Short hsthtoankTylebh) {
        this.hsthtoankTylebh = hsthtoankTylebh;
    }

    public Double getHsthtoankBntra() {
        return hsthtoankBntra;
    }

    public void setHsthtoankBntra(Double hsthtoankBntra) {
        this.hsthtoankBntra = hsthtoankBntra;
    }

    public Double getHsthtoankThtoan() {
        return hsthtoankThtoan;
    }

    public void setHsthtoankThtoan(Double hsthtoankThtoan) {
        this.hsthtoankThtoan = hsthtoankThtoan;
    }

    public Boolean getHsthtoankDatt() {
        return hsthtoankDatt;
    }

    public void setHsthtoankDatt(Boolean hsthtoankDatt) {
        this.hsthtoankDatt = hsthtoankDatt;
    }

    public Double getHsthtoankThuoc() {
        return hsthtoankThuoc;
    }

    public void setHsthtoankThuoc(Double hsthtoankThuoc) {
        this.hsthtoankThuoc = hsthtoankThuoc;
    }

    public Double getHsthtoankThuocndm() {
        return hsthtoankThuocndm;
    }

    public void setHsthtoankThuocndm(Double hsthtoankThuocndm) {
        this.hsthtoankThuocndm = hsthtoankThuocndm;
    }

    public Double getHsthtoankVtth() {
        return hsthtoankVtth;
    }

    public void setHsthtoankVtth(Double hsthtoankVtth) {
        this.hsthtoankVtth = hsthtoankVtth;
    }

    public Double getHsthtoankVtthndm() {
        return hsthtoankVtthndm;
    }

    public void setHsthtoankVtthndm(Double hsthtoankVtthndm) {
        this.hsthtoankVtthndm = hsthtoankVtthndm;
    }

    public Double getHsthtoankCls() {
        return hsthtoankCls;
    }

    public void setHsthtoankCls(Double hsthtoankCls) {
        this.hsthtoankCls = hsthtoankCls;
    }

    public Double getHsthtoankClsndm() {
        return hsthtoankClsndm;
    }

    public void setHsthtoankClsndm(Double hsthtoankClsndm) {
        this.hsthtoankClsndm = hsthtoankClsndm;
    }

    public Double getHsthtoankPhauthuat() {
        return hsthtoankPhauthuat;
    }

    public void setHsthtoankPhauthuat(Double hsthtoankPhauthuat) {
        this.hsthtoankPhauthuat = hsthtoankPhauthuat;
    }

    public Double getHsthtoankPhauthuatndm() {
        return hsthtoankPhauthuatndm;
    }

    public void setHsthtoankPhauthuatndm(Double hsthtoankPhauthuatndm) {
        this.hsthtoankPhauthuatndm = hsthtoankPhauthuatndm;
    }

    public Double getHsthtoankPhong() {
        return hsthtoankPhong;
    }

    public void setHsthtoankPhong(Double hsthtoankPhong) {
        this.hsthtoankPhong = hsthtoankPhong;
    }

    public Double getHsthtoankPhongndm() {
        return hsthtoankPhongndm;
    }

    public void setHsthtoankPhongndm(Double hsthtoankPhongndm) {
        this.hsthtoankPhongndm = hsthtoankPhongndm;
    }

    public Boolean getHsthtoankDienmien() {
        return hsthtoankDienmien;
    }

    public void setHsthtoankDienmien(Boolean hsthtoankDienmien) {
        this.hsthtoankDienmien = hsthtoankDienmien;
    }

    public Double getHsthtoankCongkham() {
        return hsthtoankCongkham;
    }

    public void setHsthtoankCongkham(Double hsthtoankCongkham) {
        this.hsthtoankCongkham = hsthtoankCongkham;
    }

    public Date getHsthtoankNgaygiott() {
        return hsthtoankNgaygiott;
    }

    public void setHsthtoankNgaygiott(Date hsthtoankNgaygiott) {
        this.hsthtoankNgaygiott = hsthtoankNgaygiott;
    }

    public Date getHsthtoankNgayd() {
        return hsthtoankNgayd;
    }

    public void setHsthtoankNgayd(Date hsthtoankNgayd) {
        this.hsthtoankNgayd = hsthtoankNgayd;
    }

    public Date getHsthtoankNgayc() {
        return hsthtoankNgayc;
    }

    public void setHsthtoankNgayc(Date hsthtoankNgayc) {
        this.hsthtoankNgayc = hsthtoankNgayc;
    }

    public Short getHsthtoankSokhoa() {
        return hsthtoankSokhoa;
    }

    public void setHsthtoankSokhoa(Short hsthtoankSokhoa) {
        this.hsthtoankSokhoa = hsthtoankSokhoa;
    }

    public DtDmCum getHsthtoankCum() {
        return hsthtoankCum;
    }

    public void setHsthtoankCum(DtDmCum hsthtoankCum) {
        this.hsthtoankCum = hsthtoankCum;
    }

    public Date getHsthtoankNgaygiocn() {
        return hsthtoankNgaygiocn;
    }

    public void setHsthtoankNgaygiocn(Date hsthtoankNgaygiocn) {
        this.hsthtoankNgaygiocn = hsthtoankNgaygiocn;
    }

    public String getHsthtoankKyhieu() {
        return hsthtoankKyhieu;
    }

    public void setHsthtoankKyhieu(String hsthtoankKyhieu) {
        this.hsthtoankKyhieu = hsthtoankKyhieu;
    }

    public String getHsthtoankQuyen() {
        return hsthtoankQuyen;
    }

    public void setHsthtoankQuyen(String hsthtoankQuyen) {
        this.hsthtoankQuyen = hsthtoankQuyen;
    }

    public String getHsthtoankBienlai() {
        return hsthtoankBienlai;
    }

    public void setHsthtoankBienlai(String hsthtoankBienlai) {
        this.hsthtoankBienlai = hsthtoankBienlai;
    }

    public Boolean getHsthtoankBovien() {
        return hsthtoankBovien;
    }

    public void setHsthtoankBovien(Boolean hsthtoankBovien) {
        this.hsthtoankBovien = hsthtoankBovien;
    }

    public TiepDon getTiepdonMa(boolean create) {
if(create && getTiepdonMa() == null) setTiepdonMa(new TiepDon());
return  getTiepdonMa();
}
    public TiepDon getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(TiepDon tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public DmBenhIcd getHsthtoankMabenh(boolean create) {
if(create && getHsthtoankMabenh() == null) setHsthtoankMabenh(new DmBenhIcd());
return  getHsthtoankMabenh();
}
    public DmBenhIcd getHsthtoankMabenh() {
        return hsthtoankMabenh;
    }

    public void setHsthtoankMabenh(DmBenhIcd hsthtoankMabenh) {
        this.hsthtoankMabenh = hsthtoankMabenh;
    }

    public DtDmNhanVien getHsthtoankNhanviencn(boolean create) {
if(create && getHsthtoankNhanviencn() == null) setHsthtoankNhanviencn(new DtDmNhanVien());
return  getHsthtoankNhanviencn();
}
    public DtDmNhanVien getHsthtoankNhanviencn() {
        return hsthtoankNhanviencn;
    }

    public void setHsthtoankNhanviencn(DtDmNhanVien hsthtoankNhanviencn) {
        this.hsthtoankNhanviencn = hsthtoankNhanviencn;
    }

    public DtDmNhanVien getHsthtoankThungan(boolean create) {
if(create && getHsthtoankThungan() == null) setHsthtoankThungan(new DtDmNhanVien());
return  getHsthtoankThungan();
}
    public DtDmNhanVien getHsthtoankThungan() {
        return hsthtoankThungan;
    }

    public void setHsthtoankThungan(DtDmNhanVien hsthtoankThungan) {
        this.hsthtoankThungan = hsthtoankThungan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHsthtoankMa() != null ? getHsthtoankMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsThtoank)) {
            return false;
        }
        HsThtoank other = (HsThtoank) object;
        if ((this.getHsthtoankMa() == null && other.getHsthtoankMa() != null) || (this.getHsthtoankMa() != null && !this.hsthtoankMa.equals(other.hsthtoankMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.HsThtoank[hsthtoankMa=" + getHsthtoankMa() + "]";
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
     * @return the hsthtoankMau
     */
    public Double getHsthtoankMau() {
        return hsthtoankMau;
    }

    /**
     * @param hsthtoankMau the hsthtoankMau to set
     */
    public void setHsthtoankMau(Double hsthtoankMau) {
        this.hsthtoankMau = hsthtoankMau;
    }

    /**
     * @return the hsthtoankXntdcn
     */
    public Double getHsthtoankXntdcn() {
        return hsthtoankXntdcn;
    }

    /**
     * @param hsthtoankXntdcn the hsthtoankXntdcn to set
     */
    public void setHsthtoankXntdcn(Double hsthtoankXntdcn) {
        this.hsthtoankXntdcn = hsthtoankXntdcn;
    }

    /**
     * @return the hsthtoankCdha
     */
    public Double getHsthtoankCdha() {
        return hsthtoankCdha;
    }

    /**
     * @param hsthtoankCdha the hsthtoankCdha to set
     */
    public void setHsthtoankCdha(Double hsthtoankCdha) {
        this.hsthtoankCdha = hsthtoankCdha;
    }

    /**
     * @return the hsthtoankDvkttt
     */
    public Double getHsthtoankDvkttt() {
        return hsthtoankDvkttt;
    }

    /**
     * @param hsthtoankDvkttt the hsthtoankDvkttt to set
     */
    public void setHsthtoankDvkttt(Double hsthtoankDvkttt) {
        this.hsthtoankDvkttt = hsthtoankDvkttt;
    }

    /**
     * @return the hsthtoankDvktc
     */
    public Double getHsthtoankDvktc() {
        return hsthtoankDvktc;
    }

    /**
     * @param hsthtoankDvktc the hsthtoankDvktc to set
     */
    public void setHsthtoankDvktc(Double hsthtoankDvktc) {
        this.hsthtoankDvktc = hsthtoankDvktc;
    }

    /**
     * @return the hsthtoankCpvc
     */
    public Double getHsthtoankCpvc() {
        return hsthtoankCpvc;
    }

    /**
     * @param hsthtoankCpvc the hsthtoankCpvc to set
     */
    public void setHsthtoankCpvc(Double hsthtoankCpvc) {
        this.hsthtoankCpvc = hsthtoankCpvc;
    }
    
    public String getHsthtoankLanin() {
        return hsthtoankLanin;
    }

    public void setHsthtoankLanin(String hsthtoankLanin) {
        this.hsthtoankLanin = hsthtoankLanin;
    }
}


