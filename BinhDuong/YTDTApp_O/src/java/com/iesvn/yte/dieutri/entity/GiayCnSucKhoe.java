/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 *
 * @author QuynhNhu
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "GIAY_CN_SUC_KHOE")
@NamedQueries({})
public class GiayCnSucKhoe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GIAY_CN_SUC_KHOE")
    @SequenceGenerator(name = "GIAY_CN_SUC_KHOE", sequenceName = "GIAY_CN_SUC_KHOE_GCNSK_MA_SEQ", allocationSize = 1)
    @Column(name = "GCNSK_MA", nullable = false)
    private Integer gcnskMa;
    @Column(name = "GCNSK_BIDANH")
    private String gcnskBidanh;
    @Column(name = "GCNSK_QUEQUAN")
    private String gcnskQuequan;
    @Column(name = "GCNSK_TIENSUBANTHAN")
    private String gcnskTiensubanthan;
    @Column(name = "GCNSK_VONGNGUCTB")
    private Double gcnskVongnguctb;
    @Column(name = "GCNSK_LUCBOPTAYTHUAN")
    private Double gcnskLucboptaythuan;
    @Column(name = "GCNSK_LUCBOPTAYKHONGTHUAN")
    private Double gcnskLucboptaykhongthuan;
    @Column(name = "GCNSK_LUCKEOTHAN")
    private Double gcnskLuckeothan;
    @Column(name = "GCNSK_MATPHAIKHONGKIENG")
    private String gcnskMatphaikhongkieng;
    @Column(name = "GCNSK_MATPHAICOKIENG")
    private String gcnskMatphaicokieng;
    @Column(name = "GCNSK_MATTRAIKHONGKIENG")
    private String gcnskMattraikhongkieng;
    @Column(name = "GCNSK_MATTRAICOKIENG")
    private String gcnskMattraicokieng;
    @Column(name = "GCNSK_KIENGLOAI")
    private String gcnskKiengloai;
    @Column(name = "GCNSK_SO")
    private String gcnskSo;
    @Column(name = "GCNSK_SACGIAC")
    private String gcnskSacgiac;
    @Column(name = "GCNSK_BENHOMAT")
    private String gcnskBenhomat;
    @Column(name = "GCNSK_TAIPHAINGHENOIBT")
    private Double gcnskTaiphainghenoibt;
    @Column(name = "GCNSK_TAIPHAINGHENOITHAM")
    private Double gcnskTaiphainghenoitham;
    @Column(name = "GCNSK_TAITRAINGHENOIBT")
    private Double gcnskTaitrainghenoibt;
    @Column(name = "GCNSK_TAITRAINGHENOITHAM")
    private Double gcnskTaitrainghenoitham;
    @Column(name = "GCNSK_BENHTAI")
    private String gcnskBenhtai;
    @Column(name = "GCNSK_BENHMUI")
    private String gcnskBenhmui;
    @Column(name = "GCNSK_BENHHONG")
    private String gcnskBenhhong;
    @Column(name = "GCNSK_HAMTREN")
    private String gcnskHamtren;
    @Column(name = "GCNSK_HAMDUOI")
    private String gcnskHamduoi;
    @Column(name = "GCNSK_DONGKINH")
    private String gcnskDongkinh;
    @Column(name = "GCNSK_TELIET")
    private String gcnskTeliet;
    @Column(name = "GCNSK_PXTAY")
    private String gcnskPxtay;
    @Column(name = "GCNSK_PXCHAN")
    private String gcnskPxchan;
    @Column(name = "GCNSK_BENHTHANKINH")
    private String gcnskBenhthankinh;
    @Column(name = "GCNSK_BENHTAMTHAN")
    private String gcnskBenhtamthan;
    @Column(name = "GCNSK_BENHTIM")
    private String gcnskBenhtim;
    @Column(name = "GCNSK_BENHMACHMAU")
    private String gcnskBenhmachmau;
    @Column(name = "GCNSK_KHOP")
    private String gcnskKhop;
    @Column(name = "GCNSK_XUONGCO")
    private String gcnskXuongco;
    @Column(name = "GCNSK_HOHAP")
    private String gcnskHohap;
    @Column(name = "GCNSK_TIEUHOA")
    private String gcnskTieuhoa;
    @Column(name = "GCNSK_TIETNIEUSD")
    private String gcnskTietnieusd;
    @Column(name = "GCNSK_BENHNGOAIDADLHL")
    private String gcnskBenhngoaidadlhl;
    @Column(name = "GCNSK_CACBOPHANKHAC")
    private String gcnskCacbophankhac;
    @Column(name = "GCNSK_XNCANLAMSANG")
    private String gcnskXncanlamsang;
    @Column(name = "GCNSK_KETLUAN")
    private String gcnskKetluan;
    @JoinColumn(name = "GCNSK_BACSIKL", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien gcnskBacsikl;
    @JoinColumn(name = "GCNSK_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham gcnskThamkham;

    public GiayCnSucKhoe() {
    }

    public GiayCnSucKhoe(Integer gcnskMa) {
        this.gcnskMa = gcnskMa;
    }

    public Integer getGcnskMa() {
        return gcnskMa;
    }

    public void setGcnskMa(Integer gcnskMa) {
        this.gcnskMa = gcnskMa;
    }

    public String getGcnskBidanh() {
        return gcnskBidanh;
    }

    public void setGcnskBidanh(String gcnskBidanh) {
        this.gcnskBidanh = gcnskBidanh;
    }

    public String getGcnskQuequan() {
        return gcnskQuequan;
    }

    public void setGcnskQuequan(String gcnskQuequan) {
        this.gcnskQuequan = gcnskQuequan;
    }

    public String getGcnskTiensubanthan() {
        return gcnskTiensubanthan;
    }

    public void setGcnskTiensubanthan(String gcnskTiensubanthan) {
        this.gcnskTiensubanthan = gcnskTiensubanthan;
    }

    public Double getGcnskVongnguctb() {
        return gcnskVongnguctb;
    }

    public void setGcnskVongnguctb(Double gcnskVongnguctb) {
        this.gcnskVongnguctb = gcnskVongnguctb;
    }

    public Double getGcnskLucboptaythuan() {
        return gcnskLucboptaythuan;
    }

    public void setGcnskLucboptaythuan(Double gcnskLucboptaythuan) {
        this.gcnskLucboptaythuan = gcnskLucboptaythuan;
    }

    public Double getGcnskLucboptaykhongthuan() {
        return gcnskLucboptaykhongthuan;
    }

    public void setGcnskLucboptaykhongthuan(Double gcnskLucboptaykhongthuan) {
        this.gcnskLucboptaykhongthuan = gcnskLucboptaykhongthuan;
    }

    public Double getGcnskLuckeothan() {
        return gcnskLuckeothan;
    }

    public void setGcnskLuckeothan(Double gcnskLuckeothan) {
        this.gcnskLuckeothan = gcnskLuckeothan;
    }

    public String getGcnskMatphaikhongkieng() {
        return gcnskMatphaikhongkieng;
    }

    public void setGcnskMatphaikhongkieng(String gcnskMatphaikhongkieng) {
        this.gcnskMatphaikhongkieng = gcnskMatphaikhongkieng;
    }

    public String getGcnskMatphaicokieng() {
        return gcnskMatphaicokieng;
    }

    public void setGcnskMatphaicokieng(String gcnskMatphaicokieng) {
        this.gcnskMatphaicokieng = gcnskMatphaicokieng;
    }

    public String getGcnskMattraikhongkieng() {
        return gcnskMattraikhongkieng;
    }

    public void setGcnskMattraikhongkieng(String gcnskMattraikhongkieng) {
        this.gcnskMattraikhongkieng = gcnskMattraikhongkieng;
    }

    public String getGcnskMattraicokieng() {
        return gcnskMattraicokieng;
    }

    public void setGcnskMattraicokieng(String gcnskMattraicokieng) {
        this.gcnskMattraicokieng = gcnskMattraicokieng;
    }

    public String getGcnskKiengloai() {
        return gcnskKiengloai;
    }

    public void setGcnskKiengloai(String gcnskKiengloai) {
        this.gcnskKiengloai = gcnskKiengloai;
    }

    public String getGcnskSo() {
        return gcnskSo;
    }

    public void setGcnskSo(String gcnskSo) {
        this.gcnskSo = gcnskSo;
    }

    public String getGcnskSacgiac() {
        return gcnskSacgiac;
    }

    public void setGcnskSacgiac(String gcnskSacgiac) {
        this.gcnskSacgiac = gcnskSacgiac;
    }

    public String getGcnskBenhomat() {
        return gcnskBenhomat;
    }

    public void setGcnskBenhomat(String gcnskBenhomat) {
        this.gcnskBenhomat = gcnskBenhomat;
    }

    public Double getGcnskTaiphainghenoibt() {
        return gcnskTaiphainghenoibt;
    }

    public void setGcnskTaiphainghenoibt(Double gcnskTaiphainghenoibt) {
        this.gcnskTaiphainghenoibt = gcnskTaiphainghenoibt;
    }

    public Double getGcnskTaiphainghenoitham() {
        return gcnskTaiphainghenoitham;
    }

    public void setGcnskTaiphainghenoitham(Double gcnskTaiphainghenoitham) {
        this.gcnskTaiphainghenoitham = gcnskTaiphainghenoitham;
    }

    public Double getGcnskTaitrainghenoibt() {
        return gcnskTaitrainghenoibt;
    }

    public void setGcnskTaitrainghenoibt(Double gcnskTaitrainghenoibt) {
        this.gcnskTaitrainghenoibt = gcnskTaitrainghenoibt;
    }

    public Double getGcnskTaitrainghenoitham() {
        return gcnskTaitrainghenoitham;
    }

    public void setGcnskTaitrainghenoitham(Double gcnskTaitrainghenoitham) {
        this.gcnskTaitrainghenoitham = gcnskTaitrainghenoitham;
    }

    public String getGcnskBenhtai() {
        return gcnskBenhtai;
    }

    public void setGcnskBenhtai(String gcnskBenhtai) {
        this.gcnskBenhtai = gcnskBenhtai;
    }

    public String getGcnskBenhmui() {
        return gcnskBenhmui;
    }

    public void setGcnskBenhmui(String gcnskBenhmui) {
        this.gcnskBenhmui = gcnskBenhmui;
    }

    public String getGcnskBenhhong() {
        return gcnskBenhhong;
    }

    public void setGcnskBenhhong(String gcnskBenhhong) {
        this.gcnskBenhhong = gcnskBenhhong;
    }

    public String getGcnskHamtren() {
        return gcnskHamtren;
    }

    public void setGcnskHamtren(String gcnskHamtren) {
        this.gcnskHamtren = gcnskHamtren;
    }

    public String getGcnskHamduoi() {
        return gcnskHamduoi;
    }

    public void setGcnskHamduoi(String gcnskHamduoi) {
        this.gcnskHamduoi = gcnskHamduoi;
    }

    public String getGcnskDongkinh() {
        return gcnskDongkinh;
    }

    public void setGcnskDongkinh(String gcnskDongkinh) {
        this.gcnskDongkinh = gcnskDongkinh;
    }

    public String getGcnskTeliet() {
        return gcnskTeliet;
    }

    public void setGcnskTeliet(String gcnskTeliet) {
        this.gcnskTeliet = gcnskTeliet;
    }

    public String getGcnskPxtay() {
        return gcnskPxtay;
    }

    public void setGcnskPxtay(String gcnskPxtay) {
        this.gcnskPxtay = gcnskPxtay;
    }

    public String getGcnskPxchan() {
        return gcnskPxchan;
    }

    public void setGcnskPxchan(String gcnskPxchan) {
        this.gcnskPxchan = gcnskPxchan;
    }

    public String getGcnskBenhthankinh() {
        return gcnskBenhthankinh;
    }

    public void setGcnskBenhthankinh(String gcnskBenhthankinh) {
        this.gcnskBenhthankinh = gcnskBenhthankinh;
    }

    public String getGcnskBenhtamthan() {
        return gcnskBenhtamthan;
    }

    public void setGcnskBenhtamthan(String gcnskBenhtamthan) {
        this.gcnskBenhtamthan = gcnskBenhtamthan;
    }

    public String getGcnskBenhtim() {
        return gcnskBenhtim;
    }

    public void setGcnskBenhtim(String gcnskBenhtim) {
        this.gcnskBenhtim = gcnskBenhtim;
    }

    public String getGcnskBenhmachmau() {
        return gcnskBenhmachmau;
    }

    public void setGcnskBenhmachmau(String gcnskBenhmachmau) {
        this.gcnskBenhmachmau = gcnskBenhmachmau;
    }

    public String getGcnskKhop() {
        return gcnskKhop;
    }

    public void setGcnskKhop(String gcnskKhop) {
        this.gcnskKhop = gcnskKhop;
    }

    public String getGcnskXuongco() {
        return gcnskXuongco;
    }

    public void setGcnskXuongco(String gcnskXuongco) {
        this.gcnskXuongco = gcnskXuongco;
    }

    public String getGcnskHohap() {
        return gcnskHohap;
    }

    public void setGcnskHohap(String gcnskHohap) {
        this.gcnskHohap = gcnskHohap;
    }

    public String getGcnskTieuhoa() {
        return gcnskTieuhoa;
    }

    public void setGcnskTieuhoa(String gcnskTieuhoa) {
        this.gcnskTieuhoa = gcnskTieuhoa;
    }

    public String getGcnskTietnieusd() {
        return gcnskTietnieusd;
    }

    public void setGcnskTietnieusd(String gcnskTietnieusd) {
        this.gcnskTietnieusd = gcnskTietnieusd;
    }

    public String getGcnskBenhngoaidadlhl() {
        return gcnskBenhngoaidadlhl;
    }

    public void setGcnskBenhngoaidadlhl(String gcnskBenhngoaidadlhl) {
        this.gcnskBenhngoaidadlhl = gcnskBenhngoaidadlhl;
    }

    public String getGcnskCacbophankhac() {
        return gcnskCacbophankhac;
    }

    public void setGcnskCacbophankhac(String gcnskCacbophankhac) {
        this.gcnskCacbophankhac = gcnskCacbophankhac;
    }

    public String getGcnskXncanlamsang() {
        return gcnskXncanlamsang;
    }

    public void setGcnskXncanlamsang(String gcnskXncanlamsang) {
        this.gcnskXncanlamsang = gcnskXncanlamsang;
    }

    public String getGcnskKetluan() {
        return gcnskKetluan;
    }

    public void setGcnskKetluan(String gcnskKetluan) {
        this.gcnskKetluan = gcnskKetluan;
    }

    public DtDmNhanVien getGcnskBacsikl() {
        return gcnskBacsikl;
    }

    public DtDmNhanVien getGcnskBacsikl(Boolean create) {
        if (create && gcnskBacsikl == null) {
            gcnskBacsikl = new DtDmNhanVien();
        }
        return gcnskBacsikl;
    }

    public void setGcnskBacsikl(DtDmNhanVien gcnskBacsikl) {
        this.gcnskBacsikl = gcnskBacsikl;
    }

    public ThamKham getGcnskThamkham() {
        return gcnskThamkham;
    }

    public ThamKham getGcnskThamkham(Boolean create) {
        if (create && gcnskThamkham == null) {
            gcnskThamkham = new ThamKham();
        }
        return gcnskThamkham;
    }

    public void setGcnskThamkham(ThamKham gcnskThamkham) {
        this.gcnskThamkham = gcnskThamkham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gcnskMa != null ? gcnskMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiayCnSucKhoe)) {
            return false;
        }
        GiayCnSucKhoe other = (GiayCnSucKhoe) object;
        if ((this.gcnskMa == null && other.gcnskMa != null) || (this.gcnskMa != null && !this.gcnskMa.equals(other.gcnskMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sourcepackage.GiayCnSucKhoe[gcnskMa=" + gcnskMa + "]";
    }
}
