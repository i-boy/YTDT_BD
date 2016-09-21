/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.entity.DmDanToc;
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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "BENH_NHAN")
public class BenhNhan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "BENHNHAN_MA", nullable = false)
    private String benhnhanMa;
    @Column(name = "BENHNHAN_HOTEN")
    private String benhnhanHoten;
    @Column(name = "BENHNHAN_NGAYSINH")
    @Temporal(TemporalType.DATE)
    private Date benhnhanNgaysinh;
    @Column(name = "BENHNHAN_DIACHI")
    private String benhnhanDiachi;
    @Column(name = "BENHNHAN_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date benhnhanNgaygiocn;


    @Column(name = "BENHNHAN_CMND")
    private String benhnhanCmnd;


    @Column(name = "BENHNHAN_NAMSINH")
    private String benhnhanNamsinh;

    @Column(name = "BENHNHAN_TUOI")
    private Integer benhnhanTuoi;
    @Column(name = "BENHNHAN_DONVITUOI")
    private Short benhnhanDonvituoi;
    @Column(name = "BENHNHAN_HOTENCHAME")
    private String benhnhanHotenchame;
//    @OneToMany(mappedBy = "benhnhanMa")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "benhnhanMa")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "benhnhanMa1")
//    private Collection<Hsba> hsbaCollection1;
    @JoinColumn(name = "DANTOC_MA", referencedColumnName = "DMDANTOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDanToc dantocMa;
    @JoinColumn(name = "QUOCGIA_MA", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia quocgiaMa;
    @JoinColumn(name = "TINH_MA", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh tinhMa;
    @JoinColumn(name = "HUYEN_MA", referencedColumnName = "DMHUYEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmHuyen huyenMa;
    @JoinColumn(name = "XA_MA", referencedColumnName = "DMXA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmXa xaMa;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "BENHNHAN_NGHE", referencedColumnName = "DMNGHENGHIEP_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNgheNghiep benhnhanNghe;
    @JoinColumn(name = "DMGT_MASO", referencedColumnName = "DMGT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmGioiTinh dmgtMaso;

    public BenhNhan() {
    }

    public BenhNhan(String benhnhanMa) {
        this.benhnhanMa = benhnhanMa;
    }

    public String getBenhnhanMa() {
        return benhnhanMa;
    }

    public void setBenhnhanMa(String benhnhanMa) {
        this.benhnhanMa = benhnhanMa;
    }

    public String getBenhnhanHoten() {
        return benhnhanHoten;
    }

    public void setBenhnhanHoten(String benhnhanHoten) {
        this.benhnhanHoten = benhnhanHoten;
    }

    public Date getBenhnhanNgaysinh() {
        return benhnhanNgaysinh;
    }

    public void setBenhnhanNgaysinh(Date benhnhanNgaysinh) {
        this.benhnhanNgaysinh = benhnhanNgaysinh;
    }

    public String getBenhnhanDiachi() {
        return benhnhanDiachi;
    }

    public void setBenhnhanDiachi(String benhnhanDiachi) {
        this.benhnhanDiachi = benhnhanDiachi;
    }

    public Date getBenhnhanNgaygiocn() {
        return benhnhanNgaygiocn;
    }

    public void setBenhnhanNgaygiocn(Date benhnhanNgaygiocn) {
        this.benhnhanNgaygiocn = benhnhanNgaygiocn;
    }

    public String getBenhnhanCmnd() {
        return benhnhanCmnd;
    }

    public void setBenhnhanCmnd(String benhnhanCmnd) {
        this.benhnhanCmnd = benhnhanCmnd;
    }

    public Integer getBenhnhanTuoi() {
        return benhnhanTuoi;
    }

    public void setBenhnhanTuoi(Integer benhnhanTuoi) {
        this.benhnhanTuoi = benhnhanTuoi;
    }

    public Short getBenhnhanDonvituoi() {
        return benhnhanDonvituoi;
    }

    public void setBenhnhanDonvituoi(Short benhnhanDonvituoi) {
        this.benhnhanDonvituoi = benhnhanDonvituoi;
    }

    public String getBenhnhanHotenchame() {
        return benhnhanHotenchame;
    }

    public void setBenhnhanHotenchame(String benhnhanHotenchame) {
        this.benhnhanHotenchame = benhnhanHotenchame;
    }
    
//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
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
    
    public DmDanToc getDantocMa(boolean create) {
if(create && dantocMa == null) dantocMa = new DmDanToc();
return dantocMa;
}
    public DmDanToc getDantocMa() {
        return dantocMa;
    }

    public void setDantocMa(DmDanToc dantocMa) {
        this.dantocMa = dantocMa;
    }

    public DmQuocGia getQuocgiaMa(boolean create) {
if(create && quocgiaMa == null) quocgiaMa = new DmQuocGia();
return quocgiaMa;
}
    public DmQuocGia getQuocgiaMa() {
        return quocgiaMa;
    }

    public void setQuocgiaMa(DmQuocGia quocgiaMa) {
        this.quocgiaMa = quocgiaMa;
    }

    public DmTinh getTinhMa(boolean create) {
if(create && tinhMa == null) tinhMa = new DmTinh();
return tinhMa;
}
    public DmTinh getTinhMa() {
        return tinhMa;
    }

    public void setTinhMa(DmTinh tinhMa) {
        this.tinhMa = tinhMa;
    }

    public DmHuyen getHuyenMa(boolean create) {
if(create && huyenMa == null) huyenMa = new DmHuyen();
return huyenMa;
}
    public DmHuyen getHuyenMa() {
        return huyenMa;
    }

    public void setHuyenMa(DmHuyen huyenMa) {
        this.huyenMa = huyenMa;
    }

    public DmXa getXaMa(boolean create) {
if(create && xaMa == null) xaMa = new DmXa();
return xaMa;
}
    public DmXa getXaMa() {
        return xaMa;
    }

    public void setXaMa(DmXa xaMa) {
        this.xaMa = xaMa;
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

    public DmNgheNghiep getBenhnhanNghe(boolean create) {
if(create && benhnhanNghe == null) benhnhanNghe = new DmNgheNghiep();
return benhnhanNghe;
}
    public DmNgheNghiep getBenhnhanNghe() {
        return benhnhanNghe;
    }

    public void setBenhnhanNghe(DmNgheNghiep benhnhanNghe) {
        this.benhnhanNghe = benhnhanNghe;
    }

    public DmGioiTinh getDmgtMaso(boolean create) {
if(create && dmgtMaso == null) dmgtMaso = new DmGioiTinh();
return dmgtMaso;
}
    public DmGioiTinh getDmgtMaso() {
        return dmgtMaso;
    }

    public void setDmgtMaso(DmGioiTinh dmgtMaso) {
        this.dmgtMaso = dmgtMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (benhnhanMa != null ? benhnhanMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BenhNhan)) {
            return false;
        }
        BenhNhan other = (BenhNhan) object;
        if ((this.benhnhanMa == null && other.benhnhanMa != null) || (this.benhnhanMa != null && !this.benhnhanMa.equals(other.benhnhanMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.BenhNhan[benhnhanMa=" + benhnhanMa + "]";
    }

    /**
     * @return the benhnhanNamsinh
     */
    public String getBenhnhanNamsinh() {
        return benhnhanNamsinh;
    }

    /**
     * @param benhnhanNamsinh the benhnhanNamsinh to set
     */
    public void setBenhnhanNamsinh(String benhnhanNamsinh) {
        this.benhnhanNamsinh = benhnhanNamsinh;
    }
}


