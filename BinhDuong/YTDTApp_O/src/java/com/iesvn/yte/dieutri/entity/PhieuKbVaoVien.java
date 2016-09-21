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
 * @author bao.ttc
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "PHIEU_KB_VAO_VIEN")
@NamedQueries({@NamedQuery(name = "PhieuKbVaoVien.findAll", query = "SELECT p FROM PhieuKbVaoVien p"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvMa", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvMa = :pkbvvMa"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvQtbenhli", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvQtbenhli = :pkbvvQtbenhli"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvTiensubenhbt", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvTiensubenhbt = :pkbvvTiensubenhbt"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvTiensubenhgd", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvTiensubenhgd = :pkbvvTiensubenhgd"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvToanthan", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvToanthan = :pkbvvToanthan"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvCacbophan", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvCacbophan = :pkbvvCacbophan"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvTtketqualamsang", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvTtketqualamsang = :pkbvvTtketqualamsang"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvCdvaovien", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvCdvaovien = :pkbvvCdvaovien"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvDaxuly", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvDaxuly = :pkbvvDaxuly"), @NamedQuery(name = "PhieuKbVaoVien.findByPkbvvChuy", query = "SELECT p FROM PhieuKbVaoVien p WHERE p.pkbvvChuy = :pkbvvChuy")})
public class PhieuKbVaoVien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKBVV_MA")
    private String pkbvvMa;
    @Column(name = "PKBVV_QTBENHLI")
    private String pkbvvQtbenhli;
    @Column(name = "PKBVV_TIENSUBENHBT")
    private String pkbvvTiensubenhbt;
    @Column(name = "PKBVV_TIENSUBENHGD")
    private String pkbvvTiensubenhgd;
    @Column(name = "PKBVV_TOANTHAN")
    private String pkbvvToanthan;
    @Column(name = "PKBVV_CACBOPHAN")
    private String pkbvvCacbophan;
    @Column(name = "PKBVV_TTKETQUALAMSANG")
    private String pkbvvTtketqualamsang;
    @Column(name = "PKBVV_CDVAOVIEN")
    private String pkbvvCdvaovien;
    @Column(name = "PKBVV_DAXULY")
    private String pkbvvDaxuly;
    @Column(name = "PKBVV_CHUY")
    private String pkbvvChuy;
    @Column(name = "PKBVV_LYDOVAOVIEN")
    private String pkbvvLydovaovien;
    @JoinColumn(name = "PKBVV_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham pkbvvThamkham;

    public PhieuKbVaoVien() {
    }

    public PhieuKbVaoVien(String pkbvvMa) {
        this.pkbvvMa = pkbvvMa;
    }

    public String getPkbvvMa() {
        return pkbvvMa;
    }

    public void setPkbvvMa(String pkbvvMa) {
        this.pkbvvMa = pkbvvMa;
    }

    public String getPkbvvQtbenhli() {
        return pkbvvQtbenhli;
    }

    public void setPkbvvQtbenhli(String pkbvvQtbenhli) {
        this.pkbvvQtbenhli = pkbvvQtbenhli;
    }

    public String getPkbvvTiensubenhbt() {
        return pkbvvTiensubenhbt;
    }

    public void setPkbvvTiensubenhbt(String pkbvvTiensubenhbt) {
        this.pkbvvTiensubenhbt = pkbvvTiensubenhbt;
    }

    public String getPkbvvTiensubenhgd() {
        return pkbvvTiensubenhgd;
    }

    public void setPkbvvTiensubenhgd(String pkbvvTiensubenhgd) {
        this.pkbvvTiensubenhgd = pkbvvTiensubenhgd;
    }

    public String getPkbvvToanthan() {
        return pkbvvToanthan;
    }

    public void setPkbvvToanthan(String pkbvvToanthan) {
        this.pkbvvToanthan = pkbvvToanthan;
    }

    public String getPkbvvCacbophan() {
        return pkbvvCacbophan;
    }

    public void setPkbvvCacbophan(String pkbvvCacbophan) {
        this.pkbvvCacbophan = pkbvvCacbophan;
    }

    public String getPkbvvTtketqualamsang() {
        return pkbvvTtketqualamsang;
    }

    public void setPkbvvTtketqualamsang(String pkbvvTtketqualamsang) {
        this.pkbvvTtketqualamsang = pkbvvTtketqualamsang;
    }

    public String getPkbvvCdvaovien() {
        return pkbvvCdvaovien;
    }

    public void setPkbvvCdvaovien(String pkbvvCdvaovien) {
        this.pkbvvCdvaovien = pkbvvCdvaovien;
    }

    public String getPkbvvDaxuly() {
        return pkbvvDaxuly;
    }

    public void setPkbvvDaxuly(String pkbvvDaxuly) {
        this.pkbvvDaxuly = pkbvvDaxuly;
    }

    public String getPkbvvChuy() {
        return pkbvvChuy;
    }

    public void setPkbvvChuy(String pkbvvChuy) {
        this.pkbvvChuy = pkbvvChuy;
    }

    public String getPkbvvLydovaovien() {
        return pkbvvLydovaovien;
    }

    public void setPkbvvLydovaovien(String pkbvvLydovaovien) {
        this.pkbvvLydovaovien = pkbvvLydovaovien;
    }

    public ThamKham getPkbvvThamkham() {
        return pkbvvThamkham;
    }

    public ThamKham getPkbvvThamkham(Boolean create) {
        if(create&&pkbvvThamkham==null) pkbvvThamkham=new ThamKham();
        return pkbvvThamkham;
    }

    public void setPkbvvThamkham(ThamKham pkbvvThamkham) {
        this.pkbvvThamkham = pkbvvThamkham;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkbvvMa != null ? pkbvvMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuKbVaoVien)) {
            return false;
        }
        PhieuKbVaoVien other = (PhieuKbVaoVien) object;
        if ((this.pkbvvMa == null && other.pkbvvMa != null) || (this.pkbvvMa != null && !this.pkbvvMa.equals(other.pkbvvMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sourcepackage.PhieuKbVaoVien[pkbvvMa=" + pkbvvMa + "]";
    }

}
