/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.entity.DmThuoc;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "thuoc_noi_tru_xuat_vien")
@NamedQueries({})
public class ThuocNoiTruXuatVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THUOC_NOI_TRU_XUAT_VIEN")
    @SequenceGenerator(name = "THUOC_NOI_TRU_XUAT_VIEN", sequenceName = "THUOC_NOI_TRU_XUAT_VIEN_THUOCN", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "THUOCNOITRUXV_MA")
    private Integer thuocnoitruxvMa;
    @Column(name = "THUOCNOITRUXV_PHONG")
    private String thuocnoitruxvPhong;
    @Column(name = "THUOCNOITRUXV_BOSUNG")
    private String thuocnoitruxvBosung;
    @Column(name = "THUOCNOITRUXV_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocnoitruxvNgaygio;
    @Column(name = "THUOCNOITRUXV_LOAI")
    private String thuocnoitruxvLoai;
    @Column(name = "THUOCNOITRUXV_SOLUONG")
    private Double thuocnoitruxvSoluong;
    @Column(name = "THUOCNOITRUXV_CUM")
    private Short thuocnoitruxvCum;
    @Column(name = "THUOCNOITRUXV_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thuocnoitruxvNgaygiocn;
    @Column(name = "THUOCNOITRUXV_STT")
    private Integer thuocnoitruxvStt;

    @JoinColumn(name = "HSBA_KHOA", referencedColumnName = "HSBAKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private HsbaKhoa hsbaKhoa;
    @JoinColumn(name = "THUOCNOITRUXV_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocnoitruxvBacsi;
    @JoinColumn(name = "THUOCNOITRUXV_KHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa thuocnoitruxvKhoa;
    @JoinColumn(name = "THUOCNOITRUXV_MAPHONG", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia thuocnoitruxvMaphong;
    @JoinColumn(name = "THUOCNOITRUXV_MATHUOC", referencedColumnName = "DMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmThuoc thuocnoitruxvMathuoc;
    @JoinColumn(name = "THUOCNOITRUXV_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien thuocnoitruxvNhanviencn;
    @JoinColumn(name = "THUOCNOITRUXV_PHANLOAI", referencedColumnName = "DMPHANLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanLoaiThuoc thuocnoitruxvPhanloai;

    @Column(name = "THUOCNOITRUXV_CHIDANMA")
    private String thuocnoitruxvMaChidan;

    @Column(name = "THUOCNOITRUXV_CHIDAN")
    private String thuocnoitruxvTenchidan;

    public ThuocNoiTruXuatVien() {
    }

    public ThuocNoiTruXuatVien(Integer thuocnoitruxvMa) {
        this.thuocnoitruxvMa = thuocnoitruxvMa;
    }

    public Integer getThuocnoitruxvMa() {
        return thuocnoitruxvMa;
    }

    public void setThuocnoitruxvMa(Integer thuocnoitruxvMa) {
        this.thuocnoitruxvMa = thuocnoitruxvMa;
    }

    public String getThuocnoitruxvPhong() {
        return thuocnoitruxvPhong;
    }

    public void setThuocnoitruxvPhong(String thuocnoitruxvPhong) {
        this.thuocnoitruxvPhong = thuocnoitruxvPhong;
    }

    public String getThuocnoitruxvBosung() {
        return thuocnoitruxvBosung;
    }

    public void setThuocnoitruxvBosung(String thuocnoitruxvBosung) {
        this.thuocnoitruxvBosung = thuocnoitruxvBosung;
    }

    public Date getThuocnoitruxvNgaygio() {
        return thuocnoitruxvNgaygio;
    }

    public void setThuocnoitruxvNgaygio(Date thuocnoitruxvNgaygio) {
        this.thuocnoitruxvNgaygio = thuocnoitruxvNgaygio;
    }

    public String getThuocnoitruxvLoai() {
        return thuocnoitruxvLoai;
    }

    public void setThuocnoitruxvLoai(String thuocnoitruxvLoai) {
        this.thuocnoitruxvLoai = thuocnoitruxvLoai;
    }

    public Double getThuocnoitruxvSoluong() {
        return thuocnoitruxvSoluong;
    }

    public void setThuocnoitruxvSoluong(Double thuocnoitruxvSoluong) {
        this.thuocnoitruxvSoluong = thuocnoitruxvSoluong;
    }

    public Short getThuocnoitruxvCum() {
        return thuocnoitruxvCum;
    }

    public void setThuocnoitruxvCum(Short thuocnoitruxvCum) {
        this.thuocnoitruxvCum = thuocnoitruxvCum;
    }

    public Date getThuocnoitruxvNgaygiocn() {
        return thuocnoitruxvNgaygiocn;
    }

    public void setThuocnoitruxvNgaygiocn(Date thuocnoitruxvNgaygiocn) {
        this.thuocnoitruxvNgaygiocn = thuocnoitruxvNgaygiocn;
    }

    public Integer getThuocnoitruxvStt() {
        return thuocnoitruxvStt;
    }

    public void setThuocnoitruxvStt(Integer thuocnoitruxvStt) {
        this.thuocnoitruxvStt = thuocnoitruxvStt;
    }
    
    public DtDmNhanVien getThuocnoitruxvBacsi(boolean create) {
        if (create && thuocnoitruxvBacsi == null) {
            thuocnoitruxvBacsi = new DtDmNhanVien();
        }
        return thuocnoitruxvBacsi;
    }

    public DtDmNhanVien getThuocnoitruxvBacsi() {
        return thuocnoitruxvBacsi;
    }

    public void setThuocnoitruxvBacsi(DtDmNhanVien thuocnoitruxvBacsi) {
        this.thuocnoitruxvBacsi = thuocnoitruxvBacsi;
    }

    public DmKhoa getThuocnoitruxvKhoa(boolean create) {
        if (create && thuocnoitruxvKhoa == null) {
            thuocnoitruxvKhoa = new DmKhoa();
        }
        return thuocnoitruxvKhoa;
    }

    public DmKhoa getThuocnoitruxvKhoa() {
        return thuocnoitruxvKhoa;
    }

    public void setThuocnoitruxvKhoa(DmKhoa thuocnoitruxvKhoa) {
        this.thuocnoitruxvKhoa = thuocnoitruxvKhoa;
    }

    public DtDmClsBangGia getThuocnoitruxvMaphong(boolean create) {
        if (create && thuocnoitruxvMaphong == null) {
            thuocnoitruxvMaphong = new DtDmClsBangGia();
        }
        return thuocnoitruxvMaphong;
    }

    public DtDmClsBangGia getThuocnoitruxvMaphong() {
        return thuocnoitruxvMaphong;
    }

    public void setThuocnoitruxvMaphong(DtDmClsBangGia thuocnoitruxvMaphong) {
        this.thuocnoitruxvMaphong = thuocnoitruxvMaphong;
    }
    
    public DmThuoc getThuocnoitrxvuMathuoc(boolean create) {
        if (create && thuocnoitruxvMathuoc == null) {
            thuocnoitruxvMathuoc = new DmThuoc();
        }
        return thuocnoitruxvMathuoc;
    }

    public DmThuoc getThuocnoitruxvMathuoc() {
        return thuocnoitruxvMathuoc;
    }

    public void setThuocnoitruxvMathuoc(DmThuoc thuocnoitruxvMathuoc) {
        this.thuocnoitruxvMathuoc = thuocnoitruxvMathuoc;
    }

    public DtDmNhanVien getThuocnoitruxvNhanviencn(boolean create) {
        if (create && thuocnoitruxvNhanviencn == null) {
            thuocnoitruxvNhanviencn = new DtDmNhanVien();
        }
        return thuocnoitruxvNhanviencn;
    }
    
    public DtDmNhanVien getThuocnoitruxvNhanviencn() {
        return thuocnoitruxvNhanviencn;
    }

    public void setThuocnoitruxvNhanviencn(DtDmNhanVien thuocnoitruxvNhanviencn) {
        this.thuocnoitruxvNhanviencn = thuocnoitruxvNhanviencn;
    }

    public DmPhanLoaiThuoc getThuocnoitruxvPhanloai(boolean create) {
        if (create && thuocnoitruxvPhanloai == null) {
            thuocnoitruxvPhanloai = new DmPhanLoaiThuoc();
        }
        return thuocnoitruxvPhanloai;
    }

    public DmPhanLoaiThuoc getThuocnoitruxvPhanloai() {
        return thuocnoitruxvPhanloai;
    }

    public void setThuocnoitruxvPhanloai(DmPhanLoaiThuoc thuocnoitruxvPhanloai) {
        this.thuocnoitruxvPhanloai = thuocnoitruxvPhanloai;
    }

    public HsbaKhoa getHsbaKhoa() {
        return hsbaKhoa;
    }

    public void setHsbaKhoa(HsbaKhoa hsbaKhoa) {
        this.hsbaKhoa = hsbaKhoa;
    }

    public HsbaKhoa getHsbaKhoa(boolean create) {
        if (create && hsbaKhoa == null) {
            hsbaKhoa = new HsbaKhoa();
        }
        return hsbaKhoa;
    }

   public String getThuocnoitruxvMaChidan() {
        return thuocnoitruxvMaChidan;
    }

    public void setThuocnoitruxvMaChidan(String thuocnoitruxvMaChidan) {
        this.thuocnoitruxvMaChidan = thuocnoitruxvMaChidan;
    }

    public String getThuocnoitruxvTenchidan() {
        return thuocnoitruxvTenchidan;
    }

    public void setThuocnoitruxvTenchidan(String thuocnoitruxvTenchidan) {
        this.thuocnoitruxvTenchidan = thuocnoitruxvTenchidan;
    }

        @Override
    public int hashCode() {
        int hash = 0;
        hash += (thuocnoitruxvMa != null ? thuocnoitruxvMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThuocNoiTruXuatVien)) {
            return false;
        }
        ThuocNoiTruXuatVien other = (ThuocNoiTruXuatVien) object;
        if ((this.thuocnoitruxvMa == null && other.thuocnoitruxvMa != null) || (this.thuocnoitruxvMa != null && !this.thuocnoitruxvMa.equals(other.thuocnoitruxvMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien[thuocnoitruxvMa=" + thuocnoitruxvMa + "]";
    }

}
