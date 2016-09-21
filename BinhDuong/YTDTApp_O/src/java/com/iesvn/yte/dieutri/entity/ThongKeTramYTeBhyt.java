/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "THONG_KE_TRAM_Y_TE_BHYT")
public class ThongKeTramYTeBhyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THONG_KE_TRAM_Y_TE_BHYT")
    @SequenceGenerator(name = "THONG_KE_TRAM_Y_TE_BHYT", sequenceName = "THONG_KE_TRAM_Y_TE_BHYT_TKTRAM", allocationSize = 1)
    @Column(name = "TKTRAMYTEBHYT_MASO", nullable = false)
    private Integer tktramytebhytMaso;
    @Column(name = "TKTRAMYTEBHYT_THANG", nullable = false)
    private String tktramytebhytThang;
    @Column(name = "TKTRAMYTEBHYT_NAM", nullable = false)
    private String tktramytebhytNam;
    
    @Column(name = "TKTRAMYTEBHYT_SOTHEBHYT")
    private Double tktramytebhytSothebhyt;
    @Column(name = "DTDMTRAMYTEBHYT_SOLUOTKCB")
    private Double dtdmtramytebhytSoluotkcb;
    @Column(name = "DTDMTRAMYTEBHYT_SOTHUBHYT")
    private Double dtdmtramytebhytSothubhyt;
    @Column(name = "DTDMTRAMYTEBHYT_TONGCONG")
    private Double dtdmtramytebhytTongcong;
    @Column(name = "DTDMTRAMYTEBHYT_TIENKHAM")
    private Double dtdmtramytebhytTienkham;
    @Column(name = "DTDMTRAMYTEBHYT_THUOCHCDICH")
    private Double dtdmtramytebhytThuochcdich;
    @Column(name = "DTDMTRAMYTEBHYT_MAU")
    private Double dtdmtramytebhytMau;
    @Column(name = "DTDMTRAMYTEBHYT_XNTDCN")
    private Double dtdmtramytebhytXntdcn;
    @Column(name = "DTDMTRAMYTEBHYT_CDHA")
    private Double dtdmtramytebhytCdha;
    @Column(name = "DTDMTRAMYTEBHYT_PTTTPHCN")
    private Double dtdmtramytebhytPtttphcn;
    @Column(name = "DTDMTRAMYTEBHYT_DVKTC")
    private Double dtdmtramytebhytDvktc;
    @Column(name = "DTDMTRAMYTEBHYT_VTYTTIEUHAO")
    private Double dtdmtramytebhytVtyttieuhao;
    @Column(name = "DTDMTRAMYTEBHYT_CPVC")
    private Double dtdmtramytebhytCpvc;
    
    
    @Column(name = "DTDMTRAMYTEBHYT_BNCUNGCHITRA")
    private Double dtdmtramytebhytBncungchitra;
    
    
    @Column(name = "DTDMTRAMYTEBHYT_NOITRU")
    private Boolean dtdmtramytebhytNoiTru;
    
    @Column(name = "DTDMTRAMYTEBHYT_TTTTIEPSOLUOT")
    private Double dtdmtramytebhytTttructiepsoluot;
    @Column(name = "DTDMTRAMYTEBHYT_TTTTIEPCHIPHI")
    private Double dtdmtramytebhytTttructiepchiphi;
    @Column(name = "DTDMTRAMYTEBHYT_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtdmtramytebhytNgaygiocn;
    
  
     @JoinColumn(name = "DTDMNHANVIEN_CN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienCn;
    
    @JoinColumn(name = "DTDMNHOMBHYT_MASO", referencedColumnName = "DTDMNHOMBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhomBhyt dtdmnhombhytMaso;
    @JoinColumn(name = "DTDMTRAMYTEBHYT_MASO", referencedColumnName = "DTDMTRAMYTEBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmTramYTeBhyt dtdmtramytebhytMaso;

    public ThongKeTramYTeBhyt() {
    }

    public ThongKeTramYTeBhyt(Integer tktramytebhytMaso) {
        this.tktramytebhytMaso = tktramytebhytMaso;
    }

    public ThongKeTramYTeBhyt(Integer tktramytebhytMaso, String tktramytebhytThang, String tktramytebhytNam, Double tktramytebhytSothebhyt, Double dtdmtramytebhytSoluotkcb) {
        this.tktramytebhytMaso = tktramytebhytMaso;
        this.tktramytebhytThang = tktramytebhytThang;
        this.tktramytebhytNam = tktramytebhytNam;
        this.tktramytebhytSothebhyt = tktramytebhytSothebhyt;
        this.dtdmtramytebhytSoluotkcb = dtdmtramytebhytSoluotkcb;
    }


    public DtDmNhanVien getDtdmnhanvienCn(boolean create) {
        if(create && dtdmnhanvienCn == null) setDtdmnhanvienCn(new DtDmNhanVien());
return dtdmnhanvienCn;
        
    }


    public DtDmNhomBhyt getDtdmnhombhytMaso(boolean create ) {
       if(create && dtdmnhombhytMaso == null) setDtdmnhombhytMaso(new DtDmNhomBhyt());
return dtdmnhombhytMaso;
       
    }

    public DtDmTramYTeBhyt getDtdmtramytebhytMaso(boolean create) {
        if(create && dtdmtramytebhytMaso == null) setDtdmtramytebhytMaso(new DtDmTramYTeBhyt());
        return dtdmtramytebhytMaso;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getTktramytebhytMaso() != null ? getTktramytebhytMaso().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThongKeTramYTeBhyt)) {
            return false;
        }
        ThongKeTramYTeBhyt other = (ThongKeTramYTeBhyt) object;
        if ((this.getTktramytebhytMaso() == null && other.getTktramytebhytMaso() != null) || (this.getTktramytebhytMaso() != null && !this.tktramytebhytMaso.equals(other.tktramytebhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ThongKeTramYTeBhyt[tktramytebhytMaso=" + getTktramytebhytMaso() + "]";
    }

    public Integer getTktramytebhytMaso() {
        return tktramytebhytMaso;
    }

    public void setTktramytebhytMaso(Integer tktramytebhytMaso) {
        this.tktramytebhytMaso = tktramytebhytMaso;
    }

    public String getTktramytebhytThang() {
        return tktramytebhytThang;
    }

    public void setTktramytebhytThang(String tktramytebhytThang) {
        this.tktramytebhytThang = tktramytebhytThang;
    }

    public String getTktramytebhytNam() {
        return tktramytebhytNam;
    }

    public void setTktramytebhytNam(String tktramytebhytNam) {
        this.tktramytebhytNam = tktramytebhytNam;
    }

    public Double getTktramytebhytSothebhyt() {
        return tktramytebhytSothebhyt;
    }

    public void setTktramytebhytSothebhyt(Double tktramytebhytSothebhyt) {
        this.tktramytebhytSothebhyt = tktramytebhytSothebhyt;
    }

    public Double getDtdmtramytebhytSoluotkcb() {
        return dtdmtramytebhytSoluotkcb;
    }

    public void setDtdmtramytebhytSoluotkcb(Double dtdmtramytebhytSoluotkcb) {
        this.dtdmtramytebhytSoluotkcb = dtdmtramytebhytSoluotkcb;
    }

    public Double getDtdmtramytebhytSothubhyt() {
        return dtdmtramytebhytSothubhyt;
    }

    public void setDtdmtramytebhytSothubhyt(Double dtdmtramytebhytSothubhyt) {
        this.dtdmtramytebhytSothubhyt = dtdmtramytebhytSothubhyt;
    }

    public Double getDtdmtramytebhytTongcong() {
        return dtdmtramytebhytTongcong;
    }

    public void setDtdmtramytebhytTongcong(Double dtdmtramytebhytTongcong) {
        this.dtdmtramytebhytTongcong = dtdmtramytebhytTongcong;
    }

    public Double getDtdmtramytebhytTienkham() {
        return dtdmtramytebhytTienkham;
    }

    public void setDtdmtramytebhytTienkham(Double dtdmtramytebhytTienkham) {
        this.dtdmtramytebhytTienkham = dtdmtramytebhytTienkham;
    }

    public Double getDtdmtramytebhytThuochcdich() {
        return dtdmtramytebhytThuochcdich;
    }

    public void setDtdmtramytebhytThuochcdich(Double dtdmtramytebhytThuochcdich) {
        this.dtdmtramytebhytThuochcdich = dtdmtramytebhytThuochcdich;
    }

    public Double getDtdmtramytebhytMau() {
        return dtdmtramytebhytMau;
    }

    public void setDtdmtramytebhytMau(Double dtdmtramytebhytMau) {
        this.dtdmtramytebhytMau = dtdmtramytebhytMau;
    }

    public Double getDtdmtramytebhytXntdcn() {
        return dtdmtramytebhytXntdcn;
    }

    public void setDtdmtramytebhytXntdcn(Double dtdmtramytebhytXntdcn) {
        this.dtdmtramytebhytXntdcn = dtdmtramytebhytXntdcn;
    }

    public Double getDtdmtramytebhytCdha() {
        return dtdmtramytebhytCdha;
    }

    public void setDtdmtramytebhytCdha(Double dtdmtramytebhytCdha) {
        this.dtdmtramytebhytCdha = dtdmtramytebhytCdha;
    }

    public Double getDtdmtramytebhytPtttphcn() {
        return dtdmtramytebhytPtttphcn;
    }

    public void setDtdmtramytebhytPtttphcn(Double dtdmtramytebhytPtttphcn) {
        this.dtdmtramytebhytPtttphcn = dtdmtramytebhytPtttphcn;
    }

    public Double getDtdmtramytebhytDvktc() {
        return dtdmtramytebhytDvktc;
    }

    public void setDtdmtramytebhytDvktc(Double dtdmtramytebhytDvktc) {
        this.dtdmtramytebhytDvktc = dtdmtramytebhytDvktc;
    }

    public Double getDtdmtramytebhytVtyttieuhao() {
        return dtdmtramytebhytVtyttieuhao;
    }

    public void setDtdmtramytebhytVtyttieuhao(Double dtdmtramytebhytVtyttieuhao) {
        this.dtdmtramytebhytVtyttieuhao = dtdmtramytebhytVtyttieuhao;
    }

    public Double getDtdmtramytebhytCpvc() {
        return dtdmtramytebhytCpvc;
    }

    public void setDtdmtramytebhytCpvc(Double dtdmtramytebhytCpvc) {
        this.dtdmtramytebhytCpvc = dtdmtramytebhytCpvc;
    }

    public Double getDtdmtramytebhytBncungchitra() {
        return dtdmtramytebhytBncungchitra;
    }

    public void setDtdmtramytebhytBncungchitra(Double dtdmtramytebhytBncungchitra) {
        this.dtdmtramytebhytBncungchitra = dtdmtramytebhytBncungchitra;
    }

    public Boolean getDtdmtramytebhytNoiTru() {
        return dtdmtramytebhytNoiTru;
    }

    public void setDtdmtramytebhytNoiTru(Boolean dtdmtramytebhytNoiTru) {
        this.dtdmtramytebhytNoiTru = dtdmtramytebhytNoiTru;
    }

    public Double getDtdmtramytebhytTttructiepsoluot() {
        return dtdmtramytebhytTttructiepsoluot;
    }

    public void setDtdmtramytebhytTttructiepsoluot(Double dtdmtramytebhytTttructiepsoluot) {
        this.dtdmtramytebhytTttructiepsoluot = dtdmtramytebhytTttructiepsoluot;
    }

    public Double getDtdmtramytebhytTttructiepchiphi() {
        return dtdmtramytebhytTttructiepchiphi;
    }

    public void setDtdmtramytebhytTttructiepchiphi(Double dtdmtramytebhytTttructiepchiphi) {
        this.dtdmtramytebhytTttructiepchiphi = dtdmtramytebhytTttructiepchiphi;
    }

    public Date getDtdmtramytebhytNgaygiocn() {
        return dtdmtramytebhytNgaygiocn;
    }

    public void setDtdmtramytebhytNgaygiocn(Date dtdmtramytebhytNgaygiocn) {
        this.dtdmtramytebhytNgaygiocn = dtdmtramytebhytNgaygiocn;
    }

    public void setDtdmnhanvienCn(DtDmNhanVien dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

    public void setDtdmnhombhytMaso(DtDmNhomBhyt dtdmnhombhytMaso) {
        this.dtdmnhombhytMaso = dtdmnhombhytMaso;
    }

    public void setDtdmtramytebhytMaso(DtDmTramYTeBhyt dtdmtramytebhytMaso) {
        this.dtdmtramytebhytMaso = dtdmtramytebhytMaso;
    }

  

}
