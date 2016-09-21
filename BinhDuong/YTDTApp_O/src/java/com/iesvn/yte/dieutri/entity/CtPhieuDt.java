/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.entity.DmNhaSanXuat;
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
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CT_PHIEU_DT")
@NamedQueries({})
public class CtPhieuDt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT_PHIEU_DT_CTDT_MA")
    @SequenceGenerator(name = "CT_PHIEU_DT_CTDT_MA", sequenceName = "CT_PHIEU_DT_CTDT_MA_SEQ", allocationSize = 1)
    @Column(name = "CTDT_MA", nullable = false)
    private Integer ctdtMa;
    @Column(name = "CTDT_MALK")
    private String ctdtMalk;
    @Column(name = "CTDT_SOLUONG")
    private Double ctdtSoluong;
    @Column(name = "CTDT_DONGIA")
    private Double ctdtDongia;
    @Column(name = "CTDT_DONGIANHAP")
    private Double ctdtDongianhap;
    @Column(name = "CTDT_TRA")
    private Double ctdtTra;
    @Column(name = "CTDT_NAMNHAP")
    private String ctdtNamnhap;
    @Column(name = "CTDT_NGAYHANDUNG")
    private String ctdtNgayhandung;
    @Column(name = "CTDT_THANGHANDUNG")
    private String ctdtThanghandung;
    @Column(name = "CTDT_NAMHANDUNG")
    private String ctdtNamhandung;
    @Column(name = "CTDT_LO")
    private String ctdtLo;
    @Column(name = "CTDT_SODANGKY")
    private String ctdtSodangky;
    @Column(name = "CTDT_STT")
    private Integer ctdtStt;
    @Column(name = "CTDT_INCHU")
    private Boolean ctdtInchu;
    @Column(name = "CTDT_PHATBN")
    private Boolean ctdtPhatbn;
    @Column(name = "CTDT_LINHGOP")
    private Boolean ctdtLinhgop;
    @Column(name = "CTDT_LINHNGAY")
    @Temporal(TemporalType.DATE)
    private Date ctdtLinhngay;
    @Column(name = "CTDT_NGAYGIOCN")
    @Temporal(TemporalType.DATE)
    private Date ctdtNgaygiocn;
    @JoinColumn(name = "DMDOITUONG_MASO", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong dmdoituongMaso;
    @JoinColumn(name = "DMNCT_MASO", referencedColumnName = "DMNCT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonChuongTrinh dmnctMaso;
    @JoinColumn(name = "DMNGUONKINHPHI_MASO", referencedColumnName = "DMNGUONKINHPHI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNguonKinhPhi dmnguonkinhphiMaso;
    @JoinColumn(name = "DMNHASANXUAT_MASO", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat dmnhasanxuatMaso;
    @JoinColumn(name = "DMQUOCGIA_MASO", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia dmquocgiaMaso;
    @JoinColumn(name = "DMTHUOC_MASO", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc dmthuocMaso;
    @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    @JoinColumn(name = "PHIEUDT_MA", referencedColumnName = "PHIEUDT_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private PhieuDuTru phieudtMa;

    public CtPhieuDt() {
    }

    public CtPhieuDt(Integer ctdtMa) {
        this.ctdtMa = ctdtMa;
    }

    public Integer getCtdtMa() {
        return ctdtMa;
    }

    public void setCtdtMa(Integer ctdtMa) {
        this.ctdtMa = ctdtMa;
    }

    public String getCtdtMalk() {
        return ctdtMalk;
    }

    public void setCtdtMalk(String ctdtMalk) {
        this.ctdtMalk = ctdtMalk;
    }

    public Double getCtdtSoluong() {
        return ctdtSoluong;
    }

    public void setCtdtSoluong(Double ctdtSoluong) {
        this.ctdtSoluong = ctdtSoluong;
    }

    public Double getCtdtDongia() {
        return ctdtDongia;
    }

    public void setCtdtDongia(Double ctdtDongia) {
        this.ctdtDongia = ctdtDongia;
    }

    public Double getCtdtDongianhap() {
        return ctdtDongianhap;
    }

    public void setCtdtDongianhap(Double ctdtDongianhap) {
        this.ctdtDongianhap = ctdtDongianhap;
    }

    public Double getCtdtTra() {
        return ctdtTra;
    }

    public void setCtdtTra(Double ctdtTra) {
        this.ctdtTra = ctdtTra;
    }

    public String getCtdtNamnhap() {
        return ctdtNamnhap;
    }

    public void setCtdtNamnhap(String ctdtNamnhap) {
        this.ctdtNamnhap = ctdtNamnhap;
    }

    public String getCtdtNgayhandung() {
        return ctdtNgayhandung;
    }

    public void setCtdtNgayhandung(String ctdtNgayhandung) {
        this.ctdtNgayhandung = ctdtNgayhandung;
    }

    public String getCtdtThanghandung() {
        return ctdtThanghandung;
    }

    public void setCtdtThanghandung(String ctdtThanghandung) {
        this.ctdtThanghandung = ctdtThanghandung;
    }

    public String getCtdtNamhandung() {
        return ctdtNamhandung;
    }

    public void setCtdtNamhandung(String ctdtNamhandung) {
        this.ctdtNamhandung = ctdtNamhandung;
    }

    public String getCtdtLo() {
        return ctdtLo;
    }

    public void setCtdtLo(String ctdtLo) {
        this.ctdtLo = ctdtLo;
    }

    public String getCtdtSodangky() {
        return ctdtSodangky;
    }

    public void setCtdtSodangky(String ctdtSodangky) {
        this.ctdtSodangky = ctdtSodangky;
    }

    public Integer getCtdtStt() {
        return ctdtStt;
    }

    public void setCtdtStt(Integer ctdtStt) {
        this.ctdtStt = ctdtStt;
    }

    public Boolean getCtdtInchu() {
        return ctdtInchu;
    }

    public void setCtdtInchu(Boolean ctdtInchu) {
        this.ctdtInchu = ctdtInchu;
    }

    public Boolean getCtdtPhatbn() {
        return ctdtPhatbn;
    }

    public void setCtdtPhatbn(Boolean ctdtPhatbn) {
        this.ctdtPhatbn = ctdtPhatbn;
    }

    public Boolean getCtdtLinhgop() {
        return ctdtLinhgop;
    }

    public void setCtdtLinhgop(Boolean ctdtLinhgop) {
        this.ctdtLinhgop = ctdtLinhgop;
    }

    public Date getCtdtLinhngay() {
        return ctdtLinhngay;
    }

    public void setCtdtLinhngay(Date ctdtLinhngay) {
        this.ctdtLinhngay = ctdtLinhngay;
    }

    public Date getCtdtNgaygiocn() {
        return ctdtNgaygiocn;
    }

    public void setCtdtNgaygiocn(Date ctdtNgaygiocn) {
        this.ctdtNgaygiocn = ctdtNgaygiocn;
    }

    public DmDoiTuong getDmdoituongMaso(boolean create) {
if(create && dmdoituongMaso == null) dmdoituongMaso = new DmDoiTuong();
return dmdoituongMaso;
}
    public DmDoiTuong getDmdoituongMaso() {
        return dmdoituongMaso;
    }

    public void setDmdoituongMaso(DmDoiTuong dmdoituongMaso) {
        this.dmdoituongMaso = dmdoituongMaso;
    }

    public DmNguonChuongTrinh getDmnctMaso(boolean create) {
if(create && dmnctMaso == null) dmnctMaso = new DmNguonChuongTrinh();
return dmnctMaso;
}
    public DmNguonChuongTrinh getDmnctMaso() {
        return dmnctMaso;
    }

    public void setDmnctMaso(DmNguonChuongTrinh dmnctMaso) {
        this.dmnctMaso = dmnctMaso;
    }

    public DmNguonKinhPhi getDmnguonkinhphiMaso(boolean create) {
if(create && dmnguonkinhphiMaso == null) dmnguonkinhphiMaso = new DmNguonKinhPhi();
return dmnguonkinhphiMaso;
}
    public DmNguonKinhPhi getDmnguonkinhphiMaso() {
        return dmnguonkinhphiMaso;
    }

    public void setDmnguonkinhphiMaso(DmNguonKinhPhi dmnguonkinhphiMaso) {
        this.dmnguonkinhphiMaso = dmnguonkinhphiMaso;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso(boolean create) {
if(create && dmnhasanxuatMaso == null) dmnhasanxuatMaso = new DmNhaSanXuat();
return dmnhasanxuatMaso;
}
    public DmNhaSanXuat getDmnhasanxuatMaso() {
        return dmnhasanxuatMaso;
    }

    public void setDmnhasanxuatMaso(DmNhaSanXuat dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public DmQuocGia getDmquocgiaMaso(boolean create) {
if(create && dmquocgiaMaso == null) dmquocgiaMaso = new DmQuocGia();
return dmquocgiaMaso;
}
    public DmQuocGia getDmquocgiaMaso() {
        return dmquocgiaMaso;
    }

    public void setDmquocgiaMaso(DmQuocGia dmquocgiaMaso) {
        this.dmquocgiaMaso = dmquocgiaMaso;
    }

    public DmThuoc getDmthuocMaso(boolean create) {
if(create && dmthuocMaso == null) dmthuocMaso = new DmThuoc();
return dmthuocMaso;
}
    public DmThuoc getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(DmThuoc dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
if(create && dtdmnhanvienCn == null) dtdmnhanvienCn = new DtDmNhanVien();
return dtdmnhanvienCn;
}
    public DtDmNhanVien getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public PhieuDuTru getPhieudtMa(boolean create) {
if(create && phieudtMa == null) phieudtMa = new PhieuDuTru();
return phieudtMa;
}
    public PhieuDuTru getPhieudtMa() {
        return phieudtMa;
    }

    public void setPhieudtMa(PhieuDuTru phieudtMa) {
        this.phieudtMa = phieudtMa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctdtMa != null ? ctdtMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtPhieuDt)) {
            return false;
        }
        CtPhieuDt other = (CtPhieuDt) object;
        if ((this.ctdtMa == null && other.ctdtMa != null) || (this.ctdtMa != null && !this.ctdtMa.equals(other.ctdtMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.CtPhieuDt[ctdtMa=" + ctdtMa + "]";
    }
}


