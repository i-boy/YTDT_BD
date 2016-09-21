/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmThuoc;
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
@Table(name = "KET_QUA_MO")
@NamedQueries({})
public class KetQuaMo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KET_QUA_MO_KETQUAMO")
    @SequenceGenerator(name = "KET_QUA_MO_KETQUAMO", sequenceName = "KET_QUA_MO_KETQUAMO_MA_SEQ", allocationSize = 1)
    @Column(name = "KETQUAMO_MA", nullable = false)
    private Integer ketquamoMa;
    @Column(name = "NGAYGIOBATDAUMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaygiobatdaumo;
    @Column(name = "NGAYGIOKETTHUCMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaygioketthucmo;
    @Column(name = "DIENGIAI")
    private String diengiai;
    @Column(name = "PHANBIET")
    private String phanbiet;
    @Column(name = "MALOAI")
    private String maloai;
    @Column(name = "BANGGIA")
    private String banggia;
    @Column(name = "DGCD1")
    private String dgcd1;
    @Column(name = "TCHINH")
    private Double tchinh;
    @Column(name = "TCHINHS")
    private String tchinhs;
    @Column(name = "TPHU1")
    private Double tphu1;
    @Column(name = "TPHU1S")
    private String tphu1s;
    @Column(name = "TPHU2")
    private Double tphu2;
    @Column(name = "TPHU2S")
    private String tphu2s;
    @Column(name = "TGAYME1")
    private Double tgayme1;
    @Column(name = "TGAYME1S")
    private String tgayme1s;
    @Column(name = "TGAYME2")
    private Double tgayme2;
    @Column(name = "TGAYME2S")
    private String tgayme2s;
    @Column(name = "TDUNGCU1")
    private Double tdungcu1;
    @Column(name = "TDUNGCU1S")
    private String tdungcu1s;
    @Column(name = "TDUNGCU2")
    private Double tdungcu2;
    @Column(name = "TDUNGCU2S")
    private String tdungcu2s;
    @Column(name = "MAHOISUC1")
    private String mahoisuc1;
    @Column(name = "THOISUC")
    private Double thoisuc;
    @Column(name = "THOISUCS")
    private String thoisucs;
    @Column(name = "TYLE")
    private Double tyle;
    @Column(name = "MAPHONG")
    private String maphong;
    @Column(name = "KETQUA")
    private String ketqua;
    @Column(name = "TAIBIEN")
    private String taibien;
    @Column(name = "MACD2")
    private String macd2;
    @Column(name = "DGCD2")
    private String dgcd2;
    @Column(name = "DUNGSAI")
    private Boolean dungsai;
    @Column(name = "TINHTRANG")
    private String tinhtrang;
    @Column(name = "GOPCA")
    private Double gopca;
    @Column(name = "PHANLOAI")
    private String phanloai;
    @Column(name = "GHICHU")
    private String ghichu;
    @Column(name = "KHOATHUCHIEN")
    private String khoathuchien;
    @Column(name = "NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaygiocn;
    @JoinColumn(name = "HSBAMO_MA", referencedColumnName = "HSBAMO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaMo hsbamoMa;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "MACD1", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd macd1;
    @JoinColumn(name = "MACHINH", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien machinh;
    @JoinColumn(name = "MAPHU1", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien maphu1;
    @JoinColumn(name = "MAPHU2", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien maphu2;
    @JoinColumn(name = "MAGAYME1", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien magayme1;
    @JoinColumn(name = "MAGAYME2", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien magayme2;
    @JoinColumn(name = "MADUNGCU1", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc madungcu1;
    @JoinColumn(name = "MADUNGCU2", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc madungcu2;


    public KetQuaMo() {
    }

    public KetQuaMo(Integer ketquamoMa) {
        this.ketquamoMa = ketquamoMa;
    }

    public Integer getKetquamoMa() {
        return ketquamoMa;
    }

    public void setKetquamoMa(Integer ketquamoMa) {
        this.ketquamoMa = ketquamoMa;
    }

    public Date getNgaygiobatdaumo() {
        return ngaygiobatdaumo;
    }

    public void setNgaygiobatdaumo(Date ngaygiobatdaumo) {
        this.ngaygiobatdaumo = ngaygiobatdaumo;
    }

    public Date getNgaygioketthucmo() {
        return ngaygioketthucmo;
    }

    public void setNgaygioketthucmo(Date ngaygioketthucmo) {
        this.ngaygioketthucmo = ngaygioketthucmo;
    }

    public String getDiengiai() {
        return diengiai;
    }

    public void setDiengiai(String diengiai) {
        this.diengiai = diengiai;
    }

    public String getPhanbiet() {
        return phanbiet;
    }

    public void setPhanbiet(String phanbiet) {
        this.phanbiet = phanbiet;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getBanggia() {
        return banggia;
    }

    public void setBanggia(String banggia) {
        this.banggia = banggia;
    }

    public String getDgcd1() {
        return dgcd1;
    }

    public void setDgcd1(String dgcd1) {
        this.dgcd1 = dgcd1;
    }

    public Double getTchinh() {
        return tchinh;
    }

    public void setTchinh(Double tchinh) {
        this.tchinh = tchinh;
    }

    public String getTchinhs() {
        return tchinhs;
    }

    public void setTchinhs(String tchinhs) {
        this.tchinhs = tchinhs;
    }

    public Double getTphu1() {
        return tphu1;
    }

    public void setTphu1(Double tphu1) {
        this.tphu1 = tphu1;
    }

    public String getTphu1s() {
        return tphu1s;
    }

    public void setTphu1s(String tphu1s) {
        this.tphu1s = tphu1s;
    }

    public Double getTphu2() {
        return tphu2;
    }

    public void setTphu2(Double tphu2) {
        this.tphu2 = tphu2;
    }

    public String getTphu2s() {
        return tphu2s;
    }

    public void setTphu2s(String tphu2s) {
        this.tphu2s = tphu2s;
    }

    public Double getTgayme1() {
        return tgayme1;
    }

    public void setTgayme1(Double tgayme1) {
        this.tgayme1 = tgayme1;
    }

    public String getTgayme1s() {
        return tgayme1s;
    }

    public void setTgayme1s(String tgayme1s) {
        this.tgayme1s = tgayme1s;
    }

    public Double getTgayme2() {
        return tgayme2;
    }

    public void setTgayme2(Double tgayme2) {
        this.tgayme2 = tgayme2;
    }

    public String getTgayme2s() {
        return tgayme2s;
    }

    public void setTgayme2s(String tgayme2s) {
        this.tgayme2s = tgayme2s;
    }

    public Double getTdungcu1() {
        return tdungcu1;
    }

    public void setTdungcu1(Double tdungcu1) {
        this.tdungcu1 = tdungcu1;
    }

    public String getTdungcu1s() {
        return tdungcu1s;
    }

    public void setTdungcu1s(String tdungcu1s) {
        this.tdungcu1s = tdungcu1s;
    }

    public Double getTdungcu2() {
        return tdungcu2;
    }

    public void setTdungcu2(Double tdungcu2) {
        this.tdungcu2 = tdungcu2;
    }

    public String getTdungcu2s() {
        return tdungcu2s;
    }

    public void setTdungcu2s(String tdungcu2s) {
        this.tdungcu2s = tdungcu2s;
    }

    public String getMahoisuc1() {
        return mahoisuc1;
    }

    public void setMahoisuc1(String mahoisuc1) {
        this.mahoisuc1 = mahoisuc1;
    }

    public Double getThoisuc() {
        return thoisuc;
    }

    public void setThoisuc(Double thoisuc) {
        this.thoisuc = thoisuc;
    }

    public String getThoisucs() {
        return thoisucs;
    }

    public void setThoisucs(String thoisucs) {
        this.thoisucs = thoisucs;
    }

    public Double getTyle() {
        return tyle;
    }

    public void setTyle(Double tyle) {
        this.tyle = tyle;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public String getTaibien() {
        return taibien;
    }

    public void setTaibien(String taibien) {
        this.taibien = taibien;
    }

    public String getMacd2() {
        return macd2;
    }

    public void setMacd2(String macd2) {
        this.macd2 = macd2;
    }

    public String getDgcd2() {
        return dgcd2;
    }

    public void setDgcd2(String dgcd2) {
        this.dgcd2 = dgcd2;
    }

    public Boolean getDungsai() {
        return dungsai;
    }

    public void setDungsai(Boolean dungsai) {
        this.dungsai = dungsai;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public Double getGopca() {
        return gopca;
    }

    public void setGopca(Double gopca) {
        this.gopca = gopca;
    }

    public String getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(String phanloai) {
        this.phanloai = phanloai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getKhoathuchien() {
        return khoathuchien;
    }

    public void setKhoathuchien(String khoathuchien) {
        this.khoathuchien = khoathuchien;
    }

    public Date getNgaygiocn() {
        return ngaygiocn;
    }

    public void setNgaygiocn(Date ngaygiocn) {
        this.ngaygiocn = ngaygiocn;
    }

    public HsbaMo getHsbamoMa(boolean create) {
if(create && hsbamoMa == null) hsbamoMa = new HsbaMo();
return hsbamoMa;
}
    public HsbaMo getHsbamoMa() {
        return hsbamoMa;
    }

    public void setHsbamoMa(HsbaMo hsbamoMa) {
        this.hsbamoMa = hsbamoMa;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
return nhanvienMa;
}
    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public DmBenhIcd getMacd1(boolean create) {
if(create && macd1 == null) macd1 = new DmBenhIcd();
return macd1;
}
    public DmBenhIcd getMacd1() {
        return macd1;
    }

    public void setMacd1(DmBenhIcd macd1) {
        this.macd1 = macd1;
    }

    public DtDmNhanVien getMachinh(boolean create) {
if(create && machinh == null) machinh = new DtDmNhanVien();
return machinh;
}
    public DtDmNhanVien getMachinh() {
        return machinh;
    }

    public void setMachinh(DtDmNhanVien machinh) {
        this.machinh = machinh;
    }

    public DtDmNhanVien getMaphu1(boolean create) {
if(create && maphu1 == null) maphu1 = new DtDmNhanVien();
return maphu1;
}
    public DtDmNhanVien getMaphu1() {
        return maphu1;
    }

    public void setMaphu1(DtDmNhanVien maphu1) {
        this.maphu1 = maphu1;
    }

    public DtDmNhanVien getMaphu2(boolean create) {
if(create && maphu2 == null) maphu2 = new DtDmNhanVien();
return maphu2;
}
    public DtDmNhanVien getMaphu2() {
        return maphu2;
    }

    public void setMaphu2(DtDmNhanVien maphu2) {
        this.maphu2 = maphu2;
    }

    public DtDmNhanVien getMagayme1(boolean create) {
if(create && magayme1 == null) magayme1 = new DtDmNhanVien();
return magayme1;
}
    public DtDmNhanVien getMagayme1() {
        return magayme1;
    }

    public void setMagayme1(DtDmNhanVien magayme1) {
        this.magayme1 = magayme1;
    }

    public DtDmNhanVien getMagayme2(boolean create) {
if(create && magayme2 == null) magayme2 = new DtDmNhanVien();
return magayme2;
}
    public DtDmNhanVien getMagayme2() {
        return magayme2;
    }

    public void setMagayme2(DtDmNhanVien magayme2) {
        this.magayme2 = magayme2;
    }

    public DmThuoc getMadungcu1(boolean create) {
if(create && madungcu1 == null) madungcu1 = new DmThuoc();
return madungcu1;
}
    public DmThuoc getMadungcu1() {
        return madungcu1;
    }

    public void setMadungcu1(DmThuoc madungcu1) {
        this.madungcu1 = madungcu1;
    }

    public DmThuoc getMadungcu2(boolean create) {
if(create && madungcu2 == null) madungcu2 = new DmThuoc();
return madungcu2;
}
    public DmThuoc getMadungcu2() {
        return madungcu2;
    }

    public void setMadungcu2(DmThuoc madungcu2) {
        this.madungcu2 = madungcu2;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ketquamoMa != null ? ketquamoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KetQuaMo)) {
            return false;
        }
        KetQuaMo other = (KetQuaMo) object;
        if ((this.ketquamoMa == null && other.ketquamoMa != null) || (this.ketquamoMa != null && !this.ketquamoMa.equals(other.ketquamoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.KetQuaMo[ketquamoMa=" + ketquamoMa + "]";
    }

}


