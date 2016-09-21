/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
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
@Table(name = "PHIEU_DU_TRU")
@NamedQueries({})
public class PhieuDuTru implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PHIEUDT_MA", nullable = false)
    private String phieudtMa;
    @Column(name = "PHIEUDT_NGAY")
    @Temporal(TemporalType.DATE)
    private Date phieudtNgay;
    @Column(name = "PHIEUDT_NGAYDAU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieudtNgaydau;
    @Column(name = "PHIEUDT_NGAYCUOI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieudtNgaycuoi;
    @Column(name = "PHIEUDT_NGAYPHAT")
    @Temporal(TemporalType.DATE)
    private Date phieudtNgayphat;
    @Column(name = "PHIEUDT_LANIN")
    private Short phieudtLanin;
    @Column(name = "PHIEUDT_SVV1")
    private String phieudtSvv1;
    @Column(name = "PHIEUDT_SVV2")
    private String phieudtSvv2;
    @Column(name = "PHIEUDT_SVV3")
    private String phieudtSvv3;
    @Column(name = "PHIEUDT_SVV4")
    private String phieudtSvv4;
    @Column(name = "PHIEUDT_SVV5")
    private String phieudtSvv5;
    @Column(name = "PHIEUDT_SVV6")
    private String phieudtSvv6;
    @Column(name = "PHIEUDT_SVV7")
    private String phieudtSvv7;
    @Column(name = "PHIEUDT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phieudtNgaygiocn;
//    @OneToMany(mappedBy = "phieudtMa")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection;
//    @OneToMany(mappedBy = "phieudtMa1")
//    private Collection<PhieuXuatKho> phieuXuatKhoCollection1;
//    @OneToMany(mappedBy = "phieudtMa")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
    @JoinColumn(name = "DMLOAITHUOC_MASO", referencedColumnName = "DMLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmLoaiThuoc dmloaithuocMaso;
    @JoinColumn(name = "DMDOITUONG_MASO", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong dmdoituongMaso;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "DTDMNHANVIEN_LAPPHIEU", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienLapphieu;
    @JoinColumn(name = "DTDMNHANVIEN_BACSIKY", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienBacsiky;
    @JoinColumn(name = "PHIEUDT_MAKHO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa phieudtMakho;
    @JoinColumn(name = "DMPHANLOAITHUOC_MASO", referencedColumnName = "DMPHANLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanLoaiThuoc dmphanloaithuocMaso;
    @JoinColumn(name = "PHIEUDT_PHANKHO", referencedColumnName = "DTDMKHO_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmKho phieudtPhankho;
    /** phan biet giua linh thuoc cho tu truc va linh thuoc cho khoa phong*/
    @Column(name = "PHIEUDT_PHANBIET")
    private Integer phieudtPhanBiet;
    /** trang thai luu vao ton kho hay chua?*/
    @Column(name = "PHIEUDT_TRANGTHAI")
    private Integer phieudtTrangThai;
    @Column(name = "PHIEUDT_DAXUAT")
    private Boolean phieudtDaXuat;
    @Column(name = "PHIEUDT_LOAIPHIEU")
    private String phieudtLoaiPhieu;

    @JoinColumn(name = "DMTANG_MASO", referencedColumnName = "DMTANG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTang dmtangMaso;
    
//    @OneToMany(mappedBy = "phieudtMa")
//    private Collection<PhieuTraKho> phieuTraKhoCollection;
    
    
    
    public PhieuDuTru() {
    }

    public PhieuDuTru(String phieudtMa) {
        this.phieudtMa = phieudtMa;
    }

    public String getPhieudtMa() {
        return phieudtMa;
    }

    public void setPhieudtMa(String phieudtMa) {
        this.phieudtMa = phieudtMa;
    }

    public Date getPhieudtNgay() {
        return phieudtNgay;
    }

    public void setPhieudtNgay(Date phieudtNgay) {
        this.phieudtNgay = phieudtNgay;
    }

    public Date getPhieudtNgaydau() {
        return phieudtNgaydau;
    }

    public void setPhieudtNgaydau(Date phieudtNgaydau) {
        this.phieudtNgaydau = phieudtNgaydau;
    }

    public Date getPhieudtNgaycuoi() {
        return phieudtNgaycuoi;
    }

    public void setPhieudtNgaycuoi(Date phieudtNgaycuoi) {
        this.phieudtNgaycuoi = phieudtNgaycuoi;
    }

    public Date getPhieudtNgayphat() {
        return phieudtNgayphat;
    }

    public void setPhieudtNgayphat(Date phieudtNgayphat) {
        this.phieudtNgayphat = phieudtNgayphat;
    }

    public Short getPhieudtLanin() {
        return phieudtLanin;
    }

    public void setPhieudtLanin(Short phieudtLanin) {
        this.phieudtLanin = phieudtLanin;
    }

    public String getPhieudtSvv1() {
        return phieudtSvv1;
    }

    public void setPhieudtSvv1(String phieudtSvv1) {
        this.phieudtSvv1 = phieudtSvv1;
    }

    public String getPhieudtSvv2() {
        return phieudtSvv2;
    }

    public void setPhieudtSvv2(String phieudtSvv2) {
        this.phieudtSvv2 = phieudtSvv2;
    }

    public String getPhieudtSvv3() {
        return phieudtSvv3;
    }

    public void setPhieudtSvv3(String phieudtSvv3) {
        this.phieudtSvv3 = phieudtSvv3;
    }

    public String getPhieudtSvv4() {
        return phieudtSvv4;
    }

    public void setPhieudtSvv4(String phieudtSvv4) {
        this.phieudtSvv4 = phieudtSvv4;
    }

    public String getPhieudtSvv5() {
        return phieudtSvv5;
    }

    public void setPhieudtSvv5(String phieudtSvv5) {
        this.phieudtSvv5 = phieudtSvv5;
    }

    public String getPhieudtSvv6() {
        return phieudtSvv6;
    }

    public void setPhieudtSvv6(String phieudtSvv6) {
        this.phieudtSvv6 = phieudtSvv6;
    }

    public String getPhieudtSvv7() {
        return phieudtSvv7;
    }

    public void setPhieudtSvv7(String phieudtSvv7) {
        this.phieudtSvv7 = phieudtSvv7;
    }

    public Date getPhieudtNgaygiocn() {
        return phieudtNgaygiocn;
    }

    public void setPhieudtNgaygiocn(Date phieudtNgaygiocn) {
        this.phieudtNgaygiocn = phieudtNgaygiocn;
    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection() {
//        return phieuXuatKhoCollection;
//    }
//
//    public void setPhieuXuatKhoCollection(Collection<PhieuXuatKho> phieuXuatKhoCollection) {
//        this.phieuXuatKhoCollection = phieuXuatKhoCollection;
//    }

//    public Collection<PhieuXuatKho> getPhieuXuatKhoCollection1() {
//        return phieuXuatKhoCollection1;
//    }
//
//    public void setPhieuXuatKhoCollection1(Collection<PhieuXuatKho> phieuXuatKhoCollection1) {
//        this.phieuXuatKhoCollection1 = phieuXuatKhoCollection1;
//    }

//    public Collection<CtPhieuDt> getCtPhieuDtCollection() {
//        return ctPhieuDtCollection;
//    }
//
//    public void setCtPhieuDtCollection(Collection<CtPhieuDt> ctPhieuDtCollection) {
//        this.ctPhieuDtCollection = ctPhieuDtCollection;
//    }
    public DmLoaiThuoc getDmloaithuocMaso(boolean create) {
        if (create && dmloaithuocMaso == null) {
            dmloaithuocMaso = new DmLoaiThuoc();
        }
        return dmloaithuocMaso;
    }

    public DmLoaiThuoc getDmloaithuocMaso() {
        return dmloaithuocMaso;
    }

    public void setDmloaithuocMaso(DmLoaiThuoc dmloaithuocMaso) {
        this.dmloaithuocMaso = dmloaithuocMaso;
    }

    public DmDoiTuong getDmdoituongMaso(boolean create) {
        if (create && dmdoituongMaso == null) {
            dmdoituongMaso = new DmDoiTuong();
        }
        return dmdoituongMaso;
    }

    public DmDoiTuong getDmdoituongMaso() {
        return dmdoituongMaso;
    }

    public void setDmdoituongMaso(DmDoiTuong dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
        if (create && dmkhoaMaso == null) {
            dmkhoaMaso = new DmKhoa();
        }
        return dmkhoaMaso;
    }

    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
        if (create && dtdmnhanvienCn == null) {
            dtdmnhanvienCn = new DtDmNhanVien();
        }
        return dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public DtDmNhanVien getDtdmnhanvienLapphieu(boolean create) {
        if (create && dtdmnhanvienLapphieu == null) {
            dtdmnhanvienLapphieu = new DtDmNhanVien();
        }
        return dtdmnhanvienLapphieu;
    }

    public DtDmNhanVien getDtdmnhanvienLapphieu() {
        return dtdmnhanvienLapphieu;
    }

    public void setDtdmnhanvienLapphieu(DtDmNhanVien dtdmnhanvienLapphieu) {
        this.dtdmnhanvienLapphieu = dtdmnhanvienLapphieu;
    }

    public DtDmNhanVien getDtdmnhanvienBacsiky(boolean create) {
        if (create && dtdmnhanvienBacsiky == null) {
            dtdmnhanvienBacsiky = new DtDmNhanVien();
        }
        return dtdmnhanvienBacsiky;
    }

    public DtDmNhanVien getDtdmnhanvienBacsiky() {
        return dtdmnhanvienBacsiky;
    }

    public void setDtdmnhanvienBacsiky(DtDmNhanVien dtdmnhanvienBacsiky) {
        this.dtdmnhanvienBacsiky = dtdmnhanvienBacsiky;
    }

    public DmKhoa getPhieudtMakho(boolean create) {
        if (create && phieudtMakho == null) {
            phieudtMakho = new DmKhoa();
        }
        return phieudtMakho;
    }

    public DmKhoa getPhieudtMakho() {
        return phieudtMakho;
    }

    public void setPhieudtMakho(DmKhoa phieudtMakho) {
        this.phieudtMakho = phieudtMakho;
    }

    public DmPhanLoaiThuoc getDmphanloaithuocMaso(boolean create) {
        if (create && dmphanloaithuocMaso == null) {
            dmphanloaithuocMaso = new DmPhanLoaiThuoc();
        }
        return dmphanloaithuocMaso;
    }

    public DmPhanLoaiThuoc getDmphanloaithuocMaso() {
        return dmphanloaithuocMaso;
    }

    public void setDmphanloaithuocMaso(DmPhanLoaiThuoc dmphanloaithuocMaso) {
        this.dmphanloaithuocMaso = dmphanloaithuocMaso;
    }

    public DtDmKho getPhieudtPhankho(boolean create) {
        if (create && phieudtPhankho == null) {
            phieudtPhankho = new DtDmKho();
        }
        return phieudtPhankho;
    }

    public DtDmKho getPhieudtPhankho() {
        return phieudtPhankho;
    }

    public void setPhieudtPhankho(DtDmKho phieudtPhankho) {
        this.phieudtPhankho = phieudtPhankho;
    }

    public DmTang getDmtangMaso(boolean create) {
        if (create && dmtangMaso == null) {
            dmtangMaso = new DmTang();
        }
        return dmtangMaso;
    }

    public DmTang getDmtangMaso() {
        return dmtangMaso;
    }

    public void setDmtangMaso(DmTang dmtangMaso) {
        this.dmtangMaso = dmtangMaso;
    }

//    public Collection<PhieuTraKho> getPhieuTraKhoCollection() {
//        return phieuTraKhoCollection;
//    }
//
//    public void setPhieuTraKhoCollection(Collection<PhieuTraKho> phieuTraKhoCollection) {
//        this.phieuTraKhoCollection = phieuTraKhoCollection;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phieudtMa != null ? phieudtMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuDuTru)) {
            return false;
        }
        PhieuDuTru other = (PhieuDuTru) object;
        if ((this.phieudtMa == null && other.phieudtMa != null) || (this.phieudtMa != null && !this.phieudtMa.equals(other.phieudtMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.PhieuDuTru[phieudtMa=" + phieudtMa + "]";
    }

    public Integer getPhieudtPhanBiet() {
        return phieudtPhanBiet;
    }

    public void setPhieudtPhanBiet(Integer phieudtPhanBiet) {
        this.phieudtPhanBiet = phieudtPhanBiet;
    }

    public Integer getPhieudtTrangThai() {
        return phieudtTrangThai;
    }

    public void setPhieudtTrangThai(Integer phieudtTrangThai) {
        this.phieudtTrangThai = phieudtTrangThai;
    }

    public Boolean getPhieudtDaXuat() {
        return phieudtDaXuat;
    }

    public void setPhieudtDaXuat(Boolean phieudtDaXuat) {
        this.phieudtDaXuat = phieudtDaXuat;
    }
    
    public String getPhieudtLoaiPhieu() {
        return phieudtLoaiPhieu;
    }

    public void setPhieudtLoaiPhieu(String phieudtLoaiPhieu) {
        this.phieudtLoaiPhieu = phieudtLoaiPhieu;
    }
}


