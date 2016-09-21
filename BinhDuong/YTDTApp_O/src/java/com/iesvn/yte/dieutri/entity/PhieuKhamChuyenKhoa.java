/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author QuynhNhu
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_KHAM_CHUYEN_KHOA")
@NamedQueries({@NamedQuery(name = "PhieuKhamChuyenKhoa.findAll", query = "SELECT p FROM PhieuKhamChuyenKhoa p"), @NamedQuery(name = "PhieuKhamChuyenKhoa.findByPkckMa", query = "SELECT p FROM PhieuKhamChuyenKhoa p WHERE p.pkckMa = :pkckMa"), @NamedQuery(name = "PhieuKhamChuyenKhoa.findByPkckKinhgoi", query = "SELECT p FROM PhieuKhamChuyenKhoa p WHERE p.pkckKinhgoi = :pkckKinhgoi"), @NamedQuery(name = "PhieuKhamChuyenKhoa.findByPkckYeucaukhamck", query = "SELECT p FROM PhieuKhamChuyenKhoa p WHERE p.pkckYeucaukhamck = :pkckYeucaukhamck")})
public class PhieuKhamChuyenKhoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKCK_MA")
    private String pkckMa;
    @Column(name = "PKCK_KINHGOI")
    private String pkckKinhgoi;
    @Column(name = "PKCK_YEUCAUKHAMCK")
    private String pkckYeucaukhamck;
    @JoinColumn(name = "PKCK_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham pkckThamkham;
    
    public PhieuKhamChuyenKhoa() {
    }

    public PhieuKhamChuyenKhoa(String pkckMa) {
        this.pkckMa = pkckMa;
    }

    public String getPkckMa() {
        return pkckMa;
    }

    public void setPkckMa(String pkckMa) {
        this.pkckMa = pkckMa;
    }

    public String getPkckKinhgoi() {
        return pkckKinhgoi;
    }

    public void setPkckKinhgoi(String pkckKinhgoi) {
        this.pkckKinhgoi = pkckKinhgoi;
    }

    public String getPkckYeucaukhamck() {
        return pkckYeucaukhamck;
    }

    public void setPkckYeucaukhamck(String pkckYeucaukhamck) {
        this.pkckYeucaukhamck = pkckYeucaukhamck;
    }

    public ThamKham getPkdtntThamkham() {
        return pkckThamkham;
    }

     public ThamKham getPkdtntThamkham(Boolean create) {
        if(create&&pkckThamkham==null) pkckThamkham=new ThamKham();
         return pkckThamkham;
    }

    public void setPkdtntThamkham(ThamKham pkckThamkham) {
        this.pkckThamkham = pkckThamkham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkckMa != null ? pkckMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuKhamChuyenKhoa)) {
            return false;
        }
        PhieuKhamChuyenKhoa other = (PhieuKhamChuyenKhoa) object;
        if ((this.pkckMa == null && other.pkckMa != null) || (this.pkckMa != null && !this.pkckMa.equals(other.pkckMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sourcepackage.PhieuKhamChuyenKhoa[pkckMa=" + pkckMa + "]";
    }

}
