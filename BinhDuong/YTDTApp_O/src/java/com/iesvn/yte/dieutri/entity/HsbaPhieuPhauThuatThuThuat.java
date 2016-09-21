/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_PHIEU_PHAU_THUAT_THUTHUAT")
@NamedQueries({
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findAll", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttMaso", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttMaso = :hsbapptttMaso"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttChandoan", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttChandoan = :hsbapptttChandoan"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttNgaycap", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttNgaycap = :hsbapptttNgaycap"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttNgaygiocn", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttNgaygiocn = :hsbapptttNgaygiocn"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttMa", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttMa = :hsbapptttMa"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttNgaycx", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttNgaycx = :hsbapptttNgaycx"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttNgayvaovien", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttNgayvaovien = :hsbapptttNgayvaovien"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttNgaypttt", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttNgaypttt = :hsbapptttNgaypttt"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttMotaLuocdo", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttMotaLuocdo = :hsbapptttMotaLuocdo"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttDanluu", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttDanluu = :hsbapptttDanluu"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttBac", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttBac = :hsbapptttBac"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttNgayrut", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttNgayrut = :hsbapptttNgayrut"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttNgaycatchi", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttNgaycatchi = :hsbapptttNgaycatchi"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttKhac", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttKhac = :hsbapptttKhac"),
    @NamedQuery(name = "HsbaPhieuPhauThuatThuThuat.findByHsbapptttTrinhtu", query = "SELECT h FROM HsbaPhieuPhauThuatThuThuat h WHERE h.hsbapptttTrinhtu = :hsbapptttTrinhtu")})
public class HsbaPhieuPhauThuatThuThuat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_PHIEU_PTTT")
    @SequenceGenerator(name = "HSBA_PHIEU_PTTT", sequenceName = "HSBA_PHIEU_PHAU_THUAT_THUTHU_1", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBAPPTTT_MASO")
    private Integer hsbapptttMaso;
    @Column(name = "HSBAPPTTT_CHANDOAN")
    private String hsbapptttChandoan;
    @Column(name = "HSBAPPTTT_NGAYCAP")
    @Temporal(TemporalType.DATE)
    private Date hsbapptttNgaycap;
    @Column(name = "HSBAPPTTT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapptttNgaygiocn;
    @Column(name = "HSBAPPTTT_MA")
    private String hsbapptttMa;
    @Column(name = "HSBAPPTTT_NGAYCX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapptttNgaycx;
    @Column(name = "HSBAPPTTT_NGAYVAOVIEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapptttNgayvaovien;
    @Column(name = "HSBAPPTTT_NGAYPTTT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapptttNgaypttt;
    @Column(name = "HSBAPPTTT_MOTA_LUOCDO")
    private String hsbapptttMotaLuocdo;
    @Column(name = "HSBAPPTTT_LOAIPTTT")
    private String hsbapptttLoaipttt;
    @Column(name = "HSBAPPTTT_DANLUU")
    private String hsbapptttDanluu;
    @Column(name = "HSBAPPTTT_BAC")
    private String hsbapptttBac;
    @Column(name = "HSBAPPTTT_NGAYRUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapptttNgayrut;
    @Column(name = "HSBAPPTTT_NGAYCATCHI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbapptttNgaycatchi;
    @Column(name = "HSBAPPTTT_KHAC")
    private String hsbapptttKhac;
    @Column(name = "HSBAPPTTT_TRINHTU")
    private String hsbapptttTrinhtu;
    @JoinColumn(name = "HSBAPPTTT_PHUONGPHAP_VOCAM", referencedColumnName = "DTDMVOCAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmVoCam hsbapptttPhuongphapVocam;
    @JoinColumn(name = "HSBAPPTTT_PHUONGPHAP", referencedColumnName = "DTDMCLSBG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmClsBangGia hsbapptttPhuongphap;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;
    @JoinColumn(name = "HSBAPPTTT_LOAI", referencedColumnName = "DTDMLOAIPT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLoaiPhauThuat hsbapptttLoai;
    @JoinColumn(name = "HSBA_SOVAOVIEN", referencedColumnName = "HSBA_SOVAOVIEN")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private Hsba hsbaSovaovien;
    @JoinColumn(name = "HSBAPPTTT_CHUANDOANMA_TRUOC", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbapptttChuandoanmaTruoc;
    @JoinColumn(name = "HSBAPPTTT_CHUANDOANMA_SAU", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd hsbapptttChuandoanmaSau;
    @JoinColumn(name = "HSBAPPTTT_BACSI_GMHS", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbapptttBacsiGmhs;
    @JoinColumn(name = "HSBAPPTTT_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbapptttBacsi;

    @JoinColumn(name = "HSBAPPTTT_KHOACHIDINH", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa hsbapptttKhoachidinh;

    public String getHsbapptttLoaipttt() {
        return hsbapptttLoaipttt;
    }

    public void setHsbapptttLoaipttt(String hsbapptttLoaipttt) {
        this.hsbapptttLoaipttt = hsbapptttLoaipttt;
    }

    public HsbaPhieuPhauThuatThuThuat() {
    }

    public HsbaPhieuPhauThuatThuThuat(Integer hsbapptttMaso) {
        this.hsbapptttMaso = hsbapptttMaso;
    }

    public Integer getHsbapptttMaso() {
        return hsbapptttMaso;
    }

    public void setHsbapptttMaso(Integer hsbapptttMaso) {
        this.hsbapptttMaso = hsbapptttMaso;
    }

    public String getHsbapptttChandoan() {
        return hsbapptttChandoan;
    }

    public void setHsbapptttChandoan(String hsbapptttChandoan) {
        this.hsbapptttChandoan = hsbapptttChandoan;
    }

    public Date getHsbapptttNgaycap() {
        return hsbapptttNgaycap;
    }

    public void setHsbapptttNgaycap(Date hsbapptttNgaycap) {
        this.hsbapptttNgaycap = hsbapptttNgaycap;
    }

    public Date getHsbapptttNgaygiocn() {
        return hsbapptttNgaygiocn;
    }

    public void setHsbapptttNgaygiocn(Date hsbapptttNgaygiocn) {
        this.hsbapptttNgaygiocn = hsbapptttNgaygiocn;
    }

    public String getHsbapptttMa() {
        return hsbapptttMa;
    }

    public void setHsbapptttMa(String hsbapptttMa) {
        this.hsbapptttMa = hsbapptttMa;
    }

    public Date getHsbapptttNgaycx() {
        return hsbapptttNgaycx;
    }

    public void setHsbapptttNgaycx(Date hsbapptttNgaycx) {
        this.hsbapptttNgaycx = hsbapptttNgaycx;
    }

    public Date getHsbapptttNgayvaovien() {
        return hsbapptttNgayvaovien;
    }

    public void setHsbapptttNgayvaovien(Date hsbapptttNgayvaovien) {
        this.hsbapptttNgayvaovien = hsbapptttNgayvaovien;
    }

    public Date getHsbapptttNgaypttt() {
        return hsbapptttNgaypttt;
    }

    public void setHsbapptttNgaypttt(Date hsbapptttNgaypttt) {
        this.hsbapptttNgaypttt = hsbapptttNgaypttt;
    }

    public String getHsbapptttMotaLuocdo() {
        return hsbapptttMotaLuocdo;
    }

    public void setHsbapptttMotaLuocdo(String hsbapptttMotaLuocdo) {
        this.hsbapptttMotaLuocdo = hsbapptttMotaLuocdo;
    }

    public String getHsbapptttDanluu() {
        return hsbapptttDanluu;
    }

    public void setHsbapptttDanluu(String hsbapptttDanluu) {
        this.hsbapptttDanluu = hsbapptttDanluu;
    }

    public String getHsbapptttBac() {
        return hsbapptttBac;
    }

    public void setHsbapptttBac(String hsbapptttBac) {
        this.hsbapptttBac = hsbapptttBac;
    }

    public Date getHsbapptttNgayrut() {
        return hsbapptttNgayrut;
    }

    public void setHsbapptttNgayrut(Date hsbapptttNgayrut) {
        this.hsbapptttNgayrut = hsbapptttNgayrut;
    }

    public Date getHsbapptttNgaycatchi() {
        return hsbapptttNgaycatchi;
    }

    public void setHsbapptttNgaycatchi(Date hsbapptttNgaycatchi) {
        this.hsbapptttNgaycatchi = hsbapptttNgaycatchi;
    }

    public String getHsbapptttKhac() {
        return hsbapptttKhac;
    }

    public void setHsbapptttKhac(String hsbapptttKhac) {
        this.hsbapptttKhac = hsbapptttKhac;
    }

    public String getHsbapptttTrinhtu() {
        return hsbapptttTrinhtu;
    }

    public void setHsbapptttTrinhtu(String hsbapptttTrinhtu) {
        this.hsbapptttTrinhtu = hsbapptttTrinhtu;
    }

    public Hsba getHsbaSovaovien() {
        return hsbaSovaovien;
    }

    public Hsba getHsbaSovaovien(boolean create) {
        if(create && hsbaSovaovien == null) hsbaSovaovien = new Hsba();
        return hsbaSovaovien;
    }

    public void setHsbaSovaovien(Hsba hsbaSovaovien) {
        this.hsbaSovaovien = hsbaSovaovien;
    }

   

    public DtDmNhanVien getHsbapptttBacsi() {
        return hsbapptttBacsi;
    }

    public DtDmNhanVien getHsbapptttBacsi(boolean create) {
        if(create && hsbapptttBacsi == null) hsbapptttBacsi = new DtDmNhanVien();
        return hsbapptttBacsi;
    }

    public void setHsbapptttBacsi(DtDmNhanVien hsbapptttBacsi) {
        this.hsbapptttBacsi = hsbapptttBacsi;
    }

    public DtDmNhanVien getHsbapptttBacsiGmhs() {
        return hsbapptttBacsiGmhs;
    }

    public DtDmNhanVien getHsbapptttBacsiGmhs(boolean create) {
        if(create && hsbapptttBacsiGmhs == null) hsbapptttBacsiGmhs = new DtDmNhanVien();
        return hsbapptttBacsiGmhs;
    }

    public void setHsbapptttBacsiGmhs(DtDmNhanVien hsbapptttBacsiGmhs) {
        this.hsbapptttBacsiGmhs = hsbapptttBacsiGmhs;
    }

    public DmBenhIcd getHsbapptttChuandoanmaSau() {
        return hsbapptttChuandoanmaSau;
    }

    public DmBenhIcd getHsbapptttChuandoanmaSau(boolean create) {
        if(create && hsbapptttChuandoanmaSau == null) hsbapptttChuandoanmaSau = new DmBenhIcd();
        return hsbapptttChuandoanmaSau;
    }

    public void setHsbapptttChuandoanmaSau(DmBenhIcd hsbapptttChuandoanmaSau) {
        this.hsbapptttChuandoanmaSau = hsbapptttChuandoanmaSau;
    }

    public DmBenhIcd getHsbapptttChuandoanmaTruoc() {
        return hsbapptttChuandoanmaTruoc;
    }

    public DmBenhIcd getHsbapptttChuandoanmaTruoc(boolean create) {
        if(create && hsbapptttChuandoanmaTruoc == null) hsbapptttChuandoanmaTruoc = new DmBenhIcd();
        return hsbapptttChuandoanmaTruoc;
    }

    public void setHsbapptttChuandoanmaTruoc(DmBenhIcd hsbapptttChuandoanmaTruoc) {
        this.hsbapptttChuandoanmaTruoc = hsbapptttChuandoanmaTruoc;
    }

    public DtDmLoaiPhauThuat getHsbapptttLoai() {
        return hsbapptttLoai;
    }

    public DtDmLoaiPhauThuat getHsbapptttLoai(boolean create) {
        if(create && hsbapptttLoai == null) hsbapptttLoai = new DtDmLoaiPhauThuat();
        return hsbapptttLoai;
    }

    public void setHsbapptttLoai(DtDmLoaiPhauThuat hsbapptttLoai) {
        this.hsbapptttLoai = hsbapptttLoai;
    }

    public DtDmClsBangGia getHsbapptttPhuongphap() {
        return hsbapptttPhuongphap;
    }

    public DtDmClsBangGia getHsbapptttPhuongphap(boolean create) {
         if(create && hsbapptttPhuongphap == null) hsbapptttPhuongphap = new DtDmClsBangGia();
        return hsbapptttPhuongphap;
    }

    public void setHsbapptttPhuongphap(DtDmClsBangGia hsbapptttPhuongphap) {
        this.hsbapptttPhuongphap = hsbapptttPhuongphap;
    }

    public DtDmVoCam getHsbapptttPhuongphapVocam() {
        return hsbapptttPhuongphapVocam;
    }

    public DtDmVoCam getHsbapptttPhuongphapVocam(boolean create) {
        if(create && hsbapptttPhuongphapVocam == null) hsbapptttPhuongphapVocam = new DtDmVoCam();
        return hsbapptttPhuongphapVocam;
    }
    
    public void setHsbapptttPhuongphapVocam(DtDmVoCam hsbapptttPhuongphapVocam) {
        this.hsbapptttPhuongphapVocam = hsbapptttPhuongphapVocam;
    }

    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
        if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }

    public DmKhoa getHsbapptttKhoachidinh() {
        return hsbapptttKhoachidinh;
    }

    public DmKhoa getHsbapptttKhoachidinh(boolean create) {
        if(create && hsbapptttKhoachidinh == null) hsbapptttKhoachidinh = new DmKhoa();
        return hsbapptttKhoachidinh;
    }

    public void setHsbapptttKhoachidinh(DmKhoa hsbapptttKhoachidinh) {
        this.hsbapptttKhoachidinh = hsbapptttKhoachidinh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbapptttMaso != null ? hsbapptttMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaPhieuPhauThuatThuThuat)) {
            return false;
        }
        HsbaPhieuPhauThuatThuThuat other = (HsbaPhieuPhauThuatThuThuat) object;
        if ((this.hsbapptttMaso == null && other.hsbapptttMaso != null) || (this.hsbapptttMaso != null && !this.hsbapptttMaso.equals(other.hsbapptttMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat[hsbapptttMaso=" + hsbapptttMaso + "]";
    }

}
